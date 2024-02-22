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
import java.time.LocalDate;
import java.time.Year;
import java.util.HashSet;
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
public class frmEditar2 extends javax.swing.JFrame {
    
    funcionario_admDAO funcionario1DAO = DaoFactory.criarFuncionario_admDAO();
    funcionario_pesDAO funcionario2DAO = DaoFactory.criarFuncionario_pesDAO();
    funcionario_endDAO funcionario3DAO = DaoFactory.criarFuncionario_endDAO();
    
    

    /**
     * Creates new form frFuncionario
     * @throws java.sql.SQLException
     */
    public frmEditar2(String id) throws SQLException {
        initComponents();
        
        String[] dias = new String[31];
        for (int i = 0; i < 31; i++) {
            dias[i] = String.valueOf(i + 1);
        }
        String[] meses = new String[12];
        for (int i = 0; i < 12; i++) {
            meses[i] = String.valueOf(i + 1);
        }
        int anoAtual = Year.now().getValue();
        String[] anos = new String[101];
        for (int i = 0; i <= 100; i++) {
            anos[i] = String.valueOf(anoAtual - i);
        }
        
        String[] ufs = {
            "AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES",
            "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ",
            "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO" 
        };
        cbDiaN.setModel(new javax.swing.DefaultComboBoxModel<>(dias));
        cbMesN.setModel(new javax.swing.DefaultComboBoxModel<>(meses));
        cbAnoN.setModel(new javax.swing.DefaultComboBoxModel<>(anos));
        
        cbDiaC.setModel(new javax.swing.DefaultComboBoxModel<>(dias));
        cbMesC.setModel(new javax.swing.DefaultComboBoxModel<>(meses));
        cbAnoC.setModel(new javax.swing.DefaultComboBoxModel<>(anos));
        
        cbDiaF.setModel(new javax.swing.DefaultComboBoxModel<>(dias));
        cbMesF.setModel(new javax.swing.DefaultComboBoxModel<>(meses));
        cbAnoF.setModel(new javax.swing.DefaultComboBoxModel<>(anos));
        
        cbUF.setModel(new javax.swing.DefaultComboBoxModel<>(ufs));
        
        try{
            selectTest(id);
        } catch(SQLException e){
            throw e;
        }
        
        funcionario_adm funcionario1 = funcionario1DAO.selecionar(id);
        funcionario_pes funcionario2 = funcionario2DAO.selecionar(id);
        funcionario_end funcionario3 = funcionario3DAO.selecionar(id);
        
        lblID.setText(id);
        txtNome.setText(funcionario2.getNome());
        txtDocumento.setText(funcionario2.getDocumento());
        
        cbSexo.setSelectedItem(funcionario2.getSexo());
        
        LocalDate data = LocalDate.parse(funcionario2.getData_nasc());
        String ano = String.valueOf(data.getYear());
        String mes = String.valueOf(data.getMonthValue());
        String dia = String.valueOf(data.getDayOfMonth());

        cbDiaN.setSelectedItem(dia);
        cbMesN.setSelectedItem(mes);
        cbAnoN.setSelectedItem(ano);
        
        
        data = LocalDate.parse(funcionario1.getData_comeco());
        ano = String.valueOf(data.getYear());
        mes = String.valueOf(data.getMonthValue());
        dia = String.valueOf(data.getDayOfMonth());
                
        cbDiaC.setSelectedItem(dia);
        cbMesC.setSelectedItem(mes);
        cbAnoC.setSelectedItem(ano);
        
        data = LocalDate.parse(funcionario1.getData_fim());
        ano = String.valueOf(data.getYear());
        mes = String.valueOf(data.getMonthValue());
        dia = String.valueOf(data.getDayOfMonth());
        
        
        cbDiaF.setSelectedItem(dia);
        cbMesF.setSelectedItem(mes);
        cbAnoF.setSelectedItem(ano);
        
        txtValor.setText(funcionario1.getSalario());
        
        txtRua.setText(funcionario3.getRua());
        txtCep.setText(funcionario3.getCep());
        txtCidade.setText(funcionario3.getCidade());
        cbUF.setSelectedItem(funcionario3.getUf());
        txtAdd.setText(funcionario3.getInf_add());   
    }
    
    private void editar(){
        funcionario_adm funcionario1 = new funcionario_adm();
        funcionario_pes funcionario2 = new funcionario_pes();
        funcionario_end funcionario3 = new funcionario_end();
        
        if (validacao()){
           String data_nasc = "" + cbAnoN.getSelectedItem() + "-" + cbMesN.getSelectedItem() + "-" + cbDiaN.getSelectedItem();
            String data_comeco = "" + cbAnoC.getSelectedItem() + "-" + cbMesC.getSelectedItem() + "-" + cbDiaC.getSelectedItem();
            String data_fim = "" + cbAnoF.getSelectedItem() + "-" + cbMesF.getSelectedItem() + "-" + cbDiaF.getSelectedItem();

            String id = lblID.getText();

            funcionario1.setId_funcionario(id);
            funcionario2.setId_funcionario(id);
            funcionario3.setId_funcionario(id);

            funcionario1.setData_comeco(data_comeco);
            funcionario1.setData_fim(data_fim);
            funcionario1.setSalario(txtValor.getText());

            funcionario2.setData_nasc(data_nasc);
            funcionario2.setDocumento(txtDocumento.getText());
            funcionario2.setNome(txtNome.getText());
            funcionario2.setSexo((String) cbSexo.getSelectedItem());

            funcionario3.setCep(txtCep.getText());
            funcionario3.setCidade(txtCidade.getText());
            funcionario3.setInf_add(txtAdd.getText());
            funcionario3.setRua(txtRua.getText());
            funcionario3.setUf((String) cbUF.getSelectedItem());


            int linha1 = funcionario1DAO.editar(funcionario1);
            int linha2 = funcionario2DAO.editar(funcionario2);
            int linha3 = funcionario3DAO.editar(funcionario3);

            if (linha1 > 0 && linha2 > 0 && linha3 > 0){
                JOptionPane.showMessageDialog(this, "Funcionario editado com sucesso!");
                this.dispose();
            } else{
                JOptionPane.showMessageDialog(this, "Erro ao editar funcionario.");
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
            this.dispose();
            new frmPrincipal().setVisible(true);
        }
    }
        private void selectTest(String id) throws SQLException{
        try {
            funcionario_adm funcionario1 = funcionario1DAO.selecionar(id);
        } catch (SQLException ex) {
            this.dispose();
            new frmPrincipal().setVisible(true);
            throw ex;         
        }
    }
        private boolean validacao(){
            boolean erro = false;
            
            if (!txtValor.getText().matches("^[0-9]+$")){
                erro = true;
            }
            
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

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtDocumento = new javax.swing.JTextField();
        txtValor = new javax.swing.JTextField();
        txtRua = new javax.swing.JTextField();
        txtCidade = new javax.swing.JTextField();
        txtCep = new javax.swing.JTextField();
        txtAdd = new javax.swing.JTextField();
        btEdt = new javax.swing.JButton();
        btCancelar = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        cbDiaN = new javax.swing.JComboBox<>();
        cbMesN = new javax.swing.JComboBox<>();
        cbAnoN = new javax.swing.JComboBox<>();
        cbDiaC = new javax.swing.JComboBox<>();
        cbMesC = new javax.swing.JComboBox<>();
        cbAnoC = new javax.swing.JComboBox<>();
        cbDiaF = new javax.swing.JComboBox<>();
        cbMesF = new javax.swing.JComboBox<>();
        cbAnoF = new javax.swing.JComboBox<>();
        cbSexo = new javax.swing.JComboBox<>();
        cbUF = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("ID Funcionário:");

        jLabel2.setText("Nome:");

        jLabel3.setText("Sexo:");

        jLabel4.setText("Data de Nascimento:");

        jLabel5.setText("Documento:");

        jLabel6.setText("Data de início de contrato:");

        jLabel7.setText("Data de final de contrato:");

        jLabel8.setText("Valor do Contrato(ano):");

        jLabel9.setText("Endereço:");

        jLabel10.setText("Logradouro:");

        jLabel11.setText("Cidade:");

        jLabel12.setText("UF:");

        jLabel13.setText("CEP:");

        jLabel14.setText("Informações Adicionais:");

        btEdt.setText("Editar");
        btEdt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEdtActionPerformed(evt);
            }
        });

        btCancelar.setText("Cancelar");
        btCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelarActionPerformed(evt);
            }
        });

        cbDiaN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMesN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbAnoN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbDiaC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMesC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbAnoC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbDiaF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbMesF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbAnoF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbSexo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "M", "F" }));

        cbUF.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRua, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                                        .addComponent(txtCidade, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btEdt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btCancelar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbDiaN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbMesN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbAnoN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbDiaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbMesC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbAnoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cbDiaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbMesF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cbAnoF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(25, 25, 25)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblID, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(cbDiaN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMesN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAnoN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(cbDiaC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbMesC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbAnoC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cbDiaF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbMesF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbAnoF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel9)
                .addGap(18, 18, 18)
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
                    .addComponent(btEdt)
                    .addComponent(btCancelar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btEdtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEdtActionPerformed
        // TODO add your handling code here:
        editar();
        
        
    }//GEN-LAST:event_btEdtActionPerformed

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
            java.util.logging.Logger.getLogger(frmEditar2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmEditar2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmEditar2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmEditar2.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new frmEditar2(null).setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(frmEditar2.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCancelar;
    private javax.swing.JButton btEdt;
    private javax.swing.JComboBox<String> cbAnoC;
    private javax.swing.JComboBox<String> cbAnoF;
    private javax.swing.JComboBox<String> cbAnoN;
    private javax.swing.JComboBox<String> cbDiaC;
    private javax.swing.JComboBox<String> cbDiaF;
    private javax.swing.JComboBox<String> cbDiaN;
    private javax.swing.JComboBox<String> cbMesC;
    private javax.swing.JComboBox<String> cbMesF;
    private javax.swing.JComboBox<String> cbMesN;
    private javax.swing.JComboBox<String> cbSexo;
    private javax.swing.JComboBox<String> cbUF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel lblID;
    private javax.swing.JTextField txtAdd;
    private javax.swing.JTextField txtCep;
    private javax.swing.JTextField txtCidade;
    private javax.swing.JTextField txtDocumento;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtRua;
    private javax.swing.JTextField txtValor;
    // End of variables declaration//GEN-END:variables
}
