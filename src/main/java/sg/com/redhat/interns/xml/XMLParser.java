package sg.com.redhat.interns.xml;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;

import sg.com.redhat.interns.beans.GithubOrganization;
import sg.com.redhat.interns.beans.GithubTeam;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;


public class XMLParser {

    public static Set<GithubOrganization> parse() {
        Set<GithubOrganization> listOfOrgs = null;

        try {
            Set<GithubTeam> teams = null;
            GithubOrganization org = null;
            InputStream inFile = readFile("./src/main/resources/github-team-data.xml");
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLEventReader reader = factory.createXMLEventReader(inFile);

            while(reader.hasNext()) {
                XMLEvent event = reader.nextEvent();
                String data;

                if(checkEventType(event, "organizations")) {
                    listOfOrgs = new HashSet<GithubOrganization>();
                }

                if(checkEventType(event, "organization")) {
                    while(reader.hasNext()) {
                        event = reader.nextEvent();
                        if(checkEventType(event, "org-name")) {
                            data = reader.nextEvent().asCharacters().getData();
                            org = new GithubOrganization();
                            org.setName(data);
                            teams = new HashSet<GithubTeam>();
                        }

                        if(checkEventType(event, "team")) {

                            String teamName = null;
                            int ID;


                            while(reader.hasNext()) {
                                event = reader.nextTag();
                                if(checkEventType(event, "team-name")) {
                                    teamName = reader.nextEvent().asCharacters().getData();
                                } else {
                                    if(checkEventType(event, "id")) {
                                        ID = Integer.parseInt(reader.nextEvent().asCharacters().getData());
                                        GithubTeam team = new GithubTeam();
                                        team.setName(teamName);
                                        team.setId(ID);

                                        teams.add(team);
                                        org.setTeams(teams);
                                        listOfOrgs.add(org);
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listOfOrgs;
    }

    // Read file to input stream and throw exception if file cannot be found.
    private static InputStream readFile(String xmlFile) {
        try {
            return new FileInputStream(xmlFile);
        } catch (FileNotFoundException e) {
            // Prints an error msg if exception is caught, this should
            // probably be replaced with some proper logging.
            System.out.println("The file cannot be found");
            return null;
        }
    }

    // Check event type. Return true if event is a start element and event name is correct.
    private static boolean checkEventType(XMLEvent event, String eventName) {
        return event.isStartElement() && event.asStartElement().getName().getLocalPart().equals(eventName);
    }
}

