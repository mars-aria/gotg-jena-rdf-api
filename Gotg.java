package assignment1;

import org.apache.jena.rdf.model.*;
import org.apache.jena.riot.RDFDataMgr;

public class Gotg extends Object {

    static final String inputFileName = "src/assignment1/gotg.rdf";
    static final String NS = "http://www.mcu.fake/siteinfo#";

    public static void main(String args[]) {

        // read the RDF file
        Model model = RDFDataMgr.loadModel(inputFileName);

        // select all the resources with the alias property
        ResIterator iter = model.listResourcesWithProperty(model.getProperty(NS, "alias"));

        // iterate over all resources with the alias property
        while (iter.hasNext()) {
            Resource resourceWithAlias = iter.nextResource();
            
            // get the value of the name property
            RDFNode nameNode = resourceWithAlias.getProperty(model.getProperty(NS, "name")).getObject();
            
            // name a guardian of the galaxy
            if ("Bill".equals(nameNode.toString())) {
                // get the value of the alias property
                RDFNode aliasNode = resourceWithAlias.getProperty(model.getProperty(NS, "alias")).getObject();
                
                // print the matching alias for the correct guardian's name
                System.out.println("The alias of the guardian " + nameNode.toString() + " is " + aliasNode.toString() + ".");
                
                // exit the loop once the alias is found
                return;
            }
        }

        // if no resources with the alias property are found
        System.out.println("A guardian with that name does not exist.");
    }
}
