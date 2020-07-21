package com.emanuel.cursocm.services;

import org.springframework.mail.SimpleMailMessage;

import com.emanuel.cursocm.domain.Cliente;
import com.emanuel.cursocm.domain.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPassword);

}
