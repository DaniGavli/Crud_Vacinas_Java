
package jdbc.cdVacinas.java.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Daniela
 */
public class UtilData {
    
    /**
     *
     * @return
     */
    //INSERIR A DATA ATUAL
    public String DataAtualCalendar(){
     
                Calendar c = Calendar.getInstance();
                SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                String formatted = format1.format(c.getTime());
                
                
      return  formatted;
    }
 
    //O METODO DEVERIA VERIFICAR A DATA ATUAL DE APLICAÇÃO DA PRIMEIRA DOSE
    //E INSERIR EM OUTRO CAMPO A DATA PREVISTA DE 2 DOSE DE ACORDO COM O LABORATORIO DA VACINA ****
   public void dataSegDose(String valor){
        Calendar c = Calendar.getInstance();
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
      
        switch (valor) {
            case "CORONAVAC":
                c.add(Calendar.DATE, +14);
                break;
            case "PFIZER":
                c.add(Calendar.DATE, +28);
                break;
            case "OXFORD":
                c.add(Calendar.DATE, +90);
                break;
            case "SINOVAC":
                c.add(Calendar.DATE, +21);
                break;
            default:
                c.add(Calendar.DATE, +0);
                JOptionPane.showMessageDialog(null, "DOSE UNICA");
                break;
        }
     
   }    
    
}

