package EmailHelper;

import FileOps.FileOps;
import FileOps.UrlConnectionReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class Account {
    
    private final String name;
    private final String filepath;
    private String stringUrl;
    private List<String> filenames;
    private Vector<String> keys;
    private FileOps fo;

    public Account(String name) throws IOException {
        this.name = name;
        this.filepath = "accounts/" + name + "/";
        
        setUrl();

        filenames = new ArrayList<>();
        setFilenames();
      
        keys = new Vector<>();
        setKeys();
    }
    
    //**************************************************************************
    // Functions that read data from files to lists
    //**************************************************************************
    private void setUrl() throws IOException {
        File f = new File(filepath + "url.txt");
        fo = new FileOps(f, false);
        this.stringUrl = setStringFromFile(fo);
    }
    
    private void setFilenames() throws IOException {
        File f = new File(filepath + "filenames.txt");
        fo = new FileOps(f, false);
        this.filenames = getListFromFile(fo);
    }
   
    private void setKeys() throws IOException {
        File f = new File(filepath + "keys.txt");
        fo = new FileOps(f, false);
        this.keys = getVectorFromFile(fo);
    }
    
    //**************************************************************************
    // Getters
    //**************************************************************************
    public String getName() {
        return name;
    }

    public String getStringUrl() {
        return stringUrl;
    }

    public List<String> getFilenames() {
        return filenames;
    }

    public Vector<String> getKeys() {
        return keys;
    }

    public String getFilepath() {
        return filepath;
    }
    
    private Vector<String> getVectorFromFile(FileOps fo) throws IOException {
        Vector<String> aVector = new Vector<>();
        try {
            aVector = fo.readToVector();
        }
        catch(IOException ioe) {
            throw ioe;
        }
        return aVector;
    }
    
    private List<String> getListFromFile(FileOps fo) throws IOException {
        List<String> aList = new ArrayList<>();
        try {
            aList = fo.readToList();
        }
        catch(IOException ioe) {
            throw ioe;
        }
        return aList;
    }
  
    private String setStringFromFile(FileOps fo) {
        String str = "";
        try {
            str = fo.readSingleLineFromFile();
        }
        catch(IOException ioe) {
            System.out.println(ioe.getMessage());
        }
        return str;
    }
    
    public String readFile(String stringKey) throws FileNotFoundException, IOException {
        
        File f;
        String tempUrl;
        String contents = "";
        
        int max = this.getKeys().size();
        
        for(int i = 0;  i < max; i++) {
	    if (stringKey.equalsIgnoreCase(this.getKeys().get(i))) {
                f = new File(this.filepath + this.getFilenames().get(i));
            
                if(f.exists()) {
                    fo = new FileOps(f, false);
                    contents = fo.readFromFile();
                }
                else {
                    tempUrl = this.getStringUrl() + this.getFilenames().get(i);
                    System.out.println(tempUrl);//debugging
                    contents = UrlConnectionReader.getUrlContents(tempUrl);
                }
	    }
        }
        return contents;
    }
}
