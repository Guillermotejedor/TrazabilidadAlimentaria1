package com.trazabilidad.repositorio;

public interface EmailService {
	 void send(String from, String to, String title, String body);

}
