/**  
*    文件名:  ShopExecution.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月13日  
*/  
package cn.zty.o2o.dto;
/**
*    类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月13日 下午7:06:34
*/

import java.util.List;

import cn.zty.o2o.entity.Shop;
import cn.zty.o2o.enums.ShopStateEnum;

public class ShopExecution {
	
	// 结果状态
	private int state;
	
	// 状态标识
	private String stateInfo;
	
	// 店铺数量
	private int count;
	
	// 操作的shop （增删改店铺的时候用到）
	private Shop shop;
	
	//shop列表（查询店铺列表的时候使用）
	private List<Shop> shopList;

	
	public ShopExecution() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// 店铺操作失败的时候，使用的构造器
	public ShopExecution(ShopStateEnum stateEnum) {
	 this.state=stateEnum.getState();
	 this.stateInfo=stateEnum.getStateInfo();
	}
 
	// 店铺操作成功的时候，使用的构造器
	public ShopExecution(ShopStateEnum stateEnum,Shop shop) {
	 this.state=stateEnum.getState();
	 this.stateInfo=stateEnum.getStateInfo();
	 this.shop=shop;
	}
	
	// 店铺操作成功的时候，使用的构造器
	public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList) {
	 this.state=stateEnum.getState();
	 this.stateInfo=stateEnum.getStateInfo();
	 this.shopList=shopList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}
	
}
