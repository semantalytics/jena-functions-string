package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public class IndexOfDifference extends AbstractFunction implements StringFunction {

    protected IndexOfDifference() {
        super(2, StringVocabulary.indexOfDifference.stringValue());
    }

    private IndexOfDifference(final IndexOfDifference indexOfDifference) {
        super(indexOfDifference);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchChars = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.indexOfDifference(string, searchChars));
    }

    @Override
    public IndexOfDifference copy() {
        return new IndexOfDifference(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.indexOfDifference.name();
    }
}
