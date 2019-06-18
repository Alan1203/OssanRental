package _00_init.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialClob;

public class SystemUtils2018 {
	
	public static Blob fileToBlob(String imageFileName) throws IOException, SQLException {
		File imageFile = new File(imageFileName);
		long size = imageFile.length();
		byte[] b = new byte[(int) size];
		Blob sb = null;
		try (FileInputStream fis = new FileInputStream(imageFile);) {
			fis.read(b);
			sb = new SerialBlob(b);
		}
		return sb;
	}

	public static Clob fileToClob(String textFileName) throws IOException, SQLException {
		Clob clob = null;
		try (InputStreamReader isr = new InputStreamReader(new FileInputStream(textFileName), "UTF-8");) {
			char[] c = new char[8192];
			StringBuffer buf = new StringBuffer();
			int len = 0;
			while ((len = isr.read(c)) != -1) {
				buf.append(new String(c, 0, len));
			}
			char[] ca = buf.toString().toCharArray();
			clob = new SerialClob(ca);
		}
		return clob;
	}

	public static void clobToFile(Clob clob, File file, String encoding) throws IOException, SQLException {
		try (Reader reader = clob.getCharacterStream();
				BufferedReader br = new BufferedReader(reader);
				FileOutputStream fos = new FileOutputStream(file);
				OutputStreamWriter osw = new OutputStreamWriter(fos, encoding);
				PrintWriter out = new PrintWriter(osw);) {
			String line = null;
			while ((line = br.readLine()) != null) {
				out.println(line);
			}
		}
	}

	public static Blob fileToBlob(InputStream is, long size) throws IOException, SQLException {
		byte[] b = new byte[(int) size];
		SerialBlob sb = null;
		is.read(b);
		sb = new SerialBlob(b);
		return sb;
	}

	public static String extractFileName(String pathName) throws IOException, SQLException {
		return pathName.substring(pathName.lastIndexOf("/") + 1);
	}

//	public static String clobToString(Clob clob) {
//        if (clob == null)
//            return "無內容";
//
//        StringBuilder sb = new StringBuilder();
//	    try {
//	        Reader reader = clob.getCharacterStream();
//	        BufferedReader br = new BufferedReader(reader);
//
//	        String line;
//	        while(null != (line = br.readLine())) {
//	            sb.append(line);
//	            sb.append("\r\n");
//	        }
//	        br.close();
//	    } catch (SQLException e) {
//	    	System.out.println("ClobToString的SQL發生例外");
//	    	e.printStackTrace();
//			throw new RuntimeException("ClobToString的SQL發生例外: " 
//										+ e.getMessage());
//			
//	    } catch (IOException e) {
//	    	System.out.println("ClobToString的IO發生例外");
//	    	e.printStackTrace();
//			throw new RuntimeException("ClobToString的IO發生例外: " 
//										+ e.getMessage());
//	    }
//	    return sb.toString();
//	}
//	
//	public static Clob stringToClob(String str) {
//        if (null == str)
//            return null;
//        else {
//            try {
//                java.sql.Clob c = new javax.sql.rowset.serial.SerialClob(str
//                        .toCharArray());
//                return c;
//            } catch (Exception e) {
//                return null;
//            }
//        }
//    }

	public static boolean getRandomBoolean() {
		return Math.random() < 0.5;
	}

	public static String BlobToBase64String(Blob blob) {
		String result = "";
		
		try {
			InputStream msgContent = blob.getBinaryStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[100];
			int n = 0;
			while (-1 != (n = msgContent.read(buffer))) {
				output.write(buffer, 0, n);
			}
			result = Base64.getEncoder().encodeToString(output.toByteArray()) ;
			output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		return result; 
	}

	public static String clobToStringWindows(Clob data) {
		StringBuilder sb = new StringBuilder();
		try {
			Reader reader = data.getCharacterStream();
			BufferedReader br = new BufferedReader(reader);

			String line;
			while (null != (line = br.readLine())) {
				sb.append(line + "\r\n");
			}
			br.close();
		} catch (SQLException e) {
			System.out.println("ClobToString的SQL發生例外");
			e.printStackTrace();
			throw new RuntimeException("ClobToString的SQL發生例外: " + e.getMessage());

		} catch (IOException e) {
			System.out.println("ClobToString的IO發生例外");
			e.printStackTrace();
			throw new RuntimeException("ClobToString的IO發生例外: " + e.getMessage());
		}
		return sb.toString();
	}

	public static Clob stringToClob(String str) {
		if (null == str)
			return null;
		else {
			try {
				Clob c = new SerialClob(str.toCharArray());
				return c;
			} catch (Exception e) {
				System.out.println("stringToClob的IO發生例外");
				return null;
			}
		}
	}

}
