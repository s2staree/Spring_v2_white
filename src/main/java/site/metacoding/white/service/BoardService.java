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
        return boardRepository.findById(id); // select 할거여서 transaction이 필요없음
    }

    @Transactional
    public void update(Long id, Board board) {

        // boardPS 영속화됨
        Board boardPS = boardRepository.findById(id);

        // 영속화된 데이터 수정하기
        boardPS.setTitle(board.getTitle());
        boardPS.setContent(board.getContent());
        boardPS.setAuthor(board.getAuthor()); // 영속화 된 데이터를 클라이언트에게 입력받은 데이터로 수정함

    }
    // 트랜잭션 종료시 자동으로 flush => 여기서 코드 끝, 만약 id가 없으면 insert
    // 트랜잭션 종료시 -> 더티체킹을 함 (실패한 데이터를 모았다가 한방에 flush ex. 가비지컬렉션)

}
