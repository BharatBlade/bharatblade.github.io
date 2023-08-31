package File;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArraySorting implements Comparator<File> {

    public ArraySorting() { }

    public ArrayList<File> sort(ArrayList<File> arrayOfFiles) {
        Collections.sort(arrayOfFiles, new ArraySorting());
        return arrayOfFiles;
    }

    public int compare( File a, File b ) {
        long aSize = a.length();
        long bSize = b.length();
        if ( aSize < bSize ) {
            return -1;
        }
        else if (aSize > bSize) {
            return 1;
        }
        else {
            return 0;
        }
    }

}
