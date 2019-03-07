package example.Response;

import org.json.simple.JSONObject;

public class Result {

    private JSONObject ReturnData;
    private int ReturnCode;
    private String ReturnMessage;

    public JSONObject getReturnData() {
        return ReturnData;
    }

    public void setReturnData(JSONObject returnData) {
        ReturnData = returnData;
    }

    public int getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(int returnCode) {
        ReturnCode = returnCode;
    }

    public String getReturnMessage() {
        return ReturnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        ReturnMessage = returnMessage;
    }
}
