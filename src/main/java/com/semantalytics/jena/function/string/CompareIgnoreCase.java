package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class CompareIgnoreCase extends AbstractFunction implements StringFunction {

    protected CompareIgnoreCase() {
        super(2, StringVocabulary.compareIgnoreCase.stringValue());
    }

    private CompareIgnoreCase(final CompareIgnoreCase compareIgnoreCase) {
        super(compareIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String firstString = assertStringLiteral(values[0]).stringValue();
        final String secondString = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.compareIgnoreCase(firstString, secondString));
    }

    @Override
    public CompareIgnoreCase copy() {
        return new CompareIgnoreCase(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.compareIgnoreCase.name();
    }
}
