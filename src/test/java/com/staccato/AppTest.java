package com.staccato;

import com.staccato.logs.FileLoader;
import com.staccato.logs.LogSorter;
import com.staccato.logs.LogWrapper;
import org.junit.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class AppTest {

    public static int MAX_LOG_LINES = 10000;

    public static int MAX_FILES = 10;

    public static int MIN_DAYS = 50;

    public static int PLUS_MAX = 59;

    public static String testDirectory = "/tmp/test-" + (int)(Math.random() * 50000) + "/";


    public static void createFile() {
        String serverName = "" + (int)(Math.random() * 50000);
        String fileName = "server-" + serverName + ".log";
        try(PrintWriter printwriter = new PrintWriter(testDirectory + fileName)){

            int maxLines = (int)(Math.random() * MAX_LOG_LINES);
            int lines = 0;
            OffsetDateTime date = OffsetDateTime.of(
                    LocalDateTime.now().minusDays((int)(Math.random() * MIN_DAYS)),
                    ZoneOffset.UTC);
            while(lines++ < maxLines) {
                date = date.plusMinutes((int)(Math.random() * PLUS_MAX));
                date = date.plusSeconds((int)(Math.random() * PLUS_MAX));
                String dateString = date.format(DateTimeFormatter.ISO_INSTANT);
                printwriter.println(dateString + ", Server " + serverName + " message");
            }
        }catch (Exception e) {
            System.out.println("An error occurred when generating test file: " + fileName);
            System.out.println(e.getMessage());
            //do nothing
        }
    }

    public void generateTestFiles(int fileCount) {
        try{
            createTestPath();
            for (int i = 0; i < fileCount; i++) {
                createFile();
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    private void createTestPath() {
        File file = new File(testDirectory);
        file.mkdir();
    }

    @Test
    public void loadTest() {
        generateTestFiles(MAX_FILES);
        Map<String, LogWrapper> logFiles =  FileLoader.loadLogs(testDirectory);
        LogSorter.printSortedLogs(logFiles);
        System.out.println("test files at:" + testDirectory);
    }
}
