package org.aburavov.otus.java.basic.hw.hw11;

import java.util.List;

public interface SearchTree<T extends Comparable<T>> {

    /**
     * @param element element to find
     * @return element if exists, otherwise - null
     */
    T find(T element);

    /**
     * @return elements in sorted order (in-order traversal)
     */
    List<T> getSortedList();
}