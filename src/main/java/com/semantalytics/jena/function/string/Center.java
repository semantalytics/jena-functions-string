package com.semantalytics.jena.function.string;

import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;

public final class Center extends AbstractFunction implements StringFunction {

    protected Center() {
        super(Range.closed(2, 3), StringVocabulary.center.stringValue());
    }

    private Center(final Center center) {
        super(center);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final int size = assertIntegerLiteral(values[1]).intValue();

        switch(values.length) {
            case 2:
                return literal(StringUtils.center(string, size));
            case 3:
                char padChar = assertStringLiteral(values[2]).stringValue().charAt(0);
                return literal(StringUtils.center(string, size, padChar));
            default:
                throw new ExpressionEvaluationException("Function requires 2 or 3 arguments. Found " + values.length);
        }

    }
}
