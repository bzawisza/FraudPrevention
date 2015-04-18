import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class InputReader {

    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;

    public InputReader(InputStream stream) {
        this.stream = stream;
    }

    public static boolean isSpaceChar(int c) {
        return c == '\n' || c == '\r' || c == -1 || c == ',';
    }

    public int read() {
        if (this.numChars == -1)
            throw new InputMismatchException();
        if (this.curChar >= this.numChars) {
            this.curChar = 0;
            try {
                this.numChars = this.stream.read(this.buf);
            } catch (IOException e) {
                throw new InputMismatchException();
            }
            if (this.numChars <= 0)
                return -1;
        }
        return this.buf[this.curChar++];
    }

    public int nextInt() {
        int c = this.read();
        while (isSpaceChar(c))
            c = this.read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = this.read();
        }
        int res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c & 15;
            c = this.read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String next() {
        int c = this.read();
        while (isSpaceChar(c))
            c = this.read();
        StringBuilder res = new StringBuilder();
        do {
            res.appendCodePoint(c);
            c = this.read();
        } while (!isSpaceChar(c));
        return res.toString();
    }

}