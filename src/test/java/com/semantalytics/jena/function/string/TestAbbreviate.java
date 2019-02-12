package com.semantalytics.stardog.kibble.string;

import com.semantalytics.jena.function.string.Abbreviate;
import com.semantalytics.jena.function.string.StringVocabulary;
import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;

import static org.junit.Assert.*;

public class TestAbbreviate extends AbstractStardogTest {

    private Model model;

    @Before
    public void setUp() {
        FunctionRegistry.get().put(Abbreviate.name, Abbreviate.class);
        model = ModelFactory.createDefaultModel();
    }

   
    @Test
    public void testAbbreviate() {
    
        final String queryString = "prefix string: <" + Abbreviate.name + "> \n" +
                    "select ?result where { bind(string:abbreviate(\"Jena abbreviate function\", 8) AS ?result) }";

        final Query query = QueryFactory.create(queryString);

        final ResultSet result = QueryExecutionFactory.create(query, model).execSelect();

        assertTrue("Should have a result", result.hasNext());

        final String aValue = result.next().getLiteral("result").getString();

        assertEquals("Jena ...", aValue);

        assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testAbbreviateWithOffset() {
  
        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"Stardog graph database\", 15, 5) AS ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("...og graph ...", aValue);
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testEmptyString() {
  
        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"\", 5) as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("", aValue);
                assertFalse("Should have no more results", aResult.hasNext());
            }     
    }

    @Test
    public void testTooFewArgs() {
    
        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"one\") as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testTooManyArgs() {

        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"one\", 9, \"three\", \"four\") as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
       
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testWrongTypeFirstArg() {
     
        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(4, 5) as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
     
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testWrongTypeSecondArg() {

        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"one\", \"two\") as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
   
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testWrongTypeThirdArg() {
 
        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"one\", 9, \"three\") as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testLengthTooShort() {
     
        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"Stardog\", 3) as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
}
