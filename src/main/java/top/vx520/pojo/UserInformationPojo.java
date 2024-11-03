package top.vx520.pojo;
//用户信息
public class UserInformationPojo {
//    账号
    private String account;
//    性别
    private String sex;
//    昵称
    private String uName;
//    手机号
    private String phone;
//    地址
    private String adress;
//    类型id 1 普通用户 2 管理员 3主播
    private int uTypeId;
//    粉丝数
    private int fan;

    public int getuTypeId() {
        return uTypeId;
    }

    public void setuTypeId(int uTypeId) {
        this.uTypeId = uTypeId;
    }

    public int getFan() {
        return fan;
    }

    public void setFan(int fan) {
        this.fan = fan;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getuName() {
        return uName;
    }

    public void setuName(String uName) {
        this.uName = uName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
