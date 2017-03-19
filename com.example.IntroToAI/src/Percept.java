
public class Percept {

    LOCATION location;
    STATUS status;

    public Percept(LOCATION location, STATUS status) {
        this.location = location;
        this.status = status;
    }

    public void setLocation(LOCATION location) {
        this.location = location;
    }

    public void setStatus(STATUS status) {
        this.status = status;
    }

    public String toString() {
        return "[" + location + ", " + status + "]";
    }
}
