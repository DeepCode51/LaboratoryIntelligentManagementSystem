package top.yjzloveyzh.common.pojo;

import java.util.List;
import java.util.Map;

public class Pagination<T> {
    private List<T> results;
    private List<Integer> pagesList;
    private int totalCount; // 总记录数
    private int totalPage; // 总页数
    private int currentPage;
    private int orderBy;
    private Map<String, Object> extra;

    String searchKeyWord;

    public Pagination() {
        super();
    }

    public List<T> getResults() {
        return results;
    }

    public List<Integer> getPagesList() {
        return pagesList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public String getSearchKeyWord() {
        return searchKeyWord;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void setPagesList(List<Integer> pagesList) {
        this.pagesList = pagesList;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public void setSearchKeyWord(String searchKeyWord) {
        this.searchKeyWord = searchKeyWord;
    }

    public int getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(int orderBy) {
        this.orderBy = orderBy;
    }

    public Map<String, Object> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, Object> extra) {
        this.extra = extra;
    }
}
