package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class IndexOfAnyBut extends AbstractFunction implements StringFunction {

    protected IndexOfAnyBut() {
        super(2, StringVocabulary.indexOfAnyBut.stringValue());
    }

    private IndexOfAnyBut(final IndexOfAnyBut indexOfAnyBut) {
        super(indexOfAnyBut);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchChars = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.indexOfAnyBut(string, searchChars));
    }
}
