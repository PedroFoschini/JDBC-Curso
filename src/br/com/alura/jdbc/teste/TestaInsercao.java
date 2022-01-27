package br.com.alura.jdbc.teste;
import java.sql.Statement;

import br.com.alura.jdbc.ConnectionFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestaInsercao {

	public static void main(String[] args) throws SQLException {

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperarConexao();

		Statement stm = connection.createStatement();
		stm.execute("INSERT INTO PRODUTO (nome, descricao) VALUES ('Mouse', 'Mouse sem fio')",
				stm.RETURN_GENERATED_KEYS);

		ResultSet rst = stm.getGeneratedKeys();
		while (rst.next()) {
			Integer id = rst.getInt(1);
			System.out.println("O ID criado foi: " + id);
		}
	}

}
