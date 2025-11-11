package org.aburavov.otus.java.basic.hw.hw11;

import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> implements SearchTree<T> {
    private class Node {
        public T value;
        public Node left;
        public Node right;

        public Node(T value) {
            this.value = value;
        }
    }

    private final List<T> sortedList;
    private final Node root;

    @Override
    public List<T> getSortedList() {
        return sortedList;
    }

    public BinarySearchTree(List<T> sortedList) {
        this.sortedList = sortedList;
        root = buildTree(sortedList);
    }

    private Node buildTree(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }

        int midIndex = list.size() / 2;

        Node result = new Node(list.get(midIndex));
        result.left = buildTree(list.subList(0, midIndex));
        result.right = buildTree(list.subList(midIndex + 1, list.size()));

        return result;
    }

    @Override
    public T find(T element) {
        Node currentNode = root;
        while (currentNode != null) {
            if (currentNode.value == element) {
                return currentNode.value;
            } else if (currentNode.value.compareTo(element) > 0) {
                currentNode = currentNode.left;
            } else {
                currentNode = currentNode.right;
            }
        }
        return null;
    }
}
