/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesven;

import basic.qcon;
import basic.utils;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author User
 */
public class jVencimientosAlmacenes extends javax.swing.JDialog {

   ArrayList<String> vencimientosPlantillaYardaId = new ArrayList<>();
   ArrayList<String> vencimientosYardaId = new ArrayList<>();
   ArrayList<String> yardasId = new ArrayList<>();
   
   
   
    DefaultTableModel vencimientosYardaModel;
    DefaultTableModel vencimientosYardaPlanModel;
    
    VencimientosV3 vencimientosVentana = null;
    
    int cont = 0;
    
    public jVencimientosAlmacenes(java.awt.Frame parent, boolean modal,VencimientosV3 temp) {
        super(parent, modal);
        initComponents();
        
        vencimientosVentana = temp;
        JTableHeader FP = tvencimientosYardaPlantilla.getTableHeader();
        FP.setForeground(new Color(102, 102, 102));
        tvencimientosYardaPlantilla.setTableHeader(FP);
        tvencimientosYardaPlantilla.getTableHeader().setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        tvencimientosYardaPlantilla.setFillsViewportHeight(true);

        vencimientosYardaPlanModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    default:
                        return false;
                }
            }
        };
        vencimientosYardaPlanModel.addColumn("vencimiento");
        tvencimientosYardaPlantilla.setModel(vencimientosYardaPlanModel);
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        
         vencimientosYardaModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    default:
                        return true;
                }
            }
        };

        vencimientosYardaModel.addColumn("Vencimiento");
        vencimientosYardaModel.addColumn("Renovacion (Dias)");
        vencimientosYardaModel.addColumn("Notas");

        tvencimientosYardas.setModel(vencimientosYardaModel);

        tvencimientosYardas.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                tableListenertvencimientosYardas(e);

            }
        });
        
        cargarPlantillaVencimientosYardas();
        cargarVencimientosYardas();
    }

  private void crearVencimientoYarda() {
        //Esta funcion es para agregar un vencimiento de ayuntamiento, se agregara vacio para que pueda
        // ser manipulado
        utils.dbInsert("INSERT INTO permisos_ayuntamiento_tbl (nombre, estatus, diasRenovacion, descripcion) VALUES ('', 1, 0, '')");
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel28 = new javax.swing.JPanel();
        jScrollPane20 = new javax.swing.JScrollPane();
        tvencimientosYardas = new javax.swing.JTable();
        jButton69 = new javax.swing.JButton();
        jButton91 = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        tvencimientosYardaPlantilla = new javax.swing.JTable();
        jLabel83 = new javax.swing.JLabel();
        jButton92 = new javax.swing.JButton();
        jButton93 = new javax.swing.JButton();
        jButton94 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1054, 600));
        setPreferredSize(new java.awt.Dimension(1054, 600));

        jPanel28.setBackground(new java.awt.Color(255, 255, 255));
        jPanel28.setLayout(null);

        tvencimientosYardas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tvencimientosYardas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tvencimientosYardasMouseClicked(evt);
            }
        });
        tvencimientosYardas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tvencimientosYardasKeyPressed(evt);
            }
        });
        jScrollPane20.setViewportView(tvencimientosYardas);

        jPanel28.add(jScrollPane20);
        jScrollPane20.setBounds(20, 50, 453, 400);

        jButton69.setText("-");
        jButton69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton69ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton69);
        jButton69.setBounds(410, 10, 50, 30);

        jButton91.setText("+");
        jButton91.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton91ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton91);
        jButton91.setBounds(350, 10, 50, 30);

        tvencimientosYardaPlantilla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tvencimientosYardaPlantilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tvencimientosYardaPlantillaMouseClicked(evt);
            }
        });
        jScrollPane21.setViewportView(tvencimientosYardaPlantilla);

        jPanel28.add(jScrollPane21);
        jScrollPane21.setBounds(510, 50, 453, 400);

        jLabel83.setText("Plantilla de Carga");
        jPanel28.add(jLabel83);
        jLabel83.setBounds(510, 20, 180, 30);

        jButton92.setText("+");
        jButton92.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton92ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton92);
        jButton92.setBounds(970, 60, 50, 30);

        jButton93.setText("-");
        jButton93.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton93ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton93);
        jButton93.setBounds(970, 100, 50, 30);

        jButton94.setText("Asignar vencimientos a todas la yardas");
        jButton94.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton94ActionPerformed(evt);
            }
        });
        jPanel28.add(jButton94);
        jButton94.setBounds(30, 470, 920, 30);

        getContentPane().add(jPanel28, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tvencimientosYardasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tvencimientosYardasMouseClicked
        if (tvencimientosYardas.getSelectedRow() >= 0) {
            //            cargarVencimientosPlantillas();
        }
    }//GEN-LAST:event_tvencimientosYardasMouseClicked

    private void cargarPlantillaVencimientosYardas() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select pp.ayunPlantId,pa.nombre from permisos_ayuntamiento_plantilla pp\n"
                + "inner join permisos_ayuntamiento_tbl pa ON pa.permisoAyunId = pp.permisoId\n"
                + "where pa.estatus is true and pp.estatus is true";
        vencimientosPlantillaYardaId.clear();
        vencimientosYardaPlanModel.setRowCount(0);
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                vencimientosPlantillaYardaId.add(rs.getString("ayunPlantId"));
                vencimientosYardaPlanModel.addRow(new Object[]{rs.getString("nombre")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar plantilla vencimientos de yarda: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }
    private void tvencimientosYardasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tvencimientosYardasKeyPressed

    }//GEN-LAST:event_tvencimientosYardasKeyPressed
  private void tableListenertvencimientosYardas(javax.swing.event.TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {

                int row = tvencimientosYardas.getSelectedRow();
                int col = tvencimientosYardas.getSelectedColumn();

                try {

                    if (col == 0) {
                        utils.dbUpdate("Update permisos_ayuntamiento_tbl set nombre='" + tvencimientosYardas.getValueAt(row, col) + "' where permisoAyunId='" + vencimientosYardaId.get(row) + "'");
                        vencimientosVentana.ejecutarCargaYarda();
                    }

                    if (col == 1) {
                        utils.dbUpdate("Update permisos_ayuntamiento_tbl set diasRenovacion= " + tvencimientosYardas.getValueAt(row, col) + " where permisoAyunId='" + vencimientosYardaId.get(row) + "'");
                    }
                    if (col == 2) {
                        utils.dbUpdate("Update permisos_ayuntamiento_tbl set descripcion= '" + tvencimientosYardas.getValueAt(row, col) + "' where permisoAyunId='" + vencimientosYardaId.get(row) + "'");
                    }

                } catch (Exception o) {
                    JOptionPane.showMessageDialog(this, "Favor de Selecionar un Elemento\nError " + o, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                cont = 0;
            }
        }
    }
    
    void prt(String c){
        System.out.println(c);
    }
    private void cargarVencimientosYardas() {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select permisoAyunId,nombre,diasRenovacion,descripcion from permisos_ayuntamiento_tbl where estatus = 1 and permisoAyunId != 0";
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            vencimientosYardaModel.setRowCount(0);/*El bueno*/
            prt("Si entro a la funcion de impresion rows" + vencimientosYardaModel.getRowCount() + " columnas = " + vencimientosYardaModel.getColumnCount());
            vencimientosYardaId.clear();
            while (rs.next()) {
                vencimientosYardaId.add(rs.getString("permisoAyunId"));
                vencimientosYardaModel.addRow(new Object[]{rs.getString("nombre"), rs.getString("diasRenovacion"),
                    rs.getString("descripcion")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar vencimientos de yarda: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }
    private void jButton69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton69ActionPerformed

        if (tvencimientosYardas.getSelectedRow() >= 0) {
            // Actualizando otras tablas con el permiso a eliminar en cuestion
            qcon.dbUpdate("update permisos_ayuntamiento_plantilla set estatus = 0 where permisoId = " + vencimientosYardaId.get(tvencimientosYardas.getSelectedRow()) + "  and ayunPlantId > 0");
            qcon.dbUpdate("update patios_vencimientos set estatus = 0 where permisoId = " + vencimientosYardaId.get(tvencimientosYardas.getSelectedRow()) + " and patioVenId > 0");
            qcon.dbUpdate("Delete from permisos_ayuntamiento_tbl where permisoAyunId='" + vencimientosYardaId.get(tvencimientosYardas.getSelectedRow()) + "'");
            cargarVencimientosYardas();
            cargarPlantillaVencimientosYardas();
            
            vencimientosVentana.ejecutarCargaYarda();
            //            cargarVencimientosPlantillas();
        } else {

        }
    }//GEN-LAST:event_jButton69ActionPerformed

    private void jButton91ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton91ActionPerformed
        crearVencimientoYarda();
        cargarVencimientosYardas();
    }//GEN-LAST:event_jButton91ActionPerformed

    private void tvencimientosYardaPlantillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tvencimientosYardaPlantillaMouseClicked

    }//GEN-LAST:event_tvencimientosYardaPlantillaMouseClicked
 private void agregarVencimientoPatioPlantilla() {
        int vencimientoYarda = tvencimientosYardas.getSelectedRow();
        String vencimientoExiste = utils.dbConsult("SELECT ayunPlantId FROM permisos_ayuntamiento_plantilla where permisoId = " + vencimientosYardaId.get(tvencimientosYardas.getSelectedRow()) + " and estatus = 1");
        if (vencimientoYarda >= 0) {
            if (vencimientoExiste.isEmpty()) {
                prt("insert into permisos_ayuntamiento_plantilla (ayunPlantId,permisoId,estatus) values ('0'," + vencimientosYardaId.get(tvencimientosYardas.getSelectedRow()) + ",1);");
                utils.dbInsert("insert into permisos_ayuntamiento_plantilla (ayunPlantId,permisoId,estatus) values ('0'," + vencimientosYardaId.get(tvencimientosYardas.getSelectedRow()) + ",1);");
            } else {
                JOptionPane.showMessageDialog(this, "Vencimientos ya en plantilla", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un vencimiento de almacen", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void jButton92ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton92ActionPerformed
        agregarVencimientoPatioPlantilla();
        cargarPlantillaVencimientosYardas();
    }//GEN-LAST:event_jButton92ActionPerformed
 private void borrarVencimientoPlantillaYarda() {
        int plantillaYardaVen = tvencimientosYardaPlantilla.getSelectedRow();
        if (plantillaYardaVen >= 0) {
            utils.dbUpdate("update permisos_ayuntamiento_plantilla "
                    + "set estatus = 0 where ayunPlantId = " + vencimientosPlantillaYardaId.get(plantillaYardaVen));
        } else {
            JOptionPane.showMessageDialog(this, "Selecciona un vencimiento de la plantilla", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void jButton93ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton93ActionPerformed
        borrarVencimientoPlantillaYarda();
        cargarPlantillaVencimientosYardas();
    }//GEN-LAST:event_jButton93ActionPerformed
private void cargarAlmacenesId() {
        int conteoFilas = tvencimientosYardaPlantilla.getRowCount();
        String query = "select PatioID from patios_tbl where Status  = 1 ";
        if (conteoFilas > 0) {
            yardasId.clear();
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                con = utils.startConnection();
                st = con.createStatement();
                rs = st.executeQuery(query);

                while (rs.next()) {
                    yardasId.add(rs.getString("patioID"));
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cargar la plantilla a las yardas", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                utils.closeAllConnections(con, st, rs);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay permisos cargados en plantilla", "Aviso", HEIGHT);
        }
    }

    private void cargarPlantillaHaciaAlm() {
        boolean yardasExisten = (yardasId.size() > 0);
        String query = "select permisoId from permisos_ayuntamiento_plantilla where estatus = 1";
        if (yardasExisten) {
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            int contadorRegistrados = 0;
            try {
                con = utils.startConnection();
                st = con.createStatement();
                rs = st.executeQuery(query);
                String alreadyLoaded = "";
                while (rs.next()) {//yardasId.add(
                    for (String yarda : yardasId) {
                        alreadyLoaded = utils.dbConsult("select patioVenId from patios_vencimientos where permisoId='" + rs.getString("permisoId") + "' and patioId='" + yarda + "' and estatus IS TRUE");
                        if (alreadyLoaded.isEmpty()) {
                            utils.dbInsert("insert into patios_vencimientos (patioVenId,permisoId,patioId,estatus,fechaVencimiento) values ('0'," + rs.getString("permisoId") + "," + yarda + ",1,null)");
                            contadorRegistrados++;
                        }
                        prt("Insertado con exito!");
                    }
                }
                if (contadorRegistrados > 0) {
                    JOptionPane.showMessageDialog(this, "Han sido asignados con exito!", "Exito", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No hay permisos que agregar a los patios", "Aviso", HEIGHT);
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al cargar la plantilla a las yardas", "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                utils.closeAllConnections(con, st, rs);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No hay permisos cargados en plantilla", "Aviso", HEIGHT);
        }
    }
    private void jButton94ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton94ActionPerformed
        cargarAlmacenesId();
        cargarPlantillaHaciaAlm();
    }//GEN-LAST:event_jButton94ActionPerformed

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
            java.util.logging.Logger.getLogger(jVencimientosAlmacenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(jVencimientosAlmacenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(jVencimientosAlmacenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(jVencimientosAlmacenes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                jVencimientosAlmacenes dialog = new jVencimientosAlmacenes(new javax.swing.JFrame(), true,null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton69;
    private javax.swing.JButton jButton91;
    private javax.swing.JButton jButton92;
    private javax.swing.JButton jButton93;
    private javax.swing.JButton jButton94;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JTable tvencimientosYardaPlantilla;
    private javax.swing.JTable tvencimientosYardas;
    // End of variables declaration//GEN-END:variables
}
