/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import dao.DaoFactory;
import dao.funcionario_admDAO;
import dao.funcionario_endDAO;
import dao.funcionario_pesDAO;
import java.sql.SQLException;
import java.time.Year;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.funcionario_adm;
import modelo.funcionario_end;
import modelo.funcionario_pes;

/**
 *
 * @author Lorran
 */
public class frmNovoFuncionario extends javax.swing.JFrame {
    
    funcionario_admDAO funcionario1DAO = DaoFactory.criarFuncionario_admDAO();
    funcionario_pesDAO funcionario2DAO = DaoFactory.criarFuncionario_pesDAO();
    funcionario_endDAO funcionario3DAO = DaoFactory.criarFuncionario_endDAO();
    funcionario_adm funcionario = null;

    /**
     * Creates new form frFuncionario
     */
    public frmNovoFuncionario(funcionario_adm funcionario1) {
        initComponents();
        
        String[] dias = new String[31];
        for (int i = 0; i < 31; i++) {
            dias[i] = String.valueOf(i + 1);
        }
        String[] mes = new String[12];
        for (int i = 0; i < 12; i++) {
            mes[i] = String.valueOf(i + 1);
        }
        int anoAtual = Year.now().getValue();
        String[] ano = new String[101];
        for (int i = 0; i <= 100; i++) {
            ano[i] = String.valueOf(anoAtual - i);
        }
        
        String[] ufs = {
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES",
            "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ",
            "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" 
        };

        
        
        cbDia.setModel(new javax.swing.DefaultComboBoxModel<>(dias));
        cbMes.setModel(new javax.swing.DefaultComboBoxModel<>(mes));
        cbAno.setModel(new javax.swing.DefaultComboBoxModel<>(ano));
        cbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"M", "F"}));
        cbUF.setModel(new javax.swing.DefaultComboBoxModel<>(ufs));
        
        funcionario = funcionario1;
        
    }
    
    private void inserir(){

        funcionario_pes funcionario2 = new funcionario_pes();
        funcionario_end funcionario3 = new funcionario_end();
        
        if (validacao()){
            String data_nasc = "" + cbAno.getSelectedItem() + "-" + cbMes.getSelectedItem() + "-" + cbDia.getSelectedItem();

            funcionario2.setId_funcionario(this.funcionario.getId_funcionario());
            funcionario3.setId_funcionario(this.funcionario.getId_funcionario());
                
            funcionario2.setData_nasc(data_nasc);
            funcionario3.setUf((String) cbUF.getSelectedItem());
            funcionario3.setInf_add(txtAdd.getText());
            funcionario2.setDocumento(txtDocumento.getText());
            funcionario2.setNome(txtNome.getText());
            funcionario2.setSexo((String) cbSexo.getSelectedItem());
            funcionario3.setCep(txtCep.getText());
            funcionario3.setCidade(txtCidade.getText());
            funcionario3.setRua(txtRua.getText());    

            int linha2 = funcionario2DAO.inserir(funcionario2);
            int linha3 = funcionario3DAO.inserir(funcionario3);

            if (linha2 > 0 && linha3 > 0){
                JOptionPane.showMessageDialog(this, "Funcionario adicionado com sucesso!");
                try {
                    new frmTabela().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(frmNovoFuncionario.class.getName()).log(Level.SEVERE, null, ex);
                }
                this.dispose();
            } else{
                JOptionPane.showMessageDialog(this, "Erro ao inserir funcionario.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Verifique os dados inseridos.");
        }
        
        
    }
    
        private void cancelar() {
        Object[] opcao = {"Não", "Sim"};
        int opcaoSelecionada = JOptionPane.showOptionDialog(this, "Deseja realmente sair?", "Aviso",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, opcao, opcao[0]);
        if (opcaoSelecionada == 1) {
            Integer id = Integer.parseInt(this.funcionario.getId_funcionario());
            apagar(id);
            this.dispose();
            try {
                new frmTabela().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(frmNovoFuncionario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
        private boolean validacao(){
            boolean erro = false;
            if (!txtDocumento.getText().matches("^[a-zA-Z0-9\\-]+$")){
                erro = true;
            }
            
            if (!txtNome.getText().matches("^[A-Za-z\\-]+$")){
                erro = true;
            }
            
            if (!txtCep.getText().matches("^[0-9]+$")){
                erro = true;
            }
            
            if (!txtCidade.getText().matches("^[A-Za-z\\-]+$")){
                erro = true;
            }
            
            if (!txtRua.getText().matches("^[A-Za-z\\-]+$")){
                erro = true;
            }
        return !erro;
    }
        
        private void apagar(int apg_codigo){
        Integer codigo = apg_codigo;
        
        int linha1 = funcionario1DAO.apagar(codigo);
        if (linha1 > 0){
            JOptionPane.showMessageDialog(this, "Item excluído com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao excluir.");
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtDocumento = new javax.swing.JTextField();
        txtRua = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtCep = new javax.swing.JTextField();
        txtAdd = new javax.swing.JTextField();
        btAdd = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        cbDia = new javax.swing.JComboBox<>();
        cbMes = new javax.swing.JComboBox<>();
        cbAno = new javax.swing.JComboBox<>();
        cbSexo = new javax.swing.JComboBox<>();
        cbUF = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setText("Nome:");

        jLabel3.setText("Sexo:");

        jLabel4.setText("Data de Nascimento:");

        jLabel5.setText("Documento:");

        jLabel9.setText("Endereço:");

        jLabel10.setText("Logradouro:");

        jLabel11.setText("Cidade:");

        jLabel12.setText("UF:");

        jLabel13.setText("CEP:");

        jLabel14.setText("Informações Adicionais:");

        btAdd.setText("Adicionar");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        cbDia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbAno.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel13)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel11)
                                                .addComponent(jLabel12))
                                            .addGap(26, 26, 26)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(cbUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btAdd)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btCancelar))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(25, 25, 25)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel9)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(cbDia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cbUF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtCep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btAdd)
                    .addComponent(btCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        // TODO add your handling code here:
        inserir();
        
    }//GEN-LAST:event_btAddActionPerformed

    private void btCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelarActionPerformed
        // TODO add your handling code here:
        cancelar();
    }//GEN-LAST:event_btCancelarActionPerformed

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
            java.util.logging.Logger.getLogger(frmNovoFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmNovoFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmNovoFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmNovoFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new frmNovoFuncionario(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JButton btCancelar;
    private javax.swing.JComboBox<String> cbAno;
    private javax.swing.JComboBox<String> cbDia;
    private javax.swing.JComboBox<String> cbMes;
    private javax.swing.JComboBox<String> cbSexo;
    private javax.swing.JComboBox<String> cbUF;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txtAdd;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRua;
    // End of variables declaration//GEN-END:variables
}
