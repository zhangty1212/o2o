/**  
*    文件名:  Area.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月9日  
*/
package cn.zty.o2o.entity;

import java.util.Date;

/**
 *  类描述:
 * 
 * @author 张涛一 ,2547260515@qq.com
 * @since 2019年5月9日 下午6:29:21
 */

public class Area {

	private Integer areaId; // ID
	private String areaName; // 名称
	private Integer priority; // 权重
	private Date createTime; // 创建时间
	private Date lastEditTime; // 最后修改时间
	
	/**
	 * 
	 */
	public Area() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param areaId
	 * @param areaName
	 * @param priority
	 * @param createTime
	 * @param lastEditTime
	 */
	public Area(Integer areaId, String areaName, Integer priority, Date createTime, Date lastEditTime) {
		super();
		this.areaId = areaId;
		this.areaName = areaName;
		this.priority = priority;
		this.createTime = createTime;
		this.lastEditTime = lastEditTime;
	}

	
	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastEditTime() {
		return lastEditTime;
	}

	public void setLastEditTime(Date lastEditTime) {
		this.lastEditTime = lastEditTime;
	}

	@Override
	public String toString() {
		return "Area [areaId=" + areaId + ", areaName=" + areaName + ", priority=" + priority + ", createTime="
				+ createTime + ", lastEditTime=" + lastEditTime + "]";
	}

}
