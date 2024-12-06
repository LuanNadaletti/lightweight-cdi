package test;

import org.junit.Test;

import com.nadaletti.impl.core.BeanContainer;
import com.nadaletti.impl.inject.Bean;
import com.nadaletti.impl.inject.Inject;
import com.nadaletti.impl.lifecycle.Singleton;

@Bean
@Singleton
public class BeanInjectionTest {

    @Inject
    BeanToInject beanToInject;

    @Test
    public void testBeanInjection() {
        BeanContainer.initialize("");
        beanToInject.getClass();
    }
}
