package com.pgbezerra.mailsender.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteresseProdutoCliente implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Produto produto;
	private Cliente cliente;

}
