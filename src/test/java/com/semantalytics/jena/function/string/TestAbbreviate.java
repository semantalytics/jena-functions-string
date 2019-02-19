package com.semantalytics.jena.function.string;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class

TestAbbreviate {

    private Model model;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        FunctionRegistry.get().put(Abbreviate.name, Abbreviate.class);
        model = ModelFactory.createDefaultModel();
    }

    @After
    public void tearDown() {
        model.close();
    }

    @Test
    public void testAbbreviateTwoArgument() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviate(\"Jena abbreviate function\", 8) AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String value = result.next().getLiteral("result").getString();

            assertEquals("Jena ...", value);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testAbbreviateWithOffset() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviate(\"Stardog graph database\", 15, 5) AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String value = result.next().getLiteral("result").getString();

            assertEquals("...og graph ...", value);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testEmptyString() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviate(\"\", 5) as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooFewArgs() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage("takes two or three arguments");

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviate(\"one\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertFalse("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooManyArgs() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage("takes two or three arguments");

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviate(\"one\", 9, 3, \"four\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertFalse("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeFirstArgConstant() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage("first argument must be a string literal");

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviate(4, 5) as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertFalse("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeFirstArgVariable() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select (string:abbreviate(?string, ?maxWidth) as ?result) where { values(?string ?maxWidth) { ( 1 3 ) }}";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertFalse("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeSecondArgConstant() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage("second argument must be a integer literal");

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviate(\"one\", \"two\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertFalse("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeSecondArgVariable() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select (string:abbreviate(?string, ?maxWidth, ?offset) as ?result) where { values(?string ?maxwidth ?offset) { (\"one\" \"two\" 3) }}";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertFalse("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeThirdArgConstant() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage("third argument must be a integer literal");

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviate(\"one\", 9, \"three\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeThirdArgVariable() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select (string:abbreviate(?string, ?maxWidth, ?offset) as ?result) where { values(?string ?maxwidth ?offset) { (\"one\" 2 \"three\") }}";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertFalse("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testLengthTooShort() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviate(\"Stardog\", 3) as ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertFalse("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
