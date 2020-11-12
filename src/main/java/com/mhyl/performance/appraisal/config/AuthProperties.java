package com.mhyl.performance.appraisal.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ArchieDing
 * @since 2020/09/08
 */
@Data
@Component
@ConfigurationProperties(prefix = "mhyl.auth")
public class AuthProperties {
	private List<String> whiteUrls;
}
