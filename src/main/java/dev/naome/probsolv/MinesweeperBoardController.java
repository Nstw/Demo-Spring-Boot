package dev.naome.probsolv;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MinesweeperBoardController {

    @GetMapping("/api/minesweeper-board")
    public ResponseEntity<ApiResponse> getNumberedBoard(@RequestParam List<String> rows) {
        MinesweeperBoardService service = new MinesweeperBoardService(rows);
        List<String> numberedBoard = service.withNumbers();
        return ResponseEntity.ok(new ApiResponse("success", numberedBoard));
    }
}
