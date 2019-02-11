package com.semantalytics.jena.function.string;


import org.apache.commons.lang3.StringUtils;

public final class Chop extends AbstractFunction implements StringFunction {

    protected Chop() {
        super(1, StringVocabulary.chop.stringValue());
    }

    private Chop(final Chop chop) {
        super(chop);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return literal(StringUtils.chop(string));
    }
}
