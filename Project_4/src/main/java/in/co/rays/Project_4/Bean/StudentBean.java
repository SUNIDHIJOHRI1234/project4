package in.co.rays.Project_4.Bean;

import java.util.Date;


/**
 * Student JavaBean encapsulates Student attributes.
 * @author Darshan
 *
 */
public class StudentBean extends BaseBean {
	private String firstName;
	private String lastName;
	private Date dob;
	private String mobileNo;
	private String email;
	private long collegeId;
	private String collegeName;
	public String getFirstname() {
		return firstName;
	}
	public void setFirstname(String firstname) {
		this.firstName = firstname;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getKey() {
		return id + "";
	}
	public String getValue() {
		return firstName + " "+lastName;
	}

}
