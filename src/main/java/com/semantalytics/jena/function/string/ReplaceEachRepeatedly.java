package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class ReplaceEachRepeatedly extends FunctionBase {

    protected ReplaceEachRepeatedly() {
        super(3, StringVocabulary.replaceEachRepeatedly.stringValue());
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        for (final Value value : values) {
            assertStringLiteral(value);
        }

        final String string = assertStringLiteral(values[0]).stringValue();
        final String[] searchList = assertStringLiteral(values[1]).stringValue().split("\u001f");
        final String[] replacementList = assertStringLiteral(values[2]).stringValue().split("\u001f");

        return Values.literal(StringUtils.replaceEachRepeatedly(string, searchList, replacementList));
    }
}
