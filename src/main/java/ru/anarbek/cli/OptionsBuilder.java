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

    public static Options build() throws LoadFileException {
        String path = "src/main/resources/options.xml";

        ArrayList<Option> options = loadFromXML(path);
        return new Options(options);
    }

    protected static ArrayList<Option> loadFromXML(final String path) throws LoadFileException {
        ArrayList<Option> records = new ArrayList<>();
        try {
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(new File(path));
            NodeList nameList = document.getElementsByTagName("option");

            int lastId = 0;
            for (int i = 0; i < nameList.getLength(); i++) {
                Node tmp = nameList.item(i);
                NamedNodeMap attributes = tmp.getAttributes();
                int currentId = Integer.parseInt(attributes.getNamedItem("id").getTextContent());
                if (lastId < currentId) {
                    lastId = currentId;
                }

                //if the option has a parent
                int parentId = Integer.parseInt(attributes.getNamedItem("parent").getTextContent());
                Option parent = null;
                if (parentId >= 0) {
                    for (Option opt: records) {
                        if (opt.getId() == parentId) {
                            parent = opt;
                        }
                    }
                }

                //crate option
                Option option = new Option(
                        Integer.parseInt(attributes.getNamedItem("id").getTextContent()),
                        attributes.getNamedItem("name").getTextContent(),
                        attributes.getNamedItem("longName").getTextContent(),
                        attributes.getNamedItem("required").getTextContent().equals("Y"),
                        attributes.getNamedItem("hasData").getTextContent().equals("Y"),
                        attributes.getNamedItem("description").getTextContent(),
                        Argument.valueOf(attributes.getNamedItem("argument").getTextContent()),
                        parent
                );

                if (parent != null) {
                    //if the option has a parent, add children to the parent
                    parent.addChildren(option);
                }

                records.add(option);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new LoadFileException(e.getMessage());
        }

        return records;
    }
}
