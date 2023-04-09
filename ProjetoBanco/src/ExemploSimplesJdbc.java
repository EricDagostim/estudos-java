import java.sql.Statement;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ExemploSimplesJdbc
{

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String USUARIO = "postgres";
	private static final String SENHA = "326159487";
	private static final String URL = "jdbc:postgresql://localhost:5432/produto";

	public void consultarProdutoSimples()
	{
		try {
			Connection conn = ExemploSimplesJdbc.getConnection();

			Statement statement = conn.createStatement();

			ResultSet rs = statement
					.executeQuery("select * from esprodut where upper(descri) " + "like 'AGUA%' limit 10;");

			while (rs.next()) {

				System.out.println("ID: " + rs.getLong("id"));
				System.out.println("Descri: " + rs.getString("descri"));
				System.out.println("Codbar: " + rs.getString("codbar"));
				System.out.println("Status: " + rs.getString("status"));
				System.out.println("Qtdest: " + rs.getBigDecimal("qtdest"));
				System.out.println("Vlrcom: " + rs.getBigDecimal("vlrcom"));
				System.out.println("Vlrven: " + rs.getBigDecimal("vlrven"));
				System.out.println("Datfab: " + rs.getDate("datfab"));
				System.out.println("Datval: " + rs.getDate("datval"));
				System.out.println("---------------------------------");
			}
			conn.close();
			statement.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void consultarProdutoPreparedStatment()
	{
		try {
			Connection conn = ExemploSimplesJdbc.getConnection();

			PreparedStatement pstmt = conn
					.prepareStatement("select * from esprodut where upper(descri) " + "like ? limit ?;");
			pstmt.setString(1, "AGUA%");
			pstmt.setInt(2, 100);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				System.out.println("ID: " + rs.getLong("id"));
				System.out.println("Descri: " + rs.getString("descri"));
				System.out.println("Codbar: " + rs.getString("codbar"));
				System.out.println("Status: " + rs.getString("status"));
				System.out.println("Qtdest: " + rs.getBigDecimal("qtdest"));
				System.out.println("Vlrcom: " + rs.getBigDecimal("vlrcom"));
				System.out.println("Vlrven: " + rs.getBigDecimal("vlrven"));
				System.out.println("Datfab: " + rs.getDate("datfab"));
				System.out.println("Datval: " + rs.getDate("datval"));
				System.out.println("---------------------------------");
			}
			conn.close();
			pstmt.close();
			rs.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static Connection getConnection() throws SQLException, ClassNotFoundException
	{
		Connection con = null;

		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USUARIO, SENHA);

		}

		catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return con;
	}

	public void inserirProdutoSimples()
	{
		try {
			Connection conn = ExemploSimplesJdbc.getConnection();
			String descri = "BATATINHA FRITA 123";
			String codbar = "1.111.111.123.123";
			String status = "I";
			BigDecimal valor = BigDecimal.ZERO;
			Long client = 1L;

			String sql = "INSERT INTO esprodut(id, descri, codbar, status," + "qtdest, vlrcom, vlrven, client) "
					+ "values (nextval('esprodut'), " + "'" + descri + "', " + "'" + codbar + "', " + "'" + status
					+ "', " + valor + ", " + valor + ", " + valor + ", " + client + ");";

			Statement st = conn.createStatement();
			st.execute(sql);

			conn.close();
			st.close();
			System.out.println("Valor inserido!");
		}

		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void inserirProdutoPrepareStatement()
	{
		try {
			Connection conn = ExemploSimplesJdbc.getConnection();
			String descri = "BATATINHA FRITA 123";
			String codbar = "1.111.111.123.123";
			String status = "I";
			BigDecimal valor = BigDecimal.ZERO;
			Long client = 1L;

			PreparedStatement pstmt = 
					conn.prepareStatement("INSERT INTO esprodut("
							+ "id, descri, codbar, status, "
							+ "qtdest, vlrcom, vlrven, client, datfab, datval) "
							+ "values (nextval('sq_produto'), ?,?,?,?,?,?,?,?,?);");
			
			pstmt.setString(1, "LEITE COM BOLACHA");
			pstmt.setString(2, "1.321.321.321.321");
			pstmt.setString(3, "I");
			pstmt.setBigDecimal(4, BigDecimal.ZERO);
			pstmt.setBigDecimal(5, BigDecimal.ZERO);
			pstmt.setBigDecimal(6, BigDecimal.ZERO);
			pstmt.setLong(7, 1L);
			pstmt.setDate(8, new java.sql.Date(new Date().getTime()));
			pstmt.setDate(9, new java.sql.Date(new Date().getTime()));

			pstmt.execute();
			conn.close();
			pstmt.close();
			System.out.println("Valor inserido!");
		}

		catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	public void excluirProdutoPrepareStatement() {
		
		try {
			Connection connection = ExemploSimplesJdbc.getConnection();
			Long id = 41903L;
			String sql = "DELETE FROM esprodut WHERE id = ?;";
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			
			preparedStatement.executeUpdate();
			connection.close();
			preparedStatement.close();
			System.out.println("Valor Excluído com sucesso!");
			
			
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args)
	{

		ExemploSimplesJdbc ex = new ExemploSimplesJdbc();

		// ex.consultarProdutoSimples();
		//ex.inserirProdutoSimples();
		ex.inserirProdutoPrepareStatement();
	}

}
