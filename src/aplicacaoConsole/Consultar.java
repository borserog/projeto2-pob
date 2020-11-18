package aplicacaoConsole;


import fachada.Fachada;

public class Consultar {
	public Consultar(String cpf){
		consultar(cpf);
	}

	public void consultar(String cpf) {
		System.out.println(Fachada.consultarContaPorCpf(cpf));
	}
}

