package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class Difference extends AbstractFunction implements StringFunction {

    protected Difference() {
        super(2, StringVocabulary.difference.stringValue());
    }

    private Difference(final Difference difference) {
        super(difference);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string1 = assertStringLiteral(values[0]).stringValue();
        final String string2 = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.difference(string1, string2));
    }
}
