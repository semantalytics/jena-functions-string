package com.semantalytics.jena.function.string;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.junit.*;

import static org.junit.Assert.*;

public class TestCompareIgnoreCase {

    @Test
    public void testEqual() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:compareIgnoreCase(\"Stardog\", \"stardog\") AS ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final int aValue = Integer.parseInt(result.next().getLiteral("result").getString());

            assertEquals(0, aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testGreaterThan() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:compareIgnoreCase(\"star\", \"dog\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final int aValue = Integer.parseInt(result.next().getLiteral("result").getString());

            assertEquals(true, aValue > 0);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testLessThan() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:compareIgnoreCase(\"dog\", \"star\") AS ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final int aValue = Integer.parseInt(result.next().getLiteral("result").getString());

            assertEquals(true, aValue < 0);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testEmptyString() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:compareIgnoreCase(\"\", \"\") as ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final int aValue = Integer.parseInt(result.next().getLiteral("result").getString());

            assertEquals(0, aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooFewArgs() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:compareIgnoreCase(\"one\") as ?result) }";
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
                "select ?result where { bind(string:compareIgnoreCase(\"one\", \"two\", \"three\") as ?result) }";
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
                "select ?result where { bind(string:compareIgnoreCase(1, \"two\") as ?result) }";
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
                "select ?result where { bind(string:compareIgnoreCase(\"one\", 2) as ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
