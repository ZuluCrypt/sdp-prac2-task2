/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileInputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class App {
    public String getGreeting() {
            return "Hello World!";
            }
    public static void readXMLFile(File file, boolean[] selectedFields) {
        try {
            // Validate if the file exists
            if (!file.exists()) {
                System.err.println("Error: The specified file does not exist.");
                return;
            }

            // Parse XML file
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new FileInputStream(file));
            document.getDocumentElement().normalize();

            // Create ObjectMapper for JSON serialization
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode rootNode = objectMapper.createObjectNode();

            // Get list of <record> elements
            NodeList nodeList = document.getElementsByTagName("record");

            // Process each <record> element
            for (int temp = 0; temp < nodeList.getLength(); temp++) {
                Node node = nodeList.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    ObjectNode recordNode = objectMapper.createObjectNode();

                    // Populate record node with selected fields
                    if (selectedFields[0]) recordNode.put("name", getElementValue(element, "name"));
                    if (selectedFields[1]) recordNode.put("postalZip", getElementValue(element, "postalZip"));
                    if (selectedFields[2]) recordNode.put("region", getElementValue(element, "region"));
                    if (selectedFields[3]) recordNode.put("country", getElementValue(element, "country"));
                    if (selectedFields[4]) recordNode.put("address", getElementValue(element, "address"));

                    // Add record node to the root node
                    rootNode.set("record" + (temp + 1), recordNode);
                }
            }

            // Serialize the root node to JSON string
            String jsonOutput = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
            System.out.println(jsonOutput);

        } catch (Exception e) {
            // Handle any exceptions gracefully
            e.printStackTrace();
        }
    }

    // Helper method to get element value by tag name
    private static String getElementValue(Element element, String tagName) {
        NodeList nodeList = element.getElementsByTagName(tagName);
        if (nodeList.getLength() > 0) {
            return nodeList.item(0).getTextContent();
        }
        return null;
    }

    // Main method for testing
    public static void main(String[] args) {
        File file = new File("data.xml");
        boolean[] selectedFields = {true, true, true, true, true}; // Select all fields to display
        readXMLFile(file, selectedFields);
    }
}
