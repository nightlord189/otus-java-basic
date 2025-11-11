package hw19;

import java.util.ArrayList;
import java.util.List;

public class Box<T extends Fruit> {
    private final List<T> content;

    public Box() {
        content = new ArrayList<>();
    }

    public void add(T item) {
        content.add(item);
    }

    public T take() {
        if (content.isEmpty()) {
            return null;
        }
        return content.removeFirst();
    }

    public int weight() {
        int result = 0;
        for (T elem : content) {
            result += elem.getWeight();
        }
        return result;
    }

    public boolean compare(Box<? extends Fruit> anotherBox) {
        return this.weight() == anotherBox.weight();
    }

    public void moveToAnotherBox(Box<T> anotherBox) {
        if (this == anotherBox) {
            return;
        }
        while (!content.isEmpty()) {
            anotherBox.add(this.take());
        }
    }
}
