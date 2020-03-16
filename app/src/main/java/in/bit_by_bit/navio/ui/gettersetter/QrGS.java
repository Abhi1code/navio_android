package in.bit_by_bit.navio.ui.gettersetter;

public class QrGS {

    private int id, type, slot;
    private String name;

    public QrGS(int id, int type, String name, int slot){
        this.id = id;
        this.slot = slot;
        this.type = type;
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public int getSlot(){
        return slot;
    }

    public int getType(){
        return type;
    }

    public String getName(){
        return name;
    }
}
