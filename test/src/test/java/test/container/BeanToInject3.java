package test.container;

import com.nadaletti.impl.annotation.Component;
import com.nadaletti.impl.annotation.Singleton;

@Component
@Singleton
public class BeanToInject3 {

    void foo() {
        System.out.println("3");
    }
}
