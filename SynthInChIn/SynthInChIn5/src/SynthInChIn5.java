import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class SynthInChIn5 extends Database{
	public static void main(String[]args) throws Exception {
		File database1 = new File("database1.txt");
		File database2a = new File("database2a.txt");
		File database3a = new File("database3a.txt");
		File database4a = new File("database4a.txt");
		databaseDiff(database1, database2a, 2);
		sortedDatabase(database2a, database3a);
		splitDatabase(database3a, database4a);
	}
}