package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import java.util.Arrays;

import static com.complexible.common.rdf.model.Values.literal;

public final class IsAllEmpty extends AbstractFunction implements StringFunction {

    protected IsAllEmpty() {
        super(1, StringVocabulary.isAllEmpty.stringValue());
    }

    private IsAllEmpty(final IsAllEmpty isAllEmpty) {
        super(isAllEmpty);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for(final Value value : values) {
            assertStringLiteral(value);
        }

        final String[] strings = Arrays.stream(values).map(Value::stringValue).toArray(String[]::new);

        return literal(StringUtils.isAllEmpty(strings));
    }

    @Override
    public IsAllEmpty copy() {
        return new IsAllEmpty(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.isAllEmpty.name();
    }
}
