/**IFPB - Curso SI 
 * Disciplina de PERSISTENCIA DE OBJETOS
 * @author Prof Fausto Ayres
 */

package daojpa;

import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.eclipse.persistence.config.PersistenceUnitProperties;

public abstract class DAO<T> implements DAOInterface<T> {
	protected static EntityManager manager;
	protected static EntityManagerFactory factory;

	public DAO() {
	}

	public static void open() {
		if (manager == null) {
			HashMap<String, String> propriedades = new HashMap<String, String>();
			
			System.out.println(propriedades);

			factory = Persistence.createEntityManagerFactory("banco-eclipselink-postgres");
			manager = factory.createEntityManager();
		}
	}

	public static void close() {
		if (manager != null) {
			manager.close();
			factory.close();
		}
	}

	public void create(T obj) {
		manager.persist(obj);
	}

	public abstract T read(Object chave);

	public T update(T obj) {
		return manager.merge(obj);
	}

	public void delete(T obj) {
		manager.remove(obj);
	}

	@SuppressWarnings("unchecked")
	public List<T> readAll() {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		TypedQuery<T> query = manager.createQuery("select x from " + type.getSimpleName() + " x", type);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> readAllPagination(int firstResult, int maxResults) {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];

		return manager.createQuery("select x from " + type.getSimpleName() + " x", type).setFirstResult(firstResult - 1)
				.setMaxResults(maxResults).getResultList();
	}

	// ----------------------- TRANSAÇÃO ----------------------
	public static void begin() {
		if (!manager.getTransaction().isActive())
			manager.getTransaction().begin();
	}

	public static void commit() {
		if (manager.getTransaction().isActive()) {
			manager.getTransaction().commit();
			manager.clear(); // ---- esvazia o cache de objetos ----
		}
	}

	public static void rollback() {
		if (manager.getTransaction().isActive())
			manager.getTransaction().rollback();
	}

}
