package model;

import java.util.Objects;
import java.util.StringJoiner;

public class GameState {

    private MapVO mapVO;
    private boolean userExit;

    public GameState(MapVO mapVO, boolean userExit) {
        this.mapVO = mapVO;
        this.userExit = userExit;
    }

    public MapVO getMapVO() {
        return mapVO;
    }

    public void setMapVO(MapVO mapVO) {
        this.mapVO = mapVO;
    }

    public boolean isUserExit() {
        return userExit;
    }

    public void setUserExit(boolean userExit) {
        this.userExit = userExit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GameState)) {
            return false;
        }
        GameState gameState = (GameState) o;
        return userExit == gameState.userExit && mapVO.equals(gameState.mapVO);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mapVO, userExit);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", GameState.class.getSimpleName() + "[", "]")
                .add("mapVO=" + mapVO)
                .add("userExit=" + userExit)
                .toString();
    }
}
