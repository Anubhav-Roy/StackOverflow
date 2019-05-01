package com.roy.anubhav.stackoverflow.retrofit;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//Serialized JSON object for a question received
public class Question {

    @SerializedName("tags")
    private List<String> tags;

    @SerializedName("owner")
    private Owner owner;

    @SerializedName("is_answered")
    private boolean is_answered;

    @SerializedName("view_count")
    private long view_count;

    @SerializedName("answer_count")
    private long answer_count;

    @SerializedName("score")
    private long score;

    @SerializedName("last_activity_date")
    private long last_activity_date;

    @SerializedName("creation_date")
    private long creation_date;

    @SerializedName("question_id")
    private long question_id;

    @SerializedName("link")
    private String link;

    @SerializedName("title")
    private String title;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public boolean isIs_answered() {
        return is_answered;
    }

    public void setIs_answered(boolean is_answered) {
        this.is_answered = is_answered;
    }

    public long getView_count() {
        return view_count;
    }

    public void setView_count(long view_count) {
        this.view_count = view_count;
    }

    public long getAnswer_count() {
        return answer_count;
    }

    public void setAnswer_count(long answer_count) {
        this.answer_count = answer_count;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public long getLast_activity_date() {
        return last_activity_date;
    }

    public void setLast_activity_date(long last_activity_date) {
        this.last_activity_date = last_activity_date;
    }

    public long getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(long creation_date) {
        this.creation_date = creation_date;
    }

    public long getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(long question_id) {
        this.question_id = question_id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public class Owner{
        @SerializedName("reputation")
        private long reputation;

        @SerializedName("user_id")
        private long user_id;

        @SerializedName("user_type")
        private String user_type;

        @SerializedName("accept_rate")
        private long accept_rate;

        @SerializedName("profile_image")
        private String profile_image;

        @SerializedName("display_name")
        private String display_name;

        @SerializedName("link")
        private String link;

        public long getReputation() {

            return reputation;
        }

        public void setReputation(long reputation) {
            this.reputation = reputation;
        }

        public long getUser_id() {
            return user_id;
        }

        public void setUser_id(long user_id) {
            this.user_id = user_id;
        }

        public String getUser_type() {
            return user_type;
        }

        public void setUser_type(String user_type) {
            this.user_type = user_type;
        }

        public long getAccept_rate() {
            return accept_rate;
        }

        public void setAccept_rate(long accept_rate) {
            this.accept_rate = accept_rate;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public String getDisplay_name() {
            return display_name;
        }

        public void setDisplay_name(String display_name) {
            this.display_name = display_name;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }
    }
}
