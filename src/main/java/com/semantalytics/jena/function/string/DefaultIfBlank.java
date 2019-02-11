package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class DefaultIfBlank extends AbstractFunction implements StringFunction {

    protected DefaultIfBlank() {
        super(2, StringVocabulary.defaultIfBlank.stringValue());
    }

    private DefaultIfBlank(final DefaultIfBlank abbreviate) {
        super(abbreviate);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String defaultString = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.defaultIfBlank(string, defaultString));
    }
}
