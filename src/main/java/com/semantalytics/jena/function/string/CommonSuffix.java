package com.semantalytics.jena.function.string;

import com.google.common.base.Strings;

public final class CommonSuffix extends AbstractFunction implements StringFunction {

    protected CommonSuffix() {
        super(2, StringVocabulary.commonSuffix.stringValue());
    }

    private CommonSuffix(final CommonSuffix commonSuffix) {
        super(commonSuffix);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String firstString = assertStringLiteral(values[0]).stringValue();
      final String secondString = assertStringLiteral(values[1]).stringValue();
      
      return literal(Strings.commonSuffix(firstString, secondString));
    }

    @Override
    public CommonSuffix copy() {
        return new CommonSuffix(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.commonSuffix.name();
    }
}
