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

public final class LastIndexOfAny extends AbstractFunction implements StringFunction {

    protected LastIndexOfAny() {
        super(Range.atLeast(2), StringVocabulary.lastIndexOfAny.stringValue());
    }

    private LastIndexOfAny(final LastIndexOfAny lastIndexOfAny) {
        super(lastIndexOfAny);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for(final Value value : values) {
            assertStringLiteral(value);
        }

        final String string = assertStringLiteral(values[0]).stringValue();
        final String[] searchStrings = Arrays.stream(values).skip(1).map(Value::stringValue).toArray(String[]::new);

        return literal(StringUtils.lastIndexOfAny(string, searchStrings));
    }

    @Override
    public LastIndexOfAny copy() {
        return new LastIndexOfAny(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.lastIndexOfAny.name();
    }
}
