
package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import java.util.Arrays;

import com.google.common.collect.Range;

import static com.complexible.common.rdf.model.Values.literal;

public final class Join extends AbstractFunction implements StringFunction {

    protected Join() {
        super(Range.atLeast(1), StringVocabulary.join.stringValue());
    }

    private Join(final Join join) {
        super(join);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for (final Value value : values) {
            assertStringLiteral(value);
        }

        final String[] pieces = Arrays.stream(values).map(Value::stringValue).toArray(String[]::new);

        return literal(StringUtils.join(pieces));
    }

    @Override
    public Join copy() {
        return new Join(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.join.name();
    }
}
