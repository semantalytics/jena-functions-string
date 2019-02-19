package com.semantalytics.jena.function.string;

import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase3;
import static org.apache.jena.ext.com.google.common.base.CaseFormat.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class CaseFormat extends FunctionBase3 {

    public static final String name = StringVocabulary.caseFormat.stringValue();

    @Override
    public NodeValue exec(final NodeValue arg0, final NodeValue arg1, final NodeValue arg2) {

        if(!arg0.isString())
            throw new ExprEvalException(Lib.className(this) + " first argument must be a string literal");
        if(!arg1.isString())
            throw new ExprEvalException(Lib.className(this) + " second argument must be a string literal");
        if(!arg2.isString())
            throw new ExprEvalException(Lib.className(this) + " third argument must be a string literal");

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
            case "fromFormat":
                return LOWER_CAMEL;
            case "FromFormat":
                return UPPER_CAMEL;
            case "from-format":
                return LOWER_HYPHEN;
            case "from_format":
                return LOWER_UNDERSCORE;
            case "FROM_FORMAT":
                return UPPER_UNDERSCORE;
            default:
                throw new ExprEvalException("Unrecognized format type " + formatString);
        }
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(args.size() != 3) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes three arguments") ;
        }
        if(args.get(0).isConstant() && !args.get(0).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' first argument must be a string literal") ;
        }
        if(args.get(1).isConstant() && !args.get(1).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' second argument must be a string literal") ;
        }
        if(args.get(2).isConstant() && !args.get(2).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' third argument must be a string literal") ;
        }
    }
}
