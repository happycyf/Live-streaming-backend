package top.vx520.pojo;
//获取提现数据
public class WithdrawalPojo {
//    主播编号
    private int id;
//    提现日期
    private String data;
//    提现金额
    private String money;
//    提现状态 0在申请 1成功 2驳回
    private int state;
//    提现账号
    private String payAccount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPayAccount() {
        return payAccount;
    }

    public void setPayAccount(String payAccount) {
        this.payAccount = payAccount;
    }
}
