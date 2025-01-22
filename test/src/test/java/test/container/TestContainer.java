package test.container;

import com.nadaletti.impl.inject.Inject;
import com.nadaletti.impl.inject.Injectable;
import com.nadaletti.impl.lifecycle.Initializable;
import com.nadaletti.impl.lifecycle.Singleton;

@Injectable
@Singleton
public class TestContainer implements Initializable {

    @Inject
    private BeanToInject beanToInject;

    @Override
    public void afterPropertiesSet() {
    }

    public BeanToInject getBeanToInject() {
        return beanToInject;
    }

}
