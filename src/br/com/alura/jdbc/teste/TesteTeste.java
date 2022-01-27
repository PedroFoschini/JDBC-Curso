package br.com.alura.jdbc.teste;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.com.alura.jdbc.ConnectionFactory;

public class TesteTeste {

	public static void main(String[] args) throws SQLException {

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {

			String sqlUpdate = "DELETE FROM PRODUTO WHERE NOME = 'cômoda'";

			try (PreparedStatement pstm = connection.prepareStatement(sqlUpdate)) {

				pstm.execute();
			}
		}

	}

}
