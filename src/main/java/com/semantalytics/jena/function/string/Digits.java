package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class Digits extends AbstractFunction implements StringFunction {

    protected Digits() {
        super(1, StringVocabulary.digits.stringValue());
    }

    private Digits(final Digits digits) {
        super(digits);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();

      return literal(StringUtils.getDigits(string));
    }
}
