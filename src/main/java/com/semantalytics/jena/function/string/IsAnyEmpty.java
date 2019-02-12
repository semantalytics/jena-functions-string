package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import java.util.Arrays;

import static com.complexible.common.rdf.model.NodeValues.*;

public final class IsAnyEmpty extends FunctionBase {

    protected IsAnyEmpty() {
        super(Range.atLeast(1), StringVocabulary.isAnyEmpty.stringNodeValue());
    }

    private IsAnyEmpty(final IsAnyEmpty isUpperCase) {
        super(isUpperCase);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        for (final NodeValue value : values) {
            assertStringLiteral(value);
        }

        final String[] strings = Arrays.stream(values).map(NodeValue::stringNodeValue).toArray(String[]::new);

        return literal(StringUtils.isAnyEmpty(strings));
    }
}
