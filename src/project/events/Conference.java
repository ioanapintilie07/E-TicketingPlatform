package project.events;

import project.locations.Location;

public class Conference extends Event {
    private String speaker;
    private String topic;

    public Conference(String name, String date, String description, int durationInHours, Location location, int participantsLimit, String speaker, String topic) {
        super(name, date, description, durationInHours, location, participantsLimit);
        this.speaker = speaker;
        this.topic = topic;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Override
    public String toString() {
        return super.toString() + "Speaker: " + speaker + ". Topic: " + topic + ".";
    }
}
