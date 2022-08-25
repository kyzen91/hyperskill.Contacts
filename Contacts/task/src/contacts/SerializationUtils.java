package contacts;

import java.io.*;

public class SerializationUtils {

    static void serialize(String directory, Object object) {
        try (FileOutputStream fos = new FileOutputStream(directory);
             BufferedOutputStream bos = new BufferedOutputStream(fos);
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            oos.writeObject(object);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static Object deserialize(String directory)  {
        try (FileInputStream fis = new FileInputStream(directory);
             BufferedInputStream bis = new BufferedInputStream(fis);
             ObjectInputStream ois = new ObjectInputStream(bis)) {

            Object obj = ois.readObject();
            return obj;

        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
