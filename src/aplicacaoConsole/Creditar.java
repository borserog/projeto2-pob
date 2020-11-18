package aplicacaoConsole;

import fachada.Fachada;

public class Creditar {
		public Creditar(String numeroConta, double valor){
			creditar(numeroConta, valor);
		}

		public void creditar(String numeroConta, double valor){
			System.out.println("Debitando...");
			try{
				System.out.println(Fachada.creditarEmConta(numeroConta, valor));
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
}
