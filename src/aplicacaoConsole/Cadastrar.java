package aplicacaoConsole;

import java.time.LocalDate;

import fachada.Fachada;

public class Cadastrar {
	public Cadastrar(String nome, String cpf, LocalDate dataNascimento, double saldo){
		cadastrar(nome, dataNascimento, cpf, saldo);
	}

	public void cadastrar(String nome, LocalDate dataNascimento, String cpf, double saldo){
		System.out.println("Cadastrando...");

		try {
			Fachada.criarConta(nome, cpf, dataNascimento, saldo);
						
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}

