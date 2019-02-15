package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;
import static org.apache.commons.lang3.StringUtils.*;

public final class IsAlphaSpace extends FunctionBase1 {

    public static final String name = StringVocabulary.isAlphaSpace.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0) {

        return NodeValue.makeBoolean(isAlphaSpace(arg0.asString()));
    }
}
