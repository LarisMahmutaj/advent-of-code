package com.laris.Y2022.days.Day07;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;
import com.laris.utils.ParseUtil;

import java.util.ArrayList;
import java.util.List;

public class Day07 implements Day {

    int partOneMaxSize = 100000;
    int requiredSizeForUpdate = 30000000;
    int diskTotalSize = 70000000;
    final Tree tree = new Tree();
    List<Node> qualifiedDirs = new ArrayList<>();
    List<Node> allDirs = new ArrayList<>();

    public Day07() {
        buildTree();
        iterateDirs(this.tree.root);
    }

    @Override
    public void partOne() {
        int qualifiedDirsSize = 0;
        for (Node node : qualifiedDirs) {
            qualifiedDirsSize += node.getSize();
        }

        System.out.println(qualifiedDirsSize);
    }

    @Override
    public void partTwo() {

        Node toDelete = null;
        int spaceToBeFreed = requiredSizeForUpdate - (diskTotalSize - this.tree.root.getSize());

        for (Node dir : allDirs) {
            if (dir.getSize() > spaceToBeFreed) {
                if (toDelete == null) {
                    toDelete = dir;
                } else if (dir.getSize() < toDelete.getSize()) {
                    toDelete = dir;
                }
            }
        }
        System.out.println(toDelete.getSize());
    }

    private int iterateDirs(Node baseNode) {
        int size = 0;
        for (Node node : baseNode.getChildren()) {
            if (node.getChildren().size() > 0) {
                size += iterateDirs(node);
            } else {
                size += node.getSize();
            }
        }
        baseNode.setSize(size);

        if (baseNode.getChildren().size() > 0) {
            allDirs.add(baseNode);
        }

        if (size < partOneMaxSize) {
            qualifiedDirs.add(baseNode);
        }
        return size;
    }

    private void buildTree() {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));

        for (String line : lines) {
            String[] lineParts = line.split(" ");

            if (lineParts[0].equals("$")) {
                String command = lineParts[1];

                if (command.equals("cd")) {
                    String name = lineParts[2];
                    if (name.equals("..")) {
                        tree.stepOut();
                    } else {
                        if (name.equals("/")) {
                            continue;
                        }
                        tree.stepIn(name);
                    }
                }
            } else {
                String name = lineParts[1];

                Node node = new Node(name);
                node.setParent(tree.currentNode);

                if (!lineParts[0].equals("dir")) {
                    int size = Integer.parseInt(lineParts[0]);
                    node.setSize(size);
                }

                this.tree.insertNode(node);
            }
        }
    }
}
