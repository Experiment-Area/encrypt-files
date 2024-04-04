import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EncryptionFolder {

    public static void main(String[] args) throws IOException {

        File folder = new File(new File(System.getenv("HOME"), "Downloads"), "EncryptDecrypt");

        if (!folder.exists()) {
            System.out.println("Folder nt found");
            return;
        }

//      to encrypt using fileWalker
        fileWalker(folder);

    }

    private static void fileWalker(File folder) throws IOException {

        File[] folderContent = folder.listFiles();
        for (File file : folderContent) {
            if (file.isDirectory()) {
                fileWalker(file);
            }
            encrypt(file);
//            decrypt(file);
        }
    }


    private static void encrypt(File folder) throws IOException {

        String SIGNATURE = "Kasun Ranasinghe";

        byte[] byteArray = new byte[(int) folder.length()];
        try (FileInputStream fis = new FileInputStream(folder)) {
            fis.read(byteArray);
        }

        //complement the bit pattern
        for (int i = 0; i < byteArray.length; i++) {
            byteArray[i] = (byte) ~byteArray[i];
        }

        try (FileOutputStream fos = new FileOutputStream(folder)) {
            fos.write(SIGNATURE.getBytes());
            fos.write(byteArray);
        }
    }

    private static void decrypt(File folder) throws IOException{

        byte[] byteArray = new byte[(int)folder.length()];
        try(FileInputStream fis = new FileInputStream(folder)) {

            byte[] SIGNATURE  = "Kasun Ranasinghe".getBytes();
            fis.skip(SIGNATURE.length);

            fis.read(byteArray);
        }

        for (int i = 0; i < folder.length(); i++) {
            byteArray[i] = (byte) ~byteArray[i];
        }

        try(FileOutputStream fos = new FileOutputStream(folder)) {
            fos.write(byteArray);
        }
    }
}
