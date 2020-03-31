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
    <title>工作台</title> 

<link href="../../css/base.css" rel="stylesheet">
<link rel="stylesheet" href="../../../custom/uimaker/easyui.css">
<link rel="stylesheet" href="../../../custom/uimaker/icon.css">
<link rel="stylesheet" href="./rectangles.css">
<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
<script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../js/myjs/myjs.js"></script> 
<script type="text/javascript" src="./js/html2canvas.js"></script>
<script type="text/javascript" src="./js/jsPdf.debug.js"></script>
<!-- <script type="text/javascript" src="http://static.runoob.com/assets/jquery/2.0.3/jquery.min.js"></script> -->
<!-- <script type="text/javascript" src="http://static.runoob.com/assets/qrcode/qrcode.min.js"></script> -->
<script type="text/javascript" src="../../js/myjs/qrcode.js"></script> 
    <script type="text/javascript" src="../../js/myjs/jquery.form.js"></script>     
</head> 
<body>
    <div class="container">
        <div id="hd">
            
        </div>

        <div id="bd">
            <div class="bd-content">
				<button id="renderPdf">DOWNLOAD PDF</button>
				<button id="importExl">导入</button>
<!-- <a href="javascript:;" class="easyui-linkbutton" style="height:27px;" onclick="$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格')">导入</a>  -->
                <div class="center-part">
                        
                    <div class="center-items todo">

                        <ul class="work-items clearfix">
 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item purple">
										<div  style="height:10px;"></div> 
										<div id="qrcode" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                   	<div class="work-item purple">
										<div  style="height:10px;"></div> 
										<div id="qrcode1" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text1" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result1" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item purple">
										<div  style="height:10px;"></div> 
										<div id="qrcode2" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text2" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                   		<h1 id="result2" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item purple">
										<div  style="height:10px;"></div> 
										<div id="qrcode3" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text3" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                   		<h1 id="result3" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode4" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text4" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result4" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode5" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text5" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result5" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode6" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text6" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                   		<h1 id="result6" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode7" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text7" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result7" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode8" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text8" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result8" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode9" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text9" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result9" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode10" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text10" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result10" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>    
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode11" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text11" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result11" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode12" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text12" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result12" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode13" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text13" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                   		<h1 id="result13" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>  
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode14" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text14" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result14" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode15" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text15" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result15" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode16" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text16" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result16" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode17" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text17" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result17" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode18" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text18" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result18" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode19" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text19" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result19" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode20" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text20" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result20" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode21" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text21" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result21" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode22" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text22" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result22" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode23" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text23" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result23" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode24" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text24" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result24" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>   
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode25" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text25" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result25" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode26" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text26" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                   		<h1 id="result26" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode27" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text27" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result27" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode28" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text28" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                   		<h1 id="result28" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode29" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text29" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result29" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>  
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode30" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text30" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                   		<h1 id="result30" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode31" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text31" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result31" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode32" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text32" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result32" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode33" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text33" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result33" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li> 
                            <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode34" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text34" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                   		<h1 id="result34" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>      
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode35" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text35" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result35" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>   
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode36" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text36" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result36" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>   
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode37" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text37" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result37" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>   
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode38" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text38" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                   		<h1 id="result38" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>   
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode39" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text39" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    	<h1 id="result39" style="width:100%; margin-top:5px; text-align:center;"></h1>
                                    </div>
                                </div>
                            </li>   
<!--                                                         <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode40" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text40" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    </div>
                                </div>
                            </li>   
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode41" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text41" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    </div>
                                </div>
                            </li>   
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode42" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text42" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    </div>
                                </div>
                            </li>   
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode43" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text43" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    </div>
                                </div>
                            </li>   
                                                        <li>
                                <div class="work-inner">
                                    <div class="work-item gray">
										<div  style="height:10px;"></div> 
										<div id="qrcode44" style="width:200px; height:200px; text-align: center;margin: 0 auto;" ></div>
										<input id="text44" type="text" value="输入部件号回车生成二维码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:center;" />
                                    </div>
                                </div>
                            </li>  -->                                                  
                        </ul>
                    </div>
                </div>
            </div>
        </div>  
    </div>

    <div id="importExcelDlg" class="easyui-dialog" style="width:280px;height:150px;padding:10px 0px" data-options="top:10,closed:true">
		<form action='' enctype="multipart/form-data" method="post" name="fileForm"> 
			<label >
	 			<input type="file" class="file" name="filename" id="fileId" onchange="upload('${pageContext.request.contextPath}/bomTree.htm/qrcodeImport')" accept=".xls"/>
			</label> 			
		</form> 
	</div> 
    
    <script type="text/javascript">

      var downPdf = document.getElementById("renderPdf");

      downPdf.onclick = function() {
          html2canvas(document.body, {
              onrendered:function(canvas) {

                  var contentWidth = canvas.width;
                  var contentHeight = canvas.height;

                  //一页pdf显示html页面生成的canvas高度;
                  var pageHeight = contentWidth / 595.28 * 841.89;
                  //未生成pdf的html页面高度
                  var leftHeight = contentHeight;
                  //pdf页面偏移
                  var position = 0;
                  //a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
                  var imgWidth = 555.28;
                  var imgHeight = 555.28/contentWidth * contentHeight;

                  var pageData = canvas.toDataURL('image/jpeg', 1.0);

                  var pdf = new jsPDF('', 'pt', 'a4');
                  //有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
                  //当内容未超过pdf一页显示的范围，无需分页
                  if (leftHeight < pageHeight) {
                      pdf.addImage(pageData, 'JPEG', 20, 0, imgWidth, imgHeight );
                  } else {
                      while(leftHeight > 0) {
                          pdf.addImage(pageData, 'JPEG', 20, position, imgWidth, imgHeight)
                          leftHeight -= pageHeight;
                          position -= 841.89;
                          //避免添加空白页
                          if(leftHeight > 0) {
                              pdf.addPage();
                          }
                      }
                  }

                  pdf.save('content.pdf');
              }
          })
      }
      var importExl = document.getElementById("importExl");
      importExl.onclick = function() {
      		$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格');
      }
      
      function upload(url) { 
			var fileName = $('#fileId').val().split('\\'); //得到文件名数组
			var fileSize =  document.getElementById('fileId').files[0]; //获得文件大小；
			fileName2 = fileName[fileName.length-1]; // 获得文件名
			filePath = $('#fileId').val().toLowerCase().split(".");
			fileType =  filePath[filePath.length - 1]; //获得文件结尾的类型如 zip rar 这种写法确保是最后的
			if(!(fileType == "xls")){
			 	alert('文件格式不符合要求！');
			}else if(fileSize.size>10485760){
			  	alert('文件超过10485760')		       
			}else{
			   	//alert('asdfsa');
				var form = $("form[name=fileForm]"); 
				var options = {  
					url:url, //'${pageContext.request.contextPath}/numTen.htm/exlImport', //上传文件的路径  
					type:'post',
					clearForm:true,
					success:function(data){
						//alert(JSON.stringify(data));
						$('#importExcelDlg').dialog('close');	
 						for(var i=0; i<data.length; i++){
							for(var key in data[i]){																	
								if(i==0){
									qrcode.clear();									
									qrcode.makeCode(data[i][key].split("||")[0]);									
									document.getElementById("text").value=data[i][key].split("||")[0];
									document.getElementById("result").innerText=data[i][key].split("||")[1];
								}else if(i==1){
									qrcode1.clear();									
									qrcode1.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text1").value=data[i][key].split("||")[0];
									document.getElementById("result1").innerText=data[i][key].split("||")[1];
								}else if(i==2){
									qrcode2.clear();									
									qrcode2.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text2").value=data[i][key].split("||")[0];
									document.getElementById("result2").innerText=data[i][key].split("||")[1];
								}else if(i==3){
									qrcode3.clear();									
									qrcode3.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text3").value=data[i][key].split("||")[0];
									document.getElementById("result3").innerText=data[i][key].split("||")[1];
								}else if(i==2){
									qrcode2.clear();									
									qrcode2.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text2").value=data[i][key].split("||")[0];
									document.getElementById("result").innerText=data[i][key].split("||")[1];
								}else if(i==4){
									qrcode4.clear();									
									qrcode4.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text4").value=data[i][key].split("||")[0];
									document.getElementById("result4").innerText=data[i][key].split("||")[1];
								}else if(i==5){
									qrcode5.clear();									
									qrcode5.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text5").value=data[i][key].split("||")[0];
									document.getElementById("result5").innerText=data[i][key].split("||")[1];
								}else if(i==6){
									qrcode6.clear();									
									qrcode6.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text6").value=data[i][key].split("||")[0];
									document.getElementById("result6").innerText=data[i][key].split("||")[1];
								}else if(i==7){
									qrcode7.clear();									
									qrcode7.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text7").value=data[i][key].split("||")[0];
									document.getElementById("result7").innerText=data[i][key].split("||")[1];
								}else if(i==8){
									qrcode8.clear();									
									qrcode8.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text8").value=data[i][key].split("||")[0];
									document.getElementById("result8").innerText=data[i][key].split("||")[1];
								}else if(i==9){
									qrcode9.clear();									
									qrcode9.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text9").value=data[i][key].split("||")[0];
									document.getElementById("result9").innerText=data[i][key].split("||")[1];
								}else if(i==10){
									qrcode10.clear();									
									qrcode10.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text10").value=data[i][key].split("||")[0];
									document.getElementById("result10").innerText=data[i][key].split("||")[1];
								}else if(i==11){
									qrcode11.clear();									
									qrcode11.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text11").value=data[i][key].split("||")[0];
									document.getElementById("result11").innerText=data[i][key].split("||")[1];
								}else if(i==12){
									qrcode12.clear();									
									qrcode12.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text12").value=data[i][key].split("||")[0];
									document.getElementById("result12").innerText=data[i][key].split("||")[1];
								}else if(i==13){
									qrcode13.clear();									
									qrcode13.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text13").value=data[i][key].split("||")[0];
									document.getElementById("result13").innerText=data[i][key].split("||")[1];
								}else if(i==14){
									qrcode14.clear();									
									qrcode14.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text14").value=data[i][key].split("||")[0];
									document.getElementById("result14").innerText=data[i][key].split("||")[1];
								}else if(i==15){
									qrcode15.clear();									
									qrcode15.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text15").value=data[i][key].split("||")[0];
									document.getElementById("result15").innerText=data[i][key].split("||")[1];
								}else if(i==16){
									qrcode16.clear();									
									qrcode16.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text16").value=data[i][key].split("||")[0];
									document.getElementById("result16").innerText=data[i][key].split("||")[1];
								}else if(i==17){
									qrcode17.clear();									
									qrcode17.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text17").value=data[i][key].split("||")[0];
									document.getElementById("result17").innerText=data[i][key].split("||")[1];
								}else if(i==18){
									qrcode18.clear();									
									qrcode18.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text18").value=data[i][key].split("||")[0];
									document.getElementById("result18").innerText=data[i][key].split("||")[1];
								}else if(i==19){
									qrcode19.clear();									
									qrcode19.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text19").value=data[i][key].split("||")[0];
									document.getElementById("result19").innerText=data[i][key].split("||")[1];
								}else if(i==20){
									qrcode20.clear();									
									qrcode20.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text20").value=data[i][key].split("||")[0];
									document.getElementById("result20").innerText=data[i][key].split("||")[1];
								}else if(i==21){
									qrcode21.clear();									
									qrcode21.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text21").value=data[i][key].split("||")[0];
									document.getElementById("result21").innerText=data[i][key].split("||")[1];
								}else if(i==22){
									qrcode22.clear();									
									qrcode22.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text22").value=data[i][key].split("||")[0];
									document.getElementById("result22").innerText=data[i][key].split("||")[1];
								}else if(i==23){
									qrcode23.clear();									
									qrcode23.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text23").value=data[i][key].split("||")[0];
									document.getElementById("result23").innerText=data[i][key].split("||")[1];
								}else if(i==24){
									qrcode24.clear();									
									qrcode24.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text24").value=data[i][key].split("||")[0];
									document.getElementById("result24").innerText=data[i][key].split("||")[1];
								}else if(i==25){
									qrcode25.clear();									
									qrcode25.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text25").value=data[i][key].split("||")[0];
									document.getElementById("result25").innerText=data[i][key].split("||")[1];
								}else if(i==26){
									qrcode26.clear();									
									qrcode26.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text26").value=data[i][key].split("||")[0];
									document.getElementById("result26").innerText=data[i][key].split("||")[1];
								}else if(i==27){
									qrcode27.clear();									
									qrcode27.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text27").value=data[i][key].split("||")[0];
									document.getElementById("result27").innerText=data[i][key].split("||")[1];
								}else if(i==28){
									qrcode28.clear();									
									qrcode28.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text28").value=data[i][key].split("||")[0];
									document.getElementById("result28").innerText=data[i][key].split("||")[1];
								}else if(i==29){
									qrcode29.clear();									
									qrcode29.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text29").value=data[i][key].split("||")[0];
									document.getElementById("result29").innerText=data[i][key].split("||")[1];
								}else if(i==30){
									qrcode30.clear();									
									qrcode30.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text30").value=data[i][key].split("||")[0];
									document.getElementById("result30").innerText=data[i][key].split("||")[1];
								}else if(i==31){
									qrcode31.clear();									
									qrcode31.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text31").value=data[i][key].split("||")[0];
									document.getElementById("result31").innerText=data[i][key].split("||")[1];
								}else if(i==32){
									qrcode32.clear();									
									qrcode32.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text32").value=data[i][key].split("||")[0];
									document.getElementById("result32").innerText=data[i][key].split("||")[1];
								}else if(i==33){
									qrcode33.clear();									
									qrcode33.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text33").value=data[i][key].split("||")[0];
									document.getElementById("result33").innerText=data[i][key].split("||")[1];
								}else if(i==34){
									qrcode34.clear();									
									qrcode34.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text34").value=data[i][key].split("||")[0];
									document.getElementById("result34").innerText=data[i][key].split("||")[1];
								}else if(i==35){
									qrcode35.clear();									
									qrcode35.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text35").value=data[i][key].split("||")[0];
									document.getElementById("result35").innerText=data[i][key].split("||")[1];
								}else if(i==36){
									qrcode36.clear();									
									qrcode36.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text36").value=data[i][key].split("||")[0];
									document.getElementById("result36").innerText=data[i][key].split("||")[1];
								}else if(i==37){
									qrcode37.clear();									
									qrcode37.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text37").value=data[i][key].split("||")[0];
									document.getElementById("result37").innerText=data[i][key].split("||")[1];
								}else if(i==38){
									qrcode38.clear();									
									qrcode38.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text38").value=data[i][key].split("||")[0];
									document.getElementById("result38").innerText=data[i][key].split("||")[1];
								}else if(i==39){
									qrcode39.clear();									
									qrcode39.makeCode(data[i][key].split("||")[0]);
									document.getElementById("text39").value=data[i][key].split("||")[0];
									document.getElementById("result39").innerText=data[i][key].split("||")[1];
								}
								break;			
							}
						} 
					},
					beforeSubmit: function () {						
						load();			        
					},
					complete: function () {
						disLoad();	
					}
				};  
				form.ajaxSubmit(options); 
				//alert('123');
			}
    	} 
    </script>
    <script type="text/javascript">
	var qrcode = new QRCode('qrcode', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode1 = new QRCode('qrcode1', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode2 = new QRCode('qrcode2', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode3 = new QRCode('qrcode3', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode4 = new QRCode('qrcode4', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode5 = new QRCode('qrcode5', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode6 = new QRCode('qrcode6', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode7 = new QRCode('qrcode7', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode8 = new QRCode('qrcode8', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode9 = new QRCode('qrcode9', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode10 = new QRCode('qrcode10', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode11 = new QRCode('qrcode11', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode12 = new QRCode('qrcode12', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode13 = new QRCode('qrcode13', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode14 = new QRCode('qrcode14', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
		var qrcode15 = new QRCode('qrcode15', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
		var qrcode16 = new QRCode('qrcode16', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
		var qrcode17 = new QRCode('qrcode17', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
		var qrcode18 = new QRCode('qrcode18', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
		var qrcode19 = new QRCode('qrcode19', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
		var qrcode20 = new QRCode('qrcode20', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
		var qrcode21 = new QRCode('qrcode21', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
	var qrcode22 = new QRCode('qrcode22', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
		var qrcode23 = new QRCode('qrcode23', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
		var qrcode24 = new QRCode('qrcode24', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
		var qrcode25 = new QRCode('qrcode25', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
			var qrcode26 = new QRCode('qrcode26', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
			var qrcode27 = new QRCode('qrcode27', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
			var qrcode28 = new QRCode('qrcode28', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
			var qrcode29 = new QRCode('qrcode29', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
			var qrcode30 = new QRCode('qrcode30', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
			var qrcode31 = new QRCode('qrcode31', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
			var qrcode32 = new QRCode('qrcode32', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
			var qrcode33 = new QRCode('qrcode33', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});
			var qrcode34 = new QRCode('qrcode34', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});	
				var qrcode35 = new QRCode('qrcode35', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});	
				var qrcode36 = new QRCode('qrcode36', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});	
				var qrcode37 = new QRCode('qrcode37', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});	
				var qrcode38 = new QRCode('qrcode38', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});	
				var qrcode39 = new QRCode('qrcode39', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});	
/* 				var qrcode40 = new QRCode('qrcode40', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});	
				var qrcode41 = new QRCode('qrcode41', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});	
				var qrcode42 = new QRCode('qrcode42', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});	
				var qrcode43 = new QRCode('qrcode43', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});	 
				var qrcode44 = new QRCode('qrcode44', {
	 // text: 'your content',
	  width: 200,
	  height: 200,
	  colorDark : '#000000',
	  colorLight : '#ffffff',
	  correctLevel : QRCode.CorrectLevel.H
	});*/	
	$("#text").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text");
			qrcode.clear();
				//alert(elText.value)
			qrcode.makeCode(elText.value);
		}
	});


	$("#text1").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text1");
			qrcode1.clear();
				//alert(elText.value)
			qrcode1.makeCode(elText.value);
		}
	});	
	$("#text2").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text2");
			qrcode2.clear();
				//alert(elText.value)
			qrcode2.makeCode(elText.value);
		}
	});	
		$("#text3").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text3");
			qrcode3.clear();
				//alert(elText.value)
			qrcode3.makeCode(elText.value);
		}
	});	
		$("#text4").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text4");
			qrcode4.clear();
				//alert(elText.value)
			qrcode4.makeCode(elText.value);
		}
	});	
		$("#text5").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text5");
			qrcode5.clear();
				//alert(elText.value)
			qrcode5.makeCode(elText.value);
		}
	});	
		$("#text6").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text6");
			qrcode6.clear();
				//alert(elText.value)
			qrcode6.makeCode(elText.value);
		}
	});	
		$("#text7").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text7");
			qrcode7.clear();
				//alert(elText.value)
			qrcode7.makeCode(elText.value);
		}
	});	
		$("#text8").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text8");
			qrcode8.clear();
				//alert(elText.value)
			qrcode8.makeCode(elText.value);
		}
	});	
		$("#text9").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text9");
			qrcode9.clear();
				//alert(elText.value)
			qrcode9.makeCode(elText.value);
		}
	});	
		$("#text10").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text10");
			qrcode10.clear();
				//alert(elText.value)
			qrcode10.makeCode(elText.value);
		}
	});	
		$("#text11").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text11");
			qrcode11.clear();
				//alert(elText.value)
			qrcode11.makeCode(elText.value);
		}
	});	
		$("#text12").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text12");
			qrcode12.clear();
				//alert(elText.value)
			qrcode12.makeCode(elText.value);
		}
	});	
		$("#text13").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text13");
			qrcode13.clear();
				//alert(elText.value)
			qrcode13.makeCode(elText.value);
		}
	});	
			$("#text14").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text14");
			qrcode14.clear();
				//alert(elText.value)
			qrcode14.makeCode(elText.value);
		}
	});	
	$("#text15").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text15");
			qrcode15.clear();
				//alert(elText.value)
			qrcode15.makeCode(elText.value);
		}
	});	
	$("#text16").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text16");
			qrcode16.clear();
				//alert(elText.value)
			qrcode16.makeCode(elText.value);
		}
	});	
	$("#text17").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text17");
			qrcode17.clear();
				//alert(elText.value)
			qrcode17.makeCode(elText.value);
		}
	});	
	$("#text18").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text18");
			qrcode18.clear();
				//alert(elText.value)
			qrcode18.makeCode(elText.value);
		}
	});	
	$("#text19").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text19");
			qrcode19.clear();
				//alert(elText.value)
			qrcode19.makeCode(elText.value);
		}
	});	
		$("#text20").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text20");
			qrcode20.clear();
				//alert(elText.value)
			qrcode20.makeCode(elText.value);
		}
	});	
		$("#text21").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text21");
			qrcode21.clear();
				//alert(elText.value)
			qrcode21.makeCode(elText.value);
		}
	});	
		$("#text22").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text22");
			qrcode22.clear();
				//alert(elText.value)
			qrcode22.makeCode(elText.value);
		}
	});	
		$("#text23").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text23");
			qrcode23.clear();
				//alert(elText.value)
			qrcode23.makeCode(elText.value);
		}
	});	
		$("#text24").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text24");
			qrcode24.clear();
				//alert(elText.value)
			qrcode24.makeCode(elText.value);
		}
	});	
			$("#text25").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text25");
			qrcode25.clear();
				//alert(elText.value)
			qrcode25.makeCode(elText.value);
		}
	});	
			$("#text26").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text26");
			qrcode26.clear();
				//alert(elText.value)
			qrcode26.makeCode(elText.value);
		}
	});	
			$("#text27").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text27");
			qrcode27.clear();
				//alert(elText.value)
			qrcode27.makeCode(elText.value);
		}
	});	
			$("#text28").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text28");
			qrcode28.clear();
				//alert(elText.value)
			qrcode28.makeCode(elText.value);
		}
	});	
			$("#text29").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text29");
			qrcode29.clear();
				//alert(elText.value)
			qrcode29.makeCode(elText.value);
		}
	});	
			$("#text30").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text30");
			qrcode30.clear();
				//alert(elText.value)
			qrcode30.makeCode(elText.value);
		}
	});	
			$("#text31").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text31");
			qrcode31.clear();
				//alert(elText.value)
			qrcode31.makeCode(elText.value);
		}
	});	
			$("#text32").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text32");
			qrcode32.clear();
				//alert(elText.value)
			qrcode32.makeCode(elText.value);
		}
	});	
			$("#text33").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text33");
			qrcode33.clear();
				//alert(elText.value)
			qrcode33.makeCode(elText.value);
		}
	});	
			$("#text34").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text34");
			qrcode34.clear();
				//alert(elText.value)
			qrcode34.makeCode(elText.value);
		}
	});	
				$("#text35").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text35");
			qrcode35.clear();
				//alert(elText.value)
			qrcode35.makeCode(elText.value);
		}
	});
				$("#text36").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text36");
			qrcode36.clear();
				//alert(elText.value)
			qrcode36.makeCode(elText.value);
		}
	});
				$("#text37").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text37");
			qrcode37.clear();
				//alert(elText.value)
			qrcode37.makeCode(elText.value);
		}
	});
				$("#text38").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text38");
			qrcode38.clear();
				//alert(elText.value)
			qrcode38.makeCode(elText.value);
		}
	});
				$("#text39").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text39");
			qrcode39.clear();
				//alert(elText.value)
			qrcode39.makeCode(elText.value);
		}
	});
/* 				$("#text40").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text40");
			qrcode40.clear();
				//alert(elText.value)
			qrcode40.makeCode(elText.value);
		}
	});
				$("#text41").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text41");
			qrcode41.clear();
				//alert(elText.value)
			qrcode41.makeCode(elText.value);
		}
	});
				$("#text42").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text42");
			qrcode42.clear();
				//alert(elText.value)
			qrcode42.makeCode(elText.value);
		}
	});
				$("#text43").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text43");
			qrcode43.clear();
				//alert(elText.value)
			qrcode43.makeCode(elText.value);
		}
	});
				$("#text44").on("keydown", function (e) {
		if (e.keyCode == 13) {
				//makeCode("text");
			var elText = document.getElementById("text44");
			qrcode44.clear();
				//alert(elText.value)
			qrcode44.makeCode(elText.value);
		}
	}); */
</script>
</body> 
</html>
