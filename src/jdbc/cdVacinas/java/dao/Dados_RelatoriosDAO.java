
package jdbc.cdVacinas.java.dao;

import com.itextpdf.text.BaseColor;
import static com.itextpdf.text.BaseColor.GRAY;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import static com.itextpdf.text.PageSize.A4;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import jdbc.cdVacinas.java.model.Cliente;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.util.SortOrder;

/**
 *
 * @author Daniela
 */
public class Dados_RelatoriosDAO {
    //CONEXAO COM BANCO DE DADOS
     private Connection connection;
    
    public Dados_RelatoriosDAO() {
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
    
    
      //O METODO SELECIONA TODOS OS DADOS DA TABELA agenda_vacina E GERA UM GRAFICO COM O USO DA B JFRECHART
    public void TipoDosesPorData(){
    int val;
    try{
        //SELECIONA OS DADOS DA TABELA E EXECUTA O METODO 
        
        String sql =  "SELECT * from agenda_vacina2";
        PreparedStatement stmt = connection.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        //INSTANCIA UM NOVO CARREGAMENTO DOS DADOS COM O DefaultCategoryDataset
        DefaultCategoryDataset ddataset = new DefaultCategoryDataset();
         
        while (rs.next()) {
            //CONTA O TOTAL DE LINHAS DA TABELA
            val=ddataset.getRowCount();
            
            //ADD AO DefaultCategoryDataset AS INFORMACOES A EXIBIR
            ddataset.setValue(new Double(val), rs.getString("dose"), rs.getString("data_"));
        }
    
         //CRIA O TIPO DE GRAFICO- BARRAS
         JFreeChart chart = ChartFactory.createBarChart3D("Tipos de doses por data", "Data", "Aplicações", ddataset);
         //CRIA O TITULO E DA COR
        chart.getTitle().setPaint(Color.RED);
          //CRIA A ESTRUTURA DO GRAFICO 
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.BLUE);
        ChartFrame frame2 = new ChartFrame("Vacinados", chart);
        //DEIXA O GRAFICO VISIBLE
        frame2.setVisible(true);
        //ESTIPULA O TAMANHO DO GRAFICO
        frame2.setSize(900,700);
        }
     catch(Exception e)
    {
        //MENSAGEM DE ERRO
       JOptionPane.showMessageDialog(null, "Erro na criação do grafico");
    }
}

    public void teste(){
        try{ 
         String sql = "SELECT * from agenda_vacina2  ";
         PreparedStatement stmt = connection.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery();
         DefaultCategoryDataset ddataset = new DefaultCategoryDataset();
            int val;
           while (rs.next()) {
            //CONTA O TOTAL DE LINHAS DA TABELA
           val=ddataset.getRowCount();

            //ADD AO DefaultCategoryDataset AS INFORMACOES A EXIBIR
          //  ddataset.setValue(new Double(7), rs.getString("dose"), rs.getString("data_"));
            ddataset.setValue(val,  rs.getString("dose"), rs.getString("lab"));
        }
         JFreeChart chart = ChartFactory.createLineChart("sql", "data_", "lab", ddataset, PlotOrientation.VERTICAL, false, true,true); 
         BarRenderer renderer =null;
         Locale.Category plot=null;
         renderer= new BarRenderer();
         ChartFrame frame = new ChartFrame("sql", chart);
         frame.setVisible(true);
         frame.setSize(900, 650);
         frame.setLocation(90, 20);
         
        
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "O grafico teste não pode ser gerado");

        }    
}
///*
//METODO CRIA UM GRAFICO PIZZA E GERA EM PNG
    public void test3() {
       try {
        
   String sql = "SELECT * from agenda_vacina2 WHERE dose LIKE '2%'";
      PreparedStatement stmt = connection.prepareStatement(sql);
       ResultSet rs = stmt.executeQuery();
        DefaultPieDataset ddataset = new DefaultPieDataset();
        while (rs.next()) { 
            ddataset.setValue(rs.getString("lab"), Integer.parseInt(rs.getString("dose")));
        } 
        JFreeChart chart= ChartFactory.createPieChart("Grafico de Doses por vacinas", ddataset, true, true, false);
        
        
        File f=new File("Grafico png");
        ChartUtilities.saveChartAsPNG(f, chart, 560, 380);
        
    }catch(Exception e){
       JOptionPane.showMessageDialog(null, "O grafico não pode ser gerado");
   
   }
    }
//*/
    
    //GERA UM GRAFICO DE PIZZA EXIBINDO DOSES DA VACINA QUE FORAM APLICADAS EM PRIMEIRA DOSE
    public void GraficoTipoDoseUsuLogado() {
        String usuario = System.getProperty("usuario",""); 
       try {
        
    String sql = "SELECT * from agenda_vacina2 WHERE usu_log LIKE?";
    PreparedStatement stmt = connection.prepareStatement(sql);
    stmt.setString(1,"%"+usuario+"%");
    ResultSet rs = stmt.executeQuery();
    DefaultPieDataset ddataset = new DefaultPieDataset();
        while (rs.next()) { 
           ddataset.setValue(rs.getString("dose"), Integer.parseInt(rs.getString("dose")));
           ddataset.sortByValues(SortOrder.ASCENDING);
            //ddataset.setValue(rs.getString("data_"),rs.getInt("id") );
        } 
        JFreeChart chart= ChartFactory.createPieChart("Grafico de Doses por vacinas da "+usuario, ddataset, true, true, false);
        PiePlot g_pizza = (PiePlot) chart.getPlot();
       //g_pizza.setForegroundAlpha(TOP_ALIGNMENT);
        ChartFrame quadro = new ChartFrame("Grafico", chart);
        quadro.setVisible(true);
        quadro.setSize(450,500);
    
        
    }catch(Exception e){
       JOptionPane.showMessageDialog(null, "Erro ao gerar grafico ", "Database Error", JOptionPane.ERROR_MESSAGE);
   
   }
    }
 
    //METODO GERA UM RELATORIO EM PDF SALVO NA PASTA DOWNLOAD, COM TODOS OS DADOS DA TABELA DE CADASTRO - BIBLIOTECA ITEXT
    public List <Cliente>  gerarRelatorio(){
        
        //INSTANCIA UM NOVO DOCUMENTO DO TIPO ITEXT E FORMATAÇÃO DE PAGINA
             Document document = new Document();
             Font f = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.BLUE);
             Font f1 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.BLACK);
             Font f2 = new Font(Font.FontFamily.HELVETICA, 8, Font.BOLD, BaseColor.WHITE);
             document.setPageSize(A4.rotate());
            
         //CONEXÃO AO BD E INSERÇÃO DOS DADOS BUSCADOS EM UM ARRAY LIST
             List<Cliente> listp = new ArrayList<>();
             String sql = "SELECT * FROM agenda_vacina2";
          
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
                vac.setEndereco(resultado.getString("endereco"));
                vac.setPosto(resultado.getString("Posto"));
                vac.setData(resultado.getString("data_"));
                vac.setLab(resultado.getString("Lab"));
                vac.setUsu_log(resultado.getString("usu_log"));
                listp.add(vac);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         //List<Cliente> retorno = new ArrayList<>();
         //VARIAVEL PARA LOCAL ONDE O PDF SERÁ SALVO
        String arqPdf = "C:\\Users\\Daniela\\Desktop\\ESTATISTICA\\PDF_REL.pdf";
        
        try{
           PdfWriter.getInstance(document, new FileOutputStream(arqPdf)); 
           document.open();

           Paragraph p = new Paragraph("Relatorio PDF", f);
           p.setAlignment(1);
           document.add(p);
           
           p = new Paragraph(" ", f);
           document.add(p);
           
            PdfPTable table = new PdfPTable(new float[] { 0.05f, 0.2f, 0.2f, 0.2f, 0.1f, 0.34f, 0.14f, 0.15f, 0.2f, 0.1f  });
           //PdfPTable table = new  PdfPTable(10);
            table.setWidthPercentage(100.0f);
            table.setHorizontalAlignment(Element.ALIGN_RIGHT);
            
            
           PdfPCell cel1 = new PdfPCell(new Paragraph("ID", f2));
           PdfPCell cel2 = new PdfPCell(new Paragraph("NOME", f2));
           PdfPCell cel3 = new PdfPCell(new Paragraph("CPF", f2));
           PdfPCell cel4 = new PdfPCell(new Paragraph("FONE", f2));
           PdfPCell cel5 = new PdfPCell(new Paragraph("DOSE", f2));
           PdfPCell cel6 = new PdfPCell(new Paragraph("EMAIL", f2));
           PdfPCell cel7 = new PdfPCell(new Paragraph("POSTO", f2));
           PdfPCell cel8 = new PdfPCell(new Paragraph("DATA", f2));
           PdfPCell cel9 = new PdfPCell(new Paragraph("LAB", f2));
           PdfPCell cel10 = new PdfPCell(new Paragraph("USU", f2));
           
           cel1.setBackgroundColor(GRAY); 
           cel2.setBackgroundColor(GRAY); 
           cel3.setBackgroundColor(GRAY); 
           cel4.setBackgroundColor(GRAY); 
           cel5.setBackgroundColor(GRAY); 
           cel6.setBackgroundColor(GRAY); 
           cel7.setBackgroundColor(GRAY); 
           cel8.setBackgroundColor(GRAY); 
           cel9.setBackgroundColor(GRAY); 
           cel10.setBackgroundColor(GRAY); 
           
                    
           table.addCell(cel1);
           table.addCell(cel2);
           table.addCell(cel3);
           table.addCell(cel4);
           table.addCell(cel5);
           table.addCell(cel6);
           table.addCell(cel7);
           table.addCell(cel8);
           table.addCell(cel9);
           table.addCell(cel10);
           
           //OS DADOS DO ARRAYLIST SAO ADD NAS CELULAS  COM UM FOR EACH
        
           for(Cliente dados: listp){
           cel1 = new PdfPCell(new Paragraph(dados.getId()+"", f1));
           cel2 = new PdfPCell(new Paragraph(dados.getNome(), f1));
           cel3 = new PdfPCell(new Paragraph(dados.getCpf(), f1));
           cel4 = new PdfPCell(new Paragraph(dados.getTelefone(), f1));
           cel5 = new PdfPCell(new Paragraph(dados.getDose(), f1));
           cel6 = new PdfPCell(new Paragraph(dados.getEndereco(), f1));
           cel7 = new PdfPCell(new Paragraph(dados.getPosto(), f1));
           cel8 = new PdfPCell(new Paragraph(dados.getData(), f1));
           cel9 = new PdfPCell(new Paragraph(dados.getLab(), f1));
           cel10 = new PdfPCell(new Paragraph(dados.getUsu_log(), f1));
           
           table.addCell(cel1);
           table.addCell(cel2);
           table.addCell(cel3);
           table.addCell(cel4);
           table.addCell(cel5);
           table.addCell(cel6);
           table.addCell(cel7);
           table.addCell(cel8);
           table.addCell(cel9);
           table.addCell(cel10);
           }
           document.add(table);
           document.close();
           Desktop.getDesktop().open(new File(arqPdf));
           
          
        
        }catch (Exception e){
     
    }  
        return listp;  
    }
 
}
