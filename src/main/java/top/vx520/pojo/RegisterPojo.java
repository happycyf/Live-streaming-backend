package top.vx520.pojo;
//重置密码
public class RegisterPojo extends UserInformationPojo{
//    重置密码
    private String pwd;

//    重置头像地址
    private String img;
//    重置验证码
    private String registerYZM;
//    用户key
    private String uKey;

    public String getuKey() {
        return uKey;
    }

    public void setuKey(String uKey) {
        this.uKey = uKey;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }



    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getRegisterYZM() {
        return registerYZM;
    }

    public void setRegisterYZM(String registerYZM) {
        this.registerYZM = registerYZM;
    }
}
