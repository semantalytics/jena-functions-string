package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class Substring extends AbstractFunction implements StringFunction {

    protected Substring() {
        super(2, StringVocabulary.substring.stringValue());
    }

    private Substring(final Substring substring) {
        super(substring);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final int start = assertIntegerLiteral(values[1]).intValue();

        return literal(StringUtils.substring(string, start));
    }

    @Override
    public Substring copy() {
        return new Substring(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.substring.name();
    }
}
