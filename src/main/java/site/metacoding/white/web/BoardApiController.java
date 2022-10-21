package site.metacoding.white.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import site.metacoding.white.domain.Board;
import site.metacoding.white.service.BoardService;

@RequiredArgsConstructor
@RestController // JSON return할 것
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/board/{id}")
    public Board findById(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @PutMapping("/board/{id}")
    public String update(@PathVariable Long id, @RequestBody Board board) {
        boardService.update(id, board); // 원래는 Entity가 아니라 dto가 들어가기때문에 id를 따로 빼서 쓰는게 맞음. 여기서만 Entity 사용함
        return "ok";
    }

    @PostMapping("/board")
    public String save(@RequestBody Board board) { // JSON으로 받을거니까 request body를 붙임
        boardService.save(board);
        return "ok";
    }
}
