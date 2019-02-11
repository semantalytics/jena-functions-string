package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class LastIndexOfIgnoreCase extends AbstractFunction implements StringFunction {

    protected LastIndexOfIgnoreCase() {
        super(Range.closed(2, 3), StringVocabulary.lastIndexOfIgnoreCase.stringValue());
    }

    private LastIndexOfIgnoreCase(final LastIndexOfIgnoreCase lastIndexOfIgnoreCase) {
        super(lastIndexOfIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchString = assertStringLiteral(values[1]).stringValue();

        switch(values.length) {
            case 2: {
                return literal(StringUtils.lastIndexOfIgnoreCase(string, searchString));
            }
            case 3: {
                final int startPos = assertNumericLiteral(values[2]).intValue();
                return literal(StringUtils.lastIndexOfIgnoreCase(string, searchString, startPos));
            }
            default:
                throw new ExpressionEvaluationException("function takes two or three arguments. Found " + values.length);
        }
    }

    @Override
    public LastIndexOfIgnoreCase copy() {
        return new LastIndexOfIgnoreCase(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.lastIndexOfIgnoreCase.name();
    }
}
