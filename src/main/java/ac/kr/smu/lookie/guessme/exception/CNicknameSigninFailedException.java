package ac.kr.smu.lookie.guessme.exception;

public class CNicknameSigninFailedException extends RuntimeException{
    public CNicknameSigninFailedException(String msg, Throwable t){
        super(msg, t);
    }

    public CNicknameSigninFailedException(String msg){
        super(msg);
    }

    public CNicknameSigninFailedException(){
        super();
    }
}
