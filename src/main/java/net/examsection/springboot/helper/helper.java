package net.examsection.springboot.helper;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import net.examsection.springboot.model.ExamSection;
import net.examsection.springboot.repository.InfoRepository;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class helper {
	@Autowired
	private InfoRepository InfoRepo;

    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        System.out.println("ok");
        return contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<ExamSection> convertExcelToListOfExamSection(InputStream is,  Integer countz) {
        List<ExamSection> list = new ArrayList<>();
        try {
            int count=0, count1 = 0;
            ExamSection q = new ExamSection();
            XSSFWorkbook workbook = new XSSFWorkbook(is);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()) {
                Row row = iterator.next();
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                ExamSection p = new ExamSection();
                while (cells.hasNext()) {
                    Cell cell = cells.next();
                    switch (cid) {
                        case 0:
                            p.setPrn(cell.getNumericCellValue());
                            break;
                        case 1:
                            p.setStudentname(cell.getStringCellValue());
                            break;
                        case 2:
                            p.setProgramname(cell.getStringCellValue());
                            break; // Add this break statement to fix the missing case.
                        default:
                            break;
                    }
                    cid++;
                }
                p.setInfoTableId(countz);
               
//                if(count1<strengths1.length && count1<blocks1.length) {
//                    if(count<strengths1[count1]) {
//                   	 p.setBlock_no(blocks1[count1]);
//                    }
//                    else {
//                   	 count1++;
//                   	 count=1;
//                   	 p.setBlock_no(blocks1[count1]);
//                    }
//                    //System.out.println("Count 1 :"+count1+"\n"+"count:"+count+"\n");
//                      }
                count++;
                list.add(p);
                
                // Move the workbook close outside the while loop to close it once after processing all rows
            }
           
            workbook.close(); // Close the workbook here
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
}
