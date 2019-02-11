package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class SubstringBeforeLast extends AbstractFunction implements StringFunction {

    protected SubstringBeforeLast() {
        super(2, StringVocabulary.substringBeforeLast.stringValue());
    }

    private SubstringBeforeLast(final SubstringBeforeLast substringBeforeLast) {
        super(substringBeforeLast);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String separator = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.substringBeforeLast(string, separator));
    }

    @Override
    public SubstringBeforeLast copy() {
        return new SubstringBeforeLast(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.substringBeforeLast.name();
    }
}
