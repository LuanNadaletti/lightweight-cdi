package test.container;

import com.nadaletti.impl.annotation.Component;
import com.nadaletti.impl.annotation.Inject;

@Component
public class BeanToInject2 implements Bean {

    @Inject
    private BeanToInject3 bean3;

    @Override
    public void doBeanStuf() {
        System.out.println("2");
    }

    public void call3() {
        bean3.foo();
    }
}
