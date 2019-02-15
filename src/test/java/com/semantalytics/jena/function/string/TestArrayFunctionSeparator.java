package com.semantalytics.stardog.kibble.string;
package com.semantalytics.jena.function.string;

import com.semantalytics.jena.function.string.StringVocabulary;
import com.semantalytics.stardog.kibble.AbstractStardogTest;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestArrayFunctionSeparator extends AbstractStardogTest {

   
    @Test
    public void test() {
    
        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:arraySeparator() AS ?result) }";

            try (final TupleQueryResult aResult = connection.select(aQuery).execute()) {

                assertTrue("Should have a result", aResult.hasNext());

                final String aValue = aResult.next().getValue("result").stringValue();

                assertEquals("\u001f", aValue);

                assertFalse("Should have no more results", aResult.hasNext());
            }
    }

    @Test
    public void testTooManyArgs() {

        final String aQuery = StringVocabulary.sparqlPrefix("string") +
                    "select ?result where { bind(string:abbreviate(\"one\") as ?result) }";

            try(final TupleQueryResult aResult = connection.select(aQuery).execute()) {
       
                assertTrue("Should have a result", aResult.hasNext());

                final BindingSet aBindingSet = aResult.next();

                assertTrue("Should have no bindings", aBindingSet.getBindingNames().isEmpty());
                assertFalse("Should have no more results", aResult.hasNext());
            }
    }
}
