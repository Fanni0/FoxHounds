package foxandhounds.service.map.validation.impl;

import java.util.List;

import foxandhounds.model.MapVO;
import foxandhounds.service.exception.InvalidRowException;
import foxandhounds.service.exception.MapValidationException;
import foxandhounds.service.map.validation.MapValidator;
import foxandhounds.service.util.CollectionUtil;
import foxandhounds.service.util.MapUtil;

public class MapByRowValidator implements MapValidator {

    private final MapUtil maputil;
    private final CollectionUtil collectionutil;

    public MapByRowValidator(MapUtil maputil, CollectionUtil collectionutil) {
        this.maputil = maputil;
        this.collectionutil = collectionutil;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        for (int i = 0; i < mapVO.getNumberofrows(); i++) {
            List<Integer> row = maputil.getRowofMap(mapVO, i);
            validateRow(row);
        }
    }

    private void validateRow(List<Integer> row) throws InvalidRowException {
        List<Integer> nonZeroValues = collectionutil.collectValues(row);

        if (!collectionutil.contains(nonZeroValues)) {
            throw new InvalidRowException("A row can only contain 0, 4 or 7");
        }
    }
}
