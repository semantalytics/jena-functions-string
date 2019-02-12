package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.function.FunctionBase2;

public final class IndexOfAnyBut extends FunctionBase2 {

    protected IndexOfAnyBut() {
        super(2, StringVocabulary.indexOfAnyBut.stringNodeValue());
    }

    private IndexOfAnyBut(final IndexOfAnyBut indexOfAnyBut) {
        super(indexOfAnyBut);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final String searchChars = assertStringLiteral(values[1]).stringNodeValue();

        return literal(StringUtils.indexOfAnyBut(string, searchChars));
    }
}
