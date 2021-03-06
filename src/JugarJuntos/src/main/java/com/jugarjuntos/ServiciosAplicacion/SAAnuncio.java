package com.jugarjuntos.ServiciosAplicacion;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import com.jugarjuntos.Entities.Anuncio;
import com.jugarjuntos.Transfers.TAnuncio;

public interface SAAnuncio {
	public long altaAnuncio(TAnuncio anuncio);

	public List<Anuncio> getAnunciosByNombreJuego(String juego);

	public Anuncio findAnuncioByUser(long id_user);
	
	public List<Anuncio> findAnunciosByAnunciante(long id_anunciante);

	public List<Anuncio> getAllAnuncios();

	public List<Anuncio> getAllAnunciosOrderByTime(String juego);
	
	public List<Anuncio> getAllAnunciosOrderByValoracion(String juego);
	
	public Anuncio getAnuncioByID(long id);

	public boolean terminarAnuncio(long id);

	public boolean borrarAnuncio(long id);

	public boolean empezarAnuncio(long idAnuncio, long idUsuario);

	public boolean checkEmpezado(long idAnuncio);

	public boolean UsuarioEnAnuncio(long idAnuncio, long idUsuario);

	public boolean valorarJugadores(List<Integer> listaNumEstrellas, List<Long> listaNumEstrellasId);

}
