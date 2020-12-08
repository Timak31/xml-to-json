package org.example.parse;

import org.example.roma.*;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.*;

public class DocumentProjectionBuilder {
    private static final Metadata metadata = new Metadata();
    private static ZonedDateTime createdAt = ZonedDateTime.now();
    private static final Map<MnecCnecTsoIdentification, CriticalBranchOutage> groupedCriticalBranches = new HashMap<>();
    private static final List<String> afterCOIdList = new ArrayList<>();
    private static final List<ComplexVariantStats> complexVariantStatList = new ArrayList<>();
    private static DocumentBuilder builder;
    private static Document xmlDocument;
    private static XPath xPath;

    protected DocumentProjectionBuilder() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
    }

    public static DocumentProjection newInstance(String pathToXml) throws IOException, SAXException, XPathExpressionException, ParserConfigurationException {
        new DocumentProjectionBuilder();
        File file = new File(pathToXml);
        xmlDocument = builder.parse(new FileInputStream(file));
        xPath = XPathFactory.newInstance().newXPath();

        createGroupedCriticalBranches();
        createComplexVariantStatList();
        createMetaData();
        createZonedDateTime();

        return new DocumentProjection(metadata, createdAt, groupedCriticalBranches, afterCOIdList, complexVariantStatList);
    }

    private static void createZonedDateTime() throws XPathExpressionException {
        createdAt = ZonedDateTime.parse((CharSequence) xPath.compile("FlowBasedConstraintDocument/CreationDateTime/@v").evaluate(xmlDocument, XPathConstants.STRING));
    }

    private static void createMetaData() throws XPathExpressionException {
        metadata.setSender((String) xPath.compile("FlowBasedConstraintDocument/SenderIdentification/@v").evaluate(xmlDocument, XPathConstants.STRING));
        metadata.setReceiver((String) xPath.compile("FlowBasedConstraintDocument/ReceiverIdentification/@v").evaluate(xmlDocument, XPathConstants.STRING));
    }

    private static void createComplexVariantStatList() throws XPathExpressionException {
        NodeList nodeVariantsList = (NodeList) xPath.compile("//complexVariant").evaluate(xmlDocument, XPathConstants.NODESET);

        for (int i = 1; i < nodeVariantsList.getLength()+1; i++) {
            complexVariantStatList.add(getStats(i));
        }
    }

    private static ComplexVariantStats getStats(int count) throws XPathExpressionException {
        ComplexVariantStats variant = new ComplexVariantStats();
        String complexPath = "FlowBasedConstraintDocument/complexVariants/complexVariant[" + count + "]";

        NodeList list = (NodeList) xPath.compile((complexPath + "/actionsSet/afterCOList/afterCOId")).evaluate(xmlDocument, XPathConstants.NODESET);
        for (int j = 0; j < list.getLength(); j++) {
            String afterCOId = list.item(j).getTextContent();
            afterCOIdList.add(afterCOId);
        }

        variant.setTsoOrigin(
                (String) xPath.compile((complexPath + "/tsoOrigin")).evaluate(xmlDocument, XPathConstants.STRING)
        );

        variant.setComplexVariant(
                (String) xPath.compile((complexPath + "/@id")).evaluate(xmlDocument, XPathConstants.STRING)
        );

        String actionType = (String) xPath.compile((complexPath + "/actionSet/action/@type")).evaluate(xmlDocument, XPathConstants.STRING);

        if (actionType.equals("PSTTAP")) {
            variant.setActionsQty(4);
        } else {
            variant.setActionsQty(1);
        }

        return variant;
    }

    private static void createGroupedCriticalBranches() throws XPathExpressionException {
        NodeList nodeBranchesList = (NodeList) xPath.compile("//criticalBranch").evaluate(xmlDocument, XPathConstants.NODESET);

        for (int i = 1; i < nodeBranchesList.getLength()+1; i++) {
            groupedCriticalBranches.put(
                    getMnecCnecTsoIdentification(i), getBranch(i)
            );
        }
    }

    private static MnecCnecTsoIdentification getMnecCnecTsoIdentification(int count) throws XPathExpressionException {
        MnecCnecTsoIdentification identification = new MnecCnecTsoIdentification();
        String criticalBranchPath = "FlowBasedConstraintDocument/criticalBranches/criticalBranch[" + count + "]";

        boolean mnec = Boolean.parseBoolean((String) xPath.compile((criticalBranchPath + "/MNEC")).evaluate(xmlDocument, XPathConstants.STRING));
        boolean cnec = Boolean.parseBoolean((String) xPath.compile((criticalBranchPath + "/CNEC")).evaluate(xmlDocument, XPathConstants.STRING));
        TsoOrigin tsoOrigin = TsoOrigin.valueOf((String) xPath.compile((criticalBranchPath + "/tsoOrigin")).evaluate(xmlDocument, XPathConstants.STRING));

        identification.setMnec(mnec);
        identification.setCnec(cnec);
        identification.setTsoOrigin(tsoOrigin);

        return identification;
    }

    private static CriticalBranchOutage getBranch(int count) throws XPathExpressionException {
        CriticalBranchOutage branch = new CriticalBranchOutage();
        String stringForXPath = "FlowBasedConstraintDocument/criticalBranches/criticalBranch[" + count + "]";
        branch.setCbId(
                (String) xPath.compile((stringForXPath + "/@id")).evaluate(xmlDocument, XPathConstants.STRING)
        );
        branch.setCbName(
                (String) xPath.compile((stringForXPath + "/branch/@name")).evaluate(xmlDocument, XPathConstants.STRING)
        );
        branch.setOutageName(
                (String) xPath.compile((stringForXPath + "/outage/@name")).evaluate(xmlDocument, XPathConstants.STRING)
        );

        String from = (String) xPath.compile((stringForXPath + "/outage/branch/@from"))
                .evaluate(xmlDocument, XPathConstants.STRING);
        branch.setFrom(
                Optional.ofNullable(from)
        );
        String to = (String) xPath.compile((stringForXPath + "/outage/branch/@to"))
                .evaluate(xmlDocument, XPathConstants.STRING);
        branch.setTo(
                Optional.ofNullable(to)
        );

        return branch;
    }
}
