package com.example.demo.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sun.istack.NotNull;	

@Entity
@Table(name = "ALUNO")
public class Aluno implements Serializable {
	
	public Aluno() {
		super();
	}

	private static final long serialVersionUID = 1L;
	
	@NotNull
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private Long id; 
	
	@NotNull
	@Column(name = "nome")
	private String nome; 
	
	@NotNull
	@Column(name = "ano")
	private Integer ano; 
	
	@NotNull
	@Column(name = "materia")
	private String materia; 
	
	@NotNull
	@Column(name = "notap1")
	private Integer notap1; 
	
	@NotNull
	@Column(name = "notap2")
	private Integer notap2; 
	
	@NotNull
	@Column(name = "notap3")
	private Integer notap3;
	
	@NotNull
	@Column(name = "media")
	private Integer media;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public Integer getNotap1() {
		return notap1;
	}

	public void setNotap1(Integer notap1) {
		this.notap1 = notap1;
	}

	public Integer getNotap2() {
		return notap2;
	}

	public void setNotap2(Integer notap2) {
		this.notap2 = notap2;
	}

	public Integer getNotap3() {
		return notap3;
	}

	public void setNotap3(Integer notap3) {
		this.notap3 = notap3;
	}

	public Integer getMedia() {
		return media;
	}

	public void setMedia(Integer media) {
		this.media = media;
	}

	


}





