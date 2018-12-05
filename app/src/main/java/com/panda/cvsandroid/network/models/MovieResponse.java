package com.panda.cvsandroid.network.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Andorid-win on 7/18/2018.
 */

public class MovieResponse extends CService_DBase
{

    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("results")
    @Expose
    private List<Moviex> Moviexs = null;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<Moviex> getMoviexs()
    {
        return Moviexs;
    }


    public void setMoviexs(List<Moviex> results) {
        this.Moviexs = results;
    }

    @Override
    public boolean Is_Data_Good()
    {
        return Moviexs!=null;
    }
}
