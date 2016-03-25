package BmobTable;

/**
 *  Application ID，SDK初始化必须用到此密钥 REST API Key，REST API请求中HTTP头部信息必须附带密钥之一
 *  Secret Key，是SDK安全密钥，不可泄漏，在云端逻辑测试云端代码时需要用到 Master
 *  Key，超级权限Key。应用开发或调试的时候可以使用该密钥进行各种权限的操作，此密钥不可泄漏
 */
public class BmobKey {


    static final String App_ID = "dfb52b73dec4e1f53a91d3e78372b1f7";
    static final String RAK = "7d72ce3ec8ee5840ade38c2c7f6d42c0";
    static final String SECK = "fd74a7f6f84e87d9";
    static final String MASK = "eef7b01e1aefc5ae2edf3a5189b7b95e";

    public static String getApp_ID() {
        return App_ID;
    }

    public static String getRAK() {
        return RAK;
    }

    public static String getSECK() {
        return SECK;
    }

    public static String getMASK() {
        return MASK;
    }
}
