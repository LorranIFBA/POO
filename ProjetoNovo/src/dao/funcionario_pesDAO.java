/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import modelo.funcionario_pes;

/**
 *
 * @author Lorran
 */
public interface funcionario_pesDAO {
    public int inserir(funcionario_pes funcionario);
    public int editar(funcionario_pes funcionario);
    public int apagar(int id);
    public List<funcionario_pes> listar();
    public funcionario_pes selecionar(String id);
}
