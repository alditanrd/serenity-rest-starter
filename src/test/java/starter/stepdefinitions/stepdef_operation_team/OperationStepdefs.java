package starter.stepdefinitions.stepdef_operation_team;

import io.cucumber.java.en.When;
import starter.base.method.OperationMethodAction;

import java.io.File;
import java.io.IOException;

public class OperationStepdefs {

    OperationMethodAction oprmethod = new OperationMethodAction();
    String pathPayload = "src/test/resources/payload/payload_operation_team/";
    File file;

    @When("user hit body {string} for send POST request {string}")
    public void userHitBodyForSendPOSTRequest(String objPayload, String objAction) throws IOException {
        pathPayload += objPayload;
        file = new File(String.format(pathPayload));
        switch (objAction) {
            case "send statement letter":
                oprmethod.postSendStatementLetter(file);
                break;
            case "approve statement letter":
                oprmethod.postApproveStatementLetter(file);
                break;
            case "create jira":
                oprmethod.postCreateJira(file);
                break;
            case "action activate user":
                oprmethod.postActionActivateUser(file);
                break;
            case "action blacklist user":
                oprmethod.postActionBlacklistUser(file);
                break;
            default:
                System.out.println("Not found parameter");
                break;
        }
    }

    @When("user hit body {string} for send GET request {string}")
    public void userHitBodyForSendGETRequest(String objPayload, String objAction) throws IOException {
        pathPayload += objPayload;
        file = new File(String.format(pathPayload));
        switch (objAction) {
            case "submit statement letter":
                oprmethod.getSubmitStatementletter(file);
                break;
            case "submission statement letter":
                oprmethod.getSubmissionStatementLetter(file);
                break;
            case "get user profile":
                oprmethod.getUserProfile(file);
                break;
            case "get list jira issues":
                oprmethod.getListJiraIssues(file);
                break;
            case "get profile":
                oprmethod.getUserProfile(file);
                break;
            case "login cow":
                oprmethod.postLoginCow(file);
                break;
            default:
                System.out.println("Not found parameter");
                break;
        }
    }
}
