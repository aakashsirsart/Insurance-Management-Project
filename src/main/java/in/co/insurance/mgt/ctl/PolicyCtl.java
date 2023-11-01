package in.co.insurance.mgt.ctl;

import java.io.IOException;
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
import in.co.insurance.mgt.bean.PolicyBean;
import in.co.insurance.mgt.bean.SubCategoryBean;
import in.co.insurance.mgt.exception.ApplicationException;
import in.co.insurance.mgt.exception.DuplicateRecordException;
import in.co.insurance.mgt.model.CategoryModel;
import in.co.insurance.mgt.model.PolicyModel;
import in.co.insurance.mgt.model.SubCategoryModel;
import in.co.insurance.mgt.util.DataUtility;
import in.co.insurance.mgt.util.DataValidator;
import in.co.insurance.mgt.util.PropertyReader;
import in.co.insurance.mgt.util.ServletUtility;

/**
 * Servlet implementation class PolicyCtl
 */
@WebServlet(name = "PolicyCtl", urlPatterns = { "/ctl/policy" })
@MultipartConfig(maxFileSize = 16177216)
public class PolicyCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(PolicyCtl.class);

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("PolicyCtl validate method start");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("name"))) {
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("sum"))) {
			request.setAttribute("sum", PropertyReader.getValue("error.require", "Sum Assured"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("premium"))) {
			request.setAttribute("premium", PropertyReader.getValue("error.require", "Premium"));
			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("tenure"))) {
			request.setAttribute("tenure", PropertyReader.getValue("error.require", "Tenure"));
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
		
		if ("-----Select-----".equalsIgnoreCase(request.getParameter("subCategory"))) {
            request.setAttribute("subCategory",
                    PropertyReader.getValue("error.require", "Sub Category"));
            pass = false;
        }

		log.debug("PolicyCtl validate method end");
		return pass;
	}

	@Override
	protected void preload(HttpServletRequest request) {
		try {
			request.setAttribute("categoryList",new CategoryModel().list());
			long cId=DataUtility.getLong(request.getParameter("category"));
			if(cId>0) {
				SubCategoryBean sBean=new SubCategoryBean();
				sBean.setCategoryId(cId);
				request.setAttribute("subCategoryList",new SubCategoryModel().search(sBean));
			}else {
				request.setAttribute("subCategoryList",new SubCategoryModel().list());
			}
			
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("PolicyCtl populateBean method start");
		PolicyBean bean = new PolicyBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setDescription(DataUtility.getString(request.getParameter("description")));
		bean.setCategoryId(DataUtility.getLong(request.getParameter("category")));
		bean.setSubCategoryId(DataUtility.getLong(request.getParameter("subCategory")));
		bean.setSumAssured(DataUtility.getString(request.getParameter("sum")));
		bean.setPreminum(DataUtility.getString(request.getParameter("premium")));
		bean.setTenure(DataUtility.getString(request.getParameter("tenure")));
		populateDTO(bean, request);
		log.debug("PolicyCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PolicyCtl doGet method start"); 
		String op = DataUtility.getString(request.getParameter("operation"));
		   PolicyModel model = new PolicyModel();
			long id = DataUtility.getLong(request.getParameter("id"));
			ServletUtility.setOpration("Add", request);
			if (id > 0 || op != null) {
				System.out.println("in id > 0  condition");
				PolicyBean bean;
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
			log.debug("PolicyCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("PolicyCtl doPost method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		PolicyModel model = new PolicyModel();
		long id = DataUtility.getLong(request.getParameter("id"));
		if (OP_SAVE.equalsIgnoreCase(op)) {
			PolicyBean bean = (PolicyBean) populateBean(request);
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
			PolicyBean bean = (PolicyBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(IMSView.POLICY_LIST_CTL, request, response);
			} catch (ApplicationException e) {
				ServletUtility.handleException(e, request, response);
				e.printStackTrace();
			}
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {
			ServletUtility.redirect(IMSView.POLICY_LIST_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(IMSView.POLICY_CTL, request, response);
			return;
		}
		
		PolicyBean bean = (PolicyBean) populateBean(request);
		ServletUtility.setBean(bean, request);

		ServletUtility.forward(getView(), request, response);
		log.debug("PolicyCtl doPost method end");
	}

	@Override
	protected String getView() {
		return IMSView.POLICY_VIEW;
	}

}
