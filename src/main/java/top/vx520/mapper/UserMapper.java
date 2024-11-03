package top.vx520.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.vx520.pojo.*;

import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 登录查询
     * @param loginPojo 登录信息
     * @return 返回登录信息
     */
    LoginPojo query(LoginPojo loginPojo);

    /**
     * 进行用户注册
     * @param registerPojo 注册信息
     * @return 是否成功
     */
    int registerUser(RegisterPojo registerPojo);

    /**
     * 获取旧密码
     * @param account 账号
     * @return 返回旧密码
     */
    String getOldPassword(String account);
    /**
     * 获得用户基本信息
     * @param account 账号
     * @return 用户信息
     */
    UserInformationPojo getUserInformation(String account);
    /**
     * 判断手机号是否注册了
     *
     * @param phone 手机号
     * @return 获得内容已经注册 没有就是没注册
     */
    String queryPhone(String phone);

    /**
     * 修改密码
     *
     * @param phone    手机号
     * @param password 密码
     * @return 修改成功与否 大于1成功 0失败
     */
    int setPassWord(String phone, String password);

    /**
     * 获取图片地址
     * @param loginPojo 登录对象
     * @return 图片路径
     */
    String getImgSrc(LoginPojo loginPojo);

    /**
     * 获取粉丝数
     *
     * @param account 账号
     * @return 返回粉丝数
     */
    int getFan(String account);

    /**
     * 获取主播推流key
     * @param account 账号
     * @return 推流的key键
     */
    String getTKey(String account);
    /**
     * 获得用户粉丝榜前一百以总发送礼物做降序排行
     *
     * @param account 得到礼物的主播
     * @return 满足条件的礼物
     */
    List<UserFenSiPojo> getUserFenSi(String account);

    /**
     * 获取今日礼物收益
     *
     * @param account 主播账号
     * @return 前一百礼物姓名已经金额
     */
    List<JinRiLiWuShouYiPojo> getJinRiLiWu(String account);

    /**
     * 获得主播余额
     * @param account 主播账号
     * @return 主播余额
     */
    double getBlance(String account);

    /**
     * 今日收益
     * @param account 账号
     * @return 今日收益
     */
    double getToDayMoney(String account);

    /**
     * 获得昨日收益
     * @param account 账号
     * @return 昨日收益
     */
    double getYesterdayMoney(String account);

    /**
     * 本月收益
     * @param account 账号
     * @return 本月收益
     */
    double getMonthlyIncome(String account);
    /**
     * 上月收益
     * @param account 账号
     * @return 上月收益
     */
    double getLastMonthMoney(String account);

    /**
     * 添加提现订单
     * @param account 提现账号
     * @param money 提现金额
     * @return 是否添加成功
     */
    int addWithdrawal(@Param("account") String account,@Param("money") double money,@Param("payAccount") String payAccount);

    /**
     * 提现扣款
     * @param account 扣款账号
     * @param money 扣款金额
     * @return 更改行数
     */
    int Deduction(@Param("account") String account,@Param("money") double money);

    /**
     * 获得提款数据
     * @param account 提现账号
     * @return 提款金额 提款状态 提现编号 提现日期
     */
    List<WithdrawalPojo> getWithdrawal(String account);

    /**
     * 获取主播月收益
     * @param account 主播账号
     * @return 主播数据
     */
    List<MonthlyIncomePojo> getMonthlyIncomePojo(String account);

    /**
     * 获取本月最大最小收益
     * @param account 账号
     * @return 本月的最大最小收益
     */
    MonthlyIncomePojo getMaxAndMinMonthlyIncomePojo(@Param("account") String account,@Param("month") int month);

    /**
     * 获得礼物总榜
     * @param account 账号
     * @return 返回礼物榜前一百
     */
    List<AllGiftPojo> getAllGiftList(String account);

    /**
     * 获取用户手机号
     * @param account 账号
     * @return 手机号
     */
    String getUserPhone(String account);

    /**
     * 修改主播基本信息
     * @param setAnchorInformation 修改对象
     * @return 是否成功
     */
    int SetAnchorInformation(SetAnchorInformation setAnchorInformation);

    int setImgUrl(@Param("uuid") String uuid,@Param("account") String account);

}
