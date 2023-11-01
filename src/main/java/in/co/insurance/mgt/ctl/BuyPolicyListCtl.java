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
import in.co.insurance.mgt.bean.UserBean;
import in.co.insurance.mgt.exception.ApplicationException;
import in.co.insurance.mgt.exception.DuplicateRecordException;
import in.co.insurance.mgt.model.BuyPolicyModel;
import in.co.insurance.mgt.util.DataUtility;
import in.co.insurance.mgt.util.PropertyReader;
import in.co.insurance.mgt.util.ServletUtility;


/**
 * Servlet implementation class BuyPolicyListCtl
 */
@WebServlet(name = "BuyPolicyListCtl", urlPatterns = { "/ctl/buyPolicyList" })
public class BuyPolicyListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = Logger.getLogger(BuyPolicyListCtl.class);

	/**
	 * Populates bean object from request parameters
	 * 
	 * @param request
	 * @return
	 */
	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		log.debug("BuyPolicyListCtl populateBean method start");
		BuyPolicyBean bean = new BuyPolicyBean();
		bean.setUserName(DataUtility.getString(request.getParameter("name")));
		bean.setPolicyName(DataUtility.getString(request.getParameter("policyName")));
		bean.setCategoryName(DataUtility.getString(request.getParameter("categoryName")));
		bean.setStatus(DataUtility.getString(request.getParameter("status")));
		log.debug("BuyPolicyListCtl populateBean method end");
		return bean;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BuyPolicyListCtl doGet method start");
		List list = null;
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize =10;
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		BuyPolicyModel model = new BuyPolicyModel();
		BuyPolicyBean bean = (BuyPolicyBean) populateBean(request);
		
		long id=DataUtility.getLong(request.getParameter("id"));
		String status=DataUtility.getString(request.getParameter("status"));
		try {
			
			if(id>0 && status !=null) {
				BuyPolicyBean bBean=model.findByPK(id);
				bBean.setStatus(status);
				model.update(bBean);
			}
			
			UserBean uBean=(UserBean)request.getSession().getAttribute("user");
			if(uBean.getRoleId()==2) {
				bean.setUserId(uBean.getId());
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
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}
		log.debug("BuyPolicyListCtl doGet method end");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("BuyPolicyListCtl doPost method start");
		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		BuyPolicyBean bean = (BuyPolicyBean) populateBean(request);

		BuyPolicyModel model = new BuyPolicyModel();
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
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(IMSView.BUY_POLICY_LIST_CTL, request, response);
			return;

		}
		try {
			
			UserBean uBean=(UserBean)request.getSession().getAttribute("user");
			if(uBean.getRoleId()==2) {
				bean.setUserId(uBean.getId());
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

		log.debug("BuyPolicyListCtl doPost method end");
	}
	@Override
	protected String getView() {
		return IMSView.BUY_POLICY_LIST_VIEW;
	}

}
