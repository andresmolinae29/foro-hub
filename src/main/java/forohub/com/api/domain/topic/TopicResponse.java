package forohub.com.api.domain.topic;

public record TopicResponse(
        Long id,
        String titulo,
        String mensaje,
        String curso
) {
    public TopicResponse(Topic topic) {
        this(topic.getId(), topic.getTitulo(), topic.getMensaje(), topic.getCurso());
    }
}
