package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class ReplaceChars extends AbstractFunction implements StringFunction {

    protected ReplaceChars() {
        super(3, StringVocabulary.replaceChars.stringValue());
    }

    private ReplaceChars(final ReplaceChars replaceChars) {
        super(replaceChars);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchString = assertStringLiteral(values[1]).stringValue();
        final String replacement = assertStringLiteral(values[2]).stringValue();

        return Values.literal(StringUtils.replaceChars(string, searchString, replacement));
    }

    @Override
    public ReplaceChars copy() {
        return new ReplaceChars(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.replaceChars.name();
    }
}
