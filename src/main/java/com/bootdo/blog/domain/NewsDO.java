package com.bootdo.blog.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * 新闻内容
 * 
 * @author jet
 * @email jet.zhang@blockshine.com
 * @date 2018-02-4 
 */
public class NewsDO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long cid;
	//标题
	private String title;
	//详细URL
	private String url;
	private String coverUrl;
	//内容
	private String content;

	//创建人id
	private Date publishDate;
	//是否置顶
	private int isTop;
	////置顶时间
	private Date topTime;


	
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}


    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

	public int getIsTop() {
		return isTop;
	}

	public void setIsTop(int isTop) {
		this.isTop = isTop;
	}

	public Date getTopTime() {
		return topTime;
	}

	public void setTopTime(Date topTime) {
		this.topTime = topTime;
	}
}
