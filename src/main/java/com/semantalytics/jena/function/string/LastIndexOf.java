package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class LastIndexOf extends AbstractFunction implements StringFunction {

    protected LastIndexOf() {
        super(Range.closed(2, 3), StringVocabulary.lastIndexOf.stringValue());
    }

    private LastIndexOf(final LastIndexOf lastIndexOf) {
        super(lastIndexOf);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String sequence = assertStringLiteral(values[0]).stringValue();
        final String searchSequence = assertStringLiteral(values[1]).stringValue();

        switch(values.length) {
            case 2: {
                return literal(StringUtils.lastIndexOf(sequence, searchSequence));
            }
            case 3: {
                final int startPos = assertNumericLiteral(values[2]).intValue();
                return literal(StringUtils.lastIndexOf(sequence, searchSequence, startPos));
            }
            default: {
                throw new ExpressionEvaluationException("Function takes 2 or 3 arguments. Found " + values.length);
            }
        }
    }

    @Override
    public LastIndexOf copy() {
        return new LastIndexOf(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.lastIndexOf.name();
    }
}
