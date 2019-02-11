package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class EndsWith extends AbstractFunction implements StringFunction {

    protected EndsWith() {
        super(2, StringVocabulary.endsWith.stringValue());
    }

    private EndsWith(final EndsWith endsWith) {
        super(endsWith);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String suffix = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.endsWith(string, suffix));
    }
}
