package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;


public final class ContainsAny extends AbstractFunction implements StringFunction {

    protected ContainsAny() {
        super(2, StringVocabulary.containsAny.stringValue());
    }

    private ContainsAny(final ContainsAny containsAny) {
        super(containsAny);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String searchChars = assertStringLiteral(values[1]).stringValue();

      return literal(StringUtils.containsAny(string, searchChars.toCharArray()));
    }
}
