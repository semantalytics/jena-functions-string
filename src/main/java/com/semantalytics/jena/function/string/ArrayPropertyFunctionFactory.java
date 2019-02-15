package com.semantalytics.jena.function.string;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.jena.graph.Node;
import org.apache.jena.sparql.engine.ExecutionContext;
import org.apache.jena.sparql.engine.QueryIterator;
import org.apache.jena.sparql.engine.binding.Binding;
import org.apache.jena.sparql.pfunction.PFuncListAndSimple;
import org.apache.jena.sparql.pfunction.PropFuncArg;
import org.apache.jena.sparql.pfunction.PropertyFunction;
import org.apache.jena.sparql.pfunction.PropertyFunctionFactory;


/**
 * <p>An implementation of a property function that takes two arguments, a value and the number of times to repeat it
 * and produces optionally two outputs the value and optionally, the iteration counter</p>
 *
 * {@code
 *   (?result ?i) <string:array> "one\u001ftwo\u001fthree
 * }
 *
 */

public final class ArrayPropertyFunctionFactory implements PropertyFunctionFactory {

    private static final String name = StringVocabulary.array.stringValue();

    @Override
    public PropertyFunction create(final String url) {
        return new PFuncListAndSimple() {


            @Override
            public QueryIterator execEvaluated(Binding binding, PropFuncArg subject, Node predicate, Node object, ExecutionContext execCxt) {
                return null;
            }
        };
    }
}
