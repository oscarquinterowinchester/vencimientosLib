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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author user
 */
public class MiRenderVencimientos extends DefaultTableCellRenderer {

    String temp;
    String[] stringsplited = new String[3];
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
    Date horaS, horaAc, hora30, hora10;
    int num;
     LocalDateTime hoy = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String fechaHoy = hoy.format(formato);
        LocalDateTime fecha30DiasDespues = hoy.plusDays(30);
        String fecha30DiasDespuesFormateada = fecha30DiasDespues.format(formato);
        LocalDate dia30LocalDate = LocalDate.parse(fecha30DiasDespuesFormateada, formato);
    

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int col) {

        JLabel cell = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        Calendar calendario = new GregorianCalendar();

        try {

            if (col >= 1) {
                temp = (String) value;
                if (temp != null && !temp.equals("")) {
                    LocalDate fechaCelda = LocalDate.parse(temp, formato);
                    LocalDate fechaHoyLocalDate = LocalDate.parse(fechaHoy, formato);
                    if( fechaCelda.isBefore(dia30LocalDate)){
                        if(fechaCelda.isBefore(fechaHoyLocalDate))
                        {
                          cell.setBackground(Color.red);  
                        }else{
                        cell.setBackground(Color.orange);
                        }
                    }else if(fechaCelda.isAfter(dia30LocalDate) || fechaCelda.equals(dia30LocalDate))
                    {
                       cell.setBackground(new Color(55, 162, 255)); 
                    }
                    
                    //stringsplited = temp.split(":");
//                    horaS = dateFormat.parse(temp);
//                    horaAc = dateFormat.parse(calendario.get(Calendar.DAY_OF_MONTH) + "." + (calendario.get(Calendar.MONTH) + 1) + "." + calendario.get(Calendar.YEAR));
//                    calendario.add(Calendar.DAY_OF_MONTH, 15);
//                    hora10 = dateFormat.parse(calendario.get(Calendar.DAY_OF_MONTH) + "." + (calendario.get(Calendar.MONTH) + 1) + "." + calendario.get(Calendar.YEAR));
//                    calendario.add(Calendar.DAY_OF_MONTH, 30);
//                    hora30 = dateFormat.parse(calendario.get(Calendar.DAY_OF_MONTH) + "." + (calendario.get(Calendar.MONTH) + 1) + "." + calendario.get(Calendar.YEAR));
//
//                    if (horaS.compareTo(horaAc) >= 0) {//ahorita es prueba, pero sera aqui donde checo cuanto tiempo falta y de faltar poco tiempo se pintaran las celdas
//
//                        cell.setBackground(new Color(55, 162, 255));
//                        if (horaS.compareTo(horaAc) >= 0 && horaS.compareTo(hora30) < 0) {
//                            cell.setBackground(Color.orange);
//                        }
////                        if (hora10.compareTo(horaS) == 0) {
////                            cell.setBackground(Color.red);
////
////                        }
//                    } else {
//                        cell.setBackground(Color.red);
//                    }
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
        }catch (DateTimeParseException e) {
    System.err.println("Error al parsear la fecha de la celda: " + e.getMessage());
    // Manejar el error según sea necesario
}
//        } catch (ParseException parseException) {
//            parseException.printStackTrace();
//        }

        return cell;
    }

}
