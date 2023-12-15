package com.voice9.core.page;

import java.util.List;
import java.util.Map;

/**
 * Create by caoliang on 2020/10/28
 */
public class Page<T> {
    /**
     * 每页显示数
     */
    private Long pageSize = 20L;

    /**
     * 当前第几页
     */
    private Long pageNum = 0L;
    /**
     * 总的记录数
     */
    private Long total = 0L;
    /**
     * 总页数
     */
    private Long totleSize = 0L;


    /**
     * 分页参数
     */
    private String orderField;

    private String orderDirection;
    /**
     * 单页数据
     */
    private List<T> list;

    /**
     * @param pageSize 页面大小
     * @param offset   起始页
     * @param total    总条数
     * @param list     单页数据
     */
    public Page(Long pageSize, Long offset, Long total, List<T> list) {
        this.pageSize = pageSize;
        this.pageNum = offset / pageSize + 1;
        this.total = total;
        this.totleSize = totalPage(total, pageSize);
        this.list = list;
    }

    public Page() {
    }

    public Page(Map<String, Object> params, Long count, List<T> list) {
        this.list = list;
        this.total = count;
        if (params.containsKey("limit") && params.containsKey("offset")) {
            Long limit = (Long) params.get("limit");
            Long offset = (Long) params.get("offset");
            this.pageNum = offset / limit + 1;
            this.pageSize = limit;
            this.totleSize = totalPage(count, limit);
        }
    }


    //计算总页数
    private static Long totalPage(Long count, Long limit) {
        if (count % limit == 0) {
            return count / limit;
        } else {
            return count / limit + 1;
        }
    }

    // 计算统一认证的第几页
    public static Long solvePageNum(Long limit, Long offset) {
        return offset / limit + 1;
    }


    public Long getPageSize() {
        return pageSize;
    }

    public void setPageSize(Long pageSize) {
        this.pageSize = pageSize;
    }

    public Long getPageNum() {
        return pageNum;
    }

    public void setPageNum(Long pageNum) {
        this.pageNum = pageNum;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getTotleSize() {
        return totleSize;
    }

    public void setTotleSize(Long totleSize) {
        this.totleSize = totleSize;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
