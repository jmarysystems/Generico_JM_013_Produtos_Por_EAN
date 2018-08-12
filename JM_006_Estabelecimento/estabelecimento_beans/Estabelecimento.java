/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estabelecimento_beans;

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
 * @author NewUser
 */
@Entity
@Table(name = "ESTABELECIMENTO", catalog = "", schema = "JM")
@NamedQueries({
    @NamedQuery(name = "Estabelecimento.findAll", query = "SELECT e FROM Estabelecimento e")
    , @NamedQuery(name = "Estabelecimento.findById", query = "SELECT e FROM Estabelecimento e WHERE e.id = :id")
    , @NamedQuery(name = "Estabelecimento.findByPessoaFisica", query = "SELECT e FROM Estabelecimento e WHERE e.pessoaFisica = :pessoaFisica")
    , @NamedQuery(name = "Estabelecimento.findByPessoaJuridica", query = "SELECT e FROM Estabelecimento e WHERE e.pessoaJuridica = :pessoaJuridica")
    , @NamedQuery(name = "Estabelecimento.findByNomeOuRazaoSocial", query = "SELECT e FROM Estabelecimento e WHERE e.nomeOuRazaoSocial = :nomeOuRazaoSocial")
    , @NamedQuery(name = "Estabelecimento.findByCodigoDeIdentificacao", query = "SELECT e FROM Estabelecimento e WHERE e.codigoDeIdentificacao = :codigoDeIdentificacao")
    , @NamedQuery(name = "Estabelecimento.findByNomeOuFantasia", query = "SELECT e FROM Estabelecimento e WHERE e.nomeOuFantasia = :nomeOuFantasia")
    , @NamedQuery(name = "Estabelecimento.findByCpfOuCnpj", query = "SELECT e FROM Estabelecimento e WHERE e.cpfOuCnpj = :cpfOuCnpj")
    , @NamedQuery(name = "Estabelecimento.findByDataCadastro", query = "SELECT e FROM Estabelecimento e WHERE e.dataCadastro = :dataCadastro")
    , @NamedQuery(name = "Estabelecimento.findByIdUsuarioSistemaCadastroEstabelecimento", query = "SELECT e FROM Estabelecimento e WHERE e.idUsuarioSistemaCadastroEstabelecimento = :idUsuarioSistemaCadastroEstabelecimento")
    , @NamedQuery(name = "Estabelecimento.findByDataAlteracao", query = "SELECT e FROM Estabelecimento e WHERE e.dataAlteracao = :dataAlteracao")
    , @NamedQuery(name = "Estabelecimento.findByIdUsuarioSistemaAlteracaoEstabelecimento", query = "SELECT e FROM Estabelecimento e WHERE e.idUsuarioSistemaAlteracaoEstabelecimento = :idUsuarioSistemaAlteracaoEstabelecimento")})
public class Estabelecimento implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "PESSOA_FISICA", nullable = false)
    private Boolean pessoaFisica;
    @Basic(optional = false)
    @Column(name = "PESSOA_JURIDICA", nullable = false)
    private Boolean pessoaJuridica;
    @Basic(optional = false)
    @Column(name = "NOME_OU_RAZAO_SOCIAL", nullable = false, length = 200)
    private String nomeOuRazaoSocial;
    @Basic(optional = false)
    @Column(name = "CODIGO_DE_IDENTIFICACAO", nullable = false, length = 20)
    private String codigoDeIdentificacao;
    @Column(name = "NOME_OU_FANTASIA", length = 100)
    private String nomeOuFantasia;
    @Column(name = "CPF_OU_CNPJ", length = 20)
    private String cpfOuCnpj;
    @Basic(optional = false)
    @Column(name = "DATA_CADASTRO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCadastro;
    @Basic(optional = false)
    @Column(name = "ID_USUARIO_SISTEMA_CADASTRO_ESTABELECIMENTO", nullable = false)
    private int idUsuarioSistemaCadastroEstabelecimento;
    @Basic(optional = false)
    @Column(name = "DATA_ALTERACAO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataAlteracao;
    @Basic(optional = false)
    @Column(name = "ID_USUARIO_SISTEMA_ALTERACAO_ESTABELECIMENTO", nullable = false)
    private int idUsuarioSistemaAlteracaoEstabelecimento;

    public Estabelecimento() {
    }

    public Estabelecimento(Integer id) {
        this.id = id;
    }

    public Estabelecimento(Integer id, Boolean pessoaFisica, Boolean pessoaJuridica, String nomeOuRazaoSocial, String codigoDeIdentificacao, Date dataCadastro, int idUsuarioSistemaCadastroEstabelecimento, Date dataAlteracao, int idUsuarioSistemaAlteracaoEstabelecimento) {
        this.id = id;
        this.pessoaFisica = pessoaFisica;
        this.pessoaJuridica = pessoaJuridica;
        this.nomeOuRazaoSocial = nomeOuRazaoSocial;
        this.codigoDeIdentificacao = codigoDeIdentificacao;
        this.dataCadastro = dataCadastro;
        this.idUsuarioSistemaCadastroEstabelecimento = idUsuarioSistemaCadastroEstabelecimento;
        this.dataAlteracao = dataAlteracao;
        this.idUsuarioSistemaAlteracaoEstabelecimento = idUsuarioSistemaAlteracaoEstabelecimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        Integer oldId = this.id;
        this.id = id;
        changeSupport.firePropertyChange("id", oldId, id);
    }

    public Boolean getPessoaFisica() {
        return pessoaFisica;
    }

    public void setPessoaFisica(Boolean pessoaFisica) {
        Boolean oldPessoaFisica = this.pessoaFisica;
        this.pessoaFisica = pessoaFisica;
        changeSupport.firePropertyChange("pessoaFisica", oldPessoaFisica, pessoaFisica);
    }

    public Boolean getPessoaJuridica() {
        return pessoaJuridica;
    }

    public void setPessoaJuridica(Boolean pessoaJuridica) {
        Boolean oldPessoaJuridica = this.pessoaJuridica;
        this.pessoaJuridica = pessoaJuridica;
        changeSupport.firePropertyChange("pessoaJuridica", oldPessoaJuridica, pessoaJuridica);
    }

    public String getNomeOuRazaoSocial() {
        return nomeOuRazaoSocial;
    }

    public void setNomeOuRazaoSocial(String nomeOuRazaoSocial) {
        String oldNomeOuRazaoSocial = this.nomeOuRazaoSocial;
        this.nomeOuRazaoSocial = nomeOuRazaoSocial;
        changeSupport.firePropertyChange("nomeOuRazaoSocial", oldNomeOuRazaoSocial, nomeOuRazaoSocial);
    }

    public String getCodigoDeIdentificacao() {
        return codigoDeIdentificacao;
    }

    public void setCodigoDeIdentificacao(String codigoDeIdentificacao) {
        String oldCodigoDeIdentificacao = this.codigoDeIdentificacao;
        this.codigoDeIdentificacao = codigoDeIdentificacao;
        changeSupport.firePropertyChange("codigoDeIdentificacao", oldCodigoDeIdentificacao, codigoDeIdentificacao);
    }

    public String getNomeOuFantasia() {
        return nomeOuFantasia;
    }

    public void setNomeOuFantasia(String nomeOuFantasia) {
        String oldNomeOuFantasia = this.nomeOuFantasia;
        this.nomeOuFantasia = nomeOuFantasia;
        changeSupport.firePropertyChange("nomeOuFantasia", oldNomeOuFantasia, nomeOuFantasia);
    }

    public String getCpfOuCnpj() {
        return cpfOuCnpj;
    }

    public void setCpfOuCnpj(String cpfOuCnpj) {
        String oldCpfOuCnpj = this.cpfOuCnpj;
        this.cpfOuCnpj = cpfOuCnpj;
        changeSupport.firePropertyChange("cpfOuCnpj", oldCpfOuCnpj, cpfOuCnpj);
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        Date oldDataCadastro = this.dataCadastro;
        this.dataCadastro = dataCadastro;
        changeSupport.firePropertyChange("dataCadastro", oldDataCadastro, dataCadastro);
    }

    public int getIdUsuarioSistemaCadastroEstabelecimento() {
        return idUsuarioSistemaCadastroEstabelecimento;
    }

    public void setIdUsuarioSistemaCadastroEstabelecimento(int idUsuarioSistemaCadastroEstabelecimento) {
        int oldIdUsuarioSistemaCadastroEstabelecimento = this.idUsuarioSistemaCadastroEstabelecimento;
        this.idUsuarioSistemaCadastroEstabelecimento = idUsuarioSistemaCadastroEstabelecimento;
        changeSupport.firePropertyChange("idUsuarioSistemaCadastroEstabelecimento", oldIdUsuarioSistemaCadastroEstabelecimento, idUsuarioSistemaCadastroEstabelecimento);
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }

    public void setDataAlteracao(Date dataAlteracao) {
        Date oldDataAlteracao = this.dataAlteracao;
        this.dataAlteracao = dataAlteracao;
        changeSupport.firePropertyChange("dataAlteracao", oldDataAlteracao, dataAlteracao);
    }

    public int getIdUsuarioSistemaAlteracaoEstabelecimento() {
        return idUsuarioSistemaAlteracaoEstabelecimento;
    }

    public void setIdUsuarioSistemaAlteracaoEstabelecimento(int idUsuarioSistemaAlteracaoEstabelecimento) {
        int oldIdUsuarioSistemaAlteracaoEstabelecimento = this.idUsuarioSistemaAlteracaoEstabelecimento;
        this.idUsuarioSistemaAlteracaoEstabelecimento = idUsuarioSistemaAlteracaoEstabelecimento;
        changeSupport.firePropertyChange("idUsuarioSistemaAlteracaoEstabelecimento", oldIdUsuarioSistemaAlteracaoEstabelecimento, idUsuarioSistemaAlteracaoEstabelecimento);
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
        if (!(object instanceof Estabelecimento)) {
            return false;
        }
        Estabelecimento other = (Estabelecimento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "estabelecimento_beans.Estabelecimento[ id=" + id + " ]";
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
