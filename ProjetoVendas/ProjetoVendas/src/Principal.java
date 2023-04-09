import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Principal {
    public static void main(String[] args) {
        String csvFile = "C:\\Users\\nasci\\OneDrive\\Área de Trabalho\\Vendas.csv";
        String csvSeparator = ",";
        
        String dbUrl = "jdbc:postgresql://localhost:5432/produto";
        String dbUser = "postgres";
        String dbPassword = "326159487";
        
        long startTime = System.nanoTime();
        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             BufferedReader buffer = new BufferedReader(new FileReader(csvFile))) {
            String line;
            boolean firstLine = true;
            while ((line = buffer.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                
                String[] fields = line.split(csvSeparator);
                
                String sql = "INSERT INTO vendas (cd_sequencial, cd_venda, data_venda, nome_loja, produto, "
                        + "quantidade, valor_unitario, valor_final) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                
                stmt.setInt(1, Integer.parseInt(fields[0]));
                stmt.setInt(2, Integer.parseInt(fields[1]));
                stmt.setDate(3, java.sql.Date.valueOf(fields[2]));
                stmt.setString(4, fields[3]);
                stmt.setString(5, fields[4]);
                stmt.setInt(6, Integer.parseInt(fields[5]));
                stmt.setDouble(7, Double.parseDouble(fields[6]));
                stmt.setDouble(8, Double.parseDouble(fields[7]));
                
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo CSV");
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        long durationInMs = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS);
        System.out.println("Tempo total de execução: " + durationInMs + " ms");
//        Deu 45s na minha máquina ;)
    }
}
