package com.semantalytics.jena.function.string;

import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public final class EqualsAny extends AbstractFunction implements StringFunction {

    protected EqualsAny() {
        super(Range.atLeast(2), StringVocabulary.equalsAny.stringValue());
    }

    private EqualsAny(final EqualsAny equalsAny) {
        super(equalsAny);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for(final Value value : values) {
            assertStringLiteral(value);
        }

        final String string = assertStringLiteral(values[0]).stringValue();
        final String[] searchStrings = Arrays.stream(values).skip(1).map(Value::stringValue).toArray(String[]::new);

        return literal(StringUtils.equalsAny(string, searchStrings));
    }
}
