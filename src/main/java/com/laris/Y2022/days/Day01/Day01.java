package com.laris.Y2022.days.Day01;

import com.laris.interfaces.Day;
import com.laris.utils.FileReaderUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day01 implements Day {
    @Override
    public void partOne() {
        String input = FileReaderUtil.readString(this);
        String[] calorieGroups = input.split("\n\n");
        int highestCalorieCount = 0;
        for (String calorieGroup : calorieGroups) {
            String[] calorieList = calorieGroup.split("\n");
            int groupTotal = 0;
            for (String calorieCount : calorieList) {
                groupTotal += Integer.parseInt(calorieCount);
            }
            if (groupTotal > highestCalorieCount) {
                highestCalorieCount = groupTotal;
            }
        }

        System.out.println(highestCalorieCount);
    }

    @Override
    public void partTwo() {
        String input = FileReaderUtil.readString(this);
        String[] calorieGroups = input.split("\n\n");

        List<Integer> calorieCountList = new ArrayList<>();

        for (String calorieGroup : calorieGroups) {
            String[] calorieList = calorieGroup.split("\n");
            int groupTotal = 0;
            for (String calorieCount : calorieList) {
                groupTotal += Integer.parseInt(calorieCount);
            }

            calorieCountList.add(groupTotal);
        }

        int listSize = calorieCountList.size();
        Collections.sort(calorieCountList);
        int topThreeTotal = calorieCountList.get(listSize - 1) + calorieCountList.get(
                listSize - 2) + calorieCountList.get(listSize - 3);

        System.out.println(topThreeTotal);
    }
}
