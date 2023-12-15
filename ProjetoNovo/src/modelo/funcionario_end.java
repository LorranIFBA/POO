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
public class funcionario_end {
    private String id_funcionario;
    private String uf;
    private String cidade;
    private String cep;
    private String rua;
    private String inf_add;

    @Override
    public String toString() {
        return "funcionario_end{" + "id_funcionario=" + id_funcionario + ", uf=" + uf + ", cidade=" + cidade + ", cep=" + cep + ", rua=" + rua + ", inf_add=" + inf_add + '}';
    }

    public String getId_funcionario() {
        return id_funcionario;
    }

    public String getUf() {
        return uf;
    }

    public String getCidade() {
        return cidade;
    }

    public String getCep() {
        return cep;
    }

    public String getRua() {
        return rua;
    }

    public String getInf_add() {
        return inf_add;
    }

    public void setId_funcionario(String id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public void setInf_add(String inf_add) {
        this.inf_add = inf_add;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.id_funcionario);
        hash = 23 * hash + Objects.hashCode(this.uf);
        hash = 23 * hash + Objects.hashCode(this.cidade);
        hash = 23 * hash + Objects.hashCode(this.cep);
        hash = 23 * hash + Objects.hashCode(this.rua);
        hash = 23 * hash + Objects.hashCode(this.inf_add);
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
        final funcionario_end other = (funcionario_end) obj;
        if (!Objects.equals(this.id_funcionario, other.id_funcionario)) {
            return false;
        }
        if (!Objects.equals(this.uf, other.uf)) {
            return false;
        }
        if (!Objects.equals(this.cidade, other.cidade)) {
            return false;
        }
        if (!Objects.equals(this.cep, other.cep)) {
            return false;
        }
        if (!Objects.equals(this.rua, other.rua)) {
            return false;
        }
        return Objects.equals(this.inf_add, other.inf_add);
    }
    
}
