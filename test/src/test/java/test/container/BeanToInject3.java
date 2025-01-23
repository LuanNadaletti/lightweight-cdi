package test.container;

import com.nadaletti.impl.annotation.Component;

@Component
public class BeanToInject3 {

    void foo() {
        System.out.println("3");
    }
}
