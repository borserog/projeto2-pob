package aplicacaoConsole;

import fachada.Fachada;

public class Deletar {
	public Deletar(String contaNumero){
		deletar(contaNumero);
		
	}

	public void deletar(String contaNumero){
		System.out.println("\nExcluindo...\n");
		try {
			Fachada.excluirConta(contaNumero);
			
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
