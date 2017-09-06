Feature: Authentication

  @test
  Scenario: Successfully create agency
    Given the Bloomy admin main page
    When create agency from table:
      |Title|email|password|
      |test-agency0|test0@tf.co|aaaaax|
#      |test-agency1|test1@tf.co|00000x|

  @test
  Scenario: Successfully create new agency girl in agency0
    Given the agency "test-agency0" main page
    When go to page add profile
    And create new profiles in agency from table:
      |name|birthday|interests|
      |girl_a|05.03.1994|05.03.1994|
      |girl_b|05.03.1992|02.03.1994|
      |girl_c|05.03.1992|02.03.1994|
      |girl_d|05.03.1992|02.03.1994|
      |girl_e|05.03.1992|02.03.1994|
      |girl_f|05.03.1992|02.03.1994|
#    When the agency "test-agency0" main page
#    And go to page add profile
#    And create new profiles in agency from table:
#      |name|birthday|interests|
#      |girl_a|05.03.1994|05.03.1994|
#      |girl_b|05.03.1992|02.03.1994|
#      |girl_c|05.03.1992|02.03.1994|
#      |girl_d|05.03.1992|02.03.1994|
#      |girl_e|05.03.1992|02.03.1994|
#      |girl_f|05.03.1992|02.03.1994|

  @test
  Scenario: Successfully create operator
    Given set some feature
    Given the agency "test-agency0" main page
    When go to page operator list
    And create operator from table with name girls:
      |name|rate|NameGirl|
      |oper1|0.07|girl_a, girl_b, girl_c|
    When go to page operator list
    And create operator from table with name girls:
      |name|rate|NameGirl|
      |oper2|0.07|girl_d, girl_e, girl_f|

  @test
  Scenario: Successfully upload any photo agency girl
    Given the agency "test-agency0" main page
    When upload any photo to all profiles in agency

  @test
  Scenario: Successfully create all bombs
    Given the chat from girl with name "girl_a" from agency "test-agency0"
    When create all bombs

  @test
  Scenario: Successfully create all bombs
    Given the chat from girl with name "girl_b" from agency "test-agency0"
    When create all bombs

  @test
  Scenario: Successfully create all bombs
    Given the chat from girl with name "girl_c" from agency "test-agency0"
    When create all bombs

  @test
  Scenario: Successfully create all bombs
    Given the chat from girl with name "girl_d" from agency "test-agency0"
    When create all bombs

  @test
  Scenario: Successfully create all bombs
    Given the chat from girl with name "girl_e" from agency "test-agency0"
    When create all bombs

  @test
  Scenario: Successfully create all bombs
    Given the chat from girl with name "girl_f" from agency "test-agency0"
    When create all bombs

