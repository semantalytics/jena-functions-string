package com.semantalytics.jena.function.string;

import org.junit.*;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import static org.junit.Assert.*;

public class TestIsNotEmpty {

    @Test
    public void testTrue() {
      
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:isNotEmpty(\"Stardog\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final boolean aValue = Boolean.parseBoolean(result.next().getLiteral("result").getString());

                assertEquals(true, aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testFalse() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:isNotEmpty(\"\") AS ?result) }";

            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final boolean aValue = Boolean.parseBoolean(result.next().getLiteral("result").getString());

            assertEquals(false, aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooFewArgs() {
      
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:isNotEmpty() as ?result) }";

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
                    "select ?result where { bind(string:isNotEmpty(\"one\", \"two\") as ?result) }";

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
                    "select ?result where { bind(string:isNotEmpty(1) as ?result) }";

                        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                            final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }
}
