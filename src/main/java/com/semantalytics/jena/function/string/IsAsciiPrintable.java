package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import static com.complexible.common.rdf.model.NodeValues.*;

public final class IsAsciiPrintable extends FunctionBase {

    protected IsAsciiPrintable() {
        super(1, StringVocabulary.isAsciiPrintable.stringNodeValue());
    }

    private IsAsciiPrintable(final IsAsciiPrintable isAsciiPrintable) {
        super(isAsciiPrintable);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();

        return literal(StringUtils.isAsciiPrintable(string));
    }
}
