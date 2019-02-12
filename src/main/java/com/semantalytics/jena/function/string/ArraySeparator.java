package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase0;

public final class ArraySeparator extends FunctionBase0 {

    public static final String name = StringVocabulary.arraySeparator.stringValue();

    @Override
    public NodeValue exec() {
        return NodeValue.makeString("\u001f");
    }

}
