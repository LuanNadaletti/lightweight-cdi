package test.container;

import com.nadaletti.impl.container.ApplicationContainer;

public class CdiTest {

    public static void startApplication() {
        ApplicationContainer.initialize("test.container");
    }
}
