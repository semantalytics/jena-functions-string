package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class ContainsOnly extends AbstractFunction implements StringFunction {

    protected ContainsOnly() {
        super(2, StringVocabulary.containsOnly.stringValue());
    }

    private ContainsOnly(final ContainsOnly containsOnly) {
        super(containsOnly);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String validChars = assertStringLiteral(values[1]).stringValue();
      
      return literal(StringUtils.containsOnly(string, validChars));
    }
}
