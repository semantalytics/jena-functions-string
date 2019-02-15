package com.semantalytics.jena.function.string;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;

import static org.junit.Assert.*;

public class TestAbbreviateWithMarker {

    private Model model;

    @Before
    public void setUp() {
        FunctionRegistry.get().put(AbbreviateWithMarker.name, AbbreviateWithMarker.class);
        model = ModelFactory.createDefaultModel();
    }

    @After
    public void tearDown() {
        model.close();
    }

  
    @Test
    public void testThreeArg() {
    
        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviateWithMarker(\"Stardog graph database\", \"***\" , 10) AS ?result) }";

        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

        assertTrue("Should have a result", result.hasNext());

        final String aValue = result.next().getValue("result").stringValue();

        assertEquals("Stardog***", aValue);
        assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testFourArg() {
     
        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviateWithMarker(\"Stardog graph database\", \"***\", 10, 3) AS ?result) }";

        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getValue("result").stringValue();

                assertEquals("Stardog***", aValue);
                assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testEmptyString() {
      
        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:abbreviateWithMarker(\"\", \"***\", 5) as ?abbreviation) }";

        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

                assertTrue("Should have a result", result.hasNext());

                final String aValue = result.next().getValue("abbreviation").stringValue();

                assertEquals("", aValue);
                assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testTooFewArgs() {

        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:abbreviateWithMarker(\"one\") as ?abbreviation) }";

        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.getBindingNames().isEmpty());
                assertFalse("Should have no more results", result.hasNext());
    }


    @Test
    public void testTooManyArgs() {

        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:abbreviateWithMarker(\"one\", 2, \"three\") as ?abbreviation) }";

        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.getBindingNames().isEmpty());
                assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testWrongTypeFirstArg() {
  
        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:abbreviateWithMarker(4, 5) as ?abbreviation) }";

        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.getBindingNames().isEmpty());
                assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testWrongTypeSecondArg() {
   
        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:abbreviateWithMarker(\"one\", \"two\") as ?abbreviation) }";

        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();


                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.getBindingNames().isEmpty());
                assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testLengthTooShort() {
    
        final String queryString = StringVocabulary.sparqlPrefix("string") +
                    "select ?abbreviation where { bind(string:abbreviateWithMarker(\"Stardog\", 3) as ?abbreviation) }";

        final Query query = QueryFactory.create(queryString);
        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

                assertTrue("Should have a result", result.hasNext());

                final QuerySolution aQuerySolution = result.next();

                assertTrue("Should have no bindings", aQuerySolution.getBindingNames().isEmpty());
                assertFalse("Should have no more results", result.hasNext());
    }
}
