package BmobTable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by Administrator on 2016/3/23.
 */
public class allRole extends BmobObject {

    /**
     * allRole 表
     */
    private static final long serialVersionUID = 1L;

    private String roleName;
    private String roleExp;
    private String HP;
    private String SAN;
    private String HUN;
    private String DMG;
    private String hunNeed;
    private String special;
    private String roleInfo;
    private BmobFile roleImg;

    public String getRoleName() {
        return roleName;
    }

    public String getRoleExp() {
        return roleExp;
    }

    public String getHP() {
        return HP;
    }

    public String getSAN() {
        return SAN;
    }

    public String getHUN() {
        return HUN;
    }

    public String getDMG() {
        return DMG;
    }

    public String getHunNeed() {
        return hunNeed;
    }

    public String getSpecial() {
        return special;
    }

    public String getRoleInfo() {
        return roleInfo;
    }

    public BmobFile getRoleImg() {
        return roleImg;
    }


}
