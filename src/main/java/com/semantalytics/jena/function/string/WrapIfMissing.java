package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.google.common.base.Preconditions.checkElementIndex;

public final class WrapIfMissing extends FunctionBase {

    protected WrapIfMissing() {
        super(2, StringVocabulary.wrapIfMissing.stringValue());
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String wrapWith = assertStringLiteral(values[1]).stringValue();

        if (wrapWith.length() != 1) {
            throw new ExpressionEvaluationException("Expecting a single character for second argument. Found '" + wrapWith + "'");
        }

        return Values.literal(StringUtils.wrapIfMissing(string, wrapWith.charAt(0)));
    }
}
