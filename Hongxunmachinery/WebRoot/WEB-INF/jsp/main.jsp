<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="mvc" uri="http://www.springframework.org/tags/form"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en"> 
<head> 
    <meta charset="utf-8"> 
    <meta name="viewport" content="width=device-width, initial-scale=1"> 
    <title>鸿勋机械进销存管理系统</title> 
	<link href="web/pages/css/base.css" rel="stylesheet">
	<link href="web/pages/css/platform.css" rel="stylesheet">
	<link rel="stylesheet" href="web/custom/uimaker/easyui.css">
	<script type="text/javascript" src="web/pages/js/myjs/myjs.js"></script>
</head> 
<body>
    <div class="container">
        <div id="pf-hd">
            <div class="pf-logo">
                <img src="web/pages/images/main/main_logo.png" alt="logo">
            </div>         
            <div class="pf-nav-wrap">
              <div class="pf-nav-ww">
                <ul class="pf-nav">
                  <li class="pf-nav-item base" data-menu="org-manage">
                      <a href="javascript:load_html('web/pages/htmls/baseMsg/pf_bd_tables1.html');">
                          <span class="iconfont">&#xe618;</span>
                          <span class="pf-nav-title">基础信息</span>
                      </a>
                  </li>
                  <li class="pf-nav-item plan" data-menu="main-data">
                      <a href="javascript:load_html('web/pages/htmls/planMag/pf_bd_tables2.html');">
                          <span class="iconfont">&#xe65c;</span>
                          <span class="pf-nav-title">计划管理</span>
                      </a>                  
                  </li>

                  <li class="pf-nav-item material" data-menu="supplier-mange">
                      <a href="javascript:load_html('web/pages/htmls/materialStock/pf_bd_tables3.html');">
                          <span class="iconfont">&#xe62a;</span>
                          <span class="pf-nav-title">原材料仓库</span>
                      </a>
                  </li>
                  <li class="pf-nav-item production" data-menu="supplier-dev">
                      <a href="javascript:load_html('web/pages/htmls/productionStock/pf_bd_tables4.html');">
                          <span class="iconfont">&#xe6bc;</span>
                          <span class="pf-nav-title">成品仓库</span>
                      </a>
                      
                  </li>
<!--                   <li class="pf-nav-item manger" data-menu="supplier-dev">
                      <a href="javascript:load_html('web/pages/htmls/seven/pf_bd_tables7.html');">
                          <span class="iconfont">&#xe6bb;</span>
                          <span class="pf-nav-title">成品管理</span>
                      </a>
                  </li> -->
<!--                   <li class="pf-nav-item device" data-menu="pur-source">
                      <a href="javascript:load_html('web/pages/htmls/five/pf_bd_tables5.html');">
                          <span class="iconfont">&#xe6c4;</span>
                          <span class="pf-nav-title">设备管理</span>
                      </a>
                  </li> -->
                  <li class="pf-nav-item message" data-menu="sys-manage">
                      <a href="javascript:load_html('web/pages/htmls/viewBoard/pf_bd_tables0.html');">
                    <!--   <a href="javascript:load_html('web/pages/htmls/zero/look/htmls/oee.html');"> -->
                          <span class="iconfont">&#xe63f;</span>
                          <span class="pf-nav-title">看板信息</span>
                      </a>       
                  </li>
                   <li class="pf-nav-item finance" data-menu="contract-mange">
                      <a href="javascript:load_html('web/pages/htmls/finance/pf_bd_tables6.html');">
                          <span class="iconfont">&#xe61c;</span>
                          <span class="pf-nav-title">财务统计</span>
                      </a>
                  </li> 
<!--                   <li class="pf-nav-item manger" data-menu="pur-source">
                  		<a href="javascript:;">                 
                          <span class="iconfont">&#xe626;</span>
                          <span class="pf-nav-title"></span>
                      </a>
                  </li>

                  <li class="pf-nav-item manger" data-menu="contract-mange">                    
                      <a href="javascript:;">
                          <span class="iconfont">&#xe65f;</span>
                          <span class="pf-nav-title"></span>
                      </a>
                  </li>
                  <li class="pf-nav-item manger" data-menu="pur-source">                   
                      <a href="javascript:;">
                          <span class="iconfont">&#xe6e6;</span>
                          <span class="pf-nav-title"></span>
                      </a>
                  </li>

                  <li class="pf-nav-item manger" data-menu="contract-mange">
                      <a href="javascript:;">
                          <span class="iconfont">&#xe677;</span>
                          <span class="pf-nav-title"></span>
                      </a>
                  </li>


                  <li class="pf-nav-item manger" data-menu="pur-source">
                      <a href="javascript:;">
                          <span class="iconfont">&#xe611;</span>
                          <span class="pf-nav-title"></span>
                      </a>
                  </li>

                  <li class="pf-nav-item manger" data-menu="contract-mange">
                      <a href="javascript:;">
                          <span class="iconfont">&#xe69e;</span>
                          <span class="pf-nav-title"></span>
                      </a>
                  </li>

                  <li class="pf-nav-item manger" data-menu="pur-source">
                      <a href="javascript:;">
                          <span class="iconfont">&#xe629;</span>
                          <span class="pf-nav-title"></span>
                      </a>
                  </li>

                  <li class="pf-nav-item manger" data-menu="contract-mange">
                      <a href="javascript:;">
                          <span class="iconfont">&#xe7f2;</span>
                          <span class="pf-nav-title"></span>
                      </a>
                  </li> -->
                </ul>
              </div>
              
              <a href="javascript:;" class="pf-nav-prev disabled iconfont">&#xe606;</a>
              <a href="javascript:;" class="pf-nav-next iconfont">&#xe607;</a>
            </div>
            <div class="pf-user">
                <div class="pf-user-photo">
                    <img src="web/pages/images/main/user.png" alt="">
                </div>
                <h4 class="pf-user-name ellipsis">鸿勋</h4>
                <i class="iconfont xiala">&#xe607;</i>

                <div class="pf-user-panel">
                    <ul class="pf-user-opt">
                        <li class="userMessage">
                        	<a href="javascript:load_html('web/pages/htmls/seven/pf_bd_tables7.jsp');">                    
                                <i class="iconfont">&#xe60d;</i>
                                <span class="pf-opt-name">用户信息</span>
                            </a>
                            
                        </li>
                        <li class="pf-modify-pwd">
                            <a href="http://www.uimaker.com">
                                <i class="iconfont">&#xe634;</i>
                                <span class="pf-opt-name">修改密码</span>
                            </a>
                        </li>
                        <li class="pf-logout">
                            <a href="index.jsp">
                                <i class="iconfont">&#xe60e;</i>
                                <span class="pf-opt-name">退出</span>
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>


         <div id="pf-body">
<!-- 			<div id="pf-sider"></div>           
            <div id="pf-page">
                  <div title="首页" style="padding:10px 5px 5px 10px;">
                    <iframe class="page-iframe" src="web/pages/workbench.html" frameborder="no"   border="no" height="100%" width="100%" scrolling="auto"></iframe>
                  </div>
            </div> -->
        </div>  

        <div id="pf-ft">
            <div class="system-name">
              <i class="iconfont">&#xe6fe;</i>
              <span>信息管理系统&nbsp;v1.0</span>
            </div>
            <div class="copyright-name">
              <span>CopyRight&nbsp;2016&nbsp;&nbsp;uimaker.com&nbsp;版权所有</span>
              <i class="iconfont" >&#xe6ff;</i>
            </div>
        </div>
        <div id="dialog"  class="easyui-dialog" style="width:90%;height:90%;padding:0px" data-options="
			closed:true,
			resizable:true,	
			modal:true,		
			onResize:function(){
				$(this).dialog('center');
			}">
		</div> 
    </div>

    <script type="text/javascript" src="web/custom/jquery.min.js"></script>
    <script type="text/javascript" src="web/custom/jquery.easyui.min.js"></script>
    <!-- <script type="text/javascript" src="js/menu.js"></script> -->
    <script type="text/javascript" src="web/pages/js/main.js"></script>
    <!--[if IE 7]>
      <script type="text/javascript">
        $(window).resize(function(){
          $('#pf-bd').height($(window).height()-76);
        }).resize();
      </script>
    <![endif]-->  

    
    <script type="text/javascript">
/*     $('.easyui-tabs1').tabs({
      tabHeight: 44,
      onSelect:function(title,index){
        var currentTab = $('.easyui-tabs1').tabs("getSelected");
        if(currentTab.find("iframe") && currentTab.find("iframe").size()){
            currentTab.find("iframe").attr("src",currentTab.find("iframe").attr("src"));
        }
      }
    }) */
/*     $(window).resize(function(){
          $('.tabs-panels').height($("#pf-page").height()-46);
          $('.panel-body').height($("#pf-page").height()-76)
    }).resize(); */

    var page = 0,
        pages = ($('.pf-nav').height() / 70) - 1;

    if(pages === 0){
      $('.pf-nav-prev,.pf-nav-next').hide();
    }
    $(document).on('click', '.pf-nav-prev,.pf-nav-next', function(){


      if($(this).hasClass('disabled')) return;
      if($(this).hasClass('pf-nav-next')){
        page++;
        $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
        if(page == pages){
          $(this).addClass('disabled');
          $('.pf-nav-prev').removeClass('disabled');
        }else{
          $('.pf-nav-prev').removeClass('disabled');
        }
        
      }else{
        page--;
        $('.pf-nav').stop().animate({'margin-top': -70*page}, 200);
        if(page == 0){
          $(this).addClass('disabled');
          $('.pf-nav-next').removeClass('disabled');
        }else{
          $('.pf-nav-next').removeClass('disabled');
        }
        
      }
    })

    // setTimeout(function(){
    //    $('.tabs-panels').height($("#pf-page").height()-46);
    //    $('.panel-body').height($("#pf-page").height()-76)
    // }, 200)
    </script>

    <script>
        function load_html(url) {
            document.getElementById("pf-body").innerHTML = '<object type="text/html" data="' + url +'" width="100%" height="100%"></object>';}
            //'<iframe class="page-iframe" src="' + url +'" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>';}         
        $(function(){
        	var authority = ${authority};       	 
        	var flag = 10;
        	var str = authority.toString(2); 
        	var cnt = 18-str.length;
        	for(var i=0; i<cnt; i++){
        		str = "0" + str;
        	} 
        	//alert(str); 
        	localStorage.setItem('authority',str); 	
	
        	if(!(str.substr(0, 3).indexOf("1")>-1)){
        		$('.pf-nav-item.finance').hide();
        	}else{
        		if(flag>6){
        			flag=6;
        		}
        	}	
        	if(!(str.substr(3, 1).indexOf("1")>-1)){
        		$('.pf-nav-item.message').hide();
        	}else{
        		if(flag>5){
        			flag=5;
        		}
        	}	
        	if(!(str.substr(4, 3).indexOf("1")>-1)){
        		$('.pf-nav-item.production').hide();
        	}else{
        		if(flag>4){
        			flag=4;
        		}
        	}	
        	if(!(str.substr(7, 3).indexOf("1")>-1)){
        		$('.pf-nav-item.material').hide();
        	}else{
        		if(flag>3){
        			flag=3;
        		}
        	}	
        	if(!(str.substr(10, 4).indexOf("1")>-1)){
        		$('.pf-nav-item.plan').hide(); 
        	}else{
        		if(flag>2){
        			flag=2;
        		}
        	}	
        	if(!(str.substr(14, 4).indexOf("1")>-1)){
        		$('.pf-nav-item.base').hide();
        	}else{
	        	if(flag>1){
        			flag=1;
        		}
        	} 
        	
        	if(flag == 1){
        		document.getElementById("pf-body").innerHTML = '<object type="text/html" data="' + "web/pages/htmls/baseMsg/pf_bd_tables1.html" +'" width="100%" height="100%"></object>';
        	}else if(flag == 2){
        		document.getElementById("pf-body").innerHTML = '<object type="text/html" data="' + "web/pages/htmls/planMag/pf_bd_tables2.html" +'" width="100%" height="100%"></object>';
        	}else if(flag == 3){
        		document.getElementById("pf-body").innerHTML = '<object type="text/html" data="' + "web/pages/htmls/materialStock/pf_bd_tables3.html" +'" width="100%" height="100%"></object>';
        	}else if(flag == 4){
        		document.getElementById("pf-body").innerHTML = '<object type="text/html" data="' + "web/pages/htmls/productionStock/pf_bd_tables4.html" +'" width="100%" height="100%"></object>';
        	}else if(flag == 5){
        		document.getElementById("pf-body").innerHTML = '<object type="text/html" data="' + "web/pages/htmls/viewBoard/pf_bd_tables0.html" +'" width="100%" height="100%"></object>';
        	}else if(flag == 6){
        		document.getElementById("pf-body").innerHTML = '<object type="text/html" data="' + "web/pages/htmls/finance/pf_bd_tables6.html" +'" width="100%" height="100%"></object>';
        	}         	
		  
        	//alert(authority1)
        	var authority1 = ${authority1};
         	str = authority1.toString(2); 
        	cnt = 18-str.length;
        	for(var i=0; i<cnt; i++){
        		str = "0" + str;
        	} 
        	//alert(str);  
        	localStorage.setItem('authority1',str);
        	if(str.indexOf("1")>-1){
        		$('.userMessage').hide();
        	}  	
        	var authority2 = ${authority2};
        	str = authority2.toString(2); 
        	cnt = 6-str.length;
        	for(var i=0; i<cnt; i++){
        		str = "0" + str;
        	} 
        	//alert(str);  
        	localStorage.setItem('authority2',str); 	
        });    
    </script>
    
<!--     <script>
    	function spreadUserMessage(){
    		$("#dialog").dialog({
			     title: '用户信息 :',		
			     content:"<iframe scrolling='auto' frameborder='0' src='web/pages/htmls/seven/pf_bd_tables7.html' style='width:100%; height:100%; display:block;'></iframe>"
			});
    	}
    </script> -->
</body> 
</html>
