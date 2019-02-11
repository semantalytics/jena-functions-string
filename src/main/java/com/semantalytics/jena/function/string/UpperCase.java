package com.semantalytics.jena.function.string;

import com.complexible.stardog.plan.filter.EvalUtil;
import com.complexible.stardog.plan.filter.ExpressionEvaluationException;
import com.complexible.stardog.plan.filter.ExpressionVisitor;
import com.complexible.stardog.plan.filter.functions.AbstractFunction;
import com.complexible.stardog.plan.filter.functions.UserDefinedFunction;
import org.apache.commons.lang3.StringUtils;
import org.openrdf.model.Literal;
import org.openrdf.model.Value;

import java.util.Locale;

import static com.complexible.common.rdf.model.Values.literal;

public class UpperCase extends AbstractFunction implements UserDefinedFunction {

    public UpperCase() {
        super(1, StringVocabulary.upperCase.stringValue());
    }

    public UpperCase(final UpperCase upperCase) {
        super(upperCase);
    }

    @Override
    protected Value internalEvaluate(final Value... values) throws ExpressionEvaluationException {

        final Literal string = assertLiteral(values[0]);

        if(EvalUtil.isStringLiteral(string)) {
                if(string.getLanguage().isPresent()) {
                    return literal(StringUtils.upperCase(string.stringValue(), Locale.forLanguageTag(string.getLanguage().get())));
                } else {
                    return literal(StringUtils.upperCase(string.stringValue()));
                }
        } else {
            throw new ExpressionEvaluationException("Invalid argument to " + this.getName() + " argument MUST be a literal value, was: " + values[0]);
        }
    }

    @Override
    public UpperCase copy() {
        return new UpperCase(this);
    }

    @Override
    public void accept(final ExpressionVisitor expressionVisitor) {
        expressionVisitor.visit(this);
    }

    @Override
    public String toString() {
        return StringVocabulary.upperCase.name();
    }
}
