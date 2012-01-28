import java.util.*;
import static java.lang.System.*;

/** Vereinfachte Handhabung grundlegender IO-Funktionalität im Rahmen der Veranstaltung
 * 
 *    Software Entwicklung 1
 *    Wintersemester 2011/2012
 *    AG Softwaretechnik
 *    Technische Universität Kaiserslautern
 *
 *  @author Markus Reitz
 *  @version 1.0
 */
public class IO {
  public static void print(Object object) {
    out.print(object);
  }
  
  public static void println(Object object) {
    out.println(object);
  }
  
  public static int readInt() {
    try {
      return new Scanner(in).nextInt();
    }
    catch(Throwable ex) {
      throw new RuntimeException("Input not an integer",ex);
    }
  }
  
  public static double readDouble() {
    try {
      Scanner s = new Scanner(in);
      s.useLocale(Locale.ENGLISH);
      return s.nextDouble();
    }
    catch(Throwable ex) {
      throw new RuntimeException("Input not a double",ex);
    }
  }

  public static String readString() {
    try {
      return new Scanner(in).nextLine();
    }
    catch(Throwable ex) {
      throw new RuntimeException("Error in reading input",ex);
    }
  }

  public static char readChar() {
    try {
      String result=readString();
      
      if (result.length()>1)
        out.println("*** Warning: Input too long.\n"+
                    "***          Rest of input discarded.");
      
      return result.charAt(0);
    }
    catch(IndexOutOfBoundsException ex) {
      throw new RuntimeException("Empty input",ex);
    }
  }
}