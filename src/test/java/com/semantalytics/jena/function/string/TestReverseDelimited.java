package com.semantalytics.jena.function.string;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestReverseDelimited {

    private Model model;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        FunctionRegistry.get().put(ReverseDelimited.name, ReverseDelimited.class);
        model = ModelFactory.createDefaultModel();
    }

    @After
    public void tearDown() {
        model.close();
    }

    @Test
    public void test() {
    
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:reverseDelimited(\"Stardog.graph.database\", \".\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("database.graph.Stardog", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testEmptyString() {
        
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:reverseDelimited(\"\", \"x\") as ?result) }";

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

       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:reverseDelimited(\"one\") as ?result) }";

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
                    "select ?result where { bind(string:reverseDelimited(\"one\", \"two\", \"three\") as ?result) }";

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
                    "select ?result where { bind(string:reverseDelimited(1, \"two\") as ?result) }";

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
                    "select ?result where { bind(string:reverseDelimited(\"one\", 2) as ?result) }";

                            try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testSecondArgNotSingleChar() {
        exception.expect(QueryBuildException.class);

       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:reverseDelimited(\"Stardog\", \"xx\") as ?result) }";

                                try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                    final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }
}
