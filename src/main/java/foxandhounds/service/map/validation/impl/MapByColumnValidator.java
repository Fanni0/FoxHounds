package foxandhounds.service.map.validation.impl;

import java.util.List;

import foxandhounds.model.MapVO;
import foxandhounds.service.exception.InvalidColumnException;
import foxandhounds.service.exception.MapValidationException;
import foxandhounds.service.map.validation.MapValidator;
import foxandhounds.service.util.CollectionUtil;
import foxandhounds.service.util.MapUtil;

public class MapByColumnValidator implements MapValidator {

    private final CollectionUtil collectionUtil;
    private final MapUtil mapUtil;

    public MapByColumnValidator(CollectionUtil collectionUtil, MapUtil mapUtil) {
        this.collectionUtil = collectionUtil;
        this.mapUtil = mapUtil;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        for (int i = 0; i < mapVO.getNumberofrows(); i++) {
            List<Integer> column = mapUtil.getColumnofMap(mapVO, i);
            validateColumn(column);
        }
    }

    private void validateColumn(List<Integer> column) throws InvalidColumnException {
        List<Integer> values = collectionUtil.collectValues(column);

        if (!collectionUtil.contains(values)) {
            throw new InvalidColumnException("A column can only contain 0, 4 or 7");
        }
    }
}
