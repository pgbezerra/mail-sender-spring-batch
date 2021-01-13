package com.pgbezerra.mailsender.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableBatchProcessing
public class EnvioEmailJobConfig {

	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	
	@Bean
	public Job envioEmailJob(Step envioEmailStep) {
		return jobBuilderFactory
				.get("envioEmailJob")
				.start(envioEmailStep)
				.incrementer(new RunIdIncrementer())
				.build();
	}
	
}
