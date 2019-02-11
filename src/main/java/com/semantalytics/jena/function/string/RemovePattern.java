package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class RemovePattern extends AbstractFunction implements StringFunction {

    protected RemovePattern() {
        super(2, StringVocabulary.removePattern.stringValue());
    }

    private RemovePattern(final RemovePattern removePattern) {
        super(removePattern);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String pattern = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.removePattern(string, pattern));
    }

    @Override
    public RemovePattern copy() {
        return new RemovePattern(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.removePattern.name();
    }
}
