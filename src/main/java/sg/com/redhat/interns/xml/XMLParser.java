package sg.com.redhat.interns.xml;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import sg.com.redhat.interns.beans.GithubOrganization;
import sg.com.redhat.interns.beans.GithubTeam;


public class XMLParser {

    /* Constant Strings */
    private static final String XML_DATA = "/home/gerald/code/sg-interns/src/main/resources/github-team-data.xml";
    private static final String ORGANIZATIONS = "organizations";
    private static final String ORGANIZATION = "organization";
    private static final String ORG_NAME = "org-name";
    private static final String TEAM = "team";
    private static final String TEAM_NAME = "team-name";
    private static final String ID = "id";

    private static Set<GithubOrganization> organizations = null;

    private XMLParser() {
    }

    public static Set<GithubOrganization> parse() {

        Set<GithubOrganization> orgs = null;
        try{
            GithubOrganization org = null;
            InputStream in = getAndCheckFile(XML_DATA);

            XMLEventReader reader = null;
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            reader = inputFactory.createXMLEventReader(in);

            while (reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                String data;

                if (checkEventType(event, ORGANIZATIONS)) {
                    data = reader.nextEvent().asCharacters().getData();
                    orgs = new HashSet<GithubOrganization>();
                }

                if (checkEventType(event, ORGANIZATION)) {

                    while (reader.hasNext()) {
                        event = reader.nextEvent();
                        if (checkEventType(event, ORG_NAME)) {
                            data = reader.nextEvent().asCharacters().getData();
                            org = new GithubOrganization(data);
                        }

                        if (checkEventType(event, TEAM)) {
                            // We have found a team. Now we will extract the team name.
                            String teamName = null;
                            int teamId;
                            while (reader.hasNext()) {
                                event = reader.nextTag();
                                if (checkEventType(event, TEAM_NAME)) {
                                    teamName = reader.nextEvent().asCharacters().getData();
                                } else {
                                    if (checkEventType(event, ID)) {
                                        teamId = Integer.parseInt(reader.nextEvent().asCharacters().getData());
                                        org.addTeam(new GithubTeam(teamName, teamId));
                                        orgs.add(org);
                                        break;
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }



        catch(XMLStreamException ex){

            ex.printStackTrace();

        }
        return orgs;
    }

    private static boolean checkEventType(XMLEvent event, String constant) {
        return event.isStartElement() && event.asStartElement().getName().getLocalPart().equals(constant);
    }

    private static InputStream getAndCheckFile(String xmlFile) {
        try{
            return new FileInputStream(xmlFile);
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }
}