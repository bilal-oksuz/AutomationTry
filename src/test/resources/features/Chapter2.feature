#@Chapter2
#Feature: Trendyol Test Automation Engineer case Rest service

#  Scenario: Verify that the API starts with an empty store
#    Given Get all books
#    Then Any book not exits
#
#  Scenario: Verify that title and author are required fields
#    Given Get all books
#    Then Check create book result
#
#  Scenario: Verify that title and author cannot be empty
#    Given Get all books
#    Then Check create book result
#
#  Scenario: Verify that you can create a new book via PUT
#    When Create a book
#    Then Check book result
#    Then Get book by Id
#    Then Check book result from getById response
#
#  Scenario: Verify that you cannot create a duplicate book
#    Given Get all books
#    Then Check create book result
