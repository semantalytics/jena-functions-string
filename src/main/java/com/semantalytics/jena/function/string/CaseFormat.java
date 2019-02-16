package com.semantalytics.jena.function.string;

import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import static org.apache.jena.ext.com.google.common.base.CaseFormat.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class CaseFormat extends FunctionBase3 {

    public static final String name = StringVocabulary.caseFormat.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1, final NodeValue arg2) {

        final String caseFormatString = arg0.asString();
        final org.apache.jena.ext.com.google.common.base.CaseFormat caseFormatFrom = getFormatType(arg1.asString());
        final org.apache.jena.ext.com.google.common.base.CaseFormat caseFormatTo = getFormatType(arg2.asString());

        switch(caseFormatFrom) {
            case LOWER_CAMEL:
                return makeString(LOWER_CAMEL.to(caseFormatTo, caseFormatString));
            case UPPER_CAMEL:
                return makeString(UPPER_CAMEL.to(caseFormatTo, caseFormatString));
            case LOWER_HYPHEN:
                return makeString(LOWER_HYPHEN.to(caseFormatTo, caseFormatString));
            case LOWER_UNDERSCORE:
                return makeString(LOWER_UNDERSCORE.to(caseFormatTo, caseFormatString));
            case UPPER_UNDERSCORE:
                return makeString(UPPER_UNDERSCORE.to(caseFormatTo, caseFormatString));
            default:
                throw new ExprEvalException("Unknown format " + arg0.getString());
        }
    }

    public org.apache.jena.ext.com.google.common.base.CaseFormat getFormatType(final String formatString) throws ExprEvalException {

        switch(formatString) {
            case "toFormat":
                return LOWER_CAMEL;
            case "ToFormat":
                return UPPER_CAMEL;
            case "to-format":
                return LOWER_HYPHEN;
            case "to_format":
                return LOWER_UNDERSCORE;
            case "TO_FORMAT":
                return UPPER_UNDERSCORE;
            default:
                throw new ExprEvalException("Unrecognized format type " + formatString);
        }
    }
}
