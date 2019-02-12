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

public final class StartsWithAny extends FunctionBase {

    protected StartsWithAny() {
        super(Range.atLeast(2), StringVocabulary.startsWithAny.stringValue());
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for (final Value value : values) {
            assertStringLiteral(value);
        }

        final String string = assertStringLiteral(values[0]).stringValue();
        final String[] prefix = Arrays.stream(values).skip(1).map(Value::stringValue).toArray(String[]::new);

        return literal(StringUtils.startsWithAny(string, prefix));
    }
}
