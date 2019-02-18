package com.semantalytics.jena.function.string;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

public class TestAbbreviateMiddle {

    private Model model;

    @Rule
    public ExpectedException exception = ExpectedException.none();

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

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(\"Stardog graph database\", \"...\", 8) AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("Sta...se", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testEmptyString() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(\"\", \"\", 10) as ?result) }";

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
        exception.expectMessage(containsString("takes three arguments"));

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(\"one\", \"two\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooManyArgs() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage(containsString("takes three arguments"));

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(\"one\", \"two\", 3, \"four\") as ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeFirstArgConstant() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage(containsString("takes three arguments"));

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(4, 5) as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeFirstArgVariable() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select (string:abbreviateMiddle(?string, ?middle, ?length) as ?result) where { values(?string ?middle ?length) {( 1 \"two\" 8 ) }}";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeSecondArgConstant() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage(containsString("second argument must be a string literal"));

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(\"one\", 2, 8) as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeSecondArgVariable() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select (string:abbreviateMiddle(?string, ?middle, ?length) as ?result) where { values(?string ?middle ?length) {( \"one\" 2 8 ) }}";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeThirdArgConstant() {
        exception.expect(QueryBuildException.class);
        exception.expectMessage(containsString("third argument must be a integer literal"));

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:abbreviateMiddle(\"one\", \"two\", \"three\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeThirdArgVariable() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select (string:abbreviateMiddle(?string, ?middle, ?length) as ?result) where { values(?string ?middle ?length) {( \"one\" \"two\" \"three\" ) }}";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertFalse("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
