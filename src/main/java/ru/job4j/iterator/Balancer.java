package ru.job4j.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Balancer {
    public static void split(List<ArrayList<Integer>> nodes, Iterator<Integer> source) {
        int head = 0;
        while (source.hasNext()) {
            if (head == nodes.size()) {
                head = 0;
            }
            nodes.get(head++).add(source.next());
        }
    }
}
