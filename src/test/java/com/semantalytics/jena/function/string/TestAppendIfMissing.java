package com.semantalytics.jena.function.string;

import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;

import static org.junit.Assert.*;

public class TestAppendIfMissing extends AbstractStardogTest {

    private Model model;

    @Before
    public void setUp() {
        FunctionRegistry.get().put(Abbreviate.name, Abbreviate.class);
        model = ModelFactory.createDefaultModel();
    }

    @Test
    public void testMissing() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:appendIfMissing(\"Stardog\", \".txt\") AS ?result) }";


            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getValue("result").stringValue();

            assertEquals("Stardog.txt", aValue);
            assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testNotMissing() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:appendIfMissing(\"Stardog.txt\", \".txt\") as ?result) }";


            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getValue("result").stringValue();

            assertEquals("Stardog.txt", aValue);
            assertFalse("Should have no more results", result.hasNext());
    }

    @Test
    public void testTooFewArgs() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:appendIfMissing(\"one\") as ?result) }";

        try(final TupleQueryResult result = connection.select(query).execute()) {

        assertTrue("Should have a result", result.hasNext());

        final BindingSet aBindingSet = result.next();

        assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
        assertFalse("Should have no more results", result.hasNext());
        }
    }


    @Test
    public void testTooManyArgs() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:appendIfMissing(\"one\", 2, \"three\") as ?result) }";

        try(final TupleQueryResult result = connection.select(query).execute()) {

            assertTrue("Should have a result", result.hasNext());

            final BindingSet aBindingSet = result.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeFirstArg() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:appendIfMissing(4, 5) as ?result) }";

        try(final TupleQueryResult result = connection.select(query).execute()) {

            assertTrue("Should have a result", result.hasNext());

            final BindingSet aBindingSet = result.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongTypeSecondArg() {


        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:appendIfMissing(\"one\", 2) as ?result) }";

        try(final TupleQueryResult result = connection.select(query).execute()) {

            assertTrue("Should have a result", result.hasNext());

            final BindingSet aBindingSet = result.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testLengthTooShort() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?result where { bind(string:appendIfMissing(\"Stardog\", 3) as ?result) }";

        try(final TupleQueryResult result = connection.select(query).execute()) {

            assertTrue("Should have a result", result.hasNext());

            final BindingSet aBindingSet = result.next();

            assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
