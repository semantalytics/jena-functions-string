package com.semantalytics.jena.function.string;

import com.google.common.base.Joiner;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase1;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class SplitByCharacterTypeCamelCase extends FunctionBase1 {

    public static final String name = StringVocabulary.splitByCharacterTypeCamelCase.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0) {
        return makeString(Joiner.on("\u001f").join(splitByCharacterTypeCamelCase(arg0.asString())));
    }
}
