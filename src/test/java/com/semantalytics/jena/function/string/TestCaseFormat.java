package com.semantalytics.jena.function.string;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.junit.*;

import static org.junit.Assert.*;

public class TestCaseFormat {
 
    @Test
    public void testLowerCamelToUpperUnderscoreByExample() {
       
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?caseFormat where { bind(string:caseFormat(\"stardogUnion\", \"fromFormat\", \"TO_FORMAT\") as ?caseFormat) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
            final ResultSet result = queryExecution.execSelect();

                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("caseFormat").getString();

                assertEquals("STARDOG_UNION", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testLowerCamelToLowerUnderscoreByExample() {
     
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?caseFormat where { bind(string:caseFormat(\"stardogUnion\", \"fromFormat\", \"to_format\") as ?caseFormat) }";
            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("caseFormat").getString();

                assertEquals("stardog_union", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testLowerCamelToLowerHyphenByExample() {
       
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?caseFormat where { bind(string:caseFormat(\"stardogUnion\", \"fromFormat\", \"to-format\") as ?caseFormat) }";
                try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                    final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("caseFormat").getString();

                assertEquals("stardog-union", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testLowerCamelToUpperCamelByExample() {
    
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?caseFormat where { bind(string:caseFormat(\"stardogUnion\", \"fromFormat\", \"ToFormat\") as ?caseFormat) }";
                    try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                        final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("caseFormat").getString();

                assertEquals("StardogUnion", aValue);
                assertFalse("Should have no more results", result.hasNext());
            }
    }

    @Test
    public void testTooManyArgs() {
      
        final String query = StringVocabulary.sparqlPrefix("string") +
                    "select ?caseFormat where { bind(string:caseFormat(\"one\", \"two\", \"three\", \"four\") as ?caseFormat) }";
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
                    "select ?caseFormat where { bind(string:caseFormat(7, 8, 9) as ?caseFormat) }";
                            try (QueryExecution queryExecution = QueryExecutionFactory.create(query)) {
                                final ResultSet result = queryExecution.execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
            }
    }
}
