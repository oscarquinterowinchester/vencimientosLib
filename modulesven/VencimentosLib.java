/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesven;

import basic.*;
import com.alee.extended.date.WebDateField;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import renders.ConfigLocal;

/**
 *
 * @author User
 */
public class VencimentosLib extends javax.swing.JFrame {

    /**
     * Creates new form VencimentosLib
     */
    private DefaultTableModel mvencimientosAltaRemolque;
    private DefaultTableModel mvencimientosPlantillaRemo;
    private DefaultTableModel tablemodelVencimientoRemolque;

    private ArrayList<String> vencimientosAltaId = new ArrayList<>();

    private ArrayList<String> vencimientosAltaIdRemolque = new ArrayList<>();
    private ArrayList<String> vencimientosidP2 = new ArrayList<>();
    private ArrayList<String> plantillaVencimientoUnidadId = new ArrayList<>();
    private ArrayList<String> vencimientosremolquesId = new ArrayList<>();
    private ArrayList<String> vencimientoIdComboRemolque = new ArrayList<>();
    
    ArrayList<String> RutaszRemolque = new ArrayList<>();
    
    
    JComboBox comboVencimientoRemolque = new JComboBox();

    private int cont = 0;

    private String SelectedRemolque = "";
    
    File filez, bfilez;
    String Filez = "", DBFilez = "";
    JFileChooser chooserz = new JFileChooser();
    VencimientosV3 ventanaPrincipal = null;

    public VencimentosLib(VencimientosV3 v3) {
        initComponents();

        ventanaPrincipal = v3;
        SelectedRemolque = "";
        inicializarTablaRemolque();
        inicializarTablaPlantillaRemolque();

    }

    public void abrirAltaVencimientoRemolque() {

        cargarAltaVencimientosRemolques();
        cargarVencimientosPlantillasRemolque();
        dAltaVencimientosRemolques.setLocationRelativeTo(this);
        dAltaVencimientosRemolques.setVisible(true);
    }
    
    public void abrirAsignacionPermisosRemo(String SelectedRemolque){
        this.SelectedRemolque = SelectedRemolque;
        cargarListaVencimientosComboRemo();
        inicializarTablaAsignacionVenRemo();
        cargarVencimientosRemolque();
        dVencimientosRemolques.setLocationRelativeTo(this);
        dVencimientosRemolques.setVisible(true);
        
    }
    
    void cargarListaVencimientosComboRemo() {
        String query = "SELECT *, concat(vencimiento,' [',dias,' dias]') as venc from vencimientos_remolques where deleted is not true and vencimiento != ''  order by ID asc";
        vencimientoIdComboRemolque.clear();
        comboVencimientoRemolque.removeAllItems();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                vencimientoIdComboRemolque.add(rs.getString("ID"));
                comboVencimientoRemolque.addItem(rs.getString("venc"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    private void inicializarTablaAsignacionVenRemo(){
        
         tablemodelVencimientoRemolque = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    case 3:
                        return false;
                    default:
                        return true;
                }
            }
        };

        JTableHeader Fce = tvencimientosremolque.getTableHeader();
        Fce.setForeground(new Color(102, 102, 102));
        tvencimientosremolque.setTableHeader(Fce);
        tvencimientosremolque.getTableHeader().setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        tvencimientosremolque.setFillsViewportHeight(true);

        tablemodelVencimientoRemolque.addColumn("Vencimiento");
        tablemodelVencimientoRemolque.addColumn("Referencia");
        tablemodelVencimientoRemolque.addColumn("Fecha");
        tablemodelVencimientoRemolque.addColumn("Documento");

        this.tvencimientosremolque.setModel(tablemodelVencimientoRemolque);

        ((DefaultTableCellRenderer) tvencimientosremolque.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);//.LEFT .RIGHT .CENTER

        tvencimientosremolque.getColumnModel().getColumn(0).setCellRenderer(Alinear);
        tvencimientosremolque.getColumnModel().getColumn(1).setCellRenderer(Alinear);
        tvencimientosremolque.getColumnModel().getColumn(2).setCellRenderer(Alinear);
        tvencimientosremolque.getColumnModel().getColumn(3).setCellRenderer(Alinear);

        DefaultCellEditor vencUnid = new DefaultCellEditor(comboVencimientoRemolque);
        tvencimientosremolque.getColumnModel().getColumn(0).setCellEditor(vencUnid);

        WebDateField fechaVencUnid = new WebDateField(new Date());
        DefaultCellEditor venxUni = new DefaultCellEditor(fechaVencUnid);
        tvencimientosremolque.getColumnModel().getColumn(2).setCellEditor(venxUni);

        ConfigLocal.WebDate(fechaVencUnid, 14);

        tvencimientosremolque.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                tableListenertvencimientosremolque(e);

            }
        });
    }
    private void inicializarTablaPlantillaRemolque() {

        JTableHeader FPx = tvencimientosRemoPlantilla.getTableHeader();
        FPx.setForeground(new Color(102, 102, 102));
        tvencimientosRemoPlantilla.setTableHeader(FPx);
        tvencimientosRemoPlantilla.getTableHeader().setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        tvencimientosRemoPlantilla.setFillsViewportHeight(true);

        mvencimientosPlantillaRemo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    default:
                        return false;
                }
            }
        };
        mvencimientosPlantillaRemo.addColumn("Vencimiento Plantilla");
        this.tvencimientosRemoPlantilla.setModel(mvencimientosPlantillaRemo);
    }

    private void inicializarTablaRemolque() {
        mvencimientosAltaRemolque = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                // Verifica si la columna es editable
                return col != 3; // Devuelve true si la columna no es la columna 3
            }
        };

        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);//.LEFT .RIGHT .CENTER

        JTableHeader Fx = tvencimientosAltaRemolque.getTableHeader();
        Fx.setForeground(new Color(102, 102, 102));
        tvencimientosAltaRemolque.setTableHeader(Fx);
        tvencimientosAltaRemolque.getTableHeader().setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        tvencimientosAltaRemolque.setFillsViewportHeight(true);

        mvencimientosAltaRemolque.addColumn("Vencimiento");
        mvencimientosAltaRemolque.addColumn("Renovacion (Dias)");
        mvencimientosAltaRemolque.addColumn("Notas");
        mvencimientosAltaRemolque.addColumn("Notacion sistema");

        this.tvencimientosAltaRemolque.setModel(mvencimientosAltaRemolque);

        ((DefaultTableCellRenderer) tvencimientosAltaRemolque.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        Alinear.setHorizontalAlignment(SwingConstants.CENTER);//.LEFT .RIGHT .CENTER

        tvencimientosAltaRemolque.getColumnModel().getColumn(0).setCellRenderer(Alinear);
        tvencimientosAltaRemolque.getColumnModel().getColumn(1).setCellRenderer(Alinear);
        tvencimientosAltaRemolque.getColumnModel().getColumn(2).setCellRenderer(Alinear);

        tvencimientosAltaRemolque.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                tableListenertvencimientosAltaRemolque(e);

            }
        });
    }

    private void tableListenertvencimientosremolque(javax.swing.event.TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {

                int row = tvencimientosremolque.getSelectedRow();
                int col = tvencimientosremolque.getSelectedColumn();

//                try {

                    if (col == 0) {
//                        if (comboVencimientoRemolque.getSelectedIndex() >= 0) {
//                            vencimientoIdComboRemolque.set(row, vencimientoIdComboRemolque.get(comboVencimientoRemolque.getSelectedIndex()));
//                        }

                        int comboElementoSeleccionado =  comboVencimientoRemolque.getSelectedIndex();
                        boolean esVencimientoDuplicado = !(utils.dbConsult("select ifnull(ID,'') from choferes_vencimientos_remolques where vencimientoID = " + vencimientoIdComboRemolque.get(comboElementoSeleccionado) + " and choferId = " + SelectedRemolque + " and deleted is null").isEmpty());
                        prt("query para ver duplicado: " + "select ifnull(ID,'') from choferes_vencimientos_remolques where vencimientoID = " + vencimientoIdComboRemolque.get(comboElementoSeleccionado) + " and choferId = " + SelectedRemolque + " and deleted is null");
                        
                        boolean noEsModificable = utils.dbConsult("select IFNULL(vencimientoFijo,'0')  from vencimientos_Remolques where ID = (select vencimientoId from choferes_vencimientos_remolques where ID = " + vencimientosremolquesId.get(row) + " limit 1) ").equals("1");

                        prt("Query para saber si es modificable: " + "select IFNULL(vencimientoFijo,'0')  from vencimientos_Remolques where ID = (select vencimientoId from choferes_vencimientos_remolques where ID = " + vencimientosremolquesId.get(row) + " limit 1) ");
                        
                        if (esVencimientoDuplicado) {
                            JOptionPane.showMessageDialog(this, "No se puede duplicar los vencimientos");
                            cargarVencimientosRemolque();
                            return;
                        }

                        if (noEsModificable) {
                            JOptionPane.showMessageDialog(this, "No se pueden alterar los conceptos fijos");
                            cargarVencimientosRemolque();
                            return;
                        }

                        utils.dbUpdate("Update choferes_vencimientos_remolques set vencimientoID='" + vencimientoIdComboRemolque.get(comboVencimientoRemolque.getSelectedIndex()) + "' where ID='" + vencimientosremolquesId.get(row) + "'");
                        prt("Update choferes_vencimientos_remolques set vencimientoID='" + vencimientoIdComboRemolque.get(comboVencimientoRemolque.getSelectedIndex()) + "' where ID='" + vencimientosremolquesId.get(row) + "'");
                    }

                    if (col == 1) {
                        utils.dbUpdate("Update choferes_vencimientos_remolques set referencia='" + tvencimientosremolque.getValueAt(row, 1).toString() + "' where ID='" + vencimientosremolquesId.get(row) + "'");
//                        ventanaPrincipal.cargarVencimientosRemolque();
                    }

                    if (col == 2) {
                        String fechaVencimiento = utils.dateFromFieldtoDBwoH(tvencimientosremolque.getValueAt(row, 2).toString(), true);
                        String registroId = vencimientosremolquesId.get(row);
                        boolean esVencimientoFijo = !(utils.dbConsult("select IFNULL(campoBd,'')  from vencimientos_Remolques where vencimientoFijo is true and ID = (select vencimientoId from choferes_vencimientos_remolques where ID = " + vencimientosremolquesId.get(row) + " limit 1)").isEmpty());
                        String campoBDVencimiento = utils.dbConsult("select IFNULL(campoBd,'')  from vencimientos_Remolques where vencimientoFijo is true and ID = (select vencimientoId from choferes_vencimientos_remolques where ID = " + vencimientosremolquesId.get(row) + " limit 1)");

                        utils.dbUpdate("Update choferes_vencimientos_remolques set fecha='" + fechaVencimiento + "' where ID='" + registroId + "'");
                        try{
                            ventanaPrincipal.cargarVencimientosRemolque();
                        ventanaPrincipal.cargarListaVencimientosRemolque();
                        }catch(Exception ee){
                            prt("FECHA EN VENCI: " + ee);
                        }
                        if (esVencimientoFijo) {
                            utils.dbUpdate("update cajas_tbl set " + campoBDVencimiento + " = '" + fechaVencimiento + "' where CajaId = " + SelectedRemolque + " ");
                        }
//                        cargarTablaRemolques();
                    }

//                } catch (Exception o) {
//                    JOptionPane.showMessageDialog(this, "Favor de Selecionar un Elemento\nError " + o, "Error", JOptionPane.ERROR_MESSAGE);
//                }
            } else {
                cont = 0;
            }
        }
    }
    
    private void tableListenertvencimientosAltaRemolque(javax.swing.event.TableModelEvent e) {

        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {
                int row = tvencimientosAltaRemolque.getSelectedRow();
                int col = tvencimientosAltaRemolque.getSelectedColumn();
                try {

                    if (col == 0) {
                        utils.dbUpdate("Update vencimientos_remolques set vencimiento='" + tvencimientosAltaRemolque.getValueAt(row, col) + "' where ID='" + vencimientosAltaIdRemolque.get(row) + "'");
                        cargarVencimientosPlantillasRemolque();
                        try{
                        ventanaPrincipal.cargarVencimientosRemolque();
                        ventanaPrincipal.cargarListaVencimientosRemolque();
                        }catch(Exception ee){
                            prt("ERROR EN COL == 0: " + ee);
                        }

                    }
                    if (col == 1) {

                        cont = 1;
                        tvencimientosAltaRemolque.setValueAt(utils.dbConsult("select digits('" + tvencimientosAltaRemolque.getValueAt(row, col) + "')"), row, col);

                        utils.dbUpdate("Update vencimientos_remolques set dias='" + tvencimientosAltaRemolque.getValueAt(row, col) + "' where ID='" + vencimientosAltaIdRemolque.get(row) + "'");
//                        cargarTablaUnidad();
                    }

                    if (col == 2) {
                        utils.dbUpdate("Update vencimientos_remolques set notas='" + tvencimientosAltaRemolque.getValueAt(row, col) + "' where ID='" + vencimientosAltaIdRemolque.get(row) + "'");
                    }

                } catch (Exception o) {
                    mostrarMensajeError("Favor de Selecionar un Elemento\nError " + o);
                }
            } else {
                cont = 0;
            }
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

        dAltaVencimientosRemolques = new javax.swing.JDialog();
        jPanel66 = new javax.swing.JPanel();
        jScrollPane29 = new javax.swing.JScrollPane();
        tvencimientosAltaRemolque = new javax.swing.JTable();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jScrollPane30 = new javax.swing.JScrollPane();
        tvencimientosRemoPlantilla = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        dVencimientosRemolques = new javax.swing.JDialog();
        jPanel65 = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        tvencimientosremolque = new javax.swing.JTable();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();

        dAltaVencimientosRemolques.setTitle("Alta De Vencimientos");
        dAltaVencimientosRemolques.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dAltaVencimientosRemolques.setMinimumSize(new java.awt.Dimension(1042, 600));

        jPanel66.setBackground(new java.awt.Color(255, 255, 255));
        jPanel66.setLayout(null);

        tvencimientosAltaRemolque.setModel(new javax.swing.table.DefaultTableModel(
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
        tvencimientosAltaRemolque.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tvencimientosAltaRemolqueMouseClicked(evt);
            }
        });
        tvencimientosAltaRemolque.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tvencimientosAltaRemolqueKeyPressed(evt);
            }
        });
        jScrollPane29.setViewportView(tvencimientosAltaRemolque);

        jPanel66.add(jScrollPane29);
        jScrollPane29.setBounds(30, 60, 453, 400);

        jButton38.setText("-");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });
        jPanel66.add(jButton38);
        jButton38.setBounds(410, 10, 50, 30);

        jButton39.setText("+");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });
        jPanel66.add(jButton39);
        jButton39.setBounds(350, 10, 50, 30);

        tvencimientosRemoPlantilla.setModel(new javax.swing.table.DefaultTableModel(
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
        tvencimientosRemoPlantilla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tvencimientosRemoPlantillaMouseClicked(evt);
            }
        });
        jScrollPane30.setViewportView(tvencimientosRemoPlantilla);

        jPanel66.add(jScrollPane30);
        jScrollPane30.setBounds(510, 50, 453, 400);

        jLabel33.setText("Plantilla de Carga");
        jPanel66.add(jLabel33);
        jLabel33.setBounds(510, 20, 180, 30);

        jButton40.setText("+");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });
        jPanel66.add(jButton40);
        jButton40.setBounds(970, 60, 50, 30);

        jButton41.setText("-");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });
        jPanel66.add(jButton41);
        jButton41.setBounds(970, 100, 50, 30);

        jButton42.setText("Asignar vencimientos a todas las unidades");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });
        jPanel66.add(jButton42);
        jButton42.setBounds(30, 470, 920, 30);

        dAltaVencimientosRemolques.getContentPane().add(jPanel66, java.awt.BorderLayout.CENTER);

        dVencimientosRemolques.setTitle("Listado de Vencimientos de las Unidades");
        dVencimientosRemolques.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dVencimientosRemolques.setMinimumSize(new java.awt.Dimension(600, 490));
        dVencimientosRemolques.setPreferredSize(new java.awt.Dimension(600, 490));

        jPanel65.setBackground(new java.awt.Color(255, 255, 255));
        jPanel65.setMinimumSize(new java.awt.Dimension(550, 472));
        jPanel65.setPreferredSize(new java.awt.Dimension(550, 472));
        jPanel65.setLayout(null);

        tvencimientosremolque.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane25.setViewportView(tvencimientosremolque);

        jPanel65.add(jScrollPane25);
        jScrollPane25.setBounds(20, 20, 453, 403);

        jButton34.setText("Doc");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });
        jPanel65.add(jButton34);
        jButton34.setBounds(480, 100, 60, 30);

        jButton35.setText("+");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });
        jPanel65.add(jButton35);
        jButton35.setBounds(480, 20, 60, 30);

        jButton36.setText("-");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });
        jPanel65.add(jButton36);
        jButton36.setBounds(480, 60, 60, 30);

        jButton37.setText("Ver");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });
        jPanel65.add(jButton37);
        jButton37.setBounds(480, 140, 60, 30);

        jButton43.setText("Generar plantilla base");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        jPanel65.add(jButton43);
        jButton43.setBounds(20, 430, 450, 30);

        dVencimientosRemolques.getContentPane().add(jPanel65, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 644, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 295, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tvencimientosAltaRemolqueMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tvencimientosAltaRemolqueMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tvencimientosAltaRemolqueMouseClicked

    private void tvencimientosAltaRemolqueKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tvencimientosAltaRemolqueKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tvencimientosAltaRemolqueKeyPressed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed

        boolean noHaySeleccion = tvencimientosAltaRemolque.getSelectedRow() < 0;

        if (noHaySeleccion) {
            JOptionPane.showMessageDialog(rootPane, "Seleccione un registro");
            return;
        }

        boolean esVencimientoFijo = utils.dbConsult("select vencimientoFijo from vencimientos_remolques where ID = " + vencimientosAltaIdRemolque.get(tvencimientosAltaRemolque.getSelectedRow()) + " ").equals("1");

        if (esVencimientoFijo) {
            JOptionPane.showMessageDialog(rootPane, "No es posible eliminar vencimientos fijos");
            return;
        }

        utils.dbUpdate("update vencimientos_remolques set deleted = 1 where ID='" + vencimientosAltaIdRemolque.get(tvencimientosAltaRemolque.getSelectedRow()) + "'");
        // Actualizando otras tablas con el permiso a eliminar en cuestion
        utils.dbUpdate("update vencimientos_plantilla_remolques set deleted = 1 where vencimientoID = " + vencimientosAltaIdRemolque.get(tvencimientosAltaRemolque.getSelectedRow()) + "  and ID > 0");
        utils.dbUpdate("update choferes_vencimientos_remolques set deleted = 1 where vencimientoID = " + vencimientosAltaIdRemolque.get(tvencimientosAltaRemolque.getSelectedRow()) + " and ID > 0");

        cargarAltaVencimientosRemolques();
        cargarVencimientosPlantillasRemolque();

         try{
                            ventanaPrincipal.cargarVencimientosRemolque();
                        ventanaPrincipal.cargarListaVencimientosRemolque();
                        }catch(Exception ee){
                            prt("SE BORRO EL VENCI: " + ee);
                        }
        
    }//GEN-LAST:event_jButton38ActionPerformed

    void cargarVencimientosPlantillasRemolque() {

        String query = "SELECT vencimientos_plantilla_remolques.ID, (select nombre from tiposchoferes_tbl where TipoC=vencimientos_plantilla_remolques.tipoID) as venx, \n"
                + "v.vencimiento as venc \n"
                + " FROM vencimientos_plantilla_remolques \n"
                + " left join vencimientos_remolques v ON v.ID = vencimientos_plantilla_remolques.vencimientoID\n"
                + " where v.vencimiento is not null and v.deleted is null and vencimientos_plantilla_remolques.deleted is null";//where tipoID='" + boxTipoPermiso1ID.get(tipoPlantilla1.getSelectedIndex()) + "'";

        Connection con;
        con = utils.startConnection();

        mvencimientosPlantillaRemo.setRowCount(0);
        vencimientosidP2.clear();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                vencimientosidP2.add(rs.getString("ID"));
                mvencimientosPlantillaRemo.addRow(new Object[]{rs.getString("venc")});
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    void cargarAltaVencimientosRemolques() {

        String verVencimientoFijos = (global.nivel != 1 ? " and vencimientoFijo = 0" : "");
        String query = "SELECT ID,Vencimiento,dias,notas,campoBD FROM vencimientos_remolques where deleted is null\n"
                + "and ID !=0 " + verVencimientoFijos;

        Connection con;
        con = utils.startConnection();

        mvencimientosAltaRemolque.setRowCount(0);
        vencimientosAltaIdRemolque.clear();

        try {

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {

                vencimientosAltaIdRemolque.add(rs.getString("ID"));
                mvencimientosAltaRemolque.addRow(new Object[]{rs.getString("Vencimiento"), rs.getString("dias"), rs.getString("notas"), rs.getString("campoBD")});

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error alcargar altas vencimientos Remolque " + e);
        }

    }
    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        utils.dbInsert("INSERT INTO vencimientos_remolques (vencimiento, dias, notas, deleted) VALUES ('', '', '', NULL)");
        cargarAltaVencimientosRemolques();
    }//GEN-LAST:event_jButton39ActionPerformed

    private void tvencimientosRemoPlantillaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tvencimientosRemoPlantillaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tvencimientosRemoPlantillaMouseClicked
    void prt(String cadena) {
        if (global.usuario == 1) {
            System.out.println(cadena);
        }
    }

    private void agregarVencimientoPlantilla() {

        int vencimientoSeleccionado = tvencimientosAltaRemolque.getSelectedRow();

        if (esRegistroInvalido(vencimientoSeleccionado)) {
            JOptionPane.showMessageDialog(this, "Seleccione un vencimiento de la tabla de arriba.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String vencimientoId = vencimientosAltaIdRemolque.get(vencimientoSeleccionado);

        String obtenerVencimientIdExistente = "select ifnull(vencimientoID,0) from vencimientos_plantilla_remolques where vencimientoID = " + vencimientoId + " "
                + "and deleted IS NOT TRUE";
        boolean estaPermisoEnPlantilla = !(utils.dbConsult(obtenerVencimientIdExistente).isEmpty());

        if (estaPermisoEnPlantilla) {
            JOptionPane.showMessageDialog(this, "Permiso ya en plantilla", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        insertarVencimientoPlantillaRemolque();
    }
    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        agregarVencimientoPlantilla();
    }//GEN-LAST:event_jButton40ActionPerformed
    private boolean esRegistroInvalido(int row) {
        return row < 0;
    }

    private void insertarVencimientoPlantillaRemolque() {

        utils.dbInsert("INSERT INTO vencimientos_plantilla_remolques (vencimientoID) "
                + "VALUES ('" + vencimientosAltaIdRemolque.get(tvencimientosAltaRemolque.getSelectedRow()) + "')");
        cargarVencimientosPlantillasRemolque();

    }

    private void eliminarVenciPlantRemo() {

        int vencimientoSeleccionado = tvencimientosRemoPlantilla.getSelectedRow();

        if (esRegistroInvalido(vencimientoSeleccionado)) {
            JOptionPane.showMessageDialog(this, "Seleccione un vencimiento de la tabla de arriba.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String vencimientoId = vencimientosidP2.get(vencimientoSeleccionado);

        removerVencimientoPlantillaRemo(vencimientoId);

    }

    private void removerVencimientoPlantillaRemo(String vencimientoId) {

        utils.dbUpdate("Delete from vencimientos_plantilla_remolques where ID='" + vencimientoId + "'");
        cargarVencimientosPlantillasRemolque();

    }
    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        eliminarVenciPlantRemo();
    }//GEN-LAST:event_jButton41ActionPerformed

    private void cargarVencimientosPlantillaRemolques() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String query = "select vencimientoID from vencimientos_plantilla_remolques where deleted is null";

        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            plantillaVencimientoUnidadId.clear();

            while (rs.next()) {
                plantillaVencimientoUnidadId.add(rs.getString("vencimientoID"));
            }

        } catch (SQLException e) {
            mostrarMensajeError("Error al cargar lista de asginacion de vencimientoRemolques: " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }

    private void mostrarMensajeError(String mensajeError) {
        JOptionPane.showMessageDialog(this, mensajeError, "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void asignarPermisosAUnidades() {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String queryCajasId = "SELECT cajaId FROM cajas_tbl where status is true";

        try {

            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(queryCajasId);

            String vencimientoAsignado = "";
            int contador = 0;
            boolean fueAsignado = false, esVencimientoAsignado = true;

            while (rs.next()) {

                for (String venId : plantillaVencimientoUnidadId) {

                    vencimientoAsignado = utils.dbConsult("select IFNULL(ID,0) from choferes_vencimientos_remolques where choferID = " + rs.getString("cajaId") + " and vencimientoId = " + venId + " and deleted is null");

                    esVencimientoAsignado = !(vencimientoAsignado.isEmpty());
                    if (esVencimientoAsignado) {
                        continue;
                    }

                    utils.dbUpdate("insert into choferes_vencimientos_remolques (choferId,vencimientoId) "
                            + "values ( " + rs.getString("cajaId") + "," + venId + ")  ");

                    vencimientoAsignado = "";
                    fueAsignado = true;

                }

                contador = (fueAsignado ? (contador + 1) : contador);
                fueAsignado = false;

            }
            JOptionPane.showMessageDialog(dAltaVencimientosRemolques, "Se han actualizado los registros de " + contador + " remolques.", "Exito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            mostrarMensajeError("Fallo en asignar los permisos:" + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }
    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        cargarVencimientosPlantillaRemolques();
        asignarPermisosAUnidades();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        if (tvencimientosremolque.getSelectedRow() >= 0) {

            chooserz.showOpenDialog(dVencimientosRemolques);
            filez = chooserz.getSelectedFile();

            if (filez != null) {

                InputStream inStream = null;
                OutputStream outStream = null;
                try {
                    boolean existeCarpetaCamion = (new File(global.path + "procesoAdjuntosRemolques")).exists();
                    if (existeCarpetaCamion) {
                        (new File(global.path + "procesoAdjuntosRemolques")).mkdir();
                    }

                    boolean success = (new File(global.path + "procesoAdjuntosRemolques\\" + SelectedRemolque + "")).mkdir();
                    if (success) {
                        System.out.println("Directory: " + global.path + "procesoAdjuntosRemolques\\" + SelectedRemolque + "" + " created");
                    }

                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }

                try {
                    bfilez = new File(global.path + "procesoAdjuntosRemolques\\" + SelectedRemolque + "\\[" + utils.dbConsult("select replace((now()),':','.')") + "] " + filez.getName());
                    System.out.println(bfilez);

                    inStream = new FileInputStream(filez);
                    outStream = new FileOutputStream(bfilez);

                    byte[] buffer = new byte[1024];

                    int length;
                    //copy the file content in bytes
                    while ((length = inStream.read(buffer)) > 0) {
                        outStream.write(buffer, 0, length);
                    }
                    inStream.close();
                    outStream.close();
                    System.out.println("File is copied successful!");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(dVencimientosRemolques, "Error constructor " + e + "\n" + e.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
                }
                Filez = bfilez.toString();
                String DBFilez = Filez.replace("\\", "%");
                utils.dbUpdate("Update choferes_vencimientos_remolques set ruta='" + DBFilez + "', nombre='" + filez.getName() + "' where ID='" + vencimientosremolquesId.get(tvencimientosremolque.getSelectedRow()) + "'");
                filez = null;
                chooserz.setSelectedFile(filez);
                cargarVencimientosRemolque();
                JOptionPane.showMessageDialog(dVencimientosRemolques, "El archivo ha sido adjuntado.", "", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(dVencimientosRemolques, "No se ha elegido ninguna archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton34ActionPerformed
    
    void cargarVencimientosRemolque() {

        String query = "select cv.ID,v.vencimiento,Referencia,cv.vencimientoID,DATE_FORMAT(cv.Fecha, '" + global.fdatedb + "') as F \n"
                + "                 ,cv.nombre, ifnull(cv.ruta,'') as rt\n"
                + "                 from choferes_vencimientos_remolques cv\n"
                + "                 left join vencimientos_remolques v ON v.ID = cv.vencimientoID\n"
                + "                 where  choferID = " + SelectedRemolque + " and v.deleted is null and cv.deleted is null ";

        
        Connection con;
        con = utils.startConnection();

        tablemodelVencimientoRemolque.setRowCount(0);
        vencimientosremolquesId.clear();
        RutaszRemolque.clear();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String file = "", temp = "";

            while (rs.next()) {

                temp = rs.getString("rt");
                file = temp.replace("%", "\\");

                vencimientosremolquesId.add(rs.getString("ID"));
                tablemodelVencimientoRemolque.addRow(new Object[]{rs.getString("vencimiento"), rs.getString("Referencia"), rs.getString("F"), rs.getString("nombre")});
                RutaszRemolque.add(file);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void asignarVencimientoARemo() {
        boolean esRemolqueVacio = ((SelectedRemolque.isEmpty() || SelectedRemolque.equals("0")));

        if (esRemolqueVacio) {
            mostrarMensajeError("RemolqueID es vacio o 0: " + SelectedRemolque);
            return;
        }

        insertarVencimientoBaseRemo(SelectedRemolque);
        cargarVencimientosRemolque();
    }
    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed

        asignarVencimientoARemo();

    }//GEN-LAST:event_jButton35ActionPerformed
    private void insertarVencimientoBaseRemo(String selectedRemolque) {
        utils.dbInsert("INSERT INTO choferes_vencimientos_remolques (`choferID`, `vencimientoID`, fecha, ruta, nombre, deleted) "
                + "VALUES ('" + selectedRemolque + "', 0, NULL, NULL, '', NULL)");
    }
    private void removerVencimientoAsignadoRemo(){
        
        int vencimientSeleccionado = tvencimientosremolque.getSelectedRow();

        if (esRegistroInvalido(vencimientSeleccionado)) {
            mostrarMensajeError("Favor de seleccionar un vencimiento");
            return;
        }

        String vencimientoId = vencimientosremolquesId.get(vencimientSeleccionado);

        String queryObtenerVencFijo = "select ifnull(vencimientoFijo,0) from vencimientos_remolques where ID = "
                + "(select vencimientoID from choferes_vencimientos_remolques cv where cv.ID = '" + vencimientoId + "') ";

        boolean esVencimientoFijo = utils.dbConsult(queryObtenerVencFijo).equals("1");

        if (esVencimientoFijo) {
            mostrarMensajeError("No es posible borrar un vencimiento fijo");
            return;
        }
        
        eliminarVenAsignadoRemolque(vencimientoId);
        cargarVencimientosRemolque();
    }
    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed

       removerVencimientoAsignadoRemo(); 
        
    }//GEN-LAST:event_jButton36ActionPerformed
private void eliminarVenAsignadoRemolque(String vencimientoId){
    utils.dbUpdate("UPDATE choferes_vencimientos_remolques set deleted = true WHERE ID='" + vencimientoId + "'");
}
    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed

        if (tvencimientosremolque.getSelectedRow() != -1) {
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(RutaszRemolque.get(tvencimientosremolque.getSelectedRow()));
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                }
            }
        }

        tvencimientosremolque.clearSelection();
    }//GEN-LAST:event_jButton37ActionPerformed
void generarPlantillaRemolque() {

        String query = "SELECT vp.ID, v.vencimiento as venc,vencimientoID\n"
                + "FROM vencimientos_plantilla_remolques vp\n"
                + "inner join vencimientos_remolques v ON v.ID = vencimientoID\n"
                + " where v.deleted is not true and vp.deleted is not true ";

        Connection con;
        con = utils.startConnection();

        String alreadyLoaded = "";

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("vencimientoID"));
                alreadyLoaded = utils.dbConsult("select ID from choferes_vencimientos_remolques where vencimientoID='" + rs.getString("vencimientoID") + "' and choferID='" + SelectedRemolque + "' and deleted IS NOT TRUE");
                if (alreadyLoaded.isEmpty()) {
                    utils.dbInsert("INSERT INTO choferes_vencimientos_remolques (`choferID`, `vencimientoID`, fecha, ruta, nombre, deleted) "
                            + "VALUES ('" + SelectedRemolque + "', '" + rs.getString("vencimientoID") + "', NULL, NULL, '', NULL)");
                }

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        generarPlantillaRemolque();
        cargarVencimientosRemolque();
    }//GEN-LAST:event_jButton43ActionPerformed
//void cargarVencimientosRemolque() {
//
//        String query = "select cv.ID,v.vencimiento,Referencia,cv.vencimientoID,DATE_FORMAT(cv.Fecha, '" + global.fdatedb + "') as F \n"
//                + "                 ,cv.nombre, ifnull(cv.ruta,'') as rt\n"
//                + "                 from choferes_vencimientos_camiones cv\n"
//                + "                 left join vencimientos_camiones v ON v.ID = cv.vencimientoID\n"
//                + "                 where  choferID = " + SelectedRemolque + " and v.deleted is not true and cv.deleted is not true ";
//
//        Connection con;
//        con = utils.startConnection();
//
//        tablemodelVencimientoCamion.setRowCount(0);
//        vencimientosremolquesId.clear();
//        RutaszRemolque.clear();
//
//        try {
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(query);
//            String file = "", temp = "";
//
//            while (rs.next()) {
//
//                temp = rs.getString("rt");
//                file = temp.replace("%", "\\");
//
//                vencimientosremolquesId.add(rs.getString("ID"));
//                tablemodelVencimientoCamion.addRow(new Object[]{rs.getString("vencimiento"), rs.getString("Referencia"), rs.getString("F"), rs.getString("nombre")});
//                RutaszRemolque.add(file);
//            }
//            con.close();
//        } catch (SQLException e) {
//            System.out.println("Error " + e);
//        }
//    }
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
            java.util.logging.Logger.getLogger(VencimentosLib.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VencimentosLib.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VencimentosLib.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VencimentosLib.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VencimentosLib(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDialog dAltaVencimientosRemolques;
    private javax.swing.JDialog dVencimientosRemolques;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JTable tvencimientosAltaRemolque;
    private javax.swing.JTable tvencimientosRemoPlantilla;
    private javax.swing.JTable tvencimientosremolque;
    // End of variables declaration//GEN-END:variables
}
