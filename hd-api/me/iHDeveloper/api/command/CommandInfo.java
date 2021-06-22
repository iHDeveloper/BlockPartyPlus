package me.iHDeveloper.api.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface CommandInfo {
  String[] commands();
  
  String[] args();
  
  String[] permissions();
  
  boolean console() default true;
  
  boolean player() default true;
  
  boolean isOp() default false;
}
