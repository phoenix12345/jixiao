package com.mhyl.performance.appraisal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author ArchieDing
 * @since 2020/09/03
 */
@Configuration
public class ExecutorConfig {
	@Bean
	public Executor asyncTask() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(30);
		executor.setMaxPoolSize(200);
		executor.setQueueCapacity(30);
		executor.setThreadNamePrefix("AsyncTask-");
		executor.initialize();
		return executor;
	}
}
