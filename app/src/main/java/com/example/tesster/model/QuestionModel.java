
package com.example.tesster.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuestionModel {

    @SerializedName("answers")
    @Expose
    public List<AnswerModel> answers = null;
    @SerializedName("text")
    @Expose
    public String text;

}
