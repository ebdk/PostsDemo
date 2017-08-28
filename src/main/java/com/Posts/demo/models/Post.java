package com.Posts.demo.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "post")

public class Post {

	//ATTIRBUTES
	@Id
	@GeneratedValue
	private Long id;
	
	private String titulo;
	private Date fecha;
	private String imgUrl;
	
	//@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, mappedBy = "post")
	@OneToMany(fetch=FetchType.EAGER, mappedBy = "post")
	private List<Comentario> comentarios;
	
	//CONSTRUCTORS
	public Post() {
		super();
		comentarios = new ArrayList<Comentario>();
	}
	public Post(String titulo, Date fecha, String imgUrl) {
		super();
		this.titulo = titulo;
		this.fecha = fecha;
		this.imgUrl = imgUrl;
		comentarios = new ArrayList<Comentario>();
	}
	
	//GETTERS AND SETTERS
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	//METHODS
	public Comentario getCabecera(){
		return this.getComentarios().get(0);
	}
	
	public void addComentario(Comentario comentario){
		this.getComentarios().add(comentario);
	}
	
	public User getCreador(){
		return this.getCabecera().getUser();
	}
}