package site.metacoding.white.domain;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // IoC 등록 // 이게 없으면 service가 repository에 의존하기 때문에 IoC에 뜰 수 없음
public class UserRepository {

    // DI (디펜던시 인덱션)
    private final EntityManager em; // db에서 가져온 데이터를 자바object로 바꿔줌

    public void save(User user) {
        // Persistence Context에 영속화 시키기 -> 자동 flush (트랜잭션 종료시)

        em.persist(user); // insert 됨 (쿼리가 자동으로 돌아감) // persist 뜻: 영속화
    }

    public User findByUsername(String username) {
        return em.createQuery("select u from User u where u.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }

}
