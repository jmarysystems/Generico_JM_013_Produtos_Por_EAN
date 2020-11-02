/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produtos_por_ean_beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Ariel Arcanjo
 */
@Entity
@Table(name = "PRODUTOS_POR_EAN", catalog = "", schema = "JM")
@NamedQueries({
    @NamedQuery(name = "ProdutosPorEan.findAll", query = "SELECT p FROM ProdutosPorEan p")
    , @NamedQuery(name = "ProdutosPorEan.findById", query = "SELECT p FROM ProdutosPorEan p WHERE p.id = :id")
    , @NamedQuery(name = "ProdutosPorEan.findByCodigoAuxiliar", query = "SELECT p FROM ProdutosPorEan p WHERE p.codigoAuxiliar = :codigoAuxiliar")
    , @NamedQuery(name = "ProdutosPorEan.findByEan", query = "SELECT p FROM ProdutosPorEan p WHERE p.ean = :ean")
    , @NamedQuery(name = "ProdutosPorEan.findByDescricao", query = "SELECT p FROM ProdutosPorEan p WHERE p.descricao = :descricao")
    , @NamedQuery(name = "ProdutosPorEan.findByDataCadastro", query = "SELECT p FROM ProdutosPorEan p WHERE p.dataCadastro = :dataCadastro")
    , @NamedQuery(name = "ProdutosPorEan.findByIdUsuarioSistemaCadastroProdutosPorEan", query = "SELECT p FROM ProdutosPorEan p WHERE p.idUsuarioSistemaCadastroProdutosPorEan = :idUsuarioSistemaCadastroProdutosPorEan")
    , @NamedQuery(name = "ProdutosPorEan.findByDataAlteracao", query = "SELECT p FROM ProdutosPorEan p WHERE p.dataAlteracao = :dataAlteracao")
    , @NamedQuery(name = "ProdutosPorEan.findByIdUsuarioSistemaAlteracaoProdutosPorEan", query = "SELECT p FROM ProdutosPorEan p WHERE p.idUsuarioSistemaAlteracaoProdutosPorEan = :idUsuarioSistemaAlteracaoProdutosPorEan")})
public class ProdutosPorEan implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "CODIGO_AUXILIAR", length = 150)
    private String codigoAuxiliar;
    @Basic(optional = false)
    @Column(name = "EAN", nullable = false, length = 150)
    private String ean;
    @Basic(optional = false)
    @Column(name = "DESCRICAO", nullable = false, length = 500)
    private String descricao;
    @Basic(optional = false)
    @Column(name = "DATA_CADASTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @Basic(optional = false)
    @Column(name = "ID_USUARIO_SISTEMA_CADASTRO_PRODUTOS_POR_EAN", nullable = false)
    private int idUsuarioSistemaCadastroProdutosPorEan;
    @Basic(optional = false)
    @Column(name = "DATA_ALTERACAO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Basic(optional = false)
    @Column(name = "ID_USUARIO_SISTEMA_ALTERACAO_PRODUTOS_POR_EAN", nullable = false)
    private int idUsuarioSistemaAlteracaoProdutosPorEan;

    public ProdutosPorEan() {
    }

    public ProdutosPorEan(Integer id) {
        this.id = id;
    }

    public ProdutosPorEan(Integer id, String ean, String descricao, Date dataCadastro, int idUsuarioSistemaCadastroProdutosPorEan, Date dataAlteracao, int idUsuarioSistemaAlteracaoProdutosPorEan) {
        this.id = id;
        this.ean = ean;
        this.descricao = descricao;
        this.dataCadastro = dataCadastro;
        this.idUsuarioSistemaCadastroProdutosPorEan = idUsuarioSistemaCadastroProdutosPorEan;
        this.dataAlteracao = dataAlteracao;
        this.idUsuarioSistemaAlteracaoProdutosPorEan = idUsuarioSistemaAlteracaoProdutosPorEan;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public String getCodigoAuxiliar() {
        return codigoAuxiliar;
    }

    public void setCodigoAuxiliar(String codigoAuxiliar) {
        String oldCodigoAuxiliar = this.codigoAuxiliar;
        this.codigoAuxiliar = codigoAuxiliar;
        changeSupport.firePropertyChange("codigoAuxiliar", oldCodigoAuxiliar, codigoAuxiliar);
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        String oldEan = this.ean;
        this.ean = ean;
        changeSupport.firePropertyChange("ean", oldEan, ean);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        changeSupport.firePropertyChange("descricao", oldDescricao, descricao);
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        Date oldDataCadastro = this.dataCadastro;
        this.dataCadastro = dataCadastro;
        changeSupport.firePropertyChange("dataCadastro", oldDataCadastro, dataCadastro);
    }

    public int getIdUsuarioSistemaCadastroProdutosPorEan() {
        return idUsuarioSistemaCadastroProdutosPorEan;
    }

    public void setIdUsuarioSistemaCadastroProdutosPorEan(int idUsuarioSistemaCadastroProdutosPorEan) {
        int oldIdUsuarioSistemaCadastroProdutosPorEan = this.idUsuarioSistemaCadastroProdutosPorEan;
        this.idUsuarioSistemaCadastroProdutosPorEan = idUsuarioSistemaCadastroProdutosPorEan;
        changeSupport.firePropertyChange("idUsuarioSistemaCadastroProdutosPorEan", oldIdUsuarioSistemaCadastroProdutosPorEan, idUsuarioSistemaCadastroProdutosPorEan);
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        Date oldDataAlteracao = this.dataAlteracao;
        this.dataAlteracao = dataAlteracao;
        changeSupport.firePropertyChange("dataAlteracao", oldDataAlteracao, dataAlteracao);
    }

    public int getIdUsuarioSistemaAlteracaoProdutosPorEan() {
        return idUsuarioSistemaAlteracaoProdutosPorEan;
    }

    public void setIdUsuarioSistemaAlteracaoProdutosPorEan(int idUsuarioSistemaAlteracaoProdutosPorEan) {
        int oldIdUsuarioSistemaAlteracaoProdutosPorEan = this.idUsuarioSistemaAlteracaoProdutosPorEan;
        this.idUsuarioSistemaAlteracaoProdutosPorEan = idUsuarioSistemaAlteracaoProdutosPorEan;
        changeSupport.firePropertyChange("idUsuarioSistemaAlteracaoProdutosPorEan", oldIdUsuarioSistemaAlteracaoProdutosPorEan, idUsuarioSistemaAlteracaoProdutosPorEan);
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
        if (!(object instanceof ProdutosPorEan)) {
            return false;
        }
        ProdutosPorEan other = (ProdutosPorEan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "produtos_beans.ProdutosPorEan[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
