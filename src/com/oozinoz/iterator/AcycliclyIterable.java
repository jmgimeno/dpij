package com.oozinoz.iterator;

import java.util.HashSet;
import java.util.Set;

public interface AcycliclyIterable<E> extends Iterable<E> {
    default ComponentIterator<E> iterator() {
        return iterator(new HashSet<>());
    }

    ComponentIterator<E> iterator(Set<E> visited);
}
