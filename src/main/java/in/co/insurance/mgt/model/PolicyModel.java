package in.co.insurance.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import in.co.insurance.mgt.bean.PolicyBean;
import in.co.insurance.mgt.exception.ApplicationException;
import in.co.insurance.mgt.exception.DatabaseException;
import in.co.insurance.mgt.exception.DuplicateRecordException;
import in.co.insurance.mgt.util.JDBCDataSource;

/**
 * JDBC Implementation of PolicyModel
 */
public class PolicyModel {
	private static Logger log = Logger.getLogger(PolicyModel.class);
	

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM Policy");
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
	 * Add a Policy
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add(PolicyBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		int pk = 0;

		PolicyBean existbean = findByName(bean.getName());

		if (existbean != null) {
			throw new DuplicateRecordException("Policy already exists");
		}
		bean.setCategoryName(new CategoryModel().findByPK(bean.getCategoryId()).getName());
		bean.setSubCategoryName(new SubCategoryModel().findByPK(bean.getSubCategoryId()).getName());
		

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Policy VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getSumAssured());
			pstmt.setString(4, bean.getPreminum());
			pstmt.setString(5, bean.getTenure());
			pstmt.setString(6, bean.getDescription());
			pstmt.setLong(7,bean.getCategoryId());
			pstmt.setString(8,bean.getCategoryName());
			pstmt.setLong(9,bean.getSubCategoryId());
			pstmt.setString(10,bean.getSubCategoryName());
			pstmt.setBlob(11,bean.getPhoto());
			pstmt.setString(12, bean.getCreatedBy());
			pstmt.setString(13, bean.getModifiedBy());
			pstmt.setTimestamp(14, bean.getCreatedDatetime());
			pstmt.setTimestamp(15, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
		
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Policy");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}

	/**
	 * Delete a Policy
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */
	public void delete(PolicyBean bean) throws ApplicationException {
		
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Policy WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
		
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Policy");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
	}

	/**
	 * Find Policy by Login
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public PolicyBean findByName(String name) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Policy WHERE Name=?");
		PolicyBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new PolicyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setSumAssured(rs.getString(3));
				bean.setPreminum(rs.getString(4));
				bean.setTenure(rs.getString(5));
				bean.setDescription(rs.getString(6));
				bean.setCategoryId(rs.getLong(7));
				bean.setCategoryName(rs.getString(8));
				bean.setSubCategoryId(rs.getLong(9));
				bean.setSubCategoryName(rs.getString(10));
				bean.setImage(rs.getBlob(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Policy by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}

	/**
	 * Find Policy by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public PolicyBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Policy WHERE ID=?");
		PolicyBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new PolicyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setSumAssured(rs.getString(3));
				bean.setPreminum(rs.getString(4));
				bean.setTenure(rs.getString(5));
				bean.setDescription(rs.getString(6));
				bean.setCategoryId(rs.getLong(7));
				bean.setCategoryName(rs.getString(8));
				bean.setSubCategoryId(rs.getLong(9));
				bean.setSubCategoryName(rs.getString(10));
				bean.setImage(rs.getBlob(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting Policy by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	/**
	 * Update a Policy
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public void update(PolicyBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		PolicyBean beanExist = findByName(bean.getName());
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("Policy is already exist");
		}
		
		bean.setCategoryName(new CategoryModel().findByPK(bean.getCategoryId()).getName());
		bean.setSubCategoryName(new SubCategoryModel().findByPK(bean.getSubCategoryId()).getName());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE Policy SET NAME=?,SUM_ASSURED=?,PREMIUM=?,TENURE=?,DESCRIPTION=?,CATEGORY_ID=?,CATEGORY_NAME=?,SUB_CATEGORY_ID=?,SUB_CATEGORY_NAME=?,IMAGE=?,"
					+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getSumAssured());
			pstmt.setString(3, bean.getPreminum());
			pstmt.setString(4, bean.getTenure());
			pstmt.setString(5, bean.getDescription());
			pstmt.setLong(6,bean.getCategoryId());
			pstmt.setString(7,bean.getCategoryName());
			pstmt.setLong(8,bean.getSubCategoryId());
			pstmt.setString(9,bean.getSubCategoryName());
			pstmt.setBlob(10,bean.getPhoto());
			pstmt.setString(11, bean.getCreatedBy());
			pstmt.setString(12, bean.getModifiedBy());
			pstmt.setTimestamp(13, bean.getCreatedDatetime());
			pstmt.setTimestamp(14, bean.getModifiedDatetime());
			pstmt.setLong(15, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Policy ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Policy
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search(PolicyBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search Policy with pagination
	 * 
	 * @return list : List of Policys
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List search(PolicyBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Policy WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getSubCategoryId() > 0) {
				sql.append(" AND SUB_CATEGORY_ID = " + bean.getSubCategoryId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			if (bean.getCategoryName() != null && bean.getCategoryName().length() > 0) {
				sql.append(" AND CATEGORY_NAME like '" + bean.getCategoryName() + "%'");
			}
			if (bean.getSubCategoryName() != null && bean.getSubCategoryName().length() > 0) {
				sql.append(" AND SUB_CATEGORY_NAME like '" + bean.getSubCategoryName() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("Policy model search  :"+sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new PolicyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setSumAssured(rs.getString(3));
				bean.setPreminum(rs.getString(4));
				bean.setTenure(rs.getString(5));
				bean.setDescription(rs.getString(6));
				bean.setCategoryId(rs.getLong(7));
				bean.setCategoryName(rs.getString(8));
				bean.setSubCategoryId(rs.getLong(9));
				bean.setSubCategoryName(rs.getString(10));
				bean.setImage(rs.getBlob(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Policy");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of Policy
	 * 
	 * @return list : List of Policy
	 * @throws DatabaseException
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Policy with pagination
	 * 
	 * @return list : List of Policys
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from Policy");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		
		System.out.println("sql in list Policy :"+sql);
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PolicyBean bean = new PolicyBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setSumAssured(rs.getString(3));
				bean.setPreminum(rs.getString(4));
				bean.setTenure(rs.getString(5));
				bean.setDescription(rs.getString(6));
				bean.setCategoryId(rs.getLong(7));
				bean.setCategoryName(rs.getString(8));
				bean.setSubCategoryId(rs.getLong(9));
				bean.setSubCategoryName(rs.getString(10));
				bean.setImage(rs.getBlob(11));
				bean.setCreatedBy(rs.getString(12));
				bean.setModifiedBy(rs.getString(13));
				bean.setCreatedDatetime(rs.getTimestamp(14));
				bean.setModifiedDatetime(rs.getTimestamp(15));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Policys");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}
}
