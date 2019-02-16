package com.semantalytics.jena.function.string;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestArrayFunction {

    @Test
    public void test() {
      
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:array(\"Stardog\", \"graph\", \"database\") AS ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();

                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("Stardog\u001fgraph\u001fdatabase", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testEmptyString() {
      
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:array(\"\", \"\") as ?result) }";
            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                final ResultSet result = queryExecution.execSelect();

                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("\u001f", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testTooFewArgs() {
      
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:array() as ?result) }";
                try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                    final ResultSet result = queryExecution.execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution querySolution = result.next();

                assertTrue("Should have no bindings", querySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testWrongTypeFirstArg() {
       
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:array(1, \"two\", \"three\") as ?result) }";
                    try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                        final ResultSet result = queryExecution.execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution querySolution = result.next();

                assertTrue("Should have no bindings", querySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testWrongTypeSecondArg() {
     
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:array(\"one\", 2, \"three\") as ?result) }";
                        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                            final ResultSet result = queryExecution.execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution querySolution = result.next();

                assertTrue("Should have no bindings", querySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testWrongTypeThirdArg() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:array(\"one\", \"two\", 3) as ?result) }";
                            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                                final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution querySolution = result.next();

            assertTrue("Should have no bindings", querySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
