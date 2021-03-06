package com.mindlin.jsast.impl.runtime.annotations;

import static java.lang.annotation.ElementType.*;
import java.lang.annotation.Target;

@Target(PARAMETER)
public @interface JSParam {
	boolean optional() default false;
	String defaultValue() default "";
}