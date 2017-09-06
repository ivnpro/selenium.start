Feature: Authentication


  Scenario: Successfully logging in
    Given the Bloomy admin login page
    When logging in as an admin
    Then the home page admin is available

  Scenario: Successfully create user
    Given the Bloomy admin main page
    When from side menu search only men account
    Then check search men account

#@test
  Scenario: Successfully create agency
    Given the Bloomy admin main page
    When create agency from table:
    |Title|email|password|
    |test|t1@tf.co|aaaaax|
    |test2|t2@tf.co|00000x|
    |Lady Bugs|fffuuu@tf.co|00000x|



  Scenario: Successfully create user
    Given the Bloomy admin main page
    When from side menu create new user
    And create user from table:
    |name|email|birthday|
    |test|t1@tf.co|05.03.1994|
    |test2|t2@tf.co|05.03.1994|