package com.test.kanuTest;

import java.io.*;
import java.util.Arrays;

public class testSample {

    public static void main(String[] args) throws IOException {
        String filePath = "ip_test.txt";
        int uniqueIPCount = countUniqueIPs(filePath);
        System.out.println("Unique IP addresses: " + uniqueIPCount);
    }

    public static int countUniqueIPs(String filePath) throws IOException {
        long totalMemory = Runtime.getRuntime().totalMemory();
        int bufferSize = (int) Math.min(totalMemory / 10, Integer.MAX_VALUE);
        String sortedFilePath = "sorted_ips.txt";
        sortLargeFile(filePath, sortedFilePath, bufferSize);
        return countUniqueIPsFromSortedFile(sortedFilePath);
    }

    private static void sortLargeFile(String filePath, String sortedFilePath, int bufferSize) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        BufferedWriter bw = new BufferedWriter(new FileWriter(sortedFilePath));
        String[] buffer = new String[bufferSize];
        int count = 0;

        try {
            String line;
            while ((line = br.readLine()) != null) {
                buffer[count++] = line;
                if (count == bufferSize) {
                    Arrays.sort(buffer, 0, count);
                    for (int i = 0; i < count; i++) {
                        bw.write(buffer[i]);
                        bw.newLine();
                    }
                    count = 0;
                }
            }
            if (count > 0) {
                Arrays.sort(buffer, 0, count);
                for (int i = 0; i < count; i++) {
                    bw.write(buffer[i]);
                    bw.newLine();
                }
            }
        } finally {
            br.close();
            bw.close();
        }
    }

    private static int countUniqueIPsFromSortedFile(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String prevIP = null;
        int uniqueCount = 0;

        try {
            String line;
            while ((line = br.readLine()) != null) {
                if (prevIP == null || !prevIP.equals(line)) {
                    prevIP = line;
                    uniqueCount++;
                }
            }
        } finally {
            br.close();
        }

        return uniqueCount;
    }
}
