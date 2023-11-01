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
import in.co.insurance.mgt.bean.PolicyBean;
import in.co.insurance.mgt.exception.ApplicationException;
import in.co.insurance.mgt.model.PolicyModel;
import in.co.insurance.mgt.util.DataUtility;
import in.co.insurance.mgt.util.PropertyReader;
import in.co.insurance.mgt.util.ServletUtility;

/**
 * Servlet implementation class PolicyCtl
 */

@WebServlet(name = "PolicyiesCtl", urlPatterns = { "/policyies" })
public class PolicyiesCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(PolicyiesCtl.class);

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("PolicyCtl populateBean method start");
		PolicyBean bean = new PolicyBean();
		bean.setName(DataUtility.getString(request.getParameter("name")));
		bean.setCategoryName(DataUtility.getString(request.getParameter("categoryName")));
		bean.setSubCategoryName(DataUtility.getString(request.getParameter("subCategoryName")));
		log.debug("PolicyCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PolicyCtl doGet method start");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize =10;
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
		long cId=DataUtility.getLong(request.getParameter("sId"));
		PolicyModel model = new PolicyModel();
		PolicyBean bean = (PolicyBean) populateBean(request);
		try {
			if(cId>0) {
				bean.setSubCategoryId(cId);
				request.getSession().setAttribute("sId",cId);
			}
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setSize(model.search(bean).size(), request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("PolicyCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("PolicyCtl doPost method start");
		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		PolicyBean bean = (PolicyBean) populateBean(request);

		PolicyModel model = new PolicyModel();
		String[] ids = request.getParameterValues("ids");
		String op = DataUtility.getString(request.getParameter("operation"));

		if (OP_SEARCH.equalsIgnoreCase(op) || OP_NEXT.equalsIgnoreCase(op) || OP_PREVIOUS.equalsIgnoreCase(op)) {
			if (OP_SEARCH.equalsIgnoreCase(op)) {
				pageNo = 1;
			} else if (OP_NEXT.equalsIgnoreCase(op)) {
				pageNo++;
			} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
				pageNo--;
			}
		}
		
		try {
			
			long cId=DataUtility.getLong(String.valueOf(request.getSession().getAttribute("sId")));
			if(cId>0) {
				bean.setSubCategoryId(cId);
			}
			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("NO Record Found", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setSize(model.search(bean).size(), request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}

		log.debug("PolicyCtl doPost method end");
	}
	@Override
	protected String getView() {
		return IMSView.POLICYIES_VIEW;
	}

}
