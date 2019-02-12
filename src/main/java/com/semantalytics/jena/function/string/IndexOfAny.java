package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

public final class IndexOfAny extends FunctionBase2 {

    protected IndexOfAny() {
        super(2, StringVocabulary.indexOfAny.stringNodeValue());
    }

    private IndexOfAny(final IndexOfAny indexOfAny) {
        super(indexOfAny);
    }

    @Override
    public NodeValue exec(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final String searchChars = assertStringLiteral(values[1]).stringNodeValue();

        return literal(StringUtils.indexOfAny(string, searchChars));
    }
}
