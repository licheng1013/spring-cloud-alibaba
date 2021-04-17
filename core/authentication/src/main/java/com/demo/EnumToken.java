package com.demo;

import lombok.Getter;

/**
 * @author root
 * @description TODO
 * @date 2021/4/17 13:43
 */
@Getter
public enum EnumToken {
    WEB("web"),PC("pc");
    private final String type;
    EnumToken(String type){
        this.type  = type;
    }
}
