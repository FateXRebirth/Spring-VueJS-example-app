package example.Response;

import org.json.simple.JSONObject;

public class Result {

    private JSONObject returnData;
    private Integer returnCode;
    private String returnMessage;

    public JSONObject getReturnData() {
        return returnData;
    }

    public void setReturnData(JSONObject returnData) {
        this.returnData = returnData;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return returnMessage;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }
}
