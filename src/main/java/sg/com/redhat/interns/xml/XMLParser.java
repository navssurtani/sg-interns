package sg.com.redhat.interns.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class XMLParser {

    public static void main(String[] args) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean orgName = false;
                boolean token = false;
                boolean teamName = false;
                boolean id = false;

                public void startElement (String uri, String locaName,
                                          String qName, Attributes attributes) throws SAXException {
                    if(qName.equalsIgnoreCase("org-name")) {
                        orgName = true;
                    }

                    if(qName.equalsIgnoreCase("token")) {
                        token = true;
                    }

                    if(qName.equalsIgnoreCase("team-name")) {
                        teamName = true;
                    }

                    if(qName.equalsIgnoreCase("id")) {
                        id = true;
                    }
                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {

                }

                public void characters(char ch[], int start, int length) throws SAXException {
                    if(orgName) {
                        System.out.println("Organization Name: " + new String(ch, start, length));
                        orgName = false;
                    }

                    if(token) {
                        System.out.println("Token: " + new String(ch, start, length));
                        token = false;
                    }

                    if(teamName) {
                        System.out.println("Team Name: " + new String(ch, start, length));
                        teamName = false;
                    }

                    if(id) {
                        System.out.println("ID: " + new String(ch, start, length));
                        id = false;
                    }
                }
            };

            parser.parse("./src/main/resources/github-team-data.xml", handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

