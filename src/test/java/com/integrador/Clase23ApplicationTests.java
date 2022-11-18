package com.integrador;

import com.integrador.dao.BD;
import com.integrador.model.Paciente;
import com.integrador.service.PacienteService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Clase23ApplicationTests {
	@Test
	public void buscarPacienteId1(){
		BD.crearTablas();
		PacienteService pacienteService=new PacienteService();
		Paciente pacienteBuscado=pacienteService.buscarPaciente(1);
		Assertions.assertEquals("Baspineiro",pacienteBuscado.getApellido());
	}
	@Test
	void contextLoads() {
	}

}
