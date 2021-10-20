package model;

public class PresentacionModelo {
    private int pre_id,pre_museoid,pre_obraid;
    private String preFechaInicio;


    public PresentacionModelo(int pre_id,int pre_museoid,int pre_obraid, String preFechaInicio){
        this.pre_id = pre_id;
        this.pre_museoid = pre_museoid;
        this.pre_obraid = pre_obraid;
        this.preFechaInicio = preFechaInicio;
    }
    
    // only setter will be fecha

    public int getPreId(){
        return pre_id;
    }


    public int pre_museoid(){
        return pre_museoid;
    }

    public int pre_obraid(){
        return pre_obraid;
    }

    public String preFechaInicio (){
        return preFechaInicio;
    }

    // setter
    public void setPreFechaInicio(String preFechaInicio){
        this.preFechaInicio = preFechaInicio;
    }
}
