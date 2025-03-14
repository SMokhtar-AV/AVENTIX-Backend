package com.AventixPay.Aventix.service.serviceImpl;

import com.AventixPay.Aventix.service.RFIDService;
import com.AventixPay.Aventix.service.UserService;
import com.fazecast.jSerialComm.SerialPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;

@Service

public class RFIDServiceImpl implements RFIDService {

    private static final String PORT_NAME = "COM5"; // port de lecture
    private static final int BAUD_RATE = 115200;
    private static final int READ_TIMEOUT = 2000; // Délai d'attente de 2000 ms
    private static final int BUFFER_SIZE = 2048; // Taille du buffer pour garantir que l'UID soit correctement capturé

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
            System.err.println("Impossible d'ouvrir le port série.");
            return null;
        }

        try {
            // Attente avant de commencer la lecture pour garantir une synchronisation
            try {
                Thread.sleep(500); // Délai de 500 ms avant de commencer la lecture
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Ignorer les données résiduelles dans le buffer
            ignoreResidualData(serialPort);
            // Lire les données depuis le port série COM5
            byte[] buffer = new byte[BUFFER_SIZE];
            Arrays.fill(buffer, (byte) 0); // Effacez le buffer avant la lecture

            int numRead = serialPort.readBytes(buffer, buffer.length);
            if (numRead > 0) {
                // Recupérer les données lues
                String uid = bytesToHex(Arrays.copyOf(buffer, numRead));
                Arrays.fill(buffer, (byte) 0); // Effacer les données du buffer après chaque lecture
                return uid;
            } else {
                System.err.println("Aucune donnée reçue du port.");
                return null;
            }
        } catch (Exception ex) {
            System.err.println("Erreur de communication série : " + ex.getMessage());
            return null;
        } finally {
            serialPort.closePort(); // Fermer le port série après chaque tentative
        }
    }

    // Méthode pour ignorer les données résiduelles dans le buffer du port série
    private void ignoreResidualData(SerialPort serialPort) {
        byte[] tempBuffer = new byte[BUFFER_SIZE];
        while (serialPort.bytesAvailable() > 0) {
            serialPort.readBytes(tempBuffer, tempBuffer.length);
        }
    }

    // Méthode pour convertir l'UID en hexadecimal
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }
}


