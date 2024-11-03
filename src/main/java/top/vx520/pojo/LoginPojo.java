package top.vx520.pojo;

public class LoginPojo {
    //    用户昵称
    private String  uName;
    //    登录账号
    private String account;
    //    登录密码
    private String pwd;
    //    图片地址
    private String img;
    //    用户状态 1普通用户 2管理员 3主播
    private int uTypeId;

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
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

    @Override
    public String toString() {
        return "LoginPojo{" +
                "uName='" + uName + '\'' +
                ", account='" + account + '\'' +
                ", pwd='" + pwd + '\'' +
                ", img='" + img + '\'' +
                ", uTypeId=" + uTypeId +
                '}';
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getuTypeId() {
        return uTypeId;
    }

    public void setuTypeId(int uTypeId) {
        this.uTypeId = uTypeId;
    }
}
