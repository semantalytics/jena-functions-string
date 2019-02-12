package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import static com.complexible.common.rdf.model.NodeValues.*;

public final class LastIndexOfIgnoreCase extends FunctionBase {

    protected LastIndexOfIgnoreCase() {
        super(Range.closed(2, 3), StringVocabulary.lastIndexOfIgnoreCase.stringNodeValue());
    }

    private LastIndexOfIgnoreCase(final LastIndexOfIgnoreCase lastIndexOfIgnoreCase) {
        super(lastIndexOfIgnoreCase);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final String searchString = assertStringLiteral(values[1]).stringNodeValue();

        switch (values.length) {
            case 2: {
                return literal(StringUtils.lastIndexOfIgnoreCase(string, searchString));
            }
            case 3: {
                final int startPos = assertNumericLiteral(values[2]).intNodeValue();
                return literal(StringUtils.lastIndexOfIgnoreCase(string, searchString, startPos));
            }
            default:
                throw new ExpressionEvaluationException("function takes two or three arguments. Found " + values.length);
        }
    }
}
