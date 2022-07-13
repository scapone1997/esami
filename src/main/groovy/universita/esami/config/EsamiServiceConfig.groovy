package universita.esami.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "esami")
class EsamiServiceConfig {

    String msg
    String buildVersion
    Map<String, String> mailDetails
    List<String> activeBranches
}
