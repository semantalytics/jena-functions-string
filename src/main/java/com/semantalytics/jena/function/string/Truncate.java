package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class Truncate extends FunctionBase2 {

    public static final String name = StringVocabulary.truncate.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {

        final String string = arg0.asString();
        final int maxWidth = arg1.getInteger().intValue();

        return makeString(truncate(string, maxWidth));
    }
}
