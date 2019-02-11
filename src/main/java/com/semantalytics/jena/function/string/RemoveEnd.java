package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class RemoveEnd extends AbstractFunction implements StringFunction {

    protected RemoveEnd() {
        super(2, StringVocabulary.removeEnd.stringValue());
    }

    private RemoveEnd(final RemoveEnd removeEnd) {
        super(removeEnd);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String remove = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.removeEnd(string, remove));
    }

    @Override
    public RemoveEnd copy() {
        return new RemoveEnd(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.removeEnd.name();
    }
}
