package com.jugarjuntos.Entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import org.springframework.lang.NonNull;

import com.jugarjuntos.Transfers.TParticipacion;

@Entity
@NamedQueries({
	@NamedQuery(name = "com.jugarjuntos.Entities.Participacion.findByid", query = "select obj from Participacion obj where :id = obj.id "),
	@NamedQuery(name = "com.jugarjuntos.Entities.Participacion.findByusuario", query = "select obj from Participacion obj where :usuario = obj.usuario "),
	@NamedQuery(name = "com.jugarjuntos.Entities.Participacion.findByanuncio", query = "select obj from Participacion obj where :anuncio = obj.anuncio ") })
public class Participacion implements Serializable{
	//Clase intermedia que une al usuario con todos los anuncios en los que participó o está participando

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ParticipacionId id;
	
	@ManyToOne
	@MapsId("usuario_id") private Usuario usuario;
	
	@ManyToOne
	@MapsId("anuncio_id") private Anuncio anuncio;
	
	//Pendiente -> Meter atributo para saber si está en lobby o solo te la has añadido (cuando se pueda añadir futuras partidas)
	@NonNull		//pendiente,aceptado,rechazado
	private String estado_solicitud = "pendiente";
	
	private String text;
	
	public Participacion(Usuario usuario, Anuncio anuncio, String estado, String text) {
		super();
		
		this.id = new ParticipacionId(usuario.getId(), anuncio.getId());
		this.usuario=usuario;
		this.anuncio=anuncio;
		estado_solicitud="pendiente";
		this.text = text;
	}

	public Participacion() {
		super();
	}

	public ParticipacionId getId() {
		return id;
	}

	public void setId(ParticipacionId id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Anuncio getAnuncio() {
		return anuncio;
	}

	public void setAnuncio(Anuncio anuncio) {
		this.anuncio = anuncio;
	}
	
	public String getEstado_solicitud() {
		return estado_solicitud;
	}

	public void setEstado_solicitud(String estado_solicitud) {
		this.estado_solicitud = estado_solicitud;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public TParticipacion entityToTransfer() {
		TParticipacion t = new TParticipacion();
		t.setId_anuncio(this.anuncio.getId());
		t.setId_usuario(this.usuario.getId());
		return t;
	}
	
	
}
