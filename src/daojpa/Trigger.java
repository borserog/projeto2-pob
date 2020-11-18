package daojpa;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;

import modelo.Cliente;

public class Trigger{

	@PrePersist
	public void prePersistMsg(Object obj) throws Exception {
		System.out.println("\n\n========== TRIGGER EVENT ===========\n");
		System.out.println("Antes de persistir, @PrePersist... " + obj);
		System.out.println("\n===============================\n");
	}

	@PostLoad
	public void postLoadIdade(Object obj) {
		System.out.println("\n\n========== TRIGGER EVENT ===========\n");
		System.out.println("Antes de carregar, @PostLoad... " + obj);
		if (obj instanceof Cliente)  {
			Cliente c = (Cliente) obj;
			int idade = calcularIdade( c );
			c.setIdade(idade);
			System.out.println("Objeto Carregado " + obj );
		}
		System.out.println("\n===============================\n");
	}	

	//============================================================
	public int calcularIdade(Cliente c) {
		LocalDate hoje = LocalDate.now();
		Period per = Period.between(c.getDataNascimento(), hoje);
		int idade = per.getYears();
		return idade;
	}

}
