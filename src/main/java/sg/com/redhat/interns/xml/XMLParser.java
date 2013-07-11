package sg.com.redhat.interns.xml;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sg.com.redhat.interns.beans.GithubOrganization;
import sg.com.redhat.interns.beans.GithubTeam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;

public class XMLParser {

    public static void execute() {
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

    //initialize variable
    private static final String organizations = "organizations";
    private static final String organization = "organization";
    private static final String org_name = "org-name";
    private static final String team = "team";
    private static final String team_name = "team-name";
    private static final String id = "id";


    public static HashSet<GithubOrganization> parse() {
        HashSet<GithubOrganization> orgs = null;
        try{
            GithubOrganization org = null;
            InputStream in = getCheckFile("/Users/purritty_bride/Desktop/Interns/sg-interns/src/main/resources/github-team-data.xml");
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            XMLEventReader reader = inputFactory.createXMLEventReader(in);

            while(reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                String data;

                if (checkEventType(event, organizations)){
                    orgs= new HashSet<GithubOrganization>();
                }

                if (checkEventType(event, organization)){

                    while (reader.hasNext()){

                        event = reader.nextEvent();
                        if (checkEventType(event, org_name)){
                            data = reader.nextEvent().asCharacters().getData();


                            org = new GithubOrganization();
                            org.setOrgName(data);
//                            org.setTeams(new HashSet<GithubTeam>());
                        }

                        if (checkEventType(event, team)){
                            String teamName = null;
                            int teamid;
                            HashSet<GithubTeam> listOfTeams = new HashSet<GithubTeam>();
                            while (reader.hasNext()){
                                event = reader.nextTag();
                                if (checkEventType(event, team_name)){
                                    teamName = reader.nextEvent().asCharacters().getData();

                                }

                                else {
                                    if (checkEventType(event, id)){
                                        teamid = Integer.parseInt(reader.nextEvent().asCharacters().getData());
                                        GithubTeam team = new GithubTeam();
                                        team.setName(teamName);
                                        team.setId(teamid);
                                        listOfTeams.add(team);
                                        org.setTeams(listOfTeams);
                                        orgs.add(org);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (XMLStreamException e){
            e.printStackTrace();
    }
    return orgs;
}


    private static boolean checkEventType(XMLEvent event, String constant){
        return event.isStartElement() && event.asStartElement().getName().getLocalPart().equals(constant);
    }

    private static InputStream getCheckFile(String xmlFile){
        try {
            return new FileInputStream(xmlFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
