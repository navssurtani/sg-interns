package sg.com.redhat.interns;

/**
 * Created with IntelliJ IDEA.
 * User: winnie
 * Date: 7/10/13
 * Time: 4:01 PM
 * To change this template use File | Settings | File Templates.
 */

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Team {

    public static void team() {

        try {

            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler() {

                boolean orgName = false;
                boolean token = false;
                boolean teamName = false;
                boolean id = false;

                public void startElement(String uri, String localName,String qName,
                                         Attributes attributes) throws SAXException {


                    if (qName.equalsIgnoreCase("ORG-NAME")) {
                        orgName = true;
                    }

                    if (qName.equalsIgnoreCase("TOKEN")) {
                        token = true;
                    }

                    if (qName.equalsIgnoreCase("TEAM-NAME")) {
                        teamName = true;
                    }

                    if (qName.equalsIgnoreCase("ID")) {
                        id = true;
                    }

                }

                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {



                }

                public void characters(char ch[], int start, int length) throws SAXException {

                    if (orgName) {
                        System.out.println("Organization Name : " + new String(ch, start, length));
                        orgName = false;
                    }

                    if (token) {
                        System.out.println("Token : " + new String(ch, start, length));
                        token = false;
                    }

                    if (teamName) {
                        System.out.println("Team Name : " + new String(ch, start, length));
                        teamName = false;
                    }

                    if (id) {
                        System.out.println("ID : " + new String(ch, start, length));
                        id = false;
                        System.out.println();
                    }
                }


            };

            saxParser.parse("/home/winnie/idea/sg-interns/src/main/resources/github-team-data.xml", handler);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
