import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DecryptionDemo {

    public static void main(String[] args) throws IOException {

        File encryptedFile = new File("maxresdefault.jpgencrypted");
        File originalFile = new File("maxresdefault.jpg");

        if (!originalFile.exists()) originalFile.createNewFile();

        try (FileInputStream fis = new FileInputStream(encryptedFile);
             FileOutputStream fos = new FileOutputStream(originalFile)) {

            byte[] SIGNATURE = "Kasun Ranasinghe".getBytes();
            fis.skip(SIGNATURE.length);

            while (true) {
                int read = fis.read();
                if (read == -1) break;
                fos.write(read - 2);           //revers bytes (~read)
            }

            // Read and write in chunks(for Bigfile)
//            while (true) {
//                byte[] buffer = new byte[1024];
//                int read = fis.read(buffer);
//                if (read == -1) break;
//                for (int i = 0; i < read; i++) {
//                    fos.write(~buffer[i]);
//                }
//            }
        }
    }
}

