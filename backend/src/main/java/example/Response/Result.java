package example.Response;

import org.json.simple.JSONObject;

public class Result {

    private JSONObject ReturnData;
    private Integer ReturnCode;
    private String ReturnMessage;

    public JSONObject getReturnData() {
        return ReturnData;
    }

    public void setReturnData(JSONObject returnData) {
        ReturnData = returnData;
    }

    public Integer getReturnCode() {
        return ReturnCode;
    }

    public void setReturnCode(Integer returnCode) {
        ReturnCode = returnCode;
    }

    public String getReturnMessage() {
        return ReturnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        ReturnMessage = returnMessage;
    }
}
