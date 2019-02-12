package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import static com.complexible.common.rdf.model.NodeValues.*;

public final class LastIndexOf extends FunctionBase {

    protected LastIndexOf() {
        super(Range.closed(2, 3), StringVocabulary.lastIndexOf.stringNodeValue());
    }

    private LastIndexOf(final LastIndexOf lastIndexOf) {
        super(lastIndexOf);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String sequence = assertStringLiteral(values[0]).stringNodeValue();
        final String searchSequence = assertStringLiteral(values[1]).stringNodeValue();

        switch (values.length) {
            case 2: {
                return literal(StringUtils.lastIndexOf(sequence, searchSequence));
            }
            case 3: {
                final int startPos = assertNumericLiteral(values[2]).intNodeValue();
                return literal(StringUtils.lastIndexOf(sequence, searchSequence, startPos));
            }
            default: {
                throw new ExpressionEvaluationException("Function takes 2 or 3 arguments. Found " + values.length);
            }
        }
    }
}
