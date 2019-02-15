package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static org.apache.jena.ext.com.google.common.base.Strings.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class Repeat extends FunctionBase2 {

    public static final String name = StringVocabulary.repeat.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {

        final String string = arg0.asString();
        final int count = arg1.getInteger().intValue();

        return makeString(repeat(string, count));
    }
}
