package monk.com.mx.misalleandroid.model.dataModels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by edago on 7/20/17.
 */
public class Pago {
    private Integer id;
    private Date fecha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setFecha(String string) throws ParseException {
        SimpleDateFormat stringFormater = new SimpleDateFormat("yyyy-MM-dd");
        setFecha(stringFormater.parse(string));
    }

    public Pago(){

    }

    public Pago(Integer id, String date) throws ParseException {
        this.id = id;
        setFecha(date);
    }
}
