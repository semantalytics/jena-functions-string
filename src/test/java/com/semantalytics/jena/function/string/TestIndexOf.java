package com.semantalytics.jena.function.string;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestIndexOf {

    private Model model;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        FunctionRegistry.get().put(IndexOf.name, IndexOf.class);
        model = ModelFactory.createDefaultModel();
    }

    @After
    public void tearDown() {
        model.close();
    }

    @Test
    public void testTwoArg() {

            final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:indexOf(\"Stardog\", \"dog\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
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

            try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final int aValue = Integer.parseInt(result.next().getLiteral("result").getString());

            assertEquals(7, aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooFewArgs() {
        exception.expect(QueryBuildException.class);

            final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:indexOf(\"one\") as ?result) }";

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

            final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:indexOf(\"one\", \"two\", \"three\", \"four\") as ?result) }";

                    try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                        final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testWrongTypeFirstArg() throws QueryBuildException {

        final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                "select ?result where { bind(string:indexOf(1, \"two\") as ?result) }";

                        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeSecondArg() throws QueryBuildException {
       
            final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                    "select ?result where { bind(string:indexOf(\"one\", 2) as ?result) }";

                            try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testWrongTypeThirdArg() {
        exception.expect(QueryBuildException.class);

        final String query = "prefix string: <" + StringVocabulary.NAMESPACE + "> " +
                "select ?result where { bind(string:indexOf(\"one\", \"two\", \"three\") as ?result) }";

                                try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                    final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}

