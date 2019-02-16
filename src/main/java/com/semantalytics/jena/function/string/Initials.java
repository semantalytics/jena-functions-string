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

import static org.apache.commons.text.WordUtils.*;
import static org.apache.jena.sparql.expr.NodeValue.*;

public final class Initials extends FunctionBase {

    public static final String name = StringVocabulary.initials.stringValue();

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(1, 2).contains(args.size()))
            throw new ExprEvalException(Lib.className(this)+": Wrong number of arguments: Wanted 1 or 2, got " + args.size()) ;


        final String string = args.get(0).asString();

        switch (args.size()) {
            case 1:
                return makeString(initials(string));
            case 2: {
                final String delimiters = args.get(1).asString();
                return makeString(initials(string, delimiters.toCharArray()));
            }
            default:
                throw new ExprEvalException("Incorrect number of parameters. Valid values are 1 or 2. Found " + args.size());
        }
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
    }
}
