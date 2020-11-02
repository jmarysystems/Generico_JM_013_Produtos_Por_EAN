package produtos_por_ean;

import br.com.jmary.home.jpa.DB_Bean;
import produtos_por_ean_beans.ProdutosPorEan;

/**
 *
 * @author ana
 */
public class Banco_Ctrl_Tabela_BD {
    
    // VERIFICAR A TABELA BEANS
    // @Table(name = "PRODUTOS", catalog = "", schema = "JM")
    
    public static ProdutosPorEan Classe_Bean_Recebida;
    
    public static String schema = "JM."; //"alone"; //network
    public static String tabela = "PRODUTOS_POR_EAN"; //"derby"; "mysql"
    public static String tabela_imagen = "PRODUTOS_POR_EAN_IMAGENS"; //"derby"; "mysql"

    public static String get() {
        
        if( DB_Bean.tipoDeBanco.equals( "derby" ) ){
            
            return schema+tabela;
        }
        else{
            
            return tabela;
        }
    }
    
    public static String get_schema() {
        
        if( DB_Bean.tipoDeBanco.equals( "derby" ) ){
            
            return schema;
        }
        else{
            
            return "";
        }
    }
    
    public static String get_imagem() {
        
        if( DB_Bean.tipoDeBanco.equals( "derby" ) ){
            
            return schema+tabela_imagen;
        }
        else{
            
            return tabela_imagen;
        }
    }
        
}
