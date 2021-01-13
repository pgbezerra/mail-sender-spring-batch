package com.pgbezerra.mailsender.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.pgbezerra.mailsender.model.Cliente;
import com.pgbezerra.mailsender.model.InteresseProdutoCliente;
import com.pgbezerra.mailsender.model.Produto;

@Configuration
public class LeitorInteresseConfig {
	
	@Bean
	public JdbcCursorItemReader<InteresseProdutoCliente> leitorInteresse(@Qualifier("appDataSource") DataSource dataSource){
		StringBuilder sql = new StringBuilder();
		  sql.append(" SELECT  ");
		  sql.append("   c.id as id_cliente, ");
		  sql.append("   c.email, ");
		  sql.append("   c.nome as nm_cliente, ");
		  sql.append("   p.nome as nm_produto, ");
		  sql.append("   p.descricao, ");
		  sql.append("   p.id as id_produto, ");
		  sql.append("   p.preco, ");
		  sql.append("   pc.cliente, ");
		  sql.append("   pc.produto ");
		  sql.append(" FROM  ");
		  sql.append("   cliente c  ");
		  sql.append("   JOIN  ");
		  sql.append("     interesse_produto_cliente pc ");
		  sql.append("     ON pc.cliente = c.id ");
		  sql.append("   JOIN produto p ");
		  sql.append("     ON pc.produto = p.id ");
		  sql.append(" order by id_cliente ");
		return new JdbcCursorItemReaderBuilder<InteresseProdutoCliente>()
				.name("leitorInteresse")
				.dataSource(dataSource)
				.sql(sql.toString())
				.rowMapper(rowMapper())
				.build();
				
	}

	private RowMapper<InteresseProdutoCliente> rowMapper() {
		return new RowMapper<InteresseProdutoCliente>() {

			@Override
			public InteresseProdutoCliente mapRow(ResultSet rs, int rowNum) throws SQLException {
				InteresseProdutoCliente interesse = new InteresseProdutoCliente();
				Cliente cliente = new Cliente();
				cliente.setEmail(rs.getString("email"));
				cliente.setNome(rs.getString("nm_cliente"));
				cliente.setId(rs.getInt("id_cliente"));
				Produto produto = new Produto();
				produto.setDescricao(rs.getString("descricao"));
				produto.setNome(rs.getString("nm_produto"));
				produto.setPreco(rs.getDouble("preco"));
				produto.setId(rs.getInt("id_produto"));
				interesse.setCliente(cliente);
				interesse.setProduto(produto);
				return interesse;
			}
		};
	}

}
