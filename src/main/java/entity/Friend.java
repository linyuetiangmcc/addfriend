package entity;

public class Friend {
    //{\"commendId\":\"370558042\",\"commendedImageUrl\":\"http://thirdwx.qlogo.cn/m/132\",\"commendedName\":\"蜗牛\",\"endPage\":0,\"shareedNum\":1,\"startPage\":0}
    private String commendId;
    private String commendedImageUrl;
    private String commendedName;
    private int endPage;
    private int shareedNum;
    private int startPage;

    public String getCommendId() {
        return commendId;
    }

    public void setCommendId(String commendId) {
        this.commendId = commendId;
    }

    public String getCommendedImageUrl() {
        return commendedImageUrl;
    }

    public void setCommendedImageUrl(String commendedImageUrl) {
        this.commendedImageUrl = commendedImageUrl;
    }

    public String getCommendedName() {
        return commendedName;
    }

    public void setCommendedName(String commendedName) {
        this.commendedName = commendedName;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public int getShareedNum() {
        return shareedNum;
    }

    public void setShareedNum(int shareedNum) {
        this.shareedNum = shareedNum;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }
}
