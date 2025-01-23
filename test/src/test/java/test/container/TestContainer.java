package test.container;

import com.nadaletti.impl.annotation.Inject;
import com.nadaletti.impl.annotation.Component;
import com.nadaletti.impl.annotation.Singleton;
import com.nadaletti.impl.lifecycle.Initializable;

@Component
@Singleton
public class TestContainer implements Initializable {

    @Inject
    private Bean beanToInject;

    @Override
    public void afterPropertiesSet() {
    }

    public Bean getBeanToInject() {
        return beanToInject;
    }

}
