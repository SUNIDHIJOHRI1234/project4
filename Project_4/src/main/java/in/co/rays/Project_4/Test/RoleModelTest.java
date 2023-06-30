package in.co.rays.Project_4.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.rays.Project_4.Bean.RoleBean;
import in.co.rays.Project_4.Exception.ApplicationException;
import in.co.rays.Project_4.Exception.DuplicateRecordException;
import in.co.rays.Project_4.Model.RoleModel;

public class RoleModelTest {

	public static RoleModel model = new RoleModel();

	public static void main(String[] args) throws ParseException {
		// testAdd();//success
		// testDelete();//success
		// testUpdate();//SUCCESS
		// testFindByPK();success
		 testFindByName();
		// testSearch();//success
		// testList();//success

	}

	public static void testAdd() throws ParseException {

		try {
			RoleBean bean = new RoleBean();

			bean.setName("shivam");
			bean.setDescription("kumar");
			bean.setCreatedBy("admin");
			bean.setModifiedBy("admin");
			bean.setCreatedDateTime(new Timestamp(new Date().getTime()));
			bean.setModifiedDateTime(new Timestamp(new Date().getTime()));
			long pk = model.add(bean);
			RoleBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("Test add fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	public static void testDelete() {

		try {
			RoleBean bean = new RoleBean();
			long pk = 1;
			bean.setId(pk);
			model.delete(bean);
			RoleBean deletedbean = model.findByPK(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() {

		try {
			RoleBean bean = model.findByPK(1);
			bean.setName("Naman");
			bean.setDescription("ist update in role");
			model.update(bean);

			RoleBean updatedbean = model.findByPK(6L);
			if (!"Naman".equals(updatedbean.getName())) {
				System.out.println("Test Update fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
	}

	public static void testFindByPK() {
		try {
			RoleBean bean = new RoleBean();
			long pk = 1;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByName() {
		try {
			RoleBean bean = new RoleBean();
			bean = model.findByName("shivam");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testSearch() {

		try {
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			bean.setName("Naman");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testList() {

		try {
			RoleBean bean = new RoleBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (RoleBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getName());
				System.out.println(bean.getDescription());
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
