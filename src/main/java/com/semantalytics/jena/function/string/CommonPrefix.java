package com.semantalytics.jena.function.string;

import com.google.common.base.Strings;

public final class CommonPrefix extends AbstractFunction implements StringFunction {

    protected CommonPrefix() {
        super(2, StringVocabulary.commonPrefix.stringValue());
    }

    private CommonPrefix(final CommonPrefix commonPrefix) {
        super(commonPrefix);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String firstString = assertStringLiteral(values[0]).stringValue();
      final String secondString = assertStringLiteral(values[1]).stringValue();
      
      return literal(Strings.commonPrefix(firstString, secondString));
    }
}
