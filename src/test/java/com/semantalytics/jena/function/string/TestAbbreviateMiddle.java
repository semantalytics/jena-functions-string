package com.semantalytics.jena.function.string;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;

import static org.junit.Assert.*;

public class TestAbbreviateMiddle {

    private Model model;

    @Before
    public void setUp() {
        FunctionRegistry.get().put(AbbreviateMiddle.name, AbbreviateMiddle.class);
        model = ModelFactory.createDefaultModel();
    }

    @After
    public void tearDown() {
        model.close();
    }

    @Test
    public void testAbbreviateMiddle() {

        final String queryString = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(\"Stardog graph database\", \"...\", 8) AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(queryString, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("Sta...se", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testEmptyString() {

        final String queryString = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(\"\", \"\", 10) as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(queryString, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooFewArgs() {

        final String queryString = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(\"one\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(queryString, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooManyArgs() {

        final String queryString = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(\"one\", 2, \"three\") as ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(queryString, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeFirstArg() {

        final String queryString = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(4, 5) as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(queryString, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeSecondArg() {

        final String queryString = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(\"one\", \"two\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(queryString, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testLengthTooShort() {

        final String queryString = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(\"Stardog\", 3) as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(queryString, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
