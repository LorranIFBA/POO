/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.Objects;

/**
 *
 * @author Lorran
 */
public class funcionario_pes {
    private String id_funcionario;
    private String nome;
    private String Data_nasc;
    private String Documento;
    private String sexo;

    @Override
    public String toString() {
        return "funcionario_pes{" + "id_funcionario=" + id_funcionario + ", nome=" + nome + ", Data_nasc=" + Data_nasc + ", Documento=" + Documento + ", sexo=" + sexo + '}';
    }

    public String getId_funcionario() {
        return id_funcionario;
    }

    public String getNome() {
        return nome;
    }

    public String getData_nasc() {
        return Data_nasc;
    }

    public String getDocumento() {
        return Documento;
    }

    public void setId_funcionario(String id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setData_nasc(String Data_nasc) {
        this.Data_nasc = Data_nasc;
    }

    public void setDocumento(String Documento) {
        this.Documento = Documento;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.id_funcionario);
        hash = 43 * hash + Objects.hashCode(this.nome);
        hash = 43 * hash + Objects.hashCode(this.Data_nasc);
        hash = 43 * hash + Objects.hashCode(this.Documento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final funcionario_pes other = (funcionario_pes) obj;
        if (!Objects.equals(this.id_funcionario, other.id_funcionario)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.Data_nasc, other.Data_nasc)) {
            return false;
        }
        return Objects.equals(this.Documento, other.Documento);
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
}
