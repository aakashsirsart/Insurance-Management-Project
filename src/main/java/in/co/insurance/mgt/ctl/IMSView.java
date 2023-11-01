package in.co.insurance.mgt.ctl;

public interface IMSView {
	
	public String APP_CONTEXT = "/Insurance-Management";

	public String LAYOUT_VIEW = "/BaseLayout.jsp";
	public String PAGE_FOLDER = "/jsp";

	public String JAVA_DOC_VIEW = APP_CONTEXT + "/doc/index.html";

	public String ERROR_VIEW = PAGE_FOLDER + "/Error.jsp";

	public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";	
	public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";
	public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";
	public String INDEX_VIEW ="/index.jsp";
	
	public String CATEGORY_VIEW = PAGE_FOLDER + "/CategoryView.jsp";	
	public String CATEGORY_LIST_VIEW = PAGE_FOLDER + "/CategoryListView.jsp";
	
	public String CATEGORIES_VIEW = PAGE_FOLDER + "/CategoriesView.jsp";
	
	public String SUB_CATEGORIES_VIEW = PAGE_FOLDER + "/SubCategoriesView.jsp";
	
	public String POLICYIES_VIEW = PAGE_FOLDER + "/PolicyiesView.jsp";
	
	public String POLICY_VIEW = PAGE_FOLDER + "/PolicyView.jsp";	
	public String POLICY_LIST_VIEW = PAGE_FOLDER + "/PolicyListView.jsp";
	
	public String BUY_POLICY_VIEW = PAGE_FOLDER + "/BuyPolicyView.jsp";
	public String BUY_POLICY_LIST_VIEW = PAGE_FOLDER + "/BuyPolicyListView.jsp";
	
	public String SUB_CATEGORY_VIEW = PAGE_FOLDER + "/SubCategoryView.jsp";	
	public String SUB_CATEGORY_LIST_VIEW = PAGE_FOLDER + "/SubCategoryListView.jsp";
	
	public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";
	
	public String GET_IMAGE_VIEW = PAGE_FOLDER + "/getImage.jsp";
	
	public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";
	public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";
	public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp";
	public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

	public String ERROR_CTL = "/ctl/ErrorCtl";

	public String USER_CTL = APP_CONTEXT + "/ctl/userCtl";
	public String USER_LIST_CTL = APP_CONTEXT + "/ctl/userList";
	
	public String CATEGORY_CTL = APP_CONTEXT + "/ctl/category";
	public String CATEGORY_LIST_CTL = APP_CONTEXT + "/ctl/categoryList";
	public String CATEGORIES_CTL = APP_CONTEXT + "/categories";
	
	public String SUB_CATEGORIES_CTL = APP_CONTEXT + "/subCategories";
	public String POLICYIES_CTL = APP_CONTEXT + "/policyies";
	
	public String SUB_CATEGORY_CTL = APP_CONTEXT + "/ctl/subCategory";
	public String SUB_CATEGORY_LIST_CTL = APP_CONTEXT + "/ctl/subCategoryList";
	
	public String POLICY_CTL = APP_CONTEXT + "/ctl/policy";
	public String POLICY_LIST_CTL = APP_CONTEXT + "/ctl/policyList";
	
	
	public String BUY_POLICY_CTL = APP_CONTEXT + "/ctl/buyPolicy";
	public String BUY_POLICY_LIST_CTL = APP_CONTEXT + "/ctl/buyPolicyList";
	
	public String USER_REGISTRATION_CTL = APP_CONTEXT + "/register";
	
	
	public String LOGIN_CTL = APP_CONTEXT + "/login";
	public String WELCOME_CTL = APP_CONTEXT + "/welcome";
	public String LOGOUT_CTL = APP_CONTEXT + "/login";
	
	public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/changePassword";
	public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/myProfile";
	public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/forgetPassword";



}
