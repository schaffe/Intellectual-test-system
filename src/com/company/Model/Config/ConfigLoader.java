package com.company.Model.Config;

import com.company.Log;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.xpath.*;
import java.io.IOException;

/**
 * Class
 *
 * @author Artur Dzidzoiev
 * @version 11/27/13
 */
public class ConfigLoader {
    public void load() {
        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
        domFactory.setNamespaceAware(true);
        try {
            DocumentBuilder builder = domFactory.newDocumentBuilder();
            Document doc = builder.parse("config.xml");
            XPath xpath = XPathFactory.newInstance().newXPath();
            // XPath Query for showing all nodes value
            XPathExpression expr = xpath.compile("//person/*/text()");

            Object result = expr.evaluate(doc, XPathConstants.NODESET);
            NodeList nodes = (NodeList) result;
            for (int i = 0; i < nodes.getLength(); i++) {
                System.out.println(nodes.item(i).getNodeValue());
            }
        } catch (ParserConfigurationException | SAXException | XPathExpressionException | IOException e) {
            Log.save(e);
        }

    }
}
