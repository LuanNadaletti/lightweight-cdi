package test.container;

import com.nadaletti.impl.annotation.Component;
import com.nadaletti.impl.annotation.Singleton;

@Component
@Singleton
public interface Bean {

    public void doBeanStuf();
}
