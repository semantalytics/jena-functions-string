package com.semantalytics.jena.function.string;

import org.junit.*;

import static org.junit.Assert.*;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
public class TestDefaultIfEmpty {

    @Test
    public void testEmpty() {
   
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:defaultIfEmpty(\"\", \"Stardog\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("Stardog", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testNotEmpty() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:defaultIfEmpty(\"z\", \"Stardog\") AS ?result) }";

            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("z", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testBlank() {
    
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:defaultIfEmpty(\" \", \"Stardog\") as ?result) }";

                try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                    final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals(" ", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testTooFewArgs() {

       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:defaultIfEmpty(\"one\") as ?result) }";

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
                    "select ?result where { bind(string:defaultIfEmpty(\"one\", \"two\", \"three\") as ?result) }";

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
                    "select ?result where { bind(string:defaultIfEmpty(\"one\", 2) as ?result) }";

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
                    "select ?result where { bind(string:defaultIfEmpty(\"one\", 2) as ?result) }";

                                try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                                    final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }
}
