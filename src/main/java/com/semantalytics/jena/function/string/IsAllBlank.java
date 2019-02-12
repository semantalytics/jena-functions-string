package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.function.FunctionBase1;

import java.util.Arrays;


public final class IsAllBlank extends FunctionBase1 {

    protected IsAllBlank() {
        super(1, StringVocabulary.isAllBlank.stringNodeValue());
    }

    private IsAllBlank(final IsAllBlank isAllBlank) {
        super(isAllBlank);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        for (final NodeValue value : values) {
            assertStringLiteral(value);
        }

        final String[] strings = Arrays.stream(values).map(NodeValue::stringNodeValue).toArray(String[]::new);

        return literal(StringUtils.isAllBlank(strings));
    }
}
