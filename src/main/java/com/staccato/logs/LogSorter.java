package com.staccato.logs;

import java.util.*;

public class LogSorter {

    public static void printSortedLogs(Map<String, LogWrapper> logs){
        List<String> keys = new ArrayList<>(logs.keySet());
        if (keys.isEmpty()) return;

        while (keys.size() > 1){
            List<String> toRemove = new LinkedList<>();
            LogWrapper oldestLog = logs.get(keys.get(0));

            for (int i=1; i<keys.size(); i++) {
                String currentKey = keys.get(i);
                LogWrapper currentLog = logs.get(currentKey);
                oldestLog = getOldestLog(oldestLog, currentLog);
            }
            //print next line and advance reader
            oldestLog.printLine();
            if (!oldestLog.hasNextLine) {
                toRemove.add(oldestLog.key);
            }

            if (!toRemove.isEmpty()) {
                keys.removeAll(toRemove);
            }
        }

        if(keys.size() == 1){
            LogWrapper lastLog = logs.get(keys.get(0));
            while(lastLog.hasNextLine){
                lastLog.printLine();
            }
        }
    }

    public static LogWrapper getOldestLog(LogWrapper oldest, LogWrapper current) {
        String oldestLogLine = oldest.lastLine;
        String currentLogLine = current.lastLine;
        return oldestLogLine.compareTo(currentLogLine) >= 0 ? current : oldest;
    }
}
