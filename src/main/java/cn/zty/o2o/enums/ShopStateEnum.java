/**  
*    文件名:  ShopStateEnum.java
*    文件描述:  
*  @author zhanggtaoyi 
*  @date 2019年5月13日  
*/  
package cn.zty.o2o.enums;
/**
*  类描述: 
*  @author 张涛一 ,2547260515@qq.com
*  @since 2019年5月13日 下午7:13:28
*/

public enum ShopStateEnum {
	
	CHECK(0, "审核中"), OFFLINE(-1, "非法店铺"), SUCCESS(1, "操作成功"), PASS(2, "通过认证"),
	INNER_ERROR(-1001, "内部系统错误"),NULL_SHOPID(-1002,"ShopId为空"),NULL_SHOP(-1003,"Shop信息为空");
	
	private int state;
	private String stateInfo;
	
	private ShopStateEnum(int state,String stateInfo) {
		this.state=state;
		this.stateInfo=stateInfo;
		
	}
	
	/**
	*  方法描述: 
	*  @param state
	*  @return
	*  @since 2019年5月13日 
	*/
	public static ShopStateEnum stateOf(int state) {
		for(ShopStateEnum stateEnum:values()) {
			if(stateEnum.getState()==state) {
				return stateEnum;
			}			
		}	
		return null;
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
	
}
