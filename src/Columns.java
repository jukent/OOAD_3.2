import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://stackoverflow.com/questions/699878/is-there-an-easy-way-to-output-two-columns-to-the-console-in-java
public class Columns {

    List<List<String>> lines = new ArrayList<>();
    List<Integer> maxLengths = new ArrayList<>();
    int numColumns = -1;

    public Columns addLine(List<String> row_strings) {

        if (numColumns == -1) {
            numColumns = row_strings.size();
            for(int column = 0; column < numColumns; column++) {
                maxLengths.add(0);
            }
        }

        if (numColumns != row_strings.size()) {
            throw new IllegalArgumentException();
        }

        for (int column = 0; column < numColumns; column++) {
            int length = Math.max(maxLengths.get(column), row_strings.get(column).length());
            maxLengths.set( column, length);
        }

        lines.add(row_strings);

        return this;
    }


    public void print(){
        System.out.println( toString() );
    }


    public String toString() {
        String result = "";
        for (List<String> line: lines) {
            for (int i = 0; i < numColumns; i++) {
                result += pad( line.get(i), maxLengths.get(i) + 1);                
            }
            result += System.lineSeparator();
        }
        return result;
    }


    private String pad(String word, int newLength) {
        while (word.length() < newLength) {
            word += " ";            
        }       
        return word;
    }
}