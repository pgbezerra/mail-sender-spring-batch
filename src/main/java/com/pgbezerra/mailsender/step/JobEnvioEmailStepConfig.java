package com.pgbezerra.mailsender.step;


import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import com.pgbezerra.mailsender.model.InteresseProdutoCliente;

@Configuration
public class JobEnvioEmailStepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step jobEnvioEmailStep(ItemReader<InteresseProdutoCliente> interesseReader,
			ItemProcessor<InteresseProdutoCliente, SimpleMailMessage> interesseProcessor,
			ItemWriter<SimpleMailMessage> interesseWriter) {
		return stepBuilderFactory
				.get("jobEnvioEmailStep")
				.<InteresseProdutoCliente, SimpleMailMessage>chunk(1)
				.reader(interesseReader)
				.processor(interesseProcessor)
				.writer(interesseWriter)
				.build();
		
	}

}
