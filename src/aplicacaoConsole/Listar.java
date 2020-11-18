package aplicacaoConsole;

import java.util.List;

import fachada.Fachada;
import modelo.Conta;

public class Listar {
	public Listar(){
		listar();
		System.out.println("\n\naviso: feche sempre o plugin eclipse antes de executar aplicação");
	}

	public void listar(){
		List<Conta> contas = Fachada.listarTodas();
		for (Conta conta : contas) {
			System.out.println(conta);
		}
	}

	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}

