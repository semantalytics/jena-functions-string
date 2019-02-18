package com.semantalytics.jena.function.string;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestOverlay {

    private Model model;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        FunctionRegistry.get().put(Overlay.name, Overlay.class);
        model = ModelFactory.createDefaultModel();
    }

    @After
    public void tearDown() {
        model.close();
    }

    @Test
    public void overlayMiddle() {

       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:overlay(\"Stardog\", \"****\", 2, 4) AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("St****dog", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void overlayEmptyString() {
  
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:overlay(\"\", \"****\", 0, 0) as ?result) }";

            try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                final ResultSet result = queryExecution.execSelect();

        
                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("****", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testTooFewArgs() {

       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:overlay(\"one\", \"two\", 3) as ?result) }";

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
   
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:overlay(\"one\", \"two\", 3, 4, 5) as ?result) }";

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
                    "select ?result where { bind(string:overlay(1, \"two\", 3, 4) as ?result) }";

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
                    "select ?result where { bind(string:overlay(\"one\", 2, 3, 4) as ?result) }";

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
        
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:overlay(\"one\", \"two\", \"three\", 4) as ?result) }";

                                try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                    final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testWrongTypeFourthArg() {
        
       final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:overlay(\"one\", \"two\", 3, \"four\") as ?result) }";

                                    try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                        final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }
}
