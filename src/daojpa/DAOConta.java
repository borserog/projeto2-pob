package daojpa;

import java.util.List;

import javax.persistence.LockModeType;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Conta;

public class DAOConta extends DAO<Conta> {

	@Override
	public Conta read(Object chave) {
		try {
			String nome = (String) chave;
			TypedQuery<Conta> query = manager.createQuery("select c from Conta c where c.numero=:n", Conta.class);
			query.setParameter("n", nome);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public Conta consultarContaPorCpf(String cpf) {
		try {
			TypedQuery<Conta> query = manager.createQuery(
					"SELECT DISTINCT con " + "FROM Conta con " + "INNER JOIN con.titular tit " + "WHERE tit.cpf=:cpf",
					Conta.class);
			query.setParameter("cpf", cpf);

			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	public void debitarConta(Conta conta, double valor) {
		try {
			manager.lock(conta, LockModeType.OPTIMISTIC);
			conta.sacar(valor);
			super.update(conta);

		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
