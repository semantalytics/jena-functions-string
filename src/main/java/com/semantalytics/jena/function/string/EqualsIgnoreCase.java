package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class EqualsIgnoreCase extends AbstractFunction implements StringFunction {

    protected EqualsIgnoreCase() {
        super(2, StringVocabulary.equalsIgnoreCase.stringValue());
    }

    private EqualsIgnoreCase(final EqualsIgnoreCase equalsIgnoreCase) {
        super(equalsIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String firstString = assertStringLiteral(values[0]).stringValue();
        final String secondString = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.equalsIgnoreCase(firstString, secondString));
    }
}
