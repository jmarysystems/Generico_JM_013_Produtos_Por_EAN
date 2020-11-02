/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtos_por_ean_beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author Ariel Arcanjo
 */
@Entity
@Table(name = "PRODUTOS_POR_EAN_IMAGENS", catalog = "", schema = "JM")
@NamedQueries({
    @NamedQuery(name = "ProdutosPorEanImagens.findAll", query = "SELECT p FROM ProdutosPorEanImagens p")
    , @NamedQuery(name = "ProdutosPorEanImagens.findById", query = "SELECT p FROM ProdutosPorEanImagens p WHERE p.id = :id")
    , @NamedQuery(name = "ProdutosPorEanImagens.findByEan", query = "SELECT p FROM ProdutosPorEanImagens p WHERE p.ean = :ean")
    , @NamedQuery(name = "ProdutosPorEanImagens.findByNome", query = "SELECT p FROM ProdutosPorEanImagens p WHERE p.nome = :nome")
    , @NamedQuery(name = "ProdutosPorEanImagens.findByPrincipal", query = "SELECT p FROM ProdutosPorEanImagens p WHERE p.principal = :principal")
    , @NamedQuery(name = "ProdutosPorEanImagens.findByIdProdutosPorEanProdutosPorEanImagens", query = "SELECT p FROM ProdutosPorEanImagens p WHERE p.idProdutosPorEanProdutosPorEanImagens = :idProdutosPorEanProdutosPorEanImagens")})
public class ProdutosPorEanImagens implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "EAN", length = 50)
    private String ean;
    @Column(name = "NOME", length = 500)
    private String nome;
    @Lob
    @Column(name = "IMAGEM")
    private Serializable imagem;
    @Basic(optional = false)
    @Column(name = "PRINCIPAL", nullable = false)
    private Boolean principal;
    @Basic(optional = false)
    @Column(name = "ID_PRODUTOS_POR_EAN_PRODUTOS_POR_EAN_IMAGENS", nullable = false)
    private int idProdutosPorEanProdutosPorEanImagens;

    public ProdutosPorEanImagens() {
    }

    public ProdutosPorEanImagens(Integer id) {
        this.id = id;
    }

    public ProdutosPorEanImagens(Integer id, Boolean principal, int idProdutosPorEanProdutosPorEanImagens) {
        this.id = id;
        this.principal = principal;
        this.idProdutosPorEanProdutosPorEanImagens = idProdutosPorEanProdutosPorEanImagens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        String oldEan = this.ean;
        this.ean = ean;
        changeSupport.firePropertyChange("ean", oldEan, ean);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        String oldNome = this.nome;
        this.nome = nome;
        changeSupport.firePropertyChange("nome", oldNome, nome);
    }

    public Serializable getImagem() {
        return imagem;
    }

    public void setImagem(Serializable imagem) {
        Serializable oldImagem = this.imagem;
        this.imagem = imagem;
        changeSupport.firePropertyChange("imagem", oldImagem, imagem);
    }

    public Boolean getPrincipal() {
        return principal;
    }

    public void setPrincipal(Boolean principal) {
        Boolean oldPrincipal = this.principal;
        this.principal = principal;
        changeSupport.firePropertyChange("principal", oldPrincipal, principal);
    }

    public int getIdProdutosPorEanProdutosPorEanImagens() {
        return idProdutosPorEanProdutosPorEanImagens;
    }

    public void setIdProdutosPorEanProdutosPorEanImagens(int idProdutosPorEanProdutosPorEanImagens) {
        int oldIdProdutosPorEanProdutosPorEanImagens = this.idProdutosPorEanProdutosPorEanImagens;
        this.idProdutosPorEanProdutosPorEanImagens = idProdutosPorEanProdutosPorEanImagens;
        changeSupport.firePropertyChange("idProdutosPorEanProdutosPorEanImagens", oldIdProdutosPorEanProdutosPorEanImagens, idProdutosPorEanProdutosPorEanImagens);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProdutosPorEanImagens)) {
            return false;
        }
        ProdutosPorEanImagens other = (ProdutosPorEanImagens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "teste.ProdutosPorEanImagens[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
