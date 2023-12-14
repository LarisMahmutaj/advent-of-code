package Y2022.days.Day08;

import interfaces.Day;
import utils.FileReaderUtil;
import utils.ParseUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Day08 implements Day {
    List<String> lines;
    SortedMap<String, Tree> trees;
    List<Tree> visibleTrees;
    int forestLength;
    int forestHeight;

    public Day08() {
        this.lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        this.trees = new TreeMap<>();
        this.visibleTrees = new ArrayList<>();
        this.forestLength = lines.get(0).length();
        this.forestHeight = lines.size();
        createTrees();
    }

    @Override
    public void partOne() {


        for (Tree tree : trees.values()) {
            if (isVisible(tree)) {
                visibleTrees.add(tree);
            }
        }

        System.out.println(visibleTrees.size());
    }

    @Override
    public void partTwo() {
        int highestScore = 0;
        for (Tree tree : trees.values()) {
            int score = getTreeScore(tree);
            if (score > highestScore) {
                highestScore = score;
            }
        }

        System.out.println(highestScore);
    }

    private int getTreeScore(Tree tree) {
        int x = tree.x();
        int y = tree.y();

        int leftCount = 0;
        int rightCount = 0;
        int upCount = 0;
        int downCount = 0;

        // X Axis Left Side
        for (int i = x - 1; i >= 0; i--) {
            Tree currentTree = trees.get(i + "," + y);
            if (currentTree.value() <= tree.value()) {
                leftCount++;
            }
            if (currentTree.value() == tree.value()) {
                break;
            }
        }

        // X Axis Right Aide
        for (int i = x + 1; i < forestLength; i++) {
            Tree currentTree = trees.get(i + "," + y);
            if (currentTree.value() <= tree.value()) {
                rightCount++;
            }
            if (currentTree.value() >= tree.value()) {
                rightCount++;
                break;
            }
        }

        // Y Axis Up Side
        for (int i = y - 1; i >= 0; i--) {
            Tree currentTree = trees.get(x + "," + i);
            if (currentTree.value() <= tree.value()) {
                upCount++;
            }
            if (currentTree.value() == tree.value()) {
                break;
            }
        }

        for (int i = y + 1; i < forestHeight; i++) {
            Tree currentTree = trees.get(x + "," + i);
            if (currentTree.value() <= tree.value()) {
                downCount++;
            }
            if (currentTree.value() >= tree.value()) {
                downCount++;
                break;
            }
        }

        return leftCount * rightCount * upCount * downCount;
    }

    private boolean isVisible(Tree tree) {
        int x = tree.x();
        int y = tree.y();

        if (x == 0 || x == forestLength - 1 || y == 0 || y == forestHeight - 1) {
            return true;
        }

        boolean leftHidden = false;
        boolean rightHidden = false;
        boolean upHidden = false;
        boolean downHidden = false;

        // Check X axis
        for (int i = 0; i < forestLength; i++) {
            Tree currTree = trees.get(i + "," + y);
            if (tree.equals(currTree)) continue;

            if (i < x && !leftHidden) {
                leftHidden = currTree.value() >= tree.value();
            } else if (i > x && !rightHidden) {
                rightHidden = currTree.value() >= tree.value();
            }

        }

        // Check Y axis
        for (int i = 0; i < forestHeight; i++) {
            Tree currTree = trees.get(x + "," + i);
            if (tree.equals(currTree)) continue;

            if (i < y && !upHidden) {
                upHidden = currTree.value() >= tree.value();
            } else if (i > y && !downHidden) {
                downHidden = currTree.value() >= tree.value();
            }
        }
        return !leftHidden || !rightHidden || !upHidden || !downHidden;
    }

    private void createTrees() {
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] stringNumbers = line.split("");

            for (int j = 0; j < stringNumbers.length; j++) {
                trees.put((j + "," + i), new Tree(j, i, Integer.parseInt(stringNumbers[j])));
            }
        }
    }

}
