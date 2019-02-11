package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class RemoveAll extends AbstractFunction implements StringFunction {

    protected RemoveAll() {
        super(2, StringVocabulary.removeAll.stringValue());
    }

    private RemoveAll(final RemoveAll removeAll) {
        super(removeAll);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String regex = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.removeAll(string, regex));
    }

    @Override
    public RemoveAll copy() {
        return new RemoveAll(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.removeAll.name();
    }
}
