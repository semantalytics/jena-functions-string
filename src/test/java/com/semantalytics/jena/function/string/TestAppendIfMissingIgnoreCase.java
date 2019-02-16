package com.semantalytics.jena.function.string;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.junit.*;

import static org.junit.Assert.*;

public class TestAppendIfMissingIgnoreCase {

    @Test
    public void testNotMissing() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:appendIfMissingIgnoreCase(\"stardog.txt\", \".txt\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("stardog.txt", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testMissing() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:appendIfMissingIgnoreCase(\"stardog\", \".txt\") AS ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("stardog.txt", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testEmptyString() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:appendIfMissingIgnoreCase(\"\", \".txt\") as ?abbreviation) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("abbreviation").getString();

            assertEquals(".txt", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooFewArgs() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:appendIfMissingIgnoreCase(\"one\") as ?abbreviation) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertTrue("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooManyArgs() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:appendIfMissingIgnoreCase(\"one\", 2, \"three\") as ?abbreviation) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertTrue("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeFirstArg() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:appendIfMissingIgnoreCase(4, 5) as ?abbreviation) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertTrue("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeSecondArg() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:appendIfMissingIgnoreCase(\"one\", 2) as ?abbreviation) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertTrue("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testLengthTooShort() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:appendIfMissingIgnoreCase(\"Stardog\", 3) as ?abbreviation) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertTrue("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
