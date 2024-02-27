/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import conexao.ConexaoMySQL;
import dao.DaoFactory;
import dao.funcionario_admDAO;
import dao.funcionario_endDAO;
import dao.funcionario_pesDAO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import modelo.funcionario_pes;

/**
 *
 * @author Lorran
 */
public class frmTabela extends javax.swing.JFrame {

    /**
     * Creates new form frmTabela
     */
    
    Connection conexao = null;
    private final DefaultTableModel modelo;
    funcionario_admDAO funcionario_adm = DaoFactory.criarFuncionario_admDAO();
    funcionario_pesDAO funcionario_pes = DaoFactory.criarFuncionario_pesDAO();
    funcionario_endDAO funcionario_end = DaoFactory.criarFuncionario_endDAO();
    
    public frmTabela() throws SQLException {
        initComponents();
        
        tblFuncionarios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelo = (DefaultTableModel) tblFuncionarios.getModel();
        
        try{
            conexao = ConexaoMySQL.getConexao();
        } catch (SQLException | ClassNotFoundException ex) {
            lblAlerta.setText("Falha na conexão.");
            Logger.getLogger(frmTabela.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void preencherTabela(){
        modelo.getDataVector().clear();
        
        
        try {
            for(funcionario_pes funcionario: funcionario_pes.listar()){
                modelo.addRow(new Object[]{funcionario.getId_funcionario(), funcionario.getNome()});
            }
        } catch (Exception e){
            throw e;
        }
    }
    
    private void editar(){
        try{
            String codigo = (String) modelo.getValueAt(tblFuncionarios.getSelectedRow(), 0);
            new frmEditar2(codigo).setVisible(true);
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Por favor, selecionar uma linha da tabela");
        }
    }
    
    private void apagar(){
        try{
            Integer codigo = Integer.parseInt((String) modelo.getValueAt(tblFuncionarios.getSelectedRow(), 0));
            
            int linha2 = funcionario_pes.apagar(codigo);
            int linha3 = funcionario_end.apagar(codigo);
            int linha1 = funcionario_adm.apagar(codigo);
            if (linha1 > 0 && linha2 > 0 && linha3 > 0){
                JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");
                modelo.removeRow(tblFuncionarios.getSelectedRow());
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao excluir.");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(this, "Por favor, selecionar uma linha da tabela.");
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblFuncionarios = new javax.swing.JTable();
        btAdd = new javax.swing.JButton();
        btEdt = new javax.swing.JButton();
        btApg = new javax.swing.JButton();
        btFechar = new javax.swing.JButton();
        lblAlerta = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        tblFuncionarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Nome"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblFuncionarios.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(tblFuncionarios);
        tblFuncionarios.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        btAdd.setText("Adicionar");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        btEdt.setText("Editar");
        btEdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEdtActionPerformed(evt);
            }
        });

        btApg.setText("Apagar");
        btApg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btApgActionPerformed(evt);
            }
        });

        btFechar.setText("Fechar");
        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFecharActionPerformed(evt);
            }
        });

        lblAlerta.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btAdd)
                    .addComponent(btEdt)
                    .addComponent(btApg)
                    .addComponent(btFechar)
                    .addComponent(lblAlerta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btAdd, btApg, btEdt, btFechar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btEdt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btApg)
                        .addGap(83, 83, 83)
                        .addComponent(lblAlerta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btFechar))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btAdd, btApg, btEdt, btFechar});

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEdtActionPerformed
        // TODO add your handling code here:
        editar();
    }//GEN-LAST:event_btEdtActionPerformed

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
        // TODO add your handling code here:
        preencherTabela();
    }//GEN-LAST:event_formWindowGainedFocus

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFecharActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btFecharActionPerformed

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        // TODO add your handling code here:
        new frmFuncionarioADM().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btAddActionPerformed

    private void btApgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btApgActionPerformed
        // TODO add your handling code here:
        apagar();
    }//GEN-LAST:event_btApgActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(frmTabela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmTabela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmTabela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmTabela.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmTabela().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(frmTabela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btApg;
    private javax.swing.JButton btEdt;
    private javax.swing.JButton btFechar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAlerta;
    private javax.swing.JTable tblFuncionarios;
    // End of variables declaration//GEN-END:variables
}
