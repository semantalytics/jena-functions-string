package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class RemoveEndIgnoreCase extends AbstractFunction implements StringFunction {

    protected RemoveEndIgnoreCase() {
        super(2, StringVocabulary.removeEndIgnoreCase.stringValue());
    }

    private RemoveEndIgnoreCase(final RemoveEndIgnoreCase removeEndIgnoreCase) {
        super(removeEndIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String remove = assertStringLiteral(values[1]).stringValue();

        return literal(StringUtils.removeEndIgnoreCase(string, remove));
    }

    @Override
    public RemoveEndIgnoreCase copy() {
        return new RemoveEndIgnoreCase(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.removeEndIgnoreCase.name();
    }
}
