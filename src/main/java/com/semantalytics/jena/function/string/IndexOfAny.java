package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class IndexOfAny extends AbstractFunction implements StringFunction {

    protected IndexOfAny() {
        super(2, StringVocabulary.indexOfAny.stringValue());
    }

    private IndexOfAny(final IndexOfAny indexOfAny) {
        super(indexOfAny);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchChars = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.indexOfAny(string, searchChars));
    }
}
