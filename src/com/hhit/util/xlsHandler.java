package com.hhit.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class xlsHandler {
	/**
	 * 创建一个Workbook对象
	 * 参数是文件的输入流
	 * */
	public static Workbook create(InputStream inp) throws IOException,InvalidFormatException {
	    if (!inp.markSupported()) {//判断该输入流是否支持
	        inp = new PushbackInputStream(inp, 8);
	    }
	    if (POIFSFileSystem.hasPOIFSHeader(inp)) {//如果是xls文件
	        return new HSSFWorkbook(inp);
	    }
	    if (POIXMLDocument.hasOOXMLHeader(inp)) {//如果是xlsx文件
	        return new XSSFWorkbook(OPCPackage.open(inp));
	    }
	    throw new IllegalArgumentException("你的excel版本目前poi解析不了");//如果不是xls或xlsx，则抛出异常
	}
}
