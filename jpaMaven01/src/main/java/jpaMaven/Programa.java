package jpaMaven;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Programa {

	private static Programa instance;
	protected EntityManager entityManager;

	public static Programa getInstance() {
		if (instance == null) {
			instance = new Programa();
		}

		return instance;
	}

	private Programa() {
		entityManager = getEntityManager(); // getEntityManager() cria uma instância de EntityManager
	}

	// EntityManager que é responsável por realizar as operações de CRUD no banco de
	// dados.
	private EntityManager getEntityManager() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa_exercicio01");
		if (entityManager == null) {
			entityManager = emf.createEntityManager();
		}

		return entityManager;
	}

	public Pessoa obterPorId(final int id) {
		return entityManager.find(Pessoa.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Pessoa> findAll() {
		return entityManager.createQuery("FROM " + Pessoa.class.getName()).getResultList();
	}

	public void inserir(Pessoa pessoa) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(pessoa);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void atualizar(Pessoa pessoa) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(pessoa);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void remover(Pessoa pessoa) {
		try {
			entityManager.getTransaction().begin();
			pessoa = entityManager.find(Pessoa.class, pessoa.getId());
			entityManager.remove(pessoa);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			entityManager.getTransaction().rollback();
		}
	}

	public void removerPeloId(final int id) {
		try {
			Pessoa pessoa = obterPorId(id);
			remover(pessoa);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
