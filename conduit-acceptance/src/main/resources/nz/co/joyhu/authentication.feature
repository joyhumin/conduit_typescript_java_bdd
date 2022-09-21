Feature: Can sign in
  As a user
  I would like to sign in
  So that I could see personalised content

  Scenario: Can sign in
    Given I am an existing conduit user
    When I login when valid credentials
    Then I should be taken to personalised feed page

  Scenario: Can sign out
    Given I am an existing conduit user
    And I logged in
    When I log out
    Then I should be taken to homepage

  Scenario: Can register
    Given I am a unregsitered user
    When I register with account information
    Then I should be taken to personalised feed page