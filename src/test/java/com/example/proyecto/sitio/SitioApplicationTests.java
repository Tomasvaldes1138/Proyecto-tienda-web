package com.example.proyecto.sitio;

import com.example.proyecto.sitio.interfaces.IUsuario;
import com.example.proyecto.sitio.modelo.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
class SitioApplicationTests {

/*	@Autowired
	private IUsuario data;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Test
	void contextLoads() {

		Usuario us = new Usuario();
		us.setRut("20.968.333-4");
		us.setNombres("DANIEL");
		us.setApellidos("MARILLANCA");
		us.setUsername("hola123@gmail.com");
		us.setPassword(encoder.encode("123"));
		Usuario retorno = data.save(us);

		Assertions.assertEquals(us.getPassword(), retorno.getPassword());

	}*/

}
