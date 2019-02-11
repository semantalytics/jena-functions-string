package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class DefaultIfEmpty extends AbstractFunction implements StringFunction {

    protected DefaultIfEmpty() {
        super(2, StringVocabulary.defaultIfEmpty.stringValue());
    }

    private DefaultIfEmpty(final DefaultIfEmpty defaultIfEmpty) {
        super(defaultIfEmpty);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String defaultString = assertStringLiteral(values[1]).stringValue();
      
      return literal(StringUtils.defaultIfEmpty(string, defaultString));
    }
}
