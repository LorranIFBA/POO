/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import conexao.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.funcionario_end;

/**
 *
 * @author Lorran
 */
public class funcionario_endDAOJDBC implements funcionario_endDAO{
    
    Connection conexao = null;
    PreparedStatement sql = null;
    ResultSet rset = null;

    @Override
    public int inserir(funcionario_end funcionario) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("INSERT INTO funcionario_end(id_funcionario, uf, cidade, cep, rua, inf_add)")
                .append("VALUES (?, ?, ?, ?, ?, ?)");
        
        String insert = sqlBuilder.toString();
        int linha = 0;
        try {
            conexao = (Connection) ConexaoMySQL.getConexao();
            sql = (PreparedStatement) conexao.prepareStatement(insert);
            
            sql.setInt(1,Integer.parseInt(funcionario.getId_funcionario()));
            sql.setString(2, funcionario.getUf());
            sql.setString(3, funcionario.getCidade());
            sql.setString(4, funcionario.getCep());
            sql.setString(5, funcionario.getRua());
            sql.setString(6,funcionario.getInf_add());
            linha = sql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
        
        return linha;
    }

    @Override
    public int editar(funcionario_end funcionario) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE funcionario_end SET ")
                .append("uf = ?,")
                .append("cidade = ?,")
                .append("cep = ?,")
                .append("rua = ?,")
                .append("inf_add = ?")
                .append(" WHERE id_funcionario = ?");
        
        String update = sqlBuilder.toString();
        int linha = 0;
        try {
            conexao = (Connection) ConexaoMySQL.getConexao();


            sql = (PreparedStatement) conexao.prepareStatement(update);
            
            
            sql.setString(1, funcionario.getUf());
            sql.setString(2, funcionario.getCidade());
            sql.setString(3, funcionario.getCep());
            sql.setString(4, funcionario.getRua());
            sql.setString(5, funcionario.getInf_add());
            sql.setInt(6,Integer.parseInt(funcionario.getId_funcionario()));
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
                .append("DELETE FROM funcionario_end ")
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
    public List<funcionario_end> listar() {
        String select = "SELECT * FROM funcionario_end";
                        
        List<funcionario_end> funcionarios = new ArrayList<funcionario_end>();

        try {
            conexao = (Connection) ConexaoMySQL.getConexao();
            sql = (PreparedStatement) conexao.prepareStatement(select);
            rset = sql.executeQuery();

            while (rset.next()) {

                funcionario_end funcionario = new funcionario_end();

                
                funcionario.setId_funcionario(Integer.toString(rset.getInt("id_funcionario")));
                funcionario.setUf(rset.getString("uf"));
                funcionario.setCidade(rset.getString("cidade"));
                funcionario.setCep(rset.getString("cep"));
                funcionario.setRua(rset.getString("rua"));
                funcionario.setInf_add(rset.getString("inf_add"));
                
                                
                funcionarios.add(funcionario);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }

        return funcionarios;
    }
    
    
    public funcionario_end selecionar(String id){
        String select = "SELECT * FROM funcionario_end WHERE id_funcionario = ?";
        funcionario_end funcionario = new funcionario_end();
         
        try {
            conexao = ConexaoMySQL.getConexao();
            sql = (PreparedStatement) conexao.prepareStatement(select);
            sql.setInt(1, Integer.parseInt(id));
            rset = sql.executeQuery();
            
            while (rset.next()){
                funcionario.setId_funcionario(id);
            
                funcionario.setCep(rset.getString("cep"));
                funcionario.setCidade(rset.getString("cidade"));
                funcionario.setInf_add(rset.getString("inf_add"));
                funcionario.setRua(rset.getString("rua"));
                funcionario.setUf(rset.getString("uf"));    
            }
            
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
        
        return funcionario;
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
