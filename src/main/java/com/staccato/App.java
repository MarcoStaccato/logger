package com.staccato;

import com.staccato.logs.FileLoader;
import com.staccato.logs.LogSorter;
import com.staccato.logs.LogWrapper;

import java.util.Map;

public class App {

    public static void main( String[] args ) {
        String path = "/tmp";
        if(args.length == 0){
            System.out.println("Loading files by default at /tmp, if you want to load files from a different path");
            System.out.println("you can specify it as: java -jar armory-logger.jar [yourpath]");
        }else{
            path = args[0];
            System.out.println("Loading files from: " + path);
        }
        Map<String, LogWrapper> logFiles =  FileLoader.loadLogs(path);
        LogSorter.printSortedLogs(logFiles);
    }



}
