package projetoSemestre;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Principal {
	
	public static void main(String[] args) {
		
		//Pegando arquivo municipios.csv para leitura na máquina local		
		 String csvMunicipios = "C:\\Users\\nasci\\OneDrive\\Área de Trabalho\\Recuperacao Intermediaria\\municipios.csv";
		 String csvCensoEscolar = "C:\\Users\\nasci\\OneDrive\\Área de Trabalho\\Recuperacao Intermediaria\\censo_por_escola.csv";
		 // Pegando arquivo censo_por_escola.csv para leitura na máquina local
	        
	 	String csvSeparator = ","; 
        String dbUrl = "jdbc:postgresql://localhost:5432/governo";
        String dbUser = "postgres";
        String dbPassword = "326159487";

        
//	        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword); 
//	        	BufferedReader buffer = new BufferedReader(new FileReader(csvMunicipios))) {
//	            String line;
//	            boolean firstLine = true;
//	            while ((line = buffer.readLine()) != null) {
//	                if (firstLine) {
//	                    firstLine = false;
//	                    continue;
//	                }
//	                
//	                String[] fields = line.split(csvSeparator);
//	                
//	                String sql = "INSERT INTO municipios (nome_uf, codigo, nome_municipio) VALUES (?, ?, ?)";
//	                PreparedStatement stmt = conn.prepareStatement(sql);
//	                
//	                stmt.setString(1,(fields[0]));
//	                stmt.setInt(2, Integer.parseInt(fields[1]));
//	                stmt.setString(3,(fields[2]));
//	                
//	                
//	                stmt.executeUpdate();
//	            }
//	        } catch (SQLException e) {
//	            System.out.println("Erro ao conectar ao banco de dados");
//	            e.printStackTrace();
//	        } catch (IOException e) {
//	            System.out.println("Erro ao ler o arquivo CSV");
//	            e.printStackTrace();
//	        }
        
	        
//	        -----------
	        
	        try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword); 
		        	BufferedReader buffer = new BufferedReader(new FileReader(csvCensoEscolar))) {
		            String line;
		            int contador = 0;
		            boolean firstLine = true;
		            while ((line = buffer.readLine()) != null) {
		            	contador++;
		            	System.out.println("Lendo linha : " + contador);
		                
		            	if (firstLine) {
		                    firstLine = false;
		                    continue;
		                }
		            
		            	
		                String[] fields = line.split(csvSeparator);
		                
		                  
//		                for(int i = 0; i <= 13; i++) {	
//		                	if(fields[i] == "" || fields[i] == null){
//		                		fields[i] = "0";		                		
//		                	}
//		                	
//		                	if(i == 13 && fields[i] == null || i == 13 && fields[i] == ""){
//		                		fields[i] = "0";		                		
//		                	}
//		                }
		                
		                String sql = "INSERT INTO censo_escolar (ano, uf, id_municipio, id_escola, rede, ensino, anos_escolares, taxa_aprovacao"
		                		+ ", indicador_rendimento, nota_saeb_mat, nota_saeb_pt, nota_saeb_mediap, ideb) VALUES "
		                		+ "(?, ?, ?, ?, ?, ? ,? ,? ,? ,? , ?, ?, ?, ?)";
		                PreparedStatement stmt = conn.prepareStatement(sql);
		                
//		                stmt.setInt(1, Integer.parseInt(fields[0]));,,,,,,,,,,,,,,,,,
		                stmt.setString(1,(fields[0]));
		                stmt.setString(2,(fields[1]));
		                stmt.setInt(3, Integer.parseInt(fields[2]));
		                stmt.setInt(4, Integer.parseInt(fields[3]));
		                stmt.setString(5,(fields[4]));
		                stmt.setString(6,(fields[5]));
		                stmt.setString(7,(fields[6]));
		                stmt.setBigDecimal(8, new BigDecimal(fields[7]));
		                stmt.setBigDecimal(9, new BigDecimal(fields[8]));
		                stmt.setBigDecimal(10, new BigDecimal(fields[9]));
		                stmt.setBigDecimal(11, new BigDecimal(fields[10]));
		                stmt.setBigDecimal(12, new BigDecimal(fields[11]));
		                stmt.setBigDecimal(13, new BigDecimal(fields[12]));
		                stmt.setString(14,(fields[13]));
//		                stmt.setBigDecimal(14, new BigDecimal(fields[13]));
		                
		                
		                
		                
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
