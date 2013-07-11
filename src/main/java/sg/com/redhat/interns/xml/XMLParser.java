package sg.com.redhat.interns.xml;


import sg.com.redhat.interns.beans.GithubOrganization;
import sg.com.redhat.interns.beans.GithubTeam;

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

public class XMLParser {

    private static final String XML_DATA = "/home/winnie/idea/sg-interns/src/main/resources/github-team-data.xml";
    private static final String ORGANIZATIONS = "organizations";
    private static final String ORGANIZATION = "organization";
    private static final String ORG_NAME = "org-name";
    private static final String TEAM = "team";
    private static final String TEAM_NAME = "team-name";
    private static final String ID = "id";

    private static Set<GithubOrganization> orgSet = null;

    private XMLParser(){

    }

//    public static Set<GithubOrganization> getOrgSet(){
//        if (orgSet == null) {
//            orgSet = parse(XML_DATA);
//        }
//        return orgSet;
//    }


    public static Set<GithubOrganization> parse() {
        // TODO: Implement me.

        try{
            GithubOrganization org = null;
//            String xmlFile = "/home/winnie/idea/sg-interns/src/main/resources/github-team-data.xml";
            InputStream in = getAndCheckFile(XML_DATA);

//          Creates a Event Reader
            XMLEventReader reader = null;

            //Initialize the factory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            reader = inputFactory.createXMLEventReader(in);

//            inputFactory.setProperty(
//                    XMLInputFactory.IS_REPLACING_ENTITY_REFERENCES,
//                    Boolean.TRUE);
//            inputFactory.setProperty(
//                    XMLInputFactory.IS_SUPPORTING_EXTERNAL_ENTITIES,
//                    Boolean.FALSE);
//             inputFactory.setProperty(
//                     XMLInputFactory.IS_COALESCING,
//                     Boolean.FALSE);

                while(reader.hasNext()){

                    XMLEvent event = reader.nextEvent();
                    String data;

                    if(checkEventType(event, ORGANIZATIONS)){
                        data = reader.nextEvent().asCharacters().getData();
                        orgSet = new HashSet<GithubOrganization>();
                    }

                    if(checkEventType(event, ORGANIZATION)){

                        while(reader.hasNext()){
                            event = reader.nextEvent();
                            if(checkEventType(event, ORG_NAME)){
                                data = reader.nextEvent().asCharacters().getData();
                                org = new GithubOrganization(data);

                            }

                            if(checkEventType(event, TEAM)){

                                String teamName = null;
                                int teamId;

                                while(reader.hasNext()){

                                    event = reader.nextTag();

                                    if(checkEventType(event, TEAM_NAME)){


                                        teamName = reader.nextEvent().asCharacters().getData();
                                    }

                                    else {
                                        if(checkEventType(event, ID)){

                                            teamId = Integer.parseInt(reader.nextEvent().asCharacters().getData());
                                            org.addTeam(new GithubTeam(teamName, teamId));
                                            orgSet.add(org);
                                            break;

                                        }
                                    }
                                }
                            }

                        }
                    }
//
                }
        }

        catch (XMLStreamException ex) {
            ex.printStackTrace();
        }

        return orgSet;
    }

    private static boolean checkEventType(XMLEvent event, String constant) {

        return event.isStartElement() && event.asStartElement().getName().getLocalPart().equals(constant);
    }

    private static InputStream getAndCheckFile(String xmlFile){
        try{

            return new FileInputStream(xmlFile);
        }
        catch(FileNotFoundException e) {

            e.printStackTrace();
            return null;
        }
    }
}


