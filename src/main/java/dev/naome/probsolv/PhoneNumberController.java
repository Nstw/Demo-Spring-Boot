package dev.naome.probsolv;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PhoneNumberController {

    @GetMapping("/api/phone-number")
    public ResponseEntity<ApiResponse> getPhoneNumber(@RequestParam String input) {
        try {
            PhoneNumberService service = new PhoneNumberService(input);
            return ResponseEntity.ok(new ApiResponse("success", service.getNumber()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponse("error", e.getMessage()));
        }
    }
}