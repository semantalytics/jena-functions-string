package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import java.util.Arrays;

import static com.complexible.common.rdf.model.Values.literal;

public final class PrependIfMissingIgnoreCase extends AbstractFunction implements StringFunction {

    protected PrependIfMissingIgnoreCase() {
        super(Range.atLeast(2), StringVocabulary.prependIfMissingIgnoreCase.stringValue());
    }

    private PrependIfMissingIgnoreCase(final PrependIfMissingIgnoreCase prependIfMissingIgnoreCase) {
        super(prependIfMissingIgnoreCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for (final Value value : values) {
            assertStringLiteral(value);
        }

        final String string = assertStringLiteral(values[0]).stringValue();
        final String suffix = assertStringLiteral(values[1]).stringValue();
        final String[] suffixes = Arrays.stream(values).skip(2).map(Value::stringValue).toArray(String[]::new);

        return literal(StringUtils.prependIfMissingIgnoreCase(string, suffix, suffixes));
    }

    @Override
    public PrependIfMissingIgnoreCase copy() {
        return new PrependIfMissingIgnoreCase(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.prependIfMissingIgnoreCase.name();
    }
}
