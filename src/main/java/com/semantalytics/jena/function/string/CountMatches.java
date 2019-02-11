package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class CountMatches extends AbstractFunction implements StringFunction {

    protected CountMatches() {
        super(2, StringVocabulary.countMatches.stringValue());
    }

    private CountMatches(final CountMatches countMatches) {
        super(countMatches);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String sequence = assertStringLiteral(values[1]).stringValue();
      
      return literal(StringUtils.countMatches(string, sequence));
    }
}
