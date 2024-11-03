package top.vx520.pojo;

public class AchorPojo {
    private String title;
//    封面名
    private String coverName;
//    描述
    private String describe;
//    推流路径
    private String tKey;
//    状态 0开播1关播
    private int statu;
    public AchorPojo() {
    }

    public AchorPojo(String title, String coverName, String describe, String tKey) {
        this.title = title;
        this.coverName = coverName;
        this.describe = describe;
        this.tKey = tKey;
    }

    @Override
    public String toString() {
        return "AchorPojo{" +
                "title='" + title + '\'' +
                ", coverName='" + coverName + '\'' +
                ", describe='" + describe + '\'' +
                ", tKey='" + tKey + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCoverName() {
        return coverName;
    }

    public void setCoverName(String coverName) {
        this.coverName = coverName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String gettKey() {
        return tKey;
    }

    public void settKey(String tKey) {
        this.tKey = tKey;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }
}
