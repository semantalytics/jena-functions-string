package com.semantalytics.jena.function.string;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestCenter {

    private Model model;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        FunctionRegistry.get().put(Center.name, Center.class);
        model = ModelFactory.createDefaultModel();
    }

    @After
    public void tearDown() {
        model.close();
    }

    @Test
    public void testCenterTwoArg() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:center(\"Stardog\", 15) AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("    Stardog    ", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testCenterThreeArg() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:center(\"Stardog\", 15, \"*\") AS ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("result").getString();

            assertEquals("****Stardog****", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testEmptyString() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:center(\"\", 5) as ?abbreviation) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("abbreviation").getString();

            assertEquals("     ", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooFewArgs() {
        exception.expect(QueryBuildException.class);

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:center(\"one\") as ?abbreviation) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test(expected= QueryBuildException.class)
    public void testTooManyArgs() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:center(\"one\", 2, \"three\", \"four\") as ?abbreviation) }";
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

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:center(4, 5) as ?abbreviation) }";
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

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?abbreviation where { bind(string:center(\"one\", \"two\") as ?abbreviation) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertFalse("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeThirdArg() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:center(\"one\", 2, 3) as ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
