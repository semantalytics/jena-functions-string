package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import static com.complexible.common.rdf.model.NodeValues.*;

public final class LeftPad extends FunctionBase {

    protected LeftPad() {
        super(Range.closed(2, 3), StringVocabulary.leftPad.stringNodeValue());
    }

    private LeftPad(final LeftPad leftPad) {
        super(leftPad);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final int size = assertNumericLiteral(values[1]).intNodeValue();

        switch (values.length) {
            case 2: {
                return literal(StringUtils.leftPad(string, size));
            }
            case 3: {
                final String padStr = assertStringLiteral(values[2]).stringNodeValue();
                return literal(StringUtils.leftPad(string, size, padStr));
            }
            default: {
                throw new ExpressionEvaluationException("Function takes 2 or 3 arguments. Found " + values.length);
            }
        }
    }
}
