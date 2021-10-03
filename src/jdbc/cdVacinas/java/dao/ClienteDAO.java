package jdbc.cdVacinas.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdbc.cdVacinas.java.model.Cliente;


//CONEXÃO COM O BD 
public class ClienteDAO {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private Connection connection;
    
    public ClienteDAO() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            String DATABASE_URL = "jdbc:derby://localhost:1527/agenda_vacina";
            String usuario = "root";
            String senha = "123";
            this.connection = DriverManager.getConnection(DATABASE_URL, usuario, senha);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    // O METODO LISTAR FAZ UM SELECT DE TODOS OS DADOS DA BD E ADDICIONA EM UM ARRAY LIST (O RETURN É A LISTA DE CLIENTES)
    public List<Cliente> listar() {
        
        String sql = "SELECT * FROM agenda_vacina2";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Cliente vac = new Cliente();
               vac.setId(resultado.getInt("id"));
                vac.setNome(resultado.getString("nome"));
                vac.setCpf(resultado.getString("cpf"));
                vac.setTelefone(resultado.getString("telefone"));
                vac.setDose(resultado.getString("Dose"));
                vac.setLote(resultado.getString("lote"));
                vac.setEndereco(resultado.getString("Endereco"));
                vac.setPosto(resultado.getString("Posto"));
                vac.setData(resultado.getString("data_"));
                vac.setLab(resultado.getString("Lab"));
                vac.setUsu_log(resultado.getString("usu_log"));
                vac.setUsu_Atualiza(resultado.getString("usu_Atualiza"));
                vac.setD22Dose2(resultado.getString("dt2dose"));
                vac.setLote2d(resultado.getString("lote2d"));
                retorno.add(vac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
     
    //O METODO INSERIR USA COMO PARAMETRO UM TIPO DA CLASSE USUARIO
    public boolean inserir(Cliente t) {
        String usuario = System.getProperty("usuario",""); 
        String sql = "INSERT INTO agenda_vacina2(nome, cpf, telefone, dose,lote, endereco, posto, data_, lab, usu_log, dt2Dose, lote2d) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, t.getNome());
            stmt.setString(2, t.getCpf());
            stmt.setString(3, t.getTelefone());
            stmt.setString(4, t.getDose());
            stmt.setString(5, t.getLote());
            stmt.setString(6, t.getEndereco());
            stmt.setString(7, t.getPosto());
            stmt.setString(8, t.getData());
            stmt.setString(9, t.getLab());
            stmt.setString(10, usuario);
            stmt.setString(11, t.getD22Dose2());
            stmt.setString(12, t.getLote2d());
           
             
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Houve um erro na inserção do cadastro");
            return false;
        }
    }

    //O METODO ALTERAR, USA COMO TIPO DE DADOS UM NEW USUARIO, E FAZ UM UPDATE DAS INFORMACOES
    public boolean alterar(Cliente p) {
         String usuario = System.getProperty("usuario","");
        String sql = "UPDATE agenda_vacina2 SET nome=?, cpf=?, telefone=?, dose=?,lote=?, endereco=?, posto=?, data_=?, lab=?,usu_atualiza=? , dt2Dose=?, lote2d=? WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getNome());
            stmt.setString(2, p.getCpf());
            stmt.setString(3, p.getTelefone());
            stmt.setString(4, p.getDose());
            stmt.setString(5, p.getLote());
            stmt.setString(6, p.getEndereco());
            stmt.setString(7, p.getPosto());
            stmt.setString(8, p.getData());
            stmt.setString(9, p.getLab());
            stmt.setString(10, usuario);
             stmt.setString(11, p.getD22Dose2());
             stmt.setString(12, p.getLote2d());
           // stmt.setString(10, sdf.format(p.getDt2Dose()));
           // System.out.println(sdf.format(p.getDt2Dose()));
            stmt.setInt(13, p.getId());
           
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Houve um erro na alteração");
            return false;
        }
    }

    //METODO REMOVER, TEM COMO PARAMETRO UM TIPO USUARIO, E O DELETE DA LINHA É FEITO PELA COMPARAÇÃO DO ID
    public boolean remover(Cliente t) {
        String sql = "DELETE FROM agenda_vacina2 WHERE id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, t.getId());
         
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Houve um erro ao excluir o registro");
            return false;
        }
    }
    
   // O METODO pesqNome, USA COMO PARAMETRO UMA STRING NOME, 
    //E A BUSCA É FEITA PELA COMPARAÇÃO COM OS DADOS DA COLUNA NOME NO BD, E INSERE NUM ARRAYLIST
    
    public List <Cliente> pesqNome (String nome ){
        String sql = "SELECT * FROM agenda_vacina2 WHERE nome LIKE?";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
          
            stmt.setString(1,"%"+nome+"%");
             ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                Cliente vac = new Cliente();
                vac.setId(resultado.getInt("id"));
                vac.setNome(resultado.getString("nome"));
                vac.setCpf(resultado.getString("cpf"));
                vac.setTelefone(resultado.getString("telefone"));
                vac.setDose(resultado.getString("Dose"));
                vac.setLote(resultado.getString("lote"));
                vac.setEndereco(resultado.getString("Endereco"));
                vac.setPosto(resultado.getString("Posto"));
                vac.setData(resultado.getString("data_"));
                vac.setLab(resultado.getString("Lab"));
                vac.setUsu_log(resultado.getString("usu_log"));
                vac.setLote2d(resultado.getString("lote2d"));
                retorno.add(vac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Houve um erro na colsulta");
        }
        return retorno;
    }
    
    // O METODO pesqNome, USA COMO PARAMETRO UMA STRING , 
    //E A BUSCA É FEITA PELA COMPARAÇÃO COM OS DADOS DA COLUNA DOSE NO BD, E INSERE NUM ARRAYLIST
    
      public List <Cliente> pesqDose (String dose ){
        String sql = "SELECT * FROM agenda_vacina2 WHERE dose LIKE?";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
          
            stmt.setString(1,"%"+dose+"%");
             ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                Cliente vac = new Cliente();
                vac.setId(resultado.getInt("id"));
                vac.setNome(resultado.getString("nome"));
                vac.setCpf(resultado.getString("cpf"));
                vac.setTelefone(resultado.getString("telefone"));
                vac.setDose(resultado.getString("Dose"));
                vac.setLote(resultado.getString("lote"));
                vac.setEndereco(resultado.getString("Endereco"));
                vac.setPosto(resultado.getString("Posto"));
                vac.setData(resultado.getString("data_"));
                vac.setLab(resultado.getString("Lab"));
                vac.setUsu_log(resultado.getString("usu_log"));
                vac.setLote2d(resultado.getString("lote2d"));
                retorno.add(vac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Houve um erro na colsulta");
        }
        return retorno;
    }
      // O METODO pesqNome, USA COMO PARAMETRO UMA STRING , 
    //E A BUSCA É FEITA PELA COMPARAÇÃO COM OS DADOS DA COLUNA DOSE NO BD, E INSERE NUM ARRAYLIST
    
      public List <Cliente> pesqUsu (String usu ){
         String sql = "SELECT * FROM agenda_vacina2 WHERE usu_log LIKE?";
        List<Cliente> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
          
            stmt.setString(1,"%"+usu+"%");
             ResultSet resultado = stmt.executeQuery();
            
            while (resultado.next()) {
                Cliente vac = new Cliente();
                vac.setId(resultado.getInt("id"));
                vac.setNome(resultado.getString("nome"));
                vac.setCpf(resultado.getString("cpf"));
                vac.setTelefone(resultado.getString("telefone"));
                vac.setDose(resultado.getString("Dose"));
                vac.setLote(resultado.getString("lote"));
                vac.setEndereco(resultado.getString("Endereco"));
                vac.setPosto(resultado.getString("Posto"));
                vac.setData(resultado.getString("data_"));
                vac.setLab(resultado.getString("Lab"));
                vac.setUsu_log(resultado.getString("usu_log"));
                vac.setLote2d(resultado.getString("lote2d"));
                retorno.add(vac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
              JOptionPane.showMessageDialog(null, "Houve um erro na colsulta");
        }
        return retorno;
    }
     
   
    

}
 

