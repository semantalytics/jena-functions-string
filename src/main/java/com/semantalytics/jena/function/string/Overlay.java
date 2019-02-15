package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase4;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class Overlay extends FunctionBase4 {

    public static final String name = StringVocabulary.overlay.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1, final NodeValue arg2, final NodeValue arg3) {

        final String string = arg0.asString();
        final String overlay = arg1.asString();
        final int start = arg2.getInteger().intValue();
        final int end = arg3.getInteger().intValue();

        return makeString(overlay(string, overlay, start, end));
    }
}
