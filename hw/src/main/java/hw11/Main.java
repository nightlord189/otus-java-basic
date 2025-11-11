package hw11;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, this is HW11");

        demo(List.of(1, 2, 3, 4, 5), 1, 3, 4, 7);

        demo(List.of(1, 2, 3, 4, 5, 6, 7, 8), 6, 9);

        demo(List.of(1), 1, 3);

        demo(List.of(1, 2), 1, 2, 3);

        demo(List.of(), 1);
    }

    private static void demo(List<Integer> list, int... numbersToSearch) {
        System.out.println("List: " + list);
        BinarySearchTree<Integer> tree = new BinarySearchTree<>(list);
        for (int val : numbersToSearch) {
            System.out.println("Search of element " + val + ": " + tree.find(val));
        }
    }
}
