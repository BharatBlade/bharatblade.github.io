package RMED;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.util.IOUtils;

public class Main {
	public static void main(String[]args)throws Exception {
		RMEDDatabase db = new RMEDDatabase();
		IOUtils.setByteArrayMaxOverride(200000000);
//		String name = "Faulknham".toLowerCase();
//		db.addFile("G:\\H\\RMED_Research\\Raw_Data\\Carrie\\test.xlsx", 48, 0, 0);
//		db.addFile("G:\\H\\RMED_Research\\RMED Graduation Survey\\CONFIDENTIAL_RMED RMEDDatabase 2016_2022 Graduates.xlsx", 26, 0, 0);
//		db.addFile("G:\\H\\RMED_Research\\RMED Graduation Survey\\CONFIDENTIAL_RMED RMEDDatabase 2016_2022 Graduates.xlsx", 48, 1, 1);
//		db.addCSV("G:\\H\\RMED_Research\\Raw_Data\\NPPES_Data_Dissemination_February_2023\\npidata_pfile_20050523-20230212.csv");
//		System.out.println(db.doctors.get(0).Authorized_Official_Telephone_Number);
		
//		for(int i = 0; i < db.doctors.size(); i++) {
//			if(db.doctors.get(i).L_Name.toLowerCase().equals(name)) {
//				System.out.println("---------------------------------------------------------");
//				System.out.println(db.doctors.get(i).F_Name);
//				System.out.println(db.doctors.get(i).L_Name);
//				System.out.println(db.doctors.get(i).Provider_Middle_Name);
//				System.out.println(db.doctors.get(i).MI);
//				System.out.println(db.doctors.get(i).NPI_Number);
//				System.out.println("---------------------------------------------------------");
//			}
//		}

		db.addFile("G:\\H\\RMED_Research\\RMED Graduation Survey\\CONFIDENTIAL_RMED 2016_2022 UPDATED.xlsx", 26, 0, 0);
		db.addFile("G:\\H\\RMED_Research\\Raw_Data\\Carrie\\test.xlsx", 48, 0, 0);
		
		InputStream inp = new FileInputStream(new File("G:\\H\\RMED_Research\\RMED Graduation Survey\\CONFIDENTIAL_RMED 2016_2022 UPDATED.xlsx"));
		Workbook wb = WorkbookFactory.create(inp);
		Sheet sheet = wb.getSheetAt(0);
		int numCol = 0;
		ArrayList<String> header = new ArrayList<String>();
		Row row = sheet.getRow(0);
		for(numCol = 0; numCol < 26; numCol++) { //49 fields for RMED tab
			Cell cell = row.getCell(numCol);
			header.add(cell.toString());
		}
		Cell cell = row.getCell(numCol);
		header.add(cell.toString());
		System.out.println(header);

		for(int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
			row = sheet.getRow(i);
			String year = row.getCell(0).toString().trim().replace("\n", "");
			String firstName = row.getCell(2).toString().trim().replace("\n", "");
			String lastName = row.getCell(3).toString().trim().replace("\n", "");
			String specialtyMatch = row.getCell(16).toString().replace("\n", "");
			RMEDDoctor doctor = db.NPPES(firstName, lastName, specialtyMatch, year);
			System.out.println(firstName + "\t" + lastName + "\t" + specialtyMatch + "\t" + year);
			db.addDoctor2(doctor);
			System.out.println(doctor);
			Thread.sleep(500);
		}
		wb.close();
		
		
	}
	
}
