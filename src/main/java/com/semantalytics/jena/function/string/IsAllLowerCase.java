package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.apache.jena.sparql.function.FunctionBase1;
import org.openrdf.model.NodeValue;

import static com.complexible.common.rdf.model.NodeValues.literal;

public final class IsAllLowerCase extends FunctionBase1 {

    protected IsAllLowerCase() {
        super(1, StringVocabulary.isAllLowerCase.stringNodeValue());
    }

    private IsAllLowerCase(final IsAllLowerCase isAllLowerCase) {
        super(isAllLowerCase);
    }

    @Override
    protected NodeValue internalEvaluate(final NodeValue... values) throws ExpressionEvaluationException {

        final String string = assertStringLiteral(values[0]).stringNodeValue();

        return literal(StringUtils.isAllLowerCase(string));
    }
}
