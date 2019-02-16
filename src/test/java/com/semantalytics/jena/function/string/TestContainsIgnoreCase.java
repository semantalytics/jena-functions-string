package com.semantalytics.jena.function.string;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.junit.*;

import static org.junit.Assert.*;

public class TestContainsIgnoreCase {

    @Test
    public void testTrue() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:containsIgnoreCase(\"Stardog\", \"Dog\") AS ?result) }";

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
                "select ?result where { bind(string:containsIgnoreCase(\"Stardog\", \"Graph\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final boolean aValue = Boolean.parseBoolean(result.next().getLiteral("result").getString());

            assertEquals(false, aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testEmptyString() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:containsIgnoreCase(\"\", \"\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final boolean aValue = Boolean.parseBoolean(result.next().getLiteral("result").getString());

            assertEquals(true, aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooFewArgs() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:containsIgnoreCase(\"one\") as ?result) }";

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
                "select ?result where { bind(string:containsIgnoreCase(\"one\", \"two\", \"three\") as ?result) }";

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
                "select ?result where { bind(string:containsIgnoreCase(1, \"two\") as ?result) }";

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
                "select ?result where { bind(string:containsIgnoreCase(\"one\", 2) as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
