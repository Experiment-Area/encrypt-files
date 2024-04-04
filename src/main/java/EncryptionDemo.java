import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncryptionDemo {

    public static void main(String[] args) throws IOException {

        String SIGNATURE = "Kasun Ranasinghe";                          //Optional
        File originalFile = new File("maxresdefault.jpg");
        File encryptedFile = new File(originalFile.getName() + "encrypted");

        if (!encryptedFile.exists()) encryptedFile.createNewFile();

        try (FileInputStream fis = new FileInputStream(originalFile);
             FileOutputStream fos = new FileOutputStream(encryptedFile)) {

            fos.write(SIGNATURE.getBytes()); //Signature convert to byte

            while (true) {
                int read = fis.read();
                if (read == -1) break;
                fos.write(read + 2);           //revers bytes (~read)
            }
            originalFile.delete();
        }
    }
}
