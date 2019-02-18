package com.semantalytics.jena.function.string;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class TestAbbreviateWithMarker {

    private Model model;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        FunctionRegistry.get().put(AbbreviateWithMarker.name, AbbreviateWithMarker.class);
        model = ModelFactory.createDefaultModel();
    }

    @After
    public void tearDown() {
        model.close();
    }

    @Test
    public void testThreeArg() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateWithMarker(\"Stardog graph database\", \"***\" , 10) AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("Stardog***", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testFourArg() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateWithMarker(\"Stardog graph database\", \"***\", 10, 3) AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("Stardog***", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testEmptyString() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:abbreviateWithMarker(\"\", \"***\", 5) as ?abbreviation) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("abbreviation").getString();

            assertEquals("", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooFewArgs() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage(containsString("takes three or four arguments"));

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:abbreviateWithMarker(\"one\") as ?abbreviation) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertTrue("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }


    @Test
    public void testTooManyArgs() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage(containsString("takes three or four arguments"));

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:abbreviateWithMarker(\"one\", \"two\", 3, 4, 5) as ?abbreviation) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertTrue("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeFirstArgConstant() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage(containsString("first argument must be a string literal"));

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:abbreviateWithMarker(1, \"two\", 3) as ?abbreviation) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertTrue("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeSecondArg() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage(containsString("second argument must be a string literal"));

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:abbreviateWithMarker(\"one\", 2, 3, 4) as ?abbreviation) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
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
                "select ?abbreviation where { bind(string:abbreviateWithMarker(\"one\", \"two\", 3) as ?abbreviation) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
