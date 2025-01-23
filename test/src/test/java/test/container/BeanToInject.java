package test.container;

import com.nadaletti.impl.annotation.Inject;
import com.nadaletti.impl.annotation.Component;
import com.nadaletti.impl.annotation.Singleton;
import com.nadaletti.impl.lifecycle.Initializable;

@Singleton
@Component
public class BeanToInject implements Initializable, Bean {

    @Inject
    private TestContainer container;

    private String value;

    @Override
    public void afterPropertiesSet() {
    }

    @Override
    public void doBeanStuf() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}