package forohub.com.api.domain.topic;

import jakarta.validation.constraints.NotNull;

public record TopicUpdate(
        @NotNull
        Long id,
        String titulo,
        String mensaje,
        String curso
) {
}
