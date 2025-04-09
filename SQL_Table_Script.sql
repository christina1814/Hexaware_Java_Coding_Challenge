-- Database creation --------------------------------------------
CREATE DATABASE insurance;

USE insurance;

-- Table creation -----------------------------------------------
CREATE TABLE User (
    userId INT PRIMARY KEY,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE Policy (
    policyId INT PRIMARY KEY,
    policyName VARCHAR(100),
    policyType VARCHAR(50),
    premiumAmount DECIMAL(10, 2),
    coverageAmount DECIMAL(10, 2)
);

CREATE TABLE Client (
    clientId INT PRIMARY KEY,
    clientName VARCHAR(100),
    contactInfo VARCHAR(150),
    policyId INT,
    FOREIGN KEY (policyId) REFERENCES Policy(policyId)
);

CREATE TABLE Claim (
    claimId INT PRIMARY KEY,
    claimNumber VARCHAR(100),
    dateFiled DATE,
    claimAmount DECIMAL(10, 2),
    status VARCHAR(50),
    policyId INT,
    clientId INT,
    FOREIGN KEY (policyId) REFERENCES Policy(policyId),
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
);

CREATE TABLE Payment (
    paymentId INT PRIMARY KEY,
    paymentDate DATE,
    paymentAmount DECIMAL(10, 2),
    clientId INT,
    FOREIGN KEY (clientId) REFERENCES Client(clientId)
); 

-- Insertion of values -----------------------------------------------

-- user table
INSERT INTO User VALUES (1, 'admin', 'admin123', 'Admin');
INSERT INTO User VALUES (2, 'agent1', 'pass123', 'Agent');

-- policy table
INSERT INTO Policy VALUES (101, 'Life Cover Basic', 'Life', 1500.00, 100000.00);
INSERT INTO Policy VALUES (102, 'Health Secure', 'Health', 800.00, 50000.00);

-- client table
INSERT INTO Client VALUES (201, 'John Doe', 'john@example.com', 101);
INSERT INTO Client VALUES (202, 'Jane Smith', 'jane@example.com', 102);

-- claim table
INSERT INTO Claim VALUES (301, 'CLM2024A1', '2024-01-15', 5000.00, 'Pending', 101, 201);
INSERT INTO Claim VALUES (302, 'CLM2024B2', '2024-02-20', 2000.00, 'Approved', 102, 202);

-- payment table
INSERT INTO Payment VALUES (401, '2024-01-20', 1500.00, 201);
INSERT INTO Payment VALUES (402, '2024-02-25', 800.00, 202);

-- ------------------------------------------------------------------------------------
select * from Policy;

