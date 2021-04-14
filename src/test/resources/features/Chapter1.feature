@Chapter1
Feature: Trendyol Test Automation Engineer case

  Scenario: Save Boutique Data to csv
    Given Get Trendyol landing page
    When Save all boutique link to csv

  Scenario: Save Image response
    Given Get Trendyol landing page
    When Scroll on page and save Image response

  Scenario Outline: Login with users
    Given Get Trendyol landing page
    When As a customer login to Trendyol
    Then Login with user "<user>"
    Then Check login process
    Examples:
    |user|
    |validUser|
    |invalidPass|
    |invalidMail|
    |notMail|
    |nullMail|
    |nullPassword|