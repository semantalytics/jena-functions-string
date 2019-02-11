package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class Equals extends AbstractFunction implements StringFunction {

    protected Equals() {
        super(2, StringVocabulary.equals.stringValue());
    }

    private Equals(final Equals equals) {
        super(equals);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String suffix = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.equals(string, suffix));
    }
}
