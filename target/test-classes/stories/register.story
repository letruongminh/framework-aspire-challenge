Narrative:
In order for the user to start using the application service, they will firstly need to complete registration flow

Meta:
@test-suite: Registration
@author: Le Truong Minh

Scenario: Successful completed registration flow as appointed director
Given a user with detailed data
When I complete registration flow as appointed director
Then I can meet the step for waiting the approval