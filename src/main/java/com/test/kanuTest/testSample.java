package com.test.kanuTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class testSample {

    public static void main(String[] args) throws IOException {
        String filePath = "ip_test.txt";
        int uniqueIPCount = countUniqueIPs(filePath);
        System.out.println("Unique IP addresses: " + uniqueIPCount);
    }

    public static int countUniqueIPs(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        List<String> uniqueIPs = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            if (!uniqueIPs.contains(line)) {
                uniqueIPs.add(line);
            }
        }

        br.close();
        return uniqueIPs.size();
    }
}
