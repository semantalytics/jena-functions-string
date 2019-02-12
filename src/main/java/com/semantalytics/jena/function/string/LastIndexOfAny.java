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

public final class LastIndexOfAny extends FunctionBase {

    protected LastIndexOfAny() {
        super(Range.atLeast(2), StringVocabulary.lastIndexOfAny.stringNodeValue());
    }

    private LastIndexOfAny(final LastIndexOfAny lastIndexOfAny) {
        super(lastIndexOfAny);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        for (final NodeValue value : values) {
            assertStringLiteral(value);
        }

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final String[] searchStrings = Arrays.stream(values).skip(1).map(NodeValue::stringNodeValue).toArray(String[]::new);

        return literal(StringUtils.lastIndexOfAny(string, searchStrings));
    }
}
