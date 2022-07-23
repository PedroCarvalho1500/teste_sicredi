package suport;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class Generator
{
    public static String  data_hora_file() {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("yyyyMMddhhmmss").format(ts);
    }
}
