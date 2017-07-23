package com.klimov.runner;

import com.klimov.comparator.SortingRulesComparator;
import com.klimov.entity.Item;
import com.klimov.entity.SortingRule;
import com.klimov.parser.SortingRulesSAXParser;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class Runner {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {



        ArrayList<Item> itemsList = new ArrayList<>();
        itemsList.add(new Item("aaa", 3, false));
        itemsList.add(new Item("bbb", 3, true));
        itemsList.add(new Item("aaa", 1, false));
        itemsList.add(new Item("bbb", 1, false));
        itemsList.add(new Item("aaa", 2, true));
        itemsList.add(new Item("bbb", 2, false));

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SortingRulesSAXParser saxp = new SortingRulesSAXParser();
        File file = new File("test.xml");
        parser.parse(file, saxp);
        ArrayList<SortingRule> sortingRules = saxp.getResult();

        Comparator<Item> comparator = new SortingRulesComparator<>(sortingRules);
        itemsList.sort(comparator);
        double result = itemsList.stream().limit(2).mapToDouble(Item::getPrice).sum();
        System.out.println();
    }
}
