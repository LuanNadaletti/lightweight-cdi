package test.container;

import com.nadaletti.impl.inject.Inject;
import com.nadaletti.impl.inject.Injectable;
import com.nadaletti.impl.lifecycle.Initializable;
import com.nadaletti.impl.lifecycle.Singleton;

@Singleton
@Injectable
public class BeanToInject implements Initializable {

    @Inject
    private TestContainer container;

    private String value;

    @Override
    public void afterPropertiesSet() {
        System.out.println("BeanToInject initializable bean after properties set");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}