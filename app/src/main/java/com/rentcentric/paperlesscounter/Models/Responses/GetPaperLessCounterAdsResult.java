
package com.rentcentric.paperlesscounter.Models.Responses;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetPaperLessCounterAdsResult {

    @SerializedName("PaperLessCounterAdsList")
    @Expose
    private List<PaperLessCounterAdsList> paperLessCounterAdsList = null;

    public List<PaperLessCounterAdsList> getPaperLessCounterAdsList() {
        return paperLessCounterAdsList;
    }

    public void setPaperLessCounterAdsList(List<PaperLessCounterAdsList> paperLessCounterAdsList) {
        this.paperLessCounterAdsList = paperLessCounterAdsList;
    }

}
