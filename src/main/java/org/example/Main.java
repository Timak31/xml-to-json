package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import org.example.parse.DocumentProjectionFactory;
import org.example.roma.DocumentProjection;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, XPathExpressionException, SAXException, ParserConfigurationException {
        DocumentProjectionFactory factory = new DocumentProjectionFactory("src/main/resources/xml/big-file.xml");

        DocumentProjection projection = factory.getDocumentProjection();
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new Jdk8Module());
        String json = om.writerWithDefaultPrettyPrinter().writeValueAsString(projection);

        System.out.println(json);

    }
}
