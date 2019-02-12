package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class ReplacePattern extends FunctionBase {

    protected ReplacePattern() {
        super(3, StringVocabulary.replacePattern.stringValue());
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String regex = assertStringLiteral(values[1]).stringValue();
        final String replacement = assertStringLiteral(values[2]).stringValue();

        return Values.literal(StringUtils.replacePattern(string, regex, replacement));
    }
}
