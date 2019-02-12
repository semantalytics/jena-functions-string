package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.function.FunctionBase2;

public class IndexOfDifference extends FunctionBase2 {

    protected IndexOfDifference() {
        super(2, StringVocabulary.indexOfDifference.stringNodeValue());
    }

    private IndexOfDifference(final IndexOfDifference indexOfDifference) {
        super(indexOfDifference);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final String searchChars = assertStringLiteral(values[1]).stringNodeValue();

        return literal(StringUtils.indexOfDifference(string, searchChars));
    }
}
