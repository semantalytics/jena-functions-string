package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.*;

public final class OrdinalIndexOf extends AbstractFunction implements StringFunction {

    protected OrdinalIndexOf() {
        super(3, StringVocabulary.ordinalIndexOf.stringValue());
    }

    private OrdinalIndexOf(final OrdinalIndexOf ordinalIndexOf) {
        super(ordinalIndexOf);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchStr = assertStringLiteral(values[1]).stringValue();
        final int ordinal = assertNumericLiteral(values[2]).intValue();

        return literal(StringUtils.ordinalIndexOf(string, searchStr, ordinal));
    }

    @Override
    public OrdinalIndexOf copy() {
        return new OrdinalIndexOf(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.ordinalIndexOf.name();
    }
}
