package _00_init;

/*  
    程式說明：建立表格與設定初始測試資料。
    表格包括：Book, BookCompany, Member, Orders, OrderItems
 
*/
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.web.store.model.OssanBean;

import _00_init.util.GlobalService;
import _00_init.util.HibernateUtils;
import _00_init.util.RandomDates;
import _00_init.util.SystemUtils2018;

public class OssanInsert {
	public static final String UTF8_BOM = "\uFEFF"; // 定義 UTF-8的BOM字元

	public static void main(String args[]) {
		
		//Hibernate方法開始
		String line = "";

		SessionFactory factory = HibernateUtils.getSessionFactory();
		Session session = factory.getCurrentSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			// 1. 由"data/inputossanNew"逐筆讀入
			try (
				FileReader fr = new FileReader("data/inputossanNew.txt"); 
				BufferedReader br = new BufferedReader(fr);
			) {
				while ((line = br.readLine()) != null) {
					if (line.startsWith(UTF8_BOM)) {
						line = line.substring(1);
					}
					String[] token = line.split("\\|");
					OssanBean ob = new OssanBean();
					
					ob.setEmail(token[0]);
					ob.setPassword(GlobalService.
							getMD5Endocing(GlobalService.encryptString(token[1])));
					ob.setName(token[2]);
					ob.setNickname(token[3]);
					ob.setUniqueId(token[4]);
					ob.setCity(token[5]);
					ob.setDistrict(token[6]);
					ob.setAddress(token[7]);
					ob.setPhone(token[8]);
					
					ob.setOssanImage(SystemUtils2018.fileToBlob(token[9]));
					ob.setFileName(token[9].substring(token[9].lastIndexOf("/") + 1));
					
					ob.setQuote(SystemUtils2018.clobToStringWindows(
							SystemUtils2018.fileToClob(token[10])));
					ob.setIntro(SystemUtils2018.fileToClob(token[11]));
					
					ob.setBirthday(Date.valueOf(RandomDates.createRandomDate(1940, 1990)));
					ob.setRegisterTime(new java.sql.Timestamp(System.currentTimeMillis()));
					
					ob.setPrivilege(token[12]);
					
					
					if(ob.getCity().equals("新北市")) {
						ob.setTwNorth(true);
					} else if (ob.getCity().equals("台中市")) {
						ob.setTwMiddle(true);
					} else if (ob.getCity().equals("台南市")) {
						ob.setTwSouth(true);
					} else {
						ob.setTwOther(true);
					}
					

					session.save(ob);
				}
				
			} catch (IOException e) {
				System.err.println("新建Ossan表格時發生IO例外: " + e.getMessage());
			}
			
			OssanBean obAdmin = new OssanBean();
			obAdmin.setEmail("admin@gmail.com");
			obAdmin.setPassword(GlobalService.getMD5Endocing(GlobalService.encryptString("Do!ng123")));
			obAdmin.setName("管理員");
			obAdmin.setPrivilege("Admin");
			session.save(obAdmin);
			
			session.flush();
			System.out.println("Ossan 資料新增成功");
			
            tx.commit();
		} catch (Exception e) {
			System.err.println("新建表格時發生例外: " + e.getMessage());
			e.printStackTrace();
			tx.rollback();
		} 
        factory.close();
//        ((ConfigurableApplicationContext) ctx).close();
	}
}