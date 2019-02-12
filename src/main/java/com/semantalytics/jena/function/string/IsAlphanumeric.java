package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.function.FunctionBase1;

public final class IsAlphanumeric extends FunctionBase1 {

    protected IsAlphanumeric() {
        super(1, StringVocabulary.isAlphanumeric.stringNodeValue());
    }

    private IsAlphanumeric(final IsAlphanumeric isUpperCase) {
        super(isUpperCase);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();

        return literal(StringUtils.isAlphanumeric(string));
    }
}
