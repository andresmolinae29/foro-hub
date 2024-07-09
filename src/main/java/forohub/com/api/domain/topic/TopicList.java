package forohub.com.api.domain.topic;

import java.time.LocalDateTime;

public record TopicList(
        Long id,
        String titulo,
        String mensaje,
        String curso
) {
    public TopicList(Topic topics) {
        this(topics.getId(), topics.getTitulo(), topics.getMensaje(), topics.getCurso());
    }

    public TopicList(Long id, String titulo, String mensaje, String curso) {
        this.id = id;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.curso = curso;
    }
}
