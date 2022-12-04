package model;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class MapVO {

    private final int numberofrows;
    private final int numberofcolumns;
    private final int[][] values;
    private final boolean[][] endrow;

    public MapVO(int numberofrows, int numberofcolumns, int[][] values, boolean[][] endrow) {
        this.numberofrows = numberofrows;
        this.numberofcolumns = numberofcolumns;
        this.values = deepCopy(values);
        this.endrow = deepCopy(endrow);
    }

    public int getNumberofrows() {
        return numberofrows;
    }

    public int getNumberofcolumns() {
        return numberofcolumns;
    }

    public int[][] getValues() {
        return deepCopy(values);
    }

    public boolean[][] getEndrow() {
        return deepCopy(endrow);
    }

    private int[][] deepCopy(int[][] array) {
        int[][] result = null;
        if (array != null) {
            result = new int[array.length][];
            for (int i = 0; i < array.length; i++) {
                result[i] = Arrays.copyOf(array[i], array[i].length);
            }
        }

        return result;
    }

    private boolean[][] deepCopy(boolean[][] array) {
        boolean[][] result = null;

        if (array != null) {
            result = new boolean[array.length][];
            for (int i = 0; i < array.length; i++) {
                result[i] = Arrays.copyOf(array[i], array[i].length);
            }
        }

        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof MapVO)) {
            return false;
        }
        MapVO mapVO = (MapVO) o;
        return numberofrows == mapVO.numberofrows && numberofcolumns == mapVO.numberofcolumns && Arrays.equals(values, mapVO.values) &&
                Arrays.equals(endrow, mapVO.endrow);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(numberofrows, numberofcolumns);
        result = 31 * result + Arrays.hashCode(values);
        result = 31 * result + Arrays.hashCode(endrow);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MapVO.class.getSimpleName() + "[", "]")
                .add("numberofrows=" + numberofrows)
                .add("numberofcolumns=" + numberofcolumns)
                .add("values=" + Arrays.deepToString(values))
                .add("endrow=" + Arrays.deepToString(endrow))
                .toString();
    }
}
