package foxandhounds.service.command.performer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import foxandhounds.model.MapVO;
import foxandhounds.service.exception.PutException;
import foxandhounds.service.util.CollectionUtil;
import foxandhounds.service.util.MapUtil;
import foxandhounds.ui.MapPrinter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PutPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapPrinter.class);

    private CollectionUtil collectionUtil;
    private MapUtil mapUtil;


    public boolean placementchecker(MapVO mapVO, int rowIndex, int columnIndex) throws PutException {
        int[][] map = mapVO.getValues();
        boolean[][] endrow = mapVO.getEndrow();
        int[] foxposition = mapVO.getFoxposition();
        int[][] houndposition = mapVO.getHoundposition();

        if ((rowIndex == foxposition[0]) && (columnIndex == foxposition[1])) {
            LOGGER.warn("Can't perform put operation on occupied position at rowindex = {} and columnindex = {}", rowIndex, columnIndex);
            throw new PutException("The fox is already there");
        }


        for (int i = 0; i < houndposition.length; i++) {
            if ((rowIndex == houndposition[i][0]) && (columnIndex == houndposition[i][1])) {
                LOGGER.warn("Can't perform put operation on occupied position at rowindex = {} and columnindex = {}", rowIndex,
                        columnIndex);
                throw new PutException("A hound is already there");
            }
        }
        return true;
    }

    public boolean checkfoxmovement(MapVO mapVO, int rowIndex, int columnIndex) throws PutException {
        int[] fox = mapVO.getFoxposition();
        boolean result = false;
        if (((rowIndex == (fox[0] - 1)) && (columnIndex == (fox[1] - 1))) ||
                (((rowIndex == (fox[0] - 1)) && (columnIndex == (fox[1] + 1)))) ||
                (((rowIndex == (fox[0] + 1)) && (columnIndex == (fox[1] - 1))) ||
                        (((rowIndex == (fox[0] + 1)) && (columnIndex == (fox[1] + 1)))))) {
            result = true;
        } else {
            throw new PutException("Can't move in that direction!");
        }
        return result;
    }

    public boolean checkhoundmovement(MapVO mapVO, int rowIndex, int columnIndex, int houndNumber) throws PutException {
        int[][] hound = mapVO.getHoundposition();
        boolean result = false;
        if (houndNumber == 1) {
            if (((rowIndex == (hound[0][0] + 1)) && (columnIndex == (hound[0][1] - 1))) ||
                    ((rowIndex == (hound[0][0] + 1)) && (columnIndex == (hound[0][1] + 1)))) {
                result = true;
            } else {
                throw new PutException("Can't move in that direction!");
            }
        } else if (houndNumber == 2) {
            if (((rowIndex == (hound[1][0] + 1)) && (columnIndex == (hound[1][1] - 1))) ||
                    ((rowIndex == (hound[1][0] + 1)) && (columnIndex == (hound[1][1] + 1)))) {
                result = true;
            } else {
                throw new PutException("Can't move in that direction!");
            }
        } else if (houndNumber == 3) {
            if (((rowIndex == (hound[2][0] + 1)) && (columnIndex == (hound[2][1] - 1))) ||
                    ((rowIndex == (hound[2][0] + 1)) && (columnIndex == (hound[2][1] + 1)))) {
                result = true;
            } else {
                throw new PutException("Can't move in that direction!");
            }
        } else if (houndNumber == 4) {
            if (((rowIndex == (hound[3][0] + 1)) && (columnIndex == (hound[3][1] - 1))) ||
                    ((rowIndex == (hound[3][0] + 1)) && (columnIndex == (hound[3][1] + 1)))) {
                result = true;
            } else {
                throw new PutException("Can't move in that direction!");
            }
        } else {
            throw new PutException("Invalid number");
        }
        return result;
    }

    public MapVO perform(MapVO mapVO, int rowIndex, int columnIndex, int number) throws PutException, IOException {
        LOGGER.info("Performing put operation with map = {}, rowindex = {}, columnindex = {}, number = {}",
                mapVO, rowIndex, columnIndex, number);
        int[][] map = mapVO.getValues();
        boolean[][] endrow = mapVO.getEndrow();
        int[] foxposition = mapVO.getFoxposition();
        int[][] houndposition = mapVO.getHoundposition();

        if (number == 4) {
            return new MapVO(mapVO.getNumberofrows(), mapVO.getNumberofcolumns(), performFox(mapVO, rowIndex, columnIndex, number), endrow,
                    foxposition, houndposition);
        } else {
            return new MapVO(mapVO.getNumberofrows(), mapVO.getNumberofcolumns(), performHound(mapVO, rowIndex, columnIndex, number),
                    endrow, foxposition, houndposition);
        }


    }

    public int[][] performFox(MapVO mapVO, int rowindex, int columnindex, int number) throws PutException {
        int[][] map = mapVO.getValues();
        int[] foxposition = mapVO.getFoxposition();

        if (placementchecker(mapVO, rowindex, columnindex) && checkfoxmovement(mapVO, rowindex, columnindex)) {
            map[foxposition[0]][foxposition[1]] = 0;
            map[rowindex][columnindex] = number;
            foxposition[0] = rowindex;
            foxposition[1] = columnindex;
        }

        return map;

    }

    public int[][] performHound(MapVO mapVO, int rowindex, int columnindex, int number) throws PutException, IOException {
        int rows = mapVO.getNumberofrows();
        int columns = mapVO.getNumberofcolumns();
        int[][] map = mapVO.getValues();
        int[][] houndposition = mapVO.getHoundposition();

        if (placementchecker(mapVO, rowindex, columnindex)) {
            System.out.println("Which hound to move?");
            BufferedReader stinput = new BufferedReader(new InputStreamReader(System.in));
            String s = stinput.readLine();
            int input = Integer.parseInt(s);
            if ((input == 1) && checkhoundmovement(mapVO, rowindex, columnindex, input)) {
                map[houndposition[0][0]][houndposition[0][1]] = 0;
                map[rowindex][columnindex] = 7;
                houndposition[0][0] = rowindex;
                houndposition[0][1] = columnindex;
                return map;
            } else if ((input == 2) && checkhoundmovement(mapVO, rowindex, columnindex, input)) {
                map[houndposition[1][0]][houndposition[1][1]] = 0;
                map[rowindex][columnindex] = 7;
                houndposition[1][0] = rowindex;
                houndposition[1][1] = columnindex;
                return map;
            } else if ((input == 3) && checkhoundmovement(mapVO, rowindex, columnindex, input)) {
                map[houndposition[2][0]][houndposition[2][1]] = 0;
                map[rowindex][columnindex] = 7;
                houndposition[2][0] = rowindex;
                houndposition[2][1] = columnindex;
                return map;
            } else if ((input == 4) && checkhoundmovement(mapVO, rowindex, columnindex, input)) {
                map[houndposition[3][0]][houndposition[3][1]] = 0;
                map[rowindex][columnindex] = 7;
                houndposition[3][0] = rowindex;
                houndposition[3][1] = columnindex;
                return map;
            } else {
                throw new PutException("Invalid number");
            }
        }
        return map;
    }

}
