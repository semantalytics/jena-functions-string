package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.NodeValues;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

public final class IsEmpty extends FunctionBase {

    protected IsEmpty() {
        super(1, StringVocabulary.isEmpty.stringNodeValue());
    }

    private IsEmpty(final IsEmpty isEmpty) {
        super(isEmpty);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();

        return NodeValues.literal(StringUtils.isEmpty(string));
    }
}
