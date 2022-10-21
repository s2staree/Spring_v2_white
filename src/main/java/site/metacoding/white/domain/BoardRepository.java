package site.metacoding.white.domain;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // 이게 없으면 service가 repository에 의존하기 때문에 IoC에 뜰 수 없음
public class BoardRepository {

    private final EntityManager em; // db에서 가져온 데이터를 자바object로 바꿔줌

    public void save(Board board) {

        em.persist(board); // insert 됨 (쿼리가 자동으로 돌아감) // persist 뜻: 영속화
    }

    public Board findById(Long id) {
        // JPQL 문법
        Board boardPS = em.createQuery("select b from Board b where b.id = :id", Board.class)
                // 간단한 쿼리작성시 사용, 복잡한 join같은건 native 쿼리 사용 -> 엔티티 조회쿼리(jpql)
                .setParameter("id", id) // mapping되는거 걸기
                .getSingleResult(); // 한 건 받을거니까 single result
        return boardPS;
    }

    public List<Board> findAll() {
        // JPQL 문법
        List<Board> boardList = em.createQuery("select b from Board b", Board.class)
                .getResultList();
        return boardList;
    }

    public void deleteById(Long id) {
        em.createQuery("delete b from Board b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
