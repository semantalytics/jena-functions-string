package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import static org.apache.commons.lang3.StringUtils.isNumeric;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class IsNumeric extends FunctionBase1 {


    public static final String name = StringVocabulary.isNumeric.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0) {
        return makeBoolean(isNumeric(arg0.asString()));
    }
}
