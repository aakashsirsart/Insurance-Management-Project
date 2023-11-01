package in.co.insurance.mgt.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import in.co.insurance.mgt.bean.BuyPolicyBean;
import in.co.insurance.mgt.exception.ApplicationException;
import in.co.insurance.mgt.exception.DatabaseException;
import in.co.insurance.mgt.exception.DuplicateRecordException;
import in.co.insurance.mgt.util.JDBCDataSource;

/**
 * JDBC Implementation of BuyPolicyModel
 */
public class BuyPolicyModel {
	private static Logger log = Logger.getLogger(BuyPolicyModel.class);
	

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM BuyPolicy");
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
	 * Add a BuyPolicy
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public long add(BuyPolicyBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO BuyPolicy VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getUserName());
			pstmt.setString(3, bean.getEmail());
			pstmt.setString(4, bean.getContactNo());
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getPolicyName());
			pstmt.setString(7,bean.getCategoryName());
			pstmt.setString(8,bean.getSubCategoryName());
			pstmt.setString(9, bean.getSumAssured());
			pstmt.setString(10, bean.getPremium());
			pstmt.setString(11, bean.getTenure());
			pstmt.setLong(12,bean.getUserId());
			pstmt.setTimestamp(13,bean.getDate());
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, bean.getCreatedDatetime());
			pstmt.setTimestamp(17, bean.getModifiedDatetime());
			pstmt.setString(18,bean.getStatus());
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
			throw new ApplicationException("Exception : Exception in add BuyPolicy");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}

	/**
	 * Delete a BuyPolicy
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */
	public void delete(BuyPolicyBean bean) throws ApplicationException {
		
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM BuyPolicy WHERE ID=?");
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
			throw new ApplicationException("Exception : Exception in delete BuyPolicy");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
	}

	/**
	 * Find BuyPolicy by Login
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public BuyPolicyBean findByName(String name) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM BuyPolicy WHERE Name=?");
		BuyPolicyBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BuyPolicyBean();
				bean.setId(rs.getLong(1));
				bean.setUserName(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setGender(rs.getString(5));
				bean.setPolicyName(rs.getString(6));
				bean.setCategoryName(rs.getString(7));
				bean.setSubCategoryName(rs.getString(8));
				bean.setSumAssured(rs.getString(9));
				bean.setPremium(rs.getString(10));
				bean.setTenure(rs.getString(11));
				bean.setUserId(rs.getLong(12));
				bean.setDate(rs.getTimestamp(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
				bean.setStatus(rs.getString(18));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting BuyPolicy by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}

	/**
	 * Find BuyPolicy by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public BuyPolicyBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM BuyPolicy WHERE ID=?");
		BuyPolicyBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BuyPolicyBean();
				bean.setId(rs.getLong(1));
				bean.setUserName(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setGender(rs.getString(5));
				bean.setPolicyName(rs.getString(6));
				bean.setCategoryName(rs.getString(7));
				bean.setSubCategoryName(rs.getString(8));
				bean.setSumAssured(rs.getString(9));
				bean.setPremium(rs.getString(10));
				bean.setTenure(rs.getString(11));
				bean.setUserId(rs.getLong(12));
				bean.setDate(rs.getTimestamp(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
				bean.setStatus(rs.getString(18));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting BuyPolicy by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	/**
	 * Update a BuyPolicy
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public void update(BuyPolicyBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE BuyPolicy SET USER_NAME=?,EMAIL=?,CONTACT_NO=?,GENDER=?,POLICY_NAME=?,CATEGORY_NAME=?,SUB_CATEGORY_NAME=?,SUM_ASSURED=?,PREMIUM=?,TENURE=?,USER_ID=?,DATE=?,"
					+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=?,STATUS=? WHERE ID=?");
			pstmt.setString(1, bean.getUserName());
			pstmt.setString(2, bean.getEmail());
			pstmt.setString(3, bean.getContactNo());
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getPolicyName());
			pstmt.setString(6,bean.getCategoryName());
			pstmt.setString(7,bean.getSubCategoryName());
			pstmt.setString(8, bean.getSumAssured());
			pstmt.setString(9, bean.getPremium());
			pstmt.setString(10, bean.getTenure());
			pstmt.setLong(11,bean.getUserId());
			pstmt.setTimestamp(12,bean.getDate());
			pstmt.setString(13, bean.getCreatedBy());
			pstmt.setString(14, bean.getModifiedBy());
			pstmt.setTimestamp(15, bean.getCreatedDatetime());
			pstmt.setTimestamp(16, bean.getModifiedDatetime());
			pstmt.setString(17, bean.getStatus());
			pstmt.setLong(18, bean.getId());
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
			throw new ApplicationException("Exception in updating BuyPolicy ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search BuyPolicy
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List search(BuyPolicyBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search BuyPolicy with pagination
	 * 
	 * @return list : List of BuyPolicys
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List search(BuyPolicyBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM BuyPolicy WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getUserId() > 0) {
				sql.append(" AND USER_ID = " + bean.getUserId());
			}
			if (bean.getCategoryName() != null && bean.getCategoryName().length() > 0) {
				sql.append(" AND Category_Name like '" + bean.getCategoryName() + "%'");
			}
			
			if (bean.getPolicyName() != null && bean.getPolicyName().length() > 0) {
				sql.append(" AND policy_Name like '" + bean.getPolicyName() + "%'");
			}
			if (bean.getUserName() != null && bean.getUserName().length() > 0) {
				sql.append(" AND User_Name like '" + bean.getUserName() + "%'");
			}
			
			if (bean.getStatus() != null && bean.getStatus().length() > 0) {
				sql.append(" AND Status like '" + bean.getStatus() + "%'");
			}
		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BuyPolicyBean();
				bean.setId(rs.getLong(1));
				bean.setUserName(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setGender(rs.getString(5));
				bean.setPolicyName(rs.getString(6));
				bean.setCategoryName(rs.getString(7));
				bean.setSubCategoryName(rs.getString(8));
				bean.setSumAssured(rs.getString(9));
				bean.setPremium(rs.getString(10));
				bean.setTenure(rs.getString(11));
				bean.setUserId(rs.getLong(12));
				bean.setDate(rs.getTimestamp(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
				bean.setStatus(rs.getString(18));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search BuyPolicy");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of BuyPolicy
	 * 
	 * @return list : List of BuyPolicy
	 * @throws DatabaseException
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of BuyPolicy with pagination
	 * 
	 * @return list : List of BuyPolicys
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from BuyPolicy");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		
		System.out.println("sql in list BuyPolicy :"+sql);
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BuyPolicyBean bean = new BuyPolicyBean();
				bean.setId(rs.getLong(1));
				bean.setUserName(rs.getString(2));
				bean.setEmail(rs.getString(3));
				bean.setContactNo(rs.getString(4));
				bean.setGender(rs.getString(5));
				bean.setPolicyName(rs.getString(6));
				bean.setCategoryName(rs.getString(7));
				bean.setSubCategoryName(rs.getString(8));
				bean.setSumAssured(rs.getString(9));
				bean.setPremium(rs.getString(10));
				bean.setTenure(rs.getString(11));
				bean.setUserId(rs.getLong(12));
				bean.setDate(rs.getTimestamp(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
				bean.setStatus(rs.getString(18));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of  s");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}
}
