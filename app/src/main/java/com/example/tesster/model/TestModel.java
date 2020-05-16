
package com.example.tesster.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TestModel {

    @SerializedName("questions")
    @Expose
    public List<QuestionModel> questions = null;
    @SerializedName("results")
    @Expose
    public List<ResultModel> results = null;

}
