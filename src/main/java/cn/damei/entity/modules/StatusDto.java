package cn.damei.entity.modules;
import org.apache.commons.lang3.StringUtils;
/**
 * 接口结果返回实体
 * @author Liwancai
 * @version 2017-05-06
 */
public class StatusDto<T> {
	
	private final static String RESP_STATUS_CODE_SUCCESS = "200";
	private final static String RESP_STATUS_CODE_FAIL = "201";
    /**
     * 状态码
     */
    protected String code;

    /**
     * 信息
     */
    protected String message;

    /**
     * 携带数据
     */
    protected T data;
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

	public static StatusDto<String> buildSuccessStatusDto() {
        return buildSuccessStatusDto("Success");
    }

    public static <E> StatusDto<E> buildDataSuccessStatusDto() {
        return buildDataSuccessStatusDto("Success");
    }


    public static <E> StatusDto<E> buildDataSuccessStatusDto(String message) {
        StatusDto<E> dto = new StatusDto<E>();
        dto.code = RESP_STATUS_CODE_SUCCESS;
        dto.message = message;
        return dto;
    }


    public static StatusDto<String> buildSuccessStatusDto(String message) {
        StatusDto<String> dto = new StatusDto<String>();
        dto.code = RESP_STATUS_CODE_SUCCESS;
        dto.message = message;
        return dto;
    }

    /**
     * 自定义code
     *
     * @param message 消息信息
     * @param code    code
     * @return
     */
    public static StatusDto<String> buildSuccessStatusDtoWithCode(String message, String code) {
        StatusDto<String> dto = new StatusDto<String>();
        dto.code = code;
        dto.message = message;
        return dto;
    }

    public static StatusDto<String> buildFailureStatusDto() {
        return buildFailureStatusDto("Failure");
    }

    public static StatusDto<String> buildFailureStatusDto(String message) {
        StatusDto<String> dto = new StatusDto<String>();
        dto.code = RESP_STATUS_CODE_FAIL;
        dto.message = message;
        return dto;
    }


    public static <E> StatusDto<E> buildDataFailureStatusDto() {
        return buildDataFailureStatusDto("Failure");
    }


    public static <E> StatusDto<E> buildDataFailureStatusDto(String message) {
        StatusDto<E> dto = new StatusDto<E>();
        dto.code = RESP_STATUS_CODE_FAIL;
        dto.message = message;
        return dto;
    }

    public boolean isSuccess() {
        return StringUtils.equals(this.code, RESP_STATUS_CODE_SUCCESS);
    }

    public static StatusDto<String> buildStatusDtoWithCode(String code, String message) {
        StatusDto<String> dto = new StatusDto<>();
        dto.code = code;
        dto.message = message;
        return dto;
    }

    public static <E> StatusDto<E> buildDataSuccessStatusDto(String message, E data) {
        StatusDto<E> dto = buildDataSuccessStatusDto(message);
        dto.data = data;
        return dto;
    }

    public static <E> StatusDto<E> buildDataSuccessStatusDto(E data) {
        StatusDto<E> dto = buildDataSuccessStatusDto();
        dto.data = data;
        return dto;
    }
}