package daojpa;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import modelo.Cliente;

public class DAOCliente extends DAO<Cliente> {

	@Override
	public Cliente read(Object chave) {
		try{
			String cpf = (String) chave;
			TypedQuery<Cliente> query = manager.createQuery("select c from Cliente c where c.cpf=:n", Cliente.class);
			query.setParameter("n", cpf);

			return query.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

}
