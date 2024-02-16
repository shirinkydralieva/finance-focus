package crdev.finance_focus.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SwaggerConfig {
    @Bean
    public OpenAPI configure() {
        return new OpenAPI()
                .info(new Info().
                        title("Finance Focus API")
                        .description("Finance Focus API для управления финансами пользователя.")
                        .version("1.0.0")
                );
    }

}
