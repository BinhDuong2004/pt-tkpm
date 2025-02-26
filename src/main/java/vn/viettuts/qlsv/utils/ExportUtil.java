package vn.viettuts.qlsv.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ExportUtil {

    public static <T> void export(List<T> list) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Excel File");
        fileChooser.setSelectedFile(new File("data.xlsx"));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel Files", "xlsx");
        fileChooser.setFileFilter(filter);

        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            String filePath = fileToSave.getAbsolutePath();
            if (!filePath.endsWith(".xlsx")) {
                filePath += ".xlsx";
            }

            try {
                exportToExcel(list, filePath);
                JOptionPane.showMessageDialog(null, "Xuất file thành công!");
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Xuất file thất bại!");
            }
        }
    }

    public static <T> void exportToExcel(List<T> dataList, String filePath) {
        Workbook workbook = new XSSFWorkbook();
        try {
            Sheet sheet = workbook.createSheet("Data");

            if (!dataList.isEmpty()) {
                T firstObject = dataList.get(0);

                // Create header row
                Row headerRow = sheet.createRow(0);
                Field[] fields = firstObject.getClass().getDeclaredFields();
                for (int i = 0; i < fields.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(fields[i].getName());
                }

                // Fill data rows
                for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
                    Row dataRow = sheet.createRow(rowIndex + 1);
                    T obj = dataList.get(rowIndex);
                    for (int cellIndex = 0; cellIndex < fields.length; cellIndex++) {
                        Field field = fields[cellIndex];
                        field.setAccessible(true);
                        Object value = field.get(obj);
                        Cell cell = dataRow.createCell(cellIndex);
                        if (value != null) {
                            if (value instanceof String) {
                                cell.setCellValue((String) value);
                            } else if (value instanceof Number) {
                                cell.setCellValue(((Number) value).doubleValue());
                            } else if (value instanceof Boolean) {
                                cell.setCellValue((Boolean) value);
                            } else if (value instanceof Calendar){
                                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                cell.setCellValue(sdf.format(((Calendar) value).getTime()));
                            }
                            else {
                                cell.setCellValue(value.toString());
                            }
                        }
                    }
                }
            }

            try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
                workbook.write(outputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
