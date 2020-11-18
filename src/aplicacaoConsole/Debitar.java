package aplicacaoConsole;

import fachada.Fachada;

public class Debitar {
	public Debitar(String numeroConta, double valor){
		debitar(numeroConta, valor);
		System.out.println("\n\naviso: feche sempre o plugin eclipse antes de executar aplicação");
	}

	public void debitar(String numeroConta, double valor){
		System.out.println("Debitando...");
		try{
			System.out.println(Fachada.debitarEmConta(numeroConta, valor));
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
