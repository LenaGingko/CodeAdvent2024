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
        System.out.println("Compressed Line: " + diskMap.getCompressedLine());

        String actualCompressedLine = diskMap.getCompressedLine();
        assertEquals(expectedCompressedLine, actualCompressedLine);
    }

    @Test
    public void testCalcChecksum() {

        String compressedLine = "0099811188827773336446555566..............";
        long expectedChecksum = 1928;

        DiskMap diskMap = new DiskMap("");
        diskMap.setCompressedLine(compressedLine);
        diskMap.calcChecksum();

        assertEquals(expectedChecksum, diskMap.getChecksum());
    }
}
