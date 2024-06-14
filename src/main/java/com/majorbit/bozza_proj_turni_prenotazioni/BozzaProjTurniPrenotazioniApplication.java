package com.majorbit.bozza_proj_turni_prenotazioni;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan(basePackages = "com.majorbit.bozza_proj_turni_prenotazioni")
public class BozzaProjTurniPrenotazioniApplication {

	public static void main(String[] args) {
		SpringApplication.run(BozzaProjTurniPrenotazioniApplication.class, args);
	}

}
