package com.semantalytics.jena.function.string;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestArrayFunctionSeparator {

   
    @Test
    public void test() {
    
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:arraySeparator() AS ?result) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();

                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("\u001f", aValue);

                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testTooManyArgs() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"one\") as ?result) }";
            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                final ResultSet result = queryExecution.execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }
}
