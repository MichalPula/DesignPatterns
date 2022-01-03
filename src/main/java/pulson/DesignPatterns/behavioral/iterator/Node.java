package pulson.DesignPatterns.behavioral.iterator;

import lombok.AllArgsConstructor;

import java.util.Iterator;

@AllArgsConstructor
public class Node<T> {
    public T value;
    public Node<T> left, right, parent;

    public Node(T value, Node<T> left, Node<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
        left.parent = right.parent = this;
    }
}

class InOrderIterator<T> implements Iterator<T> {
    private Node<T> current, root;
    private boolean yieldedStart;

    public InOrderIterator(Node<T> root) {
        this.root = current = root;
        while (current.left != null) {
            current = current.left;
        }
    }
    private boolean hasRightmostParent(Node<T> node) {
        return true;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public T next() {
        return null;
    }
}
