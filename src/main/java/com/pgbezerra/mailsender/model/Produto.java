package com.pgbezerra.mailsender.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private String descricao;
	private Double preco;
	
}
