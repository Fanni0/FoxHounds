package foxandhounds.service.map.validation;

import foxandhounds.model.MapVO;
import foxandhounds.service.exception.MapValidationException;

public interface MapValidator {

    void validate(MapVO mapVO) throws MapValidationException;
}
