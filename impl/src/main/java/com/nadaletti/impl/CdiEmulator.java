package com.nadaletti.impl;

import com.nadaletti.impl.core.BeanContainer;
import com.nadaletti.impl.inject.Bean;
import com.nadaletti.impl.inject.Inject;
import com.nadaletti.impl.lifecycle.Singleton;
import com.nadaletti.impl.test.BeanToReceiveInjecton;

@Singleton
@Bean
public class CdiEmulator {

    @Inject
    private static BeanToReceiveInjecton bean;

    public static void main(String[] args) {
        BeanContainer.initialize("cdi");
        bean.foo();
    }
}
