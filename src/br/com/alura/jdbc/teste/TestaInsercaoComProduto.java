package br.com.alura.jdbc.teste;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import br.com.alura.jdbc.ConnectionFactory;
import br.com.alura.jdbc.Produto;
import br.com.alura.jdbc.dao.ProdutoDAO;

public class TestaInsercaoComProduto {

	public static void main(String[] args) throws SQLException {

		Produto comoda = new Produto("Comoda", "Comoda vertical");

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {

			ProdutoDAO produtoDao = new ProdutoDAO(connection);

			produtoDao.salvar(comoda);
			
			List<Produto> listaDeProdutos = produtoDao.listar();
			listaDeProdutos.stream().forEach(lp -> System.out.println(lp));
			


		}

	}
}
