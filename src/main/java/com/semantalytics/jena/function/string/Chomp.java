package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class Chomp extends AbstractFunction implements StringFunction {

    protected Chomp() {
        super(1, StringVocabulary.chomp.stringValue());
    }

    private Chomp(final Chomp chomp) {
        super(chomp);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(StringUtils.chomp(string));
    }
}
