package sg.com.interns.xml;

import org.junit.Assert;

import org.testng.annotations.Test;
import sg.com.redhat.interns.beans.GithubOrganization;
import sg.com.redhat.interns.beans.GithubTeam;
import sg.com.redhat.interns.xml.XMLParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Test Class
 */
@Test(testName = "sg.com.interns.xml.XMLParserTest", groups = "functional")
public class XMLParserTest {

    @Test
    public void testParsing() {
        HashSet<GithubOrganization> orgs = XMLParser.parse();
        Assert.assertNotNull(orgs);
        Assert.assertTrue("There are two organizations in the file.", orgs.size() == 2);

        List<String> teamNames = new ArrayList<String>();
        teamNames.add("Owners");
        teamNames.add("EAP View");
        teamNames.add("Trial");

        for (GithubOrganization o : orgs) {
            Assert.assertNotNull(o.getOrgName());
            for (GithubTeam t :o.getTeams()) {
                Assert.assertNotNull(t);
                if (teamNames.contains(t.getName())) {
                    if (t.getName().equals("EAP View")) {
                        Assert.assertTrue(t.getId() == 338123);
                    }

                    if (t.getName().equals("Trial")) {
                        Assert.assertTrue(t.getId() == 405212);
                    }
                } else {
                    throw new RuntimeException("You haven't found the correct team name. Or this is a bad test.");
                }

            }
        }

    }

}
