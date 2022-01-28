package ru.job4j.concurrent;

public final class Node<T> {

    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return new Node<>(next, value);
    }

    public T getValue() {
        return new Node<>(next, value).value;
    }

    @Override
    public String toString() {
        return String.format("Node: {next node: %s, value: %s}", next, value);
    }

}
