package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class ReplaceIgnoreCase extends FunctionBase {

    protected ReplaceIgnoreCase() {
        super(3, StringVocabulary.replaceIgnoreCase.stringValue());
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String searchString = assertStringLiteral(values[1]).stringValue();
        final String replacement = assertStringLiteral(values[2]).stringValue();

        return literal(StringUtils.replaceIgnoreCase(string, searchString, replacement));
    }
}
