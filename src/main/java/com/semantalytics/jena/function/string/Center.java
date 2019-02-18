package com.semantalytics.jena.function.string;

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

public final class Center extends FunctionBase {

    public static final String name = StringVocabulary.center.stringValue();

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this) + ": Wrong number of arguments: Wanted between 2 and 3, got " + args.size()) ;

        final String string = args.get(0).asString();
        final int size = args.get(1).getInteger().intValue();

        final NodeValue result;

        switch(args.size()) {
            case 2:
                result = makeString(center(string, size));
                break;
            case 3:
                char padChar = args.get(2).asString().charAt(0);
                result = makeString(center(string, size, padChar));
                break;
            default:
                throw new ExprEvalException("Function requires 2 or 3 arguments. Found " + args.size());
        }
        return result;
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes between 2 and 3 arguments");
        }
    }
}
