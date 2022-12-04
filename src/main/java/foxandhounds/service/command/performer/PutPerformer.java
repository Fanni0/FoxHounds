package service.command.performer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import model.MapVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.exception.PutException;
import service.util.CollectionUtil;
import service.util.CurrentPosition;
import service.util.MapUtil;
import ui.MapPrinter;

public class PutPerformer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapPrinter.class);

    private CurrentPosition position;
    private CollectionUtil collectionUtil;
    private MapUtil mapUtil;

    public boolean placementchecker(MapVO mapVO, int rowIndex, int columnIndex) throws PutException {
        //TODO fix getposition
        int[][] map = mapVO.getValues();
        boolean[][] endrow = mapVO.getEndrow();
        int[] foxposition = position.getFoxPosition(mapVO);
        int[][] houndposition = position.getHoundPosition(mapVO);
        boolean occupiedfox = true;
        boolean occupiedhound = true;

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

    public MapVO perform(MapVO mapVO, int rowIndex, int columnIndex, int number) throws PutException, IOException {
        LOGGER.info("Performing put operation with map = {}, rowindex = {}, columnindex = {}, number = {}",
                mapVO, rowIndex, columnIndex, number);
        int[][] map = mapVO.getValues();
        boolean[][] endrow = mapVO.getEndrow();
        int[] foxposition = position.getFoxPosition(mapVO);
        int[][] houndposition = position.getHoundPosition(mapVO);

        if ((number == 4) && (placementchecker(mapVO, rowIndex, columnIndex))) {
            map[foxposition[0]][foxposition[1]] = 0;
            map[rowIndex][columnIndex] = number;
        } else if ((number == 7) && (placementchecker(mapVO, rowIndex, columnIndex))) {
            System.out.println("Which hound to move?");
            BufferedReader stinput = new BufferedReader(new InputStreamReader(System.in));
            String s = stinput.readLine();
            if (s == "1") {
                map[houndposition[0][0]][houndposition[0][1]] = 0;
                map[rowIndex][columnIndex] = 7;
            } else if (s == "2") {
                map[houndposition[1][0]][houndposition[1][1]] = 0;
                map[rowIndex][columnIndex] = 4;
            }

        }

        return new MapVO(mapVO.getNumberofrows(), mapVO.getNumberofcolumns(), map, endrow);
    }

}
