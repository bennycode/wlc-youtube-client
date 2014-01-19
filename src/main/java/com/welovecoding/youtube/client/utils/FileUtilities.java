package com.welovecoding.youtube.client.utils;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public final class FileUtilities {

  private FileUtilities() {
    /*
     FileOutputStream fop = new FileOutputStream(file3, true);
     byte[] contentInBytes = "Hello World".getBytes();
     fop.write(contentInBytes);
     fop.flush();
     fop.close();
     */
  }

  private static OutputStream createOutputStream(final File f) throws IOException {
    return new BufferedOutputStream(new FileOutputStream(f, true));
  }

  public static void appendStringToFileLegacy(String content, File file) throws IOException {
    if (!file.exists()) {
      file.createNewFile();
    }

    FileWriter fileWritter = new FileWriter(file.getName(), true);
    BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
    bufferWritter.write(content);
    bufferWritter.close();
  }

  // Cool
  public static String readStringFromFile(File file) throws IOException {
    FileInputStream stream = new FileInputStream(file);
    try {
      FileChannel fc = stream.getChannel();
      MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
      return Charset.defaultCharset().decode(mbb).toString();
    } finally {
      stream.close();
    }
  }

  // Cool
  public static void printFileWithBufferedReader(File file) throws FileNotFoundException, IOException {
    FileInputStream fin = new FileInputStream(file);
    String thisLine;
    BufferedReader fileInput = new BufferedReader(new InputStreamReader(fin));
    while ((thisLine = fileInput.readLine()) != null) {
      System.out.println(thisLine);
    }
  }

  // Cool
  public static void printFileWithScanner(File file) throws FileNotFoundException {
    // Delimeter "\Z" represents the end-of-file marker
    Scanner scanner = new Scanner(file).useDelimiter("\\Z");
    String contents = scanner.next();
    System.out.println(contents);
    scanner.close();
  }

  public static void writeStringToFile(String content, File file) throws IOException {
    Path target = Paths.get(file.getAbsolutePath());
    InputStream is = new ByteArrayInputStream(content.getBytes());
    Files.copy(is, target, StandardCopyOption.REPLACE_EXISTING);
  }

  public static boolean concatFiles(File infile, File outfile) {
    boolean success = false;
    StringBuilder sb = new StringBuilder();
    FileChannel inChan = null, outChan = null;
    try {
      //write the stringBuffer so it goes in the output file first:    
      PrintWriter fw = new PrintWriter(outfile);
      fw.write(sb.toString());
      fw.flush();
      fw.close();

      // create the channels appropriate for appending:
      outChan = new FileOutputStream(outfile, true).getChannel();
      inChan = new RandomAccessFile(infile, "r").getChannel();

      long startSize = outfile.length();
      long inFileSize = infile.length();
      long bytesWritten = 0;

      //set the position where we should start appending the data:
      outChan.position(startSize);
      long startByte = outChan.position();

      while (bytesWritten < infile.length()) {
        bytesWritten += outChan.transferFrom(inChan, startByte, (int) inFileSize);
        startByte = bytesWritten + 1;
      }

      inChan.close();
      outChan.close();
    } catch (Exception ex) {
      System.out.println(ex);
    } finally {
      return success;
    }
  }

  public static void appendFile(File source, File destination) throws FileNotFoundException, IOException {
    boolean append = true;
    FileOutputStream out = new FileOutputStream(destination, append);
    FileInputStream in = new FileInputStream(source);

    out.write("\r\n".getBytes()); // write line break before content of new file

    int c;
    while ((c = in.read()) != -1) {
      out.write(c);
    }

    if (in != null) {
      in.close();
    }
    if (out != null) {
      out.close();
    }
  }

  /**
   * Writes the content of file 1 into file 3 and appends file 2.
   */
  public static void concatenateFiles(File file1, File file2, File file3) throws FileNotFoundException, IOException {
    // Write content of file 1 into file 3
    Path source = Paths.get(file1.getAbsolutePath());
    Path target = Paths.get(file3.getAbsolutePath());
    Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);

    // Write content of file 2 into file 3
    appendFile(file2, file3);
  }

  public static void emptyFile(File file) throws FileNotFoundException {
    PrintWriter writer = new PrintWriter(file);
    writer.print("");
    writer.close();
  }
}