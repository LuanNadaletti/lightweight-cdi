package cdi;

import cdi.core.BeanContainer;
import cdi.inject.Inject;
import cdi.inject.Injectable;
import cdi.lifecycle.Singleton;
import cdi.test.Bean;

@Singleton
@Injectable
public class CdiEmulator {

    @Inject
    private static Bean bean;

    public static void main(String[] args) {
        BeanContainer.initialize("cdi");
        bean.foo();
    }
}
