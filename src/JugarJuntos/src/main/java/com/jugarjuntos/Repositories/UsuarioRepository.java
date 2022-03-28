package com.jugarjuntos.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.jugarjuntos.Entities.Usuario;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	@Query(value = "SELECT * FROM usuario WHERE id = ?1", nativeQuery = true)
	public Usuario findUsuarioById(long id);
	
	@Query(value = "UPDATE usuario SET estado = 'aceptado' WHERE id = ?1", nativeQuery = true)
	public void cambiarEstadoAceptado(long id);
	
	@Query(value = "SELECT * FROM usuario WHERE nombre = ?1", nativeQuery = true)
	public Usuario findUsuarioByNombre(String nombre);
	
	@Query(value = "SELECT * FROM usuario WHERE correo = ?1", nativeQuery = true)
	public Usuario findUsuarioByCorreo(String correo);

	@Query(value = "SELECT * FROM usuario WHERE correo = ?1 OR nombre = ?2 OR discord = ?3", nativeQuery = true)
	public List<Usuario> findDuplicatedUser(String correo, String name, String discord);
	
	@Query(value = "DELETE FROM usuario WHERE correo = ?1", nativeQuery = true)
	public void deleteByCorreo(String correo);
}