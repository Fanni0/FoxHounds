package service.map.validation;

import model.MapVO;
import service.exception.MapValidationException;

public interface MapValidator {

    void validate(MapVO mapVO) throws MapValidationException;
}
