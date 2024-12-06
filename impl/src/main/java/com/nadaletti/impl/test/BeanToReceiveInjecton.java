package com.nadaletti.impl.test;

import com.nadaletti.impl.inject.Bean;
import com.nadaletti.impl.inject.Inject;
import com.nadaletti.impl.lifecycle.Singleton;

@Bean
@Singleton
public class BeanToReceiveInjecton {

    @Inject
    private BeanToInject beanToInject;

    public void foo() {
    }
}
