package com.semantalytics.jena.function.string;

import org.junit.*;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import static org.junit.Assert.*;

public class TestWrap {

    @Test
    public void testNotWrapped() {

       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:wrap(\"Stardog\", \"*\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();



                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("*Stardog*", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testWrapped() {

       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:wrap(\"*Stardog*\", \"*\") as ?result) }";

            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("**Stardog**", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testTooFewArgs() {

       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:wrap(\"one\") as ?result) }";

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
                    "select ?result where { bind(string:wrap(\"one\", \"2\", \"3\") as ?result) }";

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
                    "select ?result where { bind(string:wrap(1,\"2\") as ?result) }";

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
                    "select ?result where { bind(string:wrap(\"one\", 2) as ?result) }";

                            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                                final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testMultiCharacterWrap() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:wrap(\"one\", \"**\") as ?result) }";

                                try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                                    final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
