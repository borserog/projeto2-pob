package fachada;

import java.time.LocalDate;
import java.util.List;

import daojpa.DAO;
import daojpa.DAOCliente;
import daojpa.DAOConta;
import modelo.Cliente;
import modelo.Conta;

public class Fachada {
	private static DAOCliente daoCliente = new DAOCliente();
	private static DAOConta daoConta = new DAOConta();

	public static void inicializar() {
		DAO.open();
	}

	public static void finalizar() {
		DAO.close();
	}

	public static Conta criarConta(String nome, String cpf, LocalDate dataNascimento, double saldo) {
		Cliente novoCliente = null;

		try {
			novoCliente = cadastrarCliente(nome, cpf, dataNascimento);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		Conta conta = cadastrarConta(saldo);

		vincularCliente(novoCliente, conta);

		return conta;
	}

	private static Conta cadastrarConta(double saldo) {
		DAO.begin();
		Conta conta = new Conta(saldo);
		daoConta.create(conta);
		DAO.commit();

		return conta;
	}

	private static Cliente cadastrarCliente(String nome, String cpf, LocalDate dataNascimento) throws Exception {
		DAO.begin();
		Cliente cliente = daoCliente.read(cpf);
		if (cliente != null) {
			throw new Exception("Cliente ja cadastrado com este cpf:" + cpf);
		}

		cliente = new Cliente(nome, cpf, dataNascimento);
		daoCliente.create(cliente);
		DAO.commit();
		return cliente;
	}

	private static Conta vincularCliente(Cliente cliente, Conta conta) {
		DAO.begin();
		String numeroDaConta = conta.getNumero();
		Conta contaRecuperada = daoConta.read(numeroDaConta);

		String cpfDoCliente = cliente.getCpf();
		Cliente clienteRecuperado = daoCliente.read(cpfDoCliente);

		contaRecuperada.setTitular(clienteRecuperado);

		daoConta.update(contaRecuperada);
		DAO.commit();

		return contaRecuperada;
	}

	public static void excluirConta(String numeroConta) throws Exception {
		DAO.begin();
		Conta conta = daoConta.read(numeroConta);
		if (conta == null)
			throw new Exception("Conta inexistente");

		daoConta.delete(conta);
		DAO.commit();
	}

	public static Conta debitarEmConta(String numeroConta, double valor) throws Exception {
		DAO.begin();

		Conta conta = daoConta.read(numeroConta);
		if (conta == null) {
			throw new Exception("Conta não existe");
		}

		// com lock
		daoConta.debitarConta(conta, valor);

		DAO.commit();

		return conta;
	}

	public static Conta consultarContaPorCpf(String cpf) {
		Conta conta = daoConta.consultarContaPorCpf(cpf);
		return conta;
	}

	public static List<Conta> listarTodas() {
		List<Conta> contas = daoConta.readAll();
		return contas;
	}

	public static Conta creditarEmConta(String numeroConta, double valor) throws Exception {
		DAO.begin();
		Conta conta = daoConta.read(numeroConta);
		if (conta == null) {
			throw new Exception("Conta não existe");
		}
		
		// sem lock
		conta.depositar(valor);

		daoConta.update(conta);
		DAO.commit();

		return conta;
	}

}
