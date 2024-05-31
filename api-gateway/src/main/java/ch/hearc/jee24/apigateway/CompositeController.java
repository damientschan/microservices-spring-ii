package ch.hearc.jee24.apigateway;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;

import java.util.Iterator;

@RestController
@RequestMapping("/posts-users")
public class CompositeController {

    RestClient restClient = RestClient.create();

    @GetMapping
    @ResponseBody ResponseEntity<String> readPostsUsers(){
        String response = "";

        String posts = restClient.get()
                .uri("lb://USERS-SERVICE/posts")
                .retrieve()
                .body(String.class);

        if(posts == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode rootNode = mapper.readTree(posts);

            for (Iterator<JsonNode> it = rootNode.elements(); it.hasNext(); ) {
                JsonNode post = it.next();
                String publisher = post.get("publisherId").asText();

                String user = restClient.get()
                    .uri("lb://USERS-SERVICE/users/" + publisher)
                    .retrieve()
                    .body(String.class);

                if(user == null)
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

                String postString = post.toString();
                response = response.concat(postString.replace(publisher, user));
            }
        } catch(JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
