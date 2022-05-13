package Egeszsegugy;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;

public class XmlReader {

    public static void main(String[] args) {
        ArrayList<Paciens> paciensek = readPaciensekFromXml("src/main/resources/paciensek.xml");
        System.out.println(paciensek.size());
        System.out.println(paciensek);
    }

    public static ArrayList<Paciens> readPaciensekFromXml(String filepath) {
        ArrayList<Paciens> paciensek = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);
            Element rootElement = document.getDocumentElement();
            NodeList childNodesList = rootElement.getChildNodes();
            int numberOfElementNodes = 0;
            Node node;
            for (int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    numberOfElementNodes++;
                    NodeList childNodesOfUserTag = node.getChildNodes();
                    String name = "", birthYear = "", address = "", korzet = "", allergia = " ", mutet = " ";
                    for (int j = 0; j < childNodesOfUserTag.getLength(); j++) {
                        if (childNodesOfUserTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodesOfUserTag.item(j).getNodeName()) {
                                case "name" -> name = childNodesOfUserTag.item(j).getTextContent();
                                case "address" -> address = childNodesOfUserTag.item(j).getTextContent();
                                case "birthYear" -> birthYear = childNodesOfUserTag.item(j).getTextContent();
                                case "korzet" -> korzet = childNodesOfUserTag.item(j).getTextContent();
                                case "allergia" -> allergia = childNodesOfUserTag.item(j).getTextContent();
                                case "mutet" -> mutet = childNodesOfUserTag.item(j).getTextContent();
                            }
                        }
                    }
                    paciensek.add(new Paciens(name, address, Integer.parseInt(birthYear),
                            Korzet.valueOf(korzet), allergia, mutet));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return paciensek;
    }
}