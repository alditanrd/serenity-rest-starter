@CreateJira
Feature: Create Jira

  Scenario: Get User Profile
    When user hit body "get_user_profile.json" for send GET request "get user profile"

  Scenario: Get List Jira Issues
    When user hit body "get_list_jira_issues.json" for send GET request "get list jira issues"

  Scenario: Create Jira
    When user hit body "create_jira.json" for send POST request "create jira"