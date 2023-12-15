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
public class funcionario_adm {
    private String id_funcionario;
    private String data_comeco;
    private String data_fim;
    private String salario;

    @Override
    public String toString() {
        return "funcionario_adm{" + "id_funcionario=" + id_funcionario + ", data_comeco=" + data_comeco + ", data_fim=" + data_fim + ", salario=" + salario + '}';
    }

    public String getId_funcionario() {
        return id_funcionario;
    }

    public String getData_comeco() {
        return data_comeco;
    }

    public String getData_fim() {
        return data_fim;
    }

    public String getSalario() {
        return salario;
    }

    public void setId_funcionario(String id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public void setData_comeco(String data_comeco) {
        this.data_comeco = data_comeco;
    }

    public void setData_fim(String data_fim) {
        this.data_fim = data_fim;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.id_funcionario);
        hash = 31 * hash + Objects.hashCode(this.data_comeco);
        hash = 31 * hash + Objects.hashCode(this.data_fim);
        hash = 31 * hash + Objects.hashCode(this.salario);
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
        final funcionario_adm other = (funcionario_adm) obj;
        if (!Objects.equals(this.id_funcionario, other.id_funcionario)) {
            return false;
        }
        if (!Objects.equals(this.data_comeco, other.data_comeco)) {
            return false;
        }
        if (!Objects.equals(this.data_fim, other.data_fim)) {
            return false;
        }
        return Objects.equals(this.salario, other.salario);
    }
    
}
