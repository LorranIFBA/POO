/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author Lorran
 */
public class DaoFactory {
    public static funcionario_admDAO criarFuncionario_admDAO(){
        return new funcionario_admDAOJDBC();
    }
    
    public static funcionario_pesDAO criarFuncionario_pesDAO(){
        return new funcionario_pesDAOJDBC();
    }
    
    public static funcionario_endDAO criarFuncionario_endDAO(){
        return new funcionario_endDAOJDBC();
    }
}
