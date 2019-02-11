package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class ContainsWhitespace extends AbstractFunction implements StringFunction {

    protected ContainsWhitespace() {
        super(1, StringVocabulary.containsWhitespace.stringValue());
    }

    private ContainsWhitespace(final ContainsWhitespace containsWhitespace) {
        super(containsWhitespace);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();

      return literal(StringUtils.containsWhitespace(string));
    }
}
