package com.example.demo.Scheduling;

public class Edge<T> {
    private T source;
    private T destination;
    private double weight;

    public Edge(T source, T destination, double weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public T getSource() {
        return source;
    }

    public T getDestination() {
        return destination;
    }

    public double getWeight() {
        return weight;
    }
}
