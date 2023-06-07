@ActionUser
Feature: Action User

  @getprofile
  Scenario: Get Profile
    When user hit body "get_profile.json" for send GET request "get profile"

    @activateuser
  Scenario: Action Activate User
    When user hit body "action_activate_user.json" for send POST request "action activate user"

      @blacklistuser
  Scenario: Action Blacklist User
    When user hit body "action_blacklist_user.json" for send POST request "action blacklist user"