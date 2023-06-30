package in.co.rays.Project_4.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.Project_4.Bean.UserBean;
import in.co.rays.Project_4.Exception.ApplicationException;
import in.co.rays.Project_4.Exception.DuplicateRecordException;
import in.co.rays.Project_4.Exception.RecordNotFoundException;
import in.co.rays.Project_4.Model.UserModel;

public class UserModelTest {

	  public static UserModel model = new UserModel();
	  
	  public static void main(String[] args) throws ParseException,
      DuplicateRecordException {
   testAdd();//success
   //testDelete();//success
  // testUpdate();//success
   //testFindByPK();//success
   //testFindByLogin();//success
   //testSearch();//success
   //testGetRoles();//success
   //testList();//success
   //testAuthenticate();//success
  // testRegisterUser();//success
  // testchangePassword();//
  // testforgetPassword();//
  //testresetPassword();//

}
	  public static void testAdd() throws ParseException,
      DuplicateRecordException {

  try {
      UserBean bean = new UserBean();
      SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyy");

      
      bean.setFirstName("Shivam");
      bean.setLastName("Gupta");
      bean.setLogin("guptaji1234@gmail.com");
      bean.setPassword("Qwerty@007");
      bean.setDob(sdf.parse("12-12-1996"));
      bean.setRoleId(1);
      bean.setUnSuccessfulLogin(2);
      bean.setGender("Male");
      bean.setLastLogin(new Timestamp(new Date().getTime()));
      bean.setLock("Yes");
      bean.setConfirmPassword("Qwerty@007");
      long pk = model.add(bean);
      UserBean addedbean = model.findByPK(pk);
      System.out.println("Test add succ");
      if (addedbean == null) {
          System.out.println("Test add fail");
      }
  } catch (ApplicationException e) {
      e.printStackTrace();
  }
}
	  public static void testUpdate() {

	        try {
	            UserBean bean = model.findByPK(1);
	            bean.setFirstName("Mohit");
	            bean.setLastName("Rai");
	            bean.setLogin("kartikayrai09@gmail.com");
	            bean.setPassword("Qwerty@007");

	            model.update(bean);

	            UserBean updatedbean = model.findByPK(1);
	            if (!"kartikay".equals(updatedbean.getLogin())) {
	                System.out.println("Test Update fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        } catch (DuplicateRecordException e) {
	            e.printStackTrace();
	        }
	    }
	  public static void testDelete() {

	        try {
	            UserBean bean = new UserBean();
	            long pk = 1;
	            bean.setId(pk);
	            model.delete(bean);
	            System.out.println("Test Delete succ" + bean.getId());
	            UserBean deletedbean = model.findByPK(pk);
	            if (deletedbean == null) {
	                System.out.println("Test Delete fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	   public static void testFindByPK() {
	        try {
	            UserBean bean = new UserBean();
	            long pk = 1;
	            bean = model.findByPK(pk);
	            if (bean == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getFirstName());
	            System.out.println(bean.getLastName());
	            System.out.println(bean.getLogin());
	            System.out.println(bean.getPassword());
	            System.out.println(bean.getDob());
	            System.out.println(bean.getRoleId());
	            System.out.println(bean.getUnSuccessfulLogin());
	            System.out.println(bean.getGender());
	            System.out.println(bean.getLastLogin());
	            System.out.println(bean.getLock());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	   public static void testFindByLogin() {
	        try {
	            UserBean bean = new UserBean();
	            bean = model.findByLogin("kartikayrai09@gmail.com");
	            if (bean == null) {
	                System.out.println("Test Find By PK fail");
	            }
	            System.out.println(bean.getId());
	            System.out.println(bean.getFirstName());
	            System.out.println(bean.getLastName());
	            System.out.println(bean.getLogin());
	            System.out.println(bean.getPassword());
	            System.out.println(bean.getDob());
	            System.out.println(bean.getRoleId());
	            System.out.println(bean.getUnSuccessfulLogin());
	            System.out.println(bean.getGender());
	            System.out.println(bean.getLastLogin());
	            System.out.println(bean.getLock());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	   public static void testGetRoles() {

	        try {
	            UserBean bean = new UserBean();
	            List list = new ArrayList();
	            bean.setRoleId(1);
	            list = model.getRoles(bean);
	            if (list.size() < 0) {
	                System.out.println("Test Get Roles fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (UserBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getFirstName());
	                System.out.println(bean.getLastName());
	                System.out.println(bean.getLogin());
	                System.out.println(bean.getPassword());
	                System.out.println(bean.getDob());
	                System.out.println(bean.getRoleId());
	                System.out.println(bean.getUnSuccessfulLogin());
	                System.out.println(bean.getGender());
	                System.out.println(bean.getLastLogin());
	                System.out.println(bean.getLock());
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	   public static void testSearch() {

	        try {
	            UserBean bean = new UserBean();
	            List list = new ArrayList();
	            bean.setFirstName("Kartikay");
	            list = model.search(bean, 0, 0);
	            if (list.size() < 0) {
	                System.out.println("Test Serach fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (UserBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getFirstName());
	                System.out.println(bean.getLastName());
	                System.out.println(bean.getLogin());
	                System.out.println(bean.getPassword());
	                System.out.println(bean.getDob());
	                System.out.println(bean.getRoleId());
	                System.out.println(bean.getUnSuccessfulLogin());
	                System.out.println(bean.getGender());
	                System.out.println(bean.getLastLogin());
	                System.out.println(bean.getLock());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	   public static void testList() {

	        try {
	            UserBean bean = new UserBean();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (UserBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getFirstName());
	                System.out.println(bean.getLastName());
	                System.out.println(bean.getLogin());
	                System.out.println(bean.getPassword());
	                System.out.println(bean.getDob());
	                System.out.println(bean.getRoleId());
	                System.out.println(bean.getUnSuccessfulLogin());
	                System.out.println(bean.getGender());
	                System.out.println(bean.getLastLogin());
	                System.out.println(bean.getLock());
	                System.out.println(bean.getMobileNo());
	                System.out.println(bean.getCreatedBy());
	                System.out.println(bean.getModifiedBy());
	                System.out.println(bean.getCreatedDateTime());
	                System.out.println(bean.getModifiedDateTime());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	   public static void testAuthenticate() {

	        try {
	            UserBean bean = new UserBean();
	            bean.setLogin("kartikayrai09@gmail.com");
	            bean.setPassword("Qwerty@007");
	            bean = model.authenticate(bean.getLogin(), bean.getPassword());
	            if (bean != null) {
	                System.out.println("Successfully login");

	            } else {
	                System.out.println("Invalid login Id & password");
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	   public static void testRegisterUser() throws ParseException {
	        try {
	            UserBean bean = new UserBean();
	            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	            
	            bean.setFirstName("aman");
	            
	            bean.setLogin("aman1234@gmail.com");
	            bean.setPassword("Qwerty@007");
	            bean.setConfirmPassword("Qwerty@007");
	            bean.setDob(sdf.parse("12/09/1995"));
	            bean.setGender("Male");
	            bean.setRoleId(2);
	            long pk = model.registerUser(bean);
	            System.out.println("Successfully register");
	            System.out.println(bean.getFirstName());
	            System.out.println(bean.getLogin());
	            System.out.println(bean.getLastName());
	            System.out.println(bean.getDob());
	            UserBean registerbean = model.findByPK(pk);
	            if (registerbean != null) {
	                System.out.println("Test registation fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        } catch (DuplicateRecordException e) {
	            e.printStackTrace();
	        }
	    }
	   public static void testchangePassword() {

	        try {
	            UserBean bean = model.findByLogin("ranjitchoudhary20@gmail.com");
	            String oldPassword = bean.getPassword();
	            bean.setId(15l);
	            bean.setPassword("88");
	            bean.setConfirmPassword("kk");
	            String newPassword = bean.getPassword();
	            try {
	                model.changePassword(15L, oldPassword, newPassword);
	                System.out.println("password has been change successfully");
	            } catch (RecordNotFoundException e) {
	                e.printStackTrace();
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	   public static void testforgetPassword() {
	        try {
	            boolean b = model.forgetPassword("ranjitchoudhary20@gmail.com");

	            System.out.println("Suucess : Test Forget Password Success");

	        } catch (RecordNotFoundException e) {
	            e.printStackTrace();
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	   public static void testresetPassword() {
	        UserBean bean = new UserBean();
	        try {
	            bean = model.findByLogin("ranjitchoudhary20@gmail.com");
	            if (bean != null) {
	                boolean pass = model.resetPassword(bean);
	                if (pass = false) {
	                    System.out.println("Test Update fail");
	                }
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	}


