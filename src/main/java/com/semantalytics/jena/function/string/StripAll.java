package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Joiner;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import java.util.Arrays;

import static com.complexible.common.rdf.model.Values.literal;

public final class StripAll extends FunctionBase {

    protected StripAll() {
        super(Range.atLeast(1), StringVocabulary.stripAll.stringValue());
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for (final Value value : values) {
            assertStringLiteral(value).stringValue();
        }

        final String[] strings = Arrays.stream(values).map(Value::stringValue).toArray(String[]::new);

        return literal(Joiner.on("\u001f").join(StringUtils.stripAll(strings)));
    }
}
