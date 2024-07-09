package forohub.com.api.controller;

import forohub.com.api.domain.topic.*;
import forohub.com.api.domain.users.User;
import forohub.com.api.domain.users.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
@Tag(name = "Topicos", description = "Endpoints para la gestion de topicos")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/{id}")
    @Operation(summary = "obtiene un registro de un topico por Id")
    public ResponseEntity<TopicList> getTopicById(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        return ResponseEntity.ok(new TopicList(topic));
    }

    @GetMapping
    @Operation(summary = "Listado de topicos activos")
    public ResponseEntity<Page<TopicList>> topicList(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(topicRepository.findByStatusTrue(pageable).map(TopicList::new));
    }

    @PostMapping
    @Transactional
    @Operation(summary = "Registra topic")
    public ResponseEntity<TopicResponse> createTopic(@RequestBody @Valid TopicRegister topicRegister,
                                                     UriComponentsBuilder uriBuilder) {
        User user = userRepository.getReferenceById(topicRegister.autor());
        Topic topic = topicRepository.save(new Topic(topicRegister, user));
        TopicResponse topicResponse = new TopicResponse(topic);
        URI url = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(url).body(topicResponse);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Inactiva un topico")
    public ResponseEntity<TopicResponse> deleteTopic(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        topic.inactiveTopic();
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    @Transactional
    @Operation(summary = "actualiza los datos del topico - Mensaje/Curso/Titulo")
    public ResponseEntity<TopicResponse> updateTopic(@RequestBody @Valid TopicUpdate topicUpdate) {
        Topic topic = topicRepository.getReferenceById(topicUpdate.id());
        topic.updateTopic(topicUpdate);
        System.out.println(topic.getTitulo());
        return ResponseEntity.ok(new TopicResponse(topic));
    }

}
