package in.co.rays.Project_4.Test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.Project_4.Bean.CollegeBean;
import in.co.rays.Project_4.Exception.ApplicationException;
import in.co.rays.Project_4.Exception.DuplicateRecordException;
import in.co.rays.Project_4.Model.CollegeModel;

public class CollegeModelTest {
	public static CollegeModel model = new CollegeModel();

	public static void main(String[] args) throws DuplicateRecordException {
		// testAdd();//success
		// testDelete();//success
		// testUpdate();//success
		// testFindByName();//success
		// testFindByPK();success
		// testSearch();//success
		testList();//success

	}

	public static void testAdd() throws DuplicateRecordException {

		try {
			CollegeBean bean = new CollegeBean();
			bean.setName("Shivam");
			bean.setAddress("gwaltoli");
			bean.setState("mp");
			bean.setCity("dawa bazar");
			bean.setPhoneNo("9008900345");
			bean.setCreatedBy("Admin");
			bean.setModifiedBy("Admin");
			bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
			bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			System.out.println("Test add succ");
			CollegeBean addedBean = model.findByPK(pk);
			if (addedBean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() {

		try {
			CollegeBean bean = new CollegeBean();
			long pk = 1;
			bean.setId(pk);
			model.delete(bean);
			System.out.println("Test Delete succ");
			CollegeBean deletedBean = model.findByPK(pk);
			if (deletedBean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		try {
			CollegeBean bean = model.findByPK(1);
			bean.setName("Mohit");
			bean.setAddress("cm2nd 571");

			model.update(bean);
			System.out.println("Test Update succ");
			CollegeBean updateBean = model.findByPK(1);
			if (!"Mohit".equals(updateBean.getName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByName() {

		try {
			CollegeBean bean = model.findByName("Kartikay");
			if (bean == null) {
				System.out.println("Test Find By Name fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDateTime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByPK() {
		try {
			CollegeBean bean = null;
			long pk = 1;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
			System.out.println(bean.getCity());
			System.out.println(bean.getPhoneNo());
			System.out.println(bean.getCreatedBy());
			System.out.println(bean.getCreatedDateTime());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getModifiedDateTime());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testSearch() {
		try {
			CollegeBean bean = new CollegeBean();
			;
			List list = new ArrayList();
			bean.setName("Mohit");
			list = model.search(bean, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {

				bean = (CollegeBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getState());
				System.out.println(bean.getCity());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDateTime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDateTime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testList() {

		try {
			CollegeBean bean = null;
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = new CollegeBean();
				bean = (CollegeBean) it.next();

				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getAddress());
				System.out.println(bean.getState());
				System.out.println(bean.getCity());
				System.out.println(bean.getPhoneNo());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreatedDateTime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDateTime());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

}
