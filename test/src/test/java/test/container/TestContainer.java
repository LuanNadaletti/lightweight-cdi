package test.container;

import com.nadaletti.impl.annotation.Component;
import com.nadaletti.impl.annotation.Inject;
import com.nadaletti.impl.annotation.Singleton;
import com.nadaletti.impl.lifecycle.Initializable;

@Component
@Singleton
public class TestContainer implements Initializable {

    private Bean beanToInject;

    @Inject
    public TestContainer(Bean beanToInject) {
        this.beanToInject = beanToInject;
    }

    @Override
    public void afterPropertiesSet() {
        beanToInject.doBeanStuf();
    }

    public Bean getBeanToInject() {
        return beanToInject;
    }

}
