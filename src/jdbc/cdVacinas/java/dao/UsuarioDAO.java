
package jdbc.cdVacinas.java.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdbc.cdVacinas.java.model.Usuario;

/**
 *
 * @author Daniela
 */
public class UsuarioDAO {
    private Connection connection;
    
    //CONEXAO COM A TABELA LOGIN
    public UsuarioDAO() {
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
    
    //METODO BOOLEAN QUE BUSCA NO BD O LOGIN E SENHA INFORMADOS COMO PARAMETRO, E COMPARA SE SAO IGUAIS.
    public boolean checarSenha(String login, String senha){
          String sql = "SELECT senha, usuario FROM login WHERE usuario like?";
          try{ 
              Usuario s = new Usuario(); 
          PreparedStatement stmt = connection.prepareStatement(sql); 
          stmt.setString(1,"%"+login+"%");
          ResultSet rs = stmt.executeQuery(); 
          if(rs.next()){ 
              s.setUsuario(rs.getString("usuario")); 
              s.setSenha(rs.getString("senha"));
              if(senha.equals(rs.getString("senha"))){
                     return true;    
              }else{
                  //JOptionPane.showMessageDialog(null, "senha nao bateu " ); 
              }
          } 
          //stmt.close(); 
         
          } catch (SQLException e){
              JOptionPane.showMessageDialog(null, "Erro de conexão com o BD"); 
          }
          return false;
      }
     
    
    }
    

