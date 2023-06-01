package nusiss.project.server.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RestController
@RequestMapping(path = "/demo")
public class DemoController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> sayHello() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, String> map = new HashMap<>();
        map.put("message", "Hello from secured endpoint");

        String json = objectMapper.writeValueAsString(map);

        return ResponseEntity.ok(json);
    }
    
}
