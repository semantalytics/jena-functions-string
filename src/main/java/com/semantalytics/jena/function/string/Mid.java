package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.NodeValues;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

public final class Mid extends FunctionBase {

    protected Mid() {
        super(3, StringVocabulary.mid.stringNodeValue());
    }

    private Mid(final Mid mid) {
        super(mid);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final int position = assertIntegerLiteral(values[1]).intNodeValue();
        final int length = assertIntegerLiteral(values[2]).intNodeValue();

        return NodeValues.literal(StringUtils.mid(string, position, length));
    }
}
