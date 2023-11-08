package duzo.timetable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DayTimetable {
    private List<TimetableLesson> lessons;

    public DayTimetable(List<TimetableLesson> lessons) {
        this.lessons = lessons;
    }

    @Override
    public String toString() {
        return "DayTimetable{" +
                "lessons=" + lessons +
                '}';
    }

    public static DayTimetable fromJson(JsonArray data) {
        List<TimetableLesson> list = new ArrayList<>();

        for (JsonElement element : data) {
            list.add(TimetableLesson.fromJson(element.getAsJsonObject()));
        }

        return new DayTimetable(list);
    }
}
