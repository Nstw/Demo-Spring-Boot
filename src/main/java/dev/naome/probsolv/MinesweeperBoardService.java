package dev.naome.probsolv;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;

@Service
class MinesweeperBoardService {
    private final List<String> board;
    private final int rows;
    private final int cols;

    MinesweeperBoardService(List<String> boardRows) {
        this.board = new ArrayList<>(boardRows);
        this.rows = boardRows.size();
        this.cols = rows > 0 ? boardRows.get(0).length() : 0;
    }

    List<String> withNumbers() {
        if (rows == 0 || cols == 0) {
            return new ArrayList<>(board);
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            StringBuilder row = new StringBuilder();
            String currentRow = board.get(i);
            for (int j = 0; j < cols; j++) {
                char current = currentRow.charAt(j);
                if (current == '*') {
                    row.append('*');
                } else if (current == ' ') {
                    int count = countAdjacentMines(i, j);
                    row.append(count > 0 ? (char) ('0' + count) : ' ');
                } else {
                    row.append(current);
                }
            }
            result.add(row.toString());
        }
        return result;
    }

    private int countAdjacentMines(int row, int col) {
        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0)
                    continue;

                int newRow = row + i;
                int newCol = col + j;

                if (isValidPosition(newRow, newCol) &&
                        board.get(newRow).charAt(newCol) == '*') {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}