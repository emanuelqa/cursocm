package com.emanuel.cursocm.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.emanuel.cursocm.domain.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagto, Date instance) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instance);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDataVencimento(cal.getTime());
	}
}
