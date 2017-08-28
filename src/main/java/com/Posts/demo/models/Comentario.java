package com.Posts.demo.models;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comentario")

public class Comentario {

	//ATTIRBUTES
	@Id
	@GeneratedValue
	private Long id;
	
	private String contenido;
	private Date fecha;
	
	//@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "post_id")
	private Post post;
	
	//@ManyToOne(cascade=CascadeType.ALL)
	@ManyToOne()
	@JoinColumn(name = "user_id")
	private User user;
	
	
	//CONSTRUCTORS
	public Comentario(String contenido, Date fecha, Post post, User user) {
		super();
		this.contenido = contenido;
		this.fecha = fecha;
		this.post = post;
		this.user = user;
	}
	public Comentario(){}
	

	//GETTERS AND SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
	