package com.example.chenhuamou.meicai.model;

/**
 * Created by chenhuamou on 2017/9/30.
 */

public class Product {

    /**
     * productid : 1
     * productname : 生菜
     * classid : 1
     * productnumber : 123456
     * productspec : 500g/斤
     * productpricethis : 5.8
     * productpic : https://img10.360buyimg.com/n7/jfs/t5830/103/1434256965/462196/ac9b5b91/592678caN99905dbe.jpg
     */

    private String productid;
    private String productname;
    private String classid;
    private String productnumber;
    private String productspec;
    private String productpricethis;
    private String productpic;

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getClassid() {
        return classid;
    }

    public void setClassid(String classid) {
        this.classid = classid;
    }

    public String getProductnumber() {
        return productnumber;
    }

    public void setProductnumber(String productnumber) {
        this.productnumber = productnumber;
    }

    public String getProductspec() {
        return productspec;
    }

    public void setProductspec(String productspec) {
        this.productspec = productspec;
    }

    public String getProductpricethis() {
        return productpricethis;
    }

    public void setProductpricethis(String productpricethis) {
        this.productpricethis = productpricethis;
    }

    public String getProductpic() {
        return productpic;
    }

    public void setProductpic(String productpic) {
        this.productpic = productpic;
    }
}
