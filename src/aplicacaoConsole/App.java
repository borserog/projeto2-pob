package aplicacaoConsole;

import java.time.LocalDate;
import java.util.Scanner;

import daojpa.DAO;
import fachada.Fachada;

public class App {
	public static void main(String[] args) {
		Fachada.inicializar();
		Scanner sc = new Scanner(System.in);
		int option;
		
		
		do {
			System.out.println("=======  Serviço de CONTAS =======");
			System.out.println("1. Atualizar (Debitar)");
			System.out.println("2. Atualizar (Creditar)");
			System.out.println("3. Cadastrar");
			System.out.println("4. Consultar");
			System.out.println("5. Deletar");
			System.out.println("6. Listar");
			System.out.println("0. SAIR");
			
			option = sc.nextInt();
			sc.nextLine();
			
			if (option == 1) {	
				System.out.println("\n---------------DEBITAR CONTA---------------\n");
				System.out.println("Numero da Conta: ");
				String numeroConta = sc.next();
				
				System.out.println("Valor do débito: ");
				double valor = sc.nextDouble();
				
				new Debitar(numeroConta, valor);
			}
			
			if (option == 2) {				
				System.out.println("\n---------------CREDITAR CONTA---------------\n");
				System.out.println("Numero da Conta: ");
				String numeroConta = sc.next();
				
				System.out.println("Valor do crédito: ");
				double valor = sc.nextDouble();
				
				new Creditar(numeroConta, valor);
			}
			
			if (option == 3) {
				System.out.println("\n---------------CADASTRAR NOVA CONTA---------------\n");
				System.out.println("Nome do Cliente:");
				String nome = sc.nextLine();
				
				System.out.println("CPF do Cliente:");
				String cpf = sc.nextLine();
				
				System.out.println("Data de Nascimento do Cliente (yyyy-MM-dd):");
				String dataNascimento = sc.nextLine();
				
				System.out.println("Saldo Inicial:");
				double saldo = sc.nextDouble();
				
				new Cadastrar(nome, cpf, LocalDate.parse(dataNascimento), saldo);
			}
			
			if (option == 4) {
				System.out.println("\n---------------CONSULTA DE CONTA POR CPF---------------\n");
				System.out.println("CPF do titular:");
				String cpf = sc.nextLine();
				
				new Consultar(cpf);
			}
			
			if (option == 5) {
				System.out.println("\n---------------DELETAR CONTA---------------\n");
				System.out.println("Numero da conta:");
				String contaNumero = sc.nextLine();
				
				new Deletar(contaNumero);
			}
			
			if (option == 6) {
				System.out.println("Listando todas as contas...");
	
				new Listar();
			}
			
		} while (option != 0);
		
		sc.close();
		Fachada.finalizar();
	}
}
