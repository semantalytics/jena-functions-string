package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

public final class IsNumericSpace extends FunctionBase1 {

    public static final String name = StringVocabulary.isNumericSpace.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0) {
        return NodeValue.makeBoolean(StringUtils.isNumericSpace(arg0.asString()));
    }
}
