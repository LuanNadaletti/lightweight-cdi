package test.container;

import com.nadaletti.impl.configuration.ConfigProperty;
import com.nadaletti.impl.configuration.Configuration;

@Configuration
public class ConfigurationClass {

    @ConfigProperty(key = "appName")
    private String aa = "App ..";
}
