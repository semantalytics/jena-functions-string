package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;

public final class Compare extends AbstractFunction implements StringFunction {

    protected Compare() {
        super(2, StringVocabulary.compare.stringValue());
    }

    private Compare(final Compare compare) {
        super(compare);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string1 = assertStringLiteral(values[0]).stringValue();
        final String string2 = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.compare(string1, string2));
    }

    @Override
    public Compare copy() {
        return new Compare(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.compare.name();
    }
}
