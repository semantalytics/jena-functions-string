package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class Mid extends AbstractFunction implements StringFunction {

    protected Mid() {
        super(3, StringVocabulary.mid.stringValue());
    }

    private Mid(final Mid mid) {
        super(mid);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final int position = assertIntegerLiteral(values[1]).intValue();
      final int length = assertIntegerLiteral(values[2]).intValue();

      return Values.literal(StringUtils.mid(string, position, length));
    }

    @Override
    public Mid copy() {
        return new Mid(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.mid.name();
    }
}
