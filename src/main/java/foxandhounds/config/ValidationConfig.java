package foxandhounds.config;

import java.util.List;

import foxandhounds.service.map.validation.MapValidator;
import foxandhounds.service.map.validation.impl.MapByColumnValidator;
import foxandhounds.service.map.validation.impl.MapByRowValidator;
import foxandhounds.service.map.validation.impl.MapValidatorComposer;
import foxandhounds.service.util.CollectionUtil;
import foxandhounds.service.util.MapUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidationConfig {

    /*@Autowired
    private CollectionUtil collectionUtil;

    @Autowired
    private MapUtil mapUtil;*/

    @Bean
    public MapValidatorComposer mapValidatorComposer(List<MapValidator> mapValidatorList) {
        return new MapValidatorComposer(mapValidatorList);
    }

    @Bean
    public MapValidator mapByRowValidator(CollectionUtil collectionUtil, MapUtil mapUtil) {
        return new MapByRowValidator(mapUtil, collectionUtil);
    }

    @Bean
    public MapValidator mapByColumnValidator(CollectionUtil collectionUtil, MapUtil mapUtil) {
        return new MapByColumnValidator(collectionUtil, mapUtil);
    }

}
