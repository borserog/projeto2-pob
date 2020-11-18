package modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "conta")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Version
	private Long version;

	@Column(name = "saldo")
	private double saldo;

	@Column(name = "numero")
	private String numero;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "titular_id", referencedColumnName = "id")
	private Cliente titular;

	public Conta() {
		super();
	}

	public Conta(double saldo) {
		super();
		this.saldo = saldo;
		this.numero = Conta.gerarNumeroConta();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Cliente getTitular() {
		return titular;
	}

	public void setTitular(Cliente titular) {
		this.titular = titular;
	}
	
	public static String gerarNumeroConta() {
		int resultado = (int) Math.floor(Math.random() * 100000);
		
		return Integer.toString(resultado);
	}


	@Override
	public String toString() {
		return "\n---------- Conta ----------" +
				"\nID: " + id + 
				"\nSALDO: " + saldo + 
				"\nNUMERO: " + numero + 
				"\nTITULAR: " + "\t\t\t" + titular +
				"\n";
	}

	public void sacar(double valor) {
		System.out.println("\nForam sacados $" + valor + ".");
		this.saldo -= valor;
		
		System.out.println("\nSaldo atual $" + this.saldo + ".");		
	}

	public void depositar(double valor) {
		System.out.println("\nForam depositados $" + valor + ".");
		this.saldo += valor;
		
		System.out.println("\nSaldo atual $" + this.saldo + ".");			
	}

}
