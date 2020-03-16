package in.bit_by_bit.navio.ui.gettersetter;

public class ItemGS {

    private int id;
    private int slot;
    private String name;

    public ItemGS(int id, String name, int slot){
        this.id = id;
        this.name = name;
        this.slot = slot;
    }

    public int getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getSlot(){
        return slot;
    }
}
