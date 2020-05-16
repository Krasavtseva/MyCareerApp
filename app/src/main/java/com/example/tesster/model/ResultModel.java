
package com.example.tesster.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResultModel {

    @SerializedName("min")
    @Expose
    public Integer min;
    @SerializedName("max")
    @Expose
    public Integer max;
    @SerializedName("text")
    @Expose
    public String text;

}
