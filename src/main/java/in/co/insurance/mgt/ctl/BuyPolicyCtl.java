package in.co.insurance.mgt.ctl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import in.co.insurance.mgt.bean.BaseBean;
import in.co.insurance.mgt.bean.BuyPolicyBean;
import in.co.insurance.mgt.bean.PolicyBean;
import in.co.insurance.mgt.bean.UserBean;
import in.co.insurance.mgt.exception.ApplicationException;
import in.co.insurance.mgt.exception.DuplicateRecordException;
import in.co.insurance.mgt.model.PolicyModel;
import in.co.insurance.mgt.model.BuyPolicyModel;
import in.co.insurance.mgt.util.DataUtility;
import in.co.insurance.mgt.util.PropertyReader;
import in.co.insurance.mgt.util.ServletUtility;

/**
 * Servlet implementation class BuyPolicyCtl
 */
@WebServlet(name = "BuyPolicyCtl", urlPatterns = { "/ctl/buyPolicy" })
public class BuyPolicyCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(BuyPolicyCtl.class);

	/**
	 * Validate input Data Entered By User
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected boolean validate(HttpServletRequest request) {
		log.debug("BuyPolicyCtl validate method start");
		boolean pass = true;
		return pass;
	}

	@Override
	protected void preload(HttpServletRequest request) {
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BuyPolicyCtl populateBean method start");
		BuyPolicyBean bean = new BuyPolicyBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		populateDTO(bean, request);
		log.debug("BuyPolicyCtl populateBean method end");
		return bean;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("BuyPolicyCtl doGet method start");
		String op = DataUtility.getString(request.getParameter("operation"));
		BuyPolicyModel model = new BuyPolicyModel();
		long pId = DataUtility.getLong(request.getParameter("pId"));
		BuyPolicyBean bean;
		List list = null;
		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		long cId = DataUtility.getLong(request.getParameter("sId"));
		PolicyModel pModel = new PolicyModel();
		try {
			if (pId > 0) {
				bean = new BuyPolicyBean();
				PolicyBean pBean = new PolicyModel().findByPK(pId);
				UserBean uBean = (UserBean) request.getSession().getAttribute("user");
				bean.setUserId(uBean.getId());
				bean.setUserName(uBean.getFirstName() + " " + uBean.getLastName());
				bean.setEmail(uBean.getEmail());
				bean.setContactNo(uBean.getContactNo());
				bean.setGender(uBean.getGender());
				bean.setPolicyName(pBean.getName());
				bean.setSumAssured(pBean.getSumAssured());
				bean.setPremium(pBean.getPreminum());
				bean.setTenure(pBean.getTenure());
				bean.setDate(pBean.getCreatedDatetime());
				bean.setCategoryName(pBean.getCategoryName());
				bean.setSubCategoryName(pBean.getSubCategoryName());
				bean.setCreatedBy(uBean.getLogin());
				bean.setModifiedBy(uBean.getLogin());
				bean.setCreatedDatetime(DataUtility.getCurrentTimestamp());
				bean.setModifiedDatetime(DataUtility.getCurrentTimestamp());
				bean.setStatus("Pending");
				model.add(bean);
				ServletUtility.setSuccessMessage("Policy Apply Successfully", request);
			}
			list = pModel.list(pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setSize(model.list().size(), request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			return;
		} catch (DuplicateRecordException e) {
			ServletUtility.setErrorMessage(e.getMessage(), request);
			e.printStackTrace();
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("BuyPolicyCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	@Override
	protected String getView() {
		return IMSView.POLICYIES_VIEW;
	}

}
