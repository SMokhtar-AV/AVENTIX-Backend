package com.AventixPay.Aventix.service.serviceImpl;

import com.AventixPay.Aventix.service.RFIDService;
import com.AventixPay.Aventix.service.UserService;
import com.fazecast.jSerialComm.SerialPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Service
public class RFIDServiceImpl implements RFIDService {

    private static final String PORT_NAME = "COM5"; // Port de communication avec l'Arduino
    private static final int BAUD_RATE = 115200;
    private static final int READ_TIMEOUT = 5000; // Augmenté à 5000 ms pour éviter les coupures
    private static final int BUFFER_SIZE = 256; // Taille du buffer ajustée

    @Autowired
    UserService userService;

    @Override
    public String readSerialNumberFromRFID() {
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
            // Activation du lecteur RFID
            sendCommand(serialPort, "ACTIVER_LECTEUR\n");

            // Lecture des données envoyées par l'Arduino
            byte[] buffer = new byte[BUFFER_SIZE];
            int numRead = serialPort.readBytes(buffer, buffer.length);

            if (numRead > 0) {
                // Conversion des données reçues en chaîne de caractères
                String uidRaw = new String(Arrays.copyOf(buffer, numRead), StandardCharsets.UTF_8).trim();

                // Nettoyage et conversion en hexadécimal
                String uidHex = formatToHex(uidRaw);
                System.out.println("UID RFID reçu : " + uidHex);

                return uidHex;
            } else {
                System.err.println("⚠ Aucune donnée reçue !");
                return null;
            }
        } catch (Exception ex) {
            System.err.println("Erreur communication série : " + ex.getMessage());
            return null;
        } finally {
            serialPort.closePort(); // On ferme le port après utilisation
        }
    }

    /**
     *  Envoie une commande série à l'Arduino
     */
    private void sendCommand(SerialPort serialPort, String command) {
        try {
            serialPort.getOutputStream().write(command.getBytes(StandardCharsets.UTF_8));
            serialPort.getOutputStream().flush();
            Thread.sleep(300); // Petit délai pour laisser l'Arduino répondre
        } catch (Exception e) {
            System.err.println("⚠ Erreur d'envoi de commande : " + e.getMessage());
        }
    }

    /**
     * Formate l'UID en hexadécimal proprement
     */
    private String formatToHex(String uid) {
        StringBuilder hexString = new StringBuilder();
        for (char c : uid.toCharArray()) {
            hexString.append(String.format("%02X", (byte) c));
        }
        return hexString.toString();
    }
}
