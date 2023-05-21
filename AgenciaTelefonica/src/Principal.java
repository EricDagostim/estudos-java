import java.math.BigInteger;
import java.util.List;
import javax.persistence.EntityManager;
import br.com.empresa.dao.HibernateUtil;
import br.com.empresa.vo.ContatVO;
import br.com.empresa.vo.ForconVO;


public class Principal {

	public static void main(String[] args) {
		
		Principal p = new Principal();
		
		p.cadastrarForcon();
//		p.lerForcon();
//		p.editarForcon(1);
//		p.deletarForcon(1); //id que deseja deletar 
		
		
		System.exit(0);
	}
	
	
	
	private void deletarForcon(int id) {
        EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            ForconVO forcon = em.find(ForconVO.class, id);
            if (forcon != null) {
                em.remove(forcon); 
                System.out.println("Forma de contato deletada!");
            } else {
                System.out.println("Forma de contato não encontrada!");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

	private void editarForcon(int id) {
		
		EntityManager em = HibernateUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            ForconVO forcon = em.find(ForconVO.class, id); // Localizando a entidade pelo ID
            if (forcon != null) {
             
                forcon.setTipcon("B"); 
                forcon.setDddcon("55"); 
                forcon.setNumcon("9 8888-8888"); 
                forcon.setEmacon("novoemail@email.com");

                em.merge(forcon); // Atualizando a entidade no banco de dados
                System.out.println("Forma de contato editada!");
            } else {
                System.out.println("Forma de contato não encontrada!");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
	}

	private void lerForcon() {
		
	    System.out.println("Lendo Forma de Contato...");
	
	    EntityManager em = HibernateUtil.getEntityManager();
	
	    try {
	 
	        List<ForconVO> forcons = em.createQuery("SELECT f FROM ForconVO f", ForconVO.class).getResultList();
	
	        for (ForconVO forcon : forcons) {
	            System.out.println("ID: " + forcon.getId());
	            System.out.println("Tipo de Contato: " + forcon.getTipcon());
	            System.out.println("DDD: " + forcon.getDddcon());
	            System.out.println("Número: " + forcon.getNumcon());
	            System.out.println("Email: " + forcon.getEmacon());
	            System.out.println("ID do Contato: " + forcon.getContat().getId());
	            System.out.println("--------------------------");
	        }
	
	        System.out.println("Leitura concluída!");
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
	}

	private void cadastrarForcon() {
		
		System.out.println("Cadastrando Forma de Contato...");
		
		EntityManager em = HibernateUtil.getEntityManager();
		
		ContatVO ct = new ContatVO();
		ct.setId(BigInteger.ONE);
		
		ForconVO forconVO = new ForconVO();
		forconVO.setTipcon("A");
		forconVO.setDddcon("48");
		forconVO.setNumcon("9 9999-9999");
		forconVO.setEmacon("contato@email.com");
		forconVO.setContat(ct);
		
		System.out.println("Forma de contato cadastrada!");
		
		try {
			em.getTransaction().begin();
			em.persist(forconVO);
			em.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			em.getTransaction().rollback();
		}finally {
			em.close();	
		}
	}
	
		

}
