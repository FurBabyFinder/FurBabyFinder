package com.capstone.Pojos;

import com.capstone.models.PetImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by melodytempleton on 7/8/17.
 */
public class ImageList {
    private List<PetImage> images;

    public ImageList(){
        this.images = new ArrayList<PetImage>();
    }

    public ImageList(List<PetImage> images){
        this.images = images;
    }

    public List<PetImage> getImages() {
        return images;
    }

   public void add(PetImage petImage){
        this.images.add(petImage);
   }
}
