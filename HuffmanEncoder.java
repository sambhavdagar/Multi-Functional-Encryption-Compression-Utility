import java.io.*;
import java.util.*;

class HuffmanNode implements Comparable<HuffmanNode> {
    int byteValue;
    int frequency;
    HuffmanNode left, right;

    public HuffmanNode(int byteValue, int frequency) {
        this.byteValue = byteValue;
        this.frequency = frequency;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return Integer.compare(this.frequency, other.frequency);
    }
}

public class HuffmanEncoder {
    private Map<Integer, String> codeMap = new HashMap<>();

    public void encodeFile(String inputFilePath, String outputFilePath) throws IOException {
        int[] frequencies = new int[256];
        
        // 1. Read file bytes -> frequencies
        try (FileInputStream fis = new FileInputStream(inputFilePath)) {
            int b;
            while ((b = fis.read()) != -1) {
                frequencies[b]++;
            }
        }

        // 2. Build Priority Queue
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>();
        for (int i = 0; i < 256; i++) {
            if (frequencies[i] > 0) {
                pq.add(new HuffmanNode(i, frequencies[i]));
            }
        }

        if (pq.isEmpty()) return;

        // 3. Build Huffman Tree
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();
            HuffmanNode right = pq.poll();
            HuffmanNode parent = new HuffmanNode(-1, left.frequency + right.frequency);
            parent.left = left;
            parent.right = right;
            pq.add(parent);
        }

        HuffmanNode root = pq.poll();

        // 4. Generate Code Map
        buildCodeMap(root, "");

        // 5. Write to BitOutputStream
        try (FileInputStream fis = new FileInputStream(inputFilePath);
             BitOutputStream bos = new BitOutputStream(new FileOutputStream(outputFilePath))) {
            int b;
            while ((b = fis.read()) != -1) {
                String code = codeMap.get(b);
                for (char c : code.toCharArray()) {
                    bos.writeBit(c == '1');
                }
            }
        }
    }

    private void buildCodeMap(HuffmanNode node, String code) {
        if (node == null) return;
        if (node.left == null && node.right == null) {
            codeMap.put(node.byteValue, code);
            return;
        }
        buildCodeMap(node.left, code + "0");
        buildCodeMap(node.right, code + "1");
    }
}
