package com.semantalytics.jena.function.string;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestTruncate {

    private Model model;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        FunctionRegistry.get().put(Truncate.name, Truncate.class);
        model = ModelFactory.createDefaultModel();
    }

    @After
    public void tearDown() {
        model.close();
    }

    @Test
    public void testTruncate() {
   
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:truncate(\"Jena\", 3) AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("Jen", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testEmptyString() {
      
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:truncate(\"\", 5) as ?result) }";

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
                    "select ?result where { bind(string:truncate(\"one\") as ?result) }";

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
                    "select ?result where { bind(string:truncate(\"one\", 2, \"three\") as ?result) }";

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
                    "select ?result where { bind(string:truncate(1, 2) as ?result) }";

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
                    "select ?result where { bind(string:truncate(\"one\", \"two\") as ?result) }";

                            try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }
}
