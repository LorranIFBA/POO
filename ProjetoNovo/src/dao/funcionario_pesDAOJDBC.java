/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import modelo.funcionario_pes;
import conexao.ConexaoMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import modelo.funcionario_adm;

/**
 *
 * @author Lorran
 */
public class funcionario_pesDAOJDBC implements funcionario_pesDAO{
    Connection conexao = null;
    PreparedStatement sql = null;
    ResultSet rset = null;

    @Override
    public int inserir(funcionario_pes funcionario) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("INSERT INTO funcionario_pes(id_funcionario, nome, data_nasc, documento, sexo)")
                .append("VALUES (?, ?, ?, ?, ?)");
        
        String insert = sqlBuilder.toString();
        int linha = 0;
        try {
            conexao = (Connection) ConexaoMySQL.getConexao();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date data_n = formatter.parse(funcionario.getData_nasc());
            java.sql.Date sqlData_n = new java.sql.Date(data_n.getTime());            

            

            sql = (PreparedStatement) conexao.prepareStatement(insert);
            
            sql.setInt(1,Integer.parseInt(funcionario.getId_funcionario()));
            sql.setString(2,funcionario.getNome());
            sql.setDate(3, sqlData_n);
            sql.setString(4,funcionario.getDocumento());
            sql.setString(5, funcionario.getSexo());
            linha = sql.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }
        
        return linha;
    }

    @Override
    public int editar(funcionario_pes funcionario) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder
                .append("UPDATE funcionario_pes SET ")
                .append("nome = ?,")
                .append("data_nasc = ?,")
                .append("documento = ?,")
                .append("sexo = ?")
                .append(" WHERE id_funcionario = ?");
        
        String update = sqlBuilder.toString();
        int linha = 0;
        try {
            conexao = (Connection) ConexaoMySQL.getConexao();
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date data_n = formatter.parse(funcionario.getData_nasc());
            java.sql.Date sqlData_n = new java.sql.Date(data_n.getTime());            


            sql = (PreparedStatement) conexao.prepareStatement(update);
            
            
            sql.setString(1, funcionario.getNome());
            sql.setDate(2,sqlData_n);
            sql.setString(3, funcionario.getDocumento());
            sql.setString(4, funcionario.getSexo());
            sql.setInt(5, Integer.parseInt(funcionario.getId_funcionario()));
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
                .append("DELETE FROM funcionario_pes ")
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
    public List<funcionario_pes> listar() {
        String select = "SELECT * FROM funcionario_pes";
                        
        List<funcionario_pes> funcionarios = new ArrayList<funcionario_pes>();

        try {
            conexao = (Connection) ConexaoMySQL.getConexao();
            sql = (PreparedStatement) conexao.prepareStatement(select);
            rset = sql.executeQuery();

            while (rset.next()) {

                funcionario_pes funcionario = new funcionario_pes();
                String formato_data = "MM/dd/yyyy HH:mm:ss";
                DateFormat df = new SimpleDateFormat(formato_data);
                
                funcionario.setId_funcionario(Integer.toString(rset.getInt("id_funcionario")));
                funcionario.setData_nasc(df.format(rset.getDate("data_nasc")));
                funcionario.setNome(rset.getString("nome"));
                funcionario.setDocumento(rset.getString("documento"));
                funcionario.setSexo(rset.getString("sexo"));
                                
                funcionarios.add(funcionario);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fecharConexao();
        }

        return funcionarios;
    }
    
    public funcionario_pes selecionar(String id){
        String select = "SELECT * FROM funcionario_pes WHERE id_funcionario = ?";
        funcionario_pes funcionario = new funcionario_pes();
         
        try {
            conexao = ConexaoMySQL.getConexao();
            sql = (PreparedStatement) conexao.prepareStatement(select);
            sql.setInt(1, Integer.parseInt(id));
            rset = sql.executeQuery();
            

            while (rset.next()){
                funcionario.setId_funcionario(id);
            
                funcionario.setDocumento(rset.getString("documento"));
                funcionario.setNome(rset.getString("nome"));
                funcionario.setSexo(rset.getString("sexo"));
            
                java.sql.Date dataNsql = rset.getDate("data_nasc");
                String datanString = formataData(dataNsql);
            
                funcionario.setData_nasc(datanString);    
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
