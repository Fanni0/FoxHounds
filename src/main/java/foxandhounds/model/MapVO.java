package foxandhounds.model;

import java.util.Arrays;
import java.util.Objects;
import java.util.StringJoiner;

public class MapVO {

    private final int numberofrows;
    private final int numberofcolumns;
    private final int[][] values;
    private final boolean[][] endrow;
    private int[] foxposition;
    private int[][] houndposition;

    public MapVO(int numberofrows, int numberofcolumns, int[][] values, boolean[][] endrow, int[] foxposition, int[][] houndposition) {
        this.numberofrows = numberofrows;
        this.numberofcolumns = numberofcolumns;
        this.values = deepCopy(values);
        this.endrow = deepCopy(endrow);
        this.foxposition = foxposition;
        this.houndposition = houndposition;
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

    public int[] getFoxposition() {
        return foxposition;
    }

    public int[][] getHoundposition() {
        return houndposition;
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

    private int[] getFoxpos() {
        int row = getNumberofrows();
        int column = getNumberofcolumns();
        int[][] value = getValues();
        int[] foxpos;
        foxpos = new int[2];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (value[i][j] == 4) {
                    foxpos[0] = i;
                    foxpos[1] = j;
                }
            }
        }
        return foxpos;
    }

    private int[][] getHoundpos() {
        int row = getNumberofrows();
        int column = getNumberofcolumns();
        int[][] value = getValues();
        int[][] houndpos;
        houndpos = new int[row / 2][2];
        int a = 0;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (value[i][j] == 7) {
                    houndpos[a][0] = i;
                    houndpos[a][1] = j;
                    a++;
                }
            }
        }
        return houndpos;
    }
}
