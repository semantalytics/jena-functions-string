package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class DefaultIfBlank extends FunctionBase2 {

    public static final String name = StringVocabulary.defaultIfBlank.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {

        final String string = arg0.asString();
        final String defaultString = arg0.asString();

        return makeString(defaultIfBlank(string, defaultString));
    }
}
