package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.NodeValue;

import static com.complexible.common.rdf.model.NodeValues.*;

public final class LastOrdinalIndexOf extends FunctionBase {

    protected LastOrdinalIndexOf() {
        super(3, StringVocabulary.lastOrdinalIndexOf.stringNodeValue());
    }

    private LastOrdinalIndexOf(final LastOrdinalIndexOf lastOrdinalIndexOf) {
        super(lastOrdinalIndexOf);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();
        final String searchString = assertStringLiteral(values[1]).stringNodeValue();
        final int ordinal = assertNumericLiteral(values[2]).intNodeValue();

        return literal(StringUtils.lastOrdinalIndexOf(string, searchString, ordinal));
    }
}
