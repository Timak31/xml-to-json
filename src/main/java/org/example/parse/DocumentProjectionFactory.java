package org.example.parse;

import org.example.roma.DocumentProjection;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

public class DocumentProjectionFactory {

    private DocumentProjection documentProjection;

    public DocumentProjectionFactory() {
    }

    public DocumentProjectionFactory(String pathToXml) throws XPathExpressionException, SAXException, IOException, ParserConfigurationException {
        this.documentProjection = DocumentProjectionBuilder.newInstance(pathToXml);
    }

    public DocumentProjection getDocumentProjection() {
        return this.documentProjection;
    }

    public void setDocumentProjection(DocumentProjection documentProjection) {
        this.documentProjection = documentProjection;
    }
}
