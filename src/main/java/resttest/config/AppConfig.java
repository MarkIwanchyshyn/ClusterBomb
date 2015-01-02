package resttest.config;

/**
 * Copy-pasted shit-magic, remove this comment once i understand whats going on
 */
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
@Configuration
@ComponentScan("resttest")
@EnableWebMvc
public class AppConfig {
}