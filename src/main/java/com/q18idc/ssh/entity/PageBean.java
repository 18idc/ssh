package com.q18idc.ssh.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

public class PageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 要返回的某一页的记录列表
	 */
	@Getter
	@Setter
	private List list;

	/**
	 * 总记录数
	 */
	@Getter
	@Setter
	private int allRow;

	/**
	 * 总页数
	 */
	@Getter
	@Setter
	private int totalPage;

	/**
	 * 当前页
	 */
	@Getter
	@Setter
	private int currentPage;
	/**
	 * 每页记录数
	 */
	@Getter
	@Setter
	private int pageSize;

	/**
	 * 是否为第一页
	 */
	@SuppressWarnings("unused")
	private boolean isFirstPage;

	/**
	 * 是否为最后一页
	 */
	@SuppressWarnings("unused")
	private boolean isLastPage;

	/**
	 * 是否有前一页
	 */
	private boolean hasPreviousPage;

	/**
	 * 是否有下一页
	 */
	private boolean hasNextPage;

	
    /**
     * 初始化分页信息
     */
    public void init() {
        this.isFirstPage = isFirstPage();
        this.isLastPage = isLastPage();
        this.hasPreviousPage = isHasPreviousPage();
        this.hasNextPage = isHasNextPage();
    }

    /**
     * 以下判断页的信息,只需getter方法(is方法)即可
     * 
     * @return
     */
    public boolean isFirstPage() {
        return currentPage == 1; // 如是当前页是第1页
    }

    public boolean isLastPage() {
        return currentPage == totalPage; // 如果当前页是最后一页
    }

    public boolean isHasPreviousPage() {
        return currentPage != 1;// 只要当前页不是第1页
    }

    public boolean isHasNextPage() {
        return currentPage != totalPage; // 只要当前页不是最后1页
    }

    /**
     * 计算总页数,静态方法,供外部直接通过类名调用
     * 
     * @param pageSize 每页记录数
     * @param allRow 总记录数
    * @return 总页数
    */
   public static int countTotalPage(final int pageSize, final int allRow) {
       int totalPage = allRow % pageSize == 0 ? allRow / pageSize : allRow / pageSize + 1;
       return totalPage;
   }

   /**
    * 计算当前页开始记录
    * 
    * @param pageSize 每页记录数
    * @param currentPage 当前第几页
    * @return 当前页开始记录号
    */
   public static int countOffset(final int pageSize, final int currentPage) {
       final int offset = pageSize * (currentPage - 1);
       return offset;
   }

   /**
    * 计算当前页,若为0或者请求的URL中没有"?page=",则用1代替
    * 
    * @paramPage 传入的参数(可能为空,即0,则返回1)
    * @return 当前页
    */
   public static int countCurrentPage(int page) {
       final int curPage = (page == 0 ? 1 : page);
       return curPage;
   }

    @Override
    public String toString() {
        return "PageBean{" +
                "list=" + list +
                ", allRow=" + allRow +
                ", totalPage=" + totalPage +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                ", isFirstPage=" + isFirstPage +
                ", isLastPage=" + isLastPage +
                ", hasPreviousPage=" + hasPreviousPage +
                ", hasNextPage=" + hasNextPage +
                '}';
    }
}
