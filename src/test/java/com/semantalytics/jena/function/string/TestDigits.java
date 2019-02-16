package com.semantalytics.jena.function.string;

import org.junit.*;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;

import static org.junit.Assert.*;

public class TestDigits {

    @Test
    public void testGetDigits() {
   
            final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:digits(\"Stard0g\") AS ?result) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("0", aValue);

                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testOnlyDigits() {
       

            final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:digits(\"12345\") AS ?result) }";

            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("12345", aValue);
                assertFalse("Should have no more results", result.hasNext());
            } 
    }
  
    @Test
    public void testEmptyString() {
       
            final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:digits(\"\") as ?result) }";

                try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                    final ResultSet result = queryExecution.execSelect();

        
                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }
  
    @Test
    public void testNonAscii() {
       
            final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:digits(\"१२३\") as ?result) }";

                    try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                        final ResultSet result = queryExecution.execSelect();


        
                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("१२३", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testTooFewArgs() {

       
            final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:digits() as ?result) }";

                        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
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
                    "select ?result where { bind(string:digits(\"Stardog\", \"one\") as ?result) }";

                            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                                final ResultSet result = queryExecution.execSelect();

         
                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testWrongType() {
       
            final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:digits(4) as ?result) }";

                                try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                                    final ResultSet result = queryExecution.execSelect();

       
                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }
}
