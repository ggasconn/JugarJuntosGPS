package com.jugarjuntos.registro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import com.jugarjuntos.JugarJuntosApplication;
import com.jugarjuntos.Repositories.UsuarioRepository;
import com.jugarjuntos.ServiciosAplicacion.SAUsuario;
import com.jugarjuntos.ServiciosAplicacionTests.SAUsuarioTest;
import com.jugarjuntos.Transfers.TAnuncio;
import com.jugarjuntos.Transfers.TParticipacion;
import com.jugarjuntos.Transfers.TUsuario;

import java.util.ArrayList;

@SpringBootTest(classes = JugarJuntosApplication.class)
@TestMethodOrder(MethodOrderer.MethodName.class) // Se probarán las operaciones en orden alfabetico
public class RegistroTests {

	@Autowired
	SAUsuario sAUsuario;
	
	@Autowired
	UsuarioRepository userRepo;
	
	static TUsuario tUsuarioCorrecto;
    static TUsuario tUsuarioNombreIncorrecto = new TUsuario("", "correo2@gmail.com", "password2", "discord2", new ArrayList<TAnuncio>(), new ArrayList<TParticipacion>(), "libre");
    static TUsuario tUsuarioGmailIncorrecto = new TUsuario("nombre3", "correo3", "password3", "discord3", new ArrayList<TAnuncio>(), new ArrayList<TParticipacion>(), "libre");
    static TUsuario tUsuarioDiscordIncorrecto = new TUsuario("nombre4", "correo4@gmail.com", "password4", "", new ArrayList<TAnuncio>(), new ArrayList<TParticipacion>(), "libre");


	// Operacion que prueba si funciona la operacion de crear usuario con todos los campos rellenos y correctos
	@Test
	void bcreateOneFineTest() {
		tUsuarioCorrecto = new TUsuario("TestAlta", "unalta@test.es", "vivaElAlta", "altisima#1539");
		tUsuarioCorrecto.setEstado("libre");
		tUsuarioCorrecto.setPuntuacion_total(4);
		tUsuarioCorrecto.setNum_votaciones(2);
		tUsuarioCorrecto.setId(sAUsuario.altaUsuario(tUsuarioCorrecto));
		assertNotEquals(-1, tUsuarioCorrecto.getId());
	}

	// Operacion que prueba si cuando hay un dato repetido
	// en la clave primaria falla en la operación de creación usuario
	@Test
	void ccreateOneWrongTest() {
		assertEquals(-2, sAUsuario.altaUsuario(tUsuarioCorrecto));
	}

    //Operacion que prueba si da error la creación de usuario con un nombre incorrecto(en este caso nombre vacio)
    @Test
	void ccrearNombreIncorrectoTest() {
		assertEquals(-1, sAUsuario.altaUsuario(tUsuarioNombreIncorrecto));
	}

    //Operacion que prueba si da error la creación de usuario con un gmail incorrecto(no cumple el fromato correcto "@")
    @Test
	void ccrearGmailIncorrectoTest() {
		assertEquals(-1, sAUsuario.altaUsuario(tUsuarioGmailIncorrecto));
	}

    //Operacion que prueba si da error la creación de usuario con un discord incorrecto(en este caso discord vacio)
    @Test
	void ccrearDiscordVacioTest() {
		assertEquals(-1, sAUsuario.altaUsuario(tUsuarioDiscordIncorrecto));
	}
    
    @Test
    void zClean() {
    	userRepo.deleteById(tUsuarioCorrecto.getId());
    	assertEquals(1, 1);
    }
}
