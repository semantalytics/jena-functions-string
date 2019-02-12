package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class ReplaceEach extends FunctionBase {

    protected ReplaceEach() {
        super(3, StringVocabulary.replaceEach.stringValue());
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();
        final String[] searchList = assertStringLiteral(values[1]).stringValue().split("\u001f");
        final String[] replacementList = assertStringLiteral(values[2]).stringValue().split("\u001f");

        if (searchList.length != searchList.length) {
            throw new ExpressionEvaluationException("Invalid argument to " + this.getName() + " argument 2 and 3 must an equal number of ArrayFunction elements, was: " + searchList.length + " and " + replacementList.length);
        }

        return Values.literal(StringUtils.replaceEach(string, searchList, replacementList));
    }
}
