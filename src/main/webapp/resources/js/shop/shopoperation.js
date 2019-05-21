/*
* js 作用：
* 
* 1. 从后台获取 “商铺分类”、“所属区域” 下拉列表信息，并将信息填充到前端控件；
* 2. 使用 Ajix 将前端 html 页面，提交到后台；
*/

$(function(){
	
	var shopId = getQueryString('shopId');
	var isEdit = shopId ? true : false;

	// 用于店铺注册时候的店铺类别以及区域列表的初始化的URL
	var initUrl = '/o2o/shopadmin/getshopinitinfo';
	// 注册店铺的URL
	var registerShopUrl = '/o2o/shopadmin/registershop';
	
	// 根据id查找店铺信息的URL
	var shopInfoUrl = '/o2o/shopadmin/getshopbyid?shopId=' + shopId;
	
	// 修改店铺信息的URL
	var editShopUrl = '/o2o/shopadmin/modifyshop';
	
	// 使用 alert() 进行调试，如果alert信息出现，表示 js 文件被 shopoperation.html 文件成功加载
	// alert(initUrl);
	
	// 加载js文件后，调用 getShopInitInfo() 方法，获取后台数据
	if (!isEdit) {
		getShopInitInfo();
	} else {
		getShopInfo(shopId);
	}
	
	// 通过店铺Id获取店铺信息
	function getShopInfo(shopId) {
		$.getJSON(shopInfoUrl, function(data) {
			if (data.success) {
				// 若访问成功，则依据后台传递过来的店铺信息为表单元素赋值
				var shop = data.shop;
				$('#shop-name').val(shop.shopName);
				$('#shop-addr').val(shop.shopAddr);
				$('#shop-phone').val(shop.phone);
				$('#shop-desc').val(shop.shopDesc);
				// 给店铺类别选定原先的店铺类别值
				var shopCategory = '<option data-id="'
						+ shop.shopCategory.shopCategoryId + '" selected>'
						+ shop.shopCategory.shopCategoryName + '</option>';
				var tempAreaHtml = '';
				// 初始化区域列表
				data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
							+ item.areaName + '</option>';
				});
				$('#shop-category').html(shopCategory);
				// 不允许选择店铺类别
				$('#shop-category').attr('disabled', 'disabled');
				$('#area').html(tempAreaHtml);
				// 给店铺选定原先的所属的区域
				$("#area option[data-id='" + shop.area.areaId + "']").attr(
						"selected", "selected");
			}
		});
	}
	
	// 取得所有二级店铺类别以及区域信息，并分别赋值进类别列表以及区域列表
	function getShopInitInfo(){	
		// data: 后台返回的数据
		$.getJSON(initUrl,function(data){			
			if(data.success){
	           	var tempHtml='';
	           	var tempAreaHtml='';
	           	// 遍历 shopCategoryList 数据
	           	data.shopCategoryList.map(function(item,index){	           		
	           		tempHtml += '<option data-id="' + item.shopCategoryId + '">' 
	           		+ item.shopCategoryName + '</option>';	           		
	           	});
	         	// 遍历 areaList 数据
	           	data.areaList.map(function(item, index) {
					tempAreaHtml += '<option data-id="' + item.areaId + '">'
					+ item.areaName + '</option>';
				});
	           	// 将获取到的后台数据，填充到前端
	           	$('#shop-category').html(tempHtml);
				$('#area').html(tempAreaHtml);        	
			}
		});
	}
       // 提交按钮的事件响应，对店铺注册响应
        $('#submit').click(function(){
        	
        	// 创建shop对象
        	var shop={};   	
        	if(isEdit){
        		shop.shopId=shopId;
        	}       	
        	// 获取表单里的数据并填充进对应的店铺属性中
    		shop.shopName = $('#shop-name').val();
    		shop.shopAddr = $('#shop-addr').val();
    		shop.phone = $('#shop-phone').val();
    		shop.shopDesc = $('#shop-desc').val();
    		// 选择选定好的店铺类别
    		shop.shopCategory = {
    			shopCategoryId : $('#shop-category').find('option').not(function() {
    				return !this.selected;
    			}).data('id')
    		};
    		// 选择选定好的区域信息
    		shop.area = {
    			areaId : $('#area').find('option').not(function() {
    				return !this.selected;
    			}).data('id')
    		};
    		// 获取上传的图片文件流
    		var shopImg = $('#shop-img')[0].files[0];
    		// 生成表单对象，用于接收参数并传递给后台
    		var formData = new FormData();
    		// 添加图片流进表单对象里
    		formData.append('shopImg', shopImg);
    		// 将shop json对象转成字符流保存至表单对象key为shopStr的的键值对里
    		formData.append('shopStr', JSON.stringify(shop));
    		// 获取表单里输入的验证码
    		var verifyCodeActual = $('#j_captcha').val();
    		if (!verifyCodeActual) {
    			$.toast('请输入验证码！');
    			return;
    		}
    		formData.append('verifyCodeActual', verifyCodeActual);
        
    	// 将数据提交至后台处理相关操作
		$.ajax({
			url : (isEdit ? editShopUrl:registerShopUrl),
			type : 'POST',
			data : formData,
			contentType : false,
			processData : false,
			cache : false,
			success : function(data) {
				if (data.success) {
					$.toast('提交成功！');
				} else {
					$.toast('提交失败！' + data.errMsg);
				}
				// 点击验证码图片的时候，注册码会改变
				$('#captcha_img').click();
			}
		});	
		
     });	
})
