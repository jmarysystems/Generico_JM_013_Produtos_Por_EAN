
package estabelecimento;

import br.com.jmary.home.Home;
import br.com.jmary.home.imagens.Imagens_Internas;
import br.com.jmary.home.jpa.DAOGenericoJPA;
import br.com.jmary.home.jpa.JPAUtil;
import br.com.jmary.utilidades.Exportando;
import javax.swing.ImageIcon;
import br.com.jmary.utilidades.JOPM;
import br.com.jmary.utilidades.PopupMenu_Colar;
import home_controle_menus_norte.imagens.Imagens_Menu_Norte;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.persistence.Query;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import estabelecimento_beans.Estabelecimento;
import estabelecimento_visualizador_imagens_banco_de_dados.Estabelecimento_Visualizador_De_Imagens;
import login_do_sistema.Login;
import usuarios_do_sistema_beans.UsuarioSistema;

/**
 *
 * @author AnaMariana
 */
public class Estabelecimento_02_Cadastrar_Visualizar extends javax.swing.JPanel {
    
    Home Home;
    String status_cadastro;
    Estabelecimento Estabelecimento_Recebido;
    UsuarioSistema UsuarioSistema_Recebido;
    JTabbedPane JTabbedPane_Recebido;
    PopupMenu_Colar PopupMenu_Colar;
    
    public List<String> lista_de_Endereco_imagemExterna_ = new ArrayList<>();
    
    /** Creates new form SombraVendas
     * @param Home2
     * @param JTabbedPane_Recebido2
     * @param status_cadastro2 */
    public Estabelecimento_02_Cadastrar_Visualizar( Home Home2, JTabbedPane JTabbedPane_Recebido2, String status_cadastro2 ) {
        initComponents();
        
        Home = Home2;  
        status_cadastro = status_cadastro2;
        JTabbedPane_Recebido = JTabbedPane_Recebido2;
        setar_Dados_Iniciais();
        
    }
    
    public Estabelecimento_02_Cadastrar_Visualizar( Home Home2, JTabbedPane JTabbedPane_Recebido2, String status_cadastro2, 
            Estabelecimento Estabelecimento_Recebido2, UsuarioSistema UsuarioSistema_Recebido2 ) {
        initComponents();
        
        Home = Home2;  
        status_cadastro = status_cadastro2;
        Estabelecimento_Recebido = Estabelecimento_Recebido2;
        UsuarioSistema_Recebido = UsuarioSistema_Recebido2;
        JTabbedPane_Recebido = JTabbedPane_Recebido2;
        
        setar_Dados_Iniciais();
    }
    
    private void setar_Dados_Iniciais(){
        /*new Thread() {   @Override public void run() {*/ try {  
            
//////////////////////////////////////////////////////////////////////////////////////////////            
            imgURL  = Imagens_Menu_Norte.class.getResource("seta_para_baixo.png");
            icon    = new ImageIcon( imgURL );  
            
            imgURL2  = Imagens_Menu_Norte.class.getResource("seta_para_cima.png");
            icon2   = new ImageIcon( imgURL2 ); 
//////////////////////////////////////////////////////////////////////////////////////////////

            if(status_cadastro.equalsIgnoreCase("Cadastrando...")){
                
                btExcluir.setVisible(false);
                
                mostrar_Ocultar_Jp_Padrao_Tabela();
            }
            else if(status_cadastro.equalsIgnoreCase("Visualizando...")){
                
                btExcluir.setVisible(false);
                btSalvar.setVisible(false);
                
                setar_Visualizacao_Recebida();
                //setarImagem_Principal();
                
                desabilitar_componentes();
            }
            else if(status_cadastro.equalsIgnoreCase("Alterando...")){
                
                setar_Visualizacao_Recebida();                
                //setarImagem_Principal();
            }
        } catch( Exception e ){  } //} }.start();
    }
    
    /*
    public void setarImagem_Principal(){ 
    new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
        
        List<EstabelecimentoImagens> UsuarioImagens = null;
        try{ 
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM.USUARIO_IMAGENS WHERE ID_USUARIO_SISTEMA = ?", EstabelecimentoImagens.class );
            q.setParameter(1, Estabelecimento_Recebido.getId() );
            List<EstabelecimentoImagens> lista_Banco = q.getResultList();   
            UsuarioImagens = lista_Banco;
            
            System.out.println("setarImagem_Principal() - " + Estabelecimento_Recebido.getId() +" - "+Estabelecimento_Recebido.getLogin());
        }catch( Exception e ){
            
            System.out.println("List<UsuarioImagens> UsuarioImagens - Erro - " + Estabelecimento_Recebido.getId() +" - "+Estabelecimento_Recebido.getLogin());
            e.printStackTrace();
        }
        
        String rbusca = ""; 
        try{ rbusca = UsuarioImagens.get(0).getNome(); }catch( Exception e ){}
            
        if( !rbusca.equals("") ){	
            
	    BufferedImage bufImg = null;
            ImageIcon     icon   = null;
            Image         image  = null;
            int widith = 0;
            int height = 0;
            try{
                
                byte[] byte_list = UsuarioImagens.get(0).getImagem();
                //ImageIO.read( img );
                bufImg = ImageIO.read(new ByteArrayInputStream( byte_list )); 
                icon   = new ImageIcon(bufImg);
                image  = icon.getImage();
                //image = icon.getImage();//ImageIO.read(f);  
                widith = lbImagemPrincipal.getPreferredSize().width;
                height = lbImagemPrincipal.getPreferredSize().height;
                lbImagemPrincipal.setIcon(new ImageIcon(image.getScaledInstance(widith, height, Image.SCALE_DEFAULT)));
                //ImageIO.write(img, "PNG", new File("C:/Downloads/h.png"));
                
                System.out.println("setarImagem_Principal() - bufImg = ImageIO.read" );
                System.out.println("setarImagem_Principal() - widith - " + widith);
                System.out.println("setarImagem_Principal() - height - " + height);
            } catch (Exception e) {
                
                System.out.println("setarImagem_Principal() - Erro - bufImg = ImageIO.read" );
                System.out.println("setarImagem_Principal() - Erro - widith - " + widith);
                System.out.println("setarImagem_Principal() - Erro - height - " + height);
                e.printStackTrace();
            }          
        }
        else{
            
            lbImagemPrincipal.setIcon(null);
        }
                            
    } catch( Exception e ){ } } }.start();
    }
    */
    
    public void setarImagemExterna_Endereco_for(File img){ 

        /*new Thread() {   @Override public void run() {*/ try { 
            
            BufferedImage bufImg = null;
            ImageIcon     icon   = null;
            Image         image  = null;
            try{
                        bufImg = ImageIO.read( img );
                        icon   = new ImageIcon(bufImg);
                        image  = icon.getImage();//ImageIO.read(f);  
                    } catch (IOException ex) {}  

                    try{
                
                //image = icon.getImage();//ImageIO.read(f);  
                int widith = lbImagemPrincipal.getWidth();
                int height = lbImagemPrincipal.getHeight();
            
                lbImagemPrincipal.setIcon(new ImageIcon(image.getScaledInstance(
                    widith, height, Image.SCALE_DEFAULT)));
                
		//ImageIO.write(img, "PNG", new File("C:/Downloads/h.png"));
	    }catch(Exception e){ e.printStackTrace(); }  
                
                String nome = img.getName();
                String nomeList[] = nome.split(Pattern.quote("."));             
        } catch( Exception e ){ } 
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        Pergunta = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jp_Padrao_Tabela = new javax.swing.JPanel();
        jpO = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        JPOpcao_14 = new javax.swing.JPanel();
        jLabel68 = new javax.swing.JLabel();
        JPOpcao_15 = new javax.swing.JPanel();
        lb_Cadastrado_Por = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        JPOpcao_13 = new javax.swing.JPanel();
        jLabel60 = new javax.swing.JLabel();
        JPOpcao_12 = new javax.swing.JPanel();
        lb_Alterado_Por = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        JPOpcao_18 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        JPOpcao_19 = new javax.swing.JPanel();
        lb_Data_Cadastro = new javax.swing.JLabel();
        jPanel23 = new javax.swing.JPanel();
        JPOpcao_16 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        JPOpcao_17 = new javax.swing.JPanel();
        lb_Data_Ultima_Alteracao = new javax.swing.JLabel();
        jpIcon1 = new javax.swing.JPanel();
        lbImagemPrincipal = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        btSalvar = new javax.swing.JButton();
        btExcluir = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        JPOpcao_26 = new javax.swing.JPanel();
        jLabel80 = new javax.swing.JLabel();
        JPOpcao_27 = new javax.swing.JPanel();
        tf_Nome_Ou_Razao_Social = new javax.swing.JTextField();
        jPanel29 = new javax.swing.JPanel();
        JPOpcao_28 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        JPOpcao_29 = new javax.swing.JPanel();
        tf_Codigo_de_Identificacao = new javax.swing.JTextField();
        jPanel30 = new javax.swing.JPanel();
        JPOpcao_30 = new javax.swing.JPanel();
        jLabel82 = new javax.swing.JLabel();
        JPOpcao_31 = new javax.swing.JPanel();
        tf_Cpf_Ou_Cnpj = new javax.swing.JTextField();
        jPanel31 = new javax.swing.JPanel();
        JPOpcao_32 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        JPOpcao_33 = new javax.swing.JPanel();
        tf_Nome_Fantasia = new javax.swing.JTextField();
        jPanel32 = new javax.swing.JPanel();
        JPOpcao_34 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        rb_Pessoa_Fisica = new javax.swing.JRadioButton();
        rb_Pessoa_Juridica = new javax.swing.JRadioButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(948, 593));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setPreferredSize(new java.awt.Dimension(1170, 1202));

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        jLabel11.setFont(new java.awt.Font("Calibri", 0, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Estabelecimento - Cadastrar/Visualizar");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/seta_para_cima.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLabel3MousePressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jpOLayout = new javax.swing.GroupLayout(jpO);
        jpO.setLayout(jpOLayout);
        jpOLayout.setHorizontalGroup(
            jpOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jpOLayout.setVerticalGroup(
            jpOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );

        JPOpcao_14.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_14.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_14MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_14MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_14MousePressed(evt);
            }
        });

        jLabel68.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel68.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel68.setText("Cadastrado Por.: ");

        javax.swing.GroupLayout JPOpcao_14Layout = new javax.swing.GroupLayout(JPOpcao_14);
        JPOpcao_14.setLayout(JPOpcao_14Layout);
        JPOpcao_14Layout.setHorizontalGroup(
            JPOpcao_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        );
        JPOpcao_14Layout.setVerticalGroup(
            JPOpcao_14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel68, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_15.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_15.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_15.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_15MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_15MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_15MousePressed(evt);
            }
        });

        lb_Cadastrado_Por.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lb_Cadastrado_Por.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout JPOpcao_15Layout = new javax.swing.GroupLayout(JPOpcao_15);
        JPOpcao_15.setLayout(JPOpcao_15Layout);
        JPOpcao_15Layout.setHorizontalGroup(
            JPOpcao_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_Cadastrado_Por, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_15Layout.setVerticalGroup(
            JPOpcao_15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_Cadastrado_Por, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPOpcao_14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_15, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JPOpcao_14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JPOpcao_13.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_13.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_13MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_13MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_13MousePressed(evt);
            }
        });

        jLabel60.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel60.setText("Alterado Por (Última Alteração).: ");

        javax.swing.GroupLayout JPOpcao_13Layout = new javax.swing.GroupLayout(JPOpcao_13);
        JPOpcao_13.setLayout(JPOpcao_13Layout);
        JPOpcao_13Layout.setHorizontalGroup(
            JPOpcao_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        );
        JPOpcao_13Layout.setVerticalGroup(
            JPOpcao_13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel60, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_12.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_12.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_12.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_12MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_12MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_12MousePressed(evt);
            }
        });

        lb_Alterado_Por.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lb_Alterado_Por.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout JPOpcao_12Layout = new javax.swing.GroupLayout(JPOpcao_12);
        JPOpcao_12.setLayout(JPOpcao_12Layout);
        JPOpcao_12Layout.setHorizontalGroup(
            JPOpcao_12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_Alterado_Por, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_12Layout.setVerticalGroup(
            JPOpcao_12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_Alterado_Por, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPOpcao_13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_12, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JPOpcao_13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JPOpcao_18.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_18.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_18MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_18MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_18MousePressed(evt);
            }
        });

        jLabel72.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel72.setText("Data Cadastro.: ");

        javax.swing.GroupLayout JPOpcao_18Layout = new javax.swing.GroupLayout(JPOpcao_18);
        JPOpcao_18.setLayout(JPOpcao_18Layout);
        JPOpcao_18Layout.setHorizontalGroup(
            JPOpcao_18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        );
        JPOpcao_18Layout.setVerticalGroup(
            JPOpcao_18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel72, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_19.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_19.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_19.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_19MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_19MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_19MousePressed(evt);
            }
        });

        lb_Data_Cadastro.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lb_Data_Cadastro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout JPOpcao_19Layout = new javax.swing.GroupLayout(JPOpcao_19);
        JPOpcao_19.setLayout(JPOpcao_19Layout);
        JPOpcao_19Layout.setHorizontalGroup(
            JPOpcao_19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_19Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_Data_Cadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_19Layout.setVerticalGroup(
            JPOpcao_19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_Data_Cadastro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPOpcao_18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_19, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JPOpcao_18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        JPOpcao_16.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_16.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_16MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_16MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_16MousePressed(evt);
            }
        });

        jLabel70.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel70.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel70.setText("Data última Alteração.: ");

        javax.swing.GroupLayout JPOpcao_16Layout = new javax.swing.GroupLayout(JPOpcao_16);
        JPOpcao_16.setLayout(JPOpcao_16Layout);
        JPOpcao_16Layout.setHorizontalGroup(
            JPOpcao_16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
        );
        JPOpcao_16Layout.setVerticalGroup(
            JPOpcao_16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel70, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_17.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_17.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_17.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_17MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_17MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_17MousePressed(evt);
            }
        });

        lb_Data_Ultima_Alteracao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        lb_Data_Ultima_Alteracao.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout JPOpcao_17Layout = new javax.swing.GroupLayout(JPOpcao_17);
        JPOpcao_17.setLayout(JPOpcao_17Layout);
        JPOpcao_17Layout.setHorizontalGroup(
            JPOpcao_17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_17Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_Data_Ultima_Alteracao, javax.swing.GroupLayout.DEFAULT_SIZE, 274, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_17Layout.setVerticalGroup(
            JPOpcao_17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lb_Data_Ultima_Alteracao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPOpcao_16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPOpcao_17, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(JPOpcao_16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jpIcon1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jpIcon1.setPreferredSize(new java.awt.Dimension(185, 200));
        jpIcon1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jpIcon1MousePressed(evt);
            }
        });

        lbImagemPrincipal.setBorder(new javax.swing.border.MatteBorder(null));
        lbImagemPrincipal.setPreferredSize(new java.awt.Dimension(183, 186));

        javax.swing.GroupLayout jpIcon1Layout = new javax.swing.GroupLayout(jpIcon1);
        jpIcon1.setLayout(jpIcon1Layout);
        jpIcon1Layout.setHorizontalGroup(
            jpIcon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbImagemPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jpIcon1Layout.setVerticalGroup(
            jpIcon1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbImagemPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jp_Padrao_TabelaLayout = new javax.swing.GroupLayout(jp_Padrao_Tabela);
        jp_Padrao_Tabela.setLayout(jp_Padrao_TabelaLayout);
        jp_Padrao_TabelaLayout.setHorizontalGroup(
            jp_Padrao_TabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jpO, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jp_Padrao_TabelaLayout.createSequentialGroup()
                .addComponent(jpIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jp_Padrao_TabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jp_Padrao_TabelaLayout.setVerticalGroup(
            jp_Padrao_TabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_Padrao_TabelaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jp_Padrao_TabelaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jpIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jp_Padrao_TabelaLayout.createSequentialGroup()
                        .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(41, 41, 41)
                .addComponent(jpO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jButton7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/jmary/home/imagens/ajuda.gif"))); // NOI18N
        jButton7.setText("Opções/Imagens");
        jButton7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jButton7MousePressed(evt);
            }
        });

        btSalvar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btSalvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/livroTp.png"))); // NOI18N
        btSalvar.setText("Salvar");
        btSalvar.setPreferredSize(new java.awt.Dimension(185, 31));
        btSalvar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btSalvarMousePressed(evt);
            }
        });

        btExcluir.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home_controle_menus_norte/imagens/exluir.png"))); // NOI18N
        btExcluir.setText("Excluir");
        btExcluir.setPreferredSize(new java.awt.Dimension(185, 31));
        btExcluir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                btExcluirMousePressed(evt);
            }
        });

        JPOpcao_26.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(153, 0, 153)));
        JPOpcao_26.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_26.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_26MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_26MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_26MousePressed(evt);
            }
        });

        jLabel80.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel80.setText("  Nome ou Razão social:");

        javax.swing.GroupLayout JPOpcao_26Layout = new javax.swing.GroupLayout(JPOpcao_26);
        JPOpcao_26.setLayout(JPOpcao_26Layout);
        JPOpcao_26Layout.setHorizontalGroup(
            JPOpcao_26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_26Layout.createSequentialGroup()
                .addComponent(jLabel80, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
        );
        JPOpcao_26Layout.setVerticalGroup(
            JPOpcao_26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel80, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_27.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_27.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_27.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_27MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_27MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_27MousePressed(evt);
            }
        });

        tf_Nome_Ou_Razao_Social.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tf_Nome_Ou_Razao_Social.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tf_Nome_Ou_Razao_Social.setPreferredSize(new java.awt.Dimension(209, 25));
        tf_Nome_Ou_Razao_Social.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_Nome_Ou_Razao_SocialMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tf_Nome_Ou_Razao_SocialMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tf_Nome_Ou_Razao_SocialMouseExited(evt);
            }
        });
        tf_Nome_Ou_Razao_Social.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_Nome_Ou_Razao_SocialKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_27Layout = new javax.swing.GroupLayout(JPOpcao_27);
        JPOpcao_27.setLayout(JPOpcao_27Layout);
        JPOpcao_27Layout.setHorizontalGroup(
            JPOpcao_27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_Nome_Ou_Razao_Social, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_27Layout.setVerticalGroup(
            JPOpcao_27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_27Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tf_Nome_Ou_Razao_Social, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JPOpcao_27, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(JPOpcao_26, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addComponent(JPOpcao_26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JPOpcao_27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPOpcao_28.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(153, 0, 153)));
        JPOpcao_28.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_28.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_28MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_28MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_28MousePressed(evt);
            }
        });

        jLabel81.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel81.setText("  Código de Identificação");

        javax.swing.GroupLayout JPOpcao_28Layout = new javax.swing.GroupLayout(JPOpcao_28);
        JPOpcao_28.setLayout(JPOpcao_28Layout);
        JPOpcao_28Layout.setHorizontalGroup(
            JPOpcao_28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_28Layout.createSequentialGroup()
                .addComponent(jLabel81, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
        );
        JPOpcao_28Layout.setVerticalGroup(
            JPOpcao_28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel81, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_29.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_29.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_29.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_29.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_29MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_29MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_29MousePressed(evt);
            }
        });

        tf_Codigo_de_Identificacao.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tf_Codigo_de_Identificacao.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tf_Codigo_de_Identificacao.setPreferredSize(new java.awt.Dimension(209, 25));
        tf_Codigo_de_Identificacao.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_Codigo_de_IdentificacaoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tf_Codigo_de_IdentificacaoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tf_Codigo_de_IdentificacaoMouseExited(evt);
            }
        });
        tf_Codigo_de_Identificacao.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_Codigo_de_IdentificacaoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_29Layout = new javax.swing.GroupLayout(JPOpcao_29);
        JPOpcao_29.setLayout(JPOpcao_29Layout);
        JPOpcao_29Layout.setHorizontalGroup(
            JPOpcao_29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_29Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_Codigo_de_Identificacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_29Layout.setVerticalGroup(
            JPOpcao_29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_29Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tf_Codigo_de_Identificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JPOpcao_29, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(JPOpcao_28, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addComponent(JPOpcao_28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JPOpcao_29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPOpcao_30.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(153, 0, 153)));
        JPOpcao_30.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_30MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_30MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_30MousePressed(evt);
            }
        });

        jLabel82.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel82.setText("  CPF ou CNPJ:");

        javax.swing.GroupLayout JPOpcao_30Layout = new javax.swing.GroupLayout(JPOpcao_30);
        JPOpcao_30.setLayout(JPOpcao_30Layout);
        JPOpcao_30Layout.setHorizontalGroup(
            JPOpcao_30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_30Layout.createSequentialGroup()
                .addComponent(jLabel82, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
        );
        JPOpcao_30Layout.setVerticalGroup(
            JPOpcao_30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel82, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_31.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_31.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_31.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_31MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_31MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_31MousePressed(evt);
            }
        });

        tf_Cpf_Ou_Cnpj.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tf_Cpf_Ou_Cnpj.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tf_Cpf_Ou_Cnpj.setPreferredSize(new java.awt.Dimension(209, 25));
        tf_Cpf_Ou_Cnpj.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_Cpf_Ou_CnpjMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tf_Cpf_Ou_CnpjMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tf_Cpf_Ou_CnpjMouseExited(evt);
            }
        });
        tf_Cpf_Ou_Cnpj.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_Cpf_Ou_CnpjKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_31Layout = new javax.swing.GroupLayout(JPOpcao_31);
        JPOpcao_31.setLayout(JPOpcao_31Layout);
        JPOpcao_31Layout.setHorizontalGroup(
            JPOpcao_31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_31Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_Cpf_Ou_Cnpj, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_31Layout.setVerticalGroup(
            JPOpcao_31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_31Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tf_Cpf_Ou_Cnpj, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JPOpcao_31, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(JPOpcao_30, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(JPOpcao_30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JPOpcao_31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPOpcao_32.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(153, 0, 153)));
        JPOpcao_32.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_32MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_32MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_32MousePressed(evt);
            }
        });

        jLabel83.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel83.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel83.setText("  Nome Fantasia:");

        javax.swing.GroupLayout JPOpcao_32Layout = new javax.swing.GroupLayout(JPOpcao_32);
        JPOpcao_32.setLayout(JPOpcao_32Layout);
        JPOpcao_32Layout.setHorizontalGroup(
            JPOpcao_32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_32Layout.createSequentialGroup()
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 57, Short.MAX_VALUE))
        );
        JPOpcao_32Layout.setVerticalGroup(
            JPOpcao_32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel83, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
        );

        JPOpcao_33.setBackground(new java.awt.Color(255, 255, 255));
        JPOpcao_33.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)));
        JPOpcao_33.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_33MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_33MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_33MousePressed(evt);
            }
        });

        tf_Nome_Fantasia.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        tf_Nome_Fantasia.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(201, 239, 237), 1, true));
        tf_Nome_Fantasia.setPreferredSize(new java.awt.Dimension(209, 25));
        tf_Nome_Fantasia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tf_Nome_FantasiaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tf_Nome_FantasiaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                tf_Nome_FantasiaMouseExited(evt);
            }
        });
        tf_Nome_Fantasia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_Nome_FantasiaKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout JPOpcao_33Layout = new javax.swing.GroupLayout(JPOpcao_33);
        JPOpcao_33.setLayout(JPOpcao_33Layout);
        JPOpcao_33Layout.setHorizontalGroup(
            JPOpcao_33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tf_Nome_Fantasia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPOpcao_33Layout.setVerticalGroup(
            JPOpcao_33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_33Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(tf_Nome_Fantasia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JPOpcao_33, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE)
                    .addComponent(JPOpcao_32, javax.swing.GroupLayout.DEFAULT_SIZE, 372, Short.MAX_VALUE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addComponent(JPOpcao_32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(JPOpcao_33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPOpcao_34.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 0, 0, new java.awt.Color(153, 0, 153)));
        JPOpcao_34.setPreferredSize(new java.awt.Dimension(284, 27));
        JPOpcao_34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                JPOpcao_34MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                JPOpcao_34MouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                JPOpcao_34MousePressed(evt);
            }
        });

        jLabel84.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        jLabel84.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel84.setText("  Tipo de Pessoa:");

        rb_Pessoa_Fisica.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        rb_Pessoa_Fisica.setText("Pessoa Física");

        rb_Pessoa_Juridica.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        rb_Pessoa_Juridica.setText("Pessoa Jurídica");

        javax.swing.GroupLayout JPOpcao_34Layout = new javax.swing.GroupLayout(JPOpcao_34);
        JPOpcao_34.setLayout(JPOpcao_34Layout);
        JPOpcao_34Layout.setHorizontalGroup(
            JPOpcao_34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPOpcao_34Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPOpcao_34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel84, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(JPOpcao_34Layout.createSequentialGroup()
                        .addComponent(rb_Pessoa_Fisica, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)
                        .addComponent(rb_Pessoa_Juridica, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        JPOpcao_34Layout.setVerticalGroup(
            JPOpcao_34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPOpcao_34Layout.createSequentialGroup()
                .addComponent(jLabel84, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(JPOpcao_34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rb_Pessoa_Fisica)
                    .addComponent(rb_Pessoa_Juridica)))
        );

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JPOpcao_34, javax.swing.GroupLayout.PREFERRED_SIZE, 372, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPOpcao_34, javax.swing.GroupLayout.DEFAULT_SIZE, 56, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(50, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Dados Básicos", jPanel1);

        javax.swing.GroupLayout PerguntaLayout = new javax.swing.GroupLayout(Pergunta);
        Pergunta.setLayout(PerguntaLayout);
        PerguntaLayout.setHorizontalGroup(
            PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PerguntaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jp_Padrao_Tabela, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PerguntaLayout.createSequentialGroup()
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(112, 112, 112))
                    .addComponent(jTabbedPane2))
                .addContainerGap())
        );
        PerguntaLayout.setVerticalGroup(
            PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PerguntaLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(PerguntaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btExcluir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(jp_Padrao_Tabela, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jTabbedPane2)
                .addGap(0, 0, 0))
        );

        jTabbedPane1.addTab("Cadastrar/Visualizar", Pergunta);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 948, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

   
    private URL       imgURL; 
    private ImageIcon icon;            
    private URL       imgURL2;
    private ImageIcon icon2;
    private boolean cimabaixo = true; 
    private void jLabel3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MousePressed
        
        mostrar_Ocultar_Jp_Padrao_Tabela();
    }//GEN-LAST:event_jLabel3MousePressed

    private void mostrar_Ocultar_Jp_Padrao_Tabela() {                                     
        try{

            if( cimabaixo == false ){
                cimabaixo = true;
                
                jLabel3.setToolTipText( "Ocultar Submenu" );
                jLabel3.setIcon( icon2 );              
                
                jp_Padrao_Tabela.setVisible(true);
            } else if( cimabaixo == true ){
                cimabaixo = false;
                                
                jLabel3.setToolTipText( "Mostrar Submenu" );
                jLabel3.setIcon( icon );  
                
                jp_Padrao_Tabela.setVisible(false);
            }
            
        } catch( Exception e ){ JOPM JOptionPaneMod = new JOPM( 2, "tabJanelaSubmenu, \n"
                + e.getMessage() + "\n", "Alterar_Seta_Submenu" ); }       
    } 
    
    private void JPOpcao_13MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_13MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_13MouseEntered

    private void JPOpcao_13MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_13MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_13MouseExited

    private void JPOpcao_13MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_13MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_13MousePressed

    private void JPOpcao_12MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_12MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_12MouseEntered

    private void JPOpcao_12MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_12MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_12MouseExited

    private void JPOpcao_12MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_12MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_12MousePressed

    private void JPOpcao_14MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_14MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_14MouseEntered

    private void JPOpcao_14MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_14MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_14MouseExited

    private void JPOpcao_14MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_14MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_14MousePressed

    private void JPOpcao_15MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_15MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_15MouseEntered

    private void JPOpcao_15MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_15MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_15MouseExited

    private void JPOpcao_15MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_15MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_15MousePressed

    private void JPOpcao_16MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_16MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_16MouseEntered

    private void JPOpcao_16MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_16MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_16MouseExited

    private void JPOpcao_16MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_16MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_16MousePressed

    private void JPOpcao_17MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_17MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_17MouseEntered

    private void JPOpcao_17MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_17MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_17MouseExited

    private void JPOpcao_17MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_17MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_17MousePressed

    private void JPOpcao_18MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_18MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_18MouseEntered

    private void JPOpcao_18MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_18MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_18MouseExited

    private void JPOpcao_18MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_18MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_18MousePressed

    private void JPOpcao_19MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_19MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_19MouseEntered

    private void JPOpcao_19MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_19MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_19MouseExited

    private void JPOpcao_19MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_19MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_19MousePressed

    private void jButton7MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton7MousePressed

        /*new Thread() {   @Override public void run() {*/ try { 
        
        if(status_cadastro.equalsIgnoreCase("Cadastrando...")){
                  
            Exportando = new Exportando("ABRINDO...");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            
            Exportando.pbg.setValue( 50 ); 
            
            Home.ControleTabs.AddTabComControle(jTabbedPane1, "Visualizador Imagens Banco de Dados", "livroTp.gif", 
                        new Estabelecimento_Visualizador_De_Imagens(Home, 0, status_cadastro, this ) ); 
            
            Exportando.fechar();             
        }
        else{
      
            Exportando = new Exportando("ABRINDO...");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            
            Exportando.pbg.setValue( 50 ); 
            
            Home.ControleTabs.AddTabComControle(jTabbedPane1, "Visualizador Imagens Banco de Dados", "livroTp.gif", 
                        new Estabelecimento_Visualizador_De_Imagens(Home, Estabelecimento_Recebido.getId(), status_cadastro, this) );   
            
            Exportando.fechar();  
        }      
            
    }catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); }  //} }.start(); 
    }//GEN-LAST:event_jButton7MousePressed

    private void jpIcon1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpIcon1MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jpIcon1MousePressed

    Exportando Exportando;
    private void btSalvarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btSalvarMousePressed
    new Thread() {   @Override public void run() { try { 
        
        if(status_cadastro.equalsIgnoreCase("Cadastrando...")){
            
/* ////// */Estabelecimento_Recebido = new Estabelecimento();
      
            Exportando = new Exportando("CADASTRANDO");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            
            Exportando.pbg.setValue( 50 ); 
            
            verificando_se_o_estabelecimento_e_jmarysystems();  
            
            Exportando.fechar();             
        }
        else{
      
            Exportando = new Exportando("ALTERANDO");
            Exportando.setVisible(true);
            Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            
            Exportando.pbg.setValue( 50 ); 
            
            verificando_se_o_estabelecimento_e_jmarysystems();  
            
            Exportando.fechar();  
        }     
            
    }catch( Exception e ){ Exportando.fechar(); e.printStackTrace(); }  } }.start(); 
    }//GEN-LAST:event_btSalvarMousePressed

    private void btExcluirMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btExcluirMousePressed
        try { 
    
            Object[] options = {
                "Confirmar",
                "Cancelar" 
            };
            int n = JOptionPane.showOptionDialog(null,
                    "Confirme a Opção de Excluir o Usuário\n"
                    + "Listado Abaixo.",
                    "Opção de Consulta",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]);
//////////////////////////////////////////////////////
            if(n==0){
        
                //excluir_Usuario_Atual();
            }            
        } catch( Exception e ){} 
    }//GEN-LAST:event_btExcluirMousePressed

    private void JPOpcao_26MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_26MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_26MouseEntered

    private void JPOpcao_26MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_26MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_26MouseExited

    private void JPOpcao_26MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_26MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_26MousePressed

    private void tf_Nome_Ou_Razao_SocialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_Nome_Ou_Razao_SocialMouseClicked
        try{
            PopupMenu_Colar = new PopupMenu_Colar(tf_Nome_Ou_Razao_Social);
        } catch( Exception e ){}
    }//GEN-LAST:event_tf_Nome_Ou_Razao_SocialMouseClicked

    private void tf_Nome_Ou_Razao_SocialMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_Nome_Ou_Razao_SocialMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Nome_Ou_Razao_SocialMouseEntered

    private void tf_Nome_Ou_Razao_SocialMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_Nome_Ou_Razao_SocialMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Nome_Ou_Razao_SocialMouseExited

    private void tf_Nome_Ou_Razao_SocialKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_Nome_Ou_Razao_SocialKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Nome_Ou_Razao_SocialKeyReleased

    private void JPOpcao_27MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_27MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_27MouseEntered

    private void JPOpcao_27MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_27MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_27MouseExited

    private void JPOpcao_27MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_27MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_27MousePressed

    private void JPOpcao_28MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_28MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_28MouseEntered

    private void JPOpcao_28MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_28MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_28MouseExited

    private void JPOpcao_28MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_28MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_28MousePressed

    private void tf_Codigo_de_IdentificacaoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_Codigo_de_IdentificacaoMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Codigo_de_IdentificacaoMouseClicked

    private void tf_Codigo_de_IdentificacaoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_Codigo_de_IdentificacaoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Codigo_de_IdentificacaoMouseEntered

    private void tf_Codigo_de_IdentificacaoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_Codigo_de_IdentificacaoMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Codigo_de_IdentificacaoMouseExited

    private void tf_Codigo_de_IdentificacaoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_Codigo_de_IdentificacaoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Codigo_de_IdentificacaoKeyReleased

    private void JPOpcao_29MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_29MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_29MouseEntered

    private void JPOpcao_29MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_29MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_29MouseExited

    private void JPOpcao_29MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_29MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_29MousePressed

    private void JPOpcao_30MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_30MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_30MouseEntered

    private void JPOpcao_30MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_30MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_30MouseExited

    private void JPOpcao_30MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_30MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_30MousePressed

    private void tf_Cpf_Ou_CnpjMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_Cpf_Ou_CnpjMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Cpf_Ou_CnpjMouseClicked

    private void tf_Cpf_Ou_CnpjMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_Cpf_Ou_CnpjMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Cpf_Ou_CnpjMouseEntered

    private void tf_Cpf_Ou_CnpjMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_Cpf_Ou_CnpjMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Cpf_Ou_CnpjMouseExited

    private void tf_Cpf_Ou_CnpjKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_Cpf_Ou_CnpjKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Cpf_Ou_CnpjKeyReleased

    private void JPOpcao_31MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_31MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_31MouseEntered

    private void JPOpcao_31MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_31MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_31MouseExited

    private void JPOpcao_31MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_31MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_31MousePressed

    private void JPOpcao_32MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_32MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_32MouseEntered

    private void JPOpcao_32MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_32MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_32MouseExited

    private void JPOpcao_32MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_32MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_32MousePressed

    private void tf_Nome_FantasiaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_Nome_FantasiaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Nome_FantasiaMouseClicked

    private void tf_Nome_FantasiaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_Nome_FantasiaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Nome_FantasiaMouseEntered

    private void tf_Nome_FantasiaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tf_Nome_FantasiaMouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Nome_FantasiaMouseExited

    private void tf_Nome_FantasiaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_Nome_FantasiaKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tf_Nome_FantasiaKeyReleased

    private void JPOpcao_33MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_33MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_33MouseEntered

    private void JPOpcao_33MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_33MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_33MouseExited

    private void JPOpcao_33MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_33MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_33MousePressed

    private void JPOpcao_34MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_34MousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_34MousePressed

    private void JPOpcao_34MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_34MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_34MouseExited

    private void JPOpcao_34MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JPOpcao_34MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_JPOpcao_34MouseEntered
    
    /*
    private void excluir_Usuario_Atual() {                                       
         new Thread() {   @Override public void run() { try { Thread.sleep( 1 );
        
            Exportando = new Exportando("EXCLUINDO...");
            Exportando.setVisible(true);Exportando.pbg.setMinimum(0);
            Exportando.pbg.setMaximum( 100 );
            Exportando.pbg.setValue( 50 );
            
                if( btExcluir.isEnabled() == true ){
                                                  
                    List<Estabelecimento> List_2_UsuarioSistema = null;
                    try{
                        Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM JM.USUARIO_SISTEMA WHERE ID = ?", Estabelecimento.class );
                        q2.setParameter(1, Estabelecimento_Recebido.getId() ); 
                        List_2_UsuarioSistema = q2.getResultList();
                    }catch(Exception e){}
                    
                    String rbusca = ""; try{ rbusca = List_2_UsuarioSistema.get(0).getLogin(); }catch( Exception e ){}
                    if( !rbusca.equals("") ){
                                            
                        //Home.ControleTabs.AddTabComControle(jTabbedPane1, "Alterar Usuário", "livroTp.gif", 
                        //new Estabelecimento_02_Cadastrar_Visualizar( Home, "Alterando...", List_2_UsuarioSistema.get(0) ) );
                        if( rbusca.equalsIgnoreCase("cleilson") ){
                            
                            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                            JOPM JOptionPaneMod = new JOPM( 1, "EXCLUINDO USUÁRIO SELECIONADO\n"
                                + "\nUSUÁRIO: " + rbusca 
                               + "\nVOCÊ NÃO TEM AUTORIZAÇÃO PARA EXCLUIR O USUÁRIO CLEILSON"
                                + "\nOK Para Prosseguir"
                                ,"Class: " + this.getClass().getName(),
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );  
                        }
                        else{
                            
                            excluir_Imagens( List_2_UsuarioSistema.get(0) );
                            
                            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( List_2_UsuarioSistema.get(0), JPAUtil.em());
                            DAOGenericoJPA2.excluir();

                            Home.ControleTabs.removerTabSemControleSelecionado(JTabbedPane_Recebido);
                            
                            Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                            JOPM JOptionPaneMod = new JOPM( 1, "EXCLUINDO USUÁRIO SELECIONADO\n"
                                + "\nUSUÁRIO: " + rbusca 
                               + "\nEXCLUIDO COM SUCESSO"
                                + "\nOK Para Prosseguir"
                                ,"Class: " + this.getClass().getName(),
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );                        
                        }
                    }
                    
                    Exportando.fechar();
                }
            
        } catch( InterruptedException e ){ Exportando.fechar(); e.printStackTrace(); } } }.start();
    }
    */
    
    /*
    private void excluir_Imagens(Estabelecimento Estabelecimento_Excluir_Imagens){ 
    //new Thread() {   @Override public void run() {
    try { 
             
        List<EstabelecimentoImagens> lista_Banco = null;
        try{ 
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM.ESTABELECIMENTO_IMAGENS WHERE ID_ESTABELECIMENTO = ?", EstabelecimentoImagens.class );
            q.setParameter( 1, Estabelecimento_Excluir_Imagens.getId() );
            lista_Banco = q.getResultList();   
        }catch( Exception e ){ }
        
        String rbusca = ""; 
        try{ rbusca = lista_Banco.get(0).getNome(); }catch( Exception e ){}
            
        if( !rbusca.equals("") ){	 
	          
            try{
                
                for (int i=0; i < lista_Banco.size(); i++) {
                    
                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( lista_Banco.get(i), JPAUtil.em());
                    DAOGenericoJPA2.excluir();
                }
	    }catch(Exception e){ e.printStackTrace(); }  
        }
                            
    } catch( Exception e ){ } //} }.start();
    }
    */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPOpcao_12;
    private javax.swing.JPanel JPOpcao_13;
    private javax.swing.JPanel JPOpcao_14;
    private javax.swing.JPanel JPOpcao_15;
    private javax.swing.JPanel JPOpcao_16;
    private javax.swing.JPanel JPOpcao_17;
    private javax.swing.JPanel JPOpcao_18;
    private javax.swing.JPanel JPOpcao_19;
    private javax.swing.JPanel JPOpcao_26;
    private javax.swing.JPanel JPOpcao_27;
    private javax.swing.JPanel JPOpcao_28;
    private javax.swing.JPanel JPOpcao_29;
    private javax.swing.JPanel JPOpcao_30;
    private javax.swing.JPanel JPOpcao_31;
    private javax.swing.JPanel JPOpcao_32;
    private javax.swing.JPanel JPOpcao_33;
    private javax.swing.JPanel JPOpcao_34;
    private javax.swing.JPanel Pergunta;
    private javax.swing.JButton btExcluir;
    private javax.swing.JButton btSalvar;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JPanel jpIcon1;
    private javax.swing.JPanel jpO;
    private javax.swing.JPanel jp_Padrao_Tabela;
    public javax.swing.JLabel lbImagemPrincipal;
    private javax.swing.JLabel lb_Alterado_Por;
    private javax.swing.JLabel lb_Cadastrado_Por;
    private javax.swing.JLabel lb_Data_Cadastro;
    private javax.swing.JLabel lb_Data_Ultima_Alteracao;
    private javax.swing.JRadioButton rb_Pessoa_Fisica;
    private javax.swing.JRadioButton rb_Pessoa_Juridica;
    public javax.swing.JTextField tf_Codigo_de_Identificacao;
    public javax.swing.JTextField tf_Cpf_Ou_Cnpj;
    public javax.swing.JTextField tf_Nome_Fantasia;
    public javax.swing.JTextField tf_Nome_Ou_Razao_Social;
    // End of variables declaration//GEN-END:variables
    
    public UsuarioSistema get_UsuarioSistema( int id_Estabelecimento ){ 
        UsuarioSistema retorno = null;
        
        List<UsuarioSistema> UsuarioSistema = null;
        try{ 
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM.USUARIO_SISTEMA WHERE ID = ?", UsuarioSistema.class );
            q.setParameter(1, id_Estabelecimento );
            List<UsuarioSistema> lista_Banco = q.getResultList();   
            UsuarioSistema = lista_Banco;
        }catch( Exception e ){ }
        
        String rbusca = ""; 
        try{ rbusca = UsuarioSistema.get(0).getLogin(); }catch( Exception e ){}
            
        if( !rbusca.equals("") ){
            
            retorno = UsuarioSistema.get(0);
        }

        return retorno;
    }
    
    private void setar_Visualizacao_Recebida(){                                          
        new Thread(){   @Override public void run() { try{ 
            
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////          
            try{ lb_Cadastrado_Por.setText( get_UsuarioSistema( Estabelecimento_Recebido.getIdUsuarioSistemaCadastroEstabelecimento()  ).getLogin() ); }catch(Exception e){} 
            try{ lb_Alterado_Por.setText(   get_UsuarioSistema( Estabelecimento_Recebido.getIdUsuarioSistemaAlteracaoEstabelecimento() ).getLogin() ); }catch(Exception e){}
            
            try{ 
                
                ////////dd.MM.yyyy HH:mm:ss/////////////EEEE, d MMMM yyyy HH:mm:ss
                DateFormat dfmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                String dataCadastro = ""; 
                try{ dataCadastro = dfmt.format(Estabelecimento_Recebido.getDataCadastro() ); }catch(Exception e){}
                try{ System.out.println("getDataCadastro - " + Estabelecimento_Recebido.getDataCadastro() ); }catch(Exception e){}
                lb_Data_Cadastro.setText( dataCadastro ); 
            }catch(Exception e){} 
            
            try{ 
                
                ////////dd.MM.yyyy HH:mm:ss/////////////EEEE, d MMMM yyyy HH:mm:ss
                DateFormat dfmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                String data_Ultima_Alteracao = ""; 
                try{ data_Ultima_Alteracao = dfmt.format(Estabelecimento_Recebido.getDataAlteracao()); }catch(Exception e){}
                try{ System.out.println("getDataAlteracao - " + Estabelecimento_Recebido.getDataAlteracao() ); }catch(Exception e){}
                lb_Data_Ultima_Alteracao.setText( data_Ultima_Alteracao ); 
            }catch(Exception e){} 
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////            

            try{ 
                if( Estabelecimento_Recebido.getPessoaFisica() == true ){
                    
                    rb_Pessoa_Fisica.setSelected(true);
                    rb_Pessoa_Juridica.setSelected(false);
                }    
                else{
                    
                    rb_Pessoa_Fisica.setSelected(false);
                    rb_Pessoa_Juridica.setSelected(true);
                }
            }catch(Exception e){}

            try{ tf_Nome_Ou_Razao_Social.setText(Estabelecimento_Recebido.getNomeOuRazaoSocial()); }catch(Exception e){}
            try{ tf_Nome_Fantasia.setText(Estabelecimento_Recebido.getNomeOuFantasia()); }catch(Exception e){}                       
                        
            try{ tf_Codigo_de_Identificacao.setText(Estabelecimento_Recebido.getCodigoDeIdentificacao()); }catch(Exception e){}            
            try{ tf_Cpf_Ou_Cnpj.setText(Estabelecimento_Recebido.getCpfOuCnpj()); }catch(Exception e){}
          
        } catch( Exception e ){  } } }.start();        
    }
    
    private void desabilitar_componentes() {                                          
        new Thread() {   @Override public void run() { try {

            try{ 
                
                tf_Nome_Ou_Razao_Social.setEditable(false);
                tf_Nome_Fantasia.setEditable(false);
                tf_Codigo_de_Identificacao.setEditable(false);
                tf_Cpf_Ou_Cnpj.setEditable(false);
                rb_Pessoa_Fisica.setEnabled(false);
                rb_Pessoa_Juridica.setEnabled(false);
            }catch(Exception e){}
          
        } catch( Exception e ){  } } }.start();        
    }
    
/// CADASTRANDO ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void verificando_se_o_estabelecimento_e_jmarysystems(){ 
        
        try{
            
            if(status_cadastro.equalsIgnoreCase("Cadastrando...")){
                
                setando_os_que_nao_precisam_de_validacao();
            }
            else{
                
                if( Estabelecimento_Recebido.getNomeOuFantasia().equalsIgnoreCase("jmarysystems") ){
                
                    Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                    JOPM JOptionPaneMod = new JOPM( 1, "ALTERANDO ESTABELECIMENTO SELECIONADO\n"
                            + "\nESTABELECIMENTO: " + Estabelecimento_Recebido.getNomeOuFantasia()
                            + "\nVOCÊ NÃO TEM AUTORIZAÇÃO PARA ALTERAR O ESTABELECIMENTO JMARYSYSTEM"
                            + "\nOK Para Prosseguir"
                            ,"Class: " + this.getClass().getName(),
                            new ImageIcon( clazzHome.getResource("logocangaco2.png")) );  
                }
                else{
                
                    setando_os_que_nao_precisam_de_validacao();
                }
            }
            
        }catch( Exception e ){}
    }
    
    private void setando_os_que_nao_precisam_de_validacao(){ 
        
        try{
            /*
            try{ Estabelecimento_Recebido.setCodigoDeIdentificacao(tf_Codigo_de_Identificacao.getText().trim().toUpperCase() ); }catch( Exception e ){}
            
            try{
                
                if( chPermitirAcessar.isSelected() == true ){
                    
                    try{ Estabelecimento_Recebido.setPermitirAcessoAoSistema( "SIM" ); }catch( Exception e ){}
                }
                else{
                    
                    try{ Estabelecimento_Recebido.setPermitirAcessoAoSistema( "NAO" ); }catch( Exception e ){}
                }
 
            }catch( Exception e ){}
            */
            
            verificar_Usuario_Logado();
            
        }catch( Exception e ){}
    }
    
    private void verificar_Usuario_Logado(){   
        
        String rbusca = ""; 
        try{ rbusca = Login.Usuario_Logado.getLogin(); }catch( Exception e ){}
        
        try{
            
            if( !rbusca.equals("") ){
                
                System.out.println( Login.Usuario_Logado.getLogin() );
                
                verificar_Estabelecimento_Se_Cadastra_Ou_Altera();
            }
            else{

                Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "STATUS DO CADASTRO\n"
                        + "\n"
                        + "\nNÃO HÁ USUÁRIO LOGADO!\n"
                        + "\n"
                        + "\nPARA CADASTRAR É NECESSÁRIO LOGAR!\n"
                        + "\n"
                        + "\nOK PARA PROSSEGUIR"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
            }
            
        }catch( Exception e ){ 
            
                e.printStackTrace();
                            
                Class<Imagens_Internas> clazzHome = Imagens_Internas.class;
                JOPM JOptionPaneMod = new JOPM( 1, "USUÁRIO LOGADO\n"
                        + "\n"
                        + "\nUSUÁRIO LOGADO INVÁLIDO!\n"
                        + "\n"
                        + "\nPARA CADASTRAR É NECESSÁRIO LOGAR!\n"
                        + "\n"
                        + "\nOK PARA PROSSEGUIR"
                        ,"Class: " + this.getClass().getName(), 
                        new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }
   
    private void verificar_Estabelecimento_Se_Cadastra_Ou_Altera(){   
        
        List<Estabelecimento> Estabelecimento = null;
        try{ 
            Query q = JPAUtil.em().createNativeQuery("SELECT * FROM JM.ESTABELECIMENTO WHERE ID = ?", Estabelecimento.class );
            q.setParameter(1, Estabelecimento_Recebido.getId() );
            List<Estabelecimento> lista_Banco = q.getResultList();   
            Estabelecimento = lista_Banco;
        }catch( Exception e ){ }
        
        String rbusca = ""; 
        try{ rbusca = Estabelecimento.get(0).getNomeOuRazaoSocial(); }catch( Exception e ){}
            
        //É ALTERAÇÃO 
        if( !rbusca.equals("") ){
                
            //CADASTRO / ALTERAÇÃO - USUÁRIO         
            //try{ Estabelecimento_Recebido.setIdUsuarioSistemaAlteracaoEstabelecimento( Login.Usuario_Logado.getId() ); }catch( Exception e ){}   
            try{ Estabelecimento_Recebido.setIdUsuarioSistemaAlteracaoEstabelecimento( Login.Usuario_Logado.getId() ); }catch( Exception e ){}   
             
            //CADASTRO / ALTERAÇÃO - DATA - ALTERAÇÃO 
            try{ 
                
                ////////dd.MM.yyyy HH:mm:ss/////////////EEEE, d MMMM yyyy HH:mm:ss
                Calendar calendar = Calendar.getInstance();
                java.util.Date now = calendar.getTime();
                java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());                
                
                DateFormat dfmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                String dataCadastro = ""; 
                try{ dataCadastro = dfmt.format(currentTimestamp);  }catch(Exception e){}
                ////////dd.MM.yyyy HH:mm:ss/////////////EEEE, d MMMM yyyy HH:mm:ss
                
                try{
                    
                    Estabelecimento_Recebido.setDataAlteracao( currentTimestamp ); 
                    //UsuarioSistema_Recebido.setDataUltimaAlteracaoSenha( data );
                    
                }catch(Exception e){}
            }catch(Exception e){}

            System.out.println( "Alterando Usuário: " );
                
            verificar_tipo_de_pessoa();
        }
        else{
            
            // É CADASTRO 

            //CADASTRO / ALTERAÇÃO - USUÁRIO 
            try{ Estabelecimento_Recebido.setIdUsuarioSistemaCadastroEstabelecimento(Login.Usuario_Logado.getId() ); }catch( Exception e ){}                
            try{ Estabelecimento_Recebido.setIdUsuarioSistemaAlteracaoEstabelecimento(Login.Usuario_Logado.getId() ); }catch( Exception e ){}
                
            //CADASTRO / ALTERAÇÃO - DATA - CADASTRANDO 
            try{ 
                
                ////////dd.MM.yyyy HH:mm:ss/////////////EEEE, d MMMM yyyy HH:mm:ss
                Calendar calendar = Calendar.getInstance();
                java.util.Date now = calendar.getTime();
                java.sql.Timestamp currentTimestamp = new java.sql.Timestamp(now.getTime());                
                
                DateFormat dfmt = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                String dataCadastro = ""; 
                try{ dataCadastro = dfmt.format(currentTimestamp);  }catch(Exception e){}
                ////////dd.MM.yyyy HH:mm:ss/////////////EEEE, d MMMM yyyy HH:mm:ss
                
                try{

                    Estabelecimento_Recebido.setDataCadastro( currentTimestamp ); 
                    
                    Estabelecimento_Recebido.setDataAlteracao( currentTimestamp ); 
                    
                }catch(Exception e){}
            }catch(Exception e){} 

            System.out.println(  "Novo Usuário: " );
                
            verificar_tipo_de_pessoa();
        }
    }
    
    private void verificar_tipo_de_pessoa(){ 
        
        if( rb_Pessoa_Fisica.isSelected() == true ){
            
            try{ Estabelecimento_Recebido.setPessoaFisica( true ); }catch( Exception e ){}
            try{ Estabelecimento_Recebido.setPessoaJuridica( false ); }catch( Exception e ){}
            
            verificar_Nome_Ou_Razao_Social();
        }
        else if( rb_Pessoa_Juridica.isSelected() == true ){
            
            try{ Estabelecimento_Recebido.setPessoaFisica( false ); }catch( Exception e ){}
            try{ Estabelecimento_Recebido.setPessoaJuridica( true ); }catch( Exception e ){}
            
            verificar_Nome_Ou_Razao_Social();
        }
        else{
            
            rb_Pessoa_Fisica.requestFocus();
                    
            Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "TIPO DE PESSOA\n"
                    + "\n"
                    + "\nO CAMPO TIPO DE PESSOA NÃO FOI SELECIONADO!\n"
                    + "\n"
                    + "\nPARA CADASTRAR É NECESSÁRIO SELECIONAR O TIPO DE PESSOA\n"
                    + "\n"
                    + "\nOK PARA PROSSEGUIR"
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }
    
    private void verificar_Nome_Ou_Razao_Social(){   
        
        String rbusca = ""; 
        try{ rbusca = tf_Nome_Ou_Razao_Social.getText().trim().toUpperCase(); }catch( Exception e ){}

        if( !rbusca.equals("") ){

            try{ Estabelecimento_Recebido.setNomeOuRazaoSocial( rbusca ); }catch( Exception e ){}
 
            verificar_nome_fantasia();
        }
        else{
            
            tf_Nome_Ou_Razao_Social.requestFocus();
                    
            Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "CAMPO NOME OU RAZÃO SOCIAL\n"
                    + "\n"
                    + "\nO CAMPO NOME OU RAZÃO SOCIAL ESTÁ VAZIO!\n"
                    + "\n"
                    + "\nPARA CADASTRAR É NECESSÁRIO INFORMAR UM NOME OU RAZÃO SOCIAL!\n"
                    + "\n"
                    + "\nOK PARA PROSSEGUIR"
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }
    
    private void verificar_nome_fantasia(){  
        
        String rbusca = ""; 
        try{ rbusca = tf_Nome_Fantasia.getText().trim().toUpperCase(); }catch( Exception e ){}

        if( !rbusca.equals("") ){

            try{ Estabelecimento_Recebido.setNomeOuFantasia( rbusca ); }catch( Exception e ){}
 
            verificar_Codigo_de_Identificacao();
        }
        else{
            
            tf_Nome_Fantasia.requestFocus();
                    
            Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "CAMPO NOME FANTASIA\n"
                    + "\n"
                    + "\nO CAMPO NOME FANTASIA ESTÁ VAZIO!\n"
                    + "\n"
                    + "\nPARA CADASTRAR É NECESSÁRIO INFORMAR UM NOME FANTASIA!\n"
                    + "\n"
                    + "\nOK PARA PROSSEGUIR"
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }
    
    private void verificar_Codigo_de_Identificacao(){  
        
        String rbusca = ""; 
        try{ rbusca = tf_Codigo_de_Identificacao.getText().trim().toUpperCase(); }catch( Exception e ){}

        if( !rbusca.equals("") ){

            try{ Estabelecimento_Recebido.setCodigoDeIdentificacao( rbusca ); }catch( Exception e ){}
 
            verificar_Repeticao_Codigo_de_Identificacao();
        }
        else{
            
            tf_Codigo_de_Identificacao.requestFocus();
                    
            Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "CAMPO CÓDIGO DE IDENTIFICAÇÃO\n"
                    + "\n"
                    + "\nO CAMPO CÓDIGO DE IDENTIFICAÇÃO ESTÁ VAZIO!\n"
                    + "\n"
                    + "\nPARA CADASTRAR É NECESSÁRIO INFORMAR UM CÓDIGO DE IDENTIFICAÇÃO!\n"
                    + "\n"
                    + "\nOK PARA PROSSEGUIR"
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }
     
    private void verificar_Repeticao_Codigo_de_Identificacao(){  
        System.out.println("verificar_Codigo_de_Identificacao");
        
        String rbusca = ""; 
        try{ rbusca = Estabelecimento_Recebido.getCodigoDeIdentificacao().trim(); }catch( Exception e ){}
        
        int iDbusca = 0; 
        try{ iDbusca = Estabelecimento_Recebido.getId(); }catch( Exception e ){}
        
        boolean material_ja_Cadastrado = false;
        if( !rbusca.equals("") ){
/////// VERIFICANDO REPETIÇÃO - CÓDIGO DE IDENTIFICAÇÃO /////////////////////////////////////////////////////////////////////////////////////////////////////////
            try{ 
                Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM JM.ESTABELECIMENTO WHERE CODIGO_DE_IDENTIFICACAO = ?", Estabelecimento.class );
                q2.setParameter( 1, rbusca ); 
                List<Estabelecimento> list = q2.getResultList();                    
                    
                for (Estabelecimento list1 : list) {
                        
                    if (list1.getCodigoDeIdentificacao().equals( rbusca )) {
                            
                        if (list1.getId() == iDbusca ) {
                                                        
                        }
                        else{
                            
                            material_ja_Cadastrado = true;
                            System.out.println("list1.getId() + \" - \" + iDbusca - "+ list1.getId() + " - " + iDbusca );
                            break; 
                        }
                    }
                }
            }catch( Exception e ){}
        } 
/////// VERIFICANDO REPETIÇÃO - CÓDIGO DE IDENTIFICAÇÃO ////////////////////////////////////////////////////////////////////////////////////////////////////////        
        
        if ( material_ja_Cadastrado == false ) {
            
            try{
                
                verificar_Cpf_Ou_Cnpj();
                
            }catch( Exception e ){
                
                System.out.println("Erro ao cadastrar");
                e.printStackTrace();
            }
        }
        else{
            
            Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "CAMPO CÓDIGO DE IDENTIFICAÇÃO\n"
                    + "\n"
                    + "\nO CAMPO CÓDIGO DE IDENTIFICAÇÃO JÁ EXISTE!\n"
                    + "\n"
                    + "\nINFORME UM NOVO CÓDIGO DE IDENTIFICAÇÃO QUE NÃO EXISTA CADASTRADO!\n"
                    + "\n"
                    + "\nOK PARA PROSSEGUIR"
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }

    }
    
    private void verificar_Cpf_Ou_Cnpj(){  
        
        String rbusca = ""; 
        try{ rbusca = tf_Cpf_Ou_Cnpj.getText().trim().toUpperCase(); }catch( Exception e ){}

        if( !rbusca.equals("") ){

            try{ Estabelecimento_Recebido.setCpfOuCnpj( rbusca ); }catch( Exception e ){}
 
            verificar_Repeticao_Cpf_Ou_Cnpj();
        }
        else{
            
            tf_Cpf_Ou_Cnpj.requestFocus();
                    
            Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "CAMPO CPF OU CNPJ\n"
                    + "\n"
                    + "\nO CAMPO CPF OU CNPJ ESTÁ VAZIO!\n"
                    + "\n"
                    + "\nPARA CADASTRAR É NECESSÁRIO INFORMAR UM CPF OU CNPJ!\n"
                    + "\n"
                    + "\nOK PARA PROSSEGUIR"
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }
    }
    
    private void verificar_Repeticao_Cpf_Ou_Cnpj(){  
        System.out.println("verificar_Repeticao_Cpf_Ou_Cnpj");
        
        String rbusca = ""; 
        try{ rbusca = Estabelecimento_Recebido.getCpfOuCnpj().toUpperCase(); }catch( Exception e ){}
        
        int iDbusca = 0; 
        try{ iDbusca = Estabelecimento_Recebido.getId(); }catch( Exception e ){}
        
        boolean material_ja_Cadastrado = false;
        if( !rbusca.equals("") ){
/////// VERIFICANDO REPETIÇÃO - CPF OU CNPJ /////////////////////////////////////////////////////////////////////////////////////////////////////////
            try{ 
                Query q2 = JPAUtil.em().createNativeQuery("SELECT * FROM JM.ESTABELECIMENTO WHERE CPF_OU_CNPJ = ?", Estabelecimento.class );
                q2.setParameter( 1, rbusca ); 
                List<Estabelecimento> list = q2.getResultList();                    
                    
                for (Estabelecimento list1 : list) {
                    
                    if (list1.getCpfOuCnpj().equals( rbusca )) {
                            
                        if (list1.getId() == iDbusca ) {
                                                        
                        }
                        else{
                            
                            material_ja_Cadastrado = true;
                            System.out.println("list1.getId() + \" - \" + iDbusca - "+ list1.getId() + " - " + iDbusca );
                            break; 
                        }
                    }
                }
            }catch( Exception e ){}
        } 
/////// VERIFICANDO REPETIÇÃO - CPF OU CNPJ ////////////////////////////////////////////////////////////////////////////////////////////////////////        
        
        if ( material_ja_Cadastrado == false ) {
            
            try{
                
                cadastrar_Estabelecimento();
                
            }catch( Exception e ){
                
                System.out.println("Erro ao cadastrar");
                e.printStackTrace();
            }
        }
        else{
            
            Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "CAMPO CPF OU CNPJ\n"
                    + "\n"
                    + "\nO CAMPO CPF OU CNPJ JÁ EXISTE!\n"
                    + "\n"
                    + "\nINFORME UM NOVO CPF OU CNPJ QUE NÃO EXISTA CADASTRADO!\n"
                    + "\n"
                    + "\nOK PARA PROSSEGUIR"
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }

    }
    
    private void cadastrar_Estabelecimento(){  
        try{
            
            try{ System.out.println( "\n-----------------------------------------------" ); }catch( Exception e ){}
            try{ System.out.println( Estabelecimento_Recebido.getPessoaFisica() ); }catch( Exception e ){}
            try{ System.out.println( Estabelecimento_Recebido.getPessoaJuridica()); }catch( Exception e ){}
            try{ System.out.println( Estabelecimento_Recebido.getNomeOuRazaoSocial()); }catch( Exception e ){}
            try{ System.out.println( Estabelecimento_Recebido.getCodigoDeIdentificacao()); }catch( Exception e ){}
            try{ System.out.println( Estabelecimento_Recebido.getNomeOuFantasia()); }catch( Exception e ){}
            try{ System.out.println( Estabelecimento_Recebido.getCpfOuCnpj()); }catch( Exception e ){}
            try{ System.out.println( Estabelecimento_Recebido.getDataCadastro()); }catch( Exception e ){}
            try{ System.out.println( Estabelecimento_Recebido.getIdUsuarioSistemaCadastroEstabelecimento()); }catch( Exception e ){}
            try{ System.out.println( Estabelecimento_Recebido.getDataAlteracao()); }catch( Exception e ){}
            try{ System.out.println( Estabelecimento_Recebido.getIdUsuarioSistemaAlteracaoEstabelecimento()); }catch( Exception e ){}
            try{ System.out.println( "-----------------------------------------------\n" ); }catch( Exception e ){}
            
            DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( Estabelecimento_Recebido, JPAUtil.em());
            Estabelecimento Estabelecimento_Cadastrado = (Estabelecimento) DAOGenericoJPA2.gravanovoOUatualizar();
            
            verificar_lista_de_Endereco_imagemExterna(Estabelecimento_Cadastrado);
            
            ///////////////////////////////////////////////////
            if(status_cadastro.equalsIgnoreCase("Cadastrando...")){                
                Estabelecimento_Recebido = Estabelecimento_Cadastrado;
                status_cadastro = "Visualizando...";
                setar_Dados_Iniciais();  
            }
            ///////////////////////////////////////////////////
            
            String rbusca = ""; 
            try{ rbusca = Estabelecimento_Recebido.getNomeOuRazaoSocial().toUpperCase(); }catch( Exception e ){}
        
            Class<br.com.jmary.home.imagens.Imagens_Internas> clazzHome = br.com.jmary.home.imagens.Imagens_Internas.class;
            JOPM JOptionPaneMod = new JOPM( 1, "CADASTRO\n"
                    + "\n"
                    + "\nSTATUS DO CADASTRO\n"
                    + "\n"
                    + "\nESTABELECIMENTO "+ rbusca 
                    + "\nCADASTRADO COM SUCESSO!\n"
                    + "\n"
                    + "\nOK PARA PROSSEGUIR"
                    ,"Class: " + this.getClass().getName(), 
                    new ImageIcon( clazzHome.getResource("logocangaco2.png")) );
        }catch( Exception e ){
            
            System.out.println("Erro ao cadastrar");
            e.printStackTrace();
        }
    }
    
    private void verificar_lista_de_Endereco_imagemExterna(Estabelecimento Estabelecimento_Cadastrado){
        try{
            
            for (int i=0; i < lista_de_Endereco_imagemExterna_.size(); i++) {
                
                File imagem = new File( lista_de_Endereco_imagemExterna_.get(i) ); 
                
                //salvar_imagemExterna(UsuarioSistema_Cadastrado, imagem);
            }
        }catch( Exception e ){}
    }
    
    /*
    private void salvar_imagemExterna(Estabelecimento UsuarioSistema_Cadastrado, File imagemExterna_Endereco){ 

        try { 
            
            BufferedImage bufImg = null;
            ImageIcon     icon   = null;
            Image         image  = null;
            try{
                
                bufImg = ImageIO.read( imagemExterna_Endereco );
                icon   = new ImageIcon(bufImg);
                image  = icon.getImage();//ImageIO.read(f);  
            } catch (IOException ex) {}  

            
                String nome = imagemExterna_Endereco.getName();
                String nomeList[] = nome.split(Pattern.quote("."));
                
                EstabelecimentoImagens UsuarioImagens = new EstabelecimentoImagens();
                
                ByteArrayOutputStream bytesImg = new ByteArrayOutputStream();
                String extencao = imagemExterna_Endereco.getPath().substring( imagemExterna_Endereco.getPath().lastIndexOf('.') + 1 ); 
                
                ImageIO.write((BufferedImage)image, extencao, bytesImg);//seta a imagem para bytesImg
                bytesImg.flush();//limpa a variável
                byte[] byteArray = bytesImg.toByteArray();//Converte ByteArrayOutputStream para byte[] 
                bytesImg.close();//fecha a conversão
                
                UsuarioImagens.setImagem(byteArray);
                
                try{ UsuarioImagens.setIdUsuarioSistema( UsuarioSistema_Cadastrado.getId() ); }catch( Exception e ){}
                try{ UsuarioImagens.setNome( nomeList[0] ); }catch( Exception e ){}
                
                try{
                    DAOGenericoJPA DAOGenericoJPA2 = new DAOGenericoJPA( UsuarioImagens, JPAUtil.em());
                    EstabelecimentoImagens UsuarioImagens_Cadastrado = (EstabelecimentoImagens) DAOGenericoJPA2.gravanovoOUatualizar();

                }catch( Exception e ){}
                
        } catch( Exception e ){ 
        
            JOPM JOptionPaneMod = new JOPM( 2, "ERRO AO CADASTRAR, 2"
                + "\nNÃO CADASTRADO"
                + "\nNenhum dado CADASTRADO."
                + "\n", "CADASTRAR - salvar_imagemExterna" );
        } 
    }
    */
/// CADASTRANDO ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
       
}