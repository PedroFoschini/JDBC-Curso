package br.com.alura.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PrimeiroCRUD {

	public static void main(String[] args) throws SQLException {

		Produto comoda = new Produto("Comoda", "Comoda vertical");

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {

			String sql = "INSERT INTO PRODUTO (NOME, DESCRICAO) VALUES (?, ?)";

			try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

				pstm.setString(1, comoda.getNome());
				pstm.setString(2, comoda.getDescricao());

				pstm.execute();

				try (ResultSet rst = pstm.getGeneratedKeys()) {
					while (rst.next()) {
						comoda.setId(rst.getInt(1));
					}
				}
			}
		}

		System.out.println(comoda);

		String novoNome = "Armario";

		try (Connection connection = new ConnectionFactory().recuperarConexao()) {

			String sqlUpdate = ("UPDATE PRODUTO SET NOME = ? WHERE NOME = ?");

			try (PreparedStatement pstm = connection.prepareStatement(sqlUpdate)) {

				pstm.setString(2, comoda.getNome());
				comoda.setNome(novoNome);

				pstm.setString(1, comoda.getNome());
				pstm.executeUpdate();
			}
		}

		System.out.println("O registro da antiga cômoda agora é: " + comoda);

		System.out.println("========================================");

		System.out.println("Registros do banco de dados: ");

		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection connection = connectionFactory.recuperarConexao();

		PreparedStatement stm = connection.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
		stm.execute();

		ResultSet rst = stm.getResultSet();

		while (rst.next()) {
			Integer id = rst.getInt("ID");
			System.out.println(id);
			String nome = rst.getString("nome");
			System.out.println(nome);
			String descricao = rst.getString("descricao");
			System.out.println(descricao);
		}

		connection.close();

		try (Connection connection2 = new ConnectionFactory().recuperarConexao()) {

			String sqlUpdate = "DELETE FROM PRODUTO WHERE NOME = 'Armario'";

			try (PreparedStatement pstm = connection2.prepareStatement(sqlUpdate)) {

				pstm.execute();

				System.out.println("*** Delete executado com sucesso! ***");
			}
		}

		System.out.println();
		System.out.println();
		System.out.println("### Registros no banco :");

		ConnectionFactory connectionFactory3 = new ConnectionFactory();
		Connection connection3 = connectionFactory3.recuperarConexao();

		PreparedStatement stm3 = connection3.prepareStatement("SELECT ID, NOME, DESCRICAO FROM PRODUTO");
		stm3.execute();

		ResultSet rst3 = stm3.getResultSet();

		while (rst3.next()) {
			Integer id3 = rst3.getInt("ID");
			System.out.println(id3);
			String nome3 = rst3.getString("nome");
			System.out.println(nome3);
			String descricao3 = rst3.getString("descricao");
			System.out.println(descricao3);
		}

		connection3.close();

	}

}
