package com.fourthBeam.annotation;

import java.lang.annotation.*;

/**

 * 在本项目中，一些BeanPostProcessor会在合适的时机检测Bean中是否具有某些属性，并且如果上面有`@Passed`注解，代表该Bean期望这个属性由外界初始化并传入

 *

 * 一个典型的例子就是`LoggerInjectBeanPostProcessor`会检测bean中是否有一个标注了`@Passed`注解的`org.slf4j.Logger`的属性，如果有，就自动帮它初始化

 *

 * 就像上面的例子，项目中总有些东西的初始化很繁琐，充满模式化，但又不得不做，`@Passed`注解就是为了解决这个问题

 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Passed {
    /**
     * 对于不同类型的属性，value的作用可能不一样，具体请看对应的BeanPostProcessor上的注释
     * @return
     */
    String value() default "";
}
