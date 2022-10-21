package site.metacoding.white.domain;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // 얘가 없으면 service가 repository에 의존하기때문에 IoC에 뜰 수 없다
public class BoardRepository {

    private final EntityManager em; // db에서 가져온 데이터를 자바object로 바꿔줌

    public void save(Board board) {

        em.persist(board); // insert 됨 // persist 뜻: 영속화
    }

    public Board findById(Long id) {
        Board boardPS = em.createQuery("select b from Board b where b.id = :id", Board.class)
                .setParameter("id", id)
                .getSingleResult();
        return boardPS;
    }
}
