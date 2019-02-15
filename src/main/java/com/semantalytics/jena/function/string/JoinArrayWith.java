package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

public final class JoinArrayWith extends FunctionBase2 {

    public static final String name = StringVocabulary.joinArrayWith.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {


        final String separator = arg0.asString();
        final String[] pieces = arg1.asString().split("\u001f");

        return NodeValue.makeInteger(StringUtils.joinWith(separator, pieces));
    }
}
