package ru.anarbek.cli;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import org.xml.sax.SAXException;
import ru.anarbek.constant.Argument;

public class OptionsBuilder {

    public static Options build() {
        String path = "src/main/resources/options.xml";
        ArrayList<Option> options = loadFromXML(path);
        return new Options(options);
    }

    private static ArrayList<Option> loadFromXML(final String path) {
        ArrayList<Option> records = new ArrayList<Option>();
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(path));
            NodeList nameList = document.getElementsByTagName("identifier");

            int lastId = 0;
            for (int i = 0; i < nameList.getLength(); i++) {
                Node tmp = nameList.item(i);
                NamedNodeMap attributes = tmp.getAttributes();
                int currentId = Integer.parseInt(attributes.getNamedItem("id").getTextContent());
                if (lastId < currentId) {
                    lastId = currentId;
                }
                Option option = new Option(
                        attributes.getNamedItem("name").getTextContent(),
                        attributes.getNamedItem("longName").getTextContent(),
                        attributes.getNamedItem("required").getTextContent().equals("Y"),
                        attributes.getNamedItem("description").getTextContent(),
                        Argument.valueOf(attributes.getNamedItem("argument").getTextContent())
                );
                records.add(option);
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return records;
    }
}
