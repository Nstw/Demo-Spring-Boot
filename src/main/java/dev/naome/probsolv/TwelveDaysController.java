package dev.naome.probsolv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TwelveDaysController {

    @Autowired
    private TwelveDaysService twelveDaysService;

    @GetMapping("/api/twelve-days/verse?verseNumber")
    public ResponseEntity<ApiResponse> getVerse(@RequestParam(required = false, defaultValue = "1") int verseNumber) {
        if (verseNumber < 1 || verseNumber > 12) {
            return ResponseEntity.badRequest().body(new ApiResponse("error", "Verse number must be between 1 and 12"));
        }
        String verse = twelveDaysService.verse(verseNumber);
        ApiResponse response = new ApiResponse("success", verse);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/twelve-days/verses")
    public ResponseEntity<ApiResponse> getVerses(@RequestParam int startVerse, @RequestParam int endVerse) {
        String verses = twelveDaysService.verses(startVerse, endVerse);
        ApiResponse response = new ApiResponse("success", verses);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/twelve-days/sing")
    public ResponseEntity<ApiResponse> sing() {
        String song = twelveDaysService.sing();
        ApiResponse response = new ApiResponse("success", song);
        return ResponseEntity.ok(response);
    }
}