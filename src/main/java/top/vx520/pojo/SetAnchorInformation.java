package top.vx520.pojo;

public class SetAnchorInformation extends UserInformationPojo{
    private String oldPhone;
    private String setPeopleYZM;
    private String newPhone;

    public SetAnchorInformation() {
    }

    public SetAnchorInformation(String oldPhone, String setPeopleYZM, String newPhone) {
        this.oldPhone = oldPhone;
        this.setPeopleYZM = setPeopleYZM;
        this.newPhone = newPhone;
    }

    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    public String getSetPeopleYZM() {
        return setPeopleYZM;
    }

    public void setSetPeopleYZM(String setPeopleYZM) {
        this.setPeopleYZM = setPeopleYZM;
    }

    public String getNewPhone() {
        return newPhone;
    }

    public void setNewPhone(String newPhone) {
        this.newPhone = newPhone;
    }
}
