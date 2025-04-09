package com.java.insurance.junit;

import static org.junit.Assert.*;
import org.junit.Test;

import com.java.insurance.dao.*;
import com.java.insurance.model.Policy;

import java.sql.SQLException;
import java.util.Collection;


public class JunitTest 
{
	IPolicyService service = new InsuranceServiceImpl();

    @Test
    public void testCreatePolicy() throws ClassNotFoundException, SQLException {
        Policy policy = new Policy(900, "Demo Plan", "TestType", 999.99, 50000.00);
        boolean result = service.createPolicy(policy);
        assertTrue(result); // true
    }

    @Test
    public void testGetPolicy() throws ClassNotFoundException, SQLException {
        Policy policy = new Policy(900, "Demo Plan", "Health", 999.99, 50000.00);
        service.createPolicy(policy);  
        
        Policy fetchedPolicy = service.getPolicy(900);
        assertNotNull("Policy should not be null", fetchedPolicy);
        assertEquals(900, fetchedPolicy.getPolicyId());
        assertEquals("Demo Plan", fetchedPolicy.getPolicyName());
    }


    @Test
    public void testUpdatePolicy() throws ClassNotFoundException, SQLException {
        Policy policy = service.getPolicy(900);
        policy.setPolicyType("UpdatedType");
        policy.setPremiumAmount(888.88);
        boolean result = service.updatePolicy(policy);
        assertTrue(result);
    }

    @Test
    public void testGetAllPolicies() throws ClassNotFoundException, SQLException {
        Collection<Policy> policies = service.getAllPolicies();
        assertNotNull(policies);
        assertTrue(policies.size() > 0); // At least 1
    }

    @Test
    public void testDeletePolicy() throws ClassNotFoundException, SQLException {
        boolean result = service.deletePolicy(900);
        assertTrue(result);
    }
}

