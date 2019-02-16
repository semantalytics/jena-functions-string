package com.semantalytics.jena.function.string;

import org.apache.jena.atlas.lib.Lib;
import org.apache.jena.ext.com.google.common.base.Joiner;
import org.apache.jena.ext.com.google.common.collect.Range;
import org.apache.jena.query.QueryBuildException;
import org.apache.jena.sparql.ARQInternalErrorException;
import org.apache.jena.sparql.expr.ExprEvalException;
import org.apache.jena.sparql.expr.ExprList;
import org.apache.jena.sparql.expr.NodeValue;
import org.apache.jena.sparql.function.FunctionBase;

import java.util.Arrays;
import java.util.List;

import static org.apache.jena.sparql.expr.NodeValue.*;

public final class IndexOfArray extends FunctionBase {

    public static final String name = StringVocabulary.arrayIndex.stringValue();

    @Override
    public NodeValue exec(final List<NodeValue> args) {

        if ( args == null )
            // The contract on the function interface is that this should not happen.
            throw new ARQInternalErrorException(Lib.className(this) + ": Null args list") ;

        if (!Range.closed(2, 3).contains(args.size()))
            throw new ExprEvalException(Lib.className(this) + ": Wrong number of arguments: Wanted 3, got "+args.size()) ;

        final String[] stringArray = args.get(0).asString().split("\u001f");

        switch (args.size()) {
            case 2: {
                final int index = args.get(1).getInteger().intValue();

                if (index >= stringArray.length) {
                    throw new ExprEvalException("Index " + index + " out of bound. ArrayFunction length " + stringArray.length);
                }

                return makeString(stringArray[index]);

            }
            case 3: {

                final int startIndex = args.get(1).getInteger().intValue();
                final int endIndex = args.get(2).getInteger().intValue();

                if (startIndex >= stringArray.length) {
                    throw new ExprEvalException("Index " + startIndex + " out of bound. ArrayFunction length " + stringArray.length);
                }
                if (endIndex >= stringArray.length) {
                    throw new ExprEvalException("Index " + endIndex + " out of bound. ArrayFunction length " + stringArray.length);
                }
                if (endIndex < startIndex) {
                    throw new ExprEvalException("Start index must be smaller or equal to end index");
                }

                return NodeValue.makeString(Joiner.on("\u001f").join(Arrays.copyOfRange(stringArray, startIndex, endIndex)));
            }
            default: {
                throw new ExprEvalException("Function takes either 2 or three arguments. Found " + args.size());
            }

        }
    }

    @Override
    public void checkBuild(String uri, ExprList args) {
        if(!Range.closed(2, 3).contains(args.size())) {
            throw new QueryBuildException("Function '" + Lib.className(this) + "' takes two or three arguments") ;
        }
    }
}
