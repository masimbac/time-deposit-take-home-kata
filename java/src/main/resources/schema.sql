-- Drop tables if they exist (optional, for clean rebuilds)
DROP TABLE IF EXISTS withdrawals;
DROP TABLE IF EXISTS timeDeposits;

-- Parent table
CREATE TABLE timeDeposits (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              planType VARCHAR(50) NOT NULL,
                              balance DECIMAL(19,2) NOT NULL,
                              days INT NOT NULL
);

-- Child table with FK back to timeDeposits (cascades on delete to mirror orphanRemoval)
CREATE TABLE withdrawals (
                             id BIGINT AUTO_INCREMENT PRIMARY KEY,
                             timeDepositId INT NOT NULL,
                             amount DOUBLE,
                             dateTime TIMESTAMP,
                             CONSTRAINT fk_withdrawal_time_deposit
                                 FOREIGN KEY (timeDepositId)
                                     REFERENCES timeDeposits(id)
                                     ON DELETE CASCADE
);
