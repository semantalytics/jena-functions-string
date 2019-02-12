package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.function.FunctionBase2;

public class IndexOfIgnoreCase extends FunctionBase2 {

    protected IndexOfIgnoreCase() {
        super(2, StringVocabulary.indexOfIgnoreCase.stringNodeValue());
    }

    private IndexOfIgnoreCase(final IndexOfIgnoreCase indexOfIgnoreCase) {
        super(indexOfIgnoreCase);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final String searchChars = assertStringLiteral(values[1]).stringNodeValue();

        return literal(StringUtils.indexOfIgnoreCase(string, searchChars));
    }
}
