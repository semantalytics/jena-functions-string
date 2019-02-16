package com.semantalytics.jena.function.string;

import org.junit.*;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import static org.junit.Assert.*;

public class TestPrependIfMissing {

    @Test
    public void testTwoArg() {

       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:prependIfMissing(\"Stardog\", \"star\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();



                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("starStardog", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testThreeArg() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:prependIfMissing(\"Stardog\", \"str\", \"xyz\") AS ?result) }";

            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                final ResultSet result = queryExecution.execSelect();



            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("strStardog", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testEmptyString() {
    
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:prependIfMissing(\"\", \"\") as ?result) }";

                try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                    final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testTooFewArgs() {

       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:prependIfMissing(\"one\") as ?result) }";

                    try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                        final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testWrongTypeFirstArg() {
    
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:prependIfMissing(1) as ?result) }";

                        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                            final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testWrongTypeSecondArg() {
     
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:prependIfMissing(\"one\", 2) as ?result) }";

                            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                                final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }
}
