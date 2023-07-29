package com.example.demo.Scheduling;

import java.util.*;

class Graph<T> {
    private Map<T, List<Edge<T>>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.put(vertex, new ArrayList<>());
    }

    public void addEdge(T source, T destination, double weight) {
        Edge<T> edge = new Edge<>(source, destination, weight);
        adjacencyList.get(source).add(edge);
    }

    public List<Edge<T>> getEdges(T vertex) {
        return adjacencyList.get(vertex);
    }

    public Set<T> getVertices() {
        return adjacencyList.keySet();
    }


}

