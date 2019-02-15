package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class ReplaceEach extends FunctionBase3 {

    public static final String name = StringVocabulary.replaceEach.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1, final NodeValue arg2) {

        final String string = arg0.asString();
        final String[] searchList = arg1.asString().split("\u001f");
        final String[] replacementList = arg2.asString().split("\u001f");

        if (searchList.length != searchList.length) {
            throw new ExprEvalException("Invalid argument to " + this.name + " argument 2 and 3 must an equal number of ArrayFunction elements, was: " + searchList.length + " and " + replacementList.length);
        }

        return makeString(replaceEach(string, searchList, replacementList));
    }
}
