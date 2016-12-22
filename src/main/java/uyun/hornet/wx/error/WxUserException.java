package uyun.hornet.wx.error;

/**
 * Created by xuht on 2016/12/15.
 */
public class WxUserException extends RuntimeException {

    public WxUserException(String message) {
        super(message);
    }

    public WxUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
