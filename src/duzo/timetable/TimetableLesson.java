package duzo.timetable;

import com.google.gson.JsonObject;
import duzo.homework.Homework;
import duzo.homework.HomeworkStatus;

public class TimetableLesson {
    private int id, periodNumber,key;
    private String teacher,name,subject,periodName,room;
    private boolean isAlternative;

    public TimetableLesson(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public int getKey() {
        return key;
    }

    public int getPeriodNumber() {
        return periodNumber;
    }

    public String getName() {
        return name;
    }

    public String getPeriodName() {
        return periodName;
    }

    public String getRoom() {
        return room;
    }

    public boolean isAlternative() {
        return isAlternative;
    }

    @Override
    public String toString() {
        return "TimetableLesson{" +
                "id=" + id +
                ", periodNumber=" + periodNumber +
                ", key=" + key +
                ", teacher='" + teacher + '\'' +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", periodName='" + periodName + '\'' +
                ", room='" + room + '\'' +
                ", isAlternative=" + isAlternative +
                '}';
    }

    public static TimetableLesson fromJson(JsonObject data) {
        if (!data.has("lesson_id")) return null;

        TimetableLesson lesson = new TimetableLesson(data.get("lesson_id").getAsInt());

        if (data.has("teacher_name") && !data.get("teacher_name").isJsonNull()) lesson.teacher = data.get("teacher_name").getAsString();
        if (data.has("lesson_name") && !data.get("lesson_name").isJsonNull()) lesson.name = data.get("lesson_name").getAsString();
        if (data.has("subject_name") && !data.get("subject_name").isJsonNull()) lesson.subject = data.get("subject_name").getAsString();
        if (data.has("is_alternative_lesson") && !data.get("is_alternative_lesson").isJsonNull()) lesson.isAlternative = data.get("is_alternative_lesson").getAsBoolean();
        if (data.has("period_name") && !data.get("period_name").isJsonNull()) lesson.periodName = data.get("period_name").getAsString();
        if (data.has("period_number") && !data.get("period_number").isJsonNull()) lesson.periodNumber = data.get("period_number").getAsInt();
        if (data.has("room_name") && !data.get("room_name").isJsonNull()) lesson.room = data.get("room_name").getAsString();
        if (data.has("key") && !data.get("key").isJsonNull()) lesson.key = data.get("key").getAsInt();

        return lesson;
    }
}
