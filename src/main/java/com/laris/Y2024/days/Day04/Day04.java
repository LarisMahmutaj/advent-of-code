package com.laris.Y2024.days.Day04;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;
import com.laris.utils.ParseUtil;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Day04 implements Day {
    @Override
    public void partOne() {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        List<Point> characters = new ArrayList<>();

        for (int i = 0; i < lines.get(0).length(); i++) {
            String currentLine = lines.get(i);
            for (int j = 0; j < lines.size(); j++) {
                char letter = currentLine.charAt(j);
                characters.add(new Point(i, j));
            }
        }

        System.out.println();
    }

    @Override
    public void partTwo() {

    }
}
