package duzo.homework;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import duzo.point.BehaviourPoint;
import duzo.point.PointType;

import java.util.ArrayList;
import java.util.List;

public class HomeworkStatus {
    private int id;
    private boolean ticked;
    private List<String> links = new ArrayList<>();

    public HomeworkStatus(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<String> getLinks() {
        return links;
    }

    public boolean isTicked() {
        return ticked;
    }

    @Override
    public String toString() {
        return "HomeworkStatus{" +
                "id=" + id +
                ", ticked=" + ticked +
                ", links=" + links +
                '}';
    }

    public static HomeworkStatus fromJson(JsonObject data) {
        if (!data.has("id")) return null;

        HomeworkStatus status = new HomeworkStatus(data.get("id").getAsInt());

        if (data.has("ticked") && !data.get("ticked").isJsonNull()) status.ticked = data.get("ticked").getAsBoolean();
        if (data.has("validated_links") && !data.get("validated_links").isJsonNull()) {
            // @TODO doesnt appear to work
            JsonArray links = data.get("validated_links").getAsJsonArray();

            for (JsonElement element : links) {
                status.links.add(element.getAsJsonObject().get("link").getAsString());
            }
        }

        return status;
    }
}
