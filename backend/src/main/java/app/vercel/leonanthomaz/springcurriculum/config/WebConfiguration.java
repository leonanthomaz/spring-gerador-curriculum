package app.vercel.leonanthomaz.springcurriculum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuração para permitir requisições Cross-Origin Resource Sharing (CORS).
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    /**
     * Adiciona mapeamentos CORS para permitir requisições de origens específicas.
     *
     * @param registry o registro de mapeamentos CORS
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000");
    }
}
