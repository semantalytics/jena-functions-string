package com.semantalytics.jena.function.string;

import org.junit.*;

import static org.junit.Assert.*;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

public class TestDifference {

    @Test
    public void testDifferent() {
   
            final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:difference(\"Stardog\", \"Starman\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("man", aValue);

                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testNotDifferent() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:difference(\"Stardog\", \"Stardog\") AS ?result) }";

            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("", aValue);

            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testEmptyString() {
       
            final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:difference(\"\", \"\") as ?result) }";

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
                    "select ?result where { bind(string:difference(\"one\") as ?result) }";

                    try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                        final ResultSet result = queryExecution.execSelect();

          
                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testTooManyArgs() {

            final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:difference(\"one\", \"two\", \"three\") as ?result) }";

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
                    "select ?result where { bind(string:difference(1, \"two\") as ?result) }";

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
                "select ?result where { bind(string:difference(\"one\", 2) as ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();



            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}

