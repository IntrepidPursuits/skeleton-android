package io.intrepid.skeleton.testutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class TestFileUtils {
    /**
     * Reads a file and returns its content as a string
     *
     * @param fileName    The name of the file. The file should be under the "resources" directory
     * @param classLoader Make sure the class loader is not a bootstrap class loader (ex: the loader
     *                    from the String class)
     * @return the content of the file as a string
     */
    public static String getStringFromFile(String fileName, ClassLoader classLoader) {
        URL url = classLoader.getResource(fileName);
        File file = new File(url.getPath());
        try {
            FileInputStream fin = new FileInputStream(file);
            String string = convertStreamToString(fin);
            fin.close();
            return string;
        } catch (IOException e) {
            throw new RuntimeException("Error trying to read test file", e);
        }
    }

    public static String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        try {
            String line = reader.readLine();
            while (line != null) {
                sb.append(line).append("\n");
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return sb.toString();
    }
}
