package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class StripEnd extends AbstractFunction implements StringFunction {

    protected StripEnd() {
        super(2, StringVocabulary.stripEnd.stringValue());
    }

    private StripEnd(final StripEnd stripEnd) {
        super(stripEnd);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String stripChars = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.stripEnd(string, stripChars));
    }

    @Override
    public StripEnd copy() {
        return new StripEnd(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.stripEnd.name();
    }
}
