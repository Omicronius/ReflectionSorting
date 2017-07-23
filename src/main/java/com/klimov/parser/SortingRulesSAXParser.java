package com.klimov.parser;

import com.klimov.entity.SortingRule;
import com.klimov.entity.SortingType;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import java.util.ArrayList;

public class SortingRulesSAXParser extends DefaultHandler {
    private static final String FIELD = "field";
    private static final String ORDER = "order";

    private String thisElement = "";
    private String order;
    private String field;
    private ArrayList<SortingRule> list;

    public ArrayList<SortingRule> getResult() {
        return list;
    }

    @Override
    public void startDocument() throws SAXException {
        list = new ArrayList<>();
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
        if (qName.equalsIgnoreCase(FIELD)) {
            order = attributes.getValue(ORDER);
        }
        super.startElement(namespaceURI, localName, qName, attributes);
    }

    @Override
    public void endElement(String namespaceURI, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals(FIELD)) {
            field = new String(ch, start, length);
            SortingType sortingType = SortingType.stringToEnum(order);
            list.add(new SortingRule(field, sortingType));
        }
    }
}
