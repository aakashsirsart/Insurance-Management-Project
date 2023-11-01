package in.co.insurance.mgt.ctl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.log4j.Logger;

import in.co.insurance.mgt.bean.BaseBean;
import in.co.insurance.mgt.bean.SubCategoryBean;
import in.co.insurance.mgt.exception.ApplicationException;
import in.co.insurance.mgt.exception.DuplicateRecordException;
import in.co.insurance.mgt.model.CategoryModel;
import in.co.insurance.mgt.model.SubCategoryModel;
import in.co.insurance.mgt.util.DataUtility;
import in.co.insurance.mgt.util.DataValidator;
import in.co.insurance.mgt.util.PropertyReader;
import in.co.insurance.mgt.util.ServletUtility;

/**
 * Servlet implementation class SubCategoryCtl
 */
@WebServlet(name = "SubCategoryCtl", urlPatterns = { "/ctl/subCategory" })
@MultipartConfig(maxFileSize = 16177216)
public class SubCategoryCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(SubCategoryCtl.class);

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("SubCategoryCtl validate method start");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("description"))) {
			request.setAttribute("description", PropertyReader.getValue("error.require", "Description"));
			pass = false;
		}
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("category"))) {
            request.setAttribute("category",
                    PropertyReader.getValue("error.require", "Category"));
            pass = false;
        }

		log.debug("SubCategoryCtl validate method end");
		return pass;
	}

	@Override
	protected void preload(HttpServletRequest request) {
		try {
			request.setAttribute("categoryList",new CategoryModel().list());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("SubCategoryCtl populateBean method start");
		SubCategoryBean bean = new SubCategoryBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCategoryId(DataUtility.getLong(request.getParameter("category")));
		populateDTO(bean, request);
		log.debug("SubCategoryCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("SubCategoryCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
		   SubCategoryModel model = new SubCategoryModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				SubCategoryBean bean;
				try {
					bean = model.findByPK(id);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setBean(bean, request);
				} catch (ApplicationException e) {
					ServletUtility.handleException(e, request, response);
					return;
				}
			}

			ServletUtility.forward(getView(), request, response);
			log.debug("SubCategoryCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("SubCategoryCtl doPost method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		SubCategoryModel model = new SubCategoryModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)) {

			SubCategoryBean bean = (SubCategoryBean) populateBean(request);

			Part part = request.getPart("image");
			bean.setPhoto(part.getInputStream());

			try {
				if (id > 0) {
					
					model.update(bean);
					ServletUtility.setOpration("Edit", request);
					ServletUtility.setSuccessMessage("Data is successfully Updated", request);
					ServletUtility.setBean(bean, request);

				} else {
					long pk = model.add(bean);
					// bean.setId(id);
					ServletUtility.setSuccessMessage("Data is successfully Saved", request);
					ServletUtility.forward(getView(), request, response);
				}

			} catch (ApplicationException e) {
				e.printStackTrace();
				ServletUtility.forward(IMSView.ERROR_VIEW, request, response);
				return;

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage(e.getMessage(), request);
			} 

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			SubCategoryBean bean = (SubCategoryBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(IMSView.SUB_CATEGORY_LIST_CTL, request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(IMSView.SUB_CATEGORY_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(IMSView.SUB_CATEGORY_CTL, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("SubCategoryCtl doPost method end");
	}

	@Override
	protected String getView() {
		return IMSView.SUB_CATEGORY_VIEW;
	}

}
