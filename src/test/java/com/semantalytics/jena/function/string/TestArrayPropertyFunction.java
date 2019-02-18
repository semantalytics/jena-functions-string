package com.semantalytics.jena.function.string;

import java.util.stream.Collectors;

import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.ResultSet;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestArrayPropertyFunction {

    /*
    @Test(expected = ExecutionException.class)
    public void tooManyResultsThrowsError() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                " select * where { (?too ?many ?args) string:array (\"stardog\") }";

        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
            final ResultSet result = queryExecution.execSelect();

        try {
            fail("Should not have successfully executed");
        } finally {
            result.close();
        }
    }


    @Test(expected = ExecutionException.class)
    public void resultTermsWhichAreNotVariablesShouldBeAnError() {
        final String query = StringVocabulary.sparqlPrefix("string") +
                " select * where { (\"no literals allowed\") string:array (\"stardog\") }";

            try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                final ResultSet result = queryExecution.execSelect();
        try {
            fail("Should not have successfully executed");
        } finally {
            result.close();
        }
    }

    @Test(expected = ExecutionException.class)
    public void tooManyInputsThrowsError() {
        final String query = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (\"stardog\" 5) }";
                try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                    final ResultSet result = queryExecution.execSelect();



        try {
            fail("Should not have successfully executed");
        } finally {
            result.close();
        }
    }

    @Test(expected = ExecutionException.class)
    public void argCannotBeANonnumericLiteral() {
        final String query = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (5) }";
                    try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                        final ResultSet result = queryExecution.execSelect();


        try {
            fail("Should not have successfully executed");
        } finally {
            result.close();
        }
    }

    @Test(expected = ExecutionException.class)
    public void argCannotBeAnIRI() {
        final String query = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (<http://example.com>) }";
                        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                            final ResultSet result = queryExecution.execSelect();


        try {
            fail("Should not have successfully executed");
        } finally {
            result.close();
        }
    }

    @Test
    public void argCannotBeABNode() {
        final String query = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (_:bnode) }";
                            try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                final ResultSet result = queryExecution.execSelect();

            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void varInputWithNoResultsShouldProduceZeroResults() {
        final String query = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (?input) }";
                                try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                    final ResultSet result = queryExecution.execSelect();


        try {
            assertFalse(result.hasNext());
        } finally {
            result.close();
        }
    }

    @Test
    public void simpleStringArray() {
        final String query = StringVocabulary.sparqlPrefix("string") +
                " select * where { ?result string:array (\"star\u001fdog\") }";
                                    try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                        final ResultSet result = queryExecution.execSelect();


            final List<Value> aExpected = Lists.newArrayList(literal("star"), literal("dog"));
            final List<Value> results = Iterations.stream(result).map(QuerySolutions.select("result")).collect(Collectors.toList());

            assertEquals(aExpected, results);
        }
    }

    @Test
    public void stringArrayWithIndex() {
        final String query = StringVocabulary.sparqlPrefix("string") +
                " select * where { (?result ?idx) string:array (\"star\u001fdog\") }";
                                        try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                            final ResultSet result = queryExecution.execSelect();

        QuerySolution aQuerySolution;


            aQuerySolution = result.next();

            assertEquals(literal("star"), aQuerySolution.getLiteral("result"));
            assertEquals(literal(0, StardogValueFactory.Datatype.INTEGER), aQuerySolution.getLiteral("idx"));

            aQuerySolution = result.next();

            assertEquals(literal("dog"), aQuerySolution.getLiteral("result"));
            assertEquals(literal(1, StardogValueFactory.Datatype.INTEGER), aQuerySolution.getLiteral("idx"));

            assertFalse("Should have no more results", result.hasNext());
        }
    }

    @Test
    public void repeatWithVarInput() {
        final String query = StringVocabulary.sparqlPrefix("string") +
                " select * where { (?result ?idx) string:array (?in) . values ?in { \"star\u001fdog\u001fdatabase\"} }";
                                            try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                                final ResultSet result = queryExecution.execSelect();

        QuerySolution aQuerySolution;


            aQuerySolution = result.next();

            assertEquals(literal("star"), aQuerySolution.getLiteral("result"));
            assertEquals(literal(0, StardogValueFactory.Datatype.INTEGER), aQuerySolution.getLiteral("idx"));

            aQuerySolution = result.next();

            assertEquals(literal("dog"), aQuerySolution.getLiteral("result"));
            assertEquals(literal(1, StardogValueFactory.Datatype.INTEGER), aQuerySolution.getLiteral("idx"));

            aQuerySolution = result.next();

            assertEquals(literal("database"), aQuerySolution.getLiteral("result"));
            assertEquals(literal(2, StardogValueFactory.Datatype.INTEGER), aQuerySolution.getLiteral("idx"));

            assertFalse("Should have no more results", result.hasNext());
        }
    }

    /*
    @Test
    public void costAndCardinalityShouldBeCorrect() throws Exception {
        final String query = StringVocabulary.sparqlPrefix("string") +
            "select * where { (?result ?idx) string:array (\"star\u001fdog\") }";

        Optional<PlanNode> result = PlanNodes.find(new QueryParserImpl().parseQuery(query, model, Namespaces.STARDOG).getNode(),
                                                    PlanNodes.is(ArrayPropertyFunction.ArrayPlanNode.class));

        assertTrue(result.isPresent());

        ArrayPropertyFunction.ArrayPlanNode aNode = (ArrayPropertyFunction.ArrayPlanNode) result.get();

        new ArrayPropertyFunction().estimate(aNode);

        assertEquals(5d, aNode.getCost(), .00001);

        assertEquals(Accuracy.ACCURATE, aNode.getCardinality().accuracy());
        assertEquals(5d, aNode.getCardinality().value(), .00001);
    }

    @Test
    public void costAndCardinalityShouldBeCorrectWithArg() throws Exception {

        final String query = StringVocabulary.sparqlPrefix("string") +
            "select * where { (?result ?idx) string:array (?in) . values ?in { \"star\u001fdog"} }";

        Optional<PlanNode> result = PlanNodes.find(new QueryParserImpl().parseQuery(query, model, Namespaces.STARDOG).getNode(),
                                                  PlanNodes.is(ArrayPropertyFunction.ArrayPlanNode.class));

        assertTrue(result.isPresent());

        ArrayPropertyFunction.ArrayPlanNode aNode = (ArrayPropertyFunction.ArrayPlanNode) result.get();

        aNode.getArg().setCardinality(Cardinality.of(3, Accuracy.ACCURATE));
        aNode.getArg().setCost(3);

        new ArrayPropertyFunction().estimate(aNode);

        assertEquals(18d, aNode.getCost(), .00001);

        assertEquals(Accuracy.UNKNOWN, aNode.getCardinality().accuracy());
        assertEquals(15d, aNode.getCardinality().value(), .00001);
    }

    @Test
    public void shouldRenderACustomExplanation() {

        final String query = StringVocabulary.sparqlPrefix("string") +
                "select * where { (?result ?idx) string:array (\"star\u001fdog\") }";
                                                try (QueryExecution queryExecution = QueryExecutionFactory.create(query, model)) {
                                                    final ResultSet result = queryExecution.execSelect();

        assertTrue(connection.select(query, model)).explain().contains("StringArray("));
    }

*/
}
