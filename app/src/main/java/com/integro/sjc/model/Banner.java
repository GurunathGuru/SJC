package com.integro.sjc.model;

import java.io.Serializable;

public class Banner implements Serializable{

        private String image;

        private String weblink;

        private String id;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getWeblink() {
            return weblink;
        }

        public void setWeblink(String weblink) {
            this.weblink = weblink;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
}
