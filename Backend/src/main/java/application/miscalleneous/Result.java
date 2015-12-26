package application.miscalleneous;

/**
 * Class to inform result of process whether it is successfull or not
 */
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

    /**
     * setter
     * @param result
     */
    public void setResult(String result) {
        this.result = result;
    }

    /**
     * getter
     * @return
     */
    public String getResult() {
        return result;
    }

    /**
     * setter
     * @param resultMessage
     */
    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    /**
     * getter
     * @return
     */
    public String getResultMessage() {
        return resultMessage;
    }
}
