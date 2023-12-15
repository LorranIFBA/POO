/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexao.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.funcionario_adm;

/**
 *
 * @author Lorran
 */
public class funcionario_admDAOJDBC implements funcionario_admDAO{
    
    Connection conexao = null;
    PreparedStatement sql = null;
    ResultSet rset = null;

    @Override
    public int inserir(funcionario_adm funcionario) {
        
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("INSERT INTO funcionario_adm(id_funcionario, data_comeco, data_fim, salario)")
                .append("VALUES (?, ?, ?, ?)");
        
        String insert = sqlBuilder.toString();
        int linha = 0;
        try {
            conexao = (Connection) ConexaoMySQL.getConexao();
            
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date data_c = formatter.parse(funcionario.getData_comeco());
            java.sql.Date sqlData_c = new java.sql.Date(data_c.getTime());            
            Date data_f = formatter.parse(funcionario.getData_fim());
            java.sql.Date sqlData_f = new java.sql.Date(data_f.getTime());
            

            sql = (PreparedStatement) conexao.prepareStatement(insert);
            
            sql.setInt(1,Integer.parseInt(funcionario.getId_funcionario()));
            sql.setDate(2, sqlData_c);
            sql.setDate(3, sqlData_f);
            sql.setInt(4, Integer.parseInt(funcionario.getSalario()));
            linha = sql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
        
        return linha;
        
        
    }

    @Override
    public int editar(funcionario_adm funcionario) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE funcionario_adm SET ")
                .append("data_comeco = ?,")
                .append("data_fim = ?,")
                .append("salario = ?")
                .append(" WHERE id_funcionario = ?");
        
        String update = sqlBuilder.toString();
        int linha = 0;
        try {
            conexao = (Connection) ConexaoMySQL.getConexao();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date data_c = formatter.parse(funcionario.getData_comeco());
            java.sql.Date sqlData_c = new java.sql.Date(data_c.getTime());            
            Date data_f = formatter.parse(funcionario.getData_fim());
            java.sql.Date sqlData_f = new java.sql.Date(data_f.getTime());

            sql = (PreparedStatement) conexao.prepareStatement(update);
            
            
            sql.setDate(1, sqlData_c);
            sql.setDate(2, sqlData_f);
            sql.setInt(3, Integer.parseInt(funcionario.getSalario()));
            sql.setInt(4, Integer.parseInt(funcionario.getId_funcionario()));
            linha = sql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }

        return linha;
    }

    @Override
    public int apagar(int id) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("DELETE FROM funcionario_adm ")
                .append("WHERE id_funcionario = ?");
        
        String delete = sqlBuilder.toString();
        int linha = 0;
        try {
            conexao = (Connection) ConexaoMySQL.getConexao();

            sql = (PreparedStatement) conexao.prepareStatement(delete);
            sql.setInt(1, id);
            linha = sql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }

        return linha;
    }

    @Override
    public List<funcionario_adm> listar() {
        String select = "SELECT * FROM funcionario_adm";
                        
        List<funcionario_adm> funcionarios = new ArrayList<funcionario_adm>();

        try {
            conexao = (Connection) ConexaoMySQL.getConexao();
            sql = (PreparedStatement) conexao.prepareStatement(select);
            rset = sql.executeQuery();

            while (rset.next()) {

                funcionario_adm funcionario = new funcionario_adm();
                String formato_data = "MM/dd/yyyy HH:mm:ss";
                DateFormat df = new SimpleDateFormat(formato_data);
                
                funcionario.setId_funcionario(Integer.toString(rset.getInt("id_funcionario")));
                funcionario.setData_comeco(df.format(rset.getDate("data_comeco")));
                funcionario.setData_fim(df.format(rset.getDate("data_fim")));
                funcionario.setSalario(Integer.toString(rset.getInt("salario")));
                                
                funcionarios.add(funcionario);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }

        return funcionarios;
    }
    
    public funcionario_adm selecionar(String id){
        String select = "SELECT * FROM funcionario_adm WHERE id_funcionario = ?";
        funcionario_adm funcionario = new funcionario_adm();
         
        try {
            conexao = ConexaoMySQL.getConexao();
            sql = (PreparedStatement) conexao.prepareStatement(select);
            sql.setInt(1, Integer.parseInt(id));
            rset = sql.executeQuery();
            
            while (rset.next()){
                funcionario.setId_funcionario(id);
            
                java.sql.Date dataCsql = rset.getDate("data_comeco");
                java.sql.Date dataFsql = rset.getDate("data_fim");
                String datacString = formataData(dataCsql);
                String datafString = formataData(dataFsql);
            
            
                funcionario.setData_comeco(datacString);
                funcionario.setData_fim(datafString);
            
                funcionario.setSalario("" + rset.getInt("salario"));    
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
        
        return funcionario;
    }
     
    
    private static String formataData(java.sql.Date data) {
        if (data != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(new Date(data.getTime()));
        } else {
            return null;
        }
    }
    
    private void fecharConexao() {
        try {
            if (rset != null) {
                rset.close();
            }
            if (sql != null) {
                sql.close();
            }

            if (conexao != null) {
                conexao.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
