package test;

import org.junit.Before;
import org.junit.Test;

import com.nadaletti.impl.container.BeanContainer;

import test.container.CdiTest;
import test.container.TestContainer;

public class BeanInjectionTest {

    private TestContainer testContainer;

    @Before
    public void setup() {
        BeanContainer.initialize("test.container");
        testContainer = BeanContainer.getBean(TestContainer.class);
    }

    @Test
    public void testBeanInjection() {
        System.out.println(testContainer.getBeanToInject().getValue());
        CdiTest.startApplication();
    }

}
