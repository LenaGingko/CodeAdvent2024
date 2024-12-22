package day09;

import java.util.ArrayList;
import java.util.List;

public class Attempt2 {

    public static void main(String[] args) {
        final String line = DataReader.readFile();
        System.out.println("Checksum: " + calculateChecksum(line));
    }

    public static int calculateChecksum(String diskMap) {
        List<Integer> fileBlocks = new ArrayList<>();
        List<Integer> freeSpaces = new ArrayList<>();

        // Parse the input into file lengths and free spaces
        for (int i = 0; i < diskMap.length(); i += 2) {
            fileBlocks.add(Character.getNumericValue(diskMap.charAt(i)));
            if (i + 1 < diskMap.length()) {
                freeSpaces.add(Character.getNumericValue(diskMap.charAt(i + 1)));
            }
        }

        // Generate initial block layout
        StringBuilder diskState = new StringBuilder();
        int fileId = 0;
        for (int i = 0; i < fileBlocks.size(); i++) {
            for (int j = 0; j < fileBlocks.get(i); j++) {
                diskState.append(fileId);
            }
            if (i < freeSpaces.size()) {
                for (int j = 0; j < freeSpaces.get(i); j++) {
                    diskState.append(".");
                }
            }
            fileId++;
        }

        // Compact the disk
        compactDisk(diskState);

        // Calculate the checksum
        return computeChecksum(diskState.toString());
    }

    private static void compactDisk(StringBuilder diskState) {
        int lastIndex = diskState.length() - 1;

        while (true) {
            boolean moved = false;
            for (int i = lastIndex; i >= 0; i--) {
                if (diskState.charAt(i) != '.' && i < lastIndex && diskState.charAt(i + 1) == '.') {
                    // Move the block to the leftmost free space
                    diskState.setCharAt(i + 1, diskState.charAt(i));
                    diskState.setCharAt(i, '.');
                    moved = true;
                }
            }
            if (!moved) break;
        }
    }

    private static int computeChecksum(String diskState) {
        int checksum = 0;

        for (int i = 0; i < diskState.length(); i++) {
            char c = diskState.charAt(i);
            if (c != '.') {
                checksum += i * Character.getNumericValue(c);
            }
        }
        return checksum;
    }
}
