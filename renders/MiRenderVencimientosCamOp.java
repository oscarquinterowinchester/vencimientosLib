/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package renders;
import java.awt.Color;
import java.awt.Component;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author linelle
 */
public class MiRenderVencimientosCamOp extends DefaultTableCellRenderer {

    String temp;
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    Date horaS, horaAc, hora30, hora10;
    int colToColor; // Nueva variable para almacenar la columna a colorear

    public MiRenderVencimientosCamOp(int colToColor) {
        this.colToColor = colToColor;
    }
public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
        boolean hasFocus, int row, int col) {

    JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
    Calendar calendario = new GregorianCalendar();

    try {
        if (col == colToColor) { // Solo aplica el formato si estamos en la columna deseada
            temp = (String) value;
            if (temp != null && !temp.equals("")) {
                horaS = dateFormat.parse(temp);
                horaAc = new Date(); // Obtenemos la fecha actual
                long diferenciaEnMilisegundos = horaS.getTime() - horaAc.getTime();
                long diferenciaEnDias = diferenciaEnMilisegundos / (1000 * 60 * 60 * 24);
                if (diferenciaEnDias < 0) {
                    cell.setBackground(Color.red); // Si es menor a 0, la celda es roja
                } else if (diferenciaEnDias < 30) {
                    cell.setBackground(Color.orange); // Si es menor a 30 pero mayor o igual a 0, la celda es naranja
                } else {
                    cell.setBackground(new Color(55, 162, 255)); // Si es mayor a 30, la celda es azul
                }
            } else {
                if (isSelected) {
                    setBackground(table.getSelectionBackground());
                    setForeground(table.getSelectionForeground());
                } else {
                    setBackground(table.getBackground());
                    setForeground(table.getForeground());
                }
            }
        } else {
            if (isSelected) {
                setBackground(table.getSelectionBackground());
                setForeground(table.getSelectionForeground());
            } else {
                setBackground(table.getBackground());
                setForeground(table.getForeground());
            }
        }
    } catch (ParseException parseException) {
        parseException.printStackTrace();
    }

    return cell;
}
}
//    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
//            boolean hasFocus, int row, int col) {
//
//        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
//        Calendar calendario = new GregorianCalendar();
//
//        try {
//            if (col == colToColor) { // Solo aplica el formato si estamos en la columna deseada
//                temp = (String) value;
//                if (temp != null && !temp.equals("")) {
//                    horaS = dateFormat.parse(temp);
//                    horaAc = dateFormat.parse(calendario.get(Calendar.DAY_OF_MONTH) + "." + (calendario.get(Calendar.MONTH) + 1) + "." + calendario.get(Calendar.YEAR));
//                    calendario.add(Calendar.DAY_OF_MONTH, 15);
//                    hora10 = dateFormat.parse(calendario.get(Calendar.DAY_OF_MONTH) + "." + (calendario.get(Calendar.MONTH) + 1) + "." + calendario.get(Calendar.YEAR));
//                    calendario.add(Calendar.DAY_OF_MONTH, 30);
//                    hora30 = dateFormat.parse(calendario.get(Calendar.DAY_OF_MONTH) + "." + (calendario.get(Calendar.MONTH) + 1) + "." + calendario.get(Calendar.YEAR));
//                    if (horaS.compareTo(horaAc) >= 0) {
//                        cell.setBackground(new Color(55, 162, 255));
//                        if (hora30.compareTo(horaS) >= 0) {
//                            cell.setBackground(Color.orange);
//                        }
//                        if (hora10.compareTo(horaS) == 0) {
//                            cell.setBackground(Color.red);
//                        }
//                    } else {
//                        cell.setBackground(Color.red);
//                    }
//                } else {
//                    if (isSelected) {
//                        setBackground(table.getSelectionBackground());
//                        setForeground(table.getSelectionForeground());
//                    } else {
//                        setBackground(table.getBackground());
//                        setForeground(table.getForeground());
//                    }
//                }
//            } else {
//                if (isSelected) {
//                    setBackground(table.getSelectionBackground());
//                    setForeground(table.getSelectionForeground());
//                } else {
//                    setBackground(table.getBackground());
//                    setForeground(table.getForeground());
//                }
//            }
//        } catch (ParseException parseException) {
//            parseException.printStackTrace();
//        }
//
//        return cell;
//    }
//}



