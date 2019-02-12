package com.semantalytics.jena.function.string;

import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.Arrays;

public final class EqualsAny extends FunctionBase {

    protected EqualsAny() {
        super(Range.atLeast(2), StringVocabulary.equalsAny.stringNodeValue());
    }

    @Override
    public NodeValue exec(final NodeValue... values) throws ExpressionEvaluationException {

        for(final NodeValue value : values) {
            assertStringLiteral(value);
        }

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final String[] searchStrings = Arrays.stream(values).skip(1).map(NodeValue::stringNodeValue).toArray(String[]::new);

        return literal(StringUtils.equalsAny(string, searchStrings));
    }
}
