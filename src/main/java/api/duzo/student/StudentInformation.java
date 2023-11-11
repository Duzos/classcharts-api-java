package api.duzo.student;

import com.google.gson.JsonObject;

public class StudentInformation {
    private int id,announcementsCount,messagesCount;
    private String firstName,lastName,fullName,avatarUrl,pusherChannelName;
    private boolean displayBehaviour,displayParentBehaviour,displayHomework,
            displayRewards,displayDetentions,displayReportCards,
            displayClasses,displayAnnouncements,displayReports,
            displayAttendance,displayAttendancePercentage,displayMentalHealth,
            displayMentalHealthNoTracker,displayTimetable,isDisabled,
            displayTwoWayCommunications, displayAbsences,displayEventBadges,
            displayAvatars,displayConcernSubmission,displayCustomFields,
            canAddTimetableNotes,hasBirthday,hasNewSurvey,
            displayActivity;

    public StudentInformation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAnnouncementsCount() {
        return announcementsCount;
    }

    public int getMessagesCount() {
        return messagesCount;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPusherChannelName() {
        return pusherChannelName;
    }

    public boolean isCanAddTimetableNotes() {
        return canAddTimetableNotes;
    }

    public boolean isDisabled() {
        return isDisabled;
    }

    public boolean isDisplayAbsences() {
        return displayAbsences;
    }

    public boolean isDisplayActivity() {
        return displayActivity;
    }

    public boolean isDisplayAnnouncements() {
        return displayAnnouncements;
    }

    public boolean isDisplayAttendance() {
        return displayAttendance;
    }

    public boolean isDisplayAttendancePercentage() {
        return displayAttendancePercentage;
    }

    public boolean isDisplayAvatars() {
        return displayAvatars;
    }

    public boolean isDisplayBehaviour() {
        return displayBehaviour;
    }

    public boolean isDisplayClasses() {
        return displayClasses;
    }

    public boolean isDisplayConcernSubmission() {
        return displayConcernSubmission;
    }

    public boolean isDisplayCustomFields() {
        return displayCustomFields;
    }

    public boolean isDisplayDetentions() {
        return displayDetentions;
    }

    public boolean isDisplayEventBadges() {
        return displayEventBadges;
    }

    public boolean isDisplayHomework() {
        return displayHomework;
    }

    public boolean isDisplayMentalHealth() {
        return displayMentalHealth;
    }

    public boolean isDisplayMentalHealthNoTracker() {
        return displayMentalHealthNoTracker;
    }

    public boolean isDisplayParentBehaviour() {
        return displayParentBehaviour;
    }

    public boolean isDisplayReportCards() {
        return displayReportCards;
    }

    public boolean isDisplayReports() {
        return displayReports;
    }

    public boolean isDisplayRewards() {
        return displayRewards;
    }

    public boolean isDisplayTimetable() {
        return displayTimetable;
    }

    public boolean isDisplayTwoWayCommunications() {
        return displayTwoWayCommunications;
    }

    public boolean hasBirthday() {
        return hasBirthday;
    }

    public boolean hasNewSurvey() {
        return hasNewSurvey;
    }

    @Override
    public String toString() {
        return "StudentInformation{" +
                "id=" + id +
                ", announcementsCount=" + announcementsCount +
                ", messagesCount=" + messagesCount +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fullName='" + fullName + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", pusherChannelName='" + pusherChannelName + '\'' +
                ", displayBehaviour=" + displayBehaviour +
                ", displayParentBehaviour=" + displayParentBehaviour +
                ", displayHomework=" + displayHomework +
                ", displayRewards=" + displayRewards +
                ", displayDetentions=" + displayDetentions +
                ", displayReportCards=" + displayReportCards +
                ", displayClasses=" + displayClasses +
                ", displayAnnouncements=" + displayAnnouncements +
                ", displayReports=" + displayReports +
                ", displayAttendance=" + displayAttendance +
                ", displayAttendancePercentage=" + displayAttendancePercentage +
                ", displayMentalHealth=" + displayMentalHealth +
                ", displayMentalHealthNoTracker=" + displayMentalHealthNoTracker +
                ", displayTimetable=" + displayTimetable +
                ", isDisabled=" + isDisabled +
                ", displayTwoWayCommunications=" + displayTwoWayCommunications +
                ", displayAbsences=" + displayAbsences +
                ", displayEventBadges=" + displayEventBadges +
                ", displayAvatars=" + displayAvatars +
                ", displayConcernSubmission=" + displayConcernSubmission +
                ", displayCustomFields=" + displayCustomFields +
                ", canAddTimetableNotes=" + canAddTimetableNotes +
                ", hasBirthday=" + hasBirthday +
                ", hasNewSurvey=" + hasNewSurvey +
                ", displayActivity=" + displayActivity +
                '}';
    }

    public static StudentInformation fromJson(JsonObject data) {
        if (!data.has("id")) return null;

        StudentInformation info = new StudentInformation(data.get("id").getAsInt());

        // After typing this all out manually im sure theres probably a better way of doing this
        if (data.has("name") && !data.get("name").isJsonNull()) info.fullName = data.get("name").getAsString();
        if (data.has("first_name") && !data.get("first_name").isJsonNull()) info.firstName = data.get("first_name").getAsString();
        if (data.has("last_name") && !data.get("last_name").isJsonNull()) info.lastName = data.get("last_name").getAsString();
        if (data.has("avatar_url") && !data.get("avatar_url").isJsonNull()) info.avatarUrl = data.get("avatar_url").getAsString();
        if (data.has("display_behaviour") && !data.get("display_behaviour").isJsonNull()) info.displayBehaviour = data.get("display_behaviour").getAsBoolean();
        if (data.has("display_parent_behaviour") && !data.get("display_parent_behaviour").isJsonNull()) info.displayParentBehaviour = data.get("display_parent_behaviour").getAsBoolean();
        if (data.has("display_homework") && !data.get("display_homework").isJsonNull()) info.displayHomework = data.get("display_homework").getAsBoolean();
        if (data.has("display_rewards") && !data.get("display_rewards").isJsonNull()) info.displayRewards = data.get("display_rewards").getAsBoolean();
        if (data.has("display_detentions") && !data.get("display_detentions").isJsonNull()) info.displayDetentions = data.get("display_detentions").getAsBoolean();
        if (data.has("display_report_cards") && !data.get("display_report_cards").isJsonNull()) info.displayReportCards = data.get("display_report_cards").getAsBoolean();
        if (data.has("display_classes") && !data.get("display_classes").isJsonNull()) info.displayClasses = data.get("display_classes").getAsBoolean();
        if (data.has("display_announcements") && !data.get("display_announcements").isJsonNull()) info.displayAnnouncements = data.get("display_announcements").getAsBoolean();
        if (data.has("display_academic_reports") && !data.get("display_academic_reports").isJsonNull()) info.displayReports = data.get("display_academic_reports").getAsBoolean();
        if (data.has("display_attendance") && !data.get("display_attendance").isJsonNull()) info.displayAttendance = data.get("display_attendance").getAsBoolean();
        if (data.has("display_attendance_percentage") && !data.get("display_attendance_percentage").isJsonNull()) info.displayAttendancePercentage = data.get("display_attendance_percentage").getAsBoolean();
        if (data.has("display_activity") && !data.get("display_activity").isJsonNull()) info.displayActivity = data.get("display_activity").getAsBoolean();
        if (data.has("display_mental_health") && !data.get("display_mental_health").isJsonNull()) info.displayMentalHealth = data.get("display_mental_health").getAsBoolean();
        if (data.has("display_mental_health_no_tracker") && !data.get("display_mental_health_no_tracker").isJsonNull()) info.displayMentalHealthNoTracker = data.get("display_mental_health_no_tracker").getAsBoolean();
        if (data.has("display_timetable") && !data.get("display_timetable").isJsonNull()) info.displayTimetable = data.get("display_timetable").getAsBoolean();
        if (data.has("isDisabled") && !data.get("isDisabled").isJsonNull()) info.isDisabled = data.get("isDisabled").getAsBoolean();
        if (data.has("display_two_way_communications") && !data.get("display_two_way_communications").isJsonNull()) info.displayTwoWayCommunications = data.get("display_two_way_communications").getAsBoolean();
        if (data.has("display_absences") && !data.get("display_absences").isJsonNull()) info.displayAbsences = data.get("display_absences").getAsBoolean();
        if (data.has("display_event_badges") && !data.get("display_event_badges").isJsonNull()) info.displayEventBadges = data.get("display_event_badges").getAsBoolean();
        if (data.has("display_avatars") && !data.get("display_avatars").isJsonNull()) info.displayAvatars = data.get("display_avatars").getAsBoolean();
        if (data.has("display_concern_submission") && !data.get("display_concern_submission").isJsonNull()) info.displayConcernSubmission = data.get("display_concern_submission").getAsBoolean();
        if (data.has("display_custom_fields") && !data.get("display_custom_fields").isJsonNull()) info.displayCustomFields = data.get("display_custom_fields").getAsBoolean();
        if (data.has("allow_pupils_add_timetable_notes") && !data.get("allow_pupils_add_timetable_notes").isJsonNull()) info.canAddTimetableNotes = data.get("allow_pupils_add_timetable_notes").getAsBoolean();
        if (data.has("announcements_count") && !data.get("announcements_count").isJsonNull()) info.announcementsCount = data.get("announcements_count").getAsInt();
        if (data.has("messages_count") && !data.get("messages_count").isJsonNull()) info.messagesCount = data.get("messages_count").getAsInt();
        if (data.has("pusher_channel_name") && !data.get("pusher_channel_name").isJsonNull()) info.pusherChannelName = data.get("pusher_channel_name").getAsString();
        if (data.has("has_birthday") && !data.get("has_birthday").isJsonNull()) info.hasBirthday = data.get("has_birthday").getAsBoolean();
        if (data.has("has_new_survey") && !data.get("has_new_survey").isJsonNull()) info.hasNewSurvey = data.get("has_new_survey").getAsBoolean();


        return info;
    }
}
