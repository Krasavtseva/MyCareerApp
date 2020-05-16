
package com.example.tesster.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnswerModel {

    @SerializedName("text")
    @Expose
    public String text;
    @SerializedName("points")
    @Expose
    public Integer points;

}
