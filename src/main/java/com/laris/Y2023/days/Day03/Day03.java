package com.laris.Y2023.days.Day03;

import com.laris.interfaces.Day;
import org.apache.commons.lang3.IntegerRange;
import org.apache.commons.lang3.StringUtils;
import com.laris.utils.FileReaderUtil;
import com.laris.utils.ParseUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day03 implements Day {
    private final String specialChars = "@#$%&*-=+/";
    private final List<PartNumber> allNumbers = new ArrayList<>();
    private final List<PartNumber> partNumbers = new ArrayList<>();
    private final List<Part> parts = new ArrayList<>();
    private final Map<Part, List<PartNumber>> gears = new HashMap<>();

    public Day03() {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            StringBuilder numberBuilder = new StringBuilder();
            PartNumber pnr = new PartNumber();

            String[] split = line.split("");

            for (int j = 0; j < split.length; j++) {
                String character = split[j];
                if (StringUtils.isNumeric(character)) {
                    pnr.setY(i);
                    if (numberBuilder.isEmpty()) {
                        pnr.setStartX(j);
                    }
                    numberBuilder.append(character);
                    if(j == split.length-1){
                        createNumber(numberBuilder, pnr, j);
                        pnr = new PartNumber();
                    }
                } else if(!numberBuilder.isEmpty()){
                    createNumber(numberBuilder, pnr, j-1);
                    pnr = new PartNumber();
                }
                if (specialChars.contains(character)) {
                    parts.add(new Part(j, i, character.charAt(0)));
                }
            }
        }
    }

    @Override
    public void partOne() {
        calculateParts();
        System.out.println(partNumbers.stream().mapToLong(PartNumber::getValue).sum());
    }

    @Override
    public void partTwo() {
        calculateParts();
        int totalSum = 0;
        for (Part gear : gears.keySet()) {
            if(gears.get(gear).size() == 2) {
                totalSum += gears.get(gear).stream().mapToInt(PartNumber::getValue).reduce(1, (a, b) -> a * b);
            }
        }
        System.out.println(totalSum);
    }

    private void createNumber(StringBuilder sb, PartNumber pnr, int endX) {
        pnr.setEndX(endX);
        pnr.setValue(Integer.parseInt(sb.toString()));
        allNumbers.add(pnr);
        sb.delete(0, sb.length());
    }

    private void calculateParts() {
        for (PartNumber number : allNumbers) {
            for(Part part : parts) {
                if(Math.abs(part.getY() - number.getY()) <=1 ){
                    int numberLength = String.valueOf(number.getValue()).length();
                    IntegerRange range = IntegerRange.of(part.getX() - numberLength, part.getX() + numberLength);

                    if(number.getStartX() <= part.getX() && number.getStartX() >= range.getMinimum() ||
                            number.getEndX() >= part.getX() && number.getEndX() <= range.getMaximum()) {
                        if(!partNumbers.contains(number)){
                            partNumbers.add(number);
                            if(part.getCharacter() == '*'){
                                if(!gears.containsKey(part)) {
                                    gears.put(part, new ArrayList<>());
                                    gears.get(part).add(number);
                                }else {
                                    gears.get(part).add(number);
                                }
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
}
