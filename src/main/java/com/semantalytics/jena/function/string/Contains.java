package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class Contains extends AbstractFunction implements StringFunction {

    protected Contains() {
        super(2, StringVocabulary.contains.stringValue());
    }

    private Contains(final Contains contains) {
        super(contains);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String sequence = assertStringLiteral(values[0]).stringValue();
        final String searchSequence = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.contains(sequence, searchSequence));
    }

    @Override
    public Contains copy() {
        return new Contains(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.contains.name();
    }
}
