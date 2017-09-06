Feature: Agency


  Scenario: Successfully create new agency girl
    Given the agency main page
    When go to page add profile
    And create new profiles in agency from table:
    |name|birthday|interests|
    |test|05.03.1994|05.03.1994|
    |test2|05.03.1992|02.03.1994|
#    Then the home page admin is available


  Scenario: Edit girl profile
    Given the agency "Lady Bugs" main page
    When edit profile 1329
    And change password for profile 1329

#@test
  Scenario: Successfully create operator
    Given the agency "Lady Bugs" main page
    When go to page operator list
    And create operator from table with ID girls:
      |name|rate|idGirls|
      |test|0.07|1255, 1441|

#@test
  Scenario: Change pass and write JSON for all profile in agency
    Given the agency "Lady Bugs" main page
    When edit and change pass to "00000x" to all girls


  @prod
  Scenario: Go to another prod agency
    Given the prod agency with email "a.akimkin@topface.com" and pass "6VpYXp" main page
    When edit profile 325635
    And change password for profile 3256355
    Then check search men account
