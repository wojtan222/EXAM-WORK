Feature: Address Management

  Scenario Outline: User can add a new address
    Given I open a web browser with the main page of the online shop
    When I log in with my email and password
    And I navigate to the addresses section
    And I select the "Create new address" option
    And I create new address using <keywordAlias> alias, <keywordAddress> address, <keywordCity> city, <keywordZipCode> zipCode, <keywordPhone> phone
    And I can see my new address
    Then  a new address is successfully added
    And I verify the newly created address details <expectedAlias> expectedAlias, <expectedAddress> expectedAddress, <expectedCity> expectedCity, <expectedZipCode> expectedZipCode, <expectedPhone> expectedPhone
    And I close the web browser

    Examples:
      | keywordAlias | keywordAddress  | keywordCity | keywordZipCode | keywordPhone | expectedAlias | expectedAddress | expectedCity | expectedZipCode | expectedPhone |
      | "Wojciech"    | "Chestnut St" | "Warsaw"   | "10-333"   | "503234132" | "Wojciech"      | "Chestnut St"  | "Warsaw"    | "10-333"       | "503234132"  |
