package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;
import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class ReverseDelimited extends FunctionBase2 {

    public static final String name = StringVocabulary.reverseDelimited.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {

        final String string = arg0.asString();
        final String separatorChar = arg1.asString();

        if (separatorChar.length() != 1) {
            throw new ExprEvalException("Second argument must be a single character. Found " + separatorChar.length());
        }

        return makeString(reverseDelimited(string, separatorChar.charAt(0)));
    }
}
