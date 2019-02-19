package com.semantalytics.jena.function.string;

import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.sparql.function.FunctionRegistry;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class TestCaseFormat {

    private Model model;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        FunctionRegistry.get().put(CaseFormat.name, CaseFormat.class);
        model = ModelFactory.createDefaultModel();
    }

    @After
    public void tearDown() {
        model.close();
    }

    @Test
    public void testLowerCamelToUpperUnderscoreByExample() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?caseFormat where { bind(string:caseFormat(\"stardogUnion\", \"fromFormat\", \"TO_FORMAT\") as ?caseFormat) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
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
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
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
                "select ?caseFormat where { bind(string:caseFormat(\"apacheJena\", \"fromFormat\", \"to-format\") as ?caseFormat) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("caseFormat").getString();

            assertEquals("apache-jena", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testLowerCamelToUpperCamelByExample() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?caseFormat where { bind(string:caseFormat(\"apacheJena\", \"fromFormat\", \"ToFormat\") as ?caseFormat) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final String aValue = result.next().getLiteral("caseFormat").getString();

            assertEquals("ApacheJena", aValue);
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testTooManyArgs() {
        exception.expect(QueryBuildException.class);

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?caseFormat where { bind(string:caseFormat(\"one\", \"two\", \"three\", \"four\") as ?caseFormat) }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void testWrongType() {
        exception.expect(QueryBuildException.class);

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select ?caseFormat where { bind(string:caseFormat(7, 8, 9) as ?caseFormat) }";
        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();


            assertTrue("Should have a result", result.hasNext());

            final QuerySolution aQuerySolution = result.next();

            assertTrue("Should have no bindings", aQuerySolution.varNames().hasNext());
            assertFalse("Should have no more results", result.hasNext());
        }
    }
}
