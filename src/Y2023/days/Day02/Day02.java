package Y2023.days.Day02;

import interfaces.Day;
import utils.FileReaderUtil;
import utils.ParseUtil;

import java.util.ArrayList;
import java.util.List;

public class Day02 implements Day {
    private final List<Game> games = new ArrayList<>();

    private final int redCubeCount = 12;
    private final int greenCubeCount = 13;
    private final int blueCubeCount = 14;
    private final List<Game> winningGames = new ArrayList<>();

    public Day02() {
        List<String> lines = ParseUtil.linesToStringList(FileReaderUtil.readString(this));
        for (String line : lines) {
            games.add(new Game(line));
        }
    }

    @Override
    public void partOne() {
        for (Game game : games) {
            boolean win = true;
            for (Subset subset : game.getSubsets()) {

                for (Cube cube : subset.getCubes()) {
                    if (cube.getColor().equals("red") && cube.getCount() > redCubeCount) {
                        win = false;
                        break;
                    } else if (cube.getColor().equals("green") && cube.getCount() > greenCubeCount) {
                        win = false;
                        break;
                    } else if (cube.getCount() > blueCubeCount) {
                        win = false;
                        break;
                    }
                }
            }
            if (win) winningGames.add(game);
        }

        System.out.println(winningGames.stream().mapToInt(Game::getId).sum());
    }

    @Override
    public void partTwo() {
        long result = 0;

        for (Game game : games) {
            long fewestRedCubes = 0;
            long fewestGreenCubes = 0;
            long fewestBlueCubes = 0;
            for (Subset subset : game.getSubsets()) {
                for (Cube cube : subset.getCubes()) {
                    if (cube.getColor().equals("red") && cube.getCount() > fewestRedCubes) {
                        fewestRedCubes = cube.getCount();
                        continue;
                    }
                    if (cube.getColor().equals("green") && cube.getCount() > fewestGreenCubes) {
                        fewestGreenCubes = cube.getCount();
                        continue;
                    }

                    if (cube.getColor().equals("blue") && cube.getCount() > fewestBlueCubes) {
                        fewestBlueCubes = cube.getCount();
                    }
                }
            }
            long power = fewestBlueCubes * fewestGreenCubes * fewestRedCubes;
            result += power;
        }
        System.out.println(result);
    }
}
