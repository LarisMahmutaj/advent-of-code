package Y2022.days.Day13;


import interfaces.Day;
import org.json.JSONArray;
import utils.FileReaderUtil;

import java.util.*;

public class Day13 implements Day {
    private final List<Integer> indices = new LinkedList<>();

    @Override
    public void partOne() {
        List<String> groups = List.of(FileReaderUtil.readString(this).split("\n\n"));

        for (int i = 0; i < groups.size(); i++) {
            String group = groups.get(i);

            String left = group.split("\n")[0];
            String right = group.split("\n")[1];

            List<Object> leftList = new JSONArray(left).toList();
            List<Object> rightList = new JSONArray(right).toList();

            if (isInOrder(leftList, rightList)) {
                indices.add(i + 1);
            }
        }

        int result = indices.stream().mapToInt(x -> x).sum();
        System.out.println(result);
    }

    @Override
    public void partTwo() {
        List<String> packetStrings = List.of(FileReaderUtil.readString(this).replace("\n\n", "\n").split("\n"));

        List<List<Object>> packets = new ArrayList<>(
                packetStrings.stream().map(item -> new JSONArray(item).toList()).toList());
        //add separators
        List<Object> firstDivider = new ArrayList<>(List.of(new ArrayList<>(List.of(2))));
        List<Object> secondDivider = new ArrayList<>(List.of(new ArrayList<>(List.of(6))));

        packets.add(firstDivider);
        packets.add(secondDivider);
        mergeSort(packets, packets.size());

        int firstDividerIndex = packets.indexOf(firstDivider) + 1;
        int secondDividerIndex = packets.indexOf(secondDivider) + 1;

        int result = firstDividerIndex * secondDividerIndex;

        System.out.println(result);
    }

    private void mergeSort(List<List<Object>> list, int size) {
        if (size < 2) {
            return;
        }
        int mid = size / 2;

        List<List<Object>> left = new ArrayList<>(list.subList(0, mid));
        List<List<Object>> right = new ArrayList<>(list.subList(mid, size));

        mergeSort(left, mid);
        mergeSort(right, size - mid);

        merge(list, left, right, mid, size - mid);
    }

    public void merge(
            List<List<Object>> mainList,
            List<List<Object>> leftPackets,
            List<List<Object>> rightPackets,
            int leftSize,
            int rightSize) {
        int i = 0, j = 0, k = 0;

        while (i < leftSize && j < rightSize) {
            int compareTo = compareLists(leftPackets.get(i), rightPackets.get(j));
            if (compareTo != 1) {
                mainList.set(k++, leftPackets.get(i++));
            } else {
                mainList.set(k++, rightPackets.get(j++));
            }
        }

        while (i < leftSize) {
            mainList.set(k++, leftPackets.get(i++));
        }

        while (j < rightSize) {
            mainList.set(k++, rightPackets.get(j++));
        }
    }

    private boolean isInOrder(List<Object> leftList, List<Object> rightList) {
        int result = compareLists(leftList, rightList);
        return result != 1;
    }

    public int compareLists(List<Object> leftList, List<Object> rightList) {
        int result = 0;
        for (int i = 0; i < Math.max(leftList.size(), rightList.size()); i++) {
            Object left;
            Object right;
            try {
                left = leftList.get(i);
            } catch (IndexOutOfBoundsException e) {
                return -1;
            }

            try {
                right = rightList.get(i);
            } catch (IndexOutOfBoundsException e) {
                return 1;
            }
            result = compareItems(left, right);
            if (result != 0) {
                return result;
            }
        }
        return result;
    }

    public int compareItems(Object left, Object right) {
        if (left instanceof Number && right instanceof Number) {
            if ((int) left < (int) right) {
                return -1;
            } else if ((int) left > (int) right) {
                return 1;
            }
            return 0;
        }

        List<Object> leftList;
        List<Object> rightList;
        if (left instanceof Collection<?>) {
            leftList = new ArrayList<>((Collection<?>) left);
        } else {
            leftList = Collections.singletonList(left);
        }
        if (right instanceof Collection<?>) {
            rightList = new ArrayList<>((Collection<?>) right);
        } else {
            rightList = Collections.singletonList(right);
        }

        return compareLists(leftList, rightList);
    }

}
