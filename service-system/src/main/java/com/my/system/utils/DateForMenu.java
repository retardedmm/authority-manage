package com.my.system.utils;

import com.my.model.system.SysMenu;

import java.util.ArrayList;
import java.util.List;

public class DateForMenu {

    public static List<SysMenu> toTree(List<SysMenu> sysMenus) {
        List<SysMenu> root = new ArrayList<>();
        for(SysMenu sysMenu : sysMenus){
            if(sysMenu.getParentId()==0){
                root.add(getChildren(sysMenus,sysMenu));
            }
        }

        return root;
    }
    public static SysMenu getChildren(List<SysMenu> sysMenus,SysMenu root){
        for(SysMenu sysMenu : sysMenus){
            if(sysMenu.getParentId()==root.getId()){
                root.getChildren().add(getChildren(sysMenus,sysMenu));
            }
        }
        return root;
    }
}
