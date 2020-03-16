package in.bit_by_bit.navio.ui.gettersetter;

public class PathGS {

    private int fxcord, fycord, txcord, tycord, fid, tid;

    public PathGS(int fxcord, int fycord, int txcord, int tycord, int fid, int tid){
        this.fxcord = fxcord;
        this.fycord = fycord;
        this.txcord = txcord;
        this.tycord = tycord;
        this.fid = fid;
        this.tid = tid;
    }

    public int getFxcord(){
        return fxcord;
    }

    public int getFycord(){
        return fycord;
    }

    public int getTxcord(){
        return txcord;
    }

    public int getTycord(){
        return tycord;
    }

    public int getFid(){
        return fid;
    }

    public int getTid(){
        return tid;
    }
}
