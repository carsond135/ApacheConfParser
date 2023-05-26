package apache.conf.global;

import java.io.BufferedReader;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.RegexFileFilter;

import apache.conf.parser.File;

public class Utils {

    public static String readFileAsString(File file, Charset charset) throws java.io.IOException {
        return FileUtils.readFileToString(file, charset);
    }

    public static boolean copyFile(File source, File destination) {
        try {
            FileUtils.copyFile(source, destination);
            setPermissions(destination);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean moveFile(File source, File destination) {
        try {
            if (destination.exists()) {
                FileUtils.forceDelete(destination);
            }
            FileUtils.moveFile(source, destination);
            setPermissions(destination);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean copyDirectory(File srcDir, File destDir) {
        try {
            FileUtils.copyDirectory(srcDir, destDir);
            setPermissions(destDir);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteDirectory(File path) {
        try {
            FileUtils.deleteDirectory(path);
        } catch (IOException e) {
            return false;
        }
        return true;
    }

    public static boolean moveDirectory(File srcDir, File destDir) {
        try {
            if (destDir.exists()) {
                FileUtils.deleteDirectory(destDir);
            }
            FileUtils.moveDirectory(srcDir, destDir);
            setPermissions(destDir);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String[] getFileList(File directory) throws IOException {
        return getFileList(directory, "^(.*)$");
    }

    public static String[] getFileList(File directory, String fileNameRegex) throws IOException {
        Queue<File> queue = new LinkedList<File>();
        queue.add(directory);
        ArrayList<String> fileList = new ArrayList<String>();
        FileFilter fileFilter = new RegexFileFilter(fileNameRegex);
        while (!queue.isEmpty()) {
            File currentDirectory = (File) queue.remove();
            java.io.File children[] = currentDirectory.listFiles();
            File currFile;
            for (java.io.File child : children) {
                currFile = new File(currentDirectory, child.getName());
                if (currFile.isDirectory()) {
                    queue.add(currFile);
                }
                if (currFile.isFile() && fileFilter.accept(currFile)) {
                    fileList.add(currFile.getAbsolutePath());
                }
            }
        }
        return fileList.toArray(new String[fileList.size()]);
    }

    public static void removeDuplicates(ArrayList a) {
        HashSet hs = new HashSet();
        hs.addAll(a);
        a.clear();
        a.addAll(hs);
    }
}