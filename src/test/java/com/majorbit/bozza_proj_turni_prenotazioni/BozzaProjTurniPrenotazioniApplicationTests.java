package com.majorbit.bozza_proj_turni_prenotazioni;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class BozzaProjTurniPrenotazioniApplicationTests {

	Calculator underTest = new Calculator();

	@Test
	void itShouldAddNumbers() {

		//given
		var numberOne = 20;
		var numberoTwo = 30;

		//when
		var result = underTest.add(numberOne, numberoTwo);

		//then
		assertThat(result).isEqualTo(50);

	}

	class Calculator {
		int add(int a, int b) {
			return a +b;
		}
	}
}
