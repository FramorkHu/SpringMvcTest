package com.myorg.mobile;

/**
 * Created by huyan on 15/11/25.
 */
public class EdgeValue extends PropertyValue{

    String id;
    String outRela;
    String inRela;


    public EdgeValue(String id, String outRela, String inRela){

        super(id);
        this.outRela = outRela;
        this.inRela = inRela;
    }

    public String getOutRela() {
        return outRela;
    }

    public String getInRela() {
        return inRela;
    }

    public String getId() {
        return id;
    }
}
