package Readmissions;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;
import File.FastParquet;
public class Readmissions {	
	public static void main(String[]args) throws Exception {
		FastParquet f = new FastParquet();
		f.parquetWriteCSVToParquet(
			"C:\\Users\\johnj\\Downloads\\SAS Export to STATA 202203\\Liver\\LIVER_DATA.csv", 
			"C:\\Users\\johnj\\Downloads\\SAS Export to STATA 202203\\Liver\\LIVER_DATA.parquet", 
			CompressionCodecName.GZIP, 
			1024*1024*1024, 
			1024*1024*1024
		);		
	}
}