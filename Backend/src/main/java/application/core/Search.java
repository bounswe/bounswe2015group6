package application.core;

import com.hp.hpl.jena.ontology.*;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.reasoner.Reasoner;
import com.hp.hpl.jena.reasoner.ReasonerRegistry;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Search {

    String uri = "";
    String relationshipUri = "http://family/relationship/";

    OntModel model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, null);


    OntClass user = model.createClass(uri + "User");
    OntClass tag = model.createClass(uri + "Tag");
    OntClass post = model.createClass(uri + "Post");
    OntClass topic = model.createClass(uri + "Topic");


    ObjectProperty hasCreatedTopic = model.createObjectProperty(uri + "hasCreatedTopic"); /* User has created topic */
    ObjectProperty hasCreatedPost = model.createObjectProperty(uri + "hasCreatedPost");   /* User has created post */
    ObjectProperty topicHasTag = model.createObjectProperty(uri + "topicHasTag");         /* Topic has tag */
    ObjectProperty postHasTag = model.createObjectProperty(uri + "postHasTag");           /* Post has tag */
    ObjectProperty topicRelatedTo = model.createObjectProperty(uri + "topicRelatedTo");   /* Topic relation */
    ObjectProperty usedWith = model.createObjectProperty(uri + "usedWith");               /* Tag with tag*/

    public Search() {

        File file = new File("mymodel.n3");
        if (!file.exists()) {
            FileWriter out = null;
            try {
                // XML format - long and verbose
                out = new FileWriter("mymodel.n3");
                model.write(out, "N3");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (out != null) {
                    try {
                        out.close();
                    } catch (IOException ignore) {
                    }
                }
            }
        }
    }

    public void createHasCreatedTopic(String userName, String topicTitle){
        Individual userTemp = user.createIndividual(uri + userName);
        Individual topicTemp = topic.createIndividual(uri + topicTitle);
        userTemp.addProperty(hasCreatedTopic, topicTemp);
        FileWriter out = null;
        try {
            // Turtle format - long and verbose
            out = new FileWriter( "mymodel.n3", true );
            model.write( out, "N3" );
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (out != null) {
                try {out.close();
                }
                catch (IOException ignore) {}
            }
        }
    }


    public void createHasCreatedPost(String userName, int postId){
        Individual userTemp = user.createIndividual(uri + userName);
        Individual postTemp = post.createIndividual(uri + postId);
        userTemp.addProperty(hasCreatedPost, postTemp);

        FileWriter out = null;
        try {
            // Turtle format - long and verbose
            out = new FileWriter( "mymodel.n3",true );
            model.write( out, "N3" );
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (out != null) {
                try {out.close();
                }
                catch (IOException ignore) {}
            }
        }
    }

    public void createTopicHasTag(String topicTitle, String tagName){

        model.read("file:mymodel.n3", "N3");

        Individual topicTemp = topic.createIndividual(uri + topicTitle);
        Individual tagTemp = tag.createIndividual(uri + tagName);
        topicTemp.addProperty(topicHasTag, tagTemp);

        FileWriter out = null;
        try {
            // Turtle format - long and verbose
            out = new FileWriter( "mymodel.n3");
            model.write( out, "N3" );
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (out != null) {
                try {out.close();
                }
                catch (IOException ignore) {}
            }
        }
    }

    public void createPostHasTag(String postId, String tagName){
        Individual postTemp = post.createIndividual(uri + postId);
        Individual tagTemp = tag.createIndividual(uri + tagName);
        postTemp.addProperty(postHasTag, tagTemp);

        FileWriter out = null;
        try {
            // Turtle format - long and verbose
            out = new FileWriter( "mymodel.n3", true );
            model.write( out, "N3" );
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (out != null) {
                try {out.close();
                }
                catch (IOException ignore) {}
            }
        }
    }

    public void createTopicRelatedTo(String topicName1, String topicName2){
        Individual topicTemp1 = topic.createIndividual(uri + topicName1);
        Individual topicTemp2 = topic.createIndividual(uri + topicName2);

        topicTemp1.addProperty(topicRelatedTo, topicTemp2);
        topicTemp2.addProperty(topicRelatedTo, topicTemp1);

        FileWriter out = null;
        try {
            // Turtle format - long and verbose
            out = new FileWriter( "mymodel.n3", true );
            model.write( out, "N3" );
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (out != null) {
                try {out.close();
                }
                catch (IOException ignore) {}
            }
        }
    }

    public void createUsedWith(String tagName1, String tagName2){

        model.read("file:mymodel.n3", "N3");

        Individual tagTemp1 = tag.createIndividual(uri + tagName1);
        Individual tagTemp2 = tag.createIndividual(uri + tagName2);

        tagTemp1.addProperty(usedWith, tagTemp2);

        FileWriter out = null;
        try {
            // Turtle format - long and verbose
            out = new FileWriter( "mymodel.n3");
            model.write( out, "N3" );
        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            if (out != null) {
                try {out.close();
                }
                catch (IOException ignore) {}
            }
        }
    }

    public ArrayList<String> search(String contextName){

        ArrayList<String> tagResults = new ArrayList<>();

        model.read("file:mymodel.n3", "N3");
        Reasoner reasoner = ReasonerRegistry.getOWLMiniReasoner();
        reasoner.bindSchema(model);

        Model instances = ModelFactory.createDefaultModel();
        instances.read("file:mymodel.n3", "N3");

        InfModel temp = ModelFactory.createInfModel(reasoner, instances);

        /* First search for tags */
        String queryString =
                "SELECT ?tag2 " +
                        "WHERE {" +
                        "<" + contextName + ">" + "<usedWith> ?tag2}" +
                        "ORDER BY ?tag2";

        Query query = QueryFactory.create(queryString);

        // Execute the query and obtain results
        QueryExecution qe = QueryExecutionFactory.create(query, temp);
        ResultSet results = qe.execSelect();

        tagResults.add(contextName);
        while(results.hasNext()){
            QuerySolution sol = results.nextSolution();
            Resource tag = (Resource)sol.get("tag2");
            String tagTemp = tag.getLocalName();
            if(!tagResults.contains(tagTemp))
                tagResults.add(tagTemp);
        }

        for(String s: tagResults) System.out.println(s);


        ArrayList<String> topicResults = new ArrayList<>();

        for(int i = 0; i < tagResults.size(); i++){
            String tagTemp = tagResults.get(i);
            queryString = "SELECT ?topic WHERE {?topic <topicHasTag> " + "<" + tagTemp + ">}";
            query = QueryFactory.create(queryString);
            qe = QueryExecutionFactory.create(query, temp);
            results = qe.execSelect();

            while(results.hasNext()){
                QuerySolution sol = results.nextSolution();
                Resource topic = (Resource)sol.get("topic");
                String topicTemp = topic.getLocalName();
                if(!topicResults.contains(topicTemp))
                    topicResults.add(topicTemp);
            }
        }

        for(String s : topicResults) System.out.println(s);

        /* Now it is time to search for related topics */

        for(int i = 0; i < topicResults.size(); i++){
            String topicTemp = topicResults.get(i);
            queryString = "SELECT ?relatedTopic WHERE {<" + topicTemp +">" + "<topicRelatedTo> ?relatedTopic}";
            query = QueryFactory.create(queryString);
            qe = QueryExecutionFactory.create(query, temp);
            results = qe.execSelect();

            while(results.hasNext()){
                QuerySolution sol = results.nextSolution();
                Resource relatedTopic = (Resource)sol.get("relatedTopic");
                String topicTemp2 = relatedTopic.getLocalName();
                if(!topicResults.contains(topicTemp2))
                    topicResults.add(topicTemp2);
            }
        }
        qe.close();

        return topicResults;
    }
}