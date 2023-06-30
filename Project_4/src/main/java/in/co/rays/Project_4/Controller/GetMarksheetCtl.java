package in.co.rays.Project_4.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.rays.Project_4.Bean.BaseBean;
import in.co.rays.Project_4.Bean.MarksheetBean;
import in.co.rays.Project_4.Exception.ApplicationException;
import in.co.rays.Project_4.Model.MarksheetModel;
import in.co.rays.Project_4.Utility.DataUtility;
import in.co.rays.Project_4.Utility.DataValidator;
import in.co.rays.Project_4.Utility.PropertyReader;
import in.co.rays.Project_4.Utility.ServletUtility;

/**
 *  Get Marksheet functionality Controller. Performs operation for Get Marksheet
 * @author Darshan
 *
 */
@WebServlet(name = "GetMarksheetCtl", urlPatterns = { "/ctl/GetMarksheetCtl" })
public class GetMarksheetCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(GetMarksheetCtl.class);
	 /* (non-Javadoc)
     * @see in.co.rays.ors.controller.BaseCtl#validate(javax.servlet.http.HttpServletRequest)
     */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("GetMarksheetCtl validate method started");

		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("rollNo"))) {
			request.setAttribute("rollNo", PropertyReader.getValue("error.require", "Roll number "));
			pass = false;
		}
		log.debug("GetMarksheetCtl validate method ended");
		return pass;

	}
	 /* (non-Javadoc)
     * @see in.co.rays.ors.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
     */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("GetMarksheetCtl populateBean method started");

		MarksheetBean bean = new MarksheetBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setRollNo(DataUtility.getString(request.getParameter("rollNo")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setPhysics(DataUtility.getInt(request.getParameter("physics")));
		bean.setChemistry(DataUtility.getInt(request.getParameter("chemistry")));
		bean.setMaths(DataUtility.getInt(request.getParameter("maths")));

		log.debug("GetMarksheetCtl populateBean method ended");
		return bean;
	}
	/**
     * Concept of Display method.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletUtility.forward(getView(), request, response);
	}
	/**
     * Concept of Submit Method.
     *
     * @param request the request
     * @param response the response
     * @throws ServletException the servlet exception
     * @throws IOException Signals that an I/O exception has occurred.
     */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("GetMarksheetCtl dopost method started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model

		MarksheetModel model = new MarksheetModel();

		MarksheetBean bean = (MarksheetBean) populateBean(request);

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_GO.equalsIgnoreCase(op)) {
			try {
				bean = model.findByRollNo(bean.getRollNo());
				if (bean != null) {
					ServletUtility.setBean(bean, request);
				} else {
					ServletUtility.setErrorMessage("Roll Number Does Not Exists ", request);
				}
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		} 

		ServletUtility.forward(getView(), request, response);
		log.debug("GetMarksheetCtl dopost method ended");
	}
	/* (non-Javadoc)
     * @see in.co.rays.ors.controller.BaseCtl#getView()
     */
	@Override
	protected String getView() {
		return ORSView.GET_MARKSHEET_VIEW;
	}

}