package erankan.ex4postpc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class AlbumData {

    @SerializedName("data")
    @Expose
    public Data data;

    public static class Image {

        @SerializedName("id")
        @Expose
        public String id;

        @SerializedName("title")
        @Expose
        public Object title;

        @SerializedName("description")
        @Expose
        public Object description;

        @SerializedName("datetime")
        @Expose
        public Integer datetime;

        @SerializedName("type")
        @Expose
        public String type;

        @SerializedName("height")
        @Expose
        public Integer height;

        @SerializedName("size")
        @Expose
        public Integer size;

        @SerializedName("link")
        @Expose
        public String link;

    }

    public static class Data {

        @SerializedName("id")
        @Expose
        public String id;

        @SerializedName("title")
        @Expose
        public String title;

        @SerializedName("description")
        @Expose
        public Object description;

        @SerializedName("datetime")
        @Expose
        public Integer datetime;

        @SerializedName("layout")
        @Expose
        public String layout;

        @SerializedName("link")
        @Expose
        public String link;

        @SerializedName("images")
        @Expose
        public List<Image> images = null;

    }

}