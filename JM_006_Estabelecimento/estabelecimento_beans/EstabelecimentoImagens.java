/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estabelecimento_beans;

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
 * @author NewUser
 */
@Entity
@Table(name = "ESTABELECIMENTO_IMAGENS", catalog = "", schema = "JM")
@NamedQueries({
    @NamedQuery(name = "EstabelecimentoImagens.findAll", query = "SELECT e FROM EstabelecimentoImagens e")
    , @NamedQuery(name = "EstabelecimentoImagens.findById", query = "SELECT e FROM EstabelecimentoImagens e WHERE e.id = :id")
    , @NamedQuery(name = "EstabelecimentoImagens.findByNome", query = "SELECT e FROM EstabelecimentoImagens e WHERE e.nome = :nome")
    , @NamedQuery(name = "EstabelecimentoImagens.findByIdEstabelecimento", query = "SELECT e FROM EstabelecimentoImagens e WHERE e.idEstabelecimento = :idEstabelecimento")})
public class EstabelecimentoImagens implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "NOME", length = 500)
    private String nome;
    @Lob
    @Column(name = "IMAGEM")
    private Serializable imagem;
    @Basic(optional = false)
    @Column(name = "ID_ESTABELECIMENTO", nullable = false)
    private int idEstabelecimento;

    public EstabelecimentoImagens() {
    }

    public EstabelecimentoImagens(Integer id) {
        this.id = id;
    }

    public EstabelecimentoImagens(Integer id, int idEstabelecimento) {
        this.id = id;
        this.idEstabelecimento = idEstabelecimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
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

    public int getIdEstabelecimento() {
        return idEstabelecimento;
    }

    public void setIdEstabelecimento(int idEstabelecimento) {
        int oldIdEstabelecimento = this.idEstabelecimento;
        this.idEstabelecimento = idEstabelecimento;
        changeSupport.firePropertyChange("idEstabelecimento", oldIdEstabelecimento, idEstabelecimento);
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
        if (!(object instanceof EstabelecimentoImagens)) {
            return false;
        }
        EstabelecimentoImagens other = (EstabelecimentoImagens) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "home.EstabelecimentoImagens[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
