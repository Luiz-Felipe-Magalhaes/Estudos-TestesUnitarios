package br.com.alura.tdd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import br.com.alura.tdd.modelo.Funcionario;

class BonusServiceTest {

	@Test
	void bonusDeveriaSerZeroParaFuncionarioComSalarioMuitoAlto() {
		BonusService service = new BonusService();	
		
		/*
		 * assertThrows(IllegalArgumentException.class, () -> service.calcularBonus(new
		 * Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("250000"))));
		 */
		
		try {
			service.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("250000")));
			fail("Teste falhou, não ocorreu a exception esperada.");
		} catch (Exception e ) {
			assertEquals("Funcionario com salario maior de R$10.000,00 não são aptos a receber esse bonus.", e.getMessage());
		}
		
	}

	@Test
	void bonusDeveriaSer10PorCentoDoSalario() {
		BonusService service = new BonusService();
		BigDecimal bonus = service.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("2500")));

		assertEquals(new BigDecimal("250.00"), bonus);

	}

	@Test
	void bonusDeveriaSerMilParaSalarioDeExatamenteDezMil() {
		BonusService service = new BonusService();
		BigDecimal bonus = service.calcularBonus(new Funcionario("Rodrigo", LocalDate.now(), new BigDecimal("10000")));

		assertEquals(new BigDecimal("1000.00"), bonus);

	}

}
