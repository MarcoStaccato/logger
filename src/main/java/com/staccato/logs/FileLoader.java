package com.staccato.logs;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileLoader {

    public static Map<String, LogWrapper> loadLogs(String path){
        List<Path> logPaths =  loadLogFiles(path);
        return loadLogLines(logPaths);
    }

    /**
     * Loads all file paths on a given path
     * @param path
     * @return a list of file paths
     */
    private static List<Path> loadLogFiles(String path){
        List<Path> result = new ArrayList<>();
        File dirFile = new File(path);
        File[] filePaths = dirFile.listFiles();

        if(filePaths == null) {
            System.out.println("No files found at: " + path);
        }

        for (File currentPath : filePaths) {
            String currentFilePath =  currentPath.getName();
            if(isLogFile(currentFilePath)) {
                result.add(Paths.get(currentPath.toURI()));
            }
        }
        if (result.isEmpty()) {
            System.out.println("No log files found at: " + path);
        }else {
            System.out.println("Found " + result.size() + " files");
        }
        return result;
    }

    /**
     * Loads the first line for all path files
     * @param logFiles paths of the files to be loaded
     * @return a map with the path as key and logWrapper with the first line of each file
     */
    private static Map<String, LogWrapper> loadLogLines(List<Path> logFiles){
        Map<String, LogWrapper> logMap = new HashMap<>();
        for(Path path : logFiles){
            LogWrapper wrapper = new LogWrapper(path);
            //prevent empty files from loading into the map
            if(wrapper.hasNextLine) logMap.put(wrapper.key, wrapper);
        }
        return logMap;
    }

    private static boolean isLogFile(String fileName) {
        return fileName.startsWith("server") && fileName.endsWith(".log");
    }
}
