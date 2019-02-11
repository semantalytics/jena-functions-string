package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Joiner;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class Split extends AbstractFunction implements StringFunction {

    protected Split() {
        super(Range.closed(1, 3), StringVocabulary.split.stringValue());
    }

    private Split(final Split split) {
        super(split);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        switch (values.length) {
            case 1: {
                return literal(Joiner.on("\u001f").join(StringUtils.split(string)));
            }
            case 2: {
                final String separator = assertStringLiteral(values[1]).stringValue();
                return literal(Joiner.on("\u001f").join(StringUtils.split(string, separator)));
            }
            case 3: {
                final String separator = assertStringLiteral(values[1]).stringValue();
                final int max = assertIntegerLiteral(values[2]).intValue();
                return literal(Joiner.on("\u001f").join(StringUtils.split(string, separator, max)));
            }
            default: {
                throw new ExpressionEvaluationException("Takes 1 to 3 arguments. Fount " + values.length);
            }
        }
    }

    @Override
    public Split copy() {
        return new Split(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.split.name();
    }
}
