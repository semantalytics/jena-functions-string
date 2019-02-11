package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class ReplaceFirst extends AbstractFunction implements StringFunction {

    protected ReplaceFirst() {
        super(3, StringVocabulary.replaceFirst.stringValue());
    }

    private ReplaceFirst(final ReplaceFirst replaceFirst) {
        super(replaceFirst);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchString = assertStringLiteral(values[1]).stringValue();
        final String replacement = assertStringLiteral(values[2]).stringValue();

        return literal(StringUtils.replaceFirst(string, searchString, replacement));
    }

    @Override
    public ReplaceFirst copy() {
        return new ReplaceFirst(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.replaceFirst.name();
    }
}
