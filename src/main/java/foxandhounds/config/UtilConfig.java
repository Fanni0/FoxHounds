package foxandhounds.config;

import foxandhounds.service.util.CollectionUtil;
import foxandhounds.service.util.MapUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UtilConfig {

    @Bean
    public CollectionUtil collectionUtil() {
        return new CollectionUtil();
    }

    @Bean
    public MapUtil mapUtil() {
        return new MapUtil();
    }
}
