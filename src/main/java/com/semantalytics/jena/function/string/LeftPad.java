package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class LeftPad extends AbstractFunction implements StringFunction {

    protected LeftPad() {
        super(Range.closed(2, 3), StringVocabulary.leftPad.stringValue());
    }

    private LeftPad(final LeftPad leftPad) {
        super(leftPad);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final int size = assertNumericLiteral(values[1]).intValue();

        switch(values.length) {
            case 2: {
                return literal(StringUtils.leftPad(string, size));
            }
            case 3: {
                final String padStr = assertStringLiteral(values[2]).stringValue();
                return literal(StringUtils.leftPad(string, size, padStr));
            }
            default:
            {
                throw new ExpressionEvaluationException("Function takes 2 or 3 arguments. Found " + values.length);
            }
        }
    }

    @Override
    public LeftPad copy() {
        return new LeftPad(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.leftPad.name();
    }
}
