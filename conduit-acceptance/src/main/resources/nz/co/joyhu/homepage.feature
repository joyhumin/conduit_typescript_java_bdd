Feature: Can view the details of a homepage
  As a user I would like to view the homepage of conduit so that I could see the articles

  Scenario: Can go to the conduit homepage
    Given the conduit exists
    And I want to visit conduit homepage
    When I access the homepage
    Then I can see the banner