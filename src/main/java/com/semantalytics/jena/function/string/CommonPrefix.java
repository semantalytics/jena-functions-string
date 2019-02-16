package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase2;

import static org.apache.jena.ext.com.google.common.base.Strings.commonPrefix;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class CommonPrefix extends FunctionBase2 {

    public static final String name = StringVocabulary.commonPrefix.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1) {
      
      final String firstString = arg0.asString();
      final String secondString = arg1.asString();
      
      return makeString(commonPrefix(firstString, secondString));
    }
}
