package in.bit_by_bit.navio.ui.gettersetter;

import android.util.Log;

import java.util.ArrayList;

public class PathConfig {

    private static PathConfig pathdata;
    private String itemNameToShow = "";

    /////////////////////////////
    private int floorid = 124;
    private double d_factor = 1;
    public int currentSlot = 0;
    public String currentBarcode = "";
    private ArrayList<ItemGS> itemSelected;
    private ArrayList<Integer> slotList;
    private ArrayList<QrGS> allqrList;
    private ArrayList<SlotGS> allSlotList;
    private ArrayList<PathGS> pathList;
    public int xcord = 0, ycord = 0;
    public int previousNodeToRemove = 0;

    private PathConfig() {
    }

    public static synchronized PathConfig getInstance() {

        if (pathdata == null) {
            pathdata = new PathConfig();
        }
        return pathdata;
    }

    public void setItemNameToShow(String name){
        itemNameToShow = name;
    }

    public String getItemNameToShow(){
        return itemNameToShow;
    }

    ///////////////////////////////////////////////////

    public void invalidateItemSelected(){
        itemSelected = null;
        slotList = null;
    }

    public void setSelectedItem(ItemGS itemGS){
        if(itemSelected == null){
            itemSelected = new ArrayList<>();
        }

        if(slotList == null){
            slotList = new ArrayList<>();
        }

        itemSelected.add(itemGS);
        setSlotItem(itemGS.getSlot());

    }

    public void unsetSelectedItem(ItemGS itemGS){
        if(itemSelected != null){
            itemSelected.remove(itemGS);
        }

        if(slotList != null){
            slotList.remove((Object)itemGS.getSlot());
        }
    }

    public ArrayList<ItemGS> getSelectedItemList(){
        return itemSelected;
    }

    public void setSlotItem(int slot){
        if(slotList == null)return;

        for(int i=0;i<slotList.size();i++){
            if(slot == slotList.get(i)){
                return;
            }
        }
        slotList.add(slot);
    }

    public ArrayList<Integer> getSlotList(){
        return slotList;
    }

    public String convertSlotListToString(){
        if(slotList == null)return "";

        String slots = "";
        for(int i=0;i<slotList.size();i++){
            if(i == (slotList.size()-1)){
                slots += slotList.get(i);
            } else {
                slots += slotList.get(i);
                slots += ",";
            }
        }
        return slots;
    }

    public void addSourceSlot(int slot){
        if(slotList == null)return;

        for(int i=0;i<slotList.size();i++){
            if(slotList.get(i) == slot){
                slotList.remove(i);
            }
        }
        slotList.add(0, slot);
    }

    public void removeNodeFromSlot(int slot){
        if(slotList == null)return;

        for(int i=0;i<slotList.size();i++){
            if(slotList.get(i) == slot){
                slotList.remove(i);
            }
        }
    }

    public void setQrList(ArrayList<QrGS> arrayList){
        this.allqrList = arrayList;
    }

    public ArrayList<QrGS> getQrList(){
        return allqrList;
    }

    public int getQrSlot(String qrcode){
        if(allqrList == null){
            return 0;
        }

        Log.e("ABHI", "qrcode slot finded");
        for(int i=0;i<allqrList.size();i++){
            if(allqrList.get(i).getName().equals(qrcode)){
                return allqrList.get(i).getSlot();
            }
        }
        return 0;
    }

    public void invalidateQrList(){
        allqrList = null;
    }

    public void setAllSlotList(ArrayList<SlotGS> arrayList){
        this.allSlotList = arrayList;
    }

    public ArrayList<SlotGS> getAllSlotList(){
        return allSlotList;
    }

    public void getSlotCoords(int slot){
        if(allSlotList == null){
            return;
        }

        for(int i=0;i<allSlotList.size();i++){
            if(allSlotList.get(i).getSlot() == slot){
                xcord = allSlotList.get(i).getXcord();
                ycord = allSlotList.get(i).getYcord();
                return;
            }
        }
    }

    public void invalidateAllSlotList(){
        allSlotList = null;
    }

    public void setFloorid(int floorid){
        this.floorid = floorid;
    }

    public int getFloorid(){
        return floorid;
    }

    public void setPathList(ArrayList<PathGS> arrayList){
        this.pathList = arrayList;
    }

    public ArrayList<PathGS> getPathList(){
        return pathList;
    }

    public String getSelectedSlotItems(int slot){
        String temp = "";
        for(int i=0;i<itemSelected.size();i++){
            if(itemSelected.get(i).getSlot() == slot){
                if(i == 0){
                    temp += itemSelected.get(i).getName();
                } else {
                    if(!temp.equals("")){
                        temp += "\n";
                    }
                    temp += itemSelected.get(i).getName();
                }
            }
        }
        return temp;
    }

    public void updateNewCoords(){
        PathGS pathGS = null;
        if(pathList == null || currentSlot == 0)return;

        for(int i=0;i<pathList.size();i++){
            if(pathList.get(i).getFid() == currentSlot || pathList.get(i).getTid() == currentSlot){
                pathGS = pathList.get(i);
                break;
            }
        }
        if(pathGS != null) {
            int dx = pathGS.getTxcord() - xcord;
            int dy = pathGS.getTycord() - ycord;
            double mag = (int) Math.hypot(dx, dy);

            double xinc = (d_factor * (dx / (mag*1.0)));
            double yinc = (d_factor * (dy / (mag*1.0)));
            if(xinc > 1)xinc = 1;
            if(xinc < -1)xinc = -1;
            if(yinc > 1)yinc = 1;
            if(yinc < -1)yinc = -1;
            if(xinc > 0.5 && xinc < 1)xinc = 1;
            if(xinc < -0.5 && xinc > -1)xinc = -1;
            if(xinc < 0.5 && xinc > -0.5)xinc = 0;
            if(yinc > 0.5 && yinc < 1)yinc = 1;
            if(yinc < -0.5 && yinc > -1)yinc = -1;
            if(yinc < 0.5 && yinc > -0.5)yinc = 0;
            Log.d("ABHI", "******************************* " + xcord + " " + ycord);
            Log.d("ABHI", "******************************** " + xinc + " "+ yinc);
            xcord += xinc;
            ycord += yinc;
//            xcord = (int) (xcord + (d_factor * (dx / (mag*1.0))));
//            ycord = (int) (ycord + (d_factor * (dy / (mag*1.0))));
//            xcord += 1;
//            ycord += 1;
            Log.e("ABHI", "******************************** " + xcord + " " + ycord);
        }
    }
}
