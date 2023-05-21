package br.com.empresa.dao;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
	
	private static final EntityManagerFactory emf;
	
	static {
		emf = Persistence.createEntityManagerFactory("AgenciaTelefonica");
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
		
		
	}
}