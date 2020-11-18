package modelo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;


import daojpa.Trigger;

@Entity
@Table(name="cliente", indexes = {@Index(name = "i_cpf", columnList = "cpf")})
@EntityListeners( Trigger.class )
public class Cliente {
	
	@Id		
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Transient
	private int idade;

	@Column(name="cpf")
	private String cpf;

	@Column(name="data_nascimento")
	private LocalDate dataNascimento;

	public Cliente() {
		super();
	}

	public Cliente(String nome, String cpf, LocalDate dataNascimento) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.dataNascimento = dataNascimento;
	}

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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public String toString() {
		return "\n---------- Cliente ----------" +
				"\nID: " + id + 
				"\nNOME: " + nome + 
				"\nDATA NASCIMENTO: " + dataNascimento +
				"\nIDADE: " + idade +
				"\nCPF: " + cpf +
				"\n";
	}
	
}
