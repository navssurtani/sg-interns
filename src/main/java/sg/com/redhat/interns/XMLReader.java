package sg.com.redhat.interns;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLReader {
    public static void XMLreading() {

        try{
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();

            DefaultHandler handler = new DefaultHandler(){
                boolean orgName = false;
                boolean token = false;
                boolean teamName = false;
                boolean id = false;

                public void startElement(String stg, String localName, String xmlName, Attributes attributes) throws SAXException{
                    //System.out.println("StartElement :" + xmlName);

                    if (xmlName.equalsIgnoreCase("ORG-NAME")) {
                        orgName = true;
                    }

                    if (xmlName.equalsIgnoreCase("TOKEN")){
                        token = true;
                    }

                    if (xmlName.equalsIgnoreCase("TEAM-NAME")){
                        teamName = true;
                    }

                    if (xmlName.equalsIgnoreCase("ID")){
                        id = true;
                    }
                }
                //public void endElement(String stg, String localName, String xmlName) throws SAXException{
                  //  System.out.println("End Element :" + xmlName);
                //}

                public void characters(char ch[], int start, int length) throws SAXException {
                    if (orgName){
                        System.out.println("Org Name : " + new String(ch, start, length));
                        orgName = false;
                    }

                    if (token){
                        System.out.println("Token : " + new String(ch, start, length));
                        token = false;
                    }

                    if (teamName){
                        System.out.println("Team Name : " + new String(ch, start, length));
                        teamName = false;
                    }

                    if (id){
                        System.out.println("Id : " + new String(ch, start, length));
                        id = false;
                    }

                }
            };
             saxParser.parse("/Users/purritty_bride/Desktop/Interns/sg-interns/src/main/resources/github-team-data.xml", handler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
