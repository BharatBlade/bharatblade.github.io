

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.Schema.Parser;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetReader;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetReader;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

public class CustomParquet {
	
	public CustomCSV csv;
	public File fileRead;
	public File fileWrite;
	public ParquetReader<GenericRecord> reader;
	public String delimiter = "\",\"";
	public CustomWriter printer = new CustomWriter("test2.txt");
	public CustomParquet() {
		csv = new CustomCSV();
		try {
			System.setProperty("hadoop.home.dir", new java.io.File(".").getCanonicalPath() + "\\hadoop-3.0.0");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void loadCSVToRead(String csvFilePath) {
		csv.csvFilePathRead = csvFilePath;
		csv.cr = new CustomReader(new File(csvFilePath));
	}
	public void loadCSVToRead(File csvFilePath) {
		csv.csvFilePathRead = csvFilePath.getAbsolutePath();
		csv.cr = new CustomReader(csvFilePath);
	}
	public void loadCSVToWrite(String csvFilePath) {
		csv.csvFilePathWrite = csvFilePath;
		csv.cw = new CustomWriter(new File(csvFilePath));
	}
	public void loadParquetToRead(String parquetFilePath) {
		fileRead = new File(parquetFilePath);
	}
	public void loadParquetToRead(File parquetFile) {
		fileRead = parquetFile;
	}
	public void loadParquetToWrite(String parquetFilePath) {
		fileWrite = new File(parquetFilePath);
	}
	public void loadCSVMultipleFilesToRead(File[] multipleCSVFilePaths) {
		csv.csvFilePathsRead = multipleCSVFilePaths;
	}
	
	public void parquetCloseReader() {
		try {
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void parquetReader(String filePath) {
		try {
			loadParquetToRead(filePath);
			reader = new AvroParquetReader<GenericRecord>(new Path((new File(filePath)).toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void parquetReader(File file) {
		try {
			loadParquetToRead(file);
			reader = new AvroParquetReader<GenericRecord>(new Path((file).toString()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parquetReadFileToConsole() {
		GenericRecord nextRecord;
		int count = 0;
		try {
			nextRecord = reader.read();
			List<Field> list = nextRecord.getSchema().getFields();
			for(int i = 0; i < list.size(); i++) {
				System.out.print(list.get(i).name() + "    |    ");
			}
			System.out.println();
			//System.out.println(nextRecord.getSchema().getFields());
			while(nextRecord != null) {
//				String ehr = nextRecord.get(12).toString();
//				if(!ehr.equals("EHR")) {
					System.out.println(nextRecord);
					Thread.sleep(100);
					count++;
//				}
				nextRecord = reader.read();
			}
	        reader.close();
	        System.out.println(count);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void parquetReadFileUniqueValues() {
		GenericRecord nextRecord;
		try {
			nextRecord = reader.read();
			List<Field> list = nextRecord.getSchema().getFields();
			TreeSet<String> [] treee = (TreeSet<String>[]) new TreeSet[list.size()];
			for(int i = 0; i < treee.length; i++) {
				treee[i] = new TreeSet<String>();
			}
			for(int i = 0; i < list.size(); i++) {
				printer.print(list.get(i).name() + "    |    ");
			}
			printer.println("");
			//System.out.println(nextRecord.getSchema().getFields());
			while(nextRecord != null) {
				//System.out.println(nextRecord);
				
				for(int i = 0; i < nextRecord.getSchema().getFields().size(); i++) {
					if(treee[i].size() < 100) {
						treee[i].add(nextRecord.get(i).toString());
					}
				}
				//Thread.sleep(1000);
				nextRecord = reader.read();
			}
	        reader.close();
			for(int i = 0; i <  treee.length; i++) {
				printer.println(treee[i].size() + treee[i].toString());
			}
			printer.bw.flush();
			printer.fw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void parquetReadFile5LinesToTxt(String [] substringsSearch) {
		int [] numSearch = new int [substringsSearch.length];
		int numNull = 0;
		int numFucked = 0;
		//TreeSet<String> tree = new TreeSet<String>();
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		GenericRecord nextRecord;
		try {
			
			nextRecord = reader.read();
			List<Field> list = nextRecord.getSchema().getFields();
			printer.println(fileRead.getName());
			for(int i = 0; i < list.size(); i++) {
				printer.print(list.get(i).name() + "    |    ");
			}
			printer.println("");
			//System.out.println(nextRecord.getSchema().getFields());
			while(nextRecord != null) {
				String line = nextRecord.toString();
				String lineTemp = line.substring(line.indexOf("\"HOSP\": \""));
				lineTemp = lineTemp.substring(0, lineTemp.indexOf(","));
				if(!map.containsKey(lineTemp)) {
					map.put(lineTemp, 1);
				}
				else {
					map.put(lineTemp, map.get(lineTemp)+1);
				}
				if(!line.contains("\"TRRIDCODE\": \"\",") && !line.contains("\"HOSP\": \"\",")) {
					for(int i = 0; i < substringsSearch.length; i++) {
						if(line.contains(substringsSearch[i])) {
							numSearch[i]++;
							break;
						}
						if(i == substringsSearch.length - 1) {
							numFucked++;
						}
					}
					printer.println(nextRecord.toString());					
				}
				else {
					numNull++;
				}
				nextRecord = reader.read();
			}
			printer.println("");
			printer.println("");
	        reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(Arrays.toString(substringsSearch));
		System.out.println(Arrays.toString(numSearch));
		System.out.println(numNull);
		System.out.println(numFucked);
		System.out.println(map);
	}
		
	public String createSchema(String[] fields) {
		String schema = "{"
				+ "\"namespace\": \"org.myorg.myname\",\"type\": \"record\","
				+ "\"name\": \"patient\",\""
				+ "fields\": [ ";
		
		for(int i = 0; i < fields.length-1; i++) {
			schema += "{\"name\": \"" + fields[i].replace(" ", "") + "\", \"type\": [\"string\", \"null\"]}, ";
		}
		schema += "{\"name\": \"" + fields[fields.length-1].replace(" ", "") + "\", \"type\": [\"string\", \"null\"]} ]}";
		schema = schema.replace(".", "");
		schema = schema.replace("(", "");
		schema = schema.replace(")", "");
		schema = schema.replace("_", "");
//		schema = schema.replace(" ", "");
//		while(schema.contains("{\"name\": \"")) {
//			System.out.println(schema.substring(schema.indexOf("{\"name\": \""), schema.indexOf(", \"type\": [")));
//			schema = schema.substring(schema.indexOf(", \"type\": [")+10);
//		}
		return schema;
	}
	
	public GenericData.Record createRecord(String line, String [] fields, Schema avroSchema){
		String line2 = cleanTriNetXGarbage(line);
		String [] arr = csv.csvLineToArray(line2, delimiter, fields.length, false);
		GenericData.Record record = new GenericData.Record(avroSchema);
		for(int i = 0; i < fields.length; i++) {
			record.put(cleanNPPES(fields[i]), arr[i]);
		}
		return record;
	}
	
	public String cleanTriNetXGarbage(String line2) {
		line2 = line2.replace("\"", "");
		line2 = line2.replace("true", "T");
		line2 = line2.replace("false", "F");
		line2 = line2.replace("True", "T");
		line2 = line2.replace("False", "F");		
		line2 = line2.replace(",", delimiter);
		return line2;
	}
	
	public void parquetWriteCSVToParquet(CompressionCodecName codec, int pageSize, long rowGroupSize) {
		String line = csv.nextLine();

		//TEMP JUST FOR JUSTIN
		boolean deleteFirstLastCharacters = false;
		if(delimiter.equals("\",\"")) {
			deleteFirstLastCharacters = true;
		}
		line = "Index" + line;
		
		String [] fields = csv.csvLineToArray(line, delimiter, csv.countColumns(line, delimiter), deleteFirstLastCharacters);
		Schema avroSchema = (new Schema.Parser().setValidate(true)).parse(createSchema(fields));
		System.out.println(line);
		System.out.println(Arrays.toString(fields));
		double count = 0;
		long time = System.currentTimeMillis();
		try {
			try (ParquetWriter<Object> writer = AvroParquetWriter.builder(new Path(fileWrite.getAbsolutePath()))
			.withSchema(avroSchema)
			.withCompressionCodec(codec)
			.withConf(new Configuration())
			.withPageSize(pageSize)
			.withRowGroupSize(rowGroupSize)
			.build()) {
				line = csv.nextLine();
				while(line != null) {
					writer.write(createRecord(line, fields, avroSchema));
					count++;
					if(count % 100000 == 0) {
						System.out.println(count + "\t" + (System.currentTimeMillis() - time));
					}
					line = csv.nextLine();
				}
				writer.close();
			}				   
		} catch (Exception ex) { ex.printStackTrace(System.out); }
		csv.closeReader();
	}
	
	public void parquetWriteMultipleCSVsToParquet(CompressionCodecName codec, int pageSize, long rowGroupSize) {
		String line = csv.headerCSVMulipleFiles();
		System.out.println(line);
		int col = csv.countColumns(line, delimiter);
		String [] fields = csv.csvLineToArray(line, delimiter, col, true);
		System.out.println(Arrays.toString(fields));		
		Parser parser = new Schema.Parser().setValidate(true);
		Schema avroSchema = parser.parse(createSchema(fields));		
		try {
			Configuration conf = new Configuration();
			Path path = new Path(fileWrite.getAbsolutePath());
			try (ParquetWriter<Object> writer = AvroParquetWriter.builder(path)
			.withSchema(avroSchema)
			.withCompressionCodec(codec)
			.withConf(conf)
			.withPageSize(pageSize) //For compression
			.withRowGroupSize(rowGroupSize) //For write buffering (Page size)
			.build()) {
				for(int j = 0; j < csv.csvFilePathsRead.length; j++) {
					System.out.println(csv.csvFilePathsRead[j]);
					loadCSVToRead(csv.csvFilePathsRead[j]);
					csv.nextLine();//remove header line
					line = csv.nextLine();
					while(line != null) {
						writer.write(createRecord(line, fields, avroSchema));
						line = csv.nextLine();
					}
					csv.closeReader();
				}
				writer.close();
			}				   
		} catch (Exception ex) { ex.printStackTrace(System.out); }
	}
	
	public static boolean recordContains(GenericRecord nextRecord, String ss) throws Exception {
		for(int i = 0; i < nextRecord.getSchema().getFields().size(); i++) {
			if(nextRecord.get(i).toString().toLowerCase().contains(ss.toLowerCase())) {
				System.out.println(nextRecord);
				Thread.sleep(1000);
				return true;
			}
		}
		return false;
	}
	public String cleanNPPES(String s) {
		return s.replace(".", "").replace("(", "").replace(")", "").replace("_", "").replace(" ", "");
	}

}