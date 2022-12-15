package Y2021.days.Day6;

import interfaces.Day;
import utils.FileReaderUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 implements Day {
    private int result = 0;
    private int days = 18;

    @Override
    public void partOne() {
        String input = FileReaderUtil.readString(this);
        List<Integer> inputNumbers = Arrays.stream(input.split(",")).map(Integer::parseInt).toList();

        List<Fish> fishes = new ArrayList<>(inputNumbers.stream().map(Fish::new).toList());

//            List<Fish> distinctFishes = new ArrayList<>(fishes.stream().peek(fish -> {
//                int count = (int) fishes.stream().filter(fish::equals).count();
//                fish.setMultiplier(count);
//            }).distinct().toList());

        for (int i = 0; i < days; i++) {
            for (Fish currentFish : fishes) {
//                    long count = fishes.stream().filter(fish -> fish.getTimer() == currentFish.getTimer()).count();
                fishes.remove(currentFish);
//                    if (distinctFish.getTimer() == 0) {
//                        distinctFish.setTimer(6);
//                        distinctFish.increaseMultiplier();
//                    }
//                    distinctFish.decreaseTimer();
            }
        }

//            distinctFishes.forEach(fish -> {
//                result += fish.getMultiplier();
//            });

        System.out.println("Result: " + result);
    }

    @Override
    public void partTwo() {

    }
}
