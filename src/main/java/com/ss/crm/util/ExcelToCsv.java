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
    /*工作簿*/
    private Workbook workbook = null;



    /*初始化工作簿，指定输入文件和输出文件地址*/
    public ExcelToCsv(String src, String targetFolder) {
        this.src = src;
        this.targetFolder = targetFolder;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(date);
        this.targetFile = targetFolder.concat(format).concat(".csv");
        try {
            this.fis=new FileInputStream(src);
            this.workbook = WorkbookFactory.create(this.fis);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    /*获取指定的列标题的索引*/
    public List<Integer> getNeededColumnIndex(List<String> titleList, int sheetIndex, int sheetTitleIndex) {
        List<Integer> NeededColumnIndexList = new ArrayList<>();
        final Sheet sheet = workbook.getSheetAt(sheetIndex);
        Row row = sheet.getRow(sheetTitleIndex);
        if (row == null) {
            return null;
        } else {
            for (int i = 0; i < titleList.size(); i++) {
                String columnTitle = titleList.get(i);
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    getCellIndex(sheetTitleIndex, j);
                    final Cell cell = row.getCell(j);
                    // 判断空格和类型
                    if (cell.getCellType() != CELL_TYPE_STRING) {
                        continue;
                    }
                    final String originCellTitle = cell.getStringCellValue();

                    if (originCellTitle.equals(columnTitle)) {
                        NeededColumnIndexList.add(j);
                    }
                }
            }
        }

        return NeededColumnIndexList;
    }

    /*获取指定工作表指定列行标题的索引*/
    public List<Integer> getNeededRowIndex(List<String> titleList, int columnIndex, int sheetIndex) {
        ArrayList<Integer> neededRowIndexList = new ArrayList<>();
        final Sheet sheet = workbook.getSheetAt(sheetIndex);
        int rowNum = sheet.getLastRowNum();
        for(int titleListIndex=0;titleListIndex<titleList.size();titleListIndex++){
            final String title = titleList.get(titleListIndex);
            for (int rowIndex = 0; rowIndex < rowNum; rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                final Cell cell = row.getCell(columnIndex);
                // 判断空格和类型
                if (cell.getCellType() != CELL_TYPE_STRING) {
                    continue;
                } else {
                    final String originCellTitle = cell.getStringCellValue();
                    if (originCellTitle.equals(title)) {
                        neededRowIndexList.add(rowIndex);

                    }
                }

            }
        }

        return neededRowIndexList;
    }

    /*传入一个工作表索引和开始行数将其输出到指定目录下的csv,可以指定列*/
    public String excelToCSV(int beginRowIndex, int sheetIndex, List<Integer> columnIndexList) {

        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream(src));
            final Sheet sheet = workbook.getSheetAt(sheetIndex);
            this.bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
            for (int i = beginRowIndex; i < sheet.getLastRowNum() + 1; i++) {
                final Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                if (columnIndexList == null) {
                    writeRow(row, i,row.getFirstCellNum(),row.getLastCellNum());
                } else {
                    writeSpicifyColumn(row, i, columnIndexList);
                }
                this.bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();

        } finally {
            this.close();
        }
        return targetFile;
    }


    /*合并汇总指定的行数，指定列*/
    public String  consolidation(int beginSheetIndex,int EndSheetIndex,List<Integer>rowIndexList,int beginColumnIndex,int endColumnIndex){

        try {
            this.bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
        for(int i=beginSheetIndex;i<EndSheetIndex;i++){
            System.out.println(i);
            final Sheet sheet = workbook.getSheetAt(i);
            Iterator<Integer> iterator = rowIndexList.iterator();
            while (iterator.hasNext()){
                final Integer rowIndex = iterator.next();
                final Row row = sheet.getRow(rowIndex);
                    writeRow(row,rowIndex,beginColumnIndex,endColumnIndex);
            }
            this.bw.newLine();
        }

        } catch (IOException e) {
                e.printStackTrace();
            }
        return targetFile;
    }


    /*完整的写指定索引列的一整行*/
    private void writeRow(Row row, int Rowindex,int beginColumnIndex,int endColumnIndex) throws IOException {
        for (int i = beginColumnIndex; i <=endColumnIndex; i++) {
            getCellIndex(Rowindex, i);
            final Cell cell = row.getCell(i);
            // 判断空格和类型
            if (cell != null) {
                checkTypeAndWriteCell(cell, this.bw);
            } else {
                System.out.println("空");
            }
            this.bw.write(",");
        }
    }

    /*完整的写指定索引列的一整列*/
    private void writeSpicifyColumn(Row row, Integer Rowindex, List<Integer> indexList) throws IOException {
        int i = 0;
        Iterator<Integer> iterator = indexList.iterator();
        while (iterator.hasNext()) {
            getCellIndex(Rowindex, i++);
            final Cell cell = row.getCell(iterator.next());
            // 判断空格和类型
            if (cell != null) {
                checkTypeAndWriteCell(cell, this.bw);
            } else {
                System.out.println("空");
            }
            this.bw.write(",");
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

                    bw.write(sdf.format(cell.getDateCellValue()));
                } else {

                    bw.write("" + cell.getNumericCellValue());
                }
                break;
            case CELL_TYPE_BOOLEAN:

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

        return cellIndex;
    }

    /*关流的方法封装*/
    public void close() {
        try {
            if (null != this.bw) {
                this.bw.close();
                System.out.println("关流成功");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != this.fis) {
                    this.fis.close();
                    System.out.println("关流成功");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
