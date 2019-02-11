package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import java.util.Arrays;

import static com.complexible.common.rdf.model.Values.*;

public final class IsAnyBlank extends AbstractFunction implements StringFunction {

    protected IsAnyBlank() {
        super(Range.atLeast(1), StringVocabulary.isAnyBlank.stringValue());
    }

    private IsAnyBlank(final IsAnyBlank isAnyBlank) {
        super(isAnyBlank);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for(final Value value : values) {
            assertStringLiteral(value);
        }

        final String[] strings = Arrays.stream(values).map(Value::stringValue).toArray(String[]::new);

        return literal(StringUtils.isAnyBlank(strings));
    }

    @Override
    public IsAnyBlank copy() {
        return new IsAnyBlank(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.isAnyBlank.name();
    }
}
