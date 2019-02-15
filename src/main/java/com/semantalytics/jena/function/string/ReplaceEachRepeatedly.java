package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class ReplaceEachRepeatedly extends FunctionBase3 {

    public static final String name = StringVocabulary.replaceEachRepeatedly.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1, final NodeValue arg2) {

        final String string = arg0.asString();
        final String[] searchList = arg1.asString().split("\u001f");
        final String[] replacementList = arg2.asString().split("\u001f");

        return makeString(replaceEachRepeatedly(string, searchList, replacementList));
    }
}
