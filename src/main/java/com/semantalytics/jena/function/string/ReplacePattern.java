package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class ReplacePattern extends FunctionBase3 {

    public static final String name = StringVocabulary.replacePattern.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1, final NodeValue arg2) {

        final String string = arg0.asString();
        final String regex = arg1.asString();
        final String replacement = arg2.asString();

        return makeString(replacePattern(string, regex, replacement));
    }
}
