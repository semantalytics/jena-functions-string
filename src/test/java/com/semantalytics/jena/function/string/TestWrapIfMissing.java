package com.semantalytics.jena.function.string;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestWrapIfMissing {

    private Model model;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        FunctionRegistry.get().put(WrapIfMissing.name, WrapIfMissing.class);
        model = ModelFactory.createDefaultModel();
    }

    @After
    public void tearDown() {
        model.close();
    }

    @Test
    public void testIfNoWrapping() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:wrapIfMissing(\"Jena\", \"*\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("*Jena*", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testIfWrapped() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:wrapIfMissing(\"*Stardog*\", \"*\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("*Stardog*", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooFewArgs() {
        exception.expect(QueryBuildException.class);

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:wrapIfMissing(\"one\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooManyArgs() {
        exception.expect(QueryBuildException.class);

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:wrapIfMissing(\"one\", \"two\", \"three\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeFirstArg() {
        exception.expect(QueryBuildException.class);

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:wrapIfMissing(1, \"two\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeSecondArg() {
        exception.expect(QueryBuildException.class);

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:wrapIfMissing(\"one\", 2) as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
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
                "select ?result where { bind(string:wrapIfMissing(\"Jena\", \"*\") as ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());
            QuerySolution querySolution = result.next();
            assertNotNull(querySolution.getLiteral("result"));
            final String value = querySolution.getLiteral("result").getString();

            assertEquals("*Jena*", value);
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
