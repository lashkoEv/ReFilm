package org.rf.ReFilm;

public @interface SQLQuery {
    String value() default "";
    String objectType();
}
