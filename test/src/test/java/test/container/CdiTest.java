package test.container;

import com.nadaletti.impl.container.BeanContainer;

public class CdiTest {

    public static void startApplication() {
        BeanContainer.initialize("test.container");
    }
}
