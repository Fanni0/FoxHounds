package service.util;

import java.util.ArrayList;
import java.util.List;

import model.MapVO;

public class MapUtil {
    public List<Integer> getRowofMap(MapVO mapVO, int rowIndex) {
        List<Integer> result = new ArrayList<>();

        int[][] map = mapVO.getValues();
        for (int i = 0; i < mapVO.getNumberofcolumns(); i++) {
            result.add(map[rowIndex][i]);
        }
        //System.out.println(result);
        return result;
    }

    public List<Boolean> getEndrow(MapVO mapVO, int rowIndex) {
        List<Boolean> result = new ArrayList<>();

        boolean[][] map = mapVO.getEndrow();
        for (int i = 0; i < mapVO.getNumberofcolumns(); i++) {
            result.add(map[rowIndex][i]);
        }

        return result;
    }

    public boolean isMapCompleted(MapVO mapVO) {
        boolean result = true;
        List<Boolean> firstrow = getEndrow(mapVO, 0);
        List<Integer> foxplace = getRowofMap(mapVO, 0);
        if (!(firstrow.contains(true) && foxplace.contains(4))) {
            result = false;
        }

        return result;
    }
}
