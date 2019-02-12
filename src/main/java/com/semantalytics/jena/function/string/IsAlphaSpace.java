package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import static com.complexible.common.rdf.model.NodeValues.*;

public final class IsAlphaSpace extends FunctionBase {

    protected IsAlphaSpace() {
        super(1, StringVocabulary.isAlphaSpace.stringNodeValue());
    }

    private IsAlphaSpace(final IsAlphaSpace isUpperCase) {
        super(isUpperCase);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();

        return literal(StringUtils.isAlphaSpace(string));
    }
}
