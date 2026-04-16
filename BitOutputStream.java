import java.io.FileOutputStream;
import java.io.IOException;

public class BitOutputStream implements AutoCloseable {
    private FileOutputStream out;
    private int currentByte;
    private int bitCount;

    public BitOutputStream(FileOutputStream out) {
        this.out = out;
        this.currentByte = 0;
        this.bitCount = 0;
    }

    public void writeBit(boolean bit) throws IOException {
        currentByte = (currentByte << 1) | (bit ? 1 : 0);
        bitCount++;
        
        if (bitCount == 8) {
            out.write(currentByte);
            currentByte = 0;
            bitCount = 0;
        }
    }

    @Override
    public void close() throws IOException {
        if (bitCount > 0) {
            currentByte = currentByte << (8 - bitCount); // Pad remaining bits with 0s
            out.write(currentByte);
        }
        out.close();
    }
}
