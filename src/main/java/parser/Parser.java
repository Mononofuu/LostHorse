package parser;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

public class Parser {
    public static int[][] parse() throws IOException {
        int matrix[][] = new int[0][0];

        String fileName = "c:\\Temp\\horse.xlsx";
        try (InputStream inputStream = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheetAt(0);
            int lastRowNum = sheet.getLastRowNum();
            int lastCellNum = sheet.getRow(sheet.getLastRowNum()).getLastCellNum();
            int xDiff = 0;
            int yDiff = 0;

            Iterator<Row> rows = sheet.rowIterator();
            boolean inRange = false;
            while (rows.hasNext()) {
                Row row = rows.next();
                Iterator<Cell> cells = row.cellIterator();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    int y = cell.getRowIndex() - yDiff;
                    int x = cell.getColumnIndex() - xDiff;
                    if (!inRange) {
                        short borderLeft = cell.getCellStyle().getBorderLeft();
                        short borderTop = cell.getCellStyle().getBorderTop();

                        if (borderLeft + borderTop == 4) {
                            yDiff = cell.getRowIndex();
                            xDiff = cell.getColumnIndex();
                            matrix = new int[lastRowNum - yDiff + 1][lastCellNum - xDiff];
                            inRange = true;
                            if ("s".equals(cell.getStringCellValue())) {
                                y = cell.getRowIndex() - yDiff;
                                x = cell.getColumnIndex() - xDiff;
                                matrix[y][x] = -2;
                            }
                        }
                    } else {
                        if (cell.getCellStyle().getFillPattern() == 1) {
                            matrix[y][x] = -1;
                        }
                        if ("s".equals(cell.getStringCellValue())) {
                            matrix[y][x] = -2;
                        }
                        if ("f".equals(cell.getStringCellValue())) {
                            matrix[y][x] = -3;
                        }
                    }
                }
            }
        }
        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int column : row) {
                String cell = String.valueOf(column);
                if (cell.length() == 1) {
                    cell = " " + cell;
                }
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) throws IOException {
        printMatrix(parse());
    }
}
