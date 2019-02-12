package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

public final class Capitalize extends FunctionBase1 {

    public static final String name = StringVocabulary.capitalize.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg1) {
      
      final String string = arg1.getString();

      return NodeValue.makeString(StringUtils.capitalize(string));
    }
}
