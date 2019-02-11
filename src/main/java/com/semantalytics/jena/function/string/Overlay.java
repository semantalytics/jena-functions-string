package com.semantalytics.jena.function.string;

import com.complexible.common.rdf.model.Values;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.string.StringFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Value;

public final class Overlay extends AbstractFunction implements StringFunction {

    protected Overlay() {
        super(4, StringVocabulary.overlay.stringValue());
    }

    private Overlay(final Overlay overlay) {
        super(overlay);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {
      
      final String string = assertStringLiteral(values[0]).stringValue();
      final String overlay = assertStringLiteral(values[1]).stringValue();
      final int start = assertIntegerLiteral(values[2]).intValue();
      final int end = assertIntegerLiteral(values[3]).intValue();

      return Values.literal(StringUtils.overlay(string, overlay, start, end));
    }

    @Override
    public Overlay copy() {
        return new Overlay(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.overlay.name();
    }
}
