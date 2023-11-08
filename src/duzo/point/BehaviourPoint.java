package duzo.point;

import com.google.gson.JsonObject;

import java.awt.*;

public class BehaviourPoint {
    private int id, score;
    private String reason, lessonName, teacherName;
    private PointType type;

    private BehaviourPoint(int id) {
        this.id = id;
    }

    int getId() {
        return this.id;
    }

    public int getScore() {
        return score;
    }

    public PointType getType() {
        return type;
    }

    public String getLessonName() {
        return lessonName;
    }

    public String getReason() {
        return reason;
    }

    public String getTeacherName() {
        return teacherName;
    }

    @Override
    public String toString() {
        return "BehaviourPoint{" +
                "id=" + id +
                ", score=" + score +
                ", reason='" + reason + '\'' +
                ", lessonName='" + lessonName + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", type=" + type +
                '}';
    }

    public static BehaviourPoint fromJson(JsonObject data) {
        if (!data.has("id")) return null;

        BehaviourPoint point = new BehaviourPoint(data.get("id").getAsInt());

        if (data.has("polarity") && !data.get("polarity").isJsonNull()) point.type = PointType.fromPolarity(data.get("polarity").getAsString());
        if (data.has("reason") && !data.get("reason").isJsonNull()) point.reason = data.get("reason").getAsString();
        if (data.has("score") && !data.get("score").isJsonNull()) point.score = data.get("score").getAsInt();
        if (data.has("lesson_name") && !data.get("lesson_name").isJsonNull()) point.lessonName = data.get("lesson_name").getAsString();
        if (data.has("teacher_name") && !data.get("teacher_name").isJsonNull()) point.teacherName = data.get("teacher_name").getAsString();

        return point;
    }
}
