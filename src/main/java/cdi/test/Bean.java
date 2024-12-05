package cdi.test;

import cdi.inject.Inject;
import cdi.inject.Injectable;
import cdi.lifecycle.Singleton;

@Injectable
@Singleton
public class Bean {

    @Inject
    private BeanToInject beanToInject;

    public void foo() {
    }
}
