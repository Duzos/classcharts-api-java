package api.duzo.reward;

import com.google.gson.JsonObject;

public class Reward {
    private int id,price,stock,purchasedCount,priceBalanceDifference;
    private String name, description, photoLink,unablePurchaseReason;
    private boolean stockControl, canPurchase, oncePerPupil, purchased;

    public Reward(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPriceBalanceDifference() {
        return priceBalanceDifference;
    }

    public int getPurchasedCount() {
        return purchasedCount;
    }

    public int getStock() {
        return stock;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public String getUnablePurchaseReason() {
        return unablePurchaseReason;
    }

    public boolean canPurchase() {
        return canPurchase;
    }

    public boolean isOncePerPupil() {
        return oncePerPupil;
    }

    public boolean isPurchased() {
        return purchased;
    }

    public boolean isStockControl() {
        return stockControl;
    }

    @Override
    public String toString() {
        return "Reward{" +
                "id=" + id +
                ", price=" + price +
                ", stock=" + stock +
                ", purchasedCount=" + purchasedCount +
                ", priceBalanceDifference=" + priceBalanceDifference +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", photoLink='" + photoLink + '\'' +
                ", unablePurchaseReason='" + unablePurchaseReason + '\'' +
                ", stockControl=" + stockControl +
                ", canPurchase=" + canPurchase +
                ", oncePerPupil=" + oncePerPupil +
                ", purchased=" + purchased +
                '}';
    }

    public static Reward fromJson(JsonObject data) {
        if (!data.has("id")) return null;

        Reward reward = new Reward(data.get("id").getAsInt());

        if (data.has("name") && !data.get("name").isJsonNull()) reward.name = data.get("name").getAsString();
        if (data.has("description") && !data.get("description").isJsonNull()) reward.description = data.get("description").getAsString();
        if (data.has("photo") && !data.get("photo").isJsonNull()) reward.photoLink = data.get("photo").getAsString();
        if (data.has("price") && !data.get("price").isJsonNull()) reward.price = data.get("price").getAsInt();
        if (data.has("stock_control") && !data.get("stock_control").isJsonNull()) reward.stockControl = data.get("stock_control").getAsBoolean();
        if (data.has("stock") && !data.get("stock").isJsonNull()) reward.stock = data.get("stock").getAsInt();
        if (data.has("can_purchase") && !data.get("can_purchase").isJsonNull()) reward.canPurchase = data.get("can_purchase").getAsBoolean();
        if (data.has("unable_to_purchase_reason") && !data.get("unable_to_purchase_reason").isJsonNull()) reward.unablePurchaseReason = data.get("unable_to_purchase_reason").getAsString();
        if (data.has("once_per_pupil") && !data.get("once_per_pupil").isJsonNull()) reward.oncePerPupil = data.get("once_per_pupil").getAsBoolean();
        if (data.has("purchased") && !data.get("purchased").isJsonNull()) reward.purchased = data.get("purchased").getAsBoolean();
        if (data.has("purchased_count") && !data.get("purchased_count").isJsonNull()) reward.purchasedCount = data.get("purchased_count").getAsInt();
        if (data.has("price_balance_difference") && !data.get("price_balance_difference").isJsonNull()) reward.priceBalanceDifference = data.get("price_balance_difference").getAsInt();

        return reward;
    }
}
