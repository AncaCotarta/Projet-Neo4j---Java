package com.sorbonne;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.Result;
import org.neo4j.procedure.*;
import java.util.Map;
import java.util.stream.Stream;

public class MergeProcedures {

    @Context
    public GraphDatabaseService db;

    /**
     * Procedure to merge a Person and set 'found' to true if it exists
     */
    @Procedure(name = "nn.mergePerson", mode = Mode.WRITE)
    @Description("MERGE a person and mark it as found")
    public Stream<ResultMessage> mergePerson(@Name("name") String name) {
        try (Transaction tx = db.beginTx()) {
            String query = "MERGE (p:Person {name: $name}) ON MATCH SET p.found = true RETURN p.name";
            Result result = tx.execute(query, Map.of("name", name));
            tx.commit();
            return Stream.of(new ResultMessage("Success"));
        }
    }

    /**
     * Procedure to merge Keanu Reeves with attributes
     */
    @Procedure(name = "nn.mergeKeanuReeves", mode = Mode.WRITE)
    @Description("MERGE Keanu Reeves with attributes bornIn and chauffeurName")
    public Stream<ResultMessage> mergeKeanuReeves() {
        try (Transaction tx = db.beginTx()) {
            String query = "MERGE (p:Person {name: 'Keanu Reeves', bornIn: 'Beirut', chauffeurName: 'Eric Brown'}) " +
                    "ON CREATE SET p.created = timestamp() RETURN p.name";
            tx.execute(query);
            tx.commit();
            return Stream.of(new ResultMessage("Success"));
        }
    }

    /**
     * Procedure to merge Keanu Reeves and update timestamps
     */
    @Procedure(name = "nn.mergeOrUpdateKeanuReeves", mode = Mode.WRITE)
    @Description("MERGE Keanu Reeves and update created or lastSeen timestamps")
    public Stream<ResultMessage> mergeOrUpdateKeanuReeves() {
        try (Transaction tx = db.beginTx()) {
            String query = "MERGE (p:Person {name: 'Keanu Reeves'}) " +
                    "ON CREATE SET p.created = timestamp() " +
                    "ON MATCH SET p.lastSeen = timestamp() RETURN p.name";
            tx.execute(query);
            tx.commit();
            return Stream.of(new ResultMessage("Success"));
        }
    }

    /**
     * Procedure to merge a relationship ACTED_IN between an actor and a movie
     */
    @Procedure(name = "nn.mergeActedInRelationship", mode = Mode.WRITE)
    @Description("MERGE an ACTED_IN relationship between an actor and a movie")
    public Stream<ResultMessage> mergeActedInRelationship(@Name("actorName") String actorName, @Name("movieTitle") String movieTitle) {
        try (Transaction tx = db.beginTx()) {
            String query = "MATCH (a:Person {name: $actorName}), (m:Movie {title: $movieTitle}) " +
                    "MERGE (a)-[:ACTED_IN]->(m) RETURN a.name, m.title";
            tx.execute(query, Map.of("actorName", actorName, "movieTitle", movieTitle));
            tx.commit();
            return Stream.of(new ResultMessage("Success"));
        }
    }

    /**
     * Helper class to return success messages from procedures
     */
    public static class ResultMessage {
        public String result;
        public ResultMessage(String result) {
            this.result = result;
        }
    }
}
