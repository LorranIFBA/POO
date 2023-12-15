/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
 

public class ConexaoMySQL {
    
    private static final String USUARIO = "root";
    private static final String SENHA = "123456";
    private static final String URL_BANCO = "jdbc:mysql://localhost:3306/funcionarios";
    
    public static Connection getConexao() throws SQLException, ClassNotFoundException {
        //Faz com que a classe seja carregada pela JVM

        return DriverManager.getConnection(URL_BANCO, USUARIO, SENHA);
    }
    
}
//com.mysql.jdbc.Driver private static final String URL_BANCO = "jdbc:mysql://localhost:3306/funcionario?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
