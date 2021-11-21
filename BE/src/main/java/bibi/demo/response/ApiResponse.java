package bibi.demo.response;

import lombok.Getter;

@Getter
public class ApiResponse<T> {

    private int status;
    private String message;
    private T data;

    public ApiResponse(StatusEnum status) {
        this.status = status.getStatusCode();
        this.message = status.getMessage();
    }
    public ApiResponse(StatusEnum status, T data) {
        this(status);
        this.data = data;
    }
}
