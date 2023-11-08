package duzo.announcement;

import com.google.gson.JsonObject;
import duzo.point.BehaviourPoint;
import duzo.point.PointType;

public class Announcement {
    private int id;
    public String title,description,schoolName,schoolLogo,teacher;

    public Announcement(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public String getSchoolLogo() {
        return schoolLogo;
    }

    public String getSchoolName() {
        return schoolName;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", schoolLogo='" + schoolLogo + '\'' +
                ", teacher='" + teacher + '\'' +
                '}';
    }

    public static Announcement fromJson(JsonObject data) {
        if (!data.has("id")) return null;

        Announcement announcement = new Announcement(data.get("id").getAsInt());

        if (data.has("title") && !data.get("title").isJsonNull()) announcement.title = data.get("title").getAsString();
        if (data.has("description") && !data.get("description").isJsonNull()) announcement.description = data.get("description").getAsString();
        if (data.has("school_name") && !data.get("school_name").isJsonNull()) announcement.schoolName = data.get("school_name").getAsString();
        if (data.has("school_logo") && !data.get("school_logo").isJsonNull()) announcement.schoolLogo = data.get("school_logo").getAsString();
        if (data.has("teacher_name") && !data.get("teacher_name").isJsonNull()) announcement.teacher = data.get("teacher_name").getAsString();

        return announcement;
    }
}
