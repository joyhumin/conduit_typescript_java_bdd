Feature: Can visit profile
  As a user
  I would like to go to my profile page
  So that I could see my profile details

  Scenario: Can see user profile
    Given I am an existing conduit user
    And I logged in
    When I want to visit my profile
    Then I should be taken to user profile page
    And I should see profile details
