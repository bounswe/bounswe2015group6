package application.miscalleneous;

public class Result {
    public static final String RESULT_OK = "OK";
    public static final String RESULT_FAILED = "FAILURE";

    private String result;
    private String resultMessage;

    public Result(){}

    public Result(String result, String resultMessage){
        this.result = result;
        this.resultMessage = resultMessage;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return result;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResultMessage() {
        return resultMessage;
    }
}
