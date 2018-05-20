package com.jjt.wechat.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static String[] EXCEL_CELL = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" };
	XSSFWorkbook wb = new XSSFWorkbook();
	XSSFSheet sheet1 = wb.createSheet("sheet1");
	XSSFCellStyle frameStyle = wb.createCellStyle();
	Map<String, String> values = new HashMap<String, String>();
	List<Integer[]> cellRangeList = new ArrayList<Integer[]>();
	FileOutputStream fileOut;
	XSSFCell xssfCell = null;
	
	int row;
	int cell;
	int width = 5000;
	int height = 500;
	
	public ExcelUtils(){
		
	}
	
	public ExcelUtils( int row, int cell, Map<String, String> values, List<Integer[]> cellRangeList){
		this.row = row;
		this.cell = cell;
		this.values = values;
		this.cellRangeList = cellRangeList;
	}
	
	public void setWidthAndHeight(int width, int height) {
		this.width = width;
		this.height = height;
	}
	
	void init(){
		frameStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直
		frameStyle.setAlignment(HorizontalAlignment.CENTER); // 居中
		frameStyle.setBorderBottom(BorderStyle.THIN);// 下边框
		frameStyle.setBorderLeft(BorderStyle.THIN);// 左边框
		frameStyle.setBorderRight(BorderStyle.THIN);// 右边框
		frameStyle.setBorderTop(BorderStyle.NONE);// 上边框
//		frameStyle.setFillBackgroundColor((short) 43);
		Font fontStyle = wb.createFont(); // 字体样式
		fontStyle.setFontName("宋体"); // 字体
		fontStyle.setFontHeightInPoints((short) 15); // 大小
		// 将字体样式添加到单元格样式中
		frameStyle.setFont(fontStyle);
		// 行
		for (int i = 0; i < row; i++) {
			XSSFRow rowTable = sheet1.createRow((short) i);
			sheet1.setColumnWidth(i, width);
			// 列
			for (int j = 0; j < cell; j++) {
				rowTable.setHeight((short) height);
				xssfCell = rowTable.createCell(j);
				xssfCell.setCellValue(values.get(EXCEL_CELL[j] + (i + 1)));
				xssfCell.setCellStyle(frameStyle);
			}
		}
		int cellRangeLength = cellRangeList.size();
		for (int i = 0; i < cellRangeLength; i++) {
			CellRangeAddress cr = new CellRangeAddress(cellRangeList.get(i)[0], cellRangeList.get(i)[1],
					cellRangeList.get(i)[2], cellRangeList.get(i)[3]);
			sheet1.addMergedRegion(cr);
		}
	}
	
	public void createExcel(String savePath, String fileName) {
		init();
		try {
			FileOutputStream fos = new FileOutputStream(savePath+File.separator+fileName);
			File filePath = new File(savePath);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			wb.write(fos);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
	}

}
