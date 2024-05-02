package fr.carrefour.kata;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Amine Achouri
 */
@Configuration
@EnableJpaRepositories(basePackages = { "fr.carrefour.kata.*" })
@ComponentScan(basePackages = { "fr.carrefour.kata.*" })
@EnableTransactionManagement
public class RepoConfig {
}
