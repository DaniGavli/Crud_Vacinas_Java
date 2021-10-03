
package jdbc.cdVacinasPrincipal.java;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import jdbc.cdVacinas.java.dao.ClienteDAO;
import jdbc.cdVacinas.java.dao.Dados_RelatoriosDAO;
import jdbc.cdVacinas.java.model.Cliente;
import jdbc.cdVacinas.java.model.UtilData;

/**
 *
 * @author Daniela
 */
public class TelaPrincipal extends javax.swing.JFrame {
 //DECLARAÇÃO DA VARIAVEL TIPO STRING QUE SERÁ USADA NO SWITCH CASE
     String modoNavegar;
     private final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
   
     
 //INSTANCIANDO UM NOVO ARRAY LIST DE USUARIOS
     List<Cliente> retorno = new ArrayList<>();
    
 //METODO VOID LOADTABLE() PARA A EXIBIR EM TELA OS DADOS DO CADASTRO EM UMA JTABLE
    public void LoadTableFun(){
        DefaultTableModel modelo = new DefaultTableModel(new Object[] {"Nome", "CPF", "Telefone", "Dose", "Endereco", "posto"," data_", "lab", "usu_log",    "lote", "lote2d", "usu_Atualiza"},0);
        
        //BUSCA DOS DADOS DO ARRAY LIST E INSERÇÃO NA JTABLE PARA EXIBIÇÃO EM TELA DOS DADOS CADASTRADOS.
        for(int i=0; i<retorno.size();i++){
            Object linha[] = new Object[]{retorno.get(i).getNome(),
                                           retorno.get(i).getCpf(),
                                           retorno.get(i).getTelefone(),
                                           retorno.get(i).getDose(),
                                           retorno.get(i).getEndereco(),
                                           retorno.get(i).getPosto(),
                                           retorno.get(i).getData(),
                                           retorno.get(i).getLab(),
                                           retorno.get(i).getUsu_log(),
                                           //retorno.get(i).getDt2Dose(),
                                           retorno.get(i).getLote(),
                                           retorno.get(i).getLote2d(),
                                           retorno.get(i).getUsu_Atualiza()};
                                           
            modelo.addRow(linha);
             }
       // DEFINIÇÃO DO TAMANHO DE EXIBIÇÃO DAS COLUNAS DA TABELA
        tbl_exibe.setModel(modelo);
        tbl_exibe.getColumnModel().getColumn(0).setPreferredWidth(80);
        tbl_exibe.getColumnModel().getColumn(1).setPreferredWidth(150);
        tbl_exibe.getColumnModel().getColumn(2).setPreferredWidth(150);
        tbl_exibe.getColumnModel().getColumn(3).setPreferredWidth(100);
        tbl_exibe.getColumnModel().getColumn(4).setPreferredWidth(100);
        tbl_exibe.getColumnModel().getColumn(5).setPreferredWidth(100);
        tbl_exibe.getColumnModel().getColumn(6).setPreferredWidth(80);
        tbl_exibe.getColumnModel().getColumn(7).setPreferredWidth(100);
        tbl_exibe.getColumnModel().getColumn(8).setPreferredWidth(100);
        tbl_exibe.getColumnModel().getColumn(9).setPreferredWidth(100);
        tbl_exibe.getColumnModel().getColumn(10).setPreferredWidth(100);
        tbl_exibe.getColumnModel().getColumn(11).setPreferredWidth(100);
    }

    /**
     * Creates new form NewJFrame
     */
    public TelaPrincipal() {
        initComponents();
        this.setLocationRelativeTo(null);
        modoNavegar = "Navegar";
        ManipulaInterface();
        DefaultTableModel modelo = (DefaultTableModel)tbl_exibe.getModel();
        tbl_exibe.setRowSorter(new TableRowSorter(modelo)); 
        readJTable();
        UsuAtual();
      
    }
    
//METODO QUE ADDICIONA OS DADOS NA TABELA USANDO O METODO LISTAR DA CLASSE usuarioDAO
    public void readJTable(){
        DefaultTableModel modelo = (DefaultTableModel) tbl_exibe.getModel();
        modelo.setNumRows(0);
        ClienteDAO pdao = new ClienteDAO();
        for(Cliente p: pdao.listar()){
            modelo.addRow(new Object[]{
             p.getId(),  
                p.getNome(), 
                p.getCpf(), 
                p.getTelefone(), 
                p.getDose(), 
                p.getEndereco(), 
                p.getPosto(), 
                p.getData(), 
                p.getLab(), 
                p.getUsu_log(),
                //p.getDt2Dose(),
                p.getUsu_Atualiza(),
                p.getLote(),
                p.getLote2d()
            });
            
        }
    }
 //METODO PARA PESQUISA COM PARAMETRO DO NOME DO USUARIO E O METODO pesqNome DE CONEXÃO COM O BD  DA CLASSE usuarioDAO
    
    public void pesqTblNome(String nome){
     DefaultTableModel modelo = (DefaultTableModel) tbl_exibe.getModel();
        modelo.setNumRows(0);
        ClienteDAO pdao = new ClienteDAO();
        for(Cliente p: pdao.pesqNome(nome)){
            modelo.addRow(new Object[]{
             p.getId(),  
                p.getNome(), 
                p.getCpf(), 
                p.getTelefone(), 
                p.getDose(), 
                p.getEndereco(), 
                p.getPosto(), 
                p.getData(), 
                p.getLab(), 
                p.getUsu_log(),
                p.getD22Dose2(),
                p.getUsu_Atualiza(),
                 p.getLote(),
                p.getLote2d()
            });  
        }  
    } 
 //METODO PARA PESQUISA  COM PARAMETRO A DOSE DE VACINA DO USUARIO E CHAMANDO O  METODO pesqDose DE CONEXÃO COM O BD  DA CLASSE usuarioDAO
     public void pesqTblDose(String dose){
     DefaultTableModel modelo = (DefaultTableModel) tbl_exibe.getModel();
        modelo.setNumRows(0);
        ClienteDAO pdao = new ClienteDAO();
        for(Cliente p: pdao.pesqDose(dose)){
            modelo.addRow(new Object[]{
             p.getId(),  
                p.getNome(), 
                p.getCpf(), 
                p.getTelefone(), 
                p.getDose(), 
                p.getEndereco(), 
                p.getPosto(), 
                p.getData(), 
                p.getLab(),
                p.getUsu_log(),
                p.getD22Dose2(),
                p.getUsu_Atualiza(),
                p.getLote(),
                p.getLote2d()
            });  
        }  
    } 
     
 //METODO PARA PESQUISA  COM PARAMETRO A DOSE DE VACINA DO USUARIO E CHAMANDO O  METODO pesqDose DE CONEXÃO COM O BD  DA CLASSE usuarioDAO
     public void pesqTblUsu(String usu){
     DefaultTableModel modelo = (DefaultTableModel) tbl_exibe.getModel();
        modelo.setNumRows(0);
        ClienteDAO pdao = new ClienteDAO();
        for(Cliente p: pdao.pesqUsu(usu)){
            modelo.addRow(new Object[]{
             p.getId(),  
                p.getNome(), 
                p.getCpf(), 
                p.getTelefone(), 
                p.getDose(), 
                p.getEndereco(), 
                p.getPosto(), 
                p.getData(), 
                p.getLab(),
                p.getUsu_log(),
                p.getD22Dose2(),
                p.getUsu_Atualiza(),
                p.getLote(),
                p.getLote2d()
            });  
        }  
    }
     
     //METODO ManipulaInterface PARA BLOQUEAR OS CAMPOS DE DASOS NA INTERFACE
     
     public void ManipulaInterface(){
        switch(modoNavegar){
            case "Navegar":
                txt_nome.setEnabled(false);
                txt_cpf.setEnabled(false);
                txt_tel.setEnabled(false);
                cb_dose.setEnabled(false);
                txt_end.setEnabled(false);
                txt_posto.setEnabled(false);
                txt_lote.setEnabled(false);
                txt_data.setEnabled(false);
                cb_lab.setEnabled(false);
                txt_data2D.setEnabled(false);
                btn_novo.setEnabled(true);
                btn_cadastrar.setEnabled(true);
                btn_atualizar.setEnabled(false);
                btn_Deletar.setEnabled(false);
                btn_cancelar.setEnabled(true);
                txt_lote2D.setEnabled(false);
                break;
            case "Novo":
                txt_nome.setEnabled(true);
                txt_cpf.setEnabled(true);
                txt_tel.setEnabled(true);
                cb_dose.setEnabled(true);
                txt_end.setEnabled(true);
                txt_posto.setEnabled(true);
                txt_lote.setEnabled(true);
                txt_data.setEnabled(true);
                cb_lab.setEnabled(true);
                txt_data2D.setEnabled(false);
                btn_novo.setEnabled(false);
                btn_cadastrar.setEnabled(true);
                btn_atualizar.setEnabled(false);
                btn_Deletar.setEnabled(false);
                btn_cancelar.setEnabled(true);
                txt_lote2D.setEnabled(true);
                break;
            case "Atualizar":
                txt_nome.setEnabled(true);
                txt_cpf.setEnabled(true);
                txt_tel.setEnabled(true);
                cb_dose.setEnabled(true);
                txt_end.setEnabled(true);
                txt_posto.setEnabled(true);
                txt_lote.setEnabled(false);
                txt_data.setEnabled(true);
                cb_lab.setEnabled(true);
                txt_data2D.setEnabled(true);
                btn_novo.setEnabled(true);
                btn_cadastrar.setEnabled(false);
                btn_atualizar.setEnabled(true);
                btn_Deletar.setEnabled(false);
                btn_cancelar.setEnabled(true);
                txt_lote2D.setEnabled(true);
                break;
            case "Excluir":
                txt_nome.setEnabled(false);
                txt_cpf.setEnabled(false);
                txt_tel.setEnabled(false);
                cb_dose.setEnabled(false);
                txt_end.setEnabled(false);
                txt_posto.setEnabled(false);
                txt_lote.setEnabled(false);
                txt_data.setEnabled(false);
                cb_lab.setEnabled(false);
                txt_data2D.setEnabled(false);
                btn_novo.setEnabled(true);
                btn_cadastrar.setEnabled(false);
                btn_atualizar.setEnabled(false);
                btn_Deletar.setEnabled(true);
                btn_cancelar.setEnabled(true);
                txt_lote2D.setEnabled(false);
                break;
                 case "Selecao":
                txt_nome.setEnabled(true);
                txt_cpf.setEnabled(true);
                txt_tel.setEnabled(true);
                cb_dose.setEnabled(true);
                txt_end.setEnabled(true);
                txt_posto.setEnabled(true);
                txt_lote.setEnabled(true);
                txt_data.setEnabled(true);
                cb_lab.setEnabled(true);
                txt_data2D.setEnabled(false);
                btn_novo.setEnabled(false);
                btn_cadastrar.setEnabled(false);
                btn_atualizar.setEnabled(true);
                btn_Deletar.setEnabled(true);
                btn_cancelar.setEnabled(true);
                txt_lote2D.setEnabled(true);
                break;
                 case "AltUsuario":
                txt_nome.setEnabled(false);
                txt_cpf.setEnabled(false);
                txt_tel.setEnabled(false);
                cb_dose.setEnabled(true);
                txt_end.setEnabled(false);
                txt_posto.setEnabled(true);
                txt_lote.setEnabled(true);
                txt_data.setEnabled(false);
                cb_lab.setEnabled(false);
                txt_data2D.setEnabled(true);
                btn_novo.setEnabled(false);
                btn_cadastrar.setEnabled(false);
                btn_atualizar.setEnabled(true);
                btn_Deletar.setEnabled(false);
                btn_cancelar.setEnabled(true);
                txt_lote2D.setEnabled(true);
                break;
            default: System.out.println("Modo Inválido");
        }
    }
  
// O METODO UsuAtual() SERVE PARA GRAVAR EM UMA STRING O USUARIO ATUAL, E PARA A EXIBIÇÃO DE BOAS VINDAS AO USUARIO LOGADO NO SISTEMA,
// O System.getProperty()É UMA VARIAVEL ESTATICA PADRAO ACESSIVEL EM QUALQUER PARTE DA APLICAÇÃO(AQUI BUSCO A STRING USUARIO PARA "SETAR" A INFO EM OUTROS METODOS
     public void UsuAtual(){  
         String usuario = System.getProperty("usuario",""); 
       lb_ola.setText("Olá, " + usuario+ "!");
     }
     
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        buttonGroup1 = new javax.swing.ButtonGroup();
        txt_pesquisar = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_exibe = new javax.swing.JTable();
        btn_buscar = new javax.swing.JButton();
        btnGraficos = new javax.swing.JButton();
        rbPesqNome = new javax.swing.JRadioButton();
        rbPesqDose = new javax.swing.JRadioButton();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        txt_posto = new javax.swing.JTextField();
        txt_cpf = new javax.swing.JFormattedTextField();
        txt_tel = new javax.swing.JFormattedTextField();
        cb_dose = new javax.swing.JComboBox<>();
        cb_lab = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        btn_cancelar = new javax.swing.JButton();
        btn_cadastrar = new javax.swing.JButton();
        btn_novo = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_Deletar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btn_atualizar = new javax.swing.JButton();
        txt_nome = new javax.swing.JTextField();
        txt_end = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_data = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        lb_prev2Dose = new javax.swing.JLabel();
        txt_lote = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txt_data2D = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txt_lote2D = new javax.swing.JTextField();
        btn_relPdf = new javax.swing.JButton();
        lb_ola = new javax.swing.JLabel();
        btnLogout = new javax.swing.JButton();
        rbPesqUsu = new javax.swing.JRadioButton();

        jFormattedTextField1.setText("jFormattedTextField1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        txt_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pesquisarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jLabel8.setText("Pesquisar");

        tbl_exibe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "NOME", "CPF", "TEL", "DOSE", "END", "POSTO", "DATA", "LAB", "USU", "USU_ALT", "LOTE 1", "LOTE 2"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tbl_exibe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_exibeMouseClicked(evt);
            }
        });
        tbl_exibe.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbl_exibeKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_exibe);

        btn_buscar.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        btn_buscar.setText("Buscar");
        btn_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_buscarActionPerformed(evt);
            }
        });

        btnGraficos.setBackground(new java.awt.Color(0, 255, 255));
        btnGraficos.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnGraficos.setText("Graficos");
        btnGraficos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGraficosActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbPesqNome);
        rbPesqNome.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbPesqNome.setText("Pesquisa por nome");
        rbPesqNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPesqNomeActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbPesqDose);
        rbPesqDose.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbPesqDose.setText("Pesquisa por dose aplicada");

        txt_posto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_postoActionPerformed(evt);
            }
        });

        try {
            txt_cpf.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("###.###.###-##")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        try {
            txt_tel.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##-#########")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        cb_dose.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cb_dose.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "1", "2", "UNICA" }));
        cb_dose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_doseActionPerformed(evt);
            }
        });

        cb_lab.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cb_lab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecione", "CORONAVAC", "JANSEN", "PFIZER", "OXFORD", "SINOVAC" }));
        cb_lab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_labActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Email");

        btn_cancelar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cancelar.setText("Cancelar");
        btn_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cancelarActionPerformed(evt);
            }
        });

        btn_cadastrar.setBackground(new java.awt.Color(255, 255, 255));
        btn_cadastrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_cadastrar.setText("Cadastrar");
        btn_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarActionPerformed(evt);
            }
        });

        btn_novo.setBackground(new java.awt.Color(255, 255, 255));
        btn_novo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_novo.setText("Novo");
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Data 1º D");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Laboratorio");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nome");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Posto");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("CPF");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Telefone");

        btn_Deletar.setBackground(new java.awt.Color(255, 255, 255));
        btn_Deletar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_Deletar.setText("Delete");
        btn_Deletar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeletarActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Dose");

        btn_atualizar.setBackground(new java.awt.Color(255, 255, 255));
        btn_atualizar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btn_atualizar.setText("Atualizar");
        btn_atualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_atualizarActionPerformed(evt);
            }
        });

        txt_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomeActionPerformed(evt);
            }
        });

        txt_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_endActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Sitka Text", 1, 30)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("        CADASTRO VACINAS ");

        txt_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_dataActionPerformed(evt);
            }
        });

        lb_prev2Dose.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lb_prev2Dose.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Lote");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Data 2º D");

        txt_data2D.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_data2DActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Lote 2D");

        jDesktopPane1.setLayer(txt_posto, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txt_cpf, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txt_tel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(cb_dose, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(cb_lab, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btn_cancelar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btn_cadastrar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btn_novo, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel6, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel7, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel9, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btn_Deletar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(btn_atualizar, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txt_nome, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txt_end, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel10, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txt_data, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel11, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(lb_prev2Dose, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txt_lote, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel12, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel13, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txt_data2D, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel14, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(txt_lote2D, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7)))
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(lb_prev2Dose, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_lote2D, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(147, 147, 147))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(67, 67, 67)
                                .addComponent(btn_atualizar)
                                .addGap(60, 60, 60)
                                .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(btn_cancelar))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(140, 140, 140)
                                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(64, 64, 64)
                                .addComponent(btn_cadastrar))
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cb_dose, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_posto, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_end, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txt_data2D, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cb_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txt_cpf, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_tel, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_end, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(29, 29, 29)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txt_posto, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cb_dose, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)
                    .addComponent(txt_lote, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel6)
                                    .addComponent(txt_data, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(txt_data2D, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel14)
                                    .addComponent(txt_lote2D, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(lb_prev2Dose, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cb_lab, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(154, 154, 154)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_atualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_Deletar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(207, 207, 207)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(653, Short.MAX_VALUE))
        );

        btn_relPdf.setBackground(new java.awt.Color(51, 255, 0));
        btn_relPdf.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btn_relPdf.setText("Relatorio PDF");
        btn_relPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_relPdfActionPerformed(evt);
            }
        });

        lb_ola.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        lb_ola.setForeground(new java.awt.Color(51, 51, 255));

        btnLogout.setBackground(new java.awt.Color(255, 51, 51));
        btnLogout.setText("Logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbPesqUsu);
        rbPesqUsu.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        rbPesqUsu.setText("Pesquisa por usuario");
        rbPesqUsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbPesqUsuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btn_buscar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(158, 158, 158)
                        .addComponent(lb_ola, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58)
                        .addComponent(btnLogout))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(142, 142, 142)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGraficos, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(btn_relPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(rbPesqNome)
                                .addGap(52, 52, 52)
                                .addComponent(rbPesqDose)
                                .addGap(31, 31, 31)
                                .addComponent(rbPesqUsu)))))
                .addContainerGap(266, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lb_ola, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLogout))
                .addGap(56, 56, 56)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGraficos, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_relPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbPesqNome)
                    .addComponent(rbPesqDose)
                    .addComponent(rbPesqUsu))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_buscar)
                    .addComponent(jLabel8))
                .addGap(54, 54, 54)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 583, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pesquisarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_pesquisarActionPerformed
//O BOTAO DELETAR BUSCA NA LINHA CLICADA OS DADOS DO CADASTRO, EXIBE NO FORMULARIO E DELETA CHAMANDO O METODO REMOVER DA CLASSE usuarioDAO
      //APOS LIMPA OS CAMPOS DE TEXTO DO FORMULARIO, USA O MODO NAVEGAR PARA BLOQUEAR OS CAMPOS NECESSARIOS
    
    private void btn_DeletarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeletarActionPerformed
 
        if(tbl_exibe.getSelectedRow()!= -1){
          
            Cliente p= new Cliente();
            ClienteDAO dao = new ClienteDAO();
            p.setId((int)tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),0));
            dao.remover(p);
            txt_nome.setText("");
            txt_cpf.setText("");
            txt_tel.setText("");
            cb_dose.getSelectedItem();
            txt_lote.setText("");
            txt_end.setText("");
            txt_posto.setText("");
            txt_data.setText("");
            txt_data2D.setText("");
            cb_lab.getSelectedIndex();
            txt_lote2D.setText("");
            readJTable();
            modoNavegar = "Atualizar";
            ManipulaInterface(); 
        
       
    }
    }//GEN-LAST:event_btn_DeletarActionPerformed

    private void btn_atualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_atualizarActionPerformed
       String novo = cb_dose.getSelectedItem().toString();
         String novoLab = cb_lab.getSelectedItem().toString();
          
        if(tbl_exibe.getSelectedRow()!= -1){
            
           Cliente p= new Cliente();
           ClienteDAO dao = new ClienteDAO();
            p.setNome(txt_nome.getText());
            p.setCpf(txt_cpf.getText());
            p.setTelefone(txt_tel.getText());
            p.setDose(novo);
            p.setLote(txt_lote.getText());
            p.setEndereco(txt_end.getText());
            p.setPosto(txt_posto.getText());
            p.setData(txt_data.getText());
            p.setLab(novoLab);
            p.setD22Dose2(txt_data2D.getText());
            p.setLote2d(txt_lote2D.getText());
            p.setId((int)tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),0));
           /*
           try {
               p.setDt2Dose(sdf.parse(txt_data2D.getText()));
               //p.setTeste(txt_data2D.getText()); OU ALTERAR a VARIAVEL STRING
           } catch (ParseException ex) {
               Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
           }
   */
            dao.alterar(p);
           
            txt_nome.setText("");
            txt_cpf.setText("");
            txt_tel.setText("");
            cb_dose.getSelectedItem();
            txt_lote.setText("");
            txt_end.setText("");
            txt_posto.setText("");
            txt_data.setText("");
            cb_lab.getSelectedItem();
            txt_data2D.setText("");
            txt_lote2D.setText("");
             modoNavegar = "Atualizar";
            ManipulaInterface(); 
             readJTable();
        
    }
    }//GEN-LAST:event_btn_atualizarActionPerformed

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
       
        String novo = cb_dose.getSelectedItem().toString();
        String novoLab = cb_lab.getSelectedItem().toString();
        
        Cliente p= new Cliente();
        ClienteDAO dao = new ClienteDAO();
        
        p.setNome(txt_nome.getText());
        p.setCpf(txt_cpf.getText());
        p.setTelefone(txt_tel.getText());
        p.setDose(novo);
        p.setLote(txt_lote.getText());
        p.setEndereco(txt_end.getText());
        p.setPosto(txt_posto.getText());
        p.setData(txt_data.getText());
        p.setLote2d(txt_lote2D.getText());
        p.setLab(novoLab);
        
        /*
       try {
           p.setDt2Dose(sdf.parse(txt_data2D.getText()));
               //p.setTeste(txt_data2D.getText()); OU ALTERAR a VARIAVEL STRING
           } catch (ParseException ex) {
               Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
           }
     */
       modoNavegar = "Navegar";
         ManipulaInterface();
        dao.inserir(p);
        txt_nome.setText("");
        txt_cpf.setText("");
        txt_tel.setText("");
        cb_dose.getSelectedItem();
        txt_lote.setText("");
        txt_end.setText("");
        txt_posto.setText("");
        //txt_data.setText(""); Fica inativo para nao lipar a data
        cb_lab.getSelectedItem();
        txt_lote2D.setText("");
         readJTable();
          
       
    }//GEN-LAST:event_btn_cadastrarActionPerformed
 // AQUI AO CLICAR EM UMA LINHA DA TABELA, O USUARIO SOMENTE PODERA FAZER ALTERACOES NOS REGISTROS FEITOS EM SEU PROPRIO LOGIN
    
    private void tbl_exibeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_exibeMouseClicked
String usuario = System.getProperty("usuario",""); 
        String x= (tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),9).toString());
          System.out.println(x);
          System.out.println(usuario);
        //VERIFICA SE O USUARIO ATUAL É O MESMO DA LINHA SELECIONADA
        
        if(x.equals(usuario)){   
        if(tbl_exibe.getSelectedRow() != -1){
            txt_nome.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),1).toString());
            txt_cpf.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),2).toString());
            txt_tel.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),3).toString());
            cb_dose.setSelectedItem(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),4).toString());
            txt_end.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),5).toString());
            txt_posto.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),6).toString());
            txt_data.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),7).toString());
            cb_lab.setSelectedItem(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),8).toString());
            txt_lote.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),11).toString());
            txt_lote2D.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),12).toString());
            //txt_lote2D.setText("");
             modoNavegar = "Selecao";
            ManipulaInterface();
        
        }
       }else{
                UtilData utilidades = new UtilData();
                utilidades.DataAtualCalendar();
                String dt2Dose =   utilidades.DataAtualCalendar();
                txt_data2D.setText(dt2Dose);
            txt_nome.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),1).toString());
            txt_cpf.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),2).toString());
            txt_tel.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),3).toString());
            cb_dose.setSelectedItem(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),4).toString());
            txt_end.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),5).toString());
            txt_posto.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),6).toString());
            txt_data.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),7).toString());
             cb_lab.setSelectedItem(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),8).toString());
             txt_lote.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),11).toString());
             txt_lote2D.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),12).toString());  
             //txt_lote2D.setText("");
             modoNavegar ="AltUsuario";
             ManipulaInterface();
            JOptionPane.showMessageDialog(rootPane, "Você pode apenas registrar a 2º dose da vacina");
        }
        
       
    }//GEN-LAST:event_tbl_exibeMouseClicked
//PARA PERCORRER A TABELA PELO TECLADO
    
    private void tbl_exibeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_exibeKeyReleased
        //PARA PERCORRER A TABELA PELO TECLADO
        if(tbl_exibe.getSelectedRow() != -1){
            txt_nome.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),1).toString());
            txt_cpf.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),2).toString());
            txt_tel.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),3).toString());
            cb_dose.setSelectedItem(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),4).toString());
            txt_end.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),5).toString());
            txt_posto.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),6).toString());
            txt_data.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),7).toString());
            cb_lab.setSelectedItem(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),8).toString());
             txt_lote.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),11).toString());
              txt_lote2D.setText(tbl_exibe.getValueAt(tbl_exibe.getSelectedRow(),12).toString());  
              txt_lote2D.setText("");
        }
    }//GEN-LAST:event_tbl_exibeKeyReleased
//FAZ UMA SELEÇÃO DE QUAL TIPO DE DADO O USUARIO PREFERE FAZER A BUSCA, SE POR DOSE OU NOME, E CHAMA O METODO ADEQUADO. RADIO BUTTOM
    
    private void btn_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_buscarActionPerformed
        if(rbPesqNome.isSelected())
           pesqTblNome(txt_pesquisar.getText());  
         else if(rbPesqDose.isSelected())
          pesqTblDose(txt_pesquisar.getText());
          else if(rbPesqUsu.isSelected())
          pesqTblUsu(txt_pesquisar.getText());  
         else{
             JOptionPane.showMessageDialog(null, "Voce deve selecionar o tipo de pesquisa:");
         }  
    }//GEN-LAST:event_btn_buscarActionPerformed

    private void txt_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomeActionPerformed

    private void txt_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_endActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_endActionPerformed

    private void txt_postoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_postoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_postoActionPerformed
 //CHAMA OS METOS PARA EMITIR OS GRAFICOS
    
    private void btnGraficosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGraficosActionPerformed
       Dados_RelatoriosDAO rel = new Dados_RelatoriosDAO();
       rel.TipoDosesPorData();
       rel.teste();
       rel.GraficoTipoDoseUsuLogado();
       
    }//GEN-LAST:event_btnGraficosActionPerformed

    private void cb_doseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_doseActionPerformed
       
     
    }//GEN-LAST:event_cb_doseActionPerformed

    private void rbPesqNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPesqNomeActionPerformed
       
    }//GEN-LAST:event_rbPesqNomeActionPerformed
//AO CANCELAR, OS CAMPOS DE TEXTO SAO LIMPOS E O MODO NAVEGAR BLOQUEIA OS CAMPOS NECESSARIOS
    
    private void btn_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cancelarActionPerformed
        
        modoNavegar = "Navegar";
         ManipulaInterface();
         txt_nome.setText("");
         txt_cpf.setText("");
         txt_tel.setText("");
         cb_dose.getSelectedItem();
         txt_lote.setText("");
         txt_end.setText("");
         txt_posto.setText("");
         txt_data.setText("");
         cb_lab.getSelectedItem();
         txt_data2D.setText("");
         txt_lote2D.setText("");
    }//GEN-LAST:event_btn_cancelarActionPerformed
 // O BOTAO NOVO LIBERA OS CAMPOS PARA DIGITAR E BOTOES, E INSERE A DATA ATUAL NO CAMPO DATA
    
    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
               UtilData utilidades = new UtilData();
                utilidades.DataAtualCalendar();
                String x =   utilidades.DataAtualCalendar();
                txt_data.setText(x);
                txt_lote2D.setText("NA");
                modoNavegar = "Novo";
                ManipulaInterface();
               
    }//GEN-LAST:event_btn_novoActionPerformed
//instancia uma nova classe rel e chama o metodo de gerar novo relatorioo de pdf
    
    private void btn_relPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_relPdfActionPerformed
       Dados_RelatoriosDAO rel = new Dados_RelatoriosDAO();
        rel.gerarRelatorio();
    }//GEN-LAST:event_btn_relPdfActionPerformed
 //AO CLICAR EM LOGOUT O USUARIO VOLTA A TELA DE LOGIN ONDE PRECISA VALIDAR SENHA E USUARIO NOVAMENTE  
    
    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
       dispose(); 
            Login logout= new Login();
             logout.setVisible(true);
              //
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void txt_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_dataActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_dataActionPerformed
//VERIFICA QUAL VACINA FOI SELECIONADA NA CAIXA DE SELEÇÃO E CALCULA A DATA DA 2 DOSE
    
    private void cb_labActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_labActionPerformed
             String valor = cb_lab.getSelectedItem().toString(); 
             String strData = txt_data.getText();
             Calendar cal = Calendar.getInstance();
             
         try {
             cal.setTime(sdf.parse(strData));
         } catch (ParseException ex) {
             Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
         }
        
       String formatted;
        switch (valor) {
            case "CORONAVAC":
                cal.add(Calendar.DATE, +14);
                formatted = sdf.format(cal.getTime()); 
                lb_prev2Dose.setText("2º DOSE EM: "+ formatted);
                break;
            case "PFIZER":
                cal.add(Calendar.DATE, +28);
               formatted = sdf.format(cal.getTime()); 
                lb_prev2Dose.setText("2º DOSE EM: "+ formatted);
                break;
            case "OXFORD":
                cal.add(Calendar.DATE, +90);
                formatted = sdf.format(cal.getTime()); 
                lb_prev2Dose.setText("2º DOSE EM: "+ formatted);
                break;
            case "SINOVAC":
                cal.add(Calendar.DATE, +21);
                formatted = sdf.format(cal.getTime()); 
                lb_prev2Dose.setText("2º DOSE EM: "+ formatted);
                break;
            default:
                cal.add(Calendar.DATE, +0);
                formatted = sdf.format(cal.getTime()); 
                lb_prev2Dose.setText("DOSE UNICA -"+ formatted);
                
                break;
        }
 
    }//GEN-LAST:event_cb_labActionPerformed

    private void txt_data2DActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_data2DActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_data2DActionPerformed

    private void rbPesqUsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbPesqUsuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbPesqUsuActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws ClassNotFoundException, SQLException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new TelaPrincipal().setVisible(true);
                
            }
        });
        
       
       // cli.convertData();
      } 
        
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGraficos;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btn_Deletar;
    private javax.swing.JButton btn_atualizar;
    private javax.swing.JButton btn_buscar;
    private javax.swing.JButton btn_cadastrar;
    private javax.swing.JButton btn_cancelar;
    private javax.swing.JButton btn_novo;
    private javax.swing.JButton btn_relPdf;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cb_dose;
    private javax.swing.JComboBox<String> cb_lab;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lb_ola;
    private javax.swing.JLabel lb_prev2Dose;
    private javax.swing.JRadioButton rbPesqDose;
    private javax.swing.JRadioButton rbPesqNome;
    private javax.swing.JRadioButton rbPesqUsu;
    private javax.swing.JTable tbl_exibe;
    private javax.swing.JFormattedTextField txt_cpf;
    private javax.swing.JTextField txt_data;
    private javax.swing.JTextField txt_data2D;
    private javax.swing.JTextField txt_end;
    private javax.swing.JTextField txt_lote;
    private javax.swing.JTextField txt_lote2D;
    private javax.swing.JTextField txt_nome;
    private javax.swing.JTextField txt_pesquisar;
    private javax.swing.JTextField txt_posto;
    private javax.swing.JFormattedTextField txt_tel;
    // End of variables declaration//GEN-END:variables
}
