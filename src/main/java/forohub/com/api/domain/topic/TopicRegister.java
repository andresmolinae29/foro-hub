package forohub.com.api.domain.topic;
import jakarta.validation.constraints.NotNull;

public record TopicRegister(
        @NotNull
        String titulo,
        @NotNull
        String mensaje,
        @NotNull
        String curso,
        @NotNull
        Long autor
) {
}
