package com.java.insurance.dao;

import java.sql.*;
import java.util.*;

import com.java.insurance.model.Policy;
import com.java.insurance.util.*;
import com.java.insurance.exception.PolicyNotFoundException;

public class InsuranceServiceImpl implements IPolicyService {
    Connection conn;
    PreparedStatement pst;

    @Override
    public boolean createPolicy(Policy policy) throws ClassNotFoundException, SQLException {
        Connection con = DBConnection.getConnection();
        String query = "INSERT INTO Policy (policyId, policyName, policyType, premiumAmount, coverageAmount) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, policy.getPolicyId());
        ps.setString(2, policy.getPolicyName());
        ps.setString(3, policy.getPolicyType());
        ps.setDouble(4, policy.getPremiumAmount());      
        ps.setDouble(5, policy.getCoverageAmount());     
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }

    @Override
    public Policy getPolicy(int policyId) throws ClassNotFoundException, SQLException {
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM Policy WHERE policyId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, policyId);
        ResultSet rs = ps.executeQuery();
        Policy policy = null;
        if (rs.next()) {
            policy = new Policy(
                rs.getInt("policyId"),
                rs.getString("policyName"),
                rs.getString("policyType"),
                rs.getDouble("premiumAmount"),            
                rs.getDouble("coverageAmount")        
            );
        }
        con.close();
        return policy;
    }

    @Override
    public Collection<Policy> getAllPolicies() throws ClassNotFoundException, SQLException {
        List<Policy> policies = new ArrayList<>();
        Connection con = DBConnection.getConnection();
        String query = "SELECT * FROM Policy";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            policies.add(new Policy(
                rs.getInt("policyId"),
                rs.getString("policyName"),
                rs.getString("policyType"),
                rs.getDouble("premiumAmount"),            
                rs.getDouble("coverageAmount")       
            ));
        }
        con.close();
        return policies;
    }

    @Override
    public boolean updatePolicy(Policy policy) throws ClassNotFoundException, SQLException {
        Connection con = DBConnection.getConnection();
        String query = "UPDATE Policy SET policyName=?, policyType=?, premiumAmount=?, coverageAmount=? WHERE policyId=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, policy.getPolicyName());
        ps.setString(2, policy.getPolicyType());
        ps.setDouble(3, policy.getPremiumAmount());      
        ps.setDouble(4, policy.getCoverageAmount());      
        ps.setInt(5, policy.getPolicyId());
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }

    @Override
    public boolean deletePolicy(int policyId) throws ClassNotFoundException, SQLException {
        Connection con = DBConnection.getConnection();
        String query = "DELETE FROM Policy WHERE policyId = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, policyId);
        int rows = ps.executeUpdate();
        con.close();
        return rows > 0;
    }
}
