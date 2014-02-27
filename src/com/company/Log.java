package com.company;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Logging errors and exceptions to log.
 * Class uses static methods.
 * Log file - TestSystemLog.xml
 *
 * @author Artur Dzidzoiev
 * @version 25 Nov 2013
 */
public class Log {
    private static File logFile = new File("TestSystemLog.xml");

    /**
     * Save error or exception to log.
     *
     * @param e any <code>Throwable</code>
     */
    public static void save(Throwable e) {
        if(!logFile.exists()){
            createLogFile();
        }
        modifyLog(e);
    }


    private static void createLogFile() {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = createRootElement(docBuilder);
            saveDocument(doc);
        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }

    private static Document createRootElement(DocumentBuilder docBuilder) {
        Document doc = docBuilder.newDocument();
        Element rootElement = doc.createElement("error_log");
        doc.appendChild(rootElement);
        return doc;
    }

    private static void saveDocument(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(logFile);
        transformer.transform(source, result);
    }

    private static void modifyLog(Throwable e) {
       try {

           Document doc = loadDocument();
           appendElement(e, doc);
           saveDocument(doc);
       } catch (ParserConfigurationException | TransformerException | SAXException | IOException pce) {
           pce.printStackTrace();
       }
    }

    private static void appendElement(Throwable e, Document doc) {
        Node rootElement = doc.getFirstChild();
        appendError(e, doc, rootElement);
    }

    private static void appendError(Throwable e, Document doc, Node rootElement) {
        Element error_element = doc.createElement("error");
        rootElement.appendChild(error_element);
        appendAttributes(e, error_element);
        AppendStackTrace(e, doc, error_element);
    }

    private static void appendAttributes(Throwable e, Element error_element) {
        String error = e.toString();
        error_element.setAttribute("type", error);
        error_element.setAttribute("time", currentTime());
    }

    private static void AppendStackTrace(Throwable e, Document doc, Element error_element) {
        Element stackTrace = doc.createElement("stackTrace");
        error_element.appendChild(stackTrace);

        StackTraceElement[] stackTraceArr = e.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceArr) {
            Element element = doc.createElement("element");
            element.appendChild(doc.createTextNode(stackTraceElement.toString()));
            stackTrace.appendChild(element);
        }
    }

    private static String currentTime() {
        return new SimpleDateFormat("dd/MM/yy_HH:mm:ss").format(Calendar.getInstance().getTime());
    }

    private static Document loadDocument() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        return docBuilder.parse(logFile);
    }
}