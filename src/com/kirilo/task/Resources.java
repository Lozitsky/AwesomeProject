package com.kirilo.task;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.logging.Logger;

// https://github.com/hantsy/jakartaee-faces-sample/tree/22169c003b85f634086b31c564dd855538c0cf2b/src/main/java/com/example

@Dependent
public class Resources {
    @Produces
    public Logger getLogger(InjectionPoint p) {
        return Logger.getLogger(p.getMember().getDeclaringClass().getName());
    }
}
