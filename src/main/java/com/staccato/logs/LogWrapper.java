package com.staccato.logs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class LogWrapper {

    public String key = null;
    public String lastLine = null;
    public boolean hasNextLine = false;

    private BufferedReader reader = null;

    /**
     * initializes a buffer reader for a given file
     * @param filePath
     */
    public LogWrapper(Path filePath){
        try{
            key = filePath.getFileName().toString();
            reader = new BufferedReader(new FileReader(filePath.toString()));
            loadNextLine(); //load first line
        }catch (IOException e){
            System.out.println("An error occurred while loading file at: [" + filePath.toString() + "]");
            e.printStackTrace();
            lastLine = null;
            hasNextLine = false;
        }
    }
    public void printLine(){
        System.out.println(lastLine);
        loadNextLine();
    }

    public String loadNextLine(){
        try {
            lastLine = reader.readLine();
            hasNextLine = lastLine != null;
        }catch(IOException e){
            lastLine = null;
            hasNextLine = false;
            e.printStackTrace();
        }
        return lastLine;
    }

    @Override
    protected void finalize() throws Throwable {
        if(reader != null) reader.close();
        super.finalize();
    }
}
