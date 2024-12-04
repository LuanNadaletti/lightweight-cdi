package cdi.test;

import cdi.inject.Inject;
import cdi.inject.Injectable;

@Injectable
public class Bean {

    @Inject
    private BeanToInject beanToInject;

    public void foo() {
    }
}
