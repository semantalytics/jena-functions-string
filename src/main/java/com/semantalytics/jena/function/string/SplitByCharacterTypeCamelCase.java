package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import com.google.common.base.Joiner;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class SplitByCharacterTypeCamelCase extends FunctionBase {

    protected SplitByCharacterTypeCamelCase() {
        super(1, StringVocabulary.splitByCharacterTypeCamelCase.stringValue());
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringValue();

        return Values.literal(Joiner.on("\u001f").join(StringUtils.splitByCharacterTypeCamelCase(string)));
    }
}
