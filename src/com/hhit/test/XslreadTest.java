package com.hhit.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.hhit.entity.TestingControl;
import com.hhit.util.xlsHandler;

public class XslreadTest {
	public static void main(String[] args) {
		try {
        	File file = new File("E:\\毕业设计\\数据库\\latestexceltosql.xls");
        	InputStream in = new FileInputStream(file);
        	Workbook workbook = xlsHandler.create(in);
			//读取默认第一个工作表sheet
			Sheet sheet = workbook.getSheetAt(0);
			int firstRowNum = 1;
			//获取sheet中最后一行行号
			int lastRowNum = sheet.getLastRowNum();
			for (int i = firstRowNum; i <=lastRowNum; i++) {
				Row r=sheet.getRow(i);
				int j=1;
				TestingControl tc=new TestingControl();
				tc.setTesterID(r.getCell(j++).getStringCellValue());//默认最左边编号也算一列 所以这里得j++
                tc.setTesterName(r.getCell(j++).getStringCellValue());
                tc.setClassName(r.getCell(j++).getStringCellValue());
                tc.setTestName(r.getCell(j++).getStringCellValue());
                j++;
                tc.setTestRoomName(r.getCell(j++).getStringCellValue());
                tc.setLoginFlag(r.getCell(j++).getStringCellValue());
                tc.setTestRoomID(r.getCell(j++).getStringCellValue());
                tc.setTestList(r.getCell(j++).getStringCellValue());
                j++;
                tc.setTeaname(r.getCell(j++).getStringCellValue());
                tc.setTeaid(tc.getTeaname());
				System.out.println(tc.toString());
			}
			workbook.close();
			file.deleteOnExit();
		}
	  catch (Exception e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
	}
}
