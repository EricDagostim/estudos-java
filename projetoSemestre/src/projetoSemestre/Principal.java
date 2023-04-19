package projetoSemestre;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Principal {
	
	public static void main(String[] args) {
		 String csvFile = "C:\\Users\\nasci\\OneDrive\\Área de Trabalho\\Recuperacao Intermediaria\\municipios.csv";
	        String csvSeparator = ",";
	        
	        String dbUrl = "jdbc:postgresql://localhost:5432/governo";
	        String dbUser = "postgres";
	        String dbPassword = "326159487";
	        
	       
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
	                
	                String sql = "INSERT INTO municipios (nome_uf, codigo, nome_municipio) VALUES (?, ?, ?)";
	                PreparedStatement stmt = conn.prepareStatement(sql);
	                
	                stmt.setString(1,(fields[0]));
	                stmt.setInt(2, Integer.parseInt(fields[1]));
	                stmt.setString(3,(fields[2]));
	                
	                
	                stmt.executeUpdate();
	            }
	        } catch (SQLException e) {
	            System.out.println("Erro ao conectar ao banco de dados");
	            e.printStackTrace();
	        } catch (IOException e) {
	            System.out.println("Erro ao ler o arquivo CSV");
	            e.printStackTrace();
	        }
	        
	}

}
