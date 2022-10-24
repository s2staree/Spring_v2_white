package site.metacoding.white.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.Board;
import site.metacoding.white.domain.BoardRepository;
import site.metacoding.white.dto.BoardReqDto.BoardSaveDto;

// 서비스의 역할: 트랜잭션 관리
// DTO 변환해서 컨트롤러에게 돌려줘야함

@RequiredArgsConstructor // 이거 없으면 디폴트생성자
@Service // 이거 안붙이면 IoC(메모리)에 안뜸
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void save(BoardSaveDto boardSaveDto) {
        Board board = new Board();
        board.setTitle(boardSaveDto.getTitle());
        board.setContent(boardSaveDto.getContent());
        board.setUser(boardSaveDto.getUser());
        boardRepository.save(board);
    }

    @Transactional(readOnly = true) // 세션 종료 안됨
    public Board findById(Long id) {
        Board boardPS = boardRepository.findById(id); // select 할거여서 transaction이 필요없음 // 오픈 인뷰가 false니까 조회후 세션 종료
        boardPS.getUser().getUsername(); // Lazy 로딩됨. (근데 Eager이면 이미 로딩되서 select 두번
        // 4. user select 됨?
        System.out.println("서비스단에서 지연로딩 함. 왜? 여기까지는 디비커넥션이 유지되니까");
        return boardPS;
    }

    @Transactional
    public void update(Long id, Board board) {

        // boardPS 영속화됨
        Board boardPS = boardRepository.findById(id);

        // 영속화된 데이터 수정하기
        boardPS.setTitle(board.getTitle());
        boardPS.setContent(board.getContent()); // 영속화 된 데이터를 클라이언트에게 입력받은 데이터로 수정함

    }
    // 트랜잭션 종료시 자동으로 flush => 여기서 코드 끝, 만약 id가 없으면 insert
    // 트랜잭션 종료시 -> 더티체킹을 함 (실패한 데이터를 모았다가 한방에 flush ex. 가비지컬렉션)

    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional
    public void deleteById(Long id) {
        boardRepository.deleteById(id);
    }

}
