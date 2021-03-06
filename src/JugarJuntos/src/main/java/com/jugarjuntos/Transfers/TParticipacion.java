package com.jugarjuntos.Transfers;

public class TParticipacion {

	private long id_usuario;
	private long id_anuncio;
	private String estado;
	private String text;
	
	
	public TParticipacion() {
		super();
	}

	public TParticipacion(long id_usuario, long id_anuncio) {
		super();
		this.id_usuario = id_usuario;
		this.id_anuncio = id_anuncio;
	}

	public TParticipacion(long id_usuario, long id_anuncio, String estado, String text) {
		super();
		this.id_usuario = id_usuario;
		this.id_anuncio = id_anuncio;
		this.estado = estado;
		this.text = text;
	}

	public long getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(long id_usuario) {
		this.id_usuario = id_usuario;
	}

	public long getId_anuncio() {
		return id_anuncio;
	}

	public void setId_anuncio(long id_anuncio) {
		this.id_anuncio = id_anuncio;
	}

	public String isEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}	
}
