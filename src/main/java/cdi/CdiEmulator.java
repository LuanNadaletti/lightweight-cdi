package cdi;

import cdi.inject.Inject;
import cdi.test.Bean;

public class CdiEmulator {

    @Inject
    private static Bean bean;

    public static void main(String[] args) {
        bean.foo();
    }
}
