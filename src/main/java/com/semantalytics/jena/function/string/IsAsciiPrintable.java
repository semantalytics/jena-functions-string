package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class IsAsciiPrintable extends AbstractFunction implements StringFunction {

    protected IsAsciiPrintable() {
        super(1, StringVocabulary.isAsciiPrintable.stringValue());
    }

    private IsAsciiPrintable(final IsAsciiPrintable isAsciiPrintable) {
        super(isAsciiPrintable);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(StringUtils.isAsciiPrintable(string));
    }

    @Override
    public IsAsciiPrintable copy() {
        return new IsAsciiPrintable(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.isAsciiPrintable.name();
    }
}
