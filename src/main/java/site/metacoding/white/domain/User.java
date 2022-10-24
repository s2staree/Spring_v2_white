package site.metacoding.white.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter // 나중에 지울것! 안쓰는게 좋음 (Entity가 DB에 이미 있는 데이터를 들고 오기 때문에...)
@Getter
@Entity // 예전에는 Value Object (V.O.)라고 부름 : DB에 이미 있는 데이터를 들고 오는 역할(데이터 변경 불가능) <-> DTO:
        // 변경 가능한 데이터. 화면을 위한 데이터.
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 그 DB가 쓰는 전략을 가져감
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
}
