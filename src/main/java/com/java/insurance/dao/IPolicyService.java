package com.java.insurance.dao;

import com.java.insurance.model.Policy;
import java.sql.SQLException;
import java.util.Collection;

public interface IPolicyService 
{
	boolean createPolicy(Policy policy) throws ClassNotFoundException, SQLException;

    Policy getPolicy(int policyId) throws ClassNotFoundException, SQLException;

    Collection<Policy> getAllPolicies() throws ClassNotFoundException, SQLException;

    boolean updatePolicy(Policy policy) throws ClassNotFoundException, SQLException;

    boolean deletePolicy(int policyId) throws ClassNotFoundException, SQLException;
}
