package starter.base.method;

import net.serenitybdd.rest.SerenityRest;
import starter.base.api.ApiEndpoints;


import javax.xml.crypto.Data;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class OperationMethodAction {
    public void postSendStatementLetter(File fileSL) throws IOException{
        String accessToken = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/api_token_cow.json", "access-token");
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("x-access-token", accessToken)
                .body(fileSL)
                .post(ApiEndpoints.post_send_statement_letter).then().extract().response();

    }
    public void getSubmissionStatementLetter(File file) throws IOException {
        String accessToken = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/api_token_cow.json", "access-token");
        SerenityRest.given()
                .header("x-access-token", accessToken)
                .get(ApiEndpoints.get_submission_statement_letter).then().extract().response();

        String letterID = SerenityRest.lastResponse().jsonPath().getJsonObject("data[0]['id']").toString();
        DataUtils.writeUsingFileWriter(letterID, "letter_id");

    }
    public void postApproveStatementLetter(File fileSL) throws IOException{
        String fileLetterID = System.getProperty("user.dir") + "/src/test/java/outputfile/letter_id.txt";
        String accessToken = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/api_token_cow.json", "access-token");
        String content = DataUtils.readFileintoString(fileSL);

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("x-access-token", accessToken)
                .body(content.toString().replace("{{letterID}}", DataUtils.readUsingFileReader(fileLetterID)))
                .post(ApiEndpoints.post_approval_statement_letter).then().extract().response();

    }
//
    public void getSubmitStatementletter(File fileSL) throws IOException{
        String accessToken = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/api_token_cow.json", "access-token");
        String userID = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/send_statement_letter.json", "user_id");
        String transactionID = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/params_statement_letter.json", "transaction_id");

        SerenityRest.given()
                .header("x-access-token", accessToken)
                .get(ApiEndpoints.get_submit_statement_letter + userID + "/trx?transactionId=" + transactionID).then().extract().response();

    }

    public void getUserProfile(File file) throws IOException{
        String accessToken = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/api_token_cow.json", "access-token");
        String userID = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/params_user_id.json", "user_id");

        SerenityRest.given()
                .header("x-access-token", accessToken)
                .get(ApiEndpoints.get_user_id + "/list?q=" + userID + "&type=user_id&payment_gateway_id=1002").then().extract().response();
    }

    public void getListJiraIssues(File file) throws IOException {
        String accessToken = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/api_token_cow.json", "access-token");

        SerenityRest.given()
                .header("x-access-token", accessToken)
                .get(ApiEndpoints.get_list_jira_issues).then().extract().response();
    }

    public void postCreateJira(File file) throws IOException {
    }

    public void postActionActivateUser(File file) throws IOException {
        String accessToken = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/api_token_cow.json", "access-token");
        String cookie = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/api_cookie_cow.json", "cookie");
        String userID = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/params_user_id.json", "user_id");

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("cookie", cookie)
                .header("x-access-token", accessToken)
                .body(file)
                .post(ApiEndpoints.get_user_id + "/" + userID + "/update-status").then().extract().response();
    }

    public void postActionBlacklistUser(File file) throws IOException {
        String cookie = DataUtils.getTestData("src/test/java/outputfile/cookie.json", "AWSALB");
        String userID = DataUtils.getTestData("src/test/resources/payload/payload_operation_team/params_user_id.json", "user_id");

        SerenityRest.given()
                .header("Content-Type", "application/json")
                .header("cookie", cookie)
                .body(file)
                .post(ApiEndpoints.get_user_id + "/" + userID + "/update-status").then().extract().response();
    }

    public void postLoginCow(File file) throws IOException {
        SerenityRest.given()
                .header("Content-Type", "application/json")
                .body(file)
                .post(ApiEndpoints.post_login_cow).then().extract().response();

        String cookie = SerenityRest.lastResponse().cookies().toString();
        DataUtils.writeUsingFileJson(cookie, "cookie");
        System.out.println(cookie);

    }

}
