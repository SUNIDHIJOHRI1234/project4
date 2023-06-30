package in.co.rays.Project_4.Model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.rays.Project_4.Bean.CourseBean;
import in.co.rays.Project_4.Bean.SubjectBean;
import in.co.rays.Project_4.Bean.TimeTableBean;
import in.co.rays.Project_4.Exception.ApplicationException;
import in.co.rays.Project_4.Exception.DatabaseException;
import in.co.rays.Project_4.Exception.DuplicateRecordException;
import in.co.rays.Project_4.Utility.JDBCDataSource;


/**
 * The Class TimeTableModel.
 * @author Darshan
 *
 */
public class TimeTableModel {
	private static Logger log = Logger.getLogger(TimeTableModel.class);
	/**
     * Find next PK of TimeTable
     *
     * @throws DatabaseException
     */
	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_TIMETABLE");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}
	/**
     * Add a TimeTable
     *
     * @param bean
     * @throws DatabaseException
     *
     */
	public long add(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		CourseModel cModel = new CourseModel();
		CourseBean CourseBean = cModel.FindByPK(bean.getCourseId());
		bean.setCourseName(CourseBean.getName());

		SubjectModel smodel = new SubjectModel();
		SubjectBean SubjectBean = smodel.FindByPK(bean.getSubjectId());
		bean.setSubjectName(SubjectBean.getName());

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT ST_TIMETABLE VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setLong(4, bean.getSubjectId());
			pstmt.setString(5, bean.getSubjectName());
			pstmt.setString(6, bean.getSemester());
			pstmt.setDate(7, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(8, bean.getTime());
			pstmt.setString(9, SubjectBean.getDescription());
			pstmt.setString(10, bean.getCreatedBy());
			pstmt.setString(11, bean.getModifiedBy());
			pstmt.setTimestamp(12, bean.getCreatedDateTime());
			pstmt.setTimestamp(13, bean.getModifiedDateTime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception....", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback Exception" + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;

	}
	/**
     * Delete a TimeTable
     *
     * @param bean
     * @throws DatabaseException
     */
	public void delete(TimeTableBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			int i = pstmt.executeUpdate();

			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception...", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : delete Rollback Exception" + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Timeteble");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete End");
	}
	/**
     * Update a TimeTable
     *
     * @param bean
     * @throws DatabaseException
     */
	public void update(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		CourseModel cModel = new CourseModel();
		CourseBean CourseBean = cModel.FindByPK(bean.getCourseId());
		bean.setCourseName(CourseBean.getName());

		SubjectModel smodel = new SubjectModel();
		SubjectBean SubjectBean = smodel.FindByPK(bean.getSubjectId());
		bean.setSubjectName(SubjectBean.getName());

		TimeTableBean bean1 = checkBycds(bean.getCourseId(), bean.getSemester(),
				new java.sql.Date(bean.getExamDate().getTime()));
		TimeTableBean bean2 = checkBycss(bean.getCourseId(), bean.getSubjectId(), bean.getSemester());
		if (bean1 != null || bean2 != null) {
			throw new DuplicateRecordException("TimeTable Already Exsist");

		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_TIMETABLE SET COURSE_ID=?, COURSE_NAME=?, SUBJECT_ID=?, SUBJECT_NAME=?, SEMESTER=?, EXAM_DATE=?, EXAM_TIME=?, DESCRIPTION=?, CREATED_BY=?, MODIFIED_BY=?, CREATED_DATETIME=?, NODIFIED_DATETIME=? where ID=?");

			pstmt.setLong(1, bean.getCourseId());
			pstmt.setString(2, bean.getCourseName());
			pstmt.setLong(3, bean.getSubjectId());
			pstmt.setString(4, bean.getSubjectName());
			pstmt.setString(5, bean.getSemester());
			pstmt.setDate(6, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(7, bean.getTime());
			pstmt.setString(8, bean.getDescription());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDateTime());
			pstmt.setTimestamp(12, bean.getModifiedDateTime());
			pstmt.setLong(13, bean.getId());

			pstmt.executeUpdate();
			// System.out.println("timetable update");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception....", e);
			try {
				conn.rollback();

			} catch (Exception ex) {
				throw new ApplicationException("Exception : update rollback Exception" + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating timetable");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	/**
     * Find User by TimeTable
     *
     * @param pk
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     */
	public TimeTableBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findBypk started");
		StringBuffer sql = new StringBuffer("select * from ST_timetable where id=?");
		TimeTableBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception .....", e);
			throw new ApplicationException("Exception : Exception in getting by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBypk End");
		return bean;
	}
	/**
     * Get List of TimeTable
     *
     * @return list : List of TimeTable
     * @throws DatabaseException
     */
	public List list() throws Exception {
		return list(0, 0);
	}
	/**
     * Get List of TimeTable with pagination
     *
     * @return list : List of TimeTable
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     */
	public List list(int pageNo, int pageSize) throws Exception {
		log.debug("model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE");

		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {

				TimeTableBean bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
				list.add(bean);

			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception...", e);
			throw new ApplicationException("Exception : Exception in getting list");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model list End");
		return list;
	}
	/**
     * Search TimeTable
     *
     * @param bean
     *            : Search Parameters
     * @throws DatabaseException
     */
	public List search(TimeTableBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}
	/**
     * Search TimeTable with pagination
     *
     * @return list : List of TimeTables
     * @param bean
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     *
     * @throws DatabaseException
     */
	public List search(TimeTableBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE 1=1 ");
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append("AND ID=" + bean.getId());
			}
			if (bean.getCourseId() > 0) {
				sql.append("AND COURSE_ID=" + bean.getCourseId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append("AND COURSE_NAME like '" + bean.getCourseName() + "%'");
			}
			if (bean.getSubjectId() > 0) {
				sql.append("AND SUBJECT_ID=" + bean.getSubjectId());
			}
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append("AND SUBJECT_NAME like '" + bean.getSubjectName() + "%'");
			}
			if (bean.getExamDate() != null && bean.getExamDate().getTime() > 0) {
				Date d = new Date(bean.getExamDate().getTime());
				sql.append("AND EXAM_DATE = '" + d + "'");
			}
			if (bean.getTime() !=null && bean.getTime().length() > 0) {
				System.out.println("done");
				sql.append("AND EXAM_TIME like '" + bean.getTime() + "%'");
			}
		}
		if (pageNo > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception.....", e);
			throw new ApplicationException("Exception in getting search");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search End");
		return list;

	}
	/**
     * Find TimeTable by Course,Subject,Semester
     *
     * @param pk
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     */
	public TimeTableBean checkBycss(long CourseId, long SubjectId, String semester) throws ApplicationException {
		Connection conn = null;
		TimeTableBean bean = null;
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE Course_ID=? AND Subject_ID=? AND Semester=?");

		try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			ps.setLong(2, SubjectId);
			ps.setString(3, semester);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			// log.error("database Exception....", e);
			throw new ApplicationException("Exception in list Method of timetable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		// log.debug("Timetable Model list method End");
		return bean;
	}
	/**
     * Find TimeTable by Course,semester,Date
     *
     * @param pk
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     */
	public TimeTableBean checkBycds(long CourseId, String Semester, Date ExamDate) throws ApplicationException {
		StringBuffer sql = new StringBuffer(
				"SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=? AND SEMESTER=? AND EXAM_DATE=?");

		Connection conn = null;
		TimeTableBean bean = null;
		Date ExDate = new Date(ExamDate.getTime());

		try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			ps.setString(2, Semester);
			ps.setDate(3, (java.sql.Date) ExamDate);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
			}
			rs.close();
		} catch (Exception e) {
			// e.printStackTrace();
			log.error("database Exception....", e);
			throw new ApplicationException("Exception in list Method of timetable Model");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Timetable Model list method End");
		return bean;

	}
	/**
     * Find TimeTable by Semester
     *
     * @param pk
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     */
	public static TimeTableBean checkBysemester(long CourseId, long SubjectId, String semester,
			java.util.Date ExamDAte) {

		TimeTableBean bean = null;

		Date ExDate = new Date(ExamDAte.getTime());

		StringBuffer sql = new StringBuffer(
				"SELECT * FROM TIMETABLE WHERE COURSE_ID=? AND SUBJECT_ID=? AND" + " SEMESTER=?");

		try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			ps.setLong(2, SubjectId);
			ps.setString(3, semester);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}
	/**
     * Find TimeTable by CourseName
     *
     * @param pk
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     */
	public static TimeTableBean checkByCourseName(long CourseId, java.util.Date ExamDate) {
		Connection conn = null;
		TimeTableBean bean = null;

		Date Exdate = new Date(ExamDate.getTime());

		StringBuffer sql = new StringBuffer("SELECT * FROM TIMETABLE WHERE COURSE_ID=? " + "AND EXAM_DATE=?");

		try {
			Connection con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setLong(1, CourseId);
			// ps.setDate(2, examdate);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setTime(rs.getString(8));
				bean.setDescription(rs.getString(9));
				bean.setCreatedBy(rs.getString(10));
				bean.setModifiedBy(rs.getString(11));
				bean.setCreatedDateTime(rs.getTimestamp(12));
				bean.setModifiedDateTime(rs.getTimestamp(13));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

}
