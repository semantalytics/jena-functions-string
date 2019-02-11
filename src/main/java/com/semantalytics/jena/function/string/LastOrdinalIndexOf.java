package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class LastOrdinalIndexOf extends AbstractFunction implements StringFunction {

    protected LastOrdinalIndexOf() {
        super(3, StringVocabulary.lastOrdinalIndexOf.stringValue());
    }

    private LastOrdinalIndexOf(final LastOrdinalIndexOf lastOrdinalIndexOf) {
        super(lastOrdinalIndexOf);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchString = assertStringLiteral(values[1]).stringValue();
        final int ordinal = assertNumericLiteral(values[2]).intValue();

        return literal(StringUtils.lastOrdinalIndexOf(string, searchString, ordinal));
    }

    @Override
    public LastOrdinalIndexOf copy() {
        return new LastOrdinalIndexOf(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.lastOrdinalIndexOf.name();
    }
}
