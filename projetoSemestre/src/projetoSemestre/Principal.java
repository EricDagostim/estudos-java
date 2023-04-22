package projetoSemestre;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Principal {
	static String csvSeparator = ","; 
    static String dbUrl = "jdbc:postgresql://localhost:5432/governo";
    static String dbUser = "postgres";
    static String dbPassword = "326159487";
	
	public static void main(String[] args) {
//		insertMunicipios();
//		insertCensoEscolar();
//		insertMediaCenso();
		exportPorEstado();
		  
	}
	
	private static void exportPorEstado() {
		          
			try {
				Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				String sql = "select uf, nome_uf from media_censo group by uf, nome_uf";
				
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet readLine = stmt.executeQuery();
				
					while (readLine.next()) {
						
						String estado = readLine.getString("uf") + " - " + readLine.getString("nome_uf");
						
						System.out.println("Criando arquivo " + estado);
						
						File arquivo = new File("C:/Users/nasci/OneDrive/Área de Trabalho/Recuperacao Intermediaria/relatorios/" + estado + ".csv");
						FileWriter writer = new FileWriter(arquivo);
						writer.write("uf,nome_uf,nome_municipio,rede,ensino,anos_escolares,id_municipio,"
								+ "id_escola,taxa_aprovacao,indicador_rendimento,nota_saeb_mat,nota_saeb_pt,"
								+ "nota_saeb_mediap,ideb\n");
						
						String buscaEstado = "select * from media_censo where uf = '" + readLine.getString("uf") 
						+ "' order by nome_municipio";
						
						PreparedStatement stmt2 = conn.prepareStatement(buscaEstado);
						ResultSet queryEstado = stmt2.executeQuery();
	
						
						
						
						while (queryEstado.next()) {
							String uf = queryEstado.getString("uf");
					        String nome_uf = queryEstado.getString("nome_uf");
					        String nome_municipio = queryEstado.getString("nome_municipio");
					        String rede = queryEstado.getString("rede");
					        String ensino = queryEstado.getString("ensino");
					        String anos_escolares = queryEstado.getString("anos_escolares");
					        int id_municipio = queryEstado.getInt("id_municipio");
					        int id_escola = queryEstado.getInt("id_escola");
					        BigDecimal taxa_aprovacao = queryEstado.getBigDecimal("taxa_aprovacao");
					        BigDecimal indicador_rendimento = queryEstado.getBigDecimal("indicador_rendimento");
					        BigDecimal nota_saeb_mat = queryEstado.getBigDecimal("nota_saeb_mat");
					        BigDecimal nota_saeb_pt = queryEstado.getBigDecimal("nota_saeb_pt");
					        BigDecimal nota_saeb_mediap = queryEstado.getBigDecimal("nota_saeb_mediap");
					        BigDecimal ideb = queryEstado.getBigDecimal("ideb");
							
					        writer.write(uf + "," + nome_uf + "," + nome_municipio + "," + rede + "," + ensino + "," 
									+ anos_escolares + "," + id_municipio + "," + id_escola + "," + taxa_aprovacao + "," 
									+ indicador_rendimento + "," + nota_saeb_mat + "," + nota_saeb_pt + "," + nota_saeb_mediap 
									+ "," + ideb + "\n");
					        System.out.println("Escrevendo no arquivo " + estado);
						}
						writer.close();
						System.out.println("Arquivo " + estado + " finalizado! ");
					}
				
			} catch (SQLException e) {
				System.out.println("Erro com a conexão ao banco");
				e.printStackTrace();
			} catch (IOException e) {
				System.out.println("Ocorreu algum problema com a leitura ou escrita do arquivo");
				e.printStackTrace();
			}
              
		
	}

	private static void insertMediaCenso() {
		
		try {	
			Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			String sql = "SELECT uf, m.nome_uf, m.nome_municipio, ce.rede, ce.ensino, ce.anos_escolares, ce.id_municipio, \r\n"
					+ "		ce.id_escola,\r\n"
					+ "		AVG(ce.taxa_aprovacao) as taxa_aprovacao, \r\n"
					+ "		AVG(ce.indicador_rendimento) as indicador_rendimento, \r\n"
					+ "		AVG(ce.nota_saeb_mat) as nota_saeb_mat, \r\n"
					+ "		AVG(ce.nota_saeb_pt) as nota_saeb_pt, \r\n"
					+ "		AVG(ce.nota_saeb_mediap) nota_saeb_mediap, \r\n"
					+ "		AVG(ce.ideb) as ideb\r\n"
					+ "		FROM censo_escolar ce\r\n"
					+ "		JOIN public.municipios m ON m.codigo = ce.id_municipio\r\n"
					+ "		GROUP BY m.nome_uf, m.nome_municipio, ce.rede, ce.ensino, ce.anos_escolares, "
					+ "ce.id_municipio, ce.id_escola, uf;";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet readLine = stmt.executeQuery();

					
			
			while (readLine.next()) {
		        String uf = readLine.getString("uf");
		        String nome_uf = readLine.getString("nome_uf");
		        String nome_municipio = readLine.getString("nome_municipio");
		        String rede = readLine.getString("rede");
		        String ensino = readLine.getString("ensino");
		        String anos_escolares = readLine.getString("anos_escolares");
		        int id_municipio = readLine.getInt("id_municipio");
		        int id_escola = readLine.getInt("id_escola");
		        BigDecimal taxa_aprovacao = readLine.getBigDecimal("taxa_aprovacao");
		        BigDecimal indicador_rendimento = readLine.getBigDecimal("indicador_rendimento");
		        BigDecimal nota_saeb_mat = readLine.getBigDecimal("nota_saeb_mat");
		        BigDecimal nota_saeb_pt = readLine.getBigDecimal("nota_saeb_pt");
		        BigDecimal nota_saeb_mediap = readLine.getBigDecimal("nota_saeb_mediap");
		        BigDecimal ideb = readLine.getBigDecimal("ideb");
		        
		        String insertMediaCenso = "INSERT INTO media_censo (uf, nome_uf, nome_municipio, rede, ensino, anos_escolares, "
		        		+ "id_municipio, id_escola, taxa_aprovacao, indicador_rendimento, nota_saeb_mat, nota_saeb_pt, nota_saeb_mediap,"
		        		+ " ideb ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	            
		        PreparedStatement stmt1 = conn.prepareStatement(insertMediaCenso);
	           
	            stmt1.setString(1, uf);
	            stmt1.setString(2, nome_uf);
	            stmt1.setString(3, nome_municipio);
	            stmt1.setString(4, rede);
	            stmt1.setString(5, ensino);
	            stmt1.setString(6, anos_escolares);
	            stmt1.setInt(7, id_municipio);
	            stmt1.setInt(8, id_escola);
	            stmt1.setBigDecimal(9, taxa_aprovacao);
	            stmt1.setBigDecimal(10, indicador_rendimento);
	            stmt1.setBigDecimal(11, nota_saeb_mat);
	            stmt1.setBigDecimal(12, nota_saeb_pt);
	            stmt1.setBigDecimal(13, nota_saeb_mediap);
	            stmt1.setBigDecimal(14, ideb);
	            
	            stmt1.executeUpdate();
	            
		    }
				
		} catch (SQLException e) {
			System.out.println("Erro na conexão com o banco");
			e.printStackTrace();
		}
		

	}

	private static void insertCensoEscolar(){
		String csvCensoEscolar = "C:/Users/nasci/OneDrive/Área de Trabalho/Recuperacao Intermediaria/censo_por_escola.csv";
		

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
	        	
	            String[] fields = line.split(csvSeparator, -1);
	            
	            for(int i = 0; i < fields.length; i++) {	
	            	if(fields[i] == "" || fields[i] == null){
	            		fields[i] = "0";		
	            		System.out.println("teste" + i);
	            	}
	            }
	            
	            if (fields.length == 13) {
	            	String[] newFields = new String[fields.length + 1];
	            	
	            	for(int i = 0; i < fields.length; i++) {
	            		newFields[i] = fields[i];
	            	}
	            	
	            	newFields[newFields.length - 1] = "0";
	            	fields = newFields;
	            }
	            
		            String sql = "INSERT INTO censo_escolar (ano, uf, id_municipio, id_escola, rede, ensino,"
	            		+ " anos_escolares, taxa_aprovacao"
	            		+ ", indicador_rendimento, nota_saeb_mat, nota_saeb_pt, nota_saeb_mediap, ideb, projecao) VALUES "
	            		+ "(?, ?, ?, ?, ?, ? ,? ,? ,? ,? , ?, ?, ?, ?)";
		            
		            PreparedStatement stmt = conn.prepareStatement(sql);
		            
		            stmt.setInt(1, Integer.parseInt(fields[0]));
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
		            stmt.setBigDecimal(14, new BigDecimal(fields[13]));
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

	private static void insertMunicipios() {
		
		String csvMunicipios = "C:/Users/nasci/OneDrive/Área de Trabalho/Recuperacao Intermediaria/municipios.csv";
		
		try (Connection conn = DriverManager.getConnection(dbUrl, dbUser, dbPassword); 
	    	BufferedReader buffer = new BufferedReader(new FileReader(csvMunicipios))) {
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