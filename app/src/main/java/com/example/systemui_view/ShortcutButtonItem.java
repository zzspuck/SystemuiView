package com.example.systemui_view;


/**
 * 快捷按钮数据模型
 */
public class ShortcutButtonItem {
     int id;                     // 按钮ID
     int iconResId;              // 图标资源ID
     int textResId;                // 按钮文本ID
     int signalId;               // 对应的信号ID（车辆设置）
     boolean isSystemSetting;    // 是否为系统设置
     int functionId;             // 系统设置功能ID
     boolean onlyWrite;          // 是否只写不读
     boolean isSelected;
     boolean isAvailable =  true;// 目前设置都可用

     ItemType itemType =  ItemType.NORMAL;

     int spansize = 1;


    public ShortcutButtonItem(int id, int iconResId, int textResId, int signalId, boolean onlyWrite, ItemType type,int spansize) {
        this.id = id;
        this.iconResId = iconResId;
        this.textResId = textResId;
        this.signalId = signalId;
        this.onlyWrite = onlyWrite;
        this.isSystemSetting = false;
        this.itemType = type;
        this.spansize =spansize;
    }


    public int getId() {
        return id;
    }

    public int getIconResId() {
        return iconResId;
    }

    public int getTextResId() {
        return textResId;
    }

    public int getSignalId() {
        return signalId;
    }

    public boolean isSystemSetting() {
        return isSystemSetting;
    }

    public int getFunctionId() {
        return functionId;
    }

    public boolean isOnlyWrite() {
        return onlyWrite;
    }


    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {

    }
}