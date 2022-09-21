Feature: Sign up a user
  As a new user
  I want to sign up
  So that I can see personalised content

  Scenario: Can sign up
    Given I am a new user
    And I am on sign up page
    When I sign up
    Then I should be taken to the user homepage

  Scenario Template: Sign up with existing user
    Given I am an existing conduit user
    When I sign up with <info>
    Then I should see an error message about <info>

    Examples:
      | info     |
      | email    |
      | username |

