package com.AventixPay.Aventix.service.serviceImpl;

import com.AventixPay.Aventix.DTO.PaymentRequest;
import com.AventixPay.Aventix.service.PaymentService;
import com.AventixPay.Aventix.service.RFIDService;
import com.AventixPay.Aventix.service.UserService;
import com.fazecast.jSerialComm.SerialPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service

public class RFIDServiceImpl implements RFIDService {

    private static final String PORT_NAME = "/dev/ttyACM0"; // port de lecture
    private static final int BAUD_RATE = 115200;
    private static final int READ_TIMEOUT = 2000; // Délai d'attente de 2000 ms
    private static final int BUFFER_SIZE = 2048; // Taille du buffer pour garantir que l'UID soit correctement capturé

    @Autowired
    UserService userService;

    @Autowired
    PaymentService paymentService;

    @Override
    public String readSerialNumberFromRFID(PaymentRequest paymentRequest) {
        SerialPort serialPort = SerialPort.getCommPort(PORT_NAME);
        serialPort.setBaudRate(BAUD_RATE);
        serialPort.setNumDataBits(8);
        serialPort.setNumStopBits(SerialPort.ONE_STOP_BIT);
        serialPort.setParity(SerialPort.NO_PARITY);
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, READ_TIMEOUT, 0);

        if (!serialPort.openPort()) {
            System.err.println("⚠ Impossible d'ouvrir le port série !");
            return null;
        }

        try {
            //Nettoyer le buffer
            clearResidualData(serialPort);

            //envoi du signal d'activation au lecteur de carte
            sendCommand(serialPort, "ACTIVER_LECTEUR\n");
            Thread.sleep(50); // Attendre un peu le temps de la lecture de l'uid par Arduino
            String receivedData = readFromSerial(serialPort);

            if (receivedData.isEmpty()) {
                System.err.println("⚠ Aucune donnée reçue !");
                return null;
            }
            System.out.println("UID reçu : " + receivedData);

            paymentRequest.setCardNumber(receivedData);

            paymentService.processPayment(paymentRequest);
            // Envoi de la confirmation de lecture à Arduino
            sendCommand(serialPort, "CONFIRMATION_LUE\n");
            return receivedData;
        } catch (Exception ex) {
            System.err.println("Erreur communication série : " + ex.getMessage());
            return null;
        } finally {
            serialPort.closePort();
        }
    }

    /**
     * lecture du port série
     */
    private String readFromSerial(SerialPort serialPort) {
        StringBuilder receivedData = new StringBuilder();
        long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime < READ_TIMEOUT) {
            if (serialPort.bytesAvailable() > 0) {
                byte[] buffer = new byte[BUFFER_SIZE];
                int numRead = serialPort.readBytes(buffer, buffer.length);
                receivedData.append(new String(Arrays.copyOf(buffer, numRead), StandardCharsets.UTF_8));
            }
        }
        return receivedData.toString().trim();
    }

    /**
     * Vide les données résiduelles dans le buffer du port série
     */
    private void clearResidualData(SerialPort serialPort) {
        try {
            byte[] tempBuffer = new byte[BUFFER_SIZE];
            while (serialPort.bytesAvailable() > 0) {
                serialPort.readBytes(tempBuffer, tempBuffer.length);
            }
        } catch (Exception e) {
            System.err.println("⚠ Erreur lors de la vidange du buffer : " + e.getMessage());
        }
    }

    /**
     * Envoie une commande série à l'Arduino
     */
    private void sendCommand(SerialPort serialPort, String command) {
        try {
            serialPort.getOutputStream().write(command.getBytes(StandardCharsets.UTF_8));
            serialPort.getOutputStream().flush();
            Thread.sleep(100); // Délai pour laisser l'Arduino traiter la commande
        } catch (Exception e) {
            System.err.println("⚠ Erreur d'envoi de commande : " + e.getMessage());
        }
    }

}


