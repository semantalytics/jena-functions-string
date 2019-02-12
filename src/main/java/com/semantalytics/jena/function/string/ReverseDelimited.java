package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.google.common.base.Preconditions.checkElementIndex;

public final class ReverseDelimited extends FunctionBase {

    protected ReverseDelimited() {
        super(2, StringVocabulary.reverseDelimited.stringValue());
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String separatorChar = assertStringLiteral(values[1]).stringValue();

        if (separatorChar.length() != 1) {
            throw new ExpressionEvaluationException("Second argument must be a single character. Found " + separatorChar.length());
        }

        return Values.literal(StringUtils.reverseDelimited(string, separatorChar.charAt(0)));
    }
}
