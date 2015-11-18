package org.broken.interceptor;

import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

@InterceptorBinding
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface InterceptMe {

	boolean isActive() default false;

}