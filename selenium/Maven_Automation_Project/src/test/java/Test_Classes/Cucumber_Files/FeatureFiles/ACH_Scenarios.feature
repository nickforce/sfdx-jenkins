Feature: ACH Test Scenarios

  Scenario: TC01 - Create Origination file for ACH credit & debit transactions
    Given I navigate to Salesforce page on Dev1 sandbox
    When I go to Betr-Client tab to create test client 1
    When I go to Betr-Client tab to create test client 2
    When I go to Transaction tab to create 2 debit transaction for test clients
    Then I call ACH batch job to Originate ACH Out file
    Then I verify specific data from each transaction matches with data from in ACH Page
    Then I verify transaction ID and amount matches with SF in ACH Out File
    Then I verify all transaction count equal to all Debit count in ACH Out File
    Then I verify all debit amount from a batch is equal to debit amount in batch control line
    Then I delete all QA transactions, clients and accounts from salesforce

