package org.example;

import java.io.File;
import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class readXML {
   public static void readXMLFile(File file) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new FileInputStream(file));

            document.getDocumentElement().normalize();

            NodeList nodeList = document.getElementsByTagName("record");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Element element = (Element) nodeList.item(temp);
                String name = element.getElementsByTagName("name").item(0).getTextContent();
                String postalZip = element.getElementsByTagName("postalZip").item(0).getTextContent();
                String region = element.getElementsByTagName("region").item(0).getTextContent();
                String country = element.getElementsByTagName("country").item(0).getTextContent();
                String address = element.getElementsByTagName("address").item(0).getTextContent();

                System.out.println("Name: " + name);
                System.out.println("Postal Zip: " + postalZip);
                System.out.println("Region: " + region);
                System.out.println("Country: " + country);
                System.out.println("Address: " + address);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}