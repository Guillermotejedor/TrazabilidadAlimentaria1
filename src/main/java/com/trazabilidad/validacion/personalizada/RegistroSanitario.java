package com.trazabilidad.validacion.personalizada;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RegistroSanitario implements ConstraintValidator<RegSanitario, String> {
    @Override
    public void initialize(RegSanitario elregistro) {
    	prefijoRegistro=elregistro.value();
    }
    
	
	@Override
	public boolean isValid(String arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		boolean prefijo;
		
		if(arg0!=null) prefijo=arg0.startsWith(prefijoRegistro);
		else prefijo=true;
		
		return prefijo;
	}
	
	private String prefijoRegistro;
}
