package io.tracee.examples.ejb;

import javax.ejb.Remote;

@Remote
public interface TestEjb {

    int multiply(final int mul1, final int mul2);
    int sum(final int add1, final int add2);
    int error(final int a, final int b);

}
