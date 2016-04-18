package com.tempwidget.dam44.temperaturewidget;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Dan on 18/04/2016.
 */
public class XmlHandler {

    public static NodeList xmlReader(String destination, String item) {

        try {

            URL url = new URL(destination);

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory
                    .newDocumentBuilder();
            Document document = documentBuilder.parse(new InputSource(url
                    .openStream()));

            return document.getElementsByTagName(item);
//            for (int i = 0; i < nodlist.getLength(); i++) {
//                Element element = (Element) nodlist.item(i);
//
//                NodeList nodlistid = element.getElementsByTagName("Id");
//                Element id = (Element) nodlistid.item(0);
//
//
//                NodeList nodlistBaslik = element.getElementsByTagName("tagname1");
//                Element baslik = (Element) nodlistBaslik.item(0);
//
//                NodeList nodlistDetay = element.getElementsByTagName("tagname2");
//                Element detay = (Element) nodlistDetay.item(0);
//
//                NodeList nodlistKaynak = element.getElementsByTagName("tagname3");
//                Element lat = (Element) nodlistKaynak.item(0);
//
//                NodeList nodelistMedia = element.getElementsByTagName("tagname4");
//                Element longi = (Element) nodelistMedia.item(0);
//
//                NodeList nodelistTur = element.getElementsByTagName("tagname5");
//                Element tur = (Element) nodelistTur.item(0);
//                // String resimURL =
//                // resim.getAttributes().getNamedItem("url").getNodeValue();
//
//                NodeList nodelistMedia1 = element.getElementsByTagName("enclosure");
//                Element picture= (Element) nodelistMedia1.item(0);
//                String pictureURL = resim.getAttributes().getNamedItem("picturetagname").getNodeValue();
//
//
//                xmltagname1.add(tagname1.getChildNodes().item(0).getNodeValue());
//                xmltagname2.add(tagname2.getChildNodes().item(0).getNodeValue());
//                xmltagname3.add(tagname3.getChildNodes().item(0).getNodeValue());
//                xmltagname4.add(tagname4.getChildNodes().item(0).getNodeValue());
//                xmltagname5.add(tagname5.getChildNodes().item(0).getNodeValue());


            //}

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
