/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modulesven;

import basic.*;
import com.alee.extended.date.WebDateField;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javafx.scene.input.KeyCode;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.text.DateFormatter;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import permisos.Permisos;
import renders.ConfigLocal;
import renders.MiRenderVencimientos;
import renders.MiRenderVencimientosCamOp;

/**
 *
 * @author admin
 */
public class VencimientosV3 extends javax.swing.JFrame {

    /**
     * Creates new form Vencimientos
     */
    int contador = 0;
    DefaultTableModel tablemodel;
    DefaultTableModel tablemodel2;
    DefaultTableModel tablemodelRemolque;
    DefaultTableModel tablemodel3;
    DefaultTableModel tablemodel4;
    DefaultTableModel tablemodel5;
    DefaultTableModel tablemodelBit;
    DefaultTableModel mvencimientos;
    DefaultTableModel mvencimientosP;
    DefaultTableModel mvencimientosP2;
    DefaultTableModel mvencimientosAltaUnidad;

    DefaultTableModel tablemodelVencimiento;
    DefaultTableModel tablemodelVencimientoChofer;
    DefaultTableModel tablemodelVencimientoUnidad;
    DefaultTableModel tablemodelVencimientoRemolque;
    DefaultTableModel tablemodelVencimientoYarda;
    DefaultTableModel tablemodelVencimientoCamion;

    ArrayList<String> vencimientosId = new ArrayList<>();
    ArrayList<String> vencimientoRemolqueId = new ArrayList<>();  // id vencimeintos box unidad combo
    ArrayList<String> vencimientoUnidadId = new ArrayList<>();  // id vencimeintos box unidad combo
    ArrayList<String> vencimientoChoferId = new ArrayList<>(); //Permisos choferes filtro
    ArrayList<String> correoSalienteId = new ArrayList<>();
    ArrayList<String> vencimientosIdChofer = new ArrayList<>();
    ArrayList<String> vencimientosIdYarda = new ArrayList<>();
    ArrayList<String> vencimientosIdExtintor = new ArrayList<>();
    ArrayList<String> tipoCorreoId = new ArrayList<>();
    ArrayList<String> vencimientosUnidad = new ArrayList<>();
    ArrayList<String> vencimientosRemolque = new ArrayList<>();
    ArrayList<String> vencimientosYarda = new ArrayList<>();
    ArrayList<String> vencimientosUnidadDina = new ArrayList<>();
    ArrayList<String> vencimientosRemolqueDina = new ArrayList<>();
    ArrayList<String> vencimientosYardasId = new ArrayList<>();
    ArrayList<String> yardaIdAltaExtintor = new ArrayList<>();
    ArrayList<String> agenteId = new ArrayList<>();
    ArrayList<String> extintorId = new ArrayList<>();
    ArrayList<String> tipoCantidadId = new ArrayList<>();
    ArrayList<String> extintorBitacoraId = new ArrayList<>();
    ArrayList<String> mesId = new ArrayList<>();
    ArrayList<String> choferId = new ArrayList<>();
    ArrayList<String> camionId = new ArrayList<>();
    ArrayList<String> remolqueId = new ArrayList<>();
    DefaultTableModel vencimientosPorYarda;
    DefaultTableModel mvencimientoschofer;
    ArrayList<String> vencimientosIdAlta = new ArrayList<>();
    ArrayList<String> vencimientosAltaIdUnidad = new ArrayList<>();
    ArrayList<String> plantillaVencimientoUnidadId = new ArrayList<>();

    JTable Excel = new JTable();
    DefaultTableModel Excel_Tablemodel;
    ArrayList<String> tipocid = new ArrayList<>();
    String Q = "";

    int SelectedExtintor;

    ArrayList<String> vencimientosidP2 = new ArrayList<>();
    ArrayList<String> vencimientosidP = new ArrayList<>();
    ArrayList<Integer> vencimientosid = new ArrayList<>();
    ArrayList<Integer> VID = new ArrayList<>();
    Map<String, String> vencimientosLista = new HashMap<>();
    Map<String, String> vencimientosListaUnidad = new HashMap<>();
    List<Integer> vencimientosUnidadId = new ArrayList<>();
    ArrayList<String> vencimientosYarda_Id = new ArrayList<>();
    ArrayList<Integer> Vencimiento_comboid = new ArrayList<>();
    ArrayList<Integer> Vencimiento_comboidChofer = new ArrayList<>();
    ArrayList<Integer> Vencimiento_comboidUnidad = new ArrayList<>();
    ArrayList<Integer> Vencimiento_comboTid = new ArrayList<>();
    ArrayList<String> vencimientoIdComboChofer = new ArrayList<>();
    ArrayList<String> vencimientoIdComboUnidad = new ArrayList<>();
    ArrayList<String> RutaszChofer = new ArrayList<>();
    ArrayList<String> RutaszCamion = new ArrayList<>();
    ArrayList<String> vencimientoscamionId = new ArrayList<>();

    ArrayList<String> RutaszYardas = new ArrayList<>();
    ArrayList<String> patiosid = new ArrayList<>();
    ArrayList<String> camionid = new ArrayList<>();
    ArrayList<Integer> vencimientoschofer_choferid = new ArrayList<>();
    ArrayList<Integer> tipoPlantillaID = new ArrayList<>();

    boolean doneLoad = false;
    JComboBox Vencimiento_combo = new JComboBox();
    JComboBox comboVencimientoChofer = new JComboBox();
    JComboBox comboVencimientoUnidad = new JComboBox();
//    JComboBox vencimiento_comboChoferVens = new JComboBox();
    WebDateField fechaVenc = new WebDateField(new Date());
    String SelectedAlmacen = "";
    JFileChooser chooserz = new JFileChooser();
    BufferedImage imgz = null;
    File filez, bfilez;
    String SelectedChofer = "";
    String SelectedCamion = "";
    String Filez = "", DBFilez = "";
    String QUERYAMPL = "";
    String listaIDVencimientosChoferFiltro = "";
    int cont = 0;

    VencimentosLib temp = new VencimentosLib(this);

    public VencimientosV3() {

        initComponents();
        cargarCorreosSalientes();
        FillCombo.cargarTiposChoferes(tipoPlantilla, tipoPlantillaID, "");

        UnidadIncidenteLabel.setHorizontalAlignment(JLabel.CENTER); // Center text horizontally
        UnidadIncidenteLabel.setVerticalAlignment(JLabel.CENTER);
        UnidadIncidenteLabel.setOpaque(true);
        ChoferIncidenteLabel.setOpaque(true);
        ChoferIncidenteLabel.setVerticalAlignment(JLabel.CENTER);
        ChoferIncidenteLabel.setHorizontalAlignment(JLabel.CENTER);

        tablemodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablemodel2 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vencimientosPorYarda = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    case 2:
                        return false;
                    default:
                        return true;
                }
            }
        };
        mvencimientoschofer = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    case 2:
                        return false;
                    default:
                        return true;
                }
            }
        };
        mvencimientoschofer.addColumn("Vencimiento");
        mvencimientoschofer.addColumn("Fecha");
        mvencimientoschofer.addColumn("Documento");

        this.tvencimientoschofer.setModel(mvencimientoschofer);

        JTableHeader Fcx = tvencimientoschofer.getTableHeader();
        Fcx.setForeground(new Color(102, 102, 102));
        tvencimientoschofer.setTableHeader(Fcx);
        tvencimientoschofer.getTableHeader().setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        tvencimientoschofer.setFillsViewportHeight(true);
        /*----------------------TABLA ALTA VENCIMIENTOS DE LOS CHOFERES--------------------------------*/

        //TABLA DE PLANTILLA---------------------
        JTableHeader FP = tvencimientosP.getTableHeader();
        FP.setForeground(new Color(102, 102, 102));
        tvencimientosP.setTableHeader(FP);
        tvencimientosP.getTableHeader().setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        tvencimientosP.setFillsViewportHeight(true);

        mvencimientosP = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    default:
                        return false;
                }
            }
        };
        mvencimientosP.addColumn("Tipo Operador");
        this.tvencimientosP.setModel(mvencimientosP);

        //TABLA DE PLANTILLA---------------------
        JTableHeader F = tvencimientos.getTableHeader();
        F.setForeground(new Color(102, 102, 102));
        tvencimientos.setTableHeader(F);
        tvencimientos.getTableHeader().setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        tvencimientos.setFillsViewportHeight(true);

        mvencimientos = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    default:
                        return true;
                }
            }
        };
        ((DefaultTableCellRenderer) tvencimientos.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        DefaultTableCellRenderer Alinear = new DefaultTableCellRenderer();
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);//.LEFT .RIGHT .CENTER

        tvencimientos.getColumnModel().getColumn(0).setCellRenderer(Alinear);
        tvencimientos.getColumnModel().getColumn(1).setCellRenderer(Alinear);
        tvencimientos.getColumnModel().getColumn(2).setCellRenderer(Alinear);

        mvencimientos.addColumn("Vencimiento");
        mvencimientos.addColumn("Renovacion (Dias)");
        mvencimientos.addColumn("Notas");
        this.tvencimientos.setModel(mvencimientos);
        tvencimientos.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                tableListenertvencimientos(e);
            }
        });

        /*----------------------TABLA ALTA VENCIMIENTOS DE LOS CHOFERES--------------------------------*/
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);//.LEFT .RIGHT .CENTER

        ((DefaultTableCellRenderer) tvencimientoschofer.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        tvencimientoschofer.getColumnModel().getColumn(0).setCellRenderer(Alinear);
        tvencimientoschofer.getColumnModel().getColumn(1).setCellRenderer(Alinear);
        tvencimientoschofer.getColumnModel().getColumn(2).setCellRenderer(Alinear);

        DefaultCellEditor vencx = new DefaultCellEditor(comboVencimientoChofer);
        tvencimientoschofer.getColumnModel().getColumn(0).setCellEditor(vencx);

        DefaultCellEditor venxX = new DefaultCellEditor(fechaVenc);
        tvencimientoschofer.getColumnModel().getColumn(1).setCellEditor(venxX);
        this.tvencimientoschofer.setModel(mvencimientoschofer);
        tvencimientoschofer.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                tableListenertvencimientoschofer(e);

            }
        });

        vencimientosPorYarda.addColumn("Vencimiento");
        vencimientosPorYarda.addColumn("Fecha");
        vencimientosPorYarda.addColumn("Documento");

        tvencimientosPorYarda.setModel(vencimientosPorYarda);

        JTableHeader Fc = tvencimientosPorYarda.getTableHeader();
        Fc.setForeground(new Color(102, 102, 102));
        tvencimientosPorYarda.setTableHeader(Fc);
        tvencimientosPorYarda.getTableHeader().setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        tvencimientosPorYarda.setFillsViewportHeight(true);
        Alinear.setHorizontalAlignment(SwingConstants.CENTER);
        tvencimientosPorYarda.getColumnModel().getColumn(0).setCellRenderer(Alinear);
        tvencimientosPorYarda.getColumnModel().getColumn(1).setCellRenderer(Alinear);
        tvencimientosPorYarda.getColumnModel().getColumn(2).setCellRenderer(Alinear);

        WebDateField fechaVenc = new WebDateField(new Date());
        DefaultCellEditor venc = new DefaultCellEditor(Vencimiento_combo);
        tvencimientosPorYarda.getColumnModel().getColumn(0).setCellEditor(venc);

        DefaultCellEditor venx = new DefaultCellEditor(fechaVenc);
        tvencimientosPorYarda.getColumnModel().getColumn(1).setCellEditor(venx);

        tvencimientosPorYarda.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                tableListenertvencimientosPorYardar(e);
            }
        });
        tablemodel3 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablemodel5 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablemodelBit = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        tablemodelBit.addColumn("Extintor");
        tablemodelBit.addColumn("Ubicacion");
        tablemodelBit.addColumn("Obstruido");
        tablemodelBit.addColumn("Pasador y Plastico");
        tablemodelBit.addColumn("Presion Correcta");
        tablemodelBit.addColumn("Comentarios");

        tablaBitacoraExt.setModel(tablemodelBit);
        Config.setTable(tablaBitacoraExt);
        Config.addCheck(tablaBitacoraExt, 2);
        Config.addCheck(tablaBitacoraExt, 3);
        Config.addCheck(tablaBitacoraExt, 4);

        tablaBitacoraExt.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
//                tableListenerVencimientosChofer(e);
                tableListenerBitacora(e);
            }
        });

        tablaFiltroUnidad1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
//                tableListenerVencimientosChofer(e);
                tableListenerVencimientosUnidad(e);
            }
        });

        tablemodel5.addColumn("Extintor");
        tablemodel5.addColumn("Nombre");
        tablemodel5.addColumn("Fecha Vencimiento");
        tablemodel5.addColumn("Capacidad");
        tablemodel5.addColumn("Agente");
        tablemodel5.addColumn("Comentarios");

        jTable5.setModel(tablemodel5);

        tablemodel3.addColumn("Remolque # Eco");
        tablemodel3.addColumn("Placas MEX");
        tablemodel3.addColumn("Placas USA");

        cargarColumnasUnidad(); // Validar esta parte para cuando no haya registros.
        jTable2.setModel(tablemodel2);

        tablemodel.addColumn("Chofer");
        jTable1.setModel(tablemodel);

        jTable1.setDefaultRenderer(Object.class, new MiRenderVencimientos());
        jTable2.setDefaultRenderer(Object.class, new MiRenderVencimientos());
        jTable4.setDefaultRenderer(Object.class, new MiRenderVencimientos());
        tablaAyuntamiento.setDefaultRenderer(Object.class, new MiRenderVencimientos());
        jTable5.setDefaultRenderer(Object.class, new MiRenderVencimientosCamOp(2));

        jTable1.getColumnModel().getColumn(0).setPreferredWidth(50);

        try {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
        } catch (Exception e) {
            prt("Error al obtener el modelo de columnas del jTable2");
        }

        jTable1.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 9));

//        cargarColumnasUnidad("0"); //0
//        cargarVencimientoCamionesDina("0");
        loadCamionModel("0");
        cargarTablaUnidadDinamica(procesarVencimientosUnidad("0"));
        cargarConteoDinamicoUnidad("0"); // 0equivale a todos los vencimientos, cargara todas las columnas, vencimientos e incidentes

        /*AQUI*/
        loadRemolqueModel("0");
        cargarTablaRemolqueDinamica(procesarVencimientosRemolque("0"));

//        cargarConteoDinamicoUnidad("0");
        cargarVencimientoRemolques();
        cargarListaVencimientos();
        //loadChofersModel();

        Excel_Tablemodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };

        Excel_Tablemodel.addColumn("1");
        Excel_Tablemodel.addColumn("2");
        Excel_Tablemodel.addColumn("3");
        Excel_Tablemodel.addColumn("4");

        Excel.setFillsViewportHeight(true);
        Excel.setModel(Excel_Tablemodel);

//        FillCombo.cargarUnidadNegocio(boxTipoOperador, tipocid, "Ver Todos");
        tablemodelVencimiento = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        tablemodel4 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaAyuntamiento.setModel(tablemodel4);

        /*Filtro unidades*/
        tablemodelVencimientoUnidad = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        tablemodelVencimientoUnidad.addColumn("Vencimiento");
        tablemodelVencimientoUnidad.addColumn("Filtro Aplicado");
        tablaFiltroUnidad1.setModel(tablemodelVencimientoUnidad);
        Config.setTable(tablaFiltroUnidad1);
        Config.addCheck(tablaFiltroUnidad1, 1);

        tablaFiltroUnidad1.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
//                tableListenerVencimientosChofer(e);
                tableListenerVencimientosUnidad(e);
            }
        });

        /*Filtro remolque*/
        tablemodelVencimientoRemolque = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        tablemodelVencimientoRemolque.addColumn("Vencimiento");
        tablemodelVencimientoRemolque.addColumn("Filtro Aplicado");
        tablaFiltroRemolque.setModel(tablemodelVencimientoRemolque);
        Config.setTable(tablaFiltroRemolque);
        Config.addCheck(tablaFiltroRemolque, 1);

        tablaFiltroRemolque.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
//                tableListenerVencimientosChofer(e);
                tableListenerVencimientosRemolque(e);
            }
        });


        /*Filtro unidades*/
        tablemodelVencimientoChofer = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }
        };
        tablemodelVencimientoChofer.addColumn("Vencimiento");
        tablemodelVencimientoChofer.addColumn("Filtro Aplicado");
        tablaFiltroChofer.setModel(tablemodelVencimientoChofer);
        Config.setTable(tablaFiltroChofer);
        Config.addCheck(tablaFiltroChofer, 1);

        tablaFiltroChofer.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                tableListenerVencimientosChofer(e);
            }
        });
        //--------------------------TABLA ALTA VENCIMIENTOS UNIDAD ---------------------------------------------------
        mvencimientosAltaUnidad = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                // Verifica si la columna es editable
                return col != 3; // Devuelve true si la columna no es la columna 3
            }
        };

        JTableHeader Fx = tvencimientosAltaUnidad.getTableHeader();
        Fx.setForeground(new Color(102, 102, 102));
        tvencimientosAltaUnidad.setTableHeader(Fx);
        tvencimientosAltaUnidad.getTableHeader().setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        tvencimientosAltaUnidad.setFillsViewportHeight(true);

        mvencimientosAltaUnidad.addColumn("Vencimiento");
        mvencimientosAltaUnidad.addColumn("Renovacion (Dias)");
        mvencimientosAltaUnidad.addColumn("Notas");
        mvencimientosAltaUnidad.addColumn("Notacion sistema");

        this.tvencimientosAltaUnidad.setModel(mvencimientosAltaUnidad);

        ((DefaultTableCellRenderer) tvencimientosAltaUnidad.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        Alinear.setHorizontalAlignment(SwingConstants.CENTER);//.LEFT .RIGHT .CENTER

        tvencimientosAltaUnidad.getColumnModel().getColumn(0).setCellRenderer(Alinear);
        tvencimientosAltaUnidad.getColumnModel().getColumn(1).setCellRenderer(Alinear);
        tvencimientosAltaUnidad.getColumnModel().getColumn(2).setCellRenderer(Alinear);

        tvencimientosAltaUnidad.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                tableListenertvencimientosAltaUnidad(e);

            }
        });

        JTableHeader FPx = tvencimientosP2.getTableHeader();
        FPx.setForeground(new Color(102, 102, 102));
        tvencimientosP2.setTableHeader(FPx);
        tvencimientosP2.getTableHeader().setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        tvencimientosP2.setFillsViewportHeight(true);

        mvencimientosP2 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int col) {
                switch (col) {
                    default:
                        return false;
                }
            }
        };
        mvencimientosP2.addColumn("Vencimiento Plantilla");
        this.tvencimientosP2.setModel(mvencimientosP2);
        //--------------------------TABLA ALTA VENCIMIENTOS UNIDAD --------------------------------------------------- 

        tablemodelVencimiento.addColumn("Vencimiento");
        tablemodelVencimiento.addColumn("Administrativo");
        tablemodelVencimiento.addColumn("Gerencia");
        tablaVencimientos.setModel(tablemodelVencimiento);
        Config.setTable(tablaVencimientos);
        Config.addCheck(tablaVencimientos, 1);
        Config.addCheck(tablaVencimientos, 2);

        tablaVencimientos.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                tableListenerVencimientos(e);
            }
        });

//        cargarTipoCorreo();
//---------------------------------------------------------------
        tablemodelVencimientoCamion = new DefaultTableModel() {
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

        JTableHeader Fce = tvencimientosunidad.getTableHeader();
        Fce.setForeground(new Color(102, 102, 102));
        tvencimientosunidad.setTableHeader(Fce);
        tvencimientosunidad.getTableHeader().setFont(new Font("Myriad Pro", Font.PLAIN, 14));
        tvencimientosunidad.setFillsViewportHeight(true);

        tablemodelVencimientoCamion.addColumn("Vencimiento");
        tablemodelVencimientoCamion.addColumn("Referencia");
        tablemodelVencimientoCamion.addColumn("Fecha");
        tablemodelVencimientoCamion.addColumn("Documento");

        this.tvencimientosunidad.setModel(tablemodelVencimientoCamion);

        ((DefaultTableCellRenderer) tvencimientosunidad.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);

        tvencimientosunidad.getColumnModel().getColumn(0).setCellRenderer(Alinear);
        tvencimientosunidad.getColumnModel().getColumn(1).setCellRenderer(Alinear);
        tvencimientosunidad.getColumnModel().getColumn(2).setCellRenderer(Alinear);
        tvencimientosunidad.getColumnModel().getColumn(3).setCellRenderer(Alinear);

        DefaultCellEditor vencUnid = new DefaultCellEditor(comboVencimientoUnidad);
        tvencimientosunidad.getColumnModel().getColumn(0).setCellEditor(vencUnid);

        WebDateField fechaVencUnid = new WebDateField(new Date());
        DefaultCellEditor venxUni = new DefaultCellEditor(fechaVencUnid);
        tvencimientosunidad.getColumnModel().getColumn(2).setCellEditor(venxUni);

        ConfigLocal.WebDate(fechaVencUnid, 14);

        tvencimientosunidad.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {

                tableListenertvencimientosunidad(e);

            }
        });
        /*tablemodelVencimientoCamion.setRowCount(0);
        vencimientoscamionId.clear();
        RutaszCamion.clear();*/

        //   ---------------------------------------------------------------
        cargarListaVencimientosCamion();
        cargarListaVencimientosRemolque();
        cargarColumnasYarda();
        cargarVencimientosYardas();

        cargarTodoExtintores();
        cargarListaVencimientosYardaCombo();
        cargarVencimientosPlantillasUnidad();
        doneLoad = true;
//        ejecutarPermisos();
//
//        panelYardas.setVisible(Permisos.esVentanaYardaVisible());
//
//        panelExtintores.setVisible(Permisos.esVentanaExtintoresVisible());

    }
//    private void ejecutarPermisos(){
//        try{
//           boolean ocultarCorreos = (Permisos.estaCorreosDisponible() == false);
//
//        if (ocultarCorreos) {
//            esconderElementosPermisos();
//        } 
//        }catch(Exception e){
//            System.out.println("Fallo en ejecutar permisos:"+ e);
//            esconderElementosPermisos();
//        }
//    }
//    private void esconderElementosPermisos(){
//       jButton3.setVisible(false);
//            jButton4.setVisible(false); 
//    }

    private void tableListenertvencimientosunidad(javax.swing.event.TableModelEvent e) {

        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {

                int row = tvencimientosunidad.getSelectedRow();
                int col = tvencimientosunidad.getSelectedColumn();

                try {

                    if (col == 0) {
                        if (comboVencimientoUnidad.getSelectedIndex() >= 0) {
                            vencimientoIdComboUnidad.set(row, vencimientoIdComboUnidad.get(comboVencimientoUnidad.getSelectedIndex()));
                        }
                        int indexComboVencimiento = comboVencimientoUnidad.getSelectedIndex();
                        boolean esVencimientoDuplicado = !(utils.dbConsult("select ifnull(ID,'') from choferes_vencimientos_camiones where vencimientoID = " + vencimientoIdComboUnidad.get(indexComboVencimiento) + " and choferId = " + SelectedCamion + " and deleted is null").isEmpty());
                        boolean noEsModificable = utils.dbConsult("select IFNULL(vencimientoFijo,'0')  from vencimientos_Camiones where ID = (select vencimientoId from choferes_vencimientos_camiones where ID = " + vencimientoscamionId.get(row) + " limit 1) ").equals("1");

                        if (esVencimientoDuplicado) {
                            JOptionPane.showMessageDialog(this, "No se puede duplicar los vencimientos");
                            cargarVencimientosUnidad();
                            return;
                        }

                        if (noEsModificable) {
                            JOptionPane.showMessageDialog(this, "No se pueden alterar los conceptos fijos");
                            cargarVencimientosUnidad();
                            return;
                        }

                        utils.dbUpdate("Update choferes_vencimientos_camiones set vencimientoID='" + vencimientoIdComboUnidad.get(indexComboVencimiento) + "' where ID='" + vencimientoscamionId.get(row) + "'");

                    }

                    if (col == 1) {
                        utils.dbUpdate("Update choferes_vencimientos_camiones set referencia='" + tvencimientosunidad.getValueAt(row, 1).toString() + "' where ID='" + vencimientoscamionId.get(row) + "'");
                    }

                    if (col == 2) {
                        String fechaVencimiento = utils.dateFromFieldtoDBwoH(tvencimientosunidad.getValueAt(row, 2).toString(), true);
                        String registroId = vencimientoscamionId.get(row);
                        boolean esVencimientoFijo = !(utils.dbConsult("select IFNULL(campoBd,'')  from vencimientos_Camiones where vencimientoFijo is true and ID = (select vencimientoId from choferes_vencimientos_camiones where ID = " + vencimientoscamionId.get(row) + " limit 1)").isEmpty());
                        String campoBDVencimiento = utils.dbConsult("select IFNULL(campoBd,'')  from vencimientos_Camiones where vencimientoFijo is true and ID = (select vencimientoId from choferes_vencimientos_camiones where ID = " + vencimientoscamionId.get(row) + " limit 1)");

                        utils.dbUpdate("Update choferes_vencimientos_camiones set fecha='" + fechaVencimiento + "' where ID='" + registroId + "'");
                        registrarLog(fechaVencimiento,registroId);
                        if (esVencimientoFijo) {
                            utils.dbUpdate("update camiones_tbl set " + campoBDVencimiento + " = '" + fechaVencimiento + "' where CamionId = " + SelectedCamion + " ");
                        }
                        cargarTablaUnidad();
                    }

                } catch (Exception o) {
                    JOptionPane.showMessageDialog(this, "Favor de Selecionar un Elemento\nError " + o, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                cont = 0;
            }
        }
    }

    private void registrarLog(String valor, String registroId) {

        String logDesdeVencimientos = "2";
        String usuarioModificando = String.valueOf(global.usuario);
        String unidadModificada = utils.dbConsult("SELECT IFNULL(choferID,0) FROM choferes_vencimientos_camiones where ID = " + registroId);
        String vencimientoDesc = utils.dbConsult("SELECT IFNULL(vencimiento,'') FROM choferes_vencimientos_camiones cvc\n"
                + "left join vencimientos_camiones vc ON vc.ID = cvc.vencimientoID\n"
                + "where cvc.ID = "+registroId+" limit 1; ");

        if (unidadModificada.isEmpty()) {
            return;
        }

        utils.dbConsult("SELECT insertVencimientoLog('" + valor + " - "+vencimientoDesc+"'," + usuarioModificando + "," + unidadModificada + "," + logDesdeVencimientos + ");");

    }

    void cargarListaVencimientosComboUnidad() {
        String query = "SELECT *, concat(vencimiento,' [',dias,' dias]') as venc from vencimientos_camiones where deleted is not true and vencimiento != ''  order by ID asc";
        vencimientoIdComboUnidad.clear();
        comboVencimientoUnidad.removeAllItems();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                vencimientoIdComboUnidad.add(rs.getString("ID"));
                comboVencimientoUnidad.addItem(rs.getString("venc"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    private void tableListenertvencimientosAltaUnidad(javax.swing.event.TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {
                int row = tvencimientosAltaUnidad.getSelectedRow();
                int col = tvencimientosAltaUnidad.getSelectedColumn();
                try {

                    if (col == 0) {
                        utils.dbUpdate("Update vencimientos_camiones set vencimiento='" + tvencimientosAltaUnidad.getValueAt(row, col) + "' where ID='" + vencimientosAltaIdUnidad.get(row) + "'");
                        cargarVencimientosPlantillasUnidad();
                        cargarTablaUnidad();
                        cargarListaVencimientosCamion();
                    }
                    if (col == 1) {

                        cont = 1;
                        tvencimientosAltaUnidad.setValueAt(utils.dbConsult("select digits('" + tvencimientosAltaUnidad.getValueAt(row, col) + "')"), row, col);

                        utils.dbUpdate("Update vencimientos_camiones set dias='" + tvencimientosAltaUnidad.getValueAt(row, col) + "' where ID='" + vencimientosAltaIdUnidad.get(row) + "'");
                        cargarTablaUnidad();
                    }

                    if (col == 2) {
                        utils.dbUpdate("Update vencimientos_camiones set notas='" + tvencimientosAltaUnidad.getValueAt(row, col) + "' where ID='" + vencimientosAltaIdUnidad.get(row) + "'");
                    }

                } catch (Exception o) {
                    JOptionPane.showMessageDialog(this, "Favor de Selecionar un Elemento\nError " + o, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                cont = 0;
            }
        }
    }

    private void tableListenertvencimientos(javax.swing.event.TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {
                int row = tvencimientos.getSelectedRow();
                int col = tvencimientos.getSelectedColumn();
                try {

                    if (col == 0) {
                        utils.dbUpdate("Update vencimientos set vencimiento='" + tvencimientos.getValueAt(row, col) + "' where ID='" + vencimientosIdAlta.get(row) + "'");
                        cargarVencimientosPlantillas();
                        String vencimientosIdsConcat = String.valueOf(vencimientosid.get(boxVencimientosChoferes.getSelectedIndex())); //bien
                        loadChofersModel(vencimientosIdsConcat); //bien 
                        cargarTablaChoferDinamica(procesarVencimientos(vencimientosIdsConcat));
                        cargarConteoDinamico(vencimientosIdsConcat);
                        cargarListaVencimientos();
                    }

                    if (col == 1) {
                        utils.dbUpdate("Update vencimientos set dias='" + tvencimientos.getValueAt(row, col) + "' where ID='" + vencimientosIdAlta.get(row) + "'");
                    }

                    if (col == 2) {
                        utils.dbUpdate("Update vencimientos set notas='" + tvencimientos.getValueAt(row, col) + "' where ID='" + vencimientosIdAlta.get(row) + "'");
                    }

                } catch (Exception o) {
                    JOptionPane.showMessageDialog(this, "Favor de Selecionar un Elemento\nError " + o, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                cont = 0;
            }
        }
    }

    void cargarVencimientosPlantillasUnidad() {

        String query = "SELECT vencimientos_plantilla_camiones.ID, (select nombre from tiposchoferes_tbl where TipoC=vencimientos_plantilla_camiones.tipoID) as venx, \n"
                + "v.vencimiento as venc \n"
                + " FROM vencimientos_plantilla_camiones \n"
                + " left join vencimientos_camiones v ON v.ID = vencimientos_plantilla_camiones.vencimientoID\n"
                + " where v.vencimiento is not null and v.deleted is null and vencimientos_plantilla_camiones.deleted is null";//where tipoID='" + boxTipoPermiso1ID.get(tipoPlantilla1.getSelectedIndex()) + "'";

        Connection con;
        con = utils.startConnection();

        mvencimientosP2.setRowCount(0);
        vencimientosidP2.clear();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                vencimientosidP2.add(rs.getString("ID"));
                mvencimientosP2.addRow(new Object[]{rs.getString("venc")});
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    void cargarVencimientosPlantillas() {

        if (tipoPlantilla.getSelectedIndex() >= 0) {
            String tipoId = utils.dbConsult("select TipoID from tiposchoferes_tbl where Nombre = '" + tipoPlantilla.getSelectedItem() + "'");
            String query = "SELECT vp.ID, v.vencimiento as venc\n"
                    + " FROM vencimientos_plantilla vp\n"
                    + " inner join vencimientos v ON v.ID = vencimientoID\n"
                    + " where tipoID=" + tipoId + " and v.deleted is not true and vp.deleted is not true  ";

            //System.out.println("Query de plantilla: " + query);
            Connection con;
            con = utils.startConnection();

            mvencimientosP.setRowCount(0);
            vencimientosidP.clear();

            try {
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {

                    vencimientosidP.add(rs.getString("ID"));
                    mvencimientosP.addRow(new Object[]{rs.getString("venc")});
                }
                con.close();
            } catch (SQLException e) {
                System.out.println("Error " + e);
            }

        }

    }

    void cargarVencimientosPlantillasChofer() {

        if (tipoPlantilla.getSelectedIndex() >= 0) {
            String tipoId = utils.dbConsult("select TipoID from tiposchoferes_tbl where Nombre = '" + tipoPlantilla.getSelectedItem() + "'");
            String query = "SELECT vp.ID, v.vencimiento as venc\n"
                    + " FROM vencimientos_plantilla vp\n"
                    + " inner join vencimientos v ON v.ID = vencimientoID\n"
                    + " where tipoID=" + tipoId + " and v.deleted is not true and vp.deleted is not true  ";

            //System.out.println("Query de plantilla: " + query);
            Connection con;
            con = utils.startConnection();

            mvencimientosP.setRowCount(0);
            vencimientosidP.clear();

            try {
                Statement statement = con.createStatement();
                ResultSet rs = statement.executeQuery(query);
                while (rs.next()) {

                    vencimientosidP.add(rs.getString("ID"));
                    mvencimientosP.addRow(new Object[]{rs.getString("venc")});
                }
                con.close();
            } catch (SQLException e) {
                System.out.println("Error " + e);
            }

        }

    }

    private void tableListenertvencimientoschofer(javax.swing.event.TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {

                int row = tvencimientoschofer.getSelectedRow();
                int col = tvencimientoschofer.getSelectedColumn();

                try {

                    if (col == 0) {
                        if (comboVencimientoChofer.getSelectedIndex() >= 0) {
//                            vencimientoIdComboChofer.set(row, vencimientoIdComboChofer.get(comboVencimientoChofer.getSelectedIndex()));
                            int indexComboVencimientos = comboVencimientoChofer.getSelectedIndex();
                            String permisoYaAsignado = utils.dbConsult("select IFNULL(ID,'') from choferes_vencimientos where choferID = " + SelectedChofer + " and vencimientoID=" + vencimientoIdComboChofer.get(indexComboVencimientos) + " and deleted is null");
                            if (permisoYaAsignado.isEmpty()) {
                                utils.dbUpdate("Update choferes_vencimientos set vencimientoID='" + vencimientoIdComboChofer.get(indexComboVencimientos) + "' where ID='" + vencimientoschofer_choferid.get(row) + "'");

                            } else {
                                JOptionPane.showMessageDialog(this, "Vencimiento ya asignado", "Error", JOptionPane.ERROR_MESSAGE);
                                cargarVencimientosChofer();
                            }
                        }
                    }

                    if (col == 1) {
                        utils.dbUpdate("Update choferes_vencimientos set fecha='" + utils.dateFromFieldtoDB(tvencimientoschofer.getValueAt(row, 1).toString()) + "' where ID='" + vencimientoschofer_choferid.get(row) + "'");
                        String vencimientosIdsConcat = String.valueOf(vencimientosid.get(boxVencimientosChoferes.getSelectedIndex())); //bien
                        loadChofersModel(vencimientosIdsConcat); //bien
                        cargarTablaChoferDinamica(procesarVencimientos(vencimientosIdsConcat));
                        cargarConteoDinamico(vencimientosIdsConcat);
                    }

                } catch (Exception o) {
                    JOptionPane.showMessageDialog(this, "Favor de Selecionar un Elemento\nError " + o, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                cont = 0;
            }
        }
    }

    void cargarListaVencimientosYardaCombo() {

        String query = "select permisoAyunId,nombre from permisos_ayuntamiento_tbl where estatus = 1 and permisoAyunId != 0 and nombre != ''";
        Vencimiento_combo.removeAllItems();
        Vencimiento_comboid.clear();

        Connection con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Vencimiento_combo.addItem(rs.getString("nombre"));
                Vencimiento_comboid.add(rs.getInt("permisoAyunId"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void tableListenertvencimientosPorYardar(javax.swing.event.TableModelEvent e) {
        if (e.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {

                int row = tvencimientosPorYarda.getSelectedRow();
                int col = tvencimientosPorYarda.getSelectedColumn();

                try {

                    if (col == 0) {
                        if (Vencimiento_combo.getSelectedIndex() >= 0) {
                            Vencimiento_comboTid.set(row, Vencimiento_comboid.get(Vencimiento_combo.getSelectedIndex()));
                        }
                        String patioId = patiosid.get(tablaAyuntamiento.getSelectedRow());
                        String patioVen = utils.dbConsult("select patioVenId from patios_vencimientos"
                                + " where permisoId = " + Vencimiento_comboTid.get(row) + " and patioId = " + patioId + " and estatus = 1 limit 1");

                        if (patioVen.isEmpty()) {
                            utils.dbUpdate("Update patios_vencimientos set permisoId ='" + Vencimiento_comboTid.get(row) + "' where patioVenId ='" + vencimientosYarda_Id.get(row) + "'");
                        } else {
                            JOptionPane.showMessageDialog(this, "Permiso ya asignado");
                            tvencimientosPorYarda.clearSelection();
//                            Vencimiento_combo.setSelectedIndex(-1);
                            cargarVencimientosAlmacen(patioId);
                        }
                    }

                    if (col == 1) {
                        utils.dbUpdate("Update patios_vencimientos set fechaVencimiento ='" + utils.dateFromFieldtoDB(tvencimientosPorYarda.getValueAt(row, 1).toString()) + "' where patioVenId ='" + vencimientosYarda_Id.get(row) + "'");
                        cargarColumnasYarda(vencimientosYardasId.get(boxAyuntamiento.getSelectedIndex()));
                        cargarTablaAyuntamiento(procesarVencimientosYardas(vencimientosYardasId.get(boxAyuntamiento.getSelectedIndex())));
                    }

                } catch (Exception o) {
                    JOptionPane.showMessageDialog(this, "Favor de Selecionar un Elemento\nError " + o, "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                cont = 0;
            }
        }
    }

    private String cargarUnidadVencimientosDina(String vencimientos) {
        String query = utils.dbConsult("select concat('NoEconomico,',\n"
                + "group_concat('DATE_FORMAT(',vencimiento,',''%d.%m.%Y'')' separator ',')) as vens from correosvencimiento \n"
                + "where correoReporteId " + (vencimientos.equals("0") ? " > 0" : " in (" + vencimientos + ") "));
//        String query = utils.dbConsult("select concat('NoEconomico,',\n"
//                + "group_concat(' IF(convert(',vencimiento,',date) = ''0001-01-01'','''',date_format(',vencimiento,',''%d.%m.%Y''))' separator ',')) \n"
//                + "as vens from correosvencimiento \n"
//                + "where correoReporteId " + (vencimientos.equals("0") ? " > 0" : " in (" + vencimientos + ") "));
        return query;
    }

    void cargarMeses() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select numeroMes,nombreMes from meses";
        mesId.clear();
        comboMes.removeAllItems();
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                mesId.add(rs.getString("numeroMes"));
                comboMes.addItem(rs.getString("nombreMes"));
            }
        } catch (SQLException e) {
            prt("Error al cargas el mes: " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    void cargarYear() {
        comboYear.removeAllItems();
        for (int i = 2023; i < 2100; i++) {
            comboYear.addItem(String.valueOf(i));
        }
        String year = String.valueOf(Year.now().getValue());
        comboYear.setSelectedItem(year);
    }

    private void cargarColumnasRemolque(String ven) {
        tablemodelRemolque.setColumnCount(0);
        vencimientosRemolque.clear();
        tablemodelRemolque.addColumn("NO. Economico");
        vencimientosRemolque.add("0");
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select correoReporteId,aliasVencimiento "
                + "from correosvencimiento where correoReporteId " + (ven.equals("0") ? " > 0" : "in (" + ven + ")");

        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                tablemodelRemolque.addColumn(rs.getString("aliasVencimiento"));
                vencimientosRemolque.add(rs.getString("correoReporteId"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al carga columnas unidad: " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    private void cargarColumnasUnidad(String ven) {
        tablemodel2.setColumnCount(0);
        vencimientosUnidad.clear();
        tablemodel2.addColumn("NO. Economico");
        vencimientosUnidad.add("0");
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select correoReporteId,aliasVencimiento "
                + "from correosvencimiento where correoReporteId " + (ven.equals("0") ? " > 0" : "in (" + ven + ")");

        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                tablemodel2.addColumn(rs.getString("aliasVencimiento"));
                vencimientosUnidad.add(rs.getString("correoReporteId"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al carga columnas unidad: " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    private void cargarBitacora() {

        if (mesId.size() > 0 && comboYear.getSelectedIndex() != -1 && comboMes.getSelectedIndex() != -1) {

            String bitacoraId = utils.dbConsult("select bitacoraId from bitacoras_tbl where mes = " + mesId.get(comboMes.getSelectedIndex()) + " and anio = " + comboYear.getSelectedItem().toString());
            String query = "";
            if (!(bitacoraId.isEmpty())) {
                query = "select e.extintorId,e.identificador,pa.Nombre as almacen, obstruido, pasador_plastico, presion_correcta ,bm.comentarios,\n"
                        + "            bm.bitacoraMensual\n"
                        + "                from extintores_tbl e \n"
                        + "                left join patios_tbl pa ON pa.patioid = e.yardaid\n"
                        + "                left join bitacora_mensual_ext bm ON bm.extintorid = e.extintorid\n"
                        + "                left join bitacoras_tbl b ON b.bitacoraId = bm.bitacoraMensual \n"
                        + "                where b.bitacoraId = " + bitacoraId;

                String quienReviso = utils.dbConsult("select personaRevision from bitacoras_tbl where bitacoraId =" + bitacoraId);
                nombreReviso.setText(quienReviso);
            } else {
                query = "select e.extintorId,e.identificador,pa.Nombre as almacen,\n"
                        + "                false as obstruido,\n"
                        + "                false as pasador_plastico,\n"
                        + "                false as presion_correcta,\n"
                        + "                ''  as comentarios\n"
                        + "                from extintores_tbl e \n"
                        + "                left join patios_tbl pa ON pa.patioid = e.yardaid\n"
                        + "                where e.estatus =1 ";
                nombreReviso.setText("Nombre de quien reviso");
            }

            tablemodelBit.setRowCount(0);
            extintorBitacoraId.clear();
            Connection con = null;
            Statement st = null;
            ResultSet rs = null;
            try {
                con = utils.startConnection();
                st = con.createStatement();
                rs = st.executeQuery(query);
                while (rs.next()) {
                    extintorBitacoraId.add(rs.getString("e.extintorId"));
                    tablemodelBit.addRow(new Object[]{rs.getString("identificador"), rs.getString("almacen"), rs.getBoolean("obstruido"), rs.getBoolean("pasador_plastico"),
                        rs.getBoolean("presion_correcta"), rs.getString("comentarios")});
                }
            } catch (SQLException e) {
                prt("error al cargar tabla bitacora:" + e);
            } finally {
                utils.closeAllConnections(con, st, rs);
            }
        }
    }

    private void cargarColumnasYarda() {
        tablemodel4.setColumnCount(0);
        vencimientosYarda.clear();
        tablemodel4.addColumn("Yarda");
        vencimientosYarda.add("0");
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select permisoAyunId, nombre from permisos_ayuntamiento_tbl where permisoAyunId != 0 and nombre != '' and estatus = 1 order by permisoAyunId asc";
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                tablemodel4.addColumn(rs.getString("nombre"));
                vencimientosYarda.add(rs.getString("permisoAyunId"));
            }
        } catch (SQLException e) {
            prt("Error al cargas columnas de yarda: " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    void cargarTodoExtintores() {

        cargarTablaExtintor();
        cargarPatios(yardaIdAltaExtintor, patiosAltaBox);
        cargarAgentesExtintor();
        cargarUnidadCapacidad();
        cargarTipoCapacidad();
        cargarMeses();
        cargarYear();
        precargarCombosBitacora();
    }

    private void cargarColumnasUnidad() {
        tablemodel2.setColumnCount(0);
        vencimientosUnidad.clear();
        tablemodel2.addColumn("NoEconomico");
        vencimientosUnidad.add("0");
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select correoReporteId,aliasVencimiento from correosvencimiento ";
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                tablemodel2.addColumn(rs.getString("aliasVencimiento"));
                vencimientosUnidad.add(rs.getString("correoReporteId"));
            }
        } catch (SQLException e) {
            prt("Error al cargar columnas de unidad: " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    void precargarCombosBitacora() {
    }
//        // Obtener la fecha actual
//        LocalDate fechaActual = LocalDate.now();
//        
//        // Obtener el año actual
//        int anioActual = fechaActual.getYear();
//        
//        // Obtener el mes actual
//        int mesActual = fechaActual.getMonthValue();
//        if(mesActual == -1){
//            mesActual =1;
//        }
//        comboMes.setSelectedIndex(mesId.indexOf(mesActual));
//        comboYear.setSelectedItem(anioActual);
//        
//        
//    }

    void tableListenerBitacora(javax.swing.event.TableModelEvent evt) {
        int row = tablaBitacoraExt.getSelectedRow();
        int col = tablaBitacoraExt.getSelectedColumn();

        if (evt.getType() == TableModelEvent.UPDATE) {
            if (cont < 1) {
                String ext = extintorBitacoraId.get(row);
                String bit = utils.dbConsult("select bitacoraId from bitacoras_tbl where mes=" + mesId.get(comboMes.getSelectedIndex()) + " and "
                        + " anio =" + comboYear.getSelectedItem());
                if (col == 5) {
                    utils.dbUpdate("update bitacora_mensual_ext set comentarios = '" + tablaBitacoraExt.getValueAt(row, col) + "' "
                            + " where extintorId =" + ext + " and bitacoraMensual = " + bit);
                }

            }
        } else {
            cont = 0;
        }
    }

    private void tableListenerVencimientosRemolque(javax.swing.event.TableModelEvent evt) {
        int row = tablaFiltroRemolque.getSelectedRow();
        int col = tablaFiltroRemolque.getSelectedColumn();
        if (evt.getType() == TableModelEvent.UPDATE) {

        }
    }

    private void tableListenerVencimientosUnidad(javax.swing.event.TableModelEvent evt) {
        int row = tablaFiltroChofer.getSelectedRow();
        int col = tablaFiltroChofer.getSelectedColumn();
        if (evt.getType() == TableModelEvent.UPDATE) {

        }
    }

    private void tableListenerVencimientosChofer(javax.swing.event.TableModelEvent evt) {
        int row = tablaFiltroChofer.getSelectedRow();
        int col = tablaFiltroChofer.getSelectedColumn();
        if (evt.getType() == TableModelEvent.UPDATE) {

        }
    }

    private void tableListenerVencimientos(javax.swing.event.TableModelEvent evt) {
        int row = tablaVencimientos.getSelectedRow();
        int col = tablaVencimientos.getSelectedColumn();
        if (evt.getType() == TableModelEvent.UPDATE) {
            if (comboTipoVencimientos.getSelectedIndex() == 0) {
                if (col == 1) {
                    String correoReporteId = utils.dbConsult("select correoReporteId from correoReportes where usuarioId = " + global.usuario + " and "
                            + " vencimientoId = " + vencimientosId.get(row));
                    boolean admin = (boolean) (tablaVencimientos.getValueAt(row, 1));
                    if (correoReporteId.isEmpty()) {
                        String venc = utils.dbInsert("insert into correoReportes (correoReporteId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                + " values ('0'," + vencimientosId.get(row) + "," + global.usuario + "," + admin + "," + false + ")");
                        if (venc.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "No fue insertado el registro", "Error", HEIGHT);
                        } else {
                            System.out.println("Fue insertado con exito");
                            System.out.println("insert into correoReportes (correoReporteId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                    + " values ('0'," + vencimientosId.get(row) + "," + global.usuario + "," + admin + "," + false + ")");
                        }
                    } else {
                        utils.dbUpdate(" UPDATE correoReportes set tipoAdmin = " + admin + " where correoReporteId = " + correoReporteId);
                    }
                }
                if (col == 2) {
                    String correoReporteId = utils.dbConsult("select correoReporteId from correoReportes where usuarioId = " + global.usuario + " and "
                            + " vencimientoId = " + vencimientosId.get(row));
                    boolean gerencia = (boolean) tablaVencimientos.getValueAt(row, 2);
                    if (correoReporteId.isEmpty()) {
                        String venc = utils.dbInsert("insert into correoReportes (correoReporteId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                + " values ('0'," + vencimientosId.get(row) + "," + global.usuario + "," + false + "," + gerencia + ")");
                        if (venc.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "No fue insertado el registro", "Error", HEIGHT);
                        } else {
                            System.out.println("Fue insertado con exito");
                            System.out.println("insert into correoReportes (correoReporteId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                    + " values ('0'," + vencimientosId.get(row) + "," + global.usuario + "," + false + "," + gerencia + ")");
                        }
                    } else {
                        String venc = utils.dbUpdate(" UPDATE correoReportes set  tipoGerencia = " + gerencia + " where correoReporteId = " + correoReporteId);
                    }

                }
            }
            if (comboTipoVencimientos.getSelectedIndex() == 1) {
                if (col == 1) {
                    // reporteChoferId correoreportesChofer usuarioId vencimientoId
                    String correoReporteId = utils.dbConsult("select reporteChoferId from correoreportesChofer where usuarioId = " + global.usuario + " and "
                            + " vencimientoId = " + vencimientosIdChofer.get(row));
                    System.out.println("Admin : " + tablaVencimientos.getValueAt(row, 1) + " \n gerencia: " + tablaVencimientos.getValueAt(row, 2));
                    boolean admin = (boolean) (tablaVencimientos.getValueAt(row, 1));
//                boolean gerencia = Boolean.parseBoolean(tablaVencimientos.getValueAt(row, 2).toString());
                    if (correoReporteId.isEmpty()) {
                        String venc = utils.dbInsert("insert into correoreportesChofer (reporteChoferId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                + " values ('0'," + vencimientosIdChofer.get(row) + "," + global.usuario + "," + admin + "," + false + ")");
                        if (venc.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "No fue insertado el registro", "Error", HEIGHT);
                        } else {
                            System.out.println("Fue insertado con exito");
                            System.out.println("insert into correoreportesChofer (reporteChoferId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                    + " values ('0'," + vencimientosIdChofer.get(row) + "," + global.usuario + "," + admin + "," + false + ")");
                        }
                    } else {
                        utils.dbUpdate(" UPDATE correoreportesChofer set tipoAdmin = " + admin + " where reporteChoferId = " + correoReporteId);
                    }
                }
                if (col == 2) {
                    String correoReporteId = utils.dbConsult("select reporteChoferId from correoreportesChofer where usuarioId = " + global.usuario + " and "
                            + " vencimientoId = " + vencimientosIdChofer.get(row));
                    boolean gerencia = (boolean) tablaVencimientos.getValueAt(row, 2);
                    if (correoReporteId.isEmpty()) {
                        String venc = utils.dbInsert("insert into correoreportesChofer (reporteChoferId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                + " values ('0'," + vencimientosIdChofer.get(row) + "," + global.usuario + "," + false + "," + gerencia + ")");
                        if (venc.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "No fue insertado el registro", "Error", HEIGHT);
                        } else {
                            System.out.println("Fue insertado con exito");
                            System.out.println("insert into correoreportesChofer (reporteChoferId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                    + " values ('0'," + vencimientosIdChofer.get(row) + "," + global.usuario + "," + false + "," + gerencia + ")");
                        }
                    } else {
                        String venc = utils.dbUpdate(" UPDATE correoreportesChofer set  tipoGerencia = " + gerencia + " where reporteChoferId = " + correoReporteId);
                    }
                }
            }
            if (comboTipoVencimientos.getSelectedIndex() == 2) {
                if (col == 1) {
                    // reporteYardaId correoreportesChofer usuarioId vencimientoId
                    String correoReporteId = utils.dbConsult("select reporteYardaId from correoreportesYarda where usuarioId = " + global.usuario + " and "
                            + " vencimientoId = " + vencimientosIdYarda.get(row));
                    boolean admin = (boolean) (tablaVencimientos.getValueAt(row, 1));
                    if (correoReporteId.isEmpty()) {
                        String venc = utils.dbInsert("insert into correoreportesYarda (reporteYardaId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                + " values ('0'," + vencimientosIdYarda.get(row) + "," + global.usuario + "," + admin + "," + false + ")");
                        if (venc.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "No fue insertado el registro", "Error", HEIGHT);
                        } else {
                            System.out.println("Fue insertado con exito");
                            System.out.println("insert into correoreportesYarda (reporteYardaId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                    + " values ('0'," + vencimientosIdYarda.get(row) + "," + global.usuario + "," + admin + "," + false + ")");
                        }
                    } else {
                        utils.dbUpdate(" UPDATE correoreportesYarda set tipoAdmin = " + admin + " where reporteYardaId = " + correoReporteId);
                    }
                }
                if (col == 2) {
                    String correoReporteId = utils.dbConsult("select reporteYardaId from correoreportesYarda where usuarioId = " + global.usuario + " and "
                            + " vencimientoId = " + vencimientosIdYarda.get(row));
                    boolean gerencia = (boolean) tablaVencimientos.getValueAt(row, 2);
                    if (correoReporteId.isEmpty()) {
                        String venc = utils.dbInsert("insert into correoreportesYarda (reporteYardaId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                + " values ('0'," + vencimientosIdYarda.get(row) + "," + global.usuario + "," + false + "," + gerencia + ")");
                        if (venc.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "No fue insertado el registro", "Error", HEIGHT);
                        } else {
                            System.out.println("Fue insertado con exito");
                            System.out.println("insert into correoreportesYarda (reporteYardaId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                    + " values ('0'," + vencimientosIdYarda.get(row) + "," + global.usuario + "," + false + "," + gerencia + ")");
                        }
                    } else {
                        String venc = utils.dbUpdate(" UPDATE correoreportesYarda set  tipoGerencia = " + gerencia + " where reporteYardaId = " + correoReporteId);
                    }
                }
            }
            if (comboTipoVencimientos.getSelectedIndex() == 3) {
                if (col == 1) {
                    // reporteYardaId correoreportesChofer usuarioId vencimientoId
                    String correoReporteId = utils.dbConsult("select reporteExtintorId from correoreportesExtintor where usuarioId = " + global.usuario + " and "
                            + " vencimientoId = " + vencimientosIdExtintor.get(row));
                    boolean admin = (boolean) (tablaVencimientos.getValueAt(row, 1));
                    if (correoReporteId.isEmpty()) {
                        String venc = utils.dbInsert("insert into correoreportesExtintor (reporteExtintorId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                + " values ('0'," + vencimientosIdExtintor.get(row) + "," + global.usuario + "," + admin + "," + false + ")");
                        if (venc.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "No fue insertado el registro", "Error", HEIGHT);
                        } else {
                            System.out.println("Fue insertado con exito");
                            System.out.println("insert into correoreportesExtintor (reporteExtintorId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                    + " values ('0'," + vencimientosIdExtintor.get(row) + "," + global.usuario + "," + admin + "," + false + ")");
                        }
                    } else {
                        utils.dbUpdate(" UPDATE correoreportesExtintor set tipoAdmin = " + admin + " where reporteExtintorId = " + correoReporteId);
                    }
                }
                if (col == 2) {
                    String correoReporteId = utils.dbConsult("select reporteExtintorId from correoreportesExtintor where usuarioId = " + global.usuario + " and "
                            + " vencimientoId = " + vencimientosIdExtintor.get(row));
                    boolean gerencia = (boolean) tablaVencimientos.getValueAt(row, 2);
                    if (correoReporteId.isEmpty()) {
                        String venc = utils.dbInsert("insert into correoreportesExtintor (reporteExtintorId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                + " values ('0'," + vencimientosIdExtintor.get(row) + "," + global.usuario + "," + false + "," + gerencia + ")");
                        if (venc.isEmpty()) {
                            JOptionPane.showMessageDialog(this, "No fue insertado el registro", "Error", HEIGHT);
                        } else {
                            System.out.println("Fue insertado con exito");
                            System.out.println("insert into correoreportesExtintor (reporteExtintorId,vencimientoId,usuarioId,tipoAdmin,tipoGerencia)\n"
                                    + " values ('0'," + vencimientosIdExtintor.get(row) + "," + global.usuario + "," + false + "," + gerencia + ")");
                        }
                    } else {
                        String venc = utils.dbUpdate(" UPDATE correoreportesExtintor set  tipoGerencia = " + gerencia + " where reporteExtintorId = " + correoReporteId);
                    }
                }
            }
        }
    }

    // Esta funcion es para llenar los vencmientos de las unidades en un combo box.
//    void cargarVencimientosUnidad() {
////
////        vencimientosLista.put("Placas MEX", "ExpPlacas");
////        vencimientosLista.put("Segundas Placas", "ExpPlacas2");
////        vencimientosLista.put("Placas USA", "ExpPlacasUSA");
////        vencimientosLista.put("Seguro", "ExpSeguro");
////        vencimientosLista.put("Verificacion Motor", "Verifimotor");
////        vencimientosLista.put("Verificacion Humo", "Verifihumo");
////        vencimientosLista.put("Seguro USA", "ExpSeguroUSA");
////        vencimientosLista.put("USER FEE", "permiso_userfee");
////        vencimientosLista.put("USDOT USER MX MC", "permiso_usdot_usermx");
////        vencimientosLista.put("HAZMAT DOT", "permiso_hazmatdot");
////        vencimientosLista.put("HAZMAT SHP", "user_hazmatshp");
////        vencimientosLista.put("CAAT", "user_caat");
////        vencimientosLista.put("SCAC", "user_scac");
////        vencimientosLista.put("USDOT DISEL", "user_userdot_disel");
//
//        boxUnidadVencimientos.removeAllItems();
//        vencimientoUnidadId.clear();
//
//        boxUnidadVencimientos.addItem("Ver todos");
//        vencimientoUnidadId.add("0");
//        Connection con = null;
//        Statement st = null;
//        ResultSet rs = null;
//
//        String query = "select correosVencimiento.correoReporteId,aliasVencimiento,vencimiento  from correosVencimiento ";
//
//        con = utils.startConnection();
//        try {
//            st = con.createStatement();
//            rs = st.executeQuery(query);
//
//            while (rs.next()) {
//                vencimientoUnidadId.add(rs.getString("correoReporteId"));
//                boxUnidadVencimientos.addItem(rs.getString("aliasVencimiento"));
//                vencimientosLista.put(rs.getString("aliasVencimiento"), rs.getString("vencimiento"));
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, "Error al cargar combo vencimiento unidad: " + e, "Error", JOptionPane.ERROR_MESSAGE);
//        } finally {
//            utils.closeAllConnections(con, st, rs);
//        }
//    }
    private String cargarSumaVencimientos(String vens) {
        String resultadoQuery = utils.dbConsult("select group_concat('SUM(IF(DATEDIFF(',vencimiento,',now()) <= 0, 1,0)) as ',vencimiento separator ',') from correosVencimiento where\n"
                + "correoReporteId  " + (vens.equals("0") ? " > 0 " : " in (" + vens + ") "));

        return resultadoQuery;
    }

    void unidadesIncidentes(String vencimiento) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        int conteoTotal = 0;
        try {
            con = utils.startConnection();
//            if (vencimiento.equals("0")) {
            //Lo que hara es que hara un concatenacion de una query "Suma de permisos venciods de unidades"
            String queryTodos = "select " + cargarSumaVencimientos(vencimiento) + " from "
                    + "camiones_tbl where NoEconomico LIKE '%" + unidadText.getText() + "%' and Status is true";
            st = con.createStatement();
            rs = st.executeQuery(queryTodos);
            int numeroVen = jTable2.getColumnCount() - 1; //Menos uno porque no se cuenta la columna de NoEconomico
            while (rs.next()) {
                for (int i = 1; i <= numeroVen; i++) {
                    conteoTotal = conteoTotal + rs.getInt(i);
                }
            }
            if (conteoTotal > 0) {
                UnidadIncidenteLabel.setBackground(Color.RED);
            } else {
                UnidadIncidenteLabel.setBackground(Color.GREEN);
            }
            UnidadIncidenteLabel.setText(String.valueOf(conteoTotal));

        } catch (SQLException erv) {
            JOptionPane.showMessageDialog(this, "Error al cargar el conteo de los camiones: " + erv, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    void cargarListaVencimientosComboChofer() {
        String query = "SELECT *, concat(vencimiento,' [',dias,' dias]') as venc from vencimientos where deleted is not true and vencimiento != ''  order by ID asc";
        vencimientoIdComboChofer.clear();
        comboVencimientoChofer.removeAllItems();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                vencimientoIdComboChofer.add(rs.getString("ID"));
                comboVencimientoChofer.addItem(rs.getString("venc"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    void cargarListaVencimientosRemolque() {
        String query = "SELECT *, concat(vencimiento,' [',dias,' dias]') as \n"
                + "venc from vencimientos_remolques where deleted is not true and vencimiento != ''  order by ID asc";
        vencimientoRemolqueId.clear();
        boxRemolqueVencimientos.removeAllItems();

        vencimientoRemolqueId.add("0");
        boxRemolqueVencimientos.addItem("Ver todos");
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                vencimientoRemolqueId.add(rs.getString("ID"));
                boxRemolqueVencimientos.addItem(rs.getString("venc"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al cargar combo unidad" + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    void cargarListaVencimientosCamion() {
        String query = "SELECT *, concat(vencimiento,' [',dias,' dias]') as \n"
                + "venc from vencimientos_camiones where deleted is not true and vencimiento != ''  order by ID asc";
        vencimientoUnidadId.clear();
        boxUnidadVencimientos.removeAllItems();

        vencimientoUnidadId.add("0");
        boxUnidadVencimientos.addItem("Ver todos");
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                vencimientoUnidadId.add(rs.getString("ID"));
                boxUnidadVencimientos.addItem(rs.getString("venc"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error al cargar combo unidad" + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    void cargarListaVencimientos() {

        String query = "SELECT *, concat(vencimiento,' [',dias,' dias]') as venc from vencimientos where deleted is not true and vencimiento != ''  order by ID asc";
        vencimientosid.clear();
        boxVencimientosChoferes.removeAllItems();

        vencimientosid.add(0);
        boxVencimientosChoferes.addItem("Ver todos");
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {

                vencimientosid.add(rs.getInt("ID"));
                boxVencimientosChoferes.addItem(rs.getString("venc"));
                System.out.println(rs.getString("venc"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    void loadChofersModel() {

        tablemodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        VID.clear();

        tablemodel.addColumn("Chofer");
        VID.add(0);

        if (boxVencimientosChoferes.getSelectedIndex() == 0) {
            Connection con = null;
            Statement statement = null;
            ResultSet rs = null;
            con = utils.startConnection();

            try {
                statement = con.createStatement();
                rs = statement.executeQuery("SELECT *, concat(vencimiento,' [',dias,' dias]') as venc from vencimientos where deleted is not true and vencimiento != '' order by ID asc");
                while (rs.next()) {
                    tablemodel.addColumn(rs.getString("venc"));
                    VID.add(rs.getInt("ID"));
                }
                con.close();
            } catch (SQLException e) {
                System.out.println("Error " + e);
            } finally {
                utils.closeAllConnections(con, statement, rs);
            }
        }

        if (boxVencimientosChoferes.getSelectedIndex() > 0) {
            tablemodel.addColumn(boxVencimientosChoferes.getSelectedItem().toString());
        }
        jTable1.setModel(tablemodel);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTable1.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));

    }

    private void loadRemolqueModel(String vencimientodIds) {

        boolean estanVencimientosVacios = vencimientodIds.isEmpty();

        if (estanVencimientosVacios) {
            return; //Si vencimientodIds no ejecutes nada
        }

        tablemodelRemolque = inicializarModelo();

        vencimientosRemolque.clear();

        tablemodelRemolque.addColumn("NO. Economico");
        vencimientosRemolque.add("0");

        Connection con = utils.startConnection();
        ResultSet rs = null;
        Statement st = null;

        String todosLosVencimientos = " ID > 0";
        String vencimientosSeleccionados = "ID in (" + vencimientodIds + ") ";

        // Concatena las vencimientos disponibles en forma de columnas
        String query = "SELECT *, concat(vencimiento,' [',ifnull(dias,0),' dias]') as venc \n"
                + "from vencimientos_remolques \n"
                + "where deleted is not true and vencimiento != ''  and  " + (vencimientodIds.equals("0") ? todosLosVencimientos : vencimientosSeleccionados) + " order by ID asc";

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                tablemodelRemolque.addColumn(rs.getString("venc"));
                vencimientosRemolque.add(rs.getString("ID"));

            }

        } catch (SQLException e) {
            System.out.println("Error al cargar modelo de remolque: " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

        establecerModelo(jTable4, tablemodelRemolque);
    }

    private void establecerModelo(JTable jTable, DefaultTableModel tablemodel) {

        jTable.setModel(tablemodel);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTable.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));

    }

    public DefaultTableModel inicializarModelo() {

        DefaultTableModel tablemodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        return tablemodel;
    }

    void loadCamionModel(String vencimientodIds) {
        if (vencimientodIds.isEmpty()) {
            return; //Si vencimientodIds no ejecutes nada
        }
        tablemodel2 = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        vencimientosUnidad.clear();
        tablemodel2.addColumn("NO. Economico");
        vencimientosUnidad.add("0");
        Connection con = utils.startConnection();
        ResultSet rs = null;
        Statement st = null;
        String todosVens = " ID > 0";
        String vens = "ID in (" + vencimientodIds + ") ";
        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT *, concat(vencimiento,' [',dias,' dias]') as venc \n"
                    + "from vencimientos_camiones \n"
                    + "where deleted is not true and vencimiento != ''  and  " + (vencimientodIds.equals("0") ? todosVens : vens) + " order by ID asc");
            while (rs.next()) {
                tablemodel2.addColumn(rs.getString("venc"));
                vencimientosUnidad.add(rs.getString("ID"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
        jTable2.setModel(tablemodel2);
        jTable2.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTable2.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));
    }

    void loadChofersModel(String vencimientodIds) {
        if (vencimientodIds.isEmpty()) {
            return; //Si vencimientodIds no ejecutes nada
        }
        tablemodel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        VID.clear();

        tablemodel.addColumn("Chofer");
        VID.add(0);

//        if (boxTipoOperador1.getSelectedIndex() == 0) {
        Connection con = utils.startConnection();
        ResultSet rs = null;
        Statement st = null;
        String todosVens = " ID > 0";
        String vens = "ID in (" + vencimientodIds + ") ";

        try {
            st = con.createStatement();
            rs = st.executeQuery("SELECT *, concat(vencimiento,' [',dias,' dias]') as venc from vencimientos where deleted is not true and vencimiento != ''  and " + (vencimientodIds.equals("0") ? todosVens : vens) + " order by ID asc");
            while (rs.next()) {
                tablemodel.addColumn(rs.getString("venc"));
                VID.add(rs.getInt("ID"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
//        }
//
//        if (boxTipoOperador1.getSelectedIndex() > 0) {
//            tablemodel.addColumn(boxTipoOperador1.getSelectedItem().toString());
//        }
        jTable1.setModel(tablemodel);
        jTable1.getColumnModel().getColumn(0).setPreferredWidth(200);
        jTable1.getTableHeader().setFont(new Font("Tahoma", Font.PLAIN, 14));

    }

    private void cargarTablaRemolqueDinamica(String vencFiltroId) {

        String procedimiento = "{call cargarRemolqueDinamica(?, ?)}";

        tablemodelRemolque.setRowCount(0);

        Connection con = null;
        CallableStatement callableStatement = null;
        ResultSet rs = null;

        try {

            con = utils.startConnection();
            callableStatement = con.prepareCall(procedimiento);

            callableStatement.setString(1, remolqueText.getText());
            callableStatement.setString(2, vencFiltroId);

            callableStatement.execute();
            rs = callableStatement.getResultSet();

            int noColumnaModelo = tablemodelRemolque.getColumnCount();
            remolqueId.clear();

            while (rs.next()) {

                Object[] obj = new Object[noColumnaModelo];

                for (int i = 1; i <= noColumnaModelo + 1; i++) {

                    if (i != noColumnaModelo + 1) { // 1 != 6

                        obj[i - 1] = rs.getString(i);

                    } else {

                        remolqueId.add(rs.getString(i));

                    }

                }

                tablemodelRemolque.addRow(obj);
            }

            con.close();

        } catch (SQLException e) {
            System.out.println("Error cargar tabla remolques" + e);
        } finally {
            utils.closeAllConnections(con, callableStatement, rs);
        }
    }

    private void cargarTablaUnidadDinamica(String vencFiltroId) {

        String procedimiento = "{call cargarUnidadDinamica(?, ?)}";

        tablemodel2.setRowCount(0);

        Connection con = null;
        CallableStatement callableStatement = null;
        ResultSet rs = null;

        try {

            con = utils.startConnection();
            callableStatement = con.prepareCall(procedimiento);

            callableStatement.setString(1, unidadText.getText());
            callableStatement.setString(2, vencFiltroId);

            callableStatement.execute();
            rs = callableStatement.getResultSet();

            int noColumnaModelo = tablemodel2.getColumnCount();
            camionId.clear();

            while (rs.next()) {

                Object[] obj = new Object[noColumnaModelo];

                for (int i = 1; i <= noColumnaModelo + 1; i++) {

                    if (i != noColumnaModelo + 1) { // 1 != 6

                        obj[i - 1] = rs.getString(i);

                    } else {

                        camionId.add(rs.getString(i));

                    }

                }

                tablemodel2.addRow(obj);
            }

            con.close();

        } catch (SQLException e) {
            System.out.println("Error cargar tabla remolques" + e);
        } finally {
            utils.closeAllConnections(con, callableStatement, rs);
        }
    }

    private void cargarTablaChoferDinamica(String vencFiltroId) {
        String procedimiento = "{call cargarChoferTipoPrueba(?, ?, ?)}";

        prt("{call cargarChoferTipoPrueba('" + jTextField1.getText() + "', " + vencFiltroId + ", " + boxPuesto.getSelectedIndex() + ")}");
        tablemodel.setRowCount(0);
        Connection con = null;
        con = utils.startConnection();
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        try {
            callableStatement = con.prepareCall(procedimiento);
            callableStatement.setString(1, jTextField1.getText());
            callableStatement.setString(2, vencFiltroId);
            callableStatement.setInt(3, boxPuesto.getSelectedIndex());
            callableStatement.execute();
            rs = callableStatement.getResultSet();
            int tam = tablemodel.getColumnCount();
            int ultimaPos = tablemodel.getColumnCount();
            choferId.clear();
            System.out.println(tam);
            while (rs.next()) {
                Object[] obj = new Object[tam];
                for (int i = 1; i <= tam + 1; i++) {
                    if (i != tam + 1) {
                        obj[i - 1] = rs.getString(i);
                    } else {
                        choferId.add(rs.getString(i));
                    }
                }
                tablemodel.addRow(obj);
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, callableStatement, rs);
        }
    }

    private void cargarVencimientoChoferes2() {
        String procedimiento = "{call cargarChoferTipo2(?, ?, ?)}";
        tablemodel.setRowCount(0);
        Connection con = null;
        con = utils.startConnection();
        CallableStatement callableStatement = null;
        ResultSet rs = null;
        try {
            callableStatement = con.prepareCall(procedimiento);
            callableStatement.setString(1, jTextField1.getText());
            callableStatement.setInt(2, vencimientosid.get(boxVencimientosChoferes.getSelectedIndex()));
            System.out.println();
            callableStatement.setInt(3, boxPuesto.getSelectedIndex());
            callableStatement.execute();
            rs = callableStatement.getResultSet();
            int tam = tablemodel.getColumnCount();
            System.out.println(tam);
            while (rs.next()) {
                Object[] obj = new Object[tam];
                for (int i = 1; i <= tam; i++) {
                    obj[i - 1] = rs.getString(i);
                }
                tablemodel.addRow(obj);
            }
            String conteoText = utils.dbConsult("select IFNULL(funcionChofer2Conteo('" + jTextField1.getText() + "'," + boxPuesto.getSelectedIndex() + "," + vencimientosid.get(boxVencimientosChoferes.getSelectedIndex()) + "),0) as conteo");
            if (!conteoText.equals("0")) {
                ChoferIncidenteLabel.setText(conteoText);
                ChoferIncidenteLabel.setBackground(Color.red);
            } else {
                ChoferIncidenteLabel.setText(conteoText);
                ChoferIncidenteLabel.setBackground(Color.green);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, callableStatement, rs);
        }
    }

    public static int calcularDiferencia(String fecha, LocalDate fechaActual) {
        long diferenciaEnDias = 0;
        if (!fecha.isEmpty()) {
            LocalDate fechaIngresada = LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            // Calcular la diferencia en días
            diferenciaEnDias = ChronoUnit.DAYS.between(fechaActual, fechaIngresada);
            // Retornar 1 si la diferencia es menor a 0, de lo contrario, retornar 0
        }
        return (diferenciaEnDias < 0) ? 1 : 0;
    }

    private void cargarVencimientoCamionesDina(String vensId) {
        /*unidad dad*/
        String camion = "";
        if (!unidadText.getText().isEmpty()) {
            camion = " NoEconomico LIKE '%" + unidadText.getText() + "%' and ";
        }

        String vencimiento = cargarUnidadVencimientosDina(vensId);
//        unidadesIncidentes(camion, vencimiento);
        String query = "SELECT  " + vencimiento + "  "
                + "from camiones_tbl where " + camion + " Status = true order by NoEconomico ";
        // + "from camiones_tbl where Status = true and order by NoEconomico ASC ";

        tablemodel2.setRowCount(0);

        Connection con = utils.startConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            int tam = tablemodel2.getColumnCount();
            System.out.println(tam);
            while (rs.next()) {
                Object[] obj = new Object[tam];
                for (int i = 1; i <= tam; i++) {
                    obj[i - 1] = rs.getString(i);
                }
                tablemodel2.addRow(obj);
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error al cargar vencimientos camiones " + e);
        } finally {
            utils.closeAllConnections(con, statement, rs);
        }
    }

    private void cargarVencimientoRemolques() {
        String query = "SELECT NoEconomico,"
                + "DATE_FORMAT(ExpPlacas,'%d.%m.%Y'),"
                + "DATE_FORMAT(ExpPlacasUSA,'%d.%m.%Y') "
                + "from cajas_tbl where Status = true order by CAST(NoEconomico as UNSIGNED)";

        tablemodel3.setRowCount(0);

        Connection con = utils.startConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = con.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {

                tablemodel3.addRow(new Object[]{rs.getString(1), rs.getString(2), rs.getString(3)});
            }

            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        } finally {
            utils.closeAllConnections(con, statement, rs);
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

        dCorreoEnvio = new javax.swing.JDialog();
        jPanel39 = new javax.swing.JPanel();
        jLabel57 = new javax.swing.JLabel();
        correoDiaJefe = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        jScrollPane20 = new javax.swing.JScrollPane();
        txtCorreoJefes = new javax.swing.JTextArea();
        jLabel94 = new javax.swing.JLabel();
        jButton21 = new javax.swing.JButton();
        jScrollPane21 = new javax.swing.JScrollPane();
        txtCorreoAdmin = new javax.swing.JTextArea();
        jLabel95 = new javax.swing.JLabel();
        correoDiaAdmin = new javax.swing.JTextField();
        jLabel58 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        bodyAdmin = new javax.swing.JTextArea();
        jScrollPane5 = new javax.swing.JScrollPane();
        bodyJefe = new javax.swing.JTextArea();
        subjectAdmin = new javax.swing.JTextField();
        subjectJefe = new javax.swing.JTextField();
        checkCorreoAdmin = new javax.swing.JCheckBox();
        checkCorreoJefes = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        ReportesBtn1 = new javax.swing.JButton();
        ReportesBtn = new javax.swing.JButton();
        comboCorreoSaliente = new javax.swing.JComboBox<>();
        jDialog1 = new javax.swing.JDialog();
        jPanel19 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        comboTipoVencimientos = new javax.swing.JComboBox<>();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaVencimientos = new javax.swing.JTable();
        dFiltroChoferes = new javax.swing.JDialog();
        jPanel20 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tablaFiltroChofer = new javax.swing.JTable();
        jPanel21 = new javax.swing.JPanel();
        jPanel22 = new javax.swing.JPanel();
        aceptarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        GenerarReporteDinamicBtn = new javax.swing.JButton();
        dAltaExtintor = new javax.swing.JDialog();
        jPanel42 = new javax.swing.JPanel();
        jPanel46 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        comentarioAltaExtTxt = new javax.swing.JTextArea();
        jPanel47 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        identificadorTxt = new javax.swing.JTextField();
        jPanel48 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        vencimientoTxt = new com.alee.extended.date.WebDateField();
        jPanel49 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        agenteBox = new javax.swing.JComboBox<>();
        jPanel50 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        capacidadTxt = new javax.swing.JTextField();
        comboTipoCapacidad = new javax.swing.JComboBox<>();
        jPanel51 = new javax.swing.JPanel();
        jLabel26 = new javax.swing.JLabel();
        patiosAltaBox = new javax.swing.JComboBox<>();
        guardarExtBtn = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        EditarExt = new javax.swing.JMenuItem();
        EliminarExt = new javax.swing.JMenuItem();
        dBitacoraExtintor = new javax.swing.JDialog();
        jPanel59 = new javax.swing.JPanel();
        jPanel52 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        jPanel53 = new javax.swing.JPanel();
        comboMes = new javax.swing.JComboBox<>();
        comboYear = new javax.swing.JComboBox<>();
        jPanel56 = new javax.swing.JPanel();
        nombreReviso = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        tablaBitacoraExt = new javax.swing.JTable();
        jPanel60 = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        aceptarBtn2 = new javax.swing.JButton();
        cancelarBtn2 = new javax.swing.JButton();
        jPanel62 = new javax.swing.JPanel();
        GenerarReporteBitacora = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        dVencimientosAlmacenes = new javax.swing.JDialog();
        jPanel58 = new javax.swing.JPanel();
        jScrollPane22 = new javax.swing.JScrollPane();
        tvencimientosPorYarda = new javax.swing.JTable();
        jButton95 = new javax.swing.JButton();
        jButton96 = new javax.swing.JButton();
        jButton97 = new javax.swing.JButton();
        jButton98 = new javax.swing.JButton();
        jButton99 = new javax.swing.JButton();
        menuAlmacenPermisos = new javax.swing.JPopupMenu();
        venAyuntamiento = new javax.swing.JMenuItem();
        dVencimientosChofer = new javax.swing.JDialog();
        jPanel63 = new javax.swing.JPanel();
        jScrollPane23 = new javax.swing.JScrollPane();
        tvencimientoschofer = new javax.swing.JTable();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        menuChoferPermisos = new javax.swing.JPopupMenu();
        venChofer = new javax.swing.JMenuItem();
        dAltaVencimientos = new javax.swing.JDialog();
        jPanel64 = new javax.swing.JPanel();
        jScrollPane19 = new javax.swing.JScrollPane();
        tvencimientos = new javax.swing.JTable();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jScrollPane24 = new javax.swing.JScrollPane();
        tvencimientosP = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        tipoPlantilla = new javax.swing.JComboBox();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        dAltaVencimientosUnidad = new javax.swing.JDialog();
        jPanel66 = new javax.swing.JPanel();
        jScrollPane29 = new javax.swing.JScrollPane();
        tvencimientosAltaUnidad = new javax.swing.JTable();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jScrollPane30 = new javax.swing.JScrollPane();
        tvencimientosP2 = new javax.swing.JTable();
        jLabel33 = new javax.swing.JLabel();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        jButton42 = new javax.swing.JButton();
        menuUnidades = new javax.swing.JPopupMenu();
        venUnidades = new javax.swing.JMenuItem();
        dVencimientosUnidad = new javax.swing.JDialog();
        jPanel65 = new javax.swing.JPanel();
        jScrollPane25 = new javax.swing.JScrollPane();
        tvencimientosunidad = new javax.swing.JTable();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        menuRemolques = new javax.swing.JPopupMenu();
        venRemolques = new javax.swing.JMenuItem();
        dFiltroUnidades = new javax.swing.JDialog();
        jPanel24 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tablaFiltroUnidad1 = new javax.swing.JTable();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        aceptarBtn1 = new javax.swing.JButton();
        cancelarBtn1 = new javax.swing.JButton();
        jPanel27 = new javax.swing.JPanel();
        GenerarReporteDinamicBtn1 = new javax.swing.JButton();
        dFiltroRemolques = new javax.swing.JDialog();
        jPanel74 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        tablaFiltroRemolque = new javax.swing.JTable();
        jPanel75 = new javax.swing.JPanel();
        jPanel76 = new javax.swing.JPanel();
        aceptarBtn3 = new javax.swing.JButton();
        cancelarBtn3 = new javax.swing.JButton();
        jPanel77 = new javax.swing.JPanel();
        GenerarReporteDinamicBtn2 = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelChoferes = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        jPanel57 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        boxVencimientosChoferes = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        boxPuesto = new javax.swing.JComboBox();
        jPanel11 = new javax.swing.JPanel();
        ChoferIncidenteLabel = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        panelUnidades = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jPanel12 = new javax.swing.JPanel();
        jPanel67 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        unidadText = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        diasUnidadTxt = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        boxUnidadVencimientos = new javax.swing.JComboBox();
        jButton6 = new javax.swing.JButton();
        UnidadIncidenteLabel = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        panelRemolques = new javax.swing.JPanel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jPanel68 = new javax.swing.JPanel();
        jPanel69 = new javax.swing.JPanel();
        jPanel70 = new javax.swing.JPanel();
        jPanel71 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        remolqueText = new javax.swing.JTextField();
        jPanel72 = new javax.swing.JPanel();
        jLabel30 = new javax.swing.JLabel();
        diasRemolqueTxt = new javax.swing.JTextField();
        jPanel73 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        boxRemolqueVencimientos = new javax.swing.JComboBox();
        jButton12 = new javax.swing.JButton();
        RemolqueIncidenteLabel = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        panelYardas = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tablaAyuntamiento = new javax.swing.JTable();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel54 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        ayuntamientoTxt = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        boxAyuntamiento = new javax.swing.JComboBox();
        jPanel34 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        panelExtintores = new javax.swing.JPanel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTable5 = new javax.swing.JTable();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jPanel45 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel43 = new javax.swing.JPanel();
        jButton11 = new javax.swing.JButton();
        jPanel44 = new javax.swing.JPanel();
        jButton8 = new javax.swing.JButton();
        jPanel55 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        diasExt = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        dCorreoEnvio.setTitle("Notificaciones");
        dCorreoEnvio.setMinimumSize(new java.awt.Dimension(840, 590));
        dCorreoEnvio.setResizable(false);
        dCorreoEnvio.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                dCorreoEnvioWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        jPanel39.setBackground(new java.awt.Color(255, 255, 255));
        jPanel39.setToolTipText("");
        jPanel39.setMinimumSize(new java.awt.Dimension(828, 486));
        jPanel39.setPreferredSize(new java.awt.Dimension(828, 486));
        jPanel39.setLayout(null);

        jLabel57.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(51, 51, 51));
        jLabel57.setText("Dias");
        jPanel39.add(jLabel57);
        jLabel57.setBounds(360, 80, 50, 20);

        correoDiaJefe.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPanel39.add(correoDiaJefe);
        correoDiaJefe.setBounds(360, 100, 50, 30);

        jLabel83.setBackground(new java.awt.Color(102, 102, 102));
        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel83.setForeground(new java.awt.Color(255, 255, 255));
        jLabel83.setText("Notificaciones vencimientos");
        jLabel83.setOpaque(true);
        jPanel39.add(jLabel83);
        jLabel83.setBounds(30, 20, 780, 20);

        txtCorreoJefes.setColumns(20);
        txtCorreoJefes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCorreoJefes.setLineWrap(true);
        txtCorreoJefes.setRows(5);
        txtCorreoJefes.setToolTipText("Para ingresar mas de un correo, separe por ;");
        txtCorreoJefes.setWrapStyleWord(true);
        txtCorreoJefes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoJefesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoJefesKeyTyped(evt);
            }
        });
        jScrollPane20.setViewportView(txtCorreoJefes);

        jPanel39.add(jScrollPane20);
        jScrollPane20.setBounds(30, 100, 310, 90);

        jLabel94.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel94.setForeground(new java.awt.Color(51, 51, 51));
        jLabel94.setText("Correos jefes");
        jPanel39.add(jLabel94);
        jLabel94.setBounds(30, 80, 80, 20);

        jButton21.setText("Guardar Configuracion");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });
        jPanel39.add(jButton21);
        jButton21.setBounds(620, 520, 140, 20);

        txtCorreoAdmin.setColumns(20);
        txtCorreoAdmin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCorreoAdmin.setLineWrap(true);
        txtCorreoAdmin.setRows(5);
        txtCorreoAdmin.setToolTipText("Para ingresar mas de un correo, separe por ;");
        txtCorreoAdmin.setWrapStyleWord(true);
        txtCorreoAdmin.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCorreoAdminKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCorreoAdminKeyTyped(evt);
            }
        });
        jScrollPane21.setViewportView(txtCorreoAdmin);

        jPanel39.add(jScrollPane21);
        jScrollPane21.setBounds(430, 100, 310, 90);

        jLabel95.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(51, 51, 51));
        jLabel95.setText("Correos Administradores");
        jPanel39.add(jLabel95);
        jLabel95.setBounds(430, 80, 140, 20);

        correoDiaAdmin.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        correoDiaAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                correoDiaAdminActionPerformed(evt);
            }
        });
        jPanel39.add(correoDiaAdmin);
        correoDiaAdmin.setBounds(760, 100, 50, 30);

        jLabel58.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(51, 51, 51));
        jLabel58.setText("Dias");
        jPanel39.add(jLabel58);
        jLabel58.setBounds(760, 80, 50, 20);

        bodyAdmin.setColumns(20);
        bodyAdmin.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        bodyAdmin.setRows(5);
        jScrollPane4.setViewportView(bodyAdmin);

        jPanel39.add(jScrollPane4);
        jScrollPane4.setBounds(430, 270, 310, 180);

        bodyJefe.setColumns(20);
        bodyJefe.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        bodyJefe.setRows(5);
        jScrollPane5.setViewportView(bodyJefe);

        jPanel39.add(jScrollPane5);
        jScrollPane5.setBounds(30, 270, 310, 180);

        subjectAdmin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectAdminActionPerformed(evt);
            }
        });
        jPanel39.add(subjectAdmin);
        subjectAdmin.setBounds(430, 220, 310, 24);

        subjectJefe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectJefeActionPerformed(evt);
            }
        });
        jPanel39.add(subjectJefe);
        subjectJefe.setBounds(30, 220, 310, 24);

        checkCorreoAdmin.setText("Enviar Correos");
        jPanel39.add(checkCorreoAdmin);
        checkCorreoAdmin.setBounds(430, 460, 130, 24);

        checkCorreoJefes.setText("Enviar Correos");
        jPanel39.add(checkCorreoJefes);
        checkCorreoJefes.setBounds(30, 460, 130, 24);

        jLabel10.setText("Body");
        jPanel39.add(jLabel10);
        jLabel10.setBounds(30, 250, 80, 16);

        jLabel11.setText("Subject");
        jPanel39.add(jLabel11);
        jLabel11.setBounds(30, 200, 80, 16);

        jLabel12.setText("Body");
        jPanel39.add(jLabel12);
        jLabel12.setBounds(430, 250, 80, 16);

        jLabel13.setText("Subject");
        jPanel39.add(jLabel13);
        jLabel13.setBounds(430, 200, 80, 16);

        ReportesBtn1.setText("Enviar Ahora");
        ReportesBtn1.setPreferredSize(new java.awt.Dimension(95, 25));
        ReportesBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportesBtn1ActionPerformed(evt);
            }
        });
        jPanel39.add(ReportesBtn1);
        ReportesBtn1.setBounds(30, 520, 120, 20);

        ReportesBtn.setText("Reportes");
        ReportesBtn.setPreferredSize(new java.awt.Dimension(95, 25));
        ReportesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportesBtnActionPerformed(evt);
            }
        });
        jPanel39.add(ReportesBtn);
        ReportesBtn.setBounds(490, 520, 120, 20);

        comboCorreoSaliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Correo Saliente" }));
        jPanel39.add(comboCorreoSaliente);
        comboCorreoSaliente.setBounds(30, 50, 240, 26);

        dCorreoEnvio.getContentPane().add(jPanel39, java.awt.BorderLayout.CENTER);

        jDialog1.setBackground(new java.awt.Color(255, 255, 255));
        jDialog1.setMinimumSize(new java.awt.Dimension(650, 350));

        jPanel19.setLayout(new java.awt.BorderLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 30)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Reportes");
        jLabel2.setPreferredSize(new java.awt.Dimension(130, 60));
        jPanel19.add(jLabel2, java.awt.BorderLayout.PAGE_START);

        comboTipoVencimientos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "unidades", "choferes", "yardas", "extintores" }));
        comboTipoVencimientos.setPreferredSize(new java.awt.Dimension(120, 20));
        comboTipoVencimientos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboTipoVencimientosItemStateChanged(evt);
            }
        });
        jPanel19.add(comboTipoVencimientos, java.awt.BorderLayout.EAST);

        jDialog1.getContentPane().add(jPanel19, java.awt.BorderLayout.PAGE_START);

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));
        jPanel17.setLayout(new java.awt.BorderLayout());

        tablaVencimientos.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaVencimientos.setMinimumSize(new java.awt.Dimension(650, 350));
        tablaVencimientos.setPreferredSize(new java.awt.Dimension(650, 350));
        jScrollPane6.setViewportView(tablaVencimientos);

        jPanel17.add(jScrollPane6, java.awt.BorderLayout.CENTER);

        jDialog1.getContentPane().add(jPanel17, java.awt.BorderLayout.CENTER);

        dFiltroChoferes.setBackground(new java.awt.Color(255, 255, 255));
        dFiltroChoferes.setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));
        jPanel20.setLayout(new java.awt.BorderLayout());

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("FILTRO CHOFERES");
        jPanel20.add(jLabel14, java.awt.BorderLayout.PAGE_START);

        tablaFiltroChofer.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tablaFiltroChofer);

        jPanel20.add(jScrollPane7, java.awt.BorderLayout.CENTER);

        jPanel21.setOpaque(false);
        jPanel21.setLayout(new java.awt.BorderLayout());

        jPanel22.setOpaque(false);

        aceptarBtn.setText("Aceptar");
        aceptarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarBtnActionPerformed(evt);
            }
        });
        jPanel22.add(aceptarBtn);

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtnActionPerformed(evt);
            }
        });
        jPanel22.add(cancelarBtn);

        jPanel21.add(jPanel22, java.awt.BorderLayout.WEST);

        jPanel23.setOpaque(false);

        GenerarReporteDinamicBtn.setText("Generar Reporte Dinamico");
        GenerarReporteDinamicBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarReporteDinamicBtnActionPerformed(evt);
            }
        });
        jPanel23.add(GenerarReporteDinamicBtn);

        jPanel21.add(jPanel23, java.awt.BorderLayout.EAST);

        jPanel20.add(jPanel21, java.awt.BorderLayout.PAGE_END);

        dFiltroChoferes.getContentPane().add(jPanel20, java.awt.BorderLayout.CENTER);

        dAltaExtintor.setMinimumSize(new java.awt.Dimension(685, 400));

        jPanel42.setBackground(new java.awt.Color(255, 255, 255));
        jPanel42.setLayout(null);

        jPanel46.setBackground(new java.awt.Color(255, 255, 255));
        jPanel46.setPreferredSize(new java.awt.Dimension(150, 100));
        jPanel46.setLayout(null);

        jLabel19.setText("Comentarios");
        jLabel19.setPreferredSize(new java.awt.Dimension(50, 100));
        jPanel46.add(jLabel19);
        jLabel19.setBounds(0, 0, 650, 20);

        comentarioAltaExtTxt.setColumns(20);
        comentarioAltaExtTxt.setRows(5);
        comentarioAltaExtTxt.setPreferredSize(new java.awt.Dimension(164, 100));
        jScrollPane11.setViewportView(comentarioAltaExtTxt);

        jPanel46.add(jScrollPane11);
        jScrollPane11.setBounds(0, 30, 650, 83);

        jPanel42.add(jPanel46);
        jPanel46.setBounds(10, 180, 650, 140);

        jPanel47.setBackground(new java.awt.Color(255, 255, 255));
        jPanel47.setPreferredSize(new java.awt.Dimension(150, 100));

        jLabel21.setText("Identificador");
        jPanel47.add(jLabel21);

        identificadorTxt.setMinimumSize(new java.awt.Dimension(200, 20));
        identificadorTxt.setPreferredSize(new java.awt.Dimension(200, 20));
        identificadorTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                identificadorTxtActionPerformed(evt);
            }
        });
        jPanel47.add(identificadorTxt);

        jPanel42.add(jPanel47);
        jPanel47.setBounds(0, 50, 300, 30);

        jPanel48.setBackground(new java.awt.Color(255, 255, 255));
        jPanel48.setPreferredSize(new java.awt.Dimension(150, 100));

        jLabel22.setText("Fecha vencimiento");
        jPanel48.add(jLabel22);

        vencimientoTxt.setMinimumSize(new java.awt.Dimension(100, 26));
        vencimientoTxt.setPreferredSize(new java.awt.Dimension(100, 26));
        jPanel48.add(vencimientoTxt);

        jPanel42.add(jPanel48);
        jPanel48.setBounds(310, 50, 220, 30);

        jPanel49.setBackground(new java.awt.Color(255, 255, 255));
        jPanel49.setPreferredSize(new java.awt.Dimension(150, 100));

        jLabel24.setText("Agente");
        jPanel49.add(jLabel24);

        agenteBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        agenteBox.setMinimumSize(new java.awt.Dimension(200, 20));
        agenteBox.setPreferredSize(new java.awt.Dimension(70, 20));
        jPanel49.add(agenteBox);

        jPanel42.add(jPanel49);
        jPanel49.setBounds(0, 150, 170, 30);

        jPanel50.setBackground(new java.awt.Color(255, 255, 255));
        jPanel50.setPreferredSize(new java.awt.Dimension(150, 100));

        jLabel25.setText("Capacidad");
        jPanel50.add(jLabel25);

        capacidadTxt.setMinimumSize(new java.awt.Dimension(70, 20));
        capacidadTxt.setPreferredSize(new java.awt.Dimension(70, 20));
        capacidadTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                capacidadTxtActionPerformed(evt);
            }
        });
        jPanel50.add(capacidadTxt);

        comboTipoCapacidad.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "kg", "lb" }));
        comboTipoCapacidad.setMinimumSize(new java.awt.Dimension(200, 20));
        comboTipoCapacidad.setPreferredSize(new java.awt.Dimension(50, 20));
        jPanel50.add(comboTipoCapacidad);

        jPanel42.add(jPanel50);
        jPanel50.setBounds(310, 100, 200, 30);

        jPanel51.setBackground(new java.awt.Color(255, 255, 255));
        jPanel51.setPreferredSize(new java.awt.Dimension(150, 100));

        jLabel26.setText("Ubicacion");
        jPanel51.add(jLabel26);

        patiosAltaBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        patiosAltaBox.setMinimumSize(new java.awt.Dimension(200, 20));
        patiosAltaBox.setPreferredSize(new java.awt.Dimension(200, 20));
        jPanel51.add(patiosAltaBox);

        jPanel42.add(jPanel51);
        jPanel51.setBounds(0, 100, 290, 30);

        guardarExtBtn.setText("Guardar");
        guardarExtBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarExtBtnActionPerformed(evt);
            }
        });
        jPanel42.add(guardarExtBtn);
        guardarExtBtn.setBounds(550, 320, 100, 32);

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setText("ALTA EXTINTORES");
        jPanel42.add(jLabel27);
        jLabel27.setBounds(0, 0, 660, 40);

        dAltaExtintor.getContentPane().add(jPanel42, java.awt.BorderLayout.CENTER);

        EditarExt.setText("Editar Extintor");
        EditarExt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarExtActionPerformed(evt);
            }
        });
        jPopupMenu1.add(EditarExt);

        EliminarExt.setText("Eliminar");
        EliminarExt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarExtActionPerformed(evt);
            }
        });
        jPopupMenu1.add(EliminarExt);

        dBitacoraExtintor.setBackground(new java.awt.Color(255, 255, 255));
        dBitacoraExtintor.setMinimumSize(new java.awt.Dimension(720, 320));

        jPanel59.setBackground(new java.awt.Color(255, 255, 255));
        jPanel59.setLayout(new java.awt.BorderLayout());

        jPanel52.setBackground(new java.awt.Color(255, 255, 255));
        jPanel52.setLayout(new java.awt.BorderLayout());

        jLabel34.setBackground(new java.awt.Color(255, 255, 255));
        jLabel34.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("BITACORA MENSUAL");
        jPanel52.add(jLabel34, java.awt.BorderLayout.PAGE_START);

        jPanel53.setBackground(new java.awt.Color(255, 255, 255));
        jPanel53.setPreferredSize(new java.awt.Dimension(210, 30));

        comboMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboMes.setMinimumSize(new java.awt.Dimension(100, 20));
        comboMes.setPreferredSize(new java.awt.Dimension(100, 20));
        comboMes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboMesItemStateChanged(evt);
            }
        });
        jPanel53.add(comboMes);

        comboYear.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboYear.setPreferredSize(new java.awt.Dimension(100, 20));
        comboYear.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboYearItemStateChanged(evt);
            }
        });
        jPanel53.add(comboYear);

        jPanel52.add(jPanel53, java.awt.BorderLayout.WEST);

        jPanel56.setBackground(new java.awt.Color(255, 255, 255));
        jPanel56.setLayout(new java.awt.BorderLayout());

        nombreReviso.setPreferredSize(new java.awt.Dimension(200, 15));
        nombreReviso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nombreRevisoActionPerformed(evt);
            }
        });
        jPanel56.add(nombreReviso, java.awt.BorderLayout.EAST);

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel28.setText("Nombre de quien reviso:  ");
        jPanel56.add(jLabel28, java.awt.BorderLayout.CENTER);

        jPanel52.add(jPanel56, java.awt.BorderLayout.CENTER);

        jPanel59.add(jPanel52, java.awt.BorderLayout.NORTH);

        jScrollPane13.setBackground(new java.awt.Color(255, 255, 255));

        tablaBitacoraExt.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane13.setViewportView(tablaBitacoraExt);

        jPanel59.add(jScrollPane13, java.awt.BorderLayout.CENTER);

        jPanel60.setOpaque(false);
        jPanel60.setLayout(new java.awt.BorderLayout());

        jPanel61.setOpaque(false);

        aceptarBtn2.setText("Guardar");
        aceptarBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarBtn2ActionPerformed(evt);
            }
        });
        jPanel61.add(aceptarBtn2);

        cancelarBtn2.setText("Cerrar");
        cancelarBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtn2ActionPerformed(evt);
            }
        });
        jPanel61.add(cancelarBtn2);

        jPanel60.add(jPanel61, java.awt.BorderLayout.WEST);

        jPanel62.setOpaque(false);

        GenerarReporteBitacora.setText("Generar Reporte Bitacora");
        GenerarReporteBitacora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarReporteBitacoraActionPerformed(evt);
            }
        });
        jPanel62.add(GenerarReporteBitacora);

        jPanel60.add(jPanel62, java.awt.BorderLayout.EAST);

        jPanel59.add(jPanel60, java.awt.BorderLayout.PAGE_END);

        dBitacoraExtintor.getContentPane().add(jPanel59, java.awt.BorderLayout.CENTER);

        jTextField3.setText("jTextField3");

        dVencimientosAlmacenes.setTitle("Listado de Vencimientos del Chofer");
        dVencimientosAlmacenes.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dVencimientosAlmacenes.setMinimumSize(new java.awt.Dimension(570, 510));

        jPanel58.setBackground(new java.awt.Color(255, 255, 255));
        jPanel58.setLayout(null);

        tvencimientosPorYarda.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane22.setViewportView(tvencimientosPorYarda);

        jPanel58.add(jScrollPane22);
        jScrollPane22.setBounds(20, 20, 453, 403);

        jButton95.setText("Doc");
        jButton95.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton95ActionPerformed(evt);
            }
        });
        jPanel58.add(jButton95);
        jButton95.setBounds(480, 100, 60, 30);

        jButton96.setText("+");
        jButton96.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton96ActionPerformed(evt);
            }
        });
        jPanel58.add(jButton96);
        jButton96.setBounds(480, 20, 60, 30);

        jButton97.setText("-");
        jButton97.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton97ActionPerformed(evt);
            }
        });
        jPanel58.add(jButton97);
        jButton97.setBounds(480, 60, 60, 30);

        jButton98.setText("Ver");
        jButton98.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton98ActionPerformed(evt);
            }
        });
        jPanel58.add(jButton98);
        jButton98.setBounds(480, 140, 60, 30);

        jButton99.setText("Generar plantilla base");
        jButton99.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton99ActionPerformed(evt);
            }
        });
        jPanel58.add(jButton99);
        jButton99.setBounds(20, 430, 450, 30);

        dVencimientosAlmacenes.getContentPane().add(jPanel58, java.awt.BorderLayout.CENTER);

        venAyuntamiento.setText("vencimientos ayuntamiento");
        venAyuntamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venAyuntamientoActionPerformed(evt);
            }
        });
        menuAlmacenPermisos.add(venAyuntamiento);

        dVencimientosChofer.setTitle("Listado de Vencimientos del Chofer");
        dVencimientosChofer.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dVencimientosChofer.setMinimumSize(new java.awt.Dimension(570, 510));

        jPanel63.setBackground(new java.awt.Color(255, 255, 255));
        jPanel63.setLayout(null);

        tvencimientoschofer.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane23.setViewportView(tvencimientoschofer);

        jPanel63.add(jScrollPane23);
        jScrollPane23.setBounds(20, 20, 453, 403);

        jButton24.setText("Doc");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });
        jPanel63.add(jButton24);
        jButton24.setBounds(480, 100, 60, 30);

        jButton25.setText("+");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel63.add(jButton25);
        jButton25.setBounds(480, 20, 60, 30);

        jButton26.setText("-");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });
        jPanel63.add(jButton26);
        jButton26.setBounds(480, 60, 60, 30);

        jButton27.setText("Ver");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });
        jPanel63.add(jButton27);
        jButton27.setBounds(480, 140, 60, 30);

        jButton30.setText("Generar plantilla base");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });
        jPanel63.add(jButton30);
        jButton30.setBounds(20, 430, 450, 30);

        dVencimientosChofer.getContentPane().add(jPanel63, java.awt.BorderLayout.CENTER);

        venChofer.setText("vencimientos chofer");
        venChofer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venChoferActionPerformed(evt);
            }
        });
        menuChoferPermisos.add(venChofer);

        dAltaVencimientos.setTitle("Alta De Vencimientos");
        dAltaVencimientos.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dAltaVencimientos.setMinimumSize(new java.awt.Dimension(1042, 540));

        jPanel64.setBackground(new java.awt.Color(255, 255, 255));
        jPanel64.setLayout(null);

        tvencimientos.setModel(new javax.swing.table.DefaultTableModel(
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
        tvencimientos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tvencimientosMouseClicked(evt);
            }
        });
        tvencimientos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tvencimientosKeyPressed(evt);
            }
        });
        jScrollPane19.setViewportView(tvencimientos);

        jPanel64.add(jScrollPane19);
        jScrollPane19.setBounds(20, 50, 453, 400);

        jButton22.setText("-");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });
        jPanel64.add(jButton22);
        jButton22.setBounds(410, 10, 50, 30);

        jButton23.setText("+");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });
        jPanel64.add(jButton23);
        jButton23.setBounds(360, 10, 50, 30);

        tvencimientosP.setModel(new javax.swing.table.DefaultTableModel(
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
        tvencimientosP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tvencimientosPMouseClicked(evt);
            }
        });
        jScrollPane24.setViewportView(tvencimientosP);

        jPanel64.add(jScrollPane24);
        jScrollPane24.setBounds(510, 50, 453, 400);

        jLabel32.setText("Plantilla de Carga");
        jPanel64.add(jLabel32);
        jLabel32.setBounds(510, 20, 180, 30);

        tipoPlantilla.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        tipoPlantilla.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoPlantillaItemStateChanged(evt);
            }
        });
        jPanel64.add(tipoPlantilla);
        tipoPlantilla.setBounds(730, 10, 230, 30);

        jButton28.setText("+");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });
        jPanel64.add(jButton28);
        jButton28.setBounds(970, 60, 50, 30);

        jButton29.setText("-");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });
        jPanel64.add(jButton29);
        jButton29.setBounds(970, 100, 50, 30);

        jButton31.setText("Asignar vencimientos a todos los operadores de este tipo");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });
        jPanel64.add(jButton31);
        jButton31.setBounds(30, 470, 920, 30);

        dAltaVencimientos.getContentPane().add(jPanel64, java.awt.BorderLayout.CENTER);

        dAltaVencimientosUnidad.setTitle("Alta De Vencimientos");
        dAltaVencimientosUnidad.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dAltaVencimientosUnidad.setMinimumSize(new java.awt.Dimension(1042, 540));

        jPanel66.setBackground(new java.awt.Color(255, 255, 255));
        jPanel66.setLayout(null);

        tvencimientosAltaUnidad.setModel(new javax.swing.table.DefaultTableModel(
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
        tvencimientosAltaUnidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tvencimientosAltaUnidadMouseClicked(evt);
            }
        });
        tvencimientosAltaUnidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tvencimientosAltaUnidadKeyPressed(evt);
            }
        });
        jScrollPane29.setViewportView(tvencimientosAltaUnidad);

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

        tvencimientosP2.setModel(new javax.swing.table.DefaultTableModel(
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
        tvencimientosP2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tvencimientosP2MouseClicked(evt);
            }
        });
        jScrollPane30.setViewportView(tvencimientosP2);

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

        dAltaVencimientosUnidad.getContentPane().add(jPanel66, java.awt.BorderLayout.CENTER);

        venUnidades.setText("vencimientos unidad");
        venUnidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venUnidadesActionPerformed(evt);
            }
        });
        menuUnidades.add(venUnidades);

        dVencimientosUnidad.setTitle("Listado de Vencimientos de las Unidades");
        dVencimientosUnidad.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dVencimientosUnidad.setMinimumSize(new java.awt.Dimension(570, 510));

        jPanel65.setBackground(new java.awt.Color(255, 255, 255));
        jPanel65.setLayout(null);

        tvencimientosunidad.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane25.setViewportView(tvencimientosunidad);

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

        dVencimientosUnidad.getContentPane().add(jPanel65, java.awt.BorderLayout.CENTER);

        venRemolques.setText("vencimientos unidad");
        venRemolques.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venRemolquesActionPerformed(evt);
            }
        });
        menuRemolques.add(venRemolques);

        dFiltroUnidades.setBackground(new java.awt.Color(255, 255, 255));
        dFiltroUnidades.setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));
        jPanel24.setLayout(new java.awt.BorderLayout());

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel24.add(jLabel15, java.awt.BorderLayout.PAGE_START);

        tablaFiltroUnidad1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane8.setViewportView(tablaFiltroUnidad1);

        jPanel24.add(jScrollPane8, java.awt.BorderLayout.CENTER);

        jPanel25.setOpaque(false);
        jPanel25.setLayout(new java.awt.BorderLayout());

        jPanel26.setOpaque(false);

        aceptarBtn1.setText("Aceptar");
        aceptarBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarBtn1ActionPerformed(evt);
            }
        });
        jPanel26.add(aceptarBtn1);

        cancelarBtn1.setText("Cancelar");
        cancelarBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtn1ActionPerformed(evt);
            }
        });
        jPanel26.add(cancelarBtn1);

        jPanel25.add(jPanel26, java.awt.BorderLayout.WEST);

        jPanel27.setOpaque(false);

        GenerarReporteDinamicBtn1.setText("Generar Reporte Dinamico");
        GenerarReporteDinamicBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarReporteDinamicBtn1ActionPerformed(evt);
            }
        });
        jPanel27.add(GenerarReporteDinamicBtn1);

        jPanel25.add(jPanel27, java.awt.BorderLayout.EAST);

        jPanel24.add(jPanel25, java.awt.BorderLayout.PAGE_END);

        dFiltroUnidades.getContentPane().add(jPanel24, java.awt.BorderLayout.CENTER);

        dFiltroRemolques.setBackground(new java.awt.Color(255, 255, 255));
        dFiltroRemolques.setMinimumSize(new java.awt.Dimension(400, 300));

        jPanel74.setBackground(new java.awt.Color(255, 255, 255));
        jPanel74.setLayout(new java.awt.BorderLayout());

        jLabel35.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel74.add(jLabel35, java.awt.BorderLayout.PAGE_START);

        tablaFiltroRemolque.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane14.setViewportView(tablaFiltroRemolque);

        jPanel74.add(jScrollPane14, java.awt.BorderLayout.CENTER);

        jPanel75.setOpaque(false);
        jPanel75.setLayout(new java.awt.BorderLayout());

        jPanel76.setOpaque(false);

        aceptarBtn3.setText("Aceptar");
        aceptarBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aceptarBtn3ActionPerformed(evt);
            }
        });
        jPanel76.add(aceptarBtn3);

        cancelarBtn3.setText("Cancelar");
        cancelarBtn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarBtn3ActionPerformed(evt);
            }
        });
        jPanel76.add(cancelarBtn3);

        jPanel75.add(jPanel76, java.awt.BorderLayout.WEST);

        jPanel77.setOpaque(false);

        GenerarReporteDinamicBtn2.setText("Generar Reporte Dinamico");
        GenerarReporteDinamicBtn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarReporteDinamicBtn2ActionPerformed(evt);
            }
        });
        jPanel77.add(GenerarReporteDinamicBtn2);

        jPanel75.add(jPanel77, java.awt.BorderLayout.EAST);

        jPanel74.add(jPanel75, java.awt.BorderLayout.PAGE_END);

        dFiltroRemolques.getContentPane().add(jPanel74, java.awt.BorderLayout.CENTER);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Vencimientos");
        setMinimumSize(new java.awt.Dimension(1600, 900));

        panelChoferes.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setPreferredSize(new java.awt.Dimension(1100, 403));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable1.setFillsViewportHeight(true);
        jTable1.setRowHeight(25);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        panelChoferes.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel6.setLayout(new java.awt.BorderLayout());

        jPanel18.setPreferredSize(new java.awt.Dimension(1371, 60));
        jPanel18.setLayout(new java.awt.BorderLayout());

        jPanel8.setPreferredSize(new java.awt.Dimension(390, 40));

        jPanel7.setPreferredSize(new java.awt.Dimension(280, 35));

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Chofer");
        jLabel3.setPreferredSize(new java.awt.Dimension(55, 14));
        jPanel7.add(jLabel3);

        jTextField1.setPreferredSize(new java.awt.Dimension(200, 25));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField1KeyPressed(evt);
            }
        });
        jPanel7.add(jTextField1);

        jPanel8.add(jPanel7);
        jPanel7.getAccessibleContext().setAccessibleName("");

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Dias");
        jPanel8.add(jLabel5);

        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField2.setText("1");
        jTextField2.setPreferredSize(new java.awt.Dimension(50, 30));
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField2KeyPressed(evt);
            }
        });
        jPanel8.add(jTextField2);

        jPanel57.add(jPanel8);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Vencimiento");
        jPanel9.add(jLabel4);

        boxVencimientosChoferes.setPreferredSize(new java.awt.Dimension(200, 25));
        boxVencimientosChoferes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxVencimientosChoferesItemStateChanged(evt);
            }
        });
        boxVencimientosChoferes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxVencimientosChoferesActionPerformed(evt);
            }
        });
        jPanel9.add(boxVencimientosChoferes);

        jButton5.setText("+");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton5);

        jPanel57.add(jPanel9);

        jLabel6.setText("Tipo de puesto");
        jPanel10.add(jLabel6);

        boxPuesto.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        boxPuesto.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ver todos", "Operador", "Taller", "General", "Administracion", "Guardia" }));
        boxPuesto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxPuestoItemStateChanged(evt);
            }
        });
        boxPuesto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxPuestoActionPerformed(evt);
            }
        });
        jPanel10.add(boxPuesto);

        jPanel57.add(jPanel10);

        ChoferIncidenteLabel.setBackground(new java.awt.Color(204, 204, 204));
        ChoferIncidenteLabel.setOpaque(true);
        ChoferIncidenteLabel.setPreferredSize(new java.awt.Dimension(45, 30));
        jPanel11.add(ChoferIncidenteLabel);

        jButton3.setText("Correos");
        jButton3.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton3);

        jButton1.setText("Generar documento de vencimientos");
        jButton1.setPreferredSize(new java.awt.Dimension(207, 30));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton1);

        jButton32.setText("Vencimientos");
        jButton32.setMaximumSize(new java.awt.Dimension(207, 30));
        jButton32.setMinimumSize(new java.awt.Dimension(30, 23));
        jButton32.setPreferredSize(new java.awt.Dimension(150, 30));
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });
        jPanel11.add(jButton32);

        jPanel57.add(jPanel11);

        jPanel18.add(jPanel57, java.awt.BorderLayout.WEST);

        jPanel6.add(jPanel18, java.awt.BorderLayout.CENTER);

        panelChoferes.add(jPanel6, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.addTab("Choferes", panelChoferes);

        panelUnidades.setLayout(new java.awt.BorderLayout());

        jTable2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable2.setFillsViewportHeight(true);
        jTable2.setRowHeight(25);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);

        panelUnidades.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jPanel12.setPreferredSize(new java.awt.Dimension(1677, 60));
        jPanel12.setLayout(new java.awt.BorderLayout());

        jPanel4.setPreferredSize(new java.awt.Dimension(290, 35));

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Unidad");
        jLabel7.setPreferredSize(new java.awt.Dimension(50, 14));
        jPanel4.add(jLabel7);

        unidadText.setPreferredSize(new java.awt.Dimension(200, 25));
        unidadText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unidadTextKeyPressed(evt);
            }
        });
        jPanel4.add(unidadText);

        jPanel15.add(jPanel4);
        jPanel4.getAccessibleContext().setAccessibleName("");

        jPanel14.setPreferredSize(new java.awt.Dimension(100, 40));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Dias");
        jLabel9.setPreferredSize(new java.awt.Dimension(30, 25));
        jPanel14.add(jLabel9);

        diasUnidadTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        diasUnidadTxt.setText("1");
        diasUnidadTxt.setPreferredSize(new java.awt.Dimension(50, 30));
        diasUnidadTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diasUnidadTxtKeyPressed(evt);
            }
        });
        jPanel14.add(diasUnidadTxt);

        jPanel15.add(jPanel14);

        jPanel13.setPreferredSize(new java.awt.Dimension(350, 35));

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Vencimiento");
        jPanel13.add(jLabel8);

        boxUnidadVencimientos.setPreferredSize(new java.awt.Dimension(200, 25));
        boxUnidadVencimientos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxUnidadVencimientosItemStateChanged(evt);
            }
        });
        boxUnidadVencimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxUnidadVencimientosActionPerformed(evt);
            }
        });
        jPanel13.add(boxUnidadVencimientos);

        jButton6.setText("+");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel13.add(jButton6);

        jPanel15.add(jPanel13);

        UnidadIncidenteLabel.setBackground(new java.awt.Color(204, 204, 204));
        UnidadIncidenteLabel.setOpaque(true);
        UnidadIncidenteLabel.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel15.add(UnidadIncidenteLabel);

        jButton4.setText("Correos");
        jButton4.setPreferredSize(new java.awt.Dimension(100, 30));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton4);

        jButton2.setText("Documento de vencimientos");
        jButton2.setPreferredSize(new java.awt.Dimension(207, 30));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton2);

        jButton33.setText("Vencimientos");
        jButton33.setPreferredSize(new java.awt.Dimension(150, 30));
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });
        jPanel15.add(jButton33);

        jPanel67.add(jPanel15);

        jPanel12.add(jPanel67, java.awt.BorderLayout.WEST);

        panelUnidades.add(jPanel12, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.addTab("Unidades", panelUnidades);

        panelRemolques.setLayout(new java.awt.BorderLayout());

        jTable4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable4.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable4.setFillsViewportHeight(true);
        jTable4.setRowHeight(25);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable4);

        panelRemolques.add(jScrollPane12, java.awt.BorderLayout.CENTER);

        jPanel68.setPreferredSize(new java.awt.Dimension(1677, 60));
        jPanel68.setLayout(new java.awt.BorderLayout());

        jPanel71.setMinimumSize(new java.awt.Dimension(300, 32));
        jPanel71.setPreferredSize(new java.awt.Dimension(300, 35));

        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel29.setText("Remolques");
        jLabel29.setPreferredSize(new java.awt.Dimension(90, 14));
        jPanel71.add(jLabel29);

        remolqueText.setPreferredSize(new java.awt.Dimension(200, 25));
        remolqueText.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                remolqueTextKeyPressed(evt);
            }
        });
        jPanel71.add(remolqueText);

        jPanel70.add(jPanel71);

        jPanel72.setPreferredSize(new java.awt.Dimension(100, 40));

        jLabel30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel30.setText("Dias");
        jLabel30.setPreferredSize(new java.awt.Dimension(30, 25));
        jPanel72.add(jLabel30);

        diasRemolqueTxt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        diasRemolqueTxt.setText("1");
        diasRemolqueTxt.setPreferredSize(new java.awt.Dimension(50, 30));
        diasRemolqueTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diasRemolqueTxtKeyPressed(evt);
            }
        });
        jPanel72.add(diasRemolqueTxt);

        jPanel70.add(jPanel72);

        jPanel73.setPreferredSize(new java.awt.Dimension(350, 35));

        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Vencimiento");
        jPanel73.add(jLabel31);

        boxRemolqueVencimientos.setPreferredSize(new java.awt.Dimension(200, 25));
        boxRemolqueVencimientos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxRemolqueVencimientosItemStateChanged(evt);
            }
        });
        boxRemolqueVencimientos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxRemolqueVencimientosActionPerformed(evt);
            }
        });
        jPanel73.add(boxRemolqueVencimientos);

        jButton12.setText("+");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });
        jPanel73.add(jButton12);

        jPanel70.add(jPanel73);

        RemolqueIncidenteLabel.setBackground(new java.awt.Color(204, 204, 204));
        RemolqueIncidenteLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RemolqueIncidenteLabel.setOpaque(true);
        RemolqueIncidenteLabel.setPreferredSize(new java.awt.Dimension(50, 30));
        jPanel70.add(RemolqueIncidenteLabel);

        jButton14.setText("Documento de vencimientos");
        jButton14.setPreferredSize(new java.awt.Dimension(207, 30));
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jPanel70.add(jButton14);

        jButton44.setText("Vencimientos");
        jButton44.setPreferredSize(new java.awt.Dimension(115, 30));
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        jPanel70.add(jButton44);

        jPanel69.add(jPanel70);

        jPanel68.add(jPanel69, java.awt.BorderLayout.WEST);

        panelRemolques.add(jPanel68, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.addTab("Remolques", panelRemolques);

        panelYardas.setLayout(new java.awt.BorderLayout());

        jScrollPane9.setPreferredSize(new java.awt.Dimension(1100, 403));

        tablaAyuntamiento.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaAyuntamiento.setFillsViewportHeight(true);
        tablaAyuntamiento.setRowHeight(25);
        tablaAyuntamiento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaAyuntamientoMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(tablaAyuntamiento);

        panelYardas.add(jScrollPane9, java.awt.BorderLayout.CENTER);

        jPanel29.setLayout(new java.awt.BorderLayout());

        jPanel30.setPreferredSize(new java.awt.Dimension(1371, 60));
        jPanel30.setLayout(new java.awt.BorderLayout());

        jPanel31.setPreferredSize(new java.awt.Dimension(300, 40));

        jPanel32.setPreferredSize(new java.awt.Dimension(280, 35));

        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Filtro ");
        jLabel16.setPreferredSize(new java.awt.Dimension(55, 14));
        jPanel32.add(jLabel16);

        ayuntamientoTxt.setPreferredSize(new java.awt.Dimension(200, 25));
        ayuntamientoTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ayuntamientoTxtActionPerformed(evt);
            }
        });
        ayuntamientoTxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                ayuntamientoTxtKeyPressed(evt);
            }
        });
        jPanel32.add(ayuntamientoTxt);

        jPanel31.add(jPanel32);

        jPanel54.add(jPanel31);

        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Vencimiento");
        jPanel33.add(jLabel18);

        boxAyuntamiento.setPreferredSize(new java.awt.Dimension(200, 25));
        boxAyuntamiento.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                boxAyuntamientoItemStateChanged(evt);
            }
        });
        boxAyuntamiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxAyuntamientoActionPerformed(evt);
            }
        });
        jPanel33.add(boxAyuntamiento);

        jPanel54.add(jPanel33);
        jPanel54.add(jPanel34);

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Dias");
        jPanel35.add(jLabel17);

        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField4.setText("1");
        jTextField4.setPreferredSize(new java.awt.Dimension(50, 30));
        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField4KeyPressed(evt);
            }
        });
        jPanel35.add(jTextField4);

        jButton9.setText("Documento de vencimientos");
        jButton9.setPreferredSize(new java.awt.Dimension(270, 30));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jPanel35.add(jButton9);

        jButton7.setText("Altas yardas");
        jButton7.setPreferredSize(new java.awt.Dimension(103, 30));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jPanel35.add(jButton7);

        jButton13.setText("Vencimientos");
        jButton13.setPreferredSize(new java.awt.Dimension(130, 30));
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });
        jPanel35.add(jButton13);

        jPanel54.add(jPanel35);

        jPanel30.add(jPanel54, java.awt.BorderLayout.WEST);

        jPanel29.add(jPanel30, java.awt.BorderLayout.WEST);

        panelYardas.add(jPanel29, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.addTab("Yardas", panelYardas);

        panelExtintores.setLayout(new java.awt.BorderLayout());

        jScrollPane10.setPreferredSize(new java.awt.Dimension(1100, 403));

        jTable5.setModel(new javax.swing.table.DefaultTableModel(
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
        jTable5.setFillsViewportHeight(true);
        jTable5.setRowHeight(25);
        jTable5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable5MouseClicked(evt);
            }
        });
        jScrollPane10.setViewportView(jTable5);

        panelExtintores.add(jScrollPane10, java.awt.BorderLayout.CENTER);

        jPanel37.setLayout(new java.awt.BorderLayout());

        jPanel38.setPreferredSize(new java.awt.Dimension(1371, 60));
        jPanel38.setLayout(new java.awt.BorderLayout());

        jPanel40.setPreferredSize(new java.awt.Dimension(300, 40));

        jPanel41.setPreferredSize(new java.awt.Dimension(280, 35));

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("Extintor");
        jLabel20.setPreferredSize(new java.awt.Dimension(55, 14));
        jPanel41.add(jLabel20);

        jTextField5.setPreferredSize(new java.awt.Dimension(200, 25));
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextField5KeyPressed(evt);
            }
        });
        jPanel41.add(jTextField5);

        jPanel40.add(jPanel41);

        jPanel45.add(jPanel40);

        jButton11.setText("Capturar Reporte Mensual");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jPanel43.add(jButton11);

        jPanel45.add(jPanel43);

        jButton8.setText("Agregar Extintor");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jPanel44.add(jButton8);

        jPanel45.add(jPanel44);

        jLabel23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel23.setText("Dias");
        jPanel55.add(jLabel23);

        diasExt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        diasExt.setText("1");
        diasExt.setPreferredSize(new java.awt.Dimension(50, 30));
        diasExt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                diasExtKeyPressed(evt);
            }
        });
        jPanel55.add(diasExt);

        jButton10.setText("Documento de vencimientos");
        jButton10.setPreferredSize(new java.awt.Dimension(207, 30));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jPanel55.add(jButton10);

        jPanel45.add(jPanel55);

        jPanel38.add(jPanel45, java.awt.BorderLayout.WEST);

        jPanel37.add(jPanel38, java.awt.BorderLayout.CENTER);

        panelExtintores.add(jPanel37, java.awt.BorderLayout.PAGE_START);

        jTabbedPane1.addTab("Extintores", panelExtintores);

        getContentPane().add(jTabbedPane1, java.awt.BorderLayout.CENTER);

        jPanel2.setBackground(global.headerscolor);
        jPanel2.setForeground(new java.awt.Color(32, 53, 108));
        jPanel2.setLayout(new java.awt.BorderLayout());

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new ImageIcon("images\\headvencimientos.png"));
        jLabel1.setPreferredSize(new java.awt.Dimension(0, 100));
        jPanel2.add(jLabel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void boxVencimientosChoferesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxVencimientosChoferesItemStateChanged
//        loadChofersModel();
//        cargarVencimientoChoferes2();
        if (vencimientosid.size() > 0) {
            String vencimientosIdsConcat = String.valueOf(vencimientosid.get(boxVencimientosChoferes.getSelectedIndex())); //bien
            loadChofersModel(vencimientosIdsConcat); //bien
            cargarTablaChoferDinamica(procesarVencimientos(vencimientosIdsConcat));
            cargarConteoDinamico(vencimientosIdsConcat);
        }

    }//GEN-LAST:event_boxVencimientosChoferesItemStateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            Connection con = utils.startConnection();
            JasperReport reporte = null;
            if (boxVencimientosChoferes.getSelectedIndex() > 0) {
                reporte = (JasperReport) JRLoader.loadObject(new File("ExcelVencimiento_V3.jasper"));
            }
            if (boxVencimientosChoferes.getSelectedIndex() == 0) {
                reporte = (JasperReport) JRLoader.loadObject(new File("ExcelVencimientoAll_V3.jasper"));
            }
            Map parametros = new HashMap();
            parametros.put("nombre", "%" + jTextField1.getText() + "%");
            parametros.put("tipo", boxPuesto.getSelectedIndex());
            parametros.put("vencimientoID", vencimientosid.get(boxVencimientosChoferes.getSelectedIndex()));
            System.out.println("ID VEN: " + vencimientosid.get(boxVencimientosChoferes.getSelectedIndex()));
            parametros.put("dias", jTextField2.getText());
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            utils.windowJasper(jasperPrint);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Error constructor " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            String vencimientosIdsConcat = String.valueOf(vencimientosid.get(boxVencimientosChoferes.getSelectedIndex())); //bien
            loadChofersModel(vencimientosIdsConcat); //bien
            cargarTablaChoferDinamica(procesarVencimientos(vencimientosIdsConcat));
            cargarConteoDinamico(vencimientosIdsConcat);
        }
    }//GEN-LAST:event_jTextField1KeyPressed

    private void jTextField2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2KeyPressed

    private void boxPuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxPuestoActionPerformed
        String vencimientosIdsConcat = String.valueOf(vencimientosid.get(boxVencimientosChoferes.getSelectedIndex())); //bien
        loadChofersModel(vencimientosIdsConcat); //bien
        cargarTablaChoferDinamica(procesarVencimientos(vencimientosIdsConcat));
        cargarConteoDinamico(vencimientosIdsConcat);
    }//GEN-LAST:event_boxPuestoActionPerformed

    private void boxVencimientosChoferesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxVencimientosChoferesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxVencimientosChoferesActionPerformed

    private void unidadTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unidadTextKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarTablaUnidad();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_unidadTextKeyPressed
    private void cargarTablaUnidad() {
        if (vencimientoUnidadId.size() > 0) {

            String vencimientosIdsConcat = vencimientoUnidadId.get(boxUnidadVencimientos.getSelectedIndex()); //bien
            loadCamionModel(vencimientosIdsConcat);
            cargarTablaUnidadDinamica(procesarVencimientosUnidad(vencimientosIdsConcat));
            cargarConteoDinamicoUnidad(vencimientosIdsConcat);
        }

    }

//    private void cargarTablaUnidad() {
//        String vencimientosIdsConcat = vencimientoUnidadId.get(boxUnidadVencimientos.getSelectedIndex()); //bien
//        loadCamionModel(vencimientosIdsConcat);
//        cargarTablaUnidadDinamica(procesarVencimientosUnidad(vencimientosIdsConcat));
//        cargarConteoDinamicoUnidad(vencimientosIdsConcat);
//    }

    private void boxUnidadVencimientosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxUnidadVencimientosItemStateChanged
        cargarTablaUnidad();
    }//GEN-LAST:event_boxUnidadVencimientosItemStateChanged

    private void boxUnidadVencimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxUnidadVencimientosActionPerformed

        cargarTablaUnidad();

        System.out.println(vencimientosLista.get(boxUnidadVencimientos.getSelectedItem()));
        System.out.println(boxUnidadVencimientos.getSelectedItem());
        // TODO add your handling code here:
    }//GEN-LAST:event_boxUnidadVencimientosActionPerformed

    public void generarReporteUnidades() {
        int index = boxUnidadVencimientos.getSelectedIndex();
//        if (index > 0) {
        try {

            Map parametros = new HashMap();
            String camion = "";
            if (!unidadText.getText().isEmpty()) {
                camion = " ca.NoEconomico LIKE '%" + unidadText.getText() + "%' and ";
            }
            String vencimiento = "";
            vencimiento = vencimientosLista.get(boxUnidadVencimientos.getSelectedItem());
            String vencimientoCon = " " + vencimiento + " IS NOT NULL and ";
            int diasInt = 0;
            String diasCon = "";
            try {
                diasInt = Integer.parseInt(diasUnidadTxt.getText());
                diasCon = " DATEDIFF(" + vencimiento + ", now()) < " + diasUnidadTxt.getText() + " and ";
            } catch (NumberFormatException erv) {
                System.out.println("Error al convertir los dias:" + erv);
                diasInt = 0;
                diasCon = "";
            }
            String vencimientoID = vencimientoUnidadId.get(boxUnidadVencimientos.getSelectedIndex());

            String q = "select NoEconomico,vc.vencimiento as vencimiento,datediff(cvc.fecha,now()) as dias from camiones_tbl ca \n"
                    + "inner join choferes_vencimientos_camiones cvc ON cvc.ChoferID = ca.CamionID\n"
                    + "inner join vencimientos_camiones vc ON vc.ID = cvc.vencimientoID\n"
                    + "where ca.status = 1 and datediff(cvc.fecha,now()) < " + diasInt + " and " + camion + " \n"
                    + "cvc.deleted is null and vc.deleted is null and (" + vencimientoID + " = 0 or vc.iD = " + vencimientoID + ") order by dias,NoEconomico desc";

            parametros.put("vencimiento", (boxUnidadVencimientos.getSelectedItem()));

            JRDesignQuery query = new JRDesignQuery();
            query.setText(q);
            JasperDesign jd;
            String report = generarRutaReporte() + "vencimientosUnidades.jrxml";
            jd = JRXmlLoader.load(report);
            jd.setQuery(query);
            Connection con = utils.startConnection();
            JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);//(JasperReport) JRLoader.loadObject(new File("Reporte_Transferencias.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            utils.windowJasper(jasperPrint);
        } catch (JRException ex) {
            Logger.getLogger(VencimientosV3.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String generarRutaReporte() {

        String path = "";

        if (global.db.equals("ltidb")) {
            path = "C:\\SERVER\\TransportesLTI\\dist\\";
        }

        prt("BASE DE DATOOS: " + global.db);
        return path;

    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        generarReporteUnidades();
// TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void diasUnidadTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diasUnidadTxtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_diasUnidadTxtKeyPressed

    private void dCorreoEnvioWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_dCorreoEnvioWindowGainedFocus
        //FillCombo.cargarCorreos(Correos, CorreosID, "");
    }//GEN-LAST:event_dCorreoEnvioWindowGainedFocus
    private void guardarCorreos() {

        /*Se obtienen los textos de cada cuadro, cuerpo, tema y los dias*/
        int correoSeleccionado = comboCorreoSaliente.getSelectedIndex();
        if (correoSeleccionado > 0) {
            String bodyJefeTxt = bodyJefe.getText();
            String bodyAdminTxt = bodyAdmin.getText();
            String subjectJefeTxt = subjectJefe.getText();
            String subjectAdminTxt = subjectAdmin.getText();
            int diasJefe = 0, diasAdmin = 0;
            String correosJefe = txtCorreoJefes.getText().replace("\n", "");
            String correosAdmin = txtCorreoAdmin.getText().replace("\n", "");
            boolean envioJefe = checkCorreoJefes.isSelected();
            boolean envioAdmin = checkCorreoAdmin.isSelected();

            try {
                diasJefe = Integer.parseInt((correoDiaJefe.getText().equals("") ? "0" : correoDiaJefe.getText()));
                diasAdmin = Integer.parseInt((correoDiaAdmin.getText().equals("") ? "0" : correoDiaAdmin.getText()));
                /*Se consultan en la base de datos los registros correspondiente a cada tipo, ya sea jefes o admin*/
                String jefeExiste = utils.dbConsult("SELECT correoPlantillaID from correosPlantilla where tipoCorreoId = 1 and usuarioId = " + global.usuario);
                String adminExiste = utils.dbConsult("SELECT correoPlantillaID from correosPlantilla where tipoCorreoId = 2 and usuarioId = " + global.usuario);

                /*Se comprueba si existen o no el contenido,  en base de datos*/
                if (jefeExiste.isEmpty()) {
                    utils.dbInsert("INSERT INTO correosPlantilla (correoPlantillaID,cuerpo,tema,periodoEnvio,tipoCorreoId,correos,enviarCorreo,usuarioId,correoId) "
                            + " values (0,'" + bodyJefeTxt + "','" + subjectJefeTxt + "'," + diasJefe + ",1,'" + correosJefe + "'," + envioJefe + "," + global.usuario + "," + correoSalienteId.get(correoSeleccionado) + ")  ");
                } else {
                    utils.dbUpdate("UPDATE correosPlantilla set cuerpo = '" + bodyJefeTxt + "' , tema = '" + subjectJefeTxt + "' , "
                            + " periodoEnvio = " + diasJefe + " , correos = '" + correosJefe + "' , enviarCorreo = " + envioJefe + ", correoId = " + correoSalienteId.get(correoSeleccionado) + "  where correoPlantillaID = " + jefeExiste);
                }
                if (adminExiste.isEmpty()) {
                    utils.dbInsert("INSERT INTO correosPlantilla (correoPlantillaID,cuerpo,tema,periodoEnvio,tipoCorreoId,correos,enviarCorreo,usuarioId,correoId) "
                            + " values (0,'" + bodyAdminTxt + "','" + subjectAdminTxt + "'," + diasAdmin + ",2,'" + correosAdmin + "'," + envioAdmin + "," + global.usuario + "," + correoSalienteId.get(correoSeleccionado) + ")");
                } else {
                    utils.dbUpdate("UPDATE correosPlantilla set cuerpo = '" + bodyAdminTxt + "' , tema = '" + subjectAdminTxt + "' , "
                            + " periodoEnvio = " + diasAdmin + " , correos = '" + correosAdmin + "' , enviarCorreo = " + envioAdmin + ",correoId = " + correoSalienteId.get(correoSeleccionado) + " where correoPlantillaID = " + adminExiste + " ");
                }
            } catch (NumberFormatException erv) {
                JOptionPane.showMessageDialog(this, "Formato incorrecto en los dias de envio,favor de llenar/corregir", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Favor de seleccionar un correo, si no encuentra el correo deseado"
                    + " favor de solicitar la alta del correo al equipo", "Aviso", JOptionPane.HEIGHT);
        }
    }

    public void cargarInformacionCorreoJefes() {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String queryJefes = "SELECT IFNULL(cuerpo,'') as cuerpo,IFNULL(tema,'') as tema,"
                + "IFNULL(periodoEnvio,0) as periodoEnvio,IFNULL(correos,'') as correos,"
                + "enviarCorreo FROM correosplantilla where tipoCorreoId = 1 and usuarioId = " + global.usuario + " limit 1";
        /*Carga los de jefes*/
        System.out.println(queryJefes);
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(queryJefes);

            while (rs.next()) {
                bodyJefe.setText(rs.getString("cuerpo"));
                subjectJefe.setText(rs.getString("tema"));
                correoDiaJefe.setText(String.valueOf(rs.getInt("periodoEnvio")));
                txtCorreoJefes.setText(rs.getString("correos"));
                System.out.println(rs.getString("cuerpo") + " " + rs.getString("tema") + " " + rs.getString("periodoEnvio")
                        + " " + rs.getString("correos"));
                checkCorreoJefes.setSelected(rs.getBoolean("enviarCorreo"));
            }
        } catch (SQLException erv) {

            JOptionPane.showMessageDialog(this, erv, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }

//    void generadorReportesJefe() {
//
//        ArrayList<String> vencimientos = new ArrayList<>();
//        vencimientos.add("ExpPlacas");
//        vencimientos.add("ExpPlacasUSA");
//        vencimientos.add("ExpSeguro");
//        vencimientos.add("Verifimotor");
//        vencimientos.add("Verifihumo");
//        vencimientos.add("permiso_userfee");
//        vencimientos.add("permiso_usdot_usermx");
//        vencimientos.add("permiso_hazmatdot");
//        vencimientos.add("user_hazmatshp");
//        vencimientos.add("user_caat");
//        vencimientos.add("user_scac");
//        vencimientos.add("user_userdot_disel");
//
//        
//
//            Map parametros = new HashMap();
//
//        for(String vencimiento : vencimientos){
//try {
//            String q = "select NoEconomico, " + vencimiento + " as vencimiento, abs(datediff(" + vencimiento + ",now())) as dias  "
//                    + "from camiones_tbl where " + vencimiento + " IS NOT NULL and datediff(" + vencimiento + ",now()) < 0 and Status is true order by dias";
//
//            parametros.put("vencimiento", boxUnidadVencimientos.getSelectedItem());
//            System.out.println(boxUnidadVencimientos.getSelectedItem());
//            System.out.println(q);
//            JRDesignQuery query = new JRDesignQuery();
//            query.setText(q);
//
//            JasperDesign jd = JRXmlLoader.load("C:\\SERVER\\TransportesRiosExpress\\vencimientosUnidadesJefes.jrxml");
//            jd.setQuery(query);
//            Connection con = utils.startConnection();
//            JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);//(JasperReport) JRLoader.loadObject(new File("Reporte_Transferencias.jasper"));
//            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
//            utils.windowJasper(jasperPrint);
//
//        } catch (JRException ex) {
//            Logger.getLogger(NuevoArticulo.class.getName()).log(Level.SEVERE, null, ex);
//            JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
//                }
//        }
//    }
//    
    void generadorReportesJefe(String tema, String cuerpo, String correos) {
        ArrayList<String> vencimientos = new ArrayList<>();
        vencimientos.add("ExpPlacas");
        vencimientos.add("ExpPlacasUSA");
        vencimientos.add("ExpSeguro");
        vencimientos.add("Verifimotor");
        vencimientos.add("Verifihumo");
        vencimientos.add("permiso_userfee");
        vencimientos.add("permiso_usdot_usermx");
        vencimientos.add("permiso_hazmatdot");
        vencimientos.add("user_hazmatshp");
        vencimientos.add("user_caat");
        vencimientos.add("user_scac");
        vencimientos.add("user_userdot_disel");

        ArrayList<String> vencimientosNombre = new ArrayList<>();

        vencimientosNombre.add("Placas MEXICO");
        vencimientosNombre.add("Placas USA");
        vencimientosNombre.add("Seguro");
        vencimientosNombre.add("Verificacion Motor");
        vencimientosNombre.add("Verificacion Humo");
//    vencimientosNombre.add("SCT");
        vencimientosNombre.add("USER FEE");
        vencimientosNombre.add("USDOT USER MX MC");
        vencimientosNombre.add("HAZMAT DOT");
        vencimientosNombre.add("HAZMAT SHP");
        vencimientosNombre.add("CAAT");
        vencimientosNombre.add("SCAC");
        vencimientosNombre.add("USDOT DISEL");

        Map parametros = new HashMap();

        // Create an ArrayList to store report paths
        ArrayList<String> reportPaths = new ArrayList<>();
        int cont = 0;
        for (String vencimiento : vencimientos) {
            try {
                String q = "SELECT NoEconomico, DATE_FORMAT(" + vencimiento + ",'%d.%m.%Y') AS vencimiento, ABS(DATEDIFF(" + vencimiento + ", NOW())) AS dias "
                        + "FROM camiones_tbl WHERE " + vencimiento + " IS NOT NULL AND DATEDIFF(" + vencimiento + ", NOW()) < 0 AND Status IS TRUE ORDER BY dias";

                parametros.put("vencimiento", vencimientosNombre.get(cont));
                cont++;
                System.out.println(q);
                JRDesignQuery query = new JRDesignQuery();
                query.setText(q);
                String reporteName = generarRutaReporte() + "vencimientosUnidadesJefes.jrxml";
                JasperDesign jd = JRXmlLoader.load(reporteName);
                jd.setQuery(query);
                Connection con = utils.startConnection();
                JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);

                // Fill the report
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

                // Export the report to a PDF file
                JRExporter exporter = new JRPdfExporter();
                String pdfFilePath = generarRutaReporte() + "mail\\" + vencimientosNombre.get(cont) + ".pdf";
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(pdfFilePath));
                exporter.exportReport();

                // Add the PDF file path to the reportPaths ArrayList
                reportPaths.add(pdfFilePath);

            } catch (JRException ex) {
                Logger.getLogger(VencimientosV3.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Send reports via email as attachments
        SendEmail.normalEmail(correos, reportPaths, cuerpo, tema, 1, new ArrayList<String[]>());
    }

    void enviarCorreoJefes() {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = " select tema, cuerpo, periodoEnvio, correos,IFNULL(ultimoEnvio,'2020-01-01') as ultimoEnvio, enviarCorreo from correosPlantilla where tipoCorreoId = 1 limit 1";

        String tema = "", cuerpo = "", correos = "";
        boolean enviarCorreo = false, correoEnviado = false;
        int periodoEnvio = 0;
        String ultimoEnvio = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        ArrayList<String> docs = new ArrayList<>();
        docs.add(generarRutaReporte() + "CTPAT_Desarrollo.pdf");
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);

            if (rs.next()) {
                cuerpo = rs.getString("cuerpo");
                tema = rs.getString("tema");
                correos = rs.getString("correos");
                enviarCorreo = rs.getBoolean("enviarCorreo");
                ultimoEnvio = rs.getString("ultimoEnvio");
                periodoEnvio = rs.getInt("periodoEnvio");
            }
            LocalDate ultimoEnvioDate = LocalDate.parse(ultimoEnvio, formatter);
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            if ((correos != null && !correos.isEmpty()) && enviarCorreo && (LocalDate.now().isEqual(ultimoEnvioDate.plusDays(periodoEnvio)) || LocalDate.now().isAfter(ultimoEnvioDate.plusDays(periodoEnvio)))) {
                generadorReportesJefe(tema, cuerpo, correos);
                utils.dbUpdate("update correosPlantilla set ultimoEnvio = '" + LocalDateTime.now() + "' where tipoCorreoId = 1");
            } else {
                System.out.println("Envio no realizado: no se pudo realizar el envio de correos a gerencia");
            }

        } catch (SQLException erv) {
            JOptionPane.showMessageDialog(this, erv, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }

//    public void cargarTipoCorreo() {
//        Connection con = null;
//        Statement st = null;
//        ResultSet rs = null;
//        String query = " select tipoCorreoID,nombre from tipoCorreo";
//        comboTipoCorreo.removeAllItems();
//        tipoCorreoId.clear();
//        try {
//            con = utils.startConnection();
//            st = con.createStatement();
//            rs = st.executeQuery(query);
//            while (rs.next()) {
//                comboTipoCorreo.addItem(rs.getString("nombre"));
//                tipoCorreoId.add(rs.getString("tipoCorreoID"));
//
//            }
//        } catch (SQLException e) {
//            JOptionPane.showMessageDialog(this, e, "Error", HEIGHT);
//        }
//    }
    void generadorReportesAdmin(String tema, String cuerpo, String correos) {
        ArrayList<String> vencimientos = new ArrayList<>();
        vencimientos.add("ExpPlacas");
        vencimientos.add("ExpPlacasUSA");
        vencimientos.add("ExpSeguro");
        vencimientos.add("Verifimotor");
        vencimientos.add("Verifihumo");
        vencimientos.add("permiso_userfee");
        vencimientos.add("permiso_usdot_usermx");
        vencimientos.add("permiso_hazmatdot");
        vencimientos.add("user_hazmatshp");
        vencimientos.add("user_caat");
        vencimientos.add("user_scac");
        vencimientos.add("user_userdot_disel");

        ArrayList<String> vencimientosNombre = new ArrayList<>();

        vencimientosNombre.add("Placas MEXICO");
        vencimientosNombre.add("Placas USA");
        vencimientosNombre.add("Seguro");
        vencimientosNombre.add("Verificacion Motor");
        vencimientosNombre.add("Verificacion Humo");
//    vencimientosNombre.add("SCT");
        vencimientosNombre.add("USER FEE");
        vencimientosNombre.add("USDOT USER MX MC");
        vencimientosNombre.add("HAZMAT DOT");
        vencimientosNombre.add("HAZMAT SHP");
        vencimientosNombre.add("CAAT");
        vencimientosNombre.add("SCAC");
        vencimientosNombre.add("USDOT DISEL");

        Map parametros = new HashMap();

        // Create an ArrayList to store report paths
        ArrayList<String> reportPaths = new ArrayList<>();
        int cont = 0;
        for (String vencimiento : vencimientos) {
            try {
                String q = "select NoEconomico, DATE_FORMAT(" + vencimiento + ",'%d.%m.%Y') as vencimiento,datediff(" + vencimiento + ",now()) as dias,\n"
                        + "((select periodoEnvio from correosplantilla where tipoCorreoId = 2) * 0.5) as diasRojo,\n"
                        + "((select periodoEnvio from correosplantilla where tipoCorreoId = 2) * 0.75) as diasAmarillo\n"
                        + "from camiones_tbl where " + vencimiento + " IS NOT NULL and Status is true order by dias";

                parametros.put("vencimiento", vencimientosNombre.get(cont));
                cont++;
                System.out.println(q);
                JRDesignQuery query = new JRDesignQuery();
                query.setText(q);

                JasperDesign jd = JRXmlLoader.load(generarRutaReporte() + "vencimientosUnidadesAdmin.jrxml");
                jd.setQuery(query);
                Connection con = utils.startConnection();
                JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);

                // Fill the report
                JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

                // Export the report to a PDF file
                JRExporter exporter = new JRPdfExporter();
                String pdfFilePath = generarRutaReporte() + "mail\\" + vencimiento + ".pdf";
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(pdfFilePath));
                exporter.exportReport();

                // Add the PDF file path to the reportPaths ArrayList
                reportPaths.add(pdfFilePath);

            } catch (JRException ex) {
                Logger.getLogger(VencimientosV3.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Send reports via email as attachments
        SendEmail.normalEmail(correos, reportPaths, cuerpo, tema, 1, new ArrayList<String[]>());
    }

    void enviarCorreoAdmin() {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = " select tema, cuerpo, periodoEnvio, correos,IFNULL(ultimoEnvio,'2020-01-01') as ultimoEnvio, enviarCorreo from correosPlantilla where tipoCorreoId = 2 limit 1";

        String tema = "", cuerpo = "", correos = "";
        boolean enviarCorreo = false;
        int periodoEnvio = 0;
        String ultimoEnvio = "";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);

            if (rs.next()) {
                cuerpo = rs.getString("cuerpo");
                tema = rs.getString("tema");
                correos = rs.getString("correos");
                enviarCorreo = rs.getBoolean("enviarCorreo");
                ultimoEnvio = rs.getString("ultimoEnvio");
                periodoEnvio = rs.getInt("periodoEnvio");
            }
            LocalDate ultimoEnvioDate = LocalDate.parse(ultimoEnvio, formatter);
            this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            if ((correos != null && !correos.isEmpty()) && enviarCorreo && (LocalDate.now().isEqual(ultimoEnvioDate.plusDays(periodoEnvio)) || LocalDate.now().isAfter(ultimoEnvioDate.plusDays(periodoEnvio)))) {
                generadorReportesAdmin(tema, cuerpo, correos);
                utils.dbUpdate("update correosPlantilla set ultimoEnvio = '" + LocalDateTime.now() + "' where tipoCorreoId = 2");
            } else {
                System.out.println("Envio no realizado: no se pudo realizar el envio de correos al personal administrativo");
            }

        } catch (SQLException erv) {
            JOptionPane.showMessageDialog(this, erv, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }

    void cargarInformacionCorreoAdmin() {
        /*Carga los de admin*/
        Connection con2 = null;
        Statement st2 = null;
        ResultSet rs2 = null;

        String queryAdmin = "SELECT IFNULL(cuerpo,'') as cuerpo,IFNULL(tema,'') as tema,"
                + "IFNULL(periodoEnvio,0) as periodoEnvio,IFNULL(correos,'') as correos,"
                + "enviarCorreo FROM correosplantilla where tipoCorreoId = 2 and usuarioId = " + global.usuario + " limit 1";

        try {
            con2 = utils.startConnection();
            st2 = con2.createStatement();
            rs2 = st2.executeQuery(queryAdmin);

            while (rs2.next()) {
                bodyAdmin.setText(rs2.getString("cuerpo"));
                subjectAdmin.setText(rs2.getString("tema"));
                correoDiaAdmin.setText(String.valueOf(rs2.getInt("periodoEnvio")));
                txtCorreoAdmin.setText(rs2.getString("correos"));
                checkCorreoAdmin.setSelected(rs2.getBoolean("enviarCorreo"));

            }
        } catch (SQLException erv) {

            JOptionPane.showMessageDialog(this, erv, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con2, st2, rs2);
        }
    }

    private void cargarCorreosSalientes() {
        /*Declaramos las variables conexion, y la query, limpiamos componente y arreglo*/
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String queryCorreos = "select MailID,Correo from mailinfo_tbl";
        correoSalienteId.clear();
        /*Declaramos las variables conexion, y la query, limpiamos componente y arreglo*/
        try {
            /*Iniciamos las conexiones y ejecutamos la query*/
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(queryCorreos);
            /*Iteramos y asignamos los valores*/
            correoSalienteId.add("0");
            while (rs.next()) {
                correoSalienteId.add(rs.getString("MailID"));
                comboCorreoSaliente.addItem(rs.getString("Correo"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar correos saliente: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    private void cargarCorreoUsuario() {
        String CorreoId = utils.dbConsult("select correoId from correosplantilla where usuarioId = " + global.usuario + " group by usuarioId");
        if (!(CorreoId.equals("0"))) {
            int correoIndex = correoSalienteId.indexOf(CorreoId);
            comboCorreoSaliente.setSelectedIndex(correoIndex);
        } else {
            comboCorreoSaliente.setSelectedIndex(0); //Se manda por default la opcion generica
        }
    }

    void cargarTablaVencimientosUnidades() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        vencimientosId.clear();
        tablemodelVencimiento.setRowCount(0);
        String query = "select vc.ID,vc.vencimiento,tipoAdmin,tipoGerencia from vencimientos_camiones vc  \n"
                + "left join correoReportes cp ON vc.ID = cp.vencimientoID and usuarioId= " + global.usuario + " \n"
                + "where vc.vencimiento != ''  and vc.deleted is null and vc.ID != 0  order by vc.ID";

        con = utils.startConnection();

        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                vencimientosId.add(rs.getString("vc.ID"));
                tablemodelVencimiento.addRow(new Object[]{rs.getString("vc.vencimiento"), rs.getBoolean("tipoAdmin"),
                    rs.getBoolean("tipoGerencia")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tabla vencimientos: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }

    void cargarTablaVencimientosExtintor() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        vencimientosIdExtintor.clear();
        tablemodelVencimiento.setRowCount(0);

        String query = "select e.ExtintorID as ID,concat(e.identificador,' - ',p.nombre)  as vencimiento,\n"
                + "tipoAdmin,tipoGerencia from extintores_tbl e\n"
                + "left join correoreportesExtintor\n"
                + "ON correoreportesExtintor.vencimientoId = e.ExtintorID\n"
                + "and usuarioId = " + global.usuario + " \n"
                + "inner join patios_tbl p ON p.patioId = e.yardaId\n"
                + "where e.estatus is true \n"
                + "and (e.identificador is not null and e.identificador != '' )\n"
                + "order by e.ExtintorID";

        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                vencimientosIdExtintor.add(rs.getString("ID"));

                tablemodelVencimiento.addRow(new Object[]{rs.getString("vencimiento"), rs.getBoolean("tipoAdmin"),
                    rs.getBoolean("tipoGerencia")});

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tabla vencimientos: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }

    void cargarTablaVencimientosYarda() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        vencimientosIdYarda.clear();
        tablemodelVencimiento.setRowCount(0);
        String query = "select permisoAyunId as ID,nombre as vencimiento,tipoAdmin,tipoGerencia from permisos_ayuntamiento_tbl\n"
                + "left join correoreportesYarda\n"
                + " ON correoreportesYarda.vencimientoId =permisos_ayuntamiento_tbl.permisoAyunId \n"
                + " and usuarioId = " + global.usuario + " \n"
                + "where permisos_ayuntamiento_tbl.estatus is true \n"
                + "and (permisos_ayuntamiento_tbl.nombre is not null and permisos_ayuntamiento_tbl.nombre != '' )\n"
                + "  order by permisos_ayuntamiento_tbl.permisoAyunId";
        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                vencimientosIdYarda.add(rs.getString("ID"));
                tablemodelVencimiento.addRow(new Object[]{rs.getString("vencimiento"), rs.getBoolean("tipoAdmin"),
                    rs.getBoolean("tipoGerencia")});

            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tabla vencimientos: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }

    void cargarTablaVencimientosChofer() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        vencimientosIdChofer.clear();
        tablemodelVencimiento.setRowCount(0);
        String query = "select ID,vencimiento,tipoAdmin,tipoGerencia from vencimientos\n"
                + "left join correoreportesChofer ON correoreportesChofer.vencimientoId =vencimientos.ID and usuarioId = " + global.usuario + " \n"
                + " where vencimientos.deleted is not true and (vencimientos.vencimiento is not null and vencimientos.vencimiento != '' )  order by vencimientos.ID";

        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                vencimientosIdChofer.add(rs.getString("ID"));
                tablemodelVencimiento.addRow(new Object[]{rs.getString("vencimiento"), rs.getBoolean("tipoAdmin"),
                    rs.getBoolean("tipoGerencia")});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tabla vencimientos: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }

    void resetCorreoNotificaciones() {
        /*Limpiar parte jefes*/
        bodyJefe.setText("");
        subjectJefe.setText("");
        correoDiaJefe.setText("");
        txtCorreoJefes.setText("");
        checkCorreoJefes.setSelected(false);
        /*Limpiar parte admin*/
        bodyAdmin.setText("");
        subjectAdmin.setText("");
        correoDiaAdmin.setText("");
        txtCorreoAdmin.setText("");
        checkCorreoAdmin.setSelected(false);
        /*Limpiar box correos*/
        comboCorreoSaliente.setSelectedIndex(0);
    }

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        guardarCorreos();
        dCorreoEnvio.dispose();
    }//GEN-LAST:event_jButton21ActionPerformed

    private void txtCorreoJefesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoJefesKeyTyped
        char c = evt.getKeyChar();
        if (c == ' ' || c == ',') {
            evt.consume();
        }
    }//GEN-LAST:event_txtCorreoJefesKeyTyped

    private void txtCorreoJefesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoJefesKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_SEMICOLON) {
            int position = txtCorreoJefes.getCaretPosition();
            txtCorreoJefes.insert("\n", position);
        }
    }//GEN-LAST:event_txtCorreoJefesKeyReleased

    private void txtCorreoAdminKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoAdminKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoAdminKeyReleased

    private void txtCorreoAdminKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCorreoAdminKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCorreoAdminKeyTyped

    private void subjectAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subjectAdminActionPerformed

    private void subjectJefeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectJefeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subjectJefeActionPerformed

    private void correoDiaAdminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_correoDiaAdminActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_correoDiaAdminActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        dCorreoEnvio.setVisible(true);
        dCorreoEnvio.setLocationRelativeTo(this);
        resetCorreoNotificaciones();
        cargarCorreoUsuario();
        cargarInformacionCorreoJefes();
        cargarInformacionCorreoAdmin();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        dCorreoEnvio.setVisible(true);
        dCorreoEnvio.setLocationRelativeTo(this);
        dCorreoEnvio.setIconImage(new ImageIcon("images\\icon.png").getImage());
        resetCorreoNotificaciones();
        cargarInformacionCorreoJefes();
        cargarInformacionCorreoAdmin();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void ReportesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportesBtnActionPerformed
        jDialog1.setVisible(true);
        jDialog1.setLocationRelativeTo(this);
        jDialog1.setIconImage(new ImageIcon("images\\icon.png").getImage());
        if (!(comboTipoVencimientos.getSelectedIndex() == -1)) {
            if (comboTipoVencimientos.getSelectedIndex() == 0) {
                cargarTablaVencimientosUnidades();
            }
            if (comboTipoVencimientos.getSelectedIndex() == 1) {
                cargarTablaVencimientosChofer();
            }
            if (comboTipoVencimientos.getSelectedIndex() == 2) {
                cargarTablaVencimientosYarda();
            }
            if (comboTipoVencimientos.getSelectedIndex() == 3) {
                cargarTablaVencimientosExtintor();
            }
        } else {
            cargarTablaVencimientosUnidades();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_ReportesBtnActionPerformed

    private void comboTipoVencimientosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboTipoVencimientosItemStateChanged
        if (comboTipoVencimientos.getSelectedIndex() == 0) {
            cargarTablaVencimientosUnidades();
        }
        if (comboTipoVencimientos.getSelectedIndex() == 1) {
            cargarTablaVencimientosChofer();
        }
        if (comboTipoVencimientos.getSelectedIndex() == 2) {
            cargarTablaVencimientosYarda();
        }
        if (comboTipoVencimientos.getSelectedIndex() == 3) {
            cargarTablaVencimientosExtintor();
        }

    }//GEN-LAST:event_comboTipoVencimientosItemStateChanged

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        cargarPermisosChoferes();
        dFiltroChoferes.setVisible(true);
        dFiltroChoferes.setLocationRelativeTo(this);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void cargarVencimientosFiltroUnidad() {
        tablemodelVencimientoUnidad.setRowCount(0);
        vencimientosUnidadDina.clear();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select correoReporteId,aliasVencimiento from correosvencimiento ";
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                tablemodelVencimientoUnidad.addRow(new Object[]{rs.getString("aliasVencimiento"), false});
                vencimientosUnidadDina.add(rs.getString("correoReporteId"));
            }
        } catch (SQLException e) {
            prt("error al cargar vencimiento unidades tabla dinamicas unidad:" + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    private void cargarColumnasFiltroUnidad(String vencimientos) {
//        DefaultTableModel modelo = tablemodelVencimientoUnidad;
        tablemodelVencimientoUnidad.setColumnCount(0);
        for (String value : vencimientosLista.keySet()) {
            if (vencimientos.contains(value)) {
                tablemodelVencimientoUnidad.addRow(new Object[]{value, false});
            }
        }

    }
    private void aceptarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBtnActionPerformed
//        cargarPermisosChoferes(concatenarVencIDs());
        activarTablaChoferesDinamica();
//Se sobre carga la funcion cargarPermisosChoferes, lo que hace la funcion que concatena Ids
        // de vencimientos y los manda como parametros para consultarlos y cargarlos en la tabla
        // TODO add your handling code here:
    }//GEN-LAST:event_aceptarBtnActionPerformed

    private void cancelarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtnActionPerformed
        dFiltroChoferes.dispose();
        loadChofersModel();
        cargarVencimientoChoferes2();
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarBtnActionPerformed

    private void ReportesBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportesBtn1ActionPerformed

        enviarCorreoAhora();

        // TODO add your handling code here:
    }//GEN-LAST:event_ReportesBtn1ActionPerformed

    private void GenerarReporteDinamicBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarReporteDinamicBtnActionPerformed
        try {

            Map parametros = new HashMap();
            String vencimientosIdsConcat = concatenarVencIDs();
            String muchosVencimientos = "1";
            if (vencimientosIdsConcat.isEmpty()) {
                muchosVencimientos = "0";
                vencimientosIdsConcat = "0";
            }

            String q = "select NoEmpleado,choferes_tbl.Nombre, DATE_FORMAT(choferes_vencimientos.fecha,'%d.%m.%Y') as venc,\n"
                    + "(select Nombre from tiposchoferes_tbl where tipoID=tipooperador) as tipox,\n"
                    + "(select vencimiento from vencimientos where ID=choferes_vencimientos.vencimientoID) as venx,\n"
                    + "DATEDIFF(choferes_vencimientos.fecha, now()) as dias_venc\n"
                    + "from choferes_tbl\n"
                    + "inner join choferes_vencimientos on choferes_tbl.choferID = choferes_vencimientos.choferID \n"
                    + "where choferes_tbl.Status = true \n"
                    + "and IF( " + muchosVencimientos + "+0>0, vencimientoID in (" + vencimientosIdsConcat + "), vencimientoID>=0 )\n"
                    + "and NOW()+INTERVAL 0+0 DAY > choferes_vencimientos.fecha\n"
                    + "and IF( " + boxPuesto.getSelectedIndex() + "+0>0, choferes_tbl.Puesto = " + boxPuesto.getSelectedIndex() + " , choferes_tbl.choferID>=0 )\n"
                    + "and  choferes_tbl.Nombre like '%" + jTextField1.getText() + "%' and choferes_tbl.Puesto != 5\n"
                    + "order by NoEmpleado,\n"
                    + "DATEDIFF(choferes_vencimientos.fecha, now()) desc";

            System.out.println("querytest " + q);
            System.out.println(q);
            JRDesignQuery query = new JRDesignQuery();
            query.setText(q);

            String report = generarRutaReporte() + "ExcelVencimientoDinamic_V3.jrxml";
            JasperDesign jd = JRXmlLoader.load(report);
            jd.setQuery(query);

            Connection con = utils.startConnection();
            JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);//(JasperReport) JRLoader.loadObject(new File("Reporte_Transferencias.jasper"));

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);

            utils.windowJasper(jasperPrint);
        } catch (JRException ex) {
            Logger.getLogger(VencimientosV3.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_GenerarReporteDinamicBtnActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        cargarPermisosUnidades();

        dFiltroUnidades.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dFiltroUnidades.setLocationRelativeTo(this);
        dFiltroUnidades.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed
    public void cargarVencimientosRemolque() {

        int indexVencimientoRemolque = boxRemolqueVencimientos.getSelectedIndex();
        String traerTodosLosVen = "0";
        String vencimientosIdsConcat = ""; //Concatenar los vencimientos seleccionados.

        if (indexVencimientoRemolque < 0) {
            indexVencimientoRemolque = 0;
        }
        if (vencimientoRemolqueId.size() < 1) {
            vencimientosIdsConcat = "0";
        } else {
            vencimientosIdsConcat = vencimientoRemolqueId.get(indexVencimientoRemolque);
        }

        loadRemolqueModel(vencimientosIdsConcat); // Carga el modelo en base a lo que se selecciono
        cargarTablaRemolqueDinamica(procesarVencimientosRemolque(vencimientosIdsConcat));

        //Se procesan para poder tener las querys de seleccion.
        // Cargar la tabla de forma dinamica.
        cargarConteoDinamicoRemolque(vencimientosIdsConcat);
    }
    private void aceptarBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBtn1ActionPerformed
        String vencimientosIdsConcat = concatenarVencIDUnidad(); //Concatenar los vencimientos seleccionados.
        loadCamionModel(vencimientosIdsConcat); // Carga el modelo en base a lo que se selecciono
        cargarTablaUnidadDinamica(procesarVencimientosUnidad(vencimientosIdsConcat));

        //Se procesan para poder tener las querys de seleccion.
        // Cargar la tabla de forma dinamica.
        cargarConteoDinamicoUnidad(vencimientosIdsConcat);
    }//GEN-LAST:event_aceptarBtn1ActionPerformed

    private String concatenarVencIDRemolque() {

        int numeroVencimientos = tablaFiltroRemolque.getRowCount();
        String listaVencimientos = "";
        if (numeroVencimientos > 1) {
            int i = 1;
            while (i <= (numeroVencimientos)) {
                if ((boolean) tablaFiltroRemolque.getValueAt(i - 1, 1)) {
                    String vencimiento = vencimientosRemolqueDina.get(i - 1);
                    listaVencimientos = listaVencimientos + vencimiento + ",";
                }
                i++;
            }
            if (!listaVencimientos.isEmpty()) {
                listaVencimientos = listaVencimientos.substring(0, listaVencimientos.length() - 1);
//            listaVencimientos = listaVencimientos + (((boolean) tablaFiltroUnidad.getValueAt(i - 1, 1)) ? vencimientoChoferId.get(i - 1) : ' ');
            } else {
                listaVencimientos = "0";
            }
            System.out.println(listaVencimientos);
        } else if (numeroVencimientos == 1) {
            if ((boolean) tablaFiltroRemolque.getValueAt(0, 1)) {
                listaVencimientos = vencimientosRemolqueDina.get(0);
            } else {
                listaVencimientos = "0";
            }
        }
        return (listaVencimientos);
    }

    private String concatenarVencIDUnidad() {

        int numeroVencimientos = tablaFiltroUnidad1.getRowCount();
        String listaVencimientos = "";
        if (numeroVencimientos > 1) {
            int i = 1;
            while (i <= (numeroVencimientos)) {
                if ((boolean) tablaFiltroUnidad1.getValueAt(i - 1, 1)) {
                    String vencimiento = vencimientosUnidadDina.get(i - 1);
                    listaVencimientos = listaVencimientos + vencimiento + ",";
                }
                i++;
            }
            if (!listaVencimientos.isEmpty()) {
                listaVencimientos = listaVencimientos.substring(0, listaVencimientos.length() - 1);
//            listaVencimientos = listaVencimientos + (((boolean) tablaFiltroUnidad.getValueAt(i - 1, 1)) ? vencimientoChoferId.get(i - 1) : ' ');
            } else {
                listaVencimientos = "0";
            }
            System.out.println(listaVencimientos);
        } else if (numeroVencimientos == 1) {
            if ((boolean) tablaFiltroUnidad1.getValueAt(0, 1)) {
                listaVencimientos = vencimientosUnidadDina.get(0);
            } else {
                listaVencimientos = "0";
            }
        }
        return (listaVencimientos);
    }
    private void cancelarBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtn1ActionPerformed
        boxUnidadVencimientos.setSelectedIndex(0);
        String vencimientosIdConcat = "0";  //Cero equivale a todos los
        cargarColumnasUnidad("0"); //0
        cargarVencimientoCamionesDina(vencimientosIdConcat);
        unidadesIncidentes(vencimientosIdConcat);
        dFiltroUnidades.dispose();
        boxUnidadVencimientos.setSelectedIndex(0);
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarBtn1ActionPerformed
    public void generarReporteRemolquesFijo() {
        int index = boxRemolqueVencimientos.getSelectedIndex();
        String vencimientosIdsConcat = vencimientoRemolqueId.get(index);
        String soloUnVen = "1";
        if (vencimientosIdsConcat.isEmpty() || vencimientosIdsConcat.equals("0")) {
            soloUnVen = "0";
            vencimientosIdsConcat = "0";
        }
        try {
            Map parametros = new HashMap();
            String caja = "";
            if (!remolqueText.getText().isEmpty()) {
                caja = " ca.NoEconomico LIKE '%" + remolqueText.getText() + "%' and ";
            }
            int diasInt = 0;
            try {
                diasInt = Integer.parseInt(diasRemolqueTxt.getText());
            } catch (NumberFormatException erv) {
                System.out.println("Error al convertir los dias:" + erv);
                diasInt = 0;
            }
            String vencimientoID = vencimientosIdsConcat;
            String q = "select NoEconomico,vc.vencimiento as vencimiento,datediff(cvc.fecha,now()) as dias from cajas_tbl ca \n"
                    + "inner join choferes_vencimientos_remolques cvc ON cvc.ChoferID = ca.CajaID\n"
                    + "inner join vencimientos_remolques vc ON vc.ID = cvc.vencimientoID\n"
                    + "where ca.status = 1 and datediff(cvc.fecha,now()) < " + diasInt + " and " + caja + " \n"
                    + "cvc.deleted is null and vc.deleted is null and (" + soloUnVen + " = 0 or vc.iD in (" + vencimientoID + ")) order by dias,NoEconomico desc";

            parametros.put("vencimiento", "Vencimientos");

            System.out.println(boxRemolqueVencimientos.getSelectedItem());
            System.out.println(q);
            JRDesignQuery query = new JRDesignQuery();
            query.setText(q);

            String report = generarRutaReporte() + "vencimientosRemolques.jrxml";
            JasperDesign jd = JRXmlLoader.load(report);
            jd.setQuery(query);
            Connection con = utils.startConnection();
            JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);//(JasperReport) JRLoader.loadObject(new File("Reporte_Transferencias.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            utils.windowJasper(jasperPrint);
        } catch (JRException ex) {
            Logger.getLogger(VencimientosV3.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void generarReporteRemolquesDinamica() {
        int index = boxRemolqueVencimientos.getSelectedIndex();
        String vencimientosIdsConcat = concatenarVencIDRemolque();
        String soloUnVen = "1";
        if (vencimientosIdsConcat.isEmpty() || vencimientosIdsConcat.equals("0")) {
            soloUnVen = "0";
            vencimientosIdsConcat = "0";
        }
        try {
            Map parametros = new HashMap();
            String caja = "";
            if (!remolqueText.getText().isEmpty()) {
                caja = " ca.NoEconomico LIKE '%" + remolqueText.getText() + "%' and ";
            }
            int diasInt = 0;
            try {
                diasInt = Integer.parseInt(diasRemolqueTxt.getText());
            } catch (NumberFormatException erv) {
                System.out.println("Error al convertir los dias:" + erv);
                diasInt = 0;
            }
            String vencimientoID = vencimientosIdsConcat;
            String q = "select NoEconomico,vc.vencimiento as vencimiento,datediff(cvc.fecha,now()) as dias from cajas_tbl ca \n"
                    + "inner join choferes_vencimientos_remolques cvc ON cvc.ChoferID = ca.CajaID\n"
                    + "inner join vencimientos_remolques vc ON vc.ID = cvc.vencimientoID\n"
                    + "where ca.status = 1 and datediff(cvc.fecha,now()) < " + diasInt + " and " + caja + " \n"
                    + "cvc.deleted is null and vc.deleted is null and (" + soloUnVen + " = 0 or vc.iD in (" + vencimientoID + ")) order by dias,NoEconomico desc";

            parametros.put("vencimiento", "Vencimientos");

            System.out.println(boxRemolqueVencimientos.getSelectedItem());
            System.out.println(q);
            JRDesignQuery query = new JRDesignQuery();
            query.setText(q);
            String report = generarRutaReporte() + "vencimientosRemolques.jrxml";
            JasperDesign jd = JRXmlLoader.load(report);
            jd.setQuery(query);
            Connection con = utils.startConnection();
            JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);//(JasperReport) JRLoader.loadObject(new File("Reporte_Transferencias.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            utils.windowJasper(jasperPrint);
        } catch (JRException ex) {
            Logger.getLogger(VencimientosV3.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void generarReporteUnidadesDinamica() {
        int index = boxUnidadVencimientos.getSelectedIndex();
        String vencimientosIdsConcat = concatenarVencIDUnidad();
        String soloUnVen = "1";
        if (vencimientosIdsConcat.isEmpty() || vencimientosIdsConcat.equals("0")) {
            soloUnVen = "0";
            vencimientosIdsConcat = "0";
        }
        try {
            Map parametros = new HashMap();
            String camion = "";
            if (!unidadText.getText().isEmpty()) {
                camion = " ca.NoEconomico LIKE '%" + unidadText.getText() + "%' and ";
            }
            int diasInt = 0;
            try {
                diasInt = Integer.parseInt(diasUnidadTxt.getText());
            } catch (NumberFormatException erv) {
                System.out.println("Error al convertir los dias:" + erv);
                diasInt = 0;
            }
            String vencimientoID = vencimientosIdsConcat;
            String q = "select NoEconomico,vc.vencimiento as vencimiento,datediff(cvc.fecha,now()) as dias from camiones_tbl ca \n"
                    + "inner join choferes_vencimientos_camiones cvc ON cvc.ChoferID = ca.CamionID\n"
                    + "inner join vencimientos_camiones vc ON vc.ID = cvc.vencimientoID\n"
                    + "where ca.status = 1 and datediff(cvc.fecha,now()) < " + diasInt + " and " + camion + " \n"
                    + "cvc.deleted is null and vc.deleted is null and (" + soloUnVen + " = 0 or vc.iD in (" + vencimientoID + ")) order by dias,NoEconomico desc";

            parametros.put("vencimiento", "Vencimientos");

            System.out.println(boxUnidadVencimientos.getSelectedItem());
            System.out.println(q);
            JRDesignQuery query = new JRDesignQuery();
            query.setText(q);
            String report = generarRutaReporte() + "vencimientosUnidades.jrxml";
            JasperDesign jd = JRXmlLoader.load(report);
            jd.setQuery(query);
            Connection con = utils.startConnection();
            JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);//(JasperReport) JRLoader.loadObject(new File("Reporte_Transferencias.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            utils.windowJasper(jasperPrint);
        } catch (JRException ex) {
            Logger.getLogger(VencimientosV3.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void GenerarReporteDinamicBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarReporteDinamicBtn1ActionPerformed

        generarReporteUnidadesDinamica();
    }//GEN-LAST:event_GenerarReporteDinamicBtn1ActionPerformed
    public void ejecutarCargaYarda() {
        cargarColumnasYarda(vencimientosYardasId.get(boxAyuntamiento.getSelectedIndex()));
        cargarTablaAyuntamiento(procesarVencimientosYardas(vencimientosYardasId.get(boxAyuntamiento.getSelectedIndex())));
    }
    private void ayuntamientoTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ayuntamientoTxtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarColumnasYarda(vencimientosYardasId.get(boxAyuntamiento.getSelectedIndex()));
            cargarTablaAyuntamiento(procesarVencimientosYardas(vencimientosYardasId.get(boxAyuntamiento.getSelectedIndex())));
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_ayuntamientoTxtKeyPressed

    private void boxAyuntamientoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxAyuntamientoItemStateChanged
        cargarColumnasYarda(vencimientosYardasId.get(boxAyuntamiento.getSelectedIndex()));
        cargarTablaAyuntamiento(procesarVencimientosYardas(vencimientosYardasId.get(boxAyuntamiento.getSelectedIndex())));

        // TODO add your handling code here:
    }//GEN-LAST:event_boxAyuntamientoItemStateChanged
    void cargarColumnasYarda(String vens) {
        tablemodel4.setColumnCount(0);
        vencimientosYarda.clear();
        tablemodel4.addColumn("Yarda");
        vencimientosYarda.add("0");
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        if (vens.isEmpty() || vens.equals("0")) {
            vens = " and permisoAyunId = permisoAyunId";
        } else {
            vens = " and permisoAyunId = " + vens;
        }
        String query = "select permisoAyunId, nombre from permisos_ayuntamiento_tbl where permisoAyunId != 0 and nombre != '' and estatus = 1 "
                + "   " + vens + " order by permisoAyunId asc";
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                tablemodel4.addColumn(rs.getString("nombre"));
                vencimientosYarda.add(rs.getString("permisoAyunId"));
            }
        } catch (SQLException e) {
            prt("Error al cargas columnas de yarda: " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    void cargarUnidadCapacidad() {
        //agenteBox
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select agenteId,Nombre from agenteExtintor_tbl";
        agenteBox.removeAllItems();
        agenteId.clear();
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                agenteBox.addItem(rs.getString("Nombre"));
                agenteId.add(rs.getString("agenteId"));

            }
        } catch (SQLException erv) {
            JOptionPane.showMessageDialog(this, "Error al cargar combo tipo capacidad extintor: " + erv, "error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }

    void cargarAgentesExtintor() {
        //agenteBox
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select agenteId,Nombre from agenteExtintor_tbl";
        agenteBox.removeAllItems();
        agenteId.clear();
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                agenteBox.addItem(rs.getString("Nombre"));
                agenteId.add(rs.getString("agenteId"));

            }
        } catch (SQLException erv) {
            JOptionPane.showMessageDialog(this, "Error al cargar combo agente extintor: " + erv, "error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }
//    void 

    void cargarPatios(ArrayList<String> yardaId, JComboBox<String> patios) {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select PatioID,Nombre from patios_tbl where Status = 1";
        patios.removeAllItems();
        yardaId.clear();
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                patios.addItem(rs.getString("Nombre"));
                yardaId.add(rs.getString("PatioID"));

            }
        } catch (SQLException erv) {
            JOptionPane.showMessageDialog(this, "Error al cargar combo patios extintor: " + erv, "error", JOptionPane.ERROR_MESSAGE);

        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    public void cargarTablaAyuntamiento(String vencimientosConcat) {
        Connection con = null;
        Statement st = null;
        String procedimiento = "{call cargarYardarAyun(?,?)}";
        tablemodel4.setRowCount(0);
        ResultSet rs = null;
        con = utils.startConnection();
        String patiosBuscador = ayuntamientoTxt.getText();
        try {
            procedimiento = "select concat(pa.Nombre,\" - \",pa.Codigo) as Nombre, " + vencimientosConcat + "  \n"
                    + " ,pa.PatioID FROM\n"
                    + "    patios_tbl pa\n"
                    + "        LEFT JOIN\n"
                    + "    patios_vencimientos pv ON pv.patioId = pa.patioId AND pv.estatus IS TRUE\n"
                    + "        LEFT JOIN\n"
                    + "    permisos_ayuntamiento_tbl py ON py.permisoAyunId = pv.permisoId AND py.estatus IS TRUE AND py.permisoAyunId != 0\n"
                    + "    AND py.nombre != ''\n"
                    + "WHERE\n"
                    + "    pa.Nombre LIKE '%" + patiosBuscador + "%' AND Status IS TRUE\n"
                    + "GROUP BY pa.Nombre , pa.Codigo\n"
                    + "ORDER BY permisoAyunId DESC";

            System.out.println(procedimiento);
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(procedimiento);
            int tam = tablemodel4.getColumnCount();
            patiosid.clear();
            System.out.println(tam);
            while (rs.next()) {
                Object[] obj = new Object[tam];
                for (int i = 1; i <= tam + 1; i++) {
                    if (i == (tam + 1)) {
                        patiosid.add(rs.getString(i));
                    } else {
                        obj[i - 1] = rs.getString(i);
                    }
                }
                tablemodel4.addRow(obj);
            }
            con.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar yardas: " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    private void boxAyuntamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxAyuntamientoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxAyuntamientoActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // reporteYardas
        try {
            Connection con = utils.startConnection();
            JasperReport reporte = (JasperReport) JRLoader.loadObject(new File("reporteYardas.jasper"));
            Map parametros = new HashMap();
            parametros.put("yardaNombre", "%" + ayuntamientoTxt.getText() + "%");
            parametros.put("permisoId", vencimientosYardasId.get(boxAyuntamiento.getSelectedIndex()));
            parametros.put("dias", jTextField4.getText());
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            utils.windowJasper(jasperPrint);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Error constructor " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTextField5KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarTablaExtintor();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5KeyPressed

    private void jTextField4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4KeyPressed

    private void identificadorTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_identificadorTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_identificadorTxtActionPerformed

    private void capacidadTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_capacidadTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_capacidadTxtActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed

        resettAltaExtintor();
        dAltaExtintor.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dAltaExtintor.setLocationRelativeTo(this);
        dAltaExtintor.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    void cargarTipoCapacidad() {
        //tipoCapacidadExtintor_tbl
        String query = "select tipoId,nombreCorto from tipoCapacidadExtintor_tbl";
        comboTipoCapacidad.removeAllItems();
        tipoCantidadId.clear();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                tipoCantidadId.add(rs.getString("tipoId"));
                comboTipoCapacidad.addItem(rs.getString("nombreCorto"));
            }
        } catch (SQLException e) {
            prt("error al cargar el combo de tipo de cantidad:" + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }

    void cargarTablaExtintor() {
        String query = "select extintorId,ex.identificador ,pa.Nombre as almacen, DATE_FORMAT(fechaVencimiento, '%d.%m.%Y') as fecha,\n"
                + "                       concat(ex.Capacidad,\" \",tc.NombreCorto) as capacidad\n"
                + ",ex.comentarios,ae.nombre as agente from extintores_tbl ex\n"
                + "inner join patios_tbl pa ON pa.patioId = ex.yardaId\n"
                + "inner join tipoCapacidadExtintor_tbl tc ON ex.tipoCapacidad = tc.tipoId\n"
                + "inner join agenteExtintor_tbl ae ON ae.agenteId = ex.tipoAgente "
                + "where (ex.identificador LIKE '%" + jTextField5.getText() + "%' or "
                + " pa.Nombre LIKE  '%" + jTextField5.getText() + "%' or ex.comentarios LIKE '%" + jTextField5.getText() + "%') "
                + " and ex.estatus = 1";
        tablemodel5.setRowCount(0);
        extintorId.clear();
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                extintorId.add(rs.getString("extintorId"));
                tablemodel5.addRow(new Object[]{rs.getString("identificador"), rs.getString("almacen"), rs.getString("fecha"),
                    rs.getString("capacidad"), rs.getString("agente"), rs.getString("comentarios")});
            }
        } catch (SQLException e) {
            prt("error al cargar vencimiento unidades tabla dinamicas unidad:" + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }
    private void guardarExtBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarExtBtnActionPerformed
        guardarExtintor();
        dAltaExtintor.dispose();
        cargarTablaExtintor();
    }//GEN-LAST:event_guardarExtBtnActionPerformed

    private void EditarExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarExtActionPerformed
        int row = jTable5.getSelectedRow();
        if (row >= 0) {
            SelectedExtintor = Integer.parseInt(extintorId.get(row));
            cargarExtintorAlta();
            dAltaExtintor.setLocationRelativeTo(this);
            dAltaExtintor.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Favor de seleccionar un extintor", "Alerta", HEIGHT);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_EditarExtActionPerformed

    private void jTable5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable5MouseClicked
        int row = jTable5.getSelectedRow();
        if (row >= 0) {
            if (evt.getButton() == 3) {
                jPopupMenu1.show(jTable5, evt.getX(), evt.getY());
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jTable5MouseClicked

    private void EliminarExtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarExtActionPerformed
        int row = jTable5.getSelectedRow();
        SelectedExtintor = Integer.parseInt(extintorId.get(row));
        eliminarExtintor();
        cargarTablaExtintor();
        SelectedExtintor = 0;

    }//GEN-LAST:event_EliminarExtActionPerformed

    private void aceptarBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBtn2ActionPerformed

        int rows = tablaBitacoraExt.getRowCount();
        String bitacora = crearBitacora();
        for (int i = 0; i < rows; i++) {
            guardarBitacora(i, bitacora);
        }
        dBitacoraExtintor.dispose();
    }//GEN-LAST:event_aceptarBtn2ActionPerformed
    String crearBitacora() {
        //Si no existe la bitacora, la crea sino devuelve el id de la misma ya creada
        String bitacoraExiste = utils.dbConsult("select bitacoraId from bitacoras_tbl where mes = " + mesId.get(comboMes.getSelectedIndex()) + " and anio = " + comboYear.getSelectedItem().toString());
        if (bitacoraExiste.isEmpty()) {
            String query = "INSERT INTO bitacoras_tbl (fecha, personaRevision, usuarioRevision, mes, anio)\n"
                    + "VALUES (now(), '" + nombreReviso.getText() + "', " + global.usuario + ", " + mesId.get(comboMes.getSelectedIndex()) + ", " + comboYear.getSelectedItem().toString() + ");";
            String insertId = utils.dbInsert(query);
            if (insertId.length() >= 11) {
                return "";
                //se inserto mal
            } else {
                JOptionPane.showMessageDialog(this, "Creada con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);

                return insertId; // se inserto bien  
            }
        } else {
            JOptionPane.showMessageDialog(this, "Actualizada con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
            return bitacoraExiste;
        }
    }

    void guardarBitacora(int row, String bitacoraId) {

        if (!(bitacoraId.isEmpty())) {
            String id = utils.dbConsult("select bitacoraId  \n"
                    + "from bitacora_mensual_ext where bitacoraMensual = " + bitacoraId + " and extintorId = " + extintorBitacoraId.get(row) + "");
            Boolean obs = Boolean.valueOf(tablaBitacoraExt.getValueAt(row, 2).toString());
            Boolean pas_plast = Boolean.valueOf(tablaBitacoraExt.getValueAt(row, 3).toString());
            Boolean presion = Boolean.valueOf(tablaBitacoraExt.getValueAt(row, 4).toString());
            String comentarios = tablaBitacoraExt.getValueAt(row, 5).toString();//tablaBitacoraExt.getValueAt(row, 6).toString();
            if (id.isEmpty()) {

                String bitId = utils.dbInsert("insert into bitacora_mensual_ext (extintorId, obstruido, pasador_plastico, presion_correcta, comentarios,bitacoraMensual ) values  \n"
                        + "(" + extintorBitacoraId.get(row) + "," + obs + "," + pas_plast + "," + presion + ",'" + comentarios + "', " + bitacoraId + " )");

                if (bitId.length() >= 11) {
                    prt("mal insert");
                } else {
//                    prt("exito insert");
                }
            } else {
                String query = "update bitacora_mensual_ext \n"
                        + "SET obstruido = " + obs + ", pasador_plastico = " + pas_plast + ", "
                        + "presion_correcta = " + presion + ", comentarios = '" + comentarios + "' where bitacoraId = " + id;
                String bitId = utils.dbUpdate(query);
                String queryBit = " update bitacoras_tbl set fechaModificacion = now(),personaRevision = '" + nombreReviso.getText() + "' where bitacoraId = " + bitacoraId;
                utils.dbConsult(queryBit);
                if (bitId.length() >= 11) {
                    prt("mal update");
                } else {
                    prt("exito update");
                }
            }
        } else {
            prt("Bitacora vacia");
            JOptionPane.showMessageDialog(this, "Error al guardar bitacora", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void cancelarBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtn2ActionPerformed
        dBitacoraExtintor.dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_cancelarBtn2ActionPerformed

    private void GenerarReporteBitacoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarReporteBitacoraActionPerformed

        if (comboMes.getSelectedIndex() >= 0 && comboYear.getSelectedIndex() >= 0) {
            String mes = mesId.get(comboMes.getSelectedIndex());
            String anio = comboYear.getSelectedItem().toString();
            try {
                String bitacora = utils.dbConsult("SELECT bitacoraID from bitacoras_tbl where mes = " + mes + " and anio = " + anio);
                if (!(bitacora.isEmpty())) {
                    Connection con = utils.startConnection();
                    JasperReport reporte = (JasperReport) JRLoader.loadObject(new File("bitacoraExtintor.jasper"));
                    Map parametros = new HashMap();
                    parametros.put("bitacoraId", bitacora);
                    JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
                    utils.windowJasper(jasperPrint);
                } else {
                    JOptionPane.showMessageDialog(this, "No hay ninguna bitacora en este mes: " + comboMes.getSelectedItem() + " \n y año: "
                            + comboYear.getSelectedItem(), "Aviso", HEIGHT);
                }
            } catch (JRException ex) {
                JOptionPane.showMessageDialog(this, "Error constructor " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_GenerarReporteBitacoraActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed

        cargarBitacora();
        dBitacoraExtintor.setIconImage(new ImageIcon("images\\icon.png").getImage());
        dBitacoraExtintor.setLocationRelativeTo(this);
        dBitacoraExtintor.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton11ActionPerformed

    private void comboMesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboMesItemStateChanged
        cargarBitacora();
        // TODO add your handling code here:
    }//GEN-LAST:event_comboMesItemStateChanged

    private void comboYearItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboYearItemStateChanged
        cargarBitacora();
        // TODO add your handling code here:
    }//GEN-LAST:event_comboYearItemStateChanged

    private void diasExtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diasExtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_diasExtKeyPressed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            Connection con = utils.startConnection();
            JasperReport reporte = (JasperReport) JRLoader.loadObject(new File("reporteExtintores.jasper"));
            Map parametros = new HashMap();
            parametros.put("nombre", "%" + jTextField5.getText() + "%");
            parametros.put("dias", diasExt.getText());
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            utils.windowJasper(jasperPrint);
        } catch (JRException ex) {
            JOptionPane.showMessageDialog(this, "Error constructor " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton10ActionPerformed

    private void nombreRevisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nombreRevisoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nombreRevisoActionPerformed

    private void jButton95ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton95ActionPerformed

        if (tvencimientosPorYarda.getSelectedRow() >= 0) {

            chooserz.showOpenDialog(dVencimientosAlmacenes);
            filez = chooserz.getSelectedFile();

            if (filez != null) {
                InputStream inStream = null;
                OutputStream outStream = null;

                try {
                    if (!(new File(global.path + "procesoAdjuntosAlm\\")).exists()) {
                        boolean carpetaseCreo = (new File(global.path + "procesoAdjuntosAlm\\")).mkdir();
                        if (carpetaseCreo) {
                            prt("Se creo exitosamente la carpeta de almacen: " + global.path + "procesoAdjuntosAlm\\");
                        } else {
                            JOptionPane.showMessageDialog(this, "Error en crear carpeta procesoAdjuntoAlm");
                        }
                    }
                    boolean success = (new File(global.path + "procesoAdjuntosAlm\\" + SelectedAlmacen + "")).exists();
                    if (!success) {
                        boolean carpAlmancenCreada = (new File(global.path + "procesoAdjuntosAlm\\" + SelectedAlmacen + "")).mkdir();
                        if (carpAlmancenCreada) {
                            prt("Se creo de forma exitosa la carpeta de almacen:" + global.path + "procesoAdjuntosAlm\\" + SelectedAlmacen);
                        } else {
                            JOptionPane.showMessageDialog(this, "Error en crear carpeta del almacen");
                        }

                    }

                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }
                try {
                    bfilez = new File(global.path + "procesoAdjuntosAlm\\" + SelectedAlmacen + "\\[" + utils.dbConsult("select replace((now()),':','.')") + "] " + filez.getName());
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
                    JOptionPane.showMessageDialog(dVencimientosAlmacenes, "112Error constructor " + e + "\n" + e.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
                }

                Filez = bfilez.toString();
                String DBFilez = Filez.replace("\\", "%");
                utils.dbUpdate("Update patios_vencimientos set ruta='" + DBFilez + "', nombreDoc='" + filez.getName() + "' where patioVenId ='" + vencimientosYarda_Id.get(tvencimientosPorYarda.getSelectedRow()) + "'");
                filez = null;
                chooserz.setSelectedFile(filez);

                cargarVencimientosAlmacen(SelectedAlmacen);
                JOptionPane.showMessageDialog(dVencimientosAlmacenes, "El archivo ha sido adjuntado.", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(dVencimientosAlmacenes, "No se ha elegido ninguna archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton95ActionPerformed
    private void cargarVencimientosAlmacen(String almacenSeleccionado) {

        String query = "select cv.patioVenId as ID,v.nombre as vencimiento,cv.permisoId as vencimientoID,DATE_FORMAT(cv.FechaVencimiento,'" + global.fdatedb + "') as F \n"
                + "                ,cv.nombreDoc as nombre, ifnull(cv.ruta,'') as rt\n"
                + "                from patios_vencimientos cv\n"
                + "                left join permisos_ayuntamiento_tbl v ON v.permisoAyunId = cv.permisoId\n"
                + "                where  cv.patioId = " + almacenSeleccionado + " and cv.estatus is true and v.estatus is true ";

//        System.out.println("Esta es la query de " + query);
        Connection con;
        con = utils.startConnection();

        vencimientosPorYarda.setRowCount(0);
        vencimientosYarda_Id.clear();
        Vencimiento_comboTid.clear();
        RutaszYardas.clear();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String file = "", temp = "";

            while (rs.next()) {

                temp = rs.getString("rt");
                file = temp.replace("%", "\\");

                vencimientosYarda_Id.add(rs.getString("ID"));
                Vencimiento_comboTid.add(rs.getInt("vencimientoID"));
                vencimientosPorYarda.addRow(new Object[]{rs.getString("vencimiento"), rs.getString("F"), rs.getString("nombre")});
                RutaszYardas.add(file);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void jButton96ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton96ActionPerformed
        utils.dbInsert("INSERT INTO patios_vencimientos (patioVenId, permisoId, patioId, estatus, fechaVencimiento, ruta, nombreDoc) "
                + " values ('0',0,'" + SelectedAlmacen + "',1,null,'','')");
        cargarVencimientosAlmacen(SelectedAlmacen);
        //        cargarVencimientosChofer();
    }//GEN-LAST:event_jButton96ActionPerformed

    private void jButton97ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton97ActionPerformed
        if (tvencimientosPorYarda.getSelectedRow() >= 0) {
            utils.dbUpdate("UPDATE patios_vencimientos set estatus = false where patioVenId ='" + vencimientosYarda_Id.get(tvencimientosPorYarda.getSelectedRow()) + "'");
            cargarVencimientosAlmacen(SelectedAlmacen);
        }
    }//GEN-LAST:event_jButton97ActionPerformed

    private void jButton98ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton98ActionPerformed

        if (tvencimientosPorYarda.getSelectedRow() != -1) {
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(RutaszYardas.get(tvencimientosPorYarda.getSelectedRow()));
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(this, "Error abriendo archivo");
                }
            }
        }

        tvencimientosPorYarda.clearSelection();
    }//GEN-LAST:event_jButton98ActionPerformed
    void generarPlantillaYardas() {
        String query = "SELECT ayunPlantId,permisoId,estatus FROM permisos_ayuntamiento_plantilla where estatus = 1";
        Connection con;
        con = utils.startConnection();
        String alreadyLoaded = "";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("ayunPlantId"));
                alreadyLoaded = utils.dbConsult("select patioVenId from patios_vencimientos where permisoId='" + rs.getString("permisoId") + "' and patioId='" + SelectedAlmacen + "' and estatus IS TRUE");
                if (alreadyLoaded.isEmpty()) {
                    utils.dbInsert("insert into patios_vencimientos "
                            + "(patioVenId, permisoId, patioId, estatus, fechaVencimiento, ruta, nombreDoc) "
                            + "values ('0'," + rs.getString("permisoId") + "," + SelectedAlmacen + ",1,null,'','')");
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }
    private void jButton99ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton99ActionPerformed
        generarPlantillaYardas();
        cargarVencimientosAlmacen(SelectedAlmacen);
    }//GEN-LAST:event_jButton99ActionPerformed


    private void venAyuntamientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venAyuntamientoActionPerformed
        if (tablaAyuntamiento.getSelectedRow() >= 0) {

            SelectedAlmacen = patiosid.get(tablaAyuntamiento.getSelectedRow());
            cargarVencimientosAlmacen(SelectedAlmacen);
            cargarListaVencimientosYardaCombo();
            dVencimientosAlmacenes.setLocationRelativeTo(this);
            dVencimientosAlmacenes.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(tablaAyuntamiento, "Seleccione una yarda de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_venAyuntamientoActionPerformed

    private void tablaAyuntamientoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaAyuntamientoMouseClicked
        int row = tablaAyuntamiento.getSelectedRow();
        if (row >= 0) {
            if (evt.getButton() == 3) {
                menuAlmacenPermisos.show(tablaAyuntamiento, evt.getX(), evt.getY());
            }
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_tablaAyuntamientoMouseClicked

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed

        if (tvencimientoschofer.getSelectedRow() >= 0) {

            chooserz.showOpenDialog(dVencimientosChofer);
            filez = chooserz.getSelectedFile();

            if (filez != null) {

                InputStream inStream = null;
                OutputStream outStream = null;

                /*String pulls_ID = utils.dbConsult("select ID from pull where IDBASE='"+PO.get(CPO.getSelectedIndex())+"' "
                    + "and POID='"+POID.get(CPOID.getSelectedIndex())+"'");*/
                try {
                    boolean procesoAdjuntosExite = (new File(global.path + "procesoAdjuntos\\")).exists();
                    if (!procesoAdjuntosExite) {
                        (new File(global.path + "procesoAdjuntos\\")).mkdir();
                    }
                    boolean success = (new File(global.path + "procesoAdjuntos\\" + SelectedChofer + "")).mkdir();
                    if (success) {
                        System.out.println("Directory: " + global.path + "procesoAdjuntos\\" + SelectedChofer + "" + " created");
                    }
                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }

                try {
                    bfilez = new File(global.path + "procesoAdjuntos\\" + SelectedChofer + "\\[" + utils.dbConsult("select replace((now()),':','.')") + "] " + filez.getName());
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
                    //delete the original file
                    //afile.delete();
                    System.out.println("File is copied successful!");
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(dVencimientosChofer, "Error constructor " + e + "\n" + e.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
                }

                Filez = bfilez.toString();
                String DBFilez = Filez.replace("\\", "%");
                utils.dbUpdate("Update choferes_vencimientos set ruta='" + DBFilez + "', nombre='" + filez.getName() + "' where ID='" + vencimientoschofer_choferid.get(tvencimientoschofer.getSelectedRow()) + "'");
                filez = null;
                chooserz.setSelectedFile(filez);
                cargarVencimientosChofer();
                JOptionPane.showMessageDialog(dVencimientosChofer, "El archivo ha sido adjuntado.", "", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(dVencimientosChofer, "No se ha elegido ninguna archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton24ActionPerformed
    void cargarVencimientosUnidad() {

        String query = "select cv.ID,v.vencimiento,Referencia,cv.vencimientoID,DATE_FORMAT(cv.Fecha, '" + global.fdatedb + "') as F \n"
                + "                 ,cv.nombre, ifnull(cv.ruta,'') as rt\n"
                + "                 from choferes_vencimientos_camiones cv\n"
                + "                 left join vencimientos_camiones v ON v.ID = cv.vencimientoID\n"
                + "                 where  choferID = " + SelectedCamion + " and v.deleted is not true and cv.deleted is not true ";
//        System.out.println("Esta es la query de " + query);
        Connection con;
        con = utils.startConnection();

        tablemodelVencimientoCamion.setRowCount(0);
        vencimientoscamionId.clear();
        RutaszCamion.clear();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String file = "", temp = "";

            while (rs.next()) {

                temp = rs.getString("rt");
                file = temp.replace("%", "\\");

                vencimientoscamionId.add(rs.getString("ID"));
                tablemodelVencimientoCamion.addRow(new Object[]{rs.getString("vencimiento"), rs.getString("Referencia"), rs.getString("F"), rs.getString("nombre")});
                RutaszCamion.add(file);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    void cargarVencimientosChofer() {

        String query = "select cv.ID,v.vencimiento,cv.vencimientoID,DATE_FORMAT(cv.Fecha, '" + global.fdatedb + "') as F \n"
                + " ,cv.nombre, ifnull(cv.ruta,'') as rt\n"
                + " from choferes_vencimientos cv\n"
                + " left join vencimientos v ON v.ID = cv.vencimientoID\n"
                + " where v.CTPAT IS NOT TRUE and choferID = '" + SelectedChofer + "' and v.deleted is not true and cv.deleted is not true ";

//        System.out.println("Esta es la query de " + query);
        Connection con;
        con = utils.startConnection();

        mvencimientoschofer.setRowCount(0);
        vencimientoschofer_choferid.clear();
//        vencimientoIdComboChofer.clear();
        RutaszChofer.clear();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            String file = "", temp = "";

            while (rs.next()) {

                temp = rs.getString("rt");
                file = temp.replace("%", "\\");

                vencimientoschofer_choferid.add(rs.getInt("ID"));
//                vencimientoIdComboChofer.add(rs.getString("vencimientoID"));
                mvencimientoschofer.addRow(new Object[]{rs.getString("vencimiento"), rs.getString("F"), rs.getString("nombre")});
                RutaszChofer.add(file);
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        utils.dbInsert("INSERT INTO choferes_vencimientos (`choferID`, `vencimientoID`, fecha, ruta, nombre, deleted) "
                + "VALUES ('" + SelectedChofer + "', 0, NULL, NULL, '', NULL)");
        cargarVencimientosChofer();
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        if (tvencimientoschofer.getSelectedRow() >= 0) {
            utils.dbUpdate("UPDATE choferes_vencimientos set deleted=true WHERE ID='" + vencimientoschofer_choferid.get(tvencimientoschofer.getSelectedRow()) + "'");
            cargarVencimientosChofer();
            String vencimientosIdsConcat = String.valueOf(vencimientosid.get(boxVencimientosChoferes.getSelectedIndex())); //bien
            loadChofersModel(vencimientosIdsConcat); //bien
            cargarTablaChoferDinamica(procesarVencimientos(vencimientosIdsConcat));
            cargarConteoDinamico(vencimientosIdsConcat);
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed

        if (tvencimientoschofer.getSelectedRow() != -1) {
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(RutaszChofer.get(tvencimientoschofer.getSelectedRow()));
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                }
            }
        }

        tvencimientoschofer.clearSelection();
    }//GEN-LAST:event_jButton27ActionPerformed
    void generarPlantillaUnidad() {

        String query = "SELECT vp.ID, v.vencimiento as venc,vencimientoID\n"
                + "FROM vencimientos_plantilla_camiones vp\n"
                + "inner join vencimientos_camiones v ON v.ID = vencimientoID\n"
                + " where v.deleted is not true and vp.deleted is not true ";

        Connection con;
        con = utils.startConnection();

        String alreadyLoaded = "";

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("vencimientoID"));
                alreadyLoaded = utils.dbConsult("select ID from choferes_vencimientos_camiones where vencimientoID='" + rs.getString("vencimientoID") + "' and choferID='" + SelectedCamion + "' and deleted IS NOT TRUE");
                if (alreadyLoaded.isEmpty()) {
                    utils.dbInsert("INSERT INTO choferes_vencimientos_camiones (`choferID`, `vencimientoID`, fecha, ruta, nombre, deleted) "
                            + "VALUES ('" + SelectedCamion + "', '" + rs.getString("vencimientoID") + "', NULL, NULL, '', NULL)");
                }

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    void generarPlantilla() {

        String query = "SELECT vp.ID, v.vencimiento as venc,vencimientoID\n"
                + "FROM vencimientos_plantilla vp\n"
                + "inner join vencimientos v ON v.ID = vencimientoID\n"
                + "where tipoID= (select TipoOperador from choferes_tbl where choferID= " + SelectedChofer + ")   \n"
                + "and v.deleted is not true and vp.deleted is not true ";

        Connection con;
        con = utils.startConnection();

        String alreadyLoaded = "";

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString("vencimientoID"));
                alreadyLoaded = utils.dbConsult("select ID from choferes_vencimientos where vencimientoID='" + rs.getString("vencimientoID") + "' and choferID='" + SelectedChofer + "' and deleted IS NOT TRUE");
                if (alreadyLoaded.isEmpty()) {
                    utils.dbInsert("INSERT INTO choferes_vencimientos (`choferID`, `vencimientoID`, fecha, ruta, nombre, deleted) "
                            + "VALUES ('" + SelectedChofer + "', '" + rs.getString("vencimientoID") + "', NULL, NULL, '', NULL)");
                }

            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        generarPlantilla();
        cargarVencimientosChofer();
    }//GEN-LAST:event_jButton30ActionPerformed

    private void venChoferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venChoferActionPerformed
        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            SelectedChofer = choferId.get(row);
//            prt("CHOFER ELEGIDO:  " + SelectedChofer);
            cargarListaVencimientosComboChofer();
            cargarVencimientosChofer();
            dVencimientosChofer.setLocationRelativeTo(this);
            dVencimientosChofer.setVisible(true);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_venChoferActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.getSelectedRow();
        if (row >= 0) {
            if (evt.getButton() == 3) {
                menuChoferPermisos.show(jTable1, evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void tvencimientosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tvencimientosMouseClicked
        if (tvencimientos.getSelectedRow() >= 0) {
            cargarVencimientosPlantillas();
        }
    }//GEN-LAST:event_tvencimientosMouseClicked

    private void tvencimientosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tvencimientosKeyPressed

    }//GEN-LAST:event_tvencimientosKeyPressed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed

        if (tvencimientos.getSelectedRow() >= 0) {

            utils.dbUpdate("Delete from vencimientos where ID='" + vencimientosIdAlta.get(tvencimientos.getSelectedRow()) + "'");
            // Actualizando otras tablas con el permiso a eliminar en cuestion
            utils.dbUpdate("update vencimientos_plantilla set deleted = 1 where vencimientoID = " + vencimientosIdAlta.get(tvencimientos.getSelectedRow()) + "  and ID > 0");
            utils.dbUpdate("update choferes_vencimientos set deleted = 1 where vencimientoID = " + vencimientosIdAlta.get(tvencimientos.getSelectedRow()) + " and ID > 0");

            cargarVencimientos();
            cargarVencimientosPlantillas();
            String vencimientosIdsConcat = String.valueOf(vencimientosid.get(boxVencimientosChoferes.getSelectedIndex())); //bien
            loadChofersModel(vencimientosIdsConcat); //bien
            cargarTablaChoferDinamica(procesarVencimientos(vencimientosIdsConcat));
            cargarConteoDinamico(vencimientosIdsConcat);
        } else {

        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        utils.dbInsert("INSERT INTO vencimientos (vencimiento, dias, notas, deleted) VALUES ('', '', '', NULL)");
        cargarVencimientos();
        String vencimientosIdsConcat = String.valueOf(vencimientosid.get(boxVencimientosChoferes.getSelectedIndex())); //bien
        loadChofersModel(vencimientosIdsConcat); //bien
        cargarTablaChoferDinamica(procesarVencimientos(vencimientosIdsConcat));
        cargarConteoDinamico(vencimientosIdsConcat);
    }//GEN-LAST:event_jButton23ActionPerformed

    private void tvencimientosPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tvencimientosPMouseClicked

    }//GEN-LAST:event_tvencimientosPMouseClicked

    private void tipoPlantillaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoPlantillaItemStateChanged

        if (doneLoad) {
            cargarVencimientosPlantillas();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoPlantillaItemStateChanged

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        if (tvencimientos.getSelectedRow() >= 0) {
            String tipoId = utils.dbConsult("select TipoID from tiposchoferes_tbl where Nombre = '" + tipoPlantilla.getSelectedItem() + "'");
            String consulta = utils.dbConsult("select vencimientoID from vencimientos_plantilla where vencimientoID = " + vencimientosIdAlta.get(tvencimientos.getSelectedRow()) + " and tipoID = " + tipoId + " and deleted IS NOT TRUE");
            if (consulta.equals("")) {
                utils.dbInsert("INSERT INTO vencimientos_plantilla (vencimientoID, tipoID) "
                        + "VALUES ('" + vencimientosIdAlta.get(tvencimientos.getSelectedRow()) + "', '" + tipoId + "')");
                cargarVencimientosPlantillas();
            } else {
                JOptionPane.showMessageDialog(this, "Permiso ya en plantilla", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un vencimiento de la tabla de arriba.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed

        if (tvencimientosP.getSelectedRow() >= 0) {
            utils.dbUpdate("Delete from vencimientos_plantilla where ID='" + vencimientosidP.get(tvencimientosP.getSelectedRow()) + "'");
            cargarVencimientosPlantillas();
        } else {

        }
    }//GEN-LAST:event_jButton29ActionPerformed

    void generarPlantillasAuto(String choferID) {
        String query = "";
        query = "SELECT vp.ID, v.vencimiento as venc,vencimientoID\n"
                + "FROM vencimientos_plantilla vp\n"
                + "inner join vencimientos v ON v.ID = vencimientoID\n"
                + "where tipoID= (select TipoOperador from choferes_tbl where choferID= " + choferID + ")   \n"
                + "and v.deleted is not true and vp.deleted is not true ";
        Connection con;
        con = utils.startConnection();
        String alreadyLoaded = "";
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                alreadyLoaded = utils.dbConsult("select ID from choferes_vencimientos where vencimientoID='" + rs.getString("vencimientoID") + "' and choferID='" + choferID + "' and deleted is null");
                if (alreadyLoaded.isEmpty()) {
                    utils.dbInsert("INSERT INTO choferes_vencimientos (`choferID`, `vencimientoID`, fecha, ruta, nombre, deleted) "
                            + "VALUES ('" + choferID + "', '" + rs.getString("vencimientoID") + "', NULL, NULL, '', NULL)");
                    contador++;
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    void choferesTipoFiltro() {
        String idTipoOp = utils.dbConsult("SELECT TipoID from tiposchoferes_tbl where Nombre = '" + tipoPlantilla.getSelectedItem() + "'");
        String query = "SELECT * FROM choferes_tbl where TipoOperador='" + idTipoOp + "' and status = 1";
        Connection con;
        con = utils.startConnection();
        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                generarPlantillasAuto(rs.getString("choferID"));
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }
    }

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        if (tipoPlantilla.getSelectedIndex() >= 0) {
            choferesTipoFiltro();//Esta bandera tiene que ver con la consulta en la generacion de la plantilla
            //siendo true donde se genera en vencimientos_plantilla y si es false vencimientos_plantilla_CTPAT
            JOptionPane.showMessageDialog(dAltaVencimientos, "Se han actualizado los registros de " + contador + " operadores.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            contador = 0;
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton31ActionPerformed
    void cargarVencimientos() {

        String query = "SELECT * FROM vencimientos where CTPAT IS NOT TRUE and ID != 0";
        Connection con;
        con = utils.startConnection();

        mvencimientos.setRowCount(0);

        vencimientosIdAlta.clear();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                vencimientosIdAlta.add(rs.getString("ID"));
                mvencimientos.addRow(new Object[]{rs.getString("Vencimiento"), rs.getString("dias"), rs.getString("notas")});
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error " + e);
        }

    }
    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
        cargarVencimientos();
        cargarVencimientosPlantillas();
        dAltaVencimientos.setVisible(true);
        dAltaVencimientos.setLocationRelativeTo(this);
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed

        cargarAltaVencimientosUnidades();

        dAltaVencimientosUnidad.setVisible(true);
        dAltaVencimientosUnidad.setLocationRelativeTo(this);
    }//GEN-LAST:event_jButton33ActionPerformed

    private void tvencimientosAltaUnidadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tvencimientosAltaUnidadMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tvencimientosAltaUnidadMouseClicked

    private void tvencimientosAltaUnidadKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tvencimientosAltaUnidadKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_tvencimientosAltaUnidadKeyPressed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
        if (tvencimientosAltaUnidad.getSelectedRow() >= 0) {

            String vencimientoFijo = utils.dbConsult("select vencimientoFijo from vencimientos_camiones where ID = " + vencimientosAltaIdUnidad.get(tvencimientosAltaUnidad.getSelectedRow()) + " ");
            if (vencimientoFijo.equals("0")) {
                utils.dbUpdate("update vencimientos_camiones set deleted = 1 where ID='" + vencimientosAltaIdUnidad.get(tvencimientosAltaUnidad.getSelectedRow()) + "'");
                // Actualizando otras tablas con el permiso a eliminar en cuestion
                utils.dbUpdate("update vencimientos_plantilla_camiones set deleted = 1 where vencimientoID = " + vencimientosAltaIdUnidad.get(tvencimientosAltaUnidad.getSelectedRow()) + "  and ID > 0");
                utils.dbUpdate("update choferes_vencimientos_camiones set deleted = 1 where vencimientoID = " + vencimientosAltaIdUnidad.get(tvencimientosAltaUnidad.getSelectedRow()) + " and ID > 0");
                cargarAltaVencimientosUnidades();
                cargarVencimientosPlantillasUnidad();
                cargarTablaUnidad();
            } else {
                JOptionPane.showMessageDialog(rootPane, "No es posible eliminar vencimientos fijos");
            }
        } else {
        }
    }//GEN-LAST:event_jButton38ActionPerformed
    private void ejecutarcargaDeTablaUnidad() {
        String vencimientosIdsConcat = String.valueOf(vencimientosid.get(boxVencimientosChoferes.getSelectedIndex())); //bien
        loadChofersModel(vencimientosIdsConcat); //bien
        cargarTablaChoferDinamica(procesarVencimientos(vencimientosIdsConcat));
        cargarConteoDinamico(vencimientosIdsConcat);
    }
    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
        utils.dbInsert("INSERT INTO vencimientos_camiones (vencimiento, dias, notas, deleted) VALUES ('', '', '', NULL)");
        cargarAltaVencimientosUnidades();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton39ActionPerformed

    private void tvencimientosP2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tvencimientosP2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tvencimientosP2MouseClicked

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
        if (tvencimientosAltaUnidad.getSelectedRow() >= 0) {
            String consulta = utils.dbConsult("select vencimientoID from vencimientos_plantilla_camiones where vencimientoID = " + vencimientosAltaIdUnidad.get(tvencimientosAltaUnidad.getSelectedRow()) + " and deleted IS NOT TRUE");
            if (consulta.equals("")) {
                utils.dbInsert("INSERT INTO vencimientos_plantilla_camiones (vencimientoID) "
                        + "VALUES ('" + vencimientosAltaIdUnidad.get(tvencimientosAltaUnidad.getSelectedRow()) + "')");
                cargarVencimientosPlantillasUnidad();
            } else {
                JOptionPane.showMessageDialog(this, "Permiso ya en plantilla", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un vencimiento de la tabla de arriba.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
        if (tvencimientosP2.getSelectedRow() >= 0) {
            utils.dbUpdate("Delete from vencimientos_plantilla_camiones where ID='" + vencimientosidP2.get(tvencimientosP2.getSelectedRow()) + "'");
            cargarVencimientosPlantillasUnidad();
        } else {

        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton41ActionPerformed
    private void cargarVencimientosPlantillaUnidad() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "select vencimientoID from vencimientos_plantilla_camiones where deleted is null";
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            plantillaVencimientoUnidadId.clear();
            while (rs.next()) {
                plantillaVencimientoUnidadId.add(rs.getString("vencimientoID"));
            }
        } catch (SQLException e) {

        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }

    private void asignarPermisosAUnidades() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "SELECT camionId FROM camiones_tbl where status = 1";

        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            String vencimientoAsignado = "";
            int contador = 0;
            boolean bandera = false;
            while (rs.next()) {
                for (String venId : plantillaVencimientoUnidadId) {

                    vencimientoAsignado = utils.dbConsult("select ID from choferes_vencimientos_camiones where choferID = " + rs.getString("camionId") + " and vencimientoId = " + venId + " and deleted is null");

                    if (vencimientoAsignado.isEmpty()) {
                        utils.dbUpdate("insert into choferes_vencimientos_camiones (choferId,vencimientoId) "
                                + "values ( " + rs.getString("camionId") + "," + venId + ")  ");
                        vencimientoAsignado = "";
                        bandera = true;
                    }
                }
                contador = (bandera ? (contador + 1) : contador + 0);
                bandera = false;
            }
            JOptionPane.showMessageDialog(dAltaVencimientos, "Se han actualizado los registros de " + contador + " camiones.", "Exito", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Fallo en asignar los permisos" + e, "Error", HEIGHT);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }
    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        cargarVencimientosPlantillaUnidad();
        asignarPermisosAUnidades();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton42ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int row = jTable2.getSelectedRow();
        if (evt.getButton() == 3) {
            if (row >= 0) {
                menuUnidades.show(jTable2, evt.getX(), evt.getY());
            }
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void venUnidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venUnidadesActionPerformed
        if (jTable2.getSelectedRow() >= 0) {

            SelectedCamion = camionId.get(jTable2.getSelectedRow());
            cargarVencimientosUnidad();
            cargarListaVencimientosComboUnidad();

            dVencimientosUnidad.setVisible(true);
            dVencimientosUnidad.setLocationRelativeTo(this);
        } else {
            JOptionPane.showMessageDialog(dVencimientosUnidad, "Seleccione un camion de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_venUnidadesActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
        if (tvencimientosunidad.getSelectedRow() >= 0) {

            chooserz.showOpenDialog(dVencimientosUnidad);
            filez = chooserz.getSelectedFile();

            if (filez != null) {

                InputStream inStream = null;
                OutputStream outStream = null;
                try {
                    boolean existeCarpetaCamion = (new File(global.path + "procesoAdjuntosCamiones")).exists();
                    if (existeCarpetaCamion) {
                        (new File(global.path + "procesoAdjuntosCamiones")).mkdir();
                    }

                    boolean success = (new File(global.path + "procesoAdjuntosCamiones\\" + SelectedCamion + "")).mkdir();
                    if (success) {
                        System.out.println("Directory: " + global.path + "procesoAdjuntosCamiones\\" + SelectedCamion + "" + " created");
                    }

                } catch (Exception e) {
                    System.err.println("Error: " + e.getMessage());
                }

                try {
                    bfilez = new File(global.path + "procesoAdjuntosCamiones\\" + SelectedCamion + "\\[" + utils.dbConsult("select replace((now()),':','.')") + "] " + filez.getName());
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
                    JOptionPane.showMessageDialog(dVencimientosUnidad, "Error constructor " + e + "\n" + e.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
                }
                Filez = bfilez.toString();
                String DBFilez = Filez.replace("\\", "%");
                utils.dbUpdate("Update choferes_vencimientos_camiones set ruta='" + DBFilez + "', nombre='" + filez.getName() + "' where ID='" + vencimientoscamionId.get(tvencimientosunidad.getSelectedRow()) + "'");
                filez = null;
                chooserz.setSelectedFile(filez);
                cargarVencimientosUnidad();
                JOptionPane.showMessageDialog(dVencimientosUnidad, "El archivo ha sido adjuntado.", "", JOptionPane.INFORMATION_MESSAGE);

            } else {
                JOptionPane.showMessageDialog(dVencimientosUnidad, "No se ha elegido ninguna archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed

        if (tvencimientoschofer.getRowCount() == 0 || !utils.dbConsult("select vencimientoID from choferes_vencimientos_camiones where choferID='" + SelectedCamion + "' order by ID desc limit 1").equals("0")) {

            utils.dbInsert("INSERT INTO choferes_vencimientos_camiones (`choferID`, `vencimientoID`, fecha, ruta, nombre, deleted) "
                    + "VALUES ('" + SelectedCamion + "', 0, NULL, NULL, '', NULL)");

            cargarVencimientosUnidad();
        }
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        if (tvencimientosunidad.getSelectedRow() >= 0) {
            String vencimientoFijo = utils.dbConsult("select ifnull(vencimientoFijo,'') from vencimientos_camiones where ID = (select vencimientoID from choferes_vencimientos_camiones cv where cv.ID = '" + vencimientoscamionId.get(tvencimientosunidad.getSelectedRow()) + "') ");

            if (vencimientoFijo.equals("0")) {
                utils.dbUpdate("UPDATE choferes_vencimientos_camiones set deleted=true WHERE ID='" + vencimientoscamionId.get(tvencimientosunidad.getSelectedRow()) + "'");
                cargarVencimientosUnidad();
                cargarTablaUnidad();
            } else {
                JOptionPane.showMessageDialog(this, "No es posible borrar un vencimiento fijo");
            }
        }
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed

        if (tvencimientosunidad.getSelectedRow() != -1) {
            if (Desktop.isDesktopSupported()) {
                try {
                    File myFile = new File(RutaszCamion.get(tvencimientosunidad.getSelectedRow()));
                    Desktop.getDesktop().open(myFile);
                } catch (IOException ex) {
                }
            }
        }

        tvencimientoschofer.clearSelection();
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        generarPlantillaUnidad();
        cargarVencimientosUnidad();
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        modulesal.dAltaPatios temp = new modulesal.dAltaPatios(this, true);
        temp.CargarPatios();
        temp.setLocationRelativeTo(this);
        temp.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        int row = jTable4.getSelectedRow();
        if (evt.getButton() == 3) {
            if (row >= 0) {
                menuRemolques.show(jTable4, evt.getX(), evt.getY());
            }
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4MouseClicked

    private void remolqueTextKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_remolqueTextKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            cargarVencimientosRemolque();
        }

// TODO add your handling code here:
    }//GEN-LAST:event_remolqueTextKeyPressed

    private void diasRemolqueTxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_diasRemolqueTxtKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_diasRemolqueTxtKeyPressed

    private void boxRemolqueVencimientosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxRemolqueVencimientosItemStateChanged
        cargarVencimientosRemolque();
        // TODO add your handling code here:
    }//GEN-LAST:event_boxRemolqueVencimientosItemStateChanged

    private void boxRemolqueVencimientosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxRemolqueVencimientosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxRemolqueVencimientosActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed

        cargarPermisosRemolques();
        dFiltroRemolques.setLocationRelativeTo(this);
        dFiltroRemolques.setVisible(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed
    public void generarReporteRemolques() {
        int index = boxRemolqueVencimientos.getSelectedIndex();
//        if (index > 0) {
        try {

            Map parametros = new HashMap();
            String camion = "";
            if (!remolqueText.getText().isEmpty()) {
                camion = " ca.NoEconomico LIKE '%" + remolqueText.getText() + "%' and ";
            }
            String vencimiento = "";
            vencimiento = vencimientosLista.get(boxRemolqueVencimientos.getSelectedItem());
            String vencimientoCon = " " + vencimiento + " IS NOT NULL and ";
            int diasInt = 0;
            String diasCon = "";
            try {
                diasInt = Integer.parseInt(diasUnidadTxt.getText());
                diasCon = " DATEDIFF(" + vencimiento + ", now()) < " + diasUnidadTxt.getText() + " and ";
            } catch (NumberFormatException erv) {
                System.out.println("Error al convertir los dias:" + erv);
                diasInt = 0;
                diasCon = "";
            }
            String vencimientoID = vencimientoUnidadId.get(boxUnidadVencimientos.getSelectedIndex());

            String q = "select NoEconomico,vc.vencimiento as vencimiento,datediff(cvc.fecha,now()) as dias from camiones_tbl ca \n"
                    + "inner join choferes_vencimientos_camiones cvc ON cvc.ChoferID = ca.CamionID\n"
                    + "inner join vencimientos_camiones vc ON vc.ID = cvc.vencimientoID\n"
                    + "where ca.status = 1 and datediff(cvc.fecha,now()) < " + diasInt + " and " + camion + " \n"
                    + "cvc.deleted is null and vc.deleted is null and (" + vencimientoID + " = 0 or vc.iD = " + vencimientoID + ") order by dias,NoEconomico desc";

            parametros.put("vencimiento", (boxUnidadVencimientos.getSelectedItem()));

            JRDesignQuery query = new JRDesignQuery();
            query.setText(q);
            String reporto = generarRutaReporte() + "vencimientosUnidades.jrxml";
            JasperDesign jd = JRXmlLoader.load(reporto);
            jd.setQuery(query);
            Connection con = utils.startConnection();
            JasperReport reporte = (JasperReport) JasperCompileManager.compileReport(jd);//(JasperReport) JRLoader.loadObject(new File("Reporte_Transferencias.jasper"));
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, con);
            utils.windowJasper(jasperPrint);
        } catch (JRException ex) {
            Logger.getLogger(VencimientosV3.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Error " + ex + "\n" + ex.getStackTrace()[0], "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        generarReporteRemolquesFijo();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed

//        String selectedRemolque = remolqueId.get(jTable4.getSelectedRow());
        temp.abrirAltaVencimientoRemolque();
        cargarVencimientosRemolque();

        // TODO add your handling code here:
    }//GEN-LAST:event_jButton44ActionPerformed

    private void venRemolquesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venRemolquesActionPerformed
        if (jTable4.getSelectedRow() >= 0) {

            String selectedRemolque = remolqueId.get(jTable4.getSelectedRow());

//            VencimentosLib temp = new VencimentosLib();
            temp.abrirAsignacionPermisosRemo(selectedRemolque);
            cargarVencimientosRemolque();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un camion de la lista.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_venRemolquesActionPerformed

    private void aceptarBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aceptarBtn3ActionPerformed

        String vencimientosIdsConcat = concatenarVencIDRemolque(); //Concatenar los vencimientos seleccionados.

        loadRemolqueModel(vencimientosIdsConcat); // Carga el modelo en base a lo que se selecciono

        cargarTablaRemolqueDinamica(procesarVencimientosRemolque(vencimientosIdsConcat));
        //Se procesan para poder tener las querys de seleccion.
        // Cargar la tabla de forma dinamica.
        cargarConteoDinamicoRemolque(vencimientosIdsConcat);
        // TODO add your handling code here:
    }//GEN-LAST:event_aceptarBtn3ActionPerformed

    private void cancelarBtn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarBtn3ActionPerformed
        boxRemolqueVencimientos.setSelectedIndex(0);
        cargarVencimientosRemolque();
        dFiltroRemolques.dispose();
        boxRemolqueVencimientos.setSelectedIndex(0);
    }//GEN-LAST:event_cancelarBtn3ActionPerformed

    private void GenerarReporteDinamicBtn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarReporteDinamicBtn2ActionPerformed
        generarReporteRemolquesDinamica();        // TODO add your handling code here:
    }//GEN-LAST:event_GenerarReporteDinamicBtn2ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed

        jVencimientosAlmacenes temp = new jVencimientosAlmacenes(this, true, this);

        temp.setLocationRelativeTo(this);
        temp.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void boxPuestoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_boxPuestoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_boxPuestoItemStateChanged

    private void ayuntamientoTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ayuntamientoTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ayuntamientoTxtActionPerformed
    void cargarAltaVencimientosUnidades() {

        String verVencimientoFijos = (global.nivel != 1 ? " and vencimientoFijo = 0" : "");
        String query = "SELECT ID,Vencimiento,dias,notas,campoBD FROM vencimientos_camiones where deleted is null\n"
                + "and ID !=0 " + verVencimientoFijos;
        Connection con;
        con = utils.startConnection();
        mvencimientosAltaUnidad.setRowCount(0);
        vencimientosAltaIdUnidad.clear();

        try {
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                vencimientosAltaIdUnidad.add(rs.getString("ID"));
                mvencimientosAltaUnidad.addRow(new Object[]{rs.getString("Vencimiento"), rs.getString("dias"), rs.getString("notas"), rs.getString("campoBD")});
            }
            con.close();
        } catch (SQLException e) {
            System.out.println("Error alcargar altas vencimientos unidad " + e);
        }

    }

    void eliminarExtintor() {
        utils.dbUpdate("update extintores_tbl set estatus = 0 where extintorId = " + SelectedExtintor);

    }

    void resettAltaExtintor() {
        identificadorTxt.setText("");
        patiosAltaBox.setSelectedIndex(-1);
        vencimientoTxt.setText("");
        capacidadTxt.setText("");
        agenteBox.setSelectedIndex(-1);
        comentarioAltaExtTxt.setText("");
        comboTipoCapacidad.setSelectedIndex(-1);
        SelectedExtintor = 0;

    }

    void cargarExtintorAlta() {

        String query = "select extintorId,ex.identificador ,pa.PatioId as almacen,\n"
                + " DATE_FORMAT(fechaVencimiento, '%d.%m.%Y') as fecha,ex.Capacidad as capacidad,\n"
                + "ex.tipoCapacidad as tipoCap, ae.agenteId as agente\n"
                + ",ex.comentarios  from extintores_tbl ex\n"
                + "inner join patios_tbl pa ON pa.patioId = ex.yardaId\n"
                + "inner join tipoCapacidadExtintor_tbl tc ON ex.tipoCapacidad = tc.tipoId\n"
                + "inner join agenteExtintor_tbl ae ON ae.agenteId = ex.tipoAgente\n"
                + "where extintorId = " + SelectedExtintor;

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                identificadorTxt.setText(rs.getString("identificador"));
                patiosAltaBox.setSelectedIndex(yardaIdAltaExtintor.indexOf(rs.getString("almacen")));
                vencimientoTxt.setText(rs.getString("fecha"));
                capacidadTxt.setText(rs.getString("capacidad"));
                agenteBox.setSelectedIndex(agenteId.indexOf(rs.getString("agente")));
                comentarioAltaExtTxt.setText(rs.getString("comentarios"));
                comboTipoCapacidad.setSelectedIndex(tipoCantidadId.indexOf((rs.getString("tipoCap"))));
            }
        } catch (SQLException e) {
            prt("error al cargar vencimiento unidades tabla dinamicas unidad:" + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }

    }

    void guardarExtintor() {

        String queryInsert = "";
        if (SelectedExtintor == 0 || (String.valueOf(SelectedExtintor).isEmpty())) {
            if (!(capacidadTxt.getText().isEmpty() && identificadorTxt.getText().isEmpty() && vencimientoTxt.getText().isEmpty())) {
                try {

                    float cantidadFloat = Float.valueOf(capacidadTxt.getText());

                    queryInsert = "insert into extintores_tbl \n"
                            + "(extintorId, yardaId, identificador, fechaVencimiento, capacidad, comentarios, tipoCapacidad, tipoAgente)\n"
                            + " values ('0'," + yardaIdAltaExtintor.get(patiosAltaBox.getSelectedIndex()) + ",'" + identificadorTxt.getText() + "','" + utils.dateFromFieldtoDB(vencimientoTxt.getText()) + "',\n"
                            + " " + cantidadFloat + ",'" + comentarioAltaExtTxt.getText() + "'," + tipoCantidadId.get(comboTipoCapacidad.getSelectedIndex()) + "," + agenteId.get(agenteBox.getSelectedIndex()) + ")";
                    String insertadoExito = utils.dbInsert(queryInsert);
                    if (insertadoExito.length() >= 11 || insertadoExito.isEmpty()) {
                        JOptionPane.showMessageDialog(this, "No se inserto correctamente el extintor: " + insertadoExito, "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        resettAltaExtintor();
                        JOptionPane.showMessageDialog(this, "Se ha insertado correctamente el extintor", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Error formato incorrecto para la cantidad: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                }

            } else {
                JOptionPane.showMessageDialog(this, "Error formato incorrecto para la cantidad, el vencimiento o el identificador", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (SelectedExtintor > 0) {
                try {
                    float cantidadFloat = Float.valueOf(capacidadTxt.getText());

                    String queryUpdate = "UPDATE extintores_tbl SET "
                            + "yardaId = " + yardaIdAltaExtintor.get(patiosAltaBox.getSelectedIndex()) + ", "
                            + "identificador = '" + identificadorTxt.getText() + "', "
                            + "fechaVencimiento = '" + utils.dateFromFieldtoDB(vencimientoTxt.getText()) + "', "
                            + "capacidad = " + cantidadFloat + ", "
                            + "comentarios = '" + comentarioAltaExtTxt.getText() + "', "
                            + "tipoCapacidad = " + tipoCantidadId.get(comboTipoCapacidad.getSelectedIndex()) + ", "
                            + "tipoAgente = " + agenteId.get(agenteBox.getSelectedIndex()) + " "
                            + "WHERE extintorId = " + SelectedExtintor;
                    String actualizadoExito = utils.dbUpdate(queryUpdate);
                    if (actualizadoExito.length() >= 11) {
                        JOptionPane.showMessageDialog(this, "Error al actualizar extintor ", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        resettAltaExtintor();
                        JOptionPane.showMessageDialog(this, "Actualizado con exito ", "Exito", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "Error formato incorrecto para la cantidad: " + e, "Error", JOptionPane.ERROR_MESSAGE);
                }
                SelectedExtintor = 0;
            }
        }

    }
//Funcion que modifica la ultima fecha de envio para que sea una fecha
    // muy vieja y el sistema de correos vea que es necesario hacer otro envio

    private void enviarCorreoAhora() {

        /*Se obtienen los textos de cada cuadro, cuerpo, tema y los dias*/
        String fechaReset = "'2020-01-01'";
        // Una fecha muy vieja, para que independientemente del periodo de envio
        // el sistema de correos tome como positivo el enviarlo
        String jefeExiste = utils.dbConsult("SELECT correoPlantillaID from correosPlantilla where tipoCorreoId = 1 and usuarioId = " + global.usuario);
        String adminExiste = utils.dbConsult("SELECT correoPlantillaID from correosPlantilla where tipoCorreoId = 2 and usuarioId = " + global.usuario);

        /*Se comprueba si existen o no el contenido,  en base de datos*/
        if (jefeExiste.isEmpty() && adminExiste.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se tiene configuracion de correos registrado", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (!jefeExiste.isEmpty()) {
                utils.dbUpdate("UPDATE correosPlantilla set  ultimoEnvio =  " + fechaReset + " where correoPlantillaID = " + jefeExiste);
            }
            if (!adminExiste.isEmpty()) {
                utils.dbUpdate("UPDATE correosPlantilla set  ultimoEnvio =  " + fechaReset + " where correoPlantillaID = " + adminExiste + " ");
            }
        }
    }

    // Atajo para imprimir con salto de linea
    void prt(String cadena) {
        if (global.usuario == 1) {
            System.out.println(cadena);
        }
    }

//Esta funcion hace una concatenacion de varios permisos para complementar una consulta
    void activarTablaChoferesDinamica() {
        String vencimientosIdsConcat = concatenarVencIDs(); //bien
        loadChofersModel(vencimientosIdsConcat); //bien
        cargarTablaChoferDinamica(procesarVencimientos(vencimientosIdsConcat));
        cargarConteoDinamico(vencimientosIdsConcat);
    }
//procedimientoUnidadConteo

    public void cargarConteoDinamicoRemolque(String vensId) {
        print(" call procedimientoRemolqueConteo('" + remolqueText.getText() + "','" + vensId + "') ");
        String conteoText = utils.dbConsult(" call procedimientoRemolqueConteo('" + remolqueText.getText() + "','" + vensId + "') ");
        if (!conteoText.equals("0")) {
            RemolqueIncidenteLabel.setText(conteoText);
            RemolqueIncidenteLabel.setBackground(Color.red);
        } else {
            RemolqueIncidenteLabel.setText(conteoText);
            RemolqueIncidenteLabel.setBackground(Color.green);
        }
    }

    void cargarConteoDinamicoUnidad(String vensId) {
        print(" call procedimientoUnidadConteo('" + unidadText.getText() + "','" + vensId + "') ");
        String conteoText = utils.dbConsult(" call procedimientoUnidadConteo('" + unidadText.getText() + "','" + vensId + "') ");
        if (!conteoText.equals("0")) {
            UnidadIncidenteLabel.setText(conteoText);
            UnidadIncidenteLabel.setBackground(Color.red);
        } else {
            UnidadIncidenteLabel.setText(conteoText);
            UnidadIncidenteLabel.setBackground(Color.green);
        }

    }

    void cargarConteoDinamico(String vensId) {
        print(" call procedimientoChoferonteoDinamico('" + jTextField1.getText() + "','" + vensId + "'," + boxPuesto.getSelectedIndex() + ") ");
        String conteoText = utils.dbConsult(" call procedimientoChoferonteoDinamico('" + jTextField1.getText() + "','" + vensId + "'," + boxPuesto.getSelectedIndex() + ") ");
        try {
            if (!conteoText.equals("0")) {
                ChoferIncidenteLabel.setText(conteoText);
                ChoferIncidenteLabel.setBackground(Color.red);
            } else {
                ChoferIncidenteLabel.setText(conteoText);
                ChoferIncidenteLabel.setBackground(Color.green);
            }
        } catch (NullPointerException e) {
            ChoferIncidenteLabel.setText("0");
            ChoferIncidenteLabel.setBackground(Color.green);
        }

    }

    void cargarVencimientosYardas() {
        //boxAyuntamiento
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        vencimientosYardasId.clear();
        boxAyuntamiento.removeAllItems();
        String query = "select permisoAyunId, nombre from permisos_ayuntamiento_tbl where estatus is true and permisoAyunId > 0 and nombre is not null and nombre != ''";

        vencimientosYardasId.add("0");
        boxAyuntamiento.addItem("Todos");

        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                vencimientosYardasId.add(rs.getString("permisoAyunId"));
                boxAyuntamiento.addItem(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar box vencimientos ayuntamiento: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    private String procesarVencimientosYardas(String vencimientoIdVar) {
        String todosVencimientos = "";
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String resultado = "";
        try {

            if (vencimientoIdVar.isEmpty() || vencimientoIdVar.equals("0")) {
                vencimientoIdVar = "0";
                todosVencimientos = vencimientoIdVar;
            } else {
                todosVencimientos = "1";
            }
            String query = "SELECT CONCAT(\"GROUP_CONCAT(IF(py.Nombre = '\" , nombre , \"', DATE_FORMAT(pv.FechaVencimiento, '%d.%m.%Y'), null)) as '\" , nombre , \"'\") as permiso \n"
                    + " FROM permisos_ayuntamiento_tbl\n"
                    + "WHERE estatus IS TRUE AND permisoAyunId != 0 AND nombre IS NOT NULL AND nombre != ''\n"
                    + "AND (" + todosVencimientos + " = 0 OR permisoAyunId IN (" + vencimientoIdVar + ")) order by permisoAyunId asc";

            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);
            while (rs.next()) {
                resultado = resultado + rs.getString("permiso") + ",";

            }
            resultado = removerUltimaComa(resultado);

//         resultado = utils.dbConsult(query);
            print("Query a resultado: " + resultado);
            print("Query a revisar: " + query);
            return resultado;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar vencimientos concatenados yarda: " + e);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
        return "";
    }

    private String procesarVencimientosRemolque(String vencimientoIdVar) {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String query = "", resultado = "", invocarTodosVencimientos = "";

        boolean esSoloUnVencimiento = (vencimientoIdVar.isEmpty() || vencimientoIdVar.equals("0"));

        if (esSoloUnVencimiento) {
            vencimientoIdVar = "0";
            invocarTodosVencimientos = vencimientoIdVar;
        } else {
            invocarTodosVencimientos = "1";
        }

        query = "SELECT CONCAT('GROUP_CONCAT(IF(cv.VencimientoId = ', ID, ', DATE_FORMAT(cv.fecha, ''%d.%m.%Y''), null)) as ', \n"
                + " CASE WHEN LENGTH(vencimiento) > 0 THEN CONCAT('`', vencimiento, '`') ELSE 'vencimiento_sin_nombre' END)\n"
                + " AS resultado \n"
                + "FROM vencimientos_remolques  \n"
                + "WHERE deleted IS NULL AND ((" + invocarTodosVencimientos + " = 0) OR (ID in (" + vencimientoIdVar + "))) AND vencimiento is not null and vencimiento != '' \n"
                + " ORDER BY ID ASC;";

        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {

                resultado = resultado + rs.getString("resultado") + ",";

            }

            resultado = removerUltimaComa(resultado);

        } catch (SQLException e) {

            prt("Error en procesar vencimientos remolques: " + e);

        } finally {

            utils.closeAllConnections(con, st, rs);

        }

        return resultado;
    }

    private String removerUltimaComa(String valorStr) {
        if (valorStr.isEmpty()) {
            return "";
        }

        return valorStr.substring(0, valorStr.length() - 1);

    }

    private String procesarVencimientosUnidad(String vencimientoIdVar) {

        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "";
        String resultado = "";
        String todosVencimientos = "";
        if (vencimientoIdVar.isEmpty() || vencimientoIdVar.equals("0")) {
            vencimientoIdVar = "0";
            todosVencimientos = vencimientoIdVar;
        } else {
            todosVencimientos = "1";
        }
        query = "SELECT CONCAT('GROUP_CONCAT(IF(cv.VencimientoId = ', ID, ', DATE_FORMAT(cv.fecha, ''%d.%m.%Y''), null)) as ', \n"
                + " CASE WHEN LENGTH(vencimiento) > 0 THEN CONCAT('`', vencimiento, '`') ELSE 'vencimiento_sin_nombre' END)\n"
                + " AS resultado \n"
                + "FROM vencimientos_camiones  \n"
                + "WHERE deleted IS NULL AND ((" + todosVencimientos + " = 0) OR (ID in (" + vencimientoIdVar + "))) AND vencimiento is not null and vencimiento != '' \n"
                + " ORDER BY ID ASC;";
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                resultado = resultado + rs.getString("resultado") + ",";
            }

            resultado = quitarComaTxt(resultado);

        } catch (SQLException e) {

            prt("Error al concatenar vencimientos de unidades: " + e);
            prt("////////////////////////////");
            prt("QUERY DEL ERROR: " + query);
            prt("////////////////////////////");

        } finally {
            utils.closeAllConnections(con, st, rs);
        }
        return resultado;
    }

    private String quitarComaTxt(String valor) {
        try {
            if (valor.isEmpty()) {
                return "";
            }
        } catch (NullPointerException e) {
            return "";
        }
        return valor.substring(0, valor.length() - 1);
    }

    private String procesarVencimientos(String vencimientoIdVar) {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        String query = "";
        String resultado = "";
        String todosVencimientos = "";
        if (vencimientoIdVar.isEmpty() || vencimientoIdVar.equals("0")) {
            vencimientoIdVar = "0";
            todosVencimientos = vencimientoIdVar;
        } else {
            todosVencimientos = "1";
        }
        query = "SELECT CONCAT('GROUP_CONCAT(IF(cv.VencimientoId = ', ID, ', DATE_FORMAT(cv.fecha, ''%d.%m.%Y''), null)) as ', \n"
                + " CASE WHEN LENGTH(vencimiento) > 0 THEN CONCAT('`', vencimiento, '`') ELSE 'vencimiento_sin_nombre' END)\n"
                + " AS resultado \n"
                + "FROM vencimientos  \n"
                + "WHERE deleted IS NULL AND ((" + todosVencimientos + " = 0) OR (ID in (" + vencimientoIdVar + "))) AND vencimiento is not null and vencimiento != '' \n"
                + " ORDER BY ID ASC;";
        try {
            con = utils.startConnection();
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                resultado = resultado + rs.getString("resultado") + ",";
            }
            resultado = resultado.substring(0, resultado.length() - 1);

        } catch (SQLException e) {
            prt("Error al concatenar vencimientos de unidades: " + e);
            prt("////////////////////////////");
            prt("QUERY DEL ERROR: " + query);
            prt("////////////////////////////");

        } finally {
            utils.closeAllConnections(con, st, rs);
        }
        return resultado;
    }

    void print(String cadena) {
        System.out.println(cadena);
    }

    private String concatenarVencIDs() {
        int numeroVencimientos = tablaFiltroChofer.getRowCount(); // 5
        String listaVencimientos = "";
        if (numeroVencimientos > 1) {
            int i = 1;
            while (i <= (numeroVencimientos)) {
                if ((boolean) tablaFiltroChofer.getValueAt(i - 1, 1)) {

                    listaVencimientos = listaVencimientos + vencimientoChoferId.get(i - 1) + ",";
//                    prt(listaVencimientos);
                }
                i++;
            }
            if (!listaVencimientos.isEmpty()) {
                listaVencimientos = listaVencimientos.substring(0, listaVencimientos.length() - 1);
//            listaVencimientos = listaVencimientos + (((boolean) tablaFiltroUnidad.getValueAt(i - 1, 1)) ? vencimientoChoferId.get(i - 1) : ' ');
            } else {
                listaVencimientos = "0";
            }
            System.out.println(listaVencimientos);

        } else if (numeroVencimientos == 1) {
            if ((boolean) tablaFiltroChofer.getValueAt(0, 1)) {
                listaVencimientos = vencimientoChoferId.get(0);
            } else {
                listaVencimientos = "0";
            }
        }
        return (listaVencimientos);
    }

    private void cargarPermisosRemolques() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        vencimientosRemolqueDina.clear();
        tablemodelVencimientoRemolque.setRowCount(0);
        String query = "select ID,vencimiento from vencimientos_remolques where ID != 0 and deleted is null \n"
                + "and (vencimiento is not null and vencimiento != '') ORDER BY ID DESC";

        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                vencimientosRemolqueDina.add(rs.getString("ID"));
                tablemodelVencimientoRemolque.addRow(new Object[]{rs.getString("vencimiento"), false});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tabla vencimientos de remolques: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    private void cargarPermisosUnidades() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        vencimientosUnidadDina.clear();
        tablemodelVencimientoUnidad.setRowCount(0);
        String query = "select ID,vencimiento from vencimientos_camiones where ID != 0 and deleted is null \n"
                + "and (vencimiento is not null and vencimiento != '') ORDER BY ID DESC";

        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                vencimientosUnidadDina.add(rs.getString("ID"));
                tablemodelVencimientoUnidad.addRow(new Object[]{rs.getString("vencimiento"), false});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tabla vencimientos: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

    private void cargarPermisosChoferes() {
        Connection con = null;
        Statement st = null;
        ResultSet rs = null;
        vencimientoChoferId.clear();
        tablemodelVencimientoChofer.setRowCount(0);
        String query = "select ID,vencimiento from vencimientos where ID != 0 and deleted is null and (vencimiento is not null and vencimiento != '') ORDER BY ID DESC";

        con = utils.startConnection();
        try {
            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                vencimientoChoferId.add(rs.getString("ID"));
                tablemodelVencimientoChofer.addRow(new Object[]{rs.getString("vencimiento"), false});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al cargar tabla vencimientos: " + e, "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            utils.closeAllConnections(con, st, rs);
        }
    }

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
            java.util.logging.Logger.getLogger(VencimientosV3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VencimientosV3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VencimientosV3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VencimientosV3.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VencimientosV3().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel ChoferIncidenteLabel;
    private javax.swing.JMenuItem EditarExt;
    private javax.swing.JMenuItem EliminarExt;
    private javax.swing.JButton GenerarReporteBitacora;
    private javax.swing.JButton GenerarReporteDinamicBtn;
    private javax.swing.JButton GenerarReporteDinamicBtn1;
    private javax.swing.JButton GenerarReporteDinamicBtn2;
    private javax.swing.JLabel RemolqueIncidenteLabel;
    private javax.swing.JButton ReportesBtn;
    private javax.swing.JButton ReportesBtn1;
    private javax.swing.JLabel UnidadIncidenteLabel;
    private javax.swing.JButton aceptarBtn;
    private javax.swing.JButton aceptarBtn1;
    private javax.swing.JButton aceptarBtn2;
    private javax.swing.JButton aceptarBtn3;
    private javax.swing.JComboBox<String> agenteBox;
    private javax.swing.JTextField ayuntamientoTxt;
    private javax.swing.JTextArea bodyAdmin;
    private javax.swing.JTextArea bodyJefe;
    private javax.swing.JComboBox boxAyuntamiento;
    private javax.swing.JComboBox boxPuesto;
    private javax.swing.JComboBox boxRemolqueVencimientos;
    private javax.swing.JComboBox boxUnidadVencimientos;
    private javax.swing.JComboBox boxVencimientosChoferes;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JButton cancelarBtn1;
    private javax.swing.JButton cancelarBtn2;
    private javax.swing.JButton cancelarBtn3;
    private javax.swing.JTextField capacidadTxt;
    private javax.swing.JCheckBox checkCorreoAdmin;
    private javax.swing.JCheckBox checkCorreoJefes;
    private javax.swing.JComboBox<String> comboCorreoSaliente;
    private javax.swing.JComboBox<String> comboMes;
    private javax.swing.JComboBox<String> comboTipoCapacidad;
    private javax.swing.JComboBox<String> comboTipoVencimientos;
    private javax.swing.JComboBox<String> comboYear;
    private javax.swing.JTextArea comentarioAltaExtTxt;
    private javax.swing.JTextField correoDiaAdmin;
    private javax.swing.JTextField correoDiaJefe;
    private javax.swing.JDialog dAltaExtintor;
    private javax.swing.JDialog dAltaVencimientos;
    private javax.swing.JDialog dAltaVencimientosUnidad;
    private javax.swing.JDialog dBitacoraExtintor;
    private javax.swing.JDialog dCorreoEnvio;
    private javax.swing.JDialog dFiltroChoferes;
    private javax.swing.JDialog dFiltroRemolques;
    private javax.swing.JDialog dFiltroUnidades;
    private javax.swing.JDialog dVencimientosAlmacenes;
    private javax.swing.JDialog dVencimientosChofer;
    private javax.swing.JDialog dVencimientosUnidad;
    private javax.swing.JTextField diasExt;
    private javax.swing.JTextField diasRemolqueTxt;
    private javax.swing.JTextField diasUnidadTxt;
    private javax.swing.JButton guardarExtBtn;
    private javax.swing.JTextField identificadorTxt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JButton jButton95;
    private javax.swing.JButton jButton96;
    private javax.swing.JButton jButton97;
    private javax.swing.JButton jButton98;
    private javax.swing.JButton jButton99;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel63;
    private javax.swing.JPanel jPanel64;
    private javax.swing.JPanel jPanel65;
    private javax.swing.JPanel jPanel66;
    private javax.swing.JPanel jPanel67;
    private javax.swing.JPanel jPanel68;
    private javax.swing.JPanel jPanel69;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel70;
    private javax.swing.JPanel jPanel71;
    private javax.swing.JPanel jPanel72;
    private javax.swing.JPanel jPanel73;
    private javax.swing.JPanel jPanel74;
    private javax.swing.JPanel jPanel75;
    private javax.swing.JPanel jPanel76;
    private javax.swing.JPanel jPanel77;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane19;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane20;
    private javax.swing.JScrollPane jScrollPane21;
    private javax.swing.JScrollPane jScrollPane22;
    private javax.swing.JScrollPane jScrollPane23;
    private javax.swing.JScrollPane jScrollPane24;
    private javax.swing.JScrollPane jScrollPane25;
    private javax.swing.JScrollPane jScrollPane29;
    private javax.swing.JScrollPane jScrollPane30;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable5;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JPopupMenu menuAlmacenPermisos;
    private javax.swing.JPopupMenu menuChoferPermisos;
    private javax.swing.JPopupMenu menuRemolques;
    private javax.swing.JPopupMenu menuUnidades;
    private javax.swing.JTextField nombreReviso;
    private javax.swing.JPanel panelChoferes;
    private javax.swing.JPanel panelExtintores;
    private javax.swing.JPanel panelRemolques;
    private javax.swing.JPanel panelUnidades;
    private javax.swing.JPanel panelYardas;
    private javax.swing.JComboBox<String> patiosAltaBox;
    private javax.swing.JTextField remolqueText;
    private javax.swing.JTextField subjectAdmin;
    private javax.swing.JTextField subjectJefe;
    private javax.swing.JTable tablaAyuntamiento;
    private javax.swing.JTable tablaBitacoraExt;
    private javax.swing.JTable tablaFiltroChofer;
    private javax.swing.JTable tablaFiltroRemolque;
    private javax.swing.JTable tablaFiltroUnidad1;
    private javax.swing.JTable tablaVencimientos;
    private javax.swing.JComboBox tipoPlantilla;
    private javax.swing.JTable tvencimientos;
    private javax.swing.JTable tvencimientosAltaUnidad;
    private javax.swing.JTable tvencimientosP;
    private javax.swing.JTable tvencimientosP2;
    private javax.swing.JTable tvencimientosPorYarda;
    private javax.swing.JTable tvencimientoschofer;
    private javax.swing.JTable tvencimientosunidad;
    private javax.swing.JTextArea txtCorreoAdmin;
    private javax.swing.JTextArea txtCorreoJefes;
    private javax.swing.JTextField unidadText;
    private javax.swing.JMenuItem venAyuntamiento;
    private javax.swing.JMenuItem venChofer;
    private javax.swing.JMenuItem venRemolques;
    private javax.swing.JMenuItem venUnidades;
    private com.alee.extended.date.WebDateField vencimientoTxt;
    // End of variables declaration//GEN-END:variables
}
