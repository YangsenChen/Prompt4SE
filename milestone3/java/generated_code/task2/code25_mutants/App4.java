package org.example;

import jdk.internal.org.xml.sax.SAXException;

import org.w3c.dom.Document;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {
        String xmlFilePath = "./file.xml";

        FeedManager feedManager = new FeedManager(xmlFilePath);
        try {
            Feed feed = feedManager.toObject();
            // Do something with the feed
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

}


 class FeedManager {
     private Document document;

     public FeedManager(String xmlFilePath) {
         try {
             File file = new File(xmlFilePath);
             DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
             DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
             this.document = documentBuilder.parse(file);
             this.document.getDocumentElement().normalize();
         } catch (ParserConfigurationException | IOException e) {
             e.printStackTrace();
         } catch (org.xml.sax.SAXException e) {
             throw new RuntimeException(e);
         }
     }

    // Assuming that upgradeToLatestVersion() updates the document in some way.
    public void upgradeToLatestVersion() {
        // Implementation here.
    }

    // Assuming that getDocument() returns the current document.
    public Document getDocument() {
        return null;
    }


    // semantically equaivalent code
    public Feed toObject() throws JAXBException {
        this.upgradeToLatestVersion();
        JAXBContext jaxbContext = JAXBContext.newInstance(Feed.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        Feed unmarshal = (Feed) unmarshaller.unmarshal(this.getDocument());
        return unmarshal;
    }

    // original code
//     public Feed toObject() throws JAXBException {
//         this.upgradeToLatestVersion();
//         return (Feed) ImmobiliareItUtils.createUnmarshaller().unmarshal(this.getDocument());
//     }

}


@XmlRootElement(name = "project")
 class Feed {
    private String name;
    private String description;
    private String version;

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
