package sg.com.redhat.interns.stax;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

public class StaxParser {
    static final String ORGNAME = "org-name";
    static final String TOKEN = "token";
    static final String TEAMNAME = "team-name";
    static final String ID = "id";

    @SuppressWarnings({ "unchecked", "null" })
    public List<Item> readConfig(String configFile) {
        List<Item> items = new ArrayList<Item>();
        try {
            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(configFile);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // Read the XML document
            Item item = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a item element we create a new item
                    if (startElement.getName().getLocalPart() == (ORGNAME)) {
                        item = new Item();
                        // We read the attributes from this tag and add the date
                        // attribute to our object
                        Iterator<Attribute> attributes = startElement
                                .getAttributes();
                        while (attributes.hasNext()) {
                            Attribute attribute = attributes.next();

                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(ORGNAME)) {
                            event = eventReader.nextEvent();
                            item.setOrgName(event.asCharacters().getData());
                            continue;
                        }
                    }


                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(TOKEN)) {
                            event = eventReader.nextEvent();
                            item.setToken(event.asCharacters().getData());
                            continue;
                        }
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(TEAMNAME)) {
                        event = eventReader.nextEvent();
                        item.setTeamName(event.asCharacters().getData());
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(ID)) {
                        event = eventReader.nextEvent();
                        item.setId(event.asCharacters().getData());
                        continue;
                    }


                }
                // If we reach the end of an item element we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart() == (ORGNAME)) {
                        items.add(item);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return items;
    }

}