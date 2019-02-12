package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.function.FunctionBase1;
import org.openrdf.model.NodeValue;

import java.util.Arrays;

import static com.complexible.common.rdf.model.NodeValues.literal;

public final class IsAllEmpty extends FunctionBase1 {

    protected IsAllEmpty() {
        super(1, StringVocabulary.isAllEmpty.stringNodeValue());
    }

    private IsAllEmpty(final IsAllEmpty isAllEmpty) {
        super(isAllEmpty);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        for (final NodeValue value : values) {
            assertStringLiteral(value);
        }

        final String[] strings = Arrays.stream(values).map(NodeValue::stringNodeValue).toArray(String[]::new);

        return literal(StringUtils.isAllEmpty(strings));
    }
}
