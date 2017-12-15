package controller;

import com.mysql.jdbc.Connection;
import javafx.fxml.Initializable;
import modelo.Conexion;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.mysql.jdbc.Connection;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Conexion;
import dba.DBConnection;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.common.IOUtil;
public class excel implements Initializable {
    private DBConnection conexion;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            crearExcel();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
     public static void crearExcel() throws ClassNotFoundException {
//         Workbook boook = new XSSFWorkbook();
//         Sheet sheet = boook.createSheet("Hola Java");
//
//         Row row = sheet.createRow(0);
//         row.createCell(0).setCellValue("Hola Mundo");
//         row.createCell(1).setCellValue(4.5);
//         row.createCell(2).setCellValue(true);
//
//         Cell cell = row.createCell(3);
//         cell.setCellFormula(String.format("1+1", ""));
//
//         Row row1 = sheet.createRow(1);
//         row1.createCell(0).setCellValue(7);
//         row1.createCell(1).setCellValue(8);
//
//         Cell cell1 = row1.createCell(2);
//         cell1.setCellFormula(String.format("A%d+B%d",2,2));
//
//
//         try {
//             FileOutputStream fileout = new FileOutputStream("Excel.xlsx");
//             boook.write(fileout);
//             fileout.close();
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
//     }
//
//     public static void leer(){
//         try {
//             FileInputStream file = new FileInputStream(new File(""));
//
//             XSSFWorkbook wb = new XSSFWorkbook(file);
//             XSSFSheet sheet = wb.getSheetAt(0);
//
//             int numFilas = sheet.getLastRowNum();
//
//             for (int a = 0; a < numFilas; a++){
//                 Row fila = sheet.getRow(a);
//                 int numCols = fila.getLastCellNum();
//
//                 for (int b = 0; b < numCols; b++){
//                     Cell cell = fila.getCell(b);
//                 }
//             }
//         } catch (FileNotFoundException e) {
//             e.printStackTrace();
//         } catch (IOException e) {
//             e.printStackTrace();
//         }
         Workbook book = new XSSFWorkbook();
         Sheet sheet = book.createSheet("Productos");

         try {
//             InputStream is = new FileInputStream("src\\icon\\logo.png");
//             byte[] bytes = IOUtils.toByteArray(is);
//             int imgIndex = book.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
//             is.close();
//
//             CreationHelper help = book.getCreationHelper();
//             Drawing draw = sheet.createDrawingPatriarch();
//
//             ClientAnchor anchor = help.createClientAnchor();
//             anchor.setCol1(0);
//             anchor.setRow1(1);
//             Picture pict = draw.createPicture(anchor, imgIndex);
//             pict.resize(1, 3);

             CellStyle tituloEstilo = book.createCellStyle();
             tituloEstilo.setAlignment(HorizontalAlignment.CENTER);
             tituloEstilo.setVerticalAlignment(VerticalAlignment.CENTER);
             Font fuenteTitulo = book.createFont();
             fuenteTitulo.setFontName("Arial");
             fuenteTitulo.setBold(true);
             fuenteTitulo.setFontHeightInPoints((short) 14);
             tituloEstilo.setFont(fuenteTitulo);

             Row filaTitulo = sheet.createRow(1);
             Cell celdaTitulo = filaTitulo.createCell(1);
             celdaTitulo.setCellStyle(tituloEstilo);
             celdaTitulo.setCellValue("Reporte de Citas");

             sheet.addMergedRegion(new CellRangeAddress(1, 2, 1, 3));

             String[] cabecera = new String[]{"Código", "Nombre", "Precio", "Existencia", "Importe"};

             CellStyle headerStyle = book.createCellStyle();
             headerStyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
             headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
             headerStyle.setBorderBottom(BorderStyle.THIN);
             headerStyle.setBorderLeft(BorderStyle.THIN);
             headerStyle.setBorderRight(BorderStyle.THIN);
             headerStyle.setBorderBottom(BorderStyle.THIN);

             Font font = book.createFont();
             font.setFontName("Arial");
             font.setBold(true);
             font.setColor(IndexedColors.WHITE.getIndex());
             font.setFontHeightInPoints((short) 12);
             headerStyle.setFont(font);

             Row filaEncabezados = sheet.createRow(4);

             for (int i = 0; i < cabecera.length; i++) {
                 Cell celdaEnzabezado = filaEncabezados.createCell(i);
                 celdaEnzabezado.setCellStyle(headerStyle);
                 celdaEnzabezado.setCellValue(cabecera[i]);
             }

//             Conexion con = new Conexion();
//             PreparedStatement ps;
//             ResultSet rs;
//             Connection conn = con.getConexion();

             int numFilaDatos = 5;

             CellStyle datosEstilo = book.createCellStyle();
             datosEstilo.setBorderBottom(BorderStyle.THIN);
             datosEstilo.setBorderLeft(BorderStyle.THIN);
             datosEstilo.setBorderRight(BorderStyle.THIN);
             datosEstilo.setBorderBottom(BorderStyle.THIN);
             Connection con = (Connection) DBConnection.getConnection();
             Statement stnt = con.createStatement();
             String sql = "SELECT idPacientes, nombrePaciente, apellidoPaciente, genero FROM pacientes";
             ResultSet resultado = stnt.executeQuery(sql);
//             ps = conn.prepareStatement("SELECT idPacientes, nombrePaciente, apellidoPaciente, genero FROM pacientes");
//             rs = ps.executeQuery();

             int numCol = resultado.getMetaData().getColumnCount();

             while (resultado.next()) {
                 Row filaDatos = sheet.createRow(numFilaDatos);

                 for (int a = 0; a < numCol; a++) {

                     Cell CeldaDatos = filaDatos.createCell(a);
                     CeldaDatos.setCellStyle(datosEstilo);

                     if (a == 2 || a == 3) {
                         CeldaDatos.setCellValue(resultado.getDouble(a + 1));
                     } else {
                         CeldaDatos.setCellValue(resultado.getString(a + 1));
                     }
                 }

                 Cell celdaImporte = filaDatos.createCell(4);
                 celdaImporte.setCellStyle(datosEstilo);
                 celdaImporte.setCellFormula(String.format("C%d+D%d", numFilaDatos + 1, numFilaDatos + 1));

                 numFilaDatos++;

             }

             sheet.autoSizeColumn(0);
             sheet.autoSizeColumn(1);
             sheet.autoSizeColumn(2);
             sheet.autoSizeColumn(3);
             sheet.autoSizeColumn(4);

             sheet.setZoom(150);

             FileOutputStream fileOut = new FileOutputStream("ReporteCitas.xlsx");
             book.write(fileOut);
             fileOut.close();

         } catch (FileNotFoundException e) {
             e.printStackTrace();


         } catch (IOException e) {
             e.printStackTrace();


         } catch (SQLException e) {
             e.printStackTrace();
         }
     }

}
