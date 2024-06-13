package com.majorbit.bozza_proj_turni_prenotazioni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class BozzaProjTurniPrenotazioniApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(BozzaProjTurniPrenotazioniApplication.class, args);
	}

}
