package com.AventixPay.Aventix.XMLFile;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class XMLGenerator {

    // Générer un fichier XML en se basant sur la classe PaymentTransactionInfo qui contient l
    // les infos de la transaction

    public static void generateXMLFile(PaymentTransactionInfo paymentTransactionInfo, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(PaymentTransactionInfo.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(paymentTransactionInfo, new File(filePath));

            System.out.println("Fichier XML généré : " + filePath);
        } catch(JAXBException e) {

            e.printStackTrace();
        }
    }
}
