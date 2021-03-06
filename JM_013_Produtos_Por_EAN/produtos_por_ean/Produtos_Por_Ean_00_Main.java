/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.  
 */
package produtos_por_ean;

import br.com.jmary.home.Home;
import br.com.jmary.home.imagens.Imagens_Internas;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import login_do_sistema.Login_Do_Sistema;
import produtos_por_ean_menu_e_submenu.Produtos_Por_Ean_Menu_01;
import produtos_por_ean_menu_e_submenu.Produtos_Por_Ean_Submenu_01;
 
/** 
 *
 * @author NewUser
 */
public class Produtos_Por_Ean_00_Main extends JApplet {
    
    static JFrame frame;

    Home Home;
      
    @Override
    public void init() {

        Home = new Home( frame );
        
        add( Home ); //BorderLayout.CENTER
        
        Home.adicionar_Menu(new Produtos_Por_Ean_Menu_01( Home ).Barra_Menu );
        Home.adicionar_SubMenu(new Produtos_Por_Ean_Submenu_01( Home ) );
        Home.adicionar_Tela_De_Trabalho( "Login no Sistema", new Login_Do_Sistema(Home) );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
                } catch (Exception e) {}
                
                frame = new JFrame( "JMARYSYSTEMS - Suporte: 85 9.8193.1133", null );                
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JApplet applet = new Produtos_Por_Ean_00_Main();
        applet.init();
                
        frame.setContentPane( applet.getContentPane() );
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setIconImage(null);
                
        iconeDoPrograma( frame );
        telaCheia ( frame );

        applet.start();
            }
        });
    }
    
    public static void iniciarJFrame(){
        main(new String [2]);
    }
    
    private static void telaCheia( JFrame frame ) {
        try{ frame.setUndecorated(true);                                    } catch( Exception e ){}
        try{ frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME); } catch( Exception e ){}
        try{ frame.setExtendedState(JFrame.MAXIMIZED_BOTH);                 } catch( Exception e ){}
    }
    
    /* Início Setando O Ícone do Programa  */
    private static void iconeDoPrograma( JFrame frame ) {     
        try{ 
            BufferedImage bufImg = ImageIO.read(Imagens_Internas.class.getResource("jmol.png") );  
                        
            frame.setIconImage( bufImg ); //bi
        }catch( IOException e ){}      
    }
    /* Fim Setando O Ícone do Programa  */

    
}
