package com.pgbezerra.mailsender.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.pgbezerra.mailsender.model.InteresseProdutoCliente;

@Component
public class EnvioEmailProcessor implements ItemProcessor<InteresseProdutoCliente, SimpleMailMessage>{

	@Override
	public SimpleMailMessage process(InteresseProdutoCliente interesse) throws Exception {
		SimpleMailMessage mailmessage = new SimpleMailMessage();
		mailmessage.setFrom("xpto@mail.com");
		mailmessage.setTo(interesse.getCliente().getEmail());
		mailmessage.setSubject("Promocao imperdivel");
		mailmessage.setText(gerarTexto(interesse));
		Thread.sleep(2000);
		return mailmessage;
	}

	private String gerarTexto(InteresseProdutoCliente interesse) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("Ola %s!\n\n\n", interesse.getCliente().getNome()));
		sb.append("Super promoçao!!!!\n");
		sb.append("Preço imbativel!!!\n");
		sb.append(String.format("Produto %s por R$ %s", interesse.getProduto().getNome(), interesse.getProduto().getPreco()));
		return sb.toString();
	}

}
