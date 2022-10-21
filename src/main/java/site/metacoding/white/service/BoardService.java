package site.metacoding.white.service;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.BoardRepository;

// 서비스의 역할: 트랜잭션 관리
@RequiredArgsConstructor // 이거 없으면 디폴트생성자
@Service // 이거 안붙이면 IoC(메모리)에 안뜸
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void save(Board board) {
        boardRepository.save(board);
    }

    public Board findById(Long id) {
        return boardRepository.findById(id);
    }
}
