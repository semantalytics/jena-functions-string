package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class StartsWith extends AbstractFunction implements StringFunction {

    protected StartsWith() {
        super(2, StringVocabulary.startsWith.stringValue());
    }

    private StartsWith(final StartsWith startsWith) {
        super(startsWith);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String prefix = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.startsWith(string, prefix));
    }

    @Override
    public StartsWith copy() {
        return new StartsWith(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.startsWith.name();
    }
}
