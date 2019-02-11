package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import java.util.Arrays;

import static com.complexible.common.rdf.model.Values.literal;

public final class IsAllBlank extends AbstractFunction implements StringFunction {

    protected IsAllBlank() {
        super(1, StringVocabulary.isAllBlank.stringValue());
    }

    private IsAllBlank(final IsAllBlank isAllBlank) {
        super(isAllBlank);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for(final Value value : values) {
            assertStringLiteral(value);
        }

        final String[] strings = Arrays.stream(values).map(Value::stringValue).toArray(String[]::new);

        return literal(StringUtils.isAllBlank(strings));
    }

    @Override
    public IsAllBlank copy() {
        return new IsAllBlank(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.isAllBlank.name();
    }
}
