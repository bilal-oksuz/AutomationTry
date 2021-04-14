package com.Common.Base;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class Excel {

    String filePath;
    String sheetName;
    List<String> title;
    List<List<String>> listData;

    public Excel()
    {

    }

    public Excel(String filePath, List<String> title, List<List<String>> listData)
    {
        setFilePath(filePath);
        setTitle(title);
        setListData(listData);
        excelCreate(filePath,title,listData);
    }

    public Excel(String filePath, List<String> titleFirst, List<List<String>> listDataFirst, List<String> titleSecond, List<List<String>> listDataSecond)
    {
        excelCreateMultipleSheet(filePath,titleFirst,listDataFirst,titleSecond,listDataSecond);
    }



    private void excelCreate(String filePath,List<String> title,List<List<String>> listData) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Sheet");
        int rowCount = -1;
        Row row = sheet.createRow(++rowCount);
        addTitle(row,title);
        for (List<String> aBook : listData)
        {
            row = sheet.createRow(++rowCount);
            writeBook(row,aBook);
        }
        JavaInfastructure.createExcelFile(filePath,workbook);
    }

    private void excelCreateMultipleSheet(String filePath,List<String> titleFirst,List<List<String>> listDataFirst,List<String> titleSecond,List<List<String>> listDataSecond)
    {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet1=createSheet("Sheet1",workbook,titleFirst,listDataFirst);
        XSSFSheet sheet2=createSheet("Sheet2",workbook,titleSecond,listDataSecond);
        JavaInfastructure.createExcelFile(filePath,workbook);
    }

    private XSSFSheet createSheet(String sheetName,XSSFWorkbook workbook,List<String> title,List<List<String>> listData) {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        int rowCount = -1;
        Row row = sheet.createRow(++rowCount);
        addTitle(row,title);

        for (List<String> aBook : listData)
        {
            row = sheet.createRow(++rowCount);
            writeBook(row,aBook);
        }
        return sheet;
    }

    private XSSFSheet createSheetWithWrap(String sheetName,XSSFWorkbook workbook,List<String> title,List<List<String>> listData) {
        XSSFSheet sheet = workbook.createSheet(sheetName);
        int rowCount = -1;
        Row row = sheet.createRow(++rowCount);
        row.setHeightInPoints((2*sheet.getDefaultRowHeightInPoints()));
        addTitle(row,title);
        CellStyle cs = workbook.createCellStyle();
        cs.setWrapText(true);

        for (List<String> aBook : listData)
        {
            row = sheet.createRow(++rowCount);
            writeBook(row,aBook,cs);
        }
        return sheet;
    }

    private static void writeBook(Row row,List<String> data) {
        for (int i = 0; i <data.size() ; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(data.get(i));
        }
    }

    private static void writeBook(Row row,List<String> data,CellStyle cellStyle) {
        for (int i = 0; i <data.size() ; i++) {
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(data.get(i));
        }
    }
    private static void addTitle(Row row,List<String> title) {

        for (int i = 0; i <title.size() ; i++) {
            Cell cell = row.createCell(i);
            cell.setCellValue(title.get(i));
        }
    }


//    public static void main(String[] args)
//    {
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("Sheet");
//        List<List<String>> listData=new ArrayList<>();
//        Config config = new Config();
//        config = config.config("production");
//        ArrayList<Warehouses> warehouses = new Warehouses().GetActiveWarehouses();
//        ArrayList<WarehouseAddress> warehouseAddresses = new WarehouseAddress().GetAllWarehousesAddress();
//
//        for (int i = 0; i < warehouses.size(); i++)
//        {
//            if (!warehouses.get(i).getAvailableLocations().isEmpty())
//            {
//                for (int j = 0; j < warehouses.get(i).getAvailableLocations().size(); j++)
//                {
//                    AvailableLocations availableLocations = new AvailableLocations(new JSONObject(warehouses.get(i).getAvailableLocations().get(j)));
//                    ParentMerchant parentMerchant=new ParentMerchant(warehouses.get(i).getParentMerchantId());
//                    List<String> strings=new ArrayList<>();
//                    strings.add(availableLocations.getCityName());
//                    strings.add(availableLocations.getTownName());
//                    strings.add(availableLocations.getDistrictName());
//                    strings.add(warehouses.get(i).getCode());
//                    strings.add(warehouses.get(i).getName());
//                    strings.add(parentMerchant.getName());
//                    strings.add(String.valueOf(warehouses.get(i).getIsActive()));
//                    strings.add(String.valueOf(warehouses.get(i).getIsOpenForTest()));
//                    listData.add(strings);
//                }
//            }
//        }
//
//        int rowCount = -1;
//        List<String> title=new ArrayList<>();
//        title.add( "İl");
//        title.add("İlçe");
//        title.add("Mahalle");
//        title.add("MerchantId");
//        title.add("Mağaza");
//        title.add("Mega");
//        title.add("Aktif");
//        title.add("Test Mağazası");
//        Row row = sheet.createRow(++rowCount);
//        addTitle(row,title);
//
//        for (List<String> aBook : listData)
//        {
//            row = sheet.createRow(++rowCount);
//            writeBook(row,aBook);
//        }
//        JavaInfastructure.createExcelFile(System.getProperty("user.dir")+"/Test/HXMağazalar.xlsx",workbook);
//
//
////
////        for (Object var:listData)
////        {
////            System.out.println(var);
////        }
//
////        Object[][] bookData =
////        {
////          {"Head First Java", "Kathy Serria", 79},
////          {"Effective Java", "Joshua Bloch", 36},
////          {"Clean Code", "Robert martin", 42},
////          {"Thinking in Java", "Bruce Eckel", 35},
////        };
////
////        int rowCount = 0;
////
////        for (Object[] aBook : bookData)
////        {
////            Row row = sheet.createRow(++rowCount);
////            int columnCount = -1;
////
////            for (Object field : aBook)
////            {
////                Cell cell = row.createCell(++columnCount);
////                if (field instanceof String)
////                {
////                    cell.setCellValue((String) field);
////                 }
////                else if (field instanceof Integer)
////                {
////                    cell.setCellValue((Integer) field);
////                }
////            }
////
////        }
////
////        JavaInfastructure.createExcelFile(System.getProperty("user.dir")+"/Test/HXMağazalar.xlsx",workbook);
//    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<String> getTitle() {
        return title;
    }

    public void setTitle(List<String> title) {
        this.title = title;
    }

    public List<List<String>> getListData() {
        return listData;
    }

    public void setListData(List<List<String>> listData) {
        this.listData = listData;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public static void main(String args[]) throws IOException
    {
        FileInputStream fis=new FileInputStream(new File(System.getProperty("user.dir")+"/Test/ilceler.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook();
        try
        {
            workbook = new XSSFWorkbook(fis);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        XSSFSheet sheet=workbook.getSheetAt(0);
        FormulaEvaluator formulaEvaluator=workbook.getCreationHelper().createFormulaEvaluator();
        int deger=sheet.getRow(0).getLastCellNum();
        String d2eger=sheet.getRow(0).getCell(1).toString();
        System.out.println(deger);
        System.out.println(d2eger);
//        for(Row row: sheet)
//        {
//            for(Cell cell: row)
//            {
//                switch(formulaEvaluator.evaluateInCell(cell).getCellType())
//                {
//                    case Cell.CELL_TYPE_NUMERIC:
//                        System.out.println(cell.getNumericCellValue());
//                        break;
//                    case Cell.CELL_TYPE_STRING:
//                        System.out.println(cell.getStringCellValue());
//                        break;
//                }
//                break;
//            }
//        }
    }
}



