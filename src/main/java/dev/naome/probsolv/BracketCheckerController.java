package dev.naome.probsolv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BracketCheckerController {

    @Autowired
    private BracketCheckerService bracketCheckerService;

    @GetMapping("/api/bracket-checker")
    public ResponseEntity<ApiResponse> checkBrackets(@RequestParam String expression) {
        if (expression == null || expression.isEmpty()) {
            return ResponseEntity.badRequest().body(new ApiResponse("error", "Expression cannot be empty"));
        }
        boolean isValid = bracketCheckerService.areBracketsValid(expression);
        String message = isValid ? "Brackets are valid" : "Brackets are not valid";
        ApiResponse response = new ApiResponse("success", message);
        return ResponseEntity.ok(response);
    }
}
