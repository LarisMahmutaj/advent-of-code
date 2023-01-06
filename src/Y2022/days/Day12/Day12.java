package Y2022.days.Day12;

import interfaces.Day;
import utils.FileReaderUtil;
import utils.ParseUtil;

import java.awt.*;
import java.util.List;
import java.util.Queue;
import java.util.*;

public class Day12 implements Day {
    private final SortedMap<String, Node> nodeMap;
    private int gridLength;
    private int gridHeight;
    private Node source;
    private Node end;

    public Day12() {
        nodeMap = new TreeMap<>();
        createNodes();
        findSourceAndEnd();
    }

    @Override
    public void partOne() {
        Integer shortestPath = calculateShortestPath(this.source);
        System.out.println(shortestPath);
        System.out.println();
    }

    @Override
    public void partTwo() {
        List<Node> lowestLevelNodes = nodeMap.values().stream().filter(node -> node.getValue() == 'a').toList();
        Integer shortestPath = Integer.MAX_VALUE;

        for (Node node : lowestLevelNodes) {
            Integer path = calculateShortestPath(node);
            if (path != null && path < shortestPath) {
                shortestPath = path;
            }
        }
        System.out.println(shortestPath);
    }

    private Integer calculateShortestPath(Node start) {
        Queue<Node> queue = new LinkedList<>();
        Map<Node, Integer> distances = new HashMap<>();
        Node currentNode;

        queue.offer(start);
        distances.put(start, 0);


        while (queue.size() != 0) {
            currentNode = queue.poll();
            int distance = distances.get(currentNode);
            if (currentNode.equals(end)) {
                return distance;
            }
            List<Node> adjacentNodes = getEdges(currentNode);

            for (Node edge : adjacentNodes) {
                int addedDistance = distance + 1;
                if (addedDistance >= distances.getOrDefault(edge, Integer.MAX_VALUE)) {
                    continue;
                }
                queue.offer(edge);
                if (!distances.containsKey(edge)) {
                    distances.put(edge, addedDistance);
                } else {
                    distances.replace(edge, addedDistance);
                }
            }

        }
        return null;
    }

    private void findSourceAndEnd() {
        List<Node> nodeList = nodeMap.values().stream().toList();
        source = nodeList.stream().filter(l -> l.getValue() == 'S').findFirst().get();
        source.setValue('a');
        end = nodeList.stream().filter(l -> l.getValue() == 'E').findFirst().get();
        end.setValue('z');
    }

    private void createNodes() {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        gridHeight = lines.size();
        gridLength = lines.get(0).length();
        for (int i = 0; i < lines.size(); i++) {
            char[] characters = lines.get(i).toCharArray();
            for (int j = 0; j < characters.length; j++) {
                Node node = new Node(j, i, characters[j]);
                this.nodeMap.put(j + "," + i, node);
            }
        }
        System.out.println();
    }

    private List<Node> getEdges(Node node) {
        return new LinkedList<>(List.of(
                new Point(node.getX(), node.getY() + 1),
                new Point(node.getX(), node.getY() - 1),
                new Point(node.getX() + 1, node.getY()),
                new Point(node.getX() - 1, node.getY())
        )).stream()
                .filter(point -> point.getX() >= 0 && point.getX() < gridLength
                        && point.getY() >= 0 && point.getY() < gridHeight)
                .filter(point -> nodeMap.get((int) point.getX() + "," + (int) point.getY())
                        .getValue() <= node.getValue() + 1)
                .map(point -> new Node((int) point.getX(), (int) point.getY(),
                        nodeMap.get((int) point.getX() + "," + (int) point.getY()).getValue())).toList();
    }
}
