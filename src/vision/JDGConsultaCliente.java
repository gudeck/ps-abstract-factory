/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vision;

import control.ControleVisao;
import domain.Cliente;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author gudeck
 */
public class JDGConsultaCliente extends javax.swing.JDialog {

    private static JDGConsultaCliente uniqueInstance;

    private final ControleVisao controladorVisao;

    private JDGConsultaCliente (java.awt.Frame parent, boolean modal, ControleVisao controlador) {

        super(parent, modal);
        initComponents();

        controladorVisao = controlador;
    }

    public static synchronized JDGConsultaCliente getInstance(java.awt.Frame parent, boolean modal, ControleVisao controlador) {
        if (uniqueInstance == null) {
            uniqueInstance = new JDGConsultaCliente(parent, modal, controlador);
        }

        return uniqueInstance;
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
        txtNome = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtEndereco = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        ftxtAnoNascimento = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPesquisar = new javax.swing.JTable();
        btnPesquisar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jLabel1.setText("Nome");

        jLabel2.setText("Endereço");

        jLabel3.setText("Ano de Nascimento");

        try {
            ftxtAnoNascimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        tblPesquisar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nome", "Data de Nascimento", "Endereço", "Telefone", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblPesquisar);
        if (tblPesquisar.getColumnModel().getColumnCount() > 0) {
            tblPesquisar.getColumnModel().getColumn(0).setPreferredWidth(150);
            tblPesquisar.getColumnModel().getColumn(1).setPreferredWidth(125);
            tblPesquisar.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblPesquisar.getColumnModel().getColumn(3).setPreferredWidth(100);
            tblPesquisar.getColumnModel().getColumn(4).setPreferredWidth(175);
        }

        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ftxtAnoNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnPesquisar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(ftxtAnoNascimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPesquisar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed
        String nome, endereco, anoNascimento;
        ArrayList resultadoPesquisa;
        DefaultTableModel tabela = (DefaultTableModel) tblPesquisar.getModel();

        nome = txtNome.getText();
        endereco = txtEndereco.getText();
        anoNascimento = ftxtAnoNascimento.getText();

        if (nome.isEmpty() && endereco.isEmpty() && anoNascimento.equals("    ")) {
            JOptionPane.showMessageDialog(this, "Preencha ao menos um dos campos.", "ERRO", JOptionPane.ERROR_MESSAGE);
        } else {

            try {
                resultadoPesquisa = controladorVisao.getControleDominio().clienteConsulta(nome, endereco, anoNascimento);

                tabela.setRowCount(0);
                for (int i = 0; i < resultadoPesquisa.size(); i++) {
                    tabela.addRow(new Object[]{((Cliente) resultadoPesquisa.get(i)).getNome(),
                        ((Cliente) resultadoPesquisa.get(i)).getDataNascimento(),
                        ((Cliente) resultadoPesquisa.get(i)).getEndereco(),
                        ((Cliente) resultadoPesquisa.get(i)).getTelefone(),
                        ((Cliente) resultadoPesquisa.get(i)).getEmail()});
                }

                txtNome.setText("");
                txtEndereco.setText("");
                ftxtAnoNascimento.setText("");

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao pesquisar: " + ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnPesquisarActionPerformed

    
    //Evento que limpa a tela ao fechar. Para não ficar dados de pesquisas anteriores ao abrir a tela. O padrão singleton segue sendo seguido.
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        DefaultTableModel tabela = (DefaultTableModel) tblPesquisar.getModel();
        tabela.setRowCount(0);
    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JFormattedTextField ftxtAnoNascimento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblPesquisar;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables
}
