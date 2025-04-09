package com.java.insurance.main;

import com.java.insurance.dao.*;
import com.java.insurance.model.Policy;
import com.java.insurance.exception.PolicyNotFoundException;

import java.util.Collection;
import java.util.Scanner;

public class MainModule {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            IPolicyService service = new InsuranceServiceImpl();
            int choice;

            do {
                System.out.println("\n---------- Insurance Policy Management --------------");
                System.out.println("1. Add Policy");
                System.out.println("2. View All Policies");
                System.out.println("3. Search Policy by ID");
                System.out.println("4. Update Policy");
                System.out.println("5. Delete Policy");
                System.out.println("6. Exit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        System.out.print("Enter Policy ID: ");
                        int pid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Policy Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Policy Type: ");
                        String type = sc.nextLine();
                        System.out.print("Enter Premium Amount: ");
                        double premiumAmount = sc.nextDouble();
                        System.out.print("Enter Coverage Amount: ");
                        double coverageAmount = sc.nextDouble();

                        Policy newPolicy = new Policy(pid, name, type, premiumAmount, coverageAmount);
                        boolean added = service.createPolicy(newPolicy);
                        System.out.println(added ? "Policy added successfully!!" : "Failed to add policy...");
                        break;

                    case 2:
                        Collection<Policy> policies = service.getAllPolicies();
                        if (policies.isEmpty()) {
                            System.out.println("No policies found...");
                        } else {
                            policies.forEach(System.out::println);
                        }
                        break;

                    case 3:
                        System.out.print("Enter Policy ID to search: ");
                        int searchId = sc.nextInt();
                        Policy foundPolicy = service.getPolicy(searchId);
                        if (foundPolicy != null) {
                            System.out.println(foundPolicy);
                        } else {
                            throw new PolicyNotFoundException("Policy ID " + searchId + " not found...");
                        }
                        break;

                    case 4:
                        System.out.print("Enter Policy ID to update: ");
                        int updateId = sc.nextInt();
                        Policy policyToUpdate = service.getPolicy(updateId);
                        if (policyToUpdate != null) {
                            sc.nextLine();
                            System.out.print("Enter new Policy Name: ");
                            String newName = sc.nextLine();
                            System.out.print("Enter new Policy Type: ");
                            String newType = sc.nextLine();
                            System.out.print("Enter new Premium Amount: ");
                            double newPremiumAmount = sc.nextDouble();
                            System.out.print("Enter new Coverage Amount: ");
                            double newCoverageAmount = sc.nextDouble();

                            policyToUpdate.setPolicyName(newName);
                            policyToUpdate.setPolicyType(newType);
                            policyToUpdate.setPremiumAmount(newPremiumAmount);   
                            policyToUpdate.setCoverageAmount(newCoverageAmount); 

                            boolean updated = service.updatePolicy(policyToUpdate);
                            System.out.println(updated ? "Policy updated successfully!" : "Update failed...");
                        } else {
                            throw new PolicyNotFoundException("Policy ID " + updateId + " not found...");
                        }
                        break;

                    case 5:
                        System.out.print("Enter Policy ID to delete: ");
                        int deleteId = sc.nextInt();
                        boolean deleted = service.deletePolicy(deleteId);
                        System.out.println(deleted ? "Policy deleted successfully!!" : "Deletion failed...");
                        break;

                    case 6:
                        System.out.println("Exiting the system...Byee..");
                        break;

                    default:
                        System.out.println("Invalid choice... Try again!!");
                        break;
                }

            } while (choice != 6);

        } catch (PolicyNotFoundException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
