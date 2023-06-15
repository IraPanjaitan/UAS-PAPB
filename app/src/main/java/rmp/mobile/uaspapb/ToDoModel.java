package rmp.mobile.uaspapb;

public class ToDoModel {
    private String activity;
    private String time;

    public ToDoModel(String activity, String time){
        this.activity = activity;
        this.time = time;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
