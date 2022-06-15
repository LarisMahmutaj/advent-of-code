package days.Day3;

import interfaces.Day;
import utils.FileReaderUtil;
import utils.ParseUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day3 implements Day {
    private int powerConsumption;
    private int lifeSupport;
    private String gamaRateBinary = "";
    private String epsilonRateBinary = "";


    @Override
    public void partOne() {
        try{
            String input = FileReaderUtil.readString(this);
            List<String> values = ParseUtil.linesToStringArray(input);
            int valueLength = values.get(0).length();

            for (int i = 0; i < valueLength; i++){
                int zeroCount = 0;
                int oneCount = 0;

                for (String value : values) {
                    switch(value.charAt(i)){
                        case '0' -> zeroCount++;
                        case '1' -> oneCount++;
                    }
                }

                if(zeroCount > oneCount) {
                    gamaRateBinary = gamaRateBinary.concat("0");
                    epsilonRateBinary = epsilonRateBinary.concat("1");
                }else{
                    gamaRateBinary = gamaRateBinary.concat("1");
                    epsilonRateBinary = epsilonRateBinary.concat("0");
                }
            }

            int gamaRateDecimal = Integer.parseInt(gamaRateBinary, 2);
            int epsilonRateDecimal = Integer.parseInt(epsilonRateBinary, 2);

            powerConsumption = gamaRateDecimal * epsilonRateDecimal;
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Result: " + powerConsumption);
    }

    @Override
    public void partTwo() {
        try{
            String input = FileReaderUtil.readString(this);
            List<String> values = ParseUtil.linesToStringArray(input);

            String o2RatingBinary  = filterRatings(values, false);
            String co2RatingBinary = filterRatings(values, true);

            int o2Rating = Integer.parseInt(o2RatingBinary, 2);
            int co2Rating = Integer.parseInt(co2RatingBinary, 2);

            lifeSupport = o2Rating * co2Rating;
        } catch(IOException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Result: " + lifeSupport);
    }

    private String filterRatings(List<String> ratingList, boolean filterFewer) {
        int index = 0;
        while (ratingList.size() > 1) {
            int zeroCount = 0;
            int oneCount = 0;
            for (String item : ratingList) {
                if(item.charAt(index) == '1') {
                    oneCount++;
                }else{
                    zeroCount++;
                }
            }

            char filteredChar;
            if(filterFewer){
                filteredChar = zeroCount <= oneCount ? '0' : '1';
            }else {
                filteredChar = oneCount >= zeroCount ? '1' : '0';
            }

            List<String> filteredList = new ArrayList<>();
            for (String value : ratingList) {
                if (value.charAt(index) == filteredChar) {
                    filteredList.add(value);
                }
            }
            ratingList = filteredList;
            index++;
        }
        return ratingList.get(0);
    }
}
