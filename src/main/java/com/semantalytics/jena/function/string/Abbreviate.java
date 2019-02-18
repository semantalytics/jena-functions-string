package com.semantalytics.jena.function.string;

import org.apache.commons.lang3.StringUtils;
import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.ext.com.google.common.collect.Range;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.ARQInternalErrorException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class Abbreviate extends FunctionBase {

    public static final String name = StringVocabulary.abbreviate.stringValue();

    @Override
    public NodeValue exec(List<NodeValue> args)  {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this) + ": Wrong number of arguments: Wanted 2 or 3, got " + args.size()) ;

        if(!args.get(0).isString())
            throw new ExprEvalException(Lib.className(this) + " first argument must be a string literal");

        if(!args.get(1).isInteger())
            throw new ExprEvalException(Lib.className(this) + " first argument must be a integer literal");

        if(args.size() == 3 && !args.get(2).isInteger())
            throw new ExprEvalException(Lib.className(this) + " third argument must be a integer literal");

        final String string = args.get(0).getString();
        final int maxWidth = args.get(1).getInteger().intValue();

        if(maxWidth <= 3) {
            throw new ExprEvalException("maxWidth must be greater than 3. Found " + maxWidth);
        }

        final NodeValue result;

        switch(args.size()) {
            case 2:
                result =  makeString(abbreviate(string, maxWidth));
                break;
            case 3: {
                final int offset = args.get(2).getInteger().intValue();
                result =  makeString(abbreviate(string, offset, maxWidth));
                break;
            }
            default:
                // The contract on the function interface is that this should not happen.
                throw new ARQInternalErrorException(Lib.className(this) +
                        "Incorrect number of parameters. Valid values are 1 or 2. Found " + args.size());
        }

        return result;
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
        if(args.get(0).isConstant() && !args.get(0).getConstant().isString()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' first argument must be a string literal") ;
        }
        if(args.get(1).isConstant() && !args.get(1).getConstant().isInteger()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' second argument must be a integer literal") ;
        }
        if(args.size() == 3 && args.get(2).isConstant() && !args.get(2).getConstant().isInteger()) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' third argument must be a integer literal") ;
        }
    }
}
