package dev.naome.probsolv;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WordCountController {

    @GetMapping("/api/word-count")
    public ResponseEntity<ApiResponse> countWords(@RequestParam String phrase) {
        WordCountService wordCountService = new WordCountService();
        Map<String, Integer> wordCount = wordCountService.phrase(phrase);
        ApiResponse response = new ApiResponse("success", wordCount);
        return ResponseEntity.ok(response);
    }
}
