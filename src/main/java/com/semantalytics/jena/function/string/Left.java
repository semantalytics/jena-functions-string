package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import static com.complexible.common.rdf.model.NodeValues.*;

public final class Left extends FunctionBase {

    protected Left() {
        super(2, StringVocabulary.left.stringNodeValue());
    }

    private Left(final Left left) {
        super(left);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final int length = assertNumericLiteral(values[1]).intNodeValue();

        return literal(StringUtils.left(string, length));
    }
}
