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

public final class StartsWithAny extends AbstractFunction implements StringFunction {

    protected StartsWithAny() {
        super(Range.atLeast(2), StringVocabulary.startsWithAny.stringValue());
    }

    private StartsWithAny(final StartsWithAny startsWithAny) {
        super(startsWithAny);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for(final Value value : values) {
            assertStringLiteral(value);
        }

        final String string = assertStringLiteral(values[0]).stringValue();
        final String[] prefix = Arrays.stream(values).skip(1).map(Value::stringValue).toArray(String[]::new);

        return literal(StringUtils.startsWithAny(string, prefix));
    }

    @Override
    public StartsWithAny copy() {
        return new StartsWithAny(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.startsWithAny.name();
    }
}
