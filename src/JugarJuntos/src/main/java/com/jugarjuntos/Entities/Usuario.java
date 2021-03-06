package com.jugarjuntos.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.jugarjuntos.Transfers.TAnuncio;
import com.jugarjuntos.Transfers.TParticipacion;
import com.jugarjuntos.Transfers.TUsuario;

@Entity
@NamedQueries({
	@NamedQuery(name = "com.jugarjuntos.Entities.Usuario.findByparticipacion", query = "select obj from Usuario obj where :participacion MEMBER OF obj.participacion "),
	@NamedQuery(name = "com.jugarjuntos.Entities.Usuario.UsuarioJuego", query = "select obj from Usuario obj where obj.correo = :correo ")})
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String nombre;
	
	private String correo;
	

	private String password;
	

	private String discord;
	
	@OneToMany(mappedBy = "anunciante")
	private List<Anuncio> anuncios;
	
	@OneToMany(mappedBy = "usuario")
	private List<Participacion> participacion;
	

	//ocupado o libre
	private String estado;
	
	
	private double puntuacion_total;
	
	private int num_votaciones;
	
	public Usuario() {
		super();
		this.participacion=new ArrayList<Participacion>();
		this.anuncios=new ArrayList<Anuncio>();
		num_votaciones=1;
		puntuacion_total=2.5;
	}
	

	public Usuario(String nombre, String correo, String password, String discord, String estado) {
		super();
		this.nombre = nombre;
		this.correo = correo;
		this.password = password;
		this.discord = discord;
		this.estado = estado;
		this.participacion=new ArrayList<Participacion>();
		num_votaciones=1;
		puntuacion_total=2.5;
	}


	public long getId() {
		return id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}


	public void setAnuncios(List<Anuncio> anuncio) {
		this.anuncios = anuncio;
	}


	public List<Participacion> getParticipacion() {
		return participacion;
	}


	public void setParticipacion(List<Participacion> participacion) {
		this.participacion = participacion;
	}


	public String getCorreo() {
		return correo;
	}


	public void setCorreo(String correo) {
		this.correo = correo;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getDiscord() {
		return discord;
	}


	public void setDiscord(String discord) {
		this.discord = discord;
	}


	public String getEstado() {
		return estado;
	}


	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public TUsuario entityToTransfer() {
		TUsuario aux= new TUsuario(id, nombre, correo, password, discord, estado);
		List<TParticipacion> parti  = new ArrayList<>();
		for(Participacion p: this.getParticipacion()) {
			parti.add(p.entityToTransfer());
		}
		aux.setParticipacion(parti);
		List<TAnuncio> anuncios  = new ArrayList<>();
		for(Anuncio a: this.getAnuncios()) {
			anuncios.add(a.entityToTransfer());
		}
		aux.setAnuncios(anuncios);
		
		return aux;

	}


	public double getPuntuacion_total() {
		return puntuacion_total;
	}


	public void setPuntuacion_total(double puntuacion_total) {
		this.puntuacion_total = puntuacion_total;
	}


	public int getNum_votaciones() {
		return num_votaciones;
	}


	public void setNum_votaciones(int num_votaciones) {
		this.num_votaciones = num_votaciones;
	}


	public void setId(long id) {
		this.id = id;
	}
	
	
	
}
