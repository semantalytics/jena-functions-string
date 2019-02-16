package com.semantalytics.jena.function.string;

import org.junit.*;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import static org.junit.Assert.*;

public class TestIndexOf {

    @Test
    public void testTwoArg() {

            final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:indexOf(\"Stardog\", \"dog\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final int aValue = Integer.parseInt(result.next().getLiteral("result").getString());

                assertEquals(4, aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testThreeArg() {

        final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                "select ?result where { bind(string:indexOf(\"Stardogdog\", \"dog\", 5) AS ?result) }";

            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final int aValue = Integer.parseInt(result.next().getLiteral("result").getString());

            assertEquals(7, aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooFewArgs() {

            final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:indexOf(\"one\") as ?result) }";

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

            final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:indexOf(\"one\", \"two\", \"three\", \"four\") as ?result) }";

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

        final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                "select ?result where { bind(string:indexOf(1, \"two\") as ?result) }";

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
       
            final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:indexOf(\"one\", 2) as ?result) }";

                            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                                final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testWrongTypeThirdArg() {

        final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                "select ?result where { bind(string:indexOf(\"one\", \"two\", \"three\") as ?result) }";

                                try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                                    final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}

