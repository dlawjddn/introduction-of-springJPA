package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManagerFactory emf1 = Persistence.createEntityManagerFactory("hello");
        EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("hello");
        EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("hello");

        //회원 저장
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Member member = new Member();
            member.setId(3L);
            member.setName("dlawjddn");

            em.persist(member);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        emf.close();


        //회원 수정
        EntityManager em2 = emf2.createEntityManager();
        EntityTransaction tx2 = em2.getTransaction();
        tx2.begin();
        try {
            Member findMember = em2.find(Member.class, 3L);
            findMember.setName("dlawjddn");
            tx2.commit();
        } catch (Exception e) {
            tx2.rollback();
        } finally {
            em2.close();
        }
        emf2.close();

        //회원 조회
        EntityManager em1 = emf1.createEntityManager();
        EntityTransaction tx1 = em1.getTransaction();
        tx1.begin();

        try {
            List<Member> result = em1.createQuery("select m from Member as m", Member.class)
                    .getResultList();
            for (Member member : result) {
                System.out.println("member.name = " + member.getName());
            }
            tx1.commit();
        }catch (Exception e){
            tx1.rollback();
        } finally{
            em1.close();
        }
        emf1.close();

        //회원 삭제
        EntityManager em3 = emf3.createEntityManager();
        EntityTransaction tx3 = em3.getTransaction();
        tx3.begin();
        try{
            Member findMember = em3.find(Member.class, 3L);
            em.remove(findMember);

            tx3.commit();
        }catch(Exception e){
            tx3.rollback();
        }finally{
            em3.close();
        }
        emf3.close();
    }
}