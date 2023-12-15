package com.laris.Y2022.days.Day07;

import java.util.ArrayList;
import java.util.List;

public class Node {
    private Node parent;
    private int size;
    private String name;
    private List<Node> children = new ArrayList<>();

    public Node(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getChildren() {
        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void addChild(Node node) {
        this.children.add(node);
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}
