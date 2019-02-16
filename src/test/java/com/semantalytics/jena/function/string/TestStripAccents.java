package com.semantalytics.jena.function.string;

import org.junit.*;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import static org.junit.Assert.*;

public class TestStripAccents {

    @Test
    public void testOneArgumentWithoutAccents() {

        final String query = StringVocabulary.sparqlPrefix("string") +
            "select ?result where { bind(string:stripAccents(\"Stardog\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("Stardog", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }    
    }

    @Test
    public void testOneArgumentWithAccents() {
       
        final String query = StringVocabulary.sparqlPrefix("string") +
            "select ?result where { bind(string:stripAccents(\"\\u00E9clair\") AS ?result) }";

            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("eclair", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testEmptyString() {
       
        final String query = StringVocabulary.sparqlPrefix("string") +
            "select ?result where { bind(string:stripAccents(\"\") AS ?result) }";

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
                    "select ?result where { bind(string:stripAccents() as ?result) }";

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
             "select ?result where { bind(string:stripAccents(\"one\", \"two\") as ?result) }";

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
             "select ?result where { bind(string:stripAccents(4) as ?result) }";

                            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                                final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }
}
