package com.whatsappbuisness.wsbuissness.CombinePackadge.ApiService.Media;
/*      
 Author=Supreet Singh
 Date= 12/02/21 2:46 PM
*/

import com.google.gson.Gson;

import java.util.List;

public class MediaDao {

    public List<MediaInnerDao> getMedia() {
        return media;
    }

    public void setMedia(List<MediaInnerDao> media) {
        this.media = media;
    }

    private List<MediaInnerDao> media;

    @Override
    public String toString() {
        return "MediaDao{" +
                "media=" + media +
                '}';
    }

    public MediaDao toObject(String json)
    {
        return new Gson().fromJson(json,MediaDao.class);
    }
}
