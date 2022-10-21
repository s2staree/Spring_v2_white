package site.metacoding.white.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Board { // java로 테이블 생성하는 것
    @Id // pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-increament
    private Long id;
    private String title;
    @Column(length = 1000) // 데이터의 크기가 1000자인 열
    private String content;
    private String author;
}
