package com.company.Model.session.Parser;

import com.company.Log;
import com.company.Model.session.question.Topic;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Class.
 * User: Serhey Shevchuk
 * Date: 21.11.13
 * Time: 3:10
 */
public class ParsingExecutor {
    private XMLParser saxParser;

    public ParsingExecutor(String XMLFileName){
        parse(XMLFileName);

    }

    private void parse(String XMLFileName){
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = null;
        try {
            parser = factory.newSAXParser();
        } catch (ParserConfigurationException e) {
            Log.save(e);
        } catch (SAXException e) {
            Log.save(e);
        }
        saxParser = new XMLParser();
        try {
            parser.parse(new File(XMLFileName), saxParser);
        } catch (SAXException e) {
            Log.save(e);
        } catch (IOException e) {
            Log.save(e);
        }
    }

    public Topic getTopic() {
        return saxParser.getTopic();
    }

}
