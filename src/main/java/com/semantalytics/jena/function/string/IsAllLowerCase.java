package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

import static com.complexible.common.rdf.model.Values.literal;

public final class IsAllLowerCase extends AbstractFunction implements StringFunction {

    protected IsAllLowerCase() {
        super(1, StringVocabulary.isAllLowerCase.stringValue());
    }

    private IsAllLowerCase(final IsAllLowerCase isAllLowerCase) {
        super(isAllLowerCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();

      return literal(StringUtils.isAllLowerCase(string));
    }

    @Override
    public IsAllLowerCase copy() {
        return new IsAllLowerCase(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.isAllLowerCase.name();
    }
}
