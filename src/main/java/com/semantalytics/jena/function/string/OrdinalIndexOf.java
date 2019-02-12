package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import static com.complexible.common.rdf.model.NodeValues.*;

public final class OrdinalIndexOf extends FunctionBase {

    protected OrdinalIndexOf() {
        super(3, StringVocabulary.ordinalIndexOf.stringNodeValue());
    }

    private OrdinalIndexOf(final OrdinalIndexOf ordinalIndexOf) {
        super(ordinalIndexOf);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final String searchStr = assertStringLiteral(values[1]).stringNodeValue();
        final int ordinal = assertNumericLiteral(values[2]).intNodeValue();

        return literal(StringUtils.ordinalIndexOf(string, searchStr, ordinal));
    }
}
