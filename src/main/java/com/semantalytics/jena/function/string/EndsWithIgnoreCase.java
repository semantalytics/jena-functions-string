package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class EndsWithIgnoreCase extends AbstractFunction implements StringFunction {

    protected EndsWithIgnoreCase() {
        super(2, StringVocabulary.endsWithIgnoreCase.stringValue());
    }

    private EndsWithIgnoreCase(final EndsWithIgnoreCase endsWithIgnoreCase) {
        super(endsWithIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String suffix = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.endsWithIgnoreCase(string, suffix));
    }
}
