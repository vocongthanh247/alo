package Jmeter;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;

@RestController
@RequestMapping("/v1/api")
public class LoadTestController {
    @PostMapping("/loadtest/write")
    public ResponseEntity<Response> writeData(@RequestBody RequestData request) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        Response response = new Response();
        response.setStatus("SUCCESS");
        response.setMessage("Data processed successfully");
        response.setTimestamp(new Date());

        return ResponseEntity.ok(response);
    }
}