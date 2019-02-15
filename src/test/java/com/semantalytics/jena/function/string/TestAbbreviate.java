package com.semantalytics.jena.function.string;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;

import static org.junit.Assert.*;

public class TestAbbreviate {

    private Model model;

    @Before
    public void setUp() {
        FunctionRegistry.get().put(Abbreviate.name, Abbreviate.class);
        model = ModelFactory.createDefaultModel();
    }

    @After
    public void tearDown() {
        model.close();
    }

    @Test
    public void testAbbreviate() {
    
        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"Jena abbreviate function\", 8) AS ?result) }";

        try(QueryExecution queryExecution = QueryExecutionFactory.create(queryString)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String value = result.next().getLiteral("result").getString();

            assertEquals("Jena ...", value);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testAbbreviateWithOffset() {
  
        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"Stardog graph database\", 15, 5) AS ?result) }";

        try(QueryExecution queryExecution = QueryExecutionFactory.create(queryString)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String value = result.next().getLiteral("result").getString();

            assertEquals("...og graph ...", value);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testEmptyString() {
  
        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"\", 5) as ?result) }";

            try(QueryExecution queryExecution = QueryExecutionFactory.create(queryString)) {
        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getLiteral("result").getString();

                assertEquals("", aValue);
                assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testTooFewArgs() {
    
        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"one\") as ?result) }";

                try(QueryExecution queryExecution = QueryExecutionFactory.create(queryString)) {
        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

        assertTrue("Should have a result", result.hasNext());

        final QuerySolution aBindingSet = result.next();

        assertFalse("Should have no bindings", aBindingSet.varNames().hasNext());
        assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testTooManyArgs() {

        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"one\", 9, \"three\", \"four\") as ?result) }";

                    try(QueryExecution queryExecution = QueryExecutionFactory.create(queryString)) {
        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aBindingSet = result.next();

                assertFalse("Should have no bindings", aBindingSet.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testWrongTypeFirstArg() {
     
        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(4, 5) as ?result) }";

                        try(QueryExecution queryExecution = QueryExecutionFactory.create(queryString)) {
        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aBindingSet = result.next();

                assertFalse("Should have no bindings", aBindingSet.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testWrongTypeSecondArg() {

        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"one\", \"two\") as ?result) }";

                            try(QueryExecution queryExecution = QueryExecutionFactory.create(queryString)) {
        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aBindingSet = result.next();

                assertFalse("Should have no bindings", aBindingSet.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testWrongTypeThirdArg() {
 
        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"one\", 9, \"three\") as ?result) }";

                                try(QueryExecution queryExecution = QueryExecutionFactory.create(queryString)) {
        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aBindingSet = result.next();

                assertTrue("Should have no bindings", aBindingSet.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testLengthTooShort() {

        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"Stardog\", 3) as ?result) }";
                                    try(QueryExecution queryExecution = QueryExecutionFactory.create(queryString)) {

        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aBindingSet = result.next();

                assertFalse("Should have no bindings", aBindingSet.varNames().hasNext());
                assertFalse("Should have no more results", result.hasNext());
    }
}
