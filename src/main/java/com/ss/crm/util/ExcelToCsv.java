package com.ss.crm.util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.poi.ss.usermodel.Cell.*;

public class ExcelToCsv {
    /*目标文件文件夹*/
    private String targetFolder = null;
    /*输出流*/
    private BufferedWriter bw = null;
    /*文件输入流*/
    private FileInputStream fis = null;
    /*源文件地址*/
    private String src;
    /*目标文件地址*/
    private String targetFile;
    /*工作簿*/
    private Workbook workbook;
    private List<String> appendInfo;
    private String split = "|";
    private Integer sexIndex;
    private List<Integer> columnIndexList;

    public void setTargetFolder(String targetFolder,String prefix) {
        this.targetFolder = targetFolder;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String format = sdf.format(date);
        if(prefix!=null){
            this.targetFile = targetFolder.concat(prefix).concat(format).concat(".csv");
        }else{
            this.targetFile = targetFolder.concat(format).concat(".csv");
        }

    }



    public void setSexIndex(Integer sexIndex) {

        this.sexIndex = sexIndex;

    }

    public void setColumnIndexList(List<Integer> columnIndexList) {
        this.columnIndexList = columnIndexList;
    }

    public void setColumnIndexList(int begin, int end) {
        List<Integer> columnIndexList = new ArrayList<>();
        for (; begin <= end; begin++) {
            columnIndexList.add(begin);
        }
        this.columnIndexList = columnIndexList;
    }


    public void setSplit(String split) {
        this.split = split;
    }


    public Workbook getWorkbook() {
        return workbook;
    }

    public void setSrc(String src) {
        try {
            this.fis = new FileInputStream(src);
            this.workbook = WorkbookFactory.create(this.fis);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        this.src = src;
    }


    public void setAppendInfo(List<String> appendInfo) {
        this.appendInfo = appendInfo;
    }

    /*获取指定的列标题的索引，标题必须为string 类型的*/
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
        for (int titleListIndex = 0; titleListIndex < titleList.size(); titleListIndex++) {
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
    public String excelToCSV(int beginRowIndex, int sheetIndex) {
        try {
            Workbook workbook = WorkbookFactory.create(new FileInputStream(src));
            final Sheet sheet = workbook.getSheetAt(sheetIndex);
            this.bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
            for (int i = beginRowIndex; i < sheet.getLastRowNum() + 1; i++) {
                final Row row = sheet.getRow(i);
                if (row == null) {
                    continue;
                }
                initialColumnIndexList(row);
                final List<String> rowData = getRow(row, columnIndexList);
                System.out.println(Arrays.deepToString(appendInfo( handleSex(rowData)).toArray()));
                writeRow(appendInfo( handleSex(rowData)), split);
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


    /*合并汇总从一定范围索引的工作表指定的行数，指定列*/
    public String consolidation(int beginSheetIndex, int EndSheetIndex, List<Integer> rowIndexList) {
        try {
            /*初始化输出流*/
            this.bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
            /*遍历工作表*/
            for (int i = beginSheetIndex; i < EndSheetIndex; i++) {
                final Sheet sheet = workbook.getSheetAt(i);
                final ArrayList<String> newRowdata = new ArrayList<>();
                    /*遍历指定行*/
                for(int j=0;j<rowIndexList.size();j++){
                    final Row row = sheet.getRow(rowIndexList.get(j));
                    initialColumnIndexList(row);
                    final List<String> rowData = getRow(row,columnIndexList);
                    newRowdata.addAll(rowData);
                }
                writeRow(appendInfo(handleSex(newRowdata)), split);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            this.close();
        }
        return targetFile;
    }

    private void writeRow(List<String> rowData, String split) {
        try {
            for (int i = 0; i < rowData.size(); i++) {
         /*       System.out.print(blankRemoveUtil(rowData.get(i)) + split);*/
                this.bw.write(blankRemoveUtil(rowData.get(i)) + split);
            }
   /*         System.out.println();*/
                this.bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    /*获取某行中特定的单元格*/
    private List<String> getRow(Row row, List<Integer> columnIndexList) {
        List<String> rowData = new ArrayList<>();
        for (int i = 0; i < columnIndexList.size(); i++) {
            final Cell cell = row.getCell(columnIndexList.get(i));
            if (cell != null) {
                rowData.add(getCell(cell));
            } else {
                rowData.add("");

            }
        }
        return rowData;
    }
            /*初始化列数*/
    private void initialColumnIndexList(Row row){
        if (columnIndexList == null && columnIndexList.size() == 0) {
            row.getLastCellNum();
            setColumnIndexList(row.getFirstCellNum(), row.getLastCellNum());
        }
    }

    /*自动判断选择的列，自动修改性别字段数据，*/
    private List<String> appendInfo(List<String> rowData) {
        /*此处处理不太好*/
        if (appendInfo != null && appendInfo.size() > 0) {
            rowData.addAll(appendInfo);
        }
        return rowData;
    }
    /*比较蛋疼的处理性别数据的方法*/
    private List<String> handleSex(List<String> rowData) {

        if (sexIndex != null && sexIndex < rowData.size()) {
            String orgin = rowData.get(sexIndex);

            if (orgin != null) {
                if (orgin.equals("男")) {
                    rowData.set(sexIndex, "1");
                } else if (orgin.equals("女")) {
                    rowData.set(sexIndex, "0");
                }
            }
        }

        return rowData;
    }


    /*获取指定单元格数据*/
    private String getCell(Cell cell) {
        String cellValue = null;
        switch (cell.getCellType()) {
            case CELL_TYPE_BLANK:
                cellValue = "空";
                break;
            case CELL_TYPE_STRING:
                cellValue = cell.getStringCellValue();
                break;
            case CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(cell.getDateCellValue());
                } else {
                    cellValue = "" + cell.getNumericCellValue();
                }
                break;
            case CELL_TYPE_BOOLEAN:
                cellValue = "" + cell.getBooleanCellValue();
                break;
        }
        return cellValue;
    }


    /*根据遍历的行列数返回单元格坐标  最多两位*/
    private String getCellIndex(int row, int column) {
        Integer ASCII_INDEX_OF_A = 65;
        int temp = column / 26;//十位数
        int temp1 = column % 26;//个位数
        String columnIndex = temp != 0 ? (char) (temp - 1 + ASCII_INDEX_OF_A) + "" + (char) (temp1 + ASCII_INDEX_OF_A) : (char) (temp1 + ASCII_INDEX_OF_A) + "";
        String cellIndex = columnIndex + "" + (row + 1);
        return cellIndex;
    }

    /*去除空格等*/
    public static String blankRemoveUtil(String str) {
        String result = "";
        if (str != null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            result = m.replaceAll("");
        }
        return result;
    }

    /*关流的方法封装*/
    public void close() {
        try {
            if (null != this.bw) {
                this.bw.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != this.fis) {
                    this.fis.close();

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
