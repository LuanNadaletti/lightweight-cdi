package test;

import org.junit.Before;
import org.junit.Test;

import com.nadaletti.impl.container.ApplicationContainer;

import test.container.CdiTest;
import test.container.TestContainer;

public class BeanInjectionTest {

    private TestContainer testContainer;

    @Before
    public void setup() {
        ApplicationContainer.initialize("test.container");
        testContainer = ApplicationContainer.getComponent(TestContainer.class);
    }

    @Test
    public void testBeanInjection() {
        System.out.println(testContainer.getBeanToInject());
        CdiTest.startApplication();
    }

}
