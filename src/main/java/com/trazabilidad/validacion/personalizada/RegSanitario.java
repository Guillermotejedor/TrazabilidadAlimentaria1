package com.trazabilidad.validacion.personalizada;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy=RegistroSanitario.class) //La clase que tendra la logica de la validacion
@Target({ElementType.METHOD,ElementType.FIELD}) //Especificamos donde se realizara la validacion
@Retention(RetentionPolicy.RUNTIME)	//Chequea la anotacion en tiempo de ejecucion
public @interface RegSanitario {

	//definir el R.S por defecto
	public String value() default "ES";
	//definir el mensaje de error
	public String message() default "El registro sanitario debe empezar por ES";	
	//definir los grupos
    Class<?>[] groups() default {};
    
	//definir los payload
    Class<? extends Payload>[] payload() default {};
	
}
