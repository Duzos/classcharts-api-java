package api.duzo.homework;

import com.google.gson.JsonObject;

public class Homework {
    private int id;
    private String lesson,subject,teacher,title,description;
    private HomeworkStatus status;

    public Homework(int id, HomeworkStatus status) {
        this.id = id;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public HomeworkStatus getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public String getLesson() {
        return lesson;
    }

    public String getSubject() {
        return subject;
    }

    public String getTeacher() {
        return teacher;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Homework{" +
                "id=" + id +
                ", lesson='" + lesson + '\'' +
                ", subject='" + subject + '\'' +
                ", teacher='" + teacher + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    public static Homework fromJson(JsonObject data) {
        if (!data.has("id")) return null;
        if (!data.has("status")) return null;

        HomeworkStatus status = HomeworkStatus.fromJson(data.get("status").getAsJsonObject());

        Homework homework = new Homework(data.get("id").getAsInt(), status);

        if (data.has("lesson") && !data.get("lesson").isJsonNull()) homework.lesson = data.get("lesson").getAsString();
        if (data.has("subject") && !data.get("subject").isJsonNull()) homework.subject = data.get("subject").getAsString();
        if (data.has("teacher") && !data.get("teacher").isJsonNull()) homework.teacher = data.get("teacher").getAsString();
        if (data.has("title") && !data.get("title").isJsonNull()) homework.title = data.get("title").getAsString();
        if (data.has("description") && !data.get("description").isJsonNull()) homework.description = data.get("description").getAsString();

        return homework;
    }
}
