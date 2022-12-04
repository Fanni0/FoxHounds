package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import foxandhounds.service.util.CollectionUtil;
import foxandhounds.service.util.MapUtil;

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
