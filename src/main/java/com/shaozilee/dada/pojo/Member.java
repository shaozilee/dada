package com.shaozilee.dada.pojo;

import java.util.HashMap;

/**
 * Created by lee on 16-4-27.
 */
public class Member extends HashMap{
    public Integer uid;
    public String username;
    public String password;
    public String email;
    public Integer status=0;
    public Integer regdate=0;
    public Integer credits=0;
    public Long lastloginip=0L;
    public Long lastlogintime=0L;

}
