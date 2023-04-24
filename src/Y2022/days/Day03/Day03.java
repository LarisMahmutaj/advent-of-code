package Y2022.days.Day03;

import interfaces.Day;
import utils.FileReaderUtil;
import utils.ParseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day03 implements Day {
    private final String lowercase = "abcdefghijklmnopqrstuvwxyz";
    private final String uppercase = lowercase.toUpperCase();

    @Override
    public void partOne() {
        List<String> items = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        int total = 0;
        for (String item : items) {
            String partOne = item.substring(0, item.length() / 2);
            String partTwo = item.substring(item.length() / 2);

            List<String> commonChars = new ArrayList<>();

            for (String character : partOne.split("")) {
                if (partTwo.contains(character) && !commonChars.contains(character)) {
                    commonChars.add(character);
                    total += getCharacterPriority(character);
                }
            }
        }

        System.out.println(total);
    }

    @Override
    public void partTwo() {
        List<String> items = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        int total = 0;
        for (int i = 0; i + 3 <= items.size(); i += 3) {
            List<String> group = items.subList(i, i + 3);
            String firstLine = group.get(0);
            List<String> commonChars = new ArrayList<>();
            for (String character : firstLine.split("")) {
                if (group.get(1).contains(character) && group.get(2).contains(character) && !commonChars.contains(
                        character)) {
                    commonChars.add(character);
                    total += getCharacterPriority(character);
                }

            }
        }
        System.out.println(total);
    }

    private int getCharacterPriority(String character) {
        return getLowercaseHashmap().get(character) != null ? getLowercaseHashmap().get(
                character) : getUppercaseHashmap().get(character);
    }

    private Map<String, Integer> getLowercaseHashmap() {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < this.lowercase.length(); i++) {
            map.put(String.valueOf(lowercase.charAt(i)), i + 1);
        }
        return map;
    }

    private Map<String, Integer> getUppercaseHashmap() {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < this.uppercase.length(); i++) {
            map.put(String.valueOf(uppercase.charAt(i)), i + 27);
        }
        return map;
    }
}
