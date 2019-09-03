import java.util.GregorianCalendar;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time
{
    private GregorianCalendar calendar; 
    private SimpleDateFormat sdf;

    public Time() {
        calendar = new GregorianCalendar();
        sdf = new SimpleDateFormat("MMM dd, y hh:mm:ss a");
    }

    public String increment(int field) {
        calendar.add(field, 1);
        return getTime();
    }

    public String getTime() {
        return sdf.format(calendar.getTime());
    }

    public Date time() {
        return calendar.getTime();
    }
}
