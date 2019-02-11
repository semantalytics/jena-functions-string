package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class ReplaceOnceIgnoreCase extends AbstractFunction implements StringFunction {

    protected ReplaceOnceIgnoreCase() {
        super(3, StringVocabulary.replaceOnceIgnoreCase.stringValue());
    }

    private ReplaceOnceIgnoreCase(final ReplaceOnceIgnoreCase replaceOnceIgnoreCase) {
        super(replaceOnceIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchString = assertStringLiteral(values[1]).stringValue();
        final String replacement = assertStringLiteral(values[2]).stringValue();

        return Values.literal(StringUtils.replaceOnceIgnoreCase(string, searchString, replacement));
    }

    @Override
    public ReplaceOnceIgnoreCase copy() {
        return new ReplaceOnceIgnoreCase(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.replaceOnceIgnoreCase.name();
    }
}
