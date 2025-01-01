package day09;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import java.util.ArrayList;

public class DiskMapTest {
    String denseInput = "2333133121414131402";

    @Test
    public void testFormat() {
        String expectedFormattedLine = "00...111...2...333.44.5555.6666.777.888899";
        DiskMap diskMap = new DiskMap(denseInput);

        String actualFormattedLine = diskMap.formatAsString(diskMap.getFormattedLine());
        assertEquals(expectedFormattedLine, actualFormattedLine);
    }

    @Test
    public void testCompress() {
        String expectedCompressedLine = "0099811188827773336446555566..............";
        DiskMap diskMap = new DiskMap(denseInput);

        System.out.println("Formatted Line: " + diskMap.formatAsString(diskMap.getFormattedLine()));
        System.out.println("Compressed Line: " + diskMap.formatAsString(diskMap.getCompressedLine()));

        String actualCompressedLine = diskMap.formatAsString(diskMap.getCompressedLine());
        assertEquals(expectedCompressedLine, actualCompressedLine);
    }

    @Test
    public void testCalcChecksum() {
        String compressedLine = "0099811188827773336446555566..............";
        long expectedChecksum = 1928;

        DiskMap diskMap = new DiskMap("");

        ArrayList<DiskMap.Block> compressedBlocks = new ArrayList<>();
        char currentChar = compressedLine.charAt(0);
        int count = 0;

        for (char c : compressedLine.toCharArray()) {
            if (c == currentChar) {
                count++;
            } else {
                compressedBlocks.add(new DiskMap.Block(currentChar == '.' ? -1 : Character.getNumericValue(currentChar), count));
                currentChar = c;
                count = 1;
            }
        }
        compressedBlocks.add(new DiskMap.Block(currentChar == '.' ? -1 : Character.getNumericValue(currentChar), count));

        diskMap.setCompressedLine(compressedBlocks);
        diskMap.calcChecksum();

        assertEquals(expectedChecksum, diskMap.getChecksum());
    }
}
