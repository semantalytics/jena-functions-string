package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class ContainsNone extends AbstractFunction implements StringFunction {

    protected ContainsNone() {
        super(2, StringVocabulary.containsNone.stringValue());
    }

    private ContainsNone(final ContainsNone containsNone) {
        super(containsNone);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String invalidChars = assertStringLiteral(values[1]).stringValue();
      
      return literal(StringUtils.containsNone(string, invalidChars));
    }
}
