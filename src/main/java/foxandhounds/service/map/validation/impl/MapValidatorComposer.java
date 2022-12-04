package foxandhounds.service.map.validation.impl;

import java.util.List;

import foxandhounds.model.MapVO;
import foxandhounds.service.exception.MapValidationException;
import foxandhounds.service.map.validation.MapValidator;

public class MapValidatorComposer implements MapValidator {

    private final List<MapValidator> mapValidatorList;

    public MapValidatorComposer(List<MapValidator> mapValidatorList) {
        this.mapValidatorList = mapValidatorList;
    }

    @Override
    public void validate(MapVO mapVO) throws MapValidationException {
        for (MapValidator mapValidator : mapValidatorList) {
            mapValidator.validate(mapVO);
        }
    }
}
