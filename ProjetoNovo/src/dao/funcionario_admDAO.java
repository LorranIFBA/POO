/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import modelo.funcionario_adm;

/**
 *
 * @author Lorran
 */
public interface funcionario_admDAO {
    public int inserir(funcionario_adm funcionario);
    public int editar(funcionario_adm funcionario);
    public int apagar(int id);
    public List<funcionario_adm> listar();
    public funcionario_adm selecionar(String id);
}
