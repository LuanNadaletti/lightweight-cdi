package test.container;

import com.nadaletti.impl.annotation.ConfigProperty;
import com.nadaletti.impl.annotation.Configuration;

@Configuration
public class ConfigurationClass {

    @ConfigProperty(key = "appName")
    private String aa = "App ..";
}
