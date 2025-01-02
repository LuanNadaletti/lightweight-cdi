package test.container;

import com.nadaletti.impl.inject.Inject;
import com.nadaletti.impl.inject.Injectable;
import com.nadaletti.impl.lifecycle.Singleton;

@Injectable
@Singleton
public class TestContainer {

    @Inject
    BeanToInject beanToInject;

    public void testBeanToInjectInjection() {
        beanToInject.getClass();
    }
}
