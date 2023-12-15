/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import modelo.funcionario_end;

/**
 *
 * @author Lorran
 */
public interface funcionario_endDAO {
    public int inserir(funcionario_end funcionario);
    public int editar(funcionario_end funcionario);
    public int apagar(int id);
    public List<funcionario_end> listar();
    public funcionario_end selecionar(String id);    
}
