package in.bit_by_bit.navio.ui.gettersetter;

public class SlotGS  {

    private int slot, xcord, ycord;

    public SlotGS(int slot, int xcord, int ycord){
        this.slot = slot;
        this.xcord = xcord;
        this.ycord = ycord;
    }

    public int getSlot(){
        return slot;
    }

    public int getXcord(){
        return xcord;
    }

    public int getYcord(){
        return ycord;
    }
}
