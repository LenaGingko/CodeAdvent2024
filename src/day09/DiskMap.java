package day09;

import java.util.ArrayList;

public class DiskMap {
    private long checksum = 0;
    private final String line;
    private ArrayList<Block> formattedLine = new ArrayList<>();
    private ArrayList<Block> compressedLine = new ArrayList<>();

    public DiskMap(String line) {
        this.line = line;
        this.format();
        this.compress();
        this.calcChecksum();
    }

    public static class Block {
        int id;
        int count;
        public Block(int id, int count) {
            this.id = id;
            this.count = count;
        }
    }

    public void format() {
        boolean isFileBlock = true;
        int id = 0;
        int currentId = -1;

        for (char c : line.toCharArray()) {
            int number = Character.getNumericValue(c);

            if (isFileBlock) {
                if (currentId != id) {
                    formattedLine.add(new Block(id, number));
                    currentId = id; // Update current ID
                } else {
                    // Extend the current block
                    Block lastBlock = formattedLine.get(formattedLine.size() - 1);
                    lastBlock.count += number;
                }
                id++;
            } else {
                if (!formattedLine.isEmpty() && formattedLine.get(formattedLine.size() - 1).id == -1) {
                    // Extend the last free block
                    Block lastBlock = formattedLine.get(formattedLine.size() - 1);
                    lastBlock.count += number;
                } else {
                    // Add a new free block
                    formattedLine.add(new Block(-1, number));
                }
            }
            isFileBlock = !isFileBlock;
        }
    }


    public void compress() {
        ArrayList<Block> newCompressedLine = new ArrayList<>();

        //copy  to newCompressedLine
        for (Block block : this.formattedLine) {
            if (block.id != -1) {
                //file blocks
                newCompressedLine.add(new Block(block.id, block.count));
            } else {
                //empty space blocks
                newCompressedLine.add(new Block(-1, block.count));
            }
        }

        int i = 0;
        int j = newCompressedLine.size() - 1;

        while (i < j) {
            //left
            while (i < newCompressedLine.size() && newCompressedLine.get(i).id != -1) {
                i++;
            }

            //right
            while (j >= 0 && newCompressedLine.get(j).id == -1) {
                j--;
            }

            if (i < j) {
                Block temp = newCompressedLine.get(i);
                newCompressedLine.set(i, newCompressedLine.get(j));
                newCompressedLine.set(j, temp);
                i++;
                j--;
            }
            System.out.println(formatAsString(newCompressedLine));
        }

        this.compressedLine = newCompressedLine;
    }

    public void calcChecksum() {
        long checksum = 0;
        int pos = 0;

        for (Block block : compressedLine) {
            //leere Blöcke
            if (block.id == -1) {
                continue;
            }

            for (int i = 0; i < block.count; i++) {
                checksum += Long.parseLong(String.valueOf(block.id)) * pos++;
            }
        }

        this.checksum = checksum;
    }

    public long getChecksum() {
        return checksum;
    }

    public ArrayList<Block> getFormattedLine() {
        return formattedLine;
    }

    public ArrayList<Block> getCompressedLine() {
        return compressedLine;
    }

    //für test
    public void setCompressedLine(ArrayList<Block> compressedLine) {
        this.compressedLine = compressedLine;
    }

    public String toString() {
        return "Checksum: " + checksum;
    }

    public String formatAsString(ArrayList<Block> line) {
        StringBuilder sb = new StringBuilder();
        for (Block block : line) {
            if (block.id == -1) {
                sb.append(".".repeat(block.count));
            } else {
                sb.append(String.valueOf(block.id).repeat(block.count));
            }
        }
        return sb.toString();
    }
}
