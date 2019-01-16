package com.ss.crm.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import static org.apache.poi.ss.usermodel.Cell.*;

public class ExcelToCsv {


    /*目标文件文件夹*/
    private String targetFolder = null;
    /*日期格式*/
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    /*输出流*/
    private BufferedWriter bw = null;
    /*文件输入流*/
    private FileInputStream fis = null;
    /*源文件地址*/
    private String src;
    /*目标文件地址*/
    private String targetFile;

    public ExcelToCsv() {

    }

    public ExcelToCsv(String src, String targetFolder) {
        this.src = src;
        this.targetFolder = targetFolder;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(date);
        this.targetFile = targetFolder.concat(format).concat(".csv");
    }

    public List<Integer> getNeededColumnIndex(List<String> columnTitleList, int sheetIndex, int sheetTitleIndex) {
        List<Integer> NeededColumnIndexList = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(new FileInputStream(src));
            final Sheet sheet = workbook.getSheetAt(sheetIndex);
            Row row = sheet.getRow(sheetTitleIndex);
            if (row == null) {
                return null;
            } else {
                for (int i = 0; i < columnTitleList.size(); i++) {
                    String columnTitle = columnTitleList.get(i);
                    for (int j = 0; j < row.getLastCellNum(); j++) {
                        getCellIndex(sheetTitleIndex, j);
                        final Cell cell = row.getCell(j);
                        // 判断空格和类型
                        if (cell.getCellType() != CELL_TYPE_STRING) {
                            continue;
                        }
                        final String originCellTitle = cell.getStringCellValue();
                        System.out.println(originCellTitle);
                        if (originCellTitle.equals(columnTitle)) {
                            NeededColumnIndexList.add(j);
                        }
                    }


                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return NeededColumnIndexList;
    }

    /*传入一个excel文件地址将其转化为指定目录下的csv文件*/
    public String excelToCSV(int beginRowIndex, int sheetIndex, List<Integer> columnIndexList) {

        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream(src));
            final Sheet sheet = workbook.getSheetAt(sheetIndex);
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
            System.out.println("多少行 ： " + sheet.getLastRowNum());
            for (int i = beginRowIndex; i < sheet.getLastRowNum() + 1; i++) {
                final Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                if (columnIndexList == null) {
                    writeFullRow(row, i);
                } else {
                    writeSpicifyColumn(row,i,columnIndexList);
                }

                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();

        } finally {
            close(bw, fis);
        }
        return targetFile;
    }

    /*完整的写指定索引列的一整行*/
    private void writeFullRow(Row row, Integer Rowindex) throws IOException {
        for (int columnIndex = 0; columnIndex < row.getLastCellNum(); columnIndex++) {
            getCellIndex(Rowindex, columnIndex);
            final Cell cell = row.getCell(columnIndex);
            // 判断空格和类型
            if (cell != null) {
                checkTypeAndWriteCell(cell, bw);
            } else {
                System.out.println("空");
            }
            bw.write(",");
        }
    }

    /*完整的写指定索引列的一整行*/
    private void writeSpicifyColumn(Row row, Integer Rowindex, List<Integer> indexList) throws IOException {
        int i=0;
        Iterator<Integer> iterator = indexList.iterator();
        while (iterator.hasNext()) {
            getCellIndex(Rowindex,i++);
            final Cell cell = row.getCell(iterator.next());
            // 判断空格和类型
            if (cell != null) {
                checkTypeAndWriteCell(cell, bw);
            } else {
                System.out.println("空");
            }
            bw.write(",");
        }

    }


    /*判断单元格类型并写出指定单元格数据*/
    private void checkTypeAndWriteCell(Cell cell, BufferedWriter bw) throws IOException {
        switch (cell.getCellType()) {
            case CELL_TYPE_BLANK:
                bw.write("空");
                System.out.println("空");
                break;
            case CELL_TYPE_STRING:
                String str = cell.getStringCellValue();
                System.out.println(str);
                bw.write(cell.getStringCellValue());
                break;
            case CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    System.out.println(cell.getDateCellValue());
                    bw.write(sdf.format(cell.getDateCellValue()));
                } else {
                    System.out.println(cell.getNumericCellValue());
                    bw.write("" + cell.getNumericCellValue());
                }
                break;
            case CELL_TYPE_BOOLEAN:
                System.out.println(cell.getBooleanCellValue());
                bw.write("" + cell.getBooleanCellValue());
                break;
        }


    }


    /*根据遍历的行列数返回单元格坐标*/
    private String getCellIndex(int row, int column) {
        Integer ASCII_INDEX_OF_A = 65;
        int temp = column / 26;//十位数
        int temp1 = column % 26;//个位数
        String columnIndex = temp != 0 ? (char) (temp - 1 + ASCII_INDEX_OF_A) + "" + (char) (temp1 + ASCII_INDEX_OF_A) : (char) (temp1 + ASCII_INDEX_OF_A) + "";
        String cellIndex = columnIndex + "" + (row + 1);
        System.out.print(cellIndex + ":");
        return cellIndex;
    }

    /*关流的方法封装*/
    private void close(BufferedWriter bw, FileInputStream fis) {
        try {
            if (null != bw) {
                bw.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
