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
<link rel="stylesheet" href="./barcode.css">
<script type="text/javascript" src="../../../custom/jquery.min.js"></script>
<script type="text/javascript" src="../../../custom/jquery.easyui.min.js"></script>
<script type="text/javascript" src="../../js/myjs/myjs.js"></script> 
<script type="text/javascript" src="./js/html2canvas.js"></script>
<script type="text/javascript" src="./js/jsPdf.debug.js"></script>
<!-- <script type="text/javascript" src="http://static.runoob.com/assets/jquery/2.0.3/jquery.min.js"></script> -->
<!-- <script type="text/javascript" src="http://static.runoob.com/assets/qrcode/qrcode.min.js"></script> -->
<script type="text/javascript" src="../../js/myjs/qrcode.js"></script> 
<script type="text/javascript" src="../../js/myjs/jquery.form.js"></script> 
<script type="text/javascript" src="../../js/myjs/JsBarcode.all.js"></script>    
</head> 
<body>
    <div class="container">
        <div id="hd">
            
        </div>

        <div id="bd">
            <div class="bd-content">
				<button id="renderPdf" style="display: none;">DOWNLOAD PDF</button>
				<button id="importExl">导入</button>
				<a style="display: none;" >下载</a>
<!-- <a href="javascript:;" class="easyui-linkbutton" style="height:27px;" onclick="$('#importExcelDlg').dialog('open').dialog('setTitle','导入表格')">导入</a>  -->
                <div class="center-part">         
                    <div class="center-items todo">
                        <ul class="work-items clearfix">
                            <li>
                                <div class="work-inner">
                                    <div class="work-item purple">																				
									  	<img id="imgcode0" style="text-align: center;margin: 0 auto;"/> 
									  	<input id="text0" type="text" value="输入部件号回车生成条形码" style="width:98%; margin-top:0px;border: 0px;outline:none; text-align:left;" />
                                    	<h1 id="result0" style="width:100%; margin-top:5px; text-align:left;"></h1>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                   	<div class="work-item purple">
										<img id="imgcode1" style="text-align: center;margin: 0 auto;"/> 
										<input id="text1" type="text" value="输入部件号回车生成条形码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
                                    	<h1 id="result1" style="width:100%; margin-top:5px; text-align:left;"></h1>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="work-inner">
                                   	<div class="work-item purple">
										<img id="imgcode2" style="text-align: center;margin: 0 auto;"/> 
										<input id="text2" type="text" value="输入部件号回车生成条形码" style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
                                    	<h1 id="result2" style="width:100%; margin-top:5px; text-align:left;"></h1>
                                    </div>
                                </div>
                            </li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode3" style="text-align: center;margin: 0 auto;" />
										<input id="text3" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result3"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode4" style="text-align: center;margin: 0 auto;" />
										<input id="text4" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result4"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode5" style="text-align: center;margin: 0 auto;" />
										<input id="text5" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result5"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode6" style="text-align: center;margin: 0 auto;" />
										<input id="text6" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result6"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode7" style="text-align: center;margin: 0 auto;" />
										<input id="text7" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result7"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode8" style="text-align: center;margin: 0 auto;" />
										<input id="text8" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result8"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode9" style="text-align: center;margin: 0 auto;" />
										<input id="text9" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result9"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode10" style="text-align: center;margin: 0 auto;" />
										<input id="text10" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result10"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode11" style="text-align: center;margin: 0 auto;" />
										<input id="text11" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result11"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode12" style="text-align: center;margin: 0 auto;" />
										<input id="text12" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result12"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode13" style="text-align: center;margin: 0 auto;" />
										<input id="text13" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result13"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode14" style="text-align: center;margin: 0 auto;" />
										<input id="text14" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result14"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode15" style="text-align: center;margin: 0 auto;" />
										<input id="text15" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result15"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode16" style="text-align: center;margin: 0 auto;" />
										<input id="text16" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result16"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode17" style="text-align: center;margin: 0 auto;" />
										<input id="text17" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result17"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode18" style="text-align: center;margin: 0 auto;" />
										<input id="text18" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result18"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode19" style="text-align: center;margin: 0 auto;" />
										<input id="text19" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result19"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode20" style="text-align: center;margin: 0 auto;" />
										<input id="text20" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result20"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode21" style="text-align: center;margin: 0 auto;" />
										<input id="text21" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result21"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode22" style="text-align: center;margin: 0 auto;" />
										<input id="text22" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result22"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode23" style="text-align: center;margin: 0 auto;" />
										<input id="text23" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result23"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode24" style="text-align: center;margin: 0 auto;" />
										<input id="text24" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result24"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode25" style="text-align: center;margin: 0 auto;" />
										<input id="text25" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result25"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode26" style="text-align: center;margin: 0 auto;" />
										<input id="text26" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result26"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode27" style="text-align: center;margin: 0 auto;" />
										<input id="text27" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result27"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode28" style="text-align: center;margin: 0 auto;" />
										<input id="text28" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result28"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode29" style="text-align: center;margin: 0 auto;" />
										<input id="text29" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result29"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode30" style="text-align: center;margin: 0 auto;" />
										<input id="text30" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result30"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode31" style="text-align: center;margin: 0 auto;" />
										<input id="text31" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result31"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode32" style="text-align: center;margin: 0 auto;" />
										<input id="text32" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result32"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode33" style="text-align: center;margin: 0 auto;" />
										<input id="text33" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result33"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode34" style="text-align: center;margin: 0 auto;" />
										<input id="text34" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result34"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode35" style="text-align: center;margin: 0 auto;" />
										<input id="text35" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result35"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode36" style="text-align: center;margin: 0 auto;" />
										<input id="text36" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result36"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode37" style="text-align: center;margin: 0 auto;" />
										<input id="text37" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result37"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode38" style="text-align: center;margin: 0 auto;" />
										<input id="text38" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result38"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode39" style="text-align: center;margin: 0 auto;" />
										<input id="text39" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result39"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode40" style="text-align: center;margin: 0 auto;" />
										<input id="text40" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result40"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode41" style="text-align: center;margin: 0 auto;" />
										<input id="text41" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result41"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode42" style="text-align: center;margin: 0 auto;" />
										<input id="text42" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result42"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode43" style="text-align: center;margin: 0 auto;" />
										<input id="text43" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result43"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode44" style="text-align: center;margin: 0 auto;" />
										<input id="text44" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result44"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode45" style="text-align: center;margin: 0 auto;" />
										<input id="text45" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result45"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode46" style="text-align: center;margin: 0 auto;" />
										<input id="text46" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result46"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode47" style="text-align: center;margin: 0 auto;" />
										<input id="text47" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result47"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode48" style="text-align: center;margin: 0 auto;" />
										<input id="text48" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result48"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode49" style="text-align: center;margin: 0 auto;" />
										<input id="text49" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result49"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode50" style="text-align: center;margin: 0 auto;" />
										<input id="text50" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result50"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode51" style="text-align: center;margin: 0 auto;" />
										<input id="text51" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result51"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode52" style="text-align: center;margin: 0 auto;" />
										<input id="text52" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result52"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode53" style="text-align: center;margin: 0 auto;" />
										<input id="text53" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result53"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode54" style="text-align: center;margin: 0 auto;" />
										<input id="text54" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result54"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode55" style="text-align: center;margin: 0 auto;" />
										<input id="text55" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result55"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode56" style="text-align: center;margin: 0 auto;" />
										<input id="text56" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result56"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode57" style="text-align: center;margin: 0 auto;" />
										<input id="text57" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result57"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode58" style="text-align: center;margin: 0 auto;" />
										<input id="text58" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result58"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode59" style="text-align: center;margin: 0 auto;" />
										<input id="text59" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result59"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode60" style="text-align: center;margin: 0 auto;" />
										<input id="text60" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result60"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode61" style="text-align: center;margin: 0 auto;" />
										<input id="text61" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result61"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
														<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode62" style="text-align: center;margin: 0 auto;" />
										<input id="text62" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result62"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode63" style="text-align: center;margin: 0 auto;" />
										<input id="text63" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result63"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode64" style="text-align: center;margin: 0 auto;" />
										<input id="text64" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result64"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode65" style="text-align: center;margin: 0 auto;" />
										<input id="text65" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result65"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode66" style="text-align: center;margin: 0 auto;" />
										<input id="text66" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result66"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>
							<li>
								<div class="work-inner">
									<div class="work-item purple">
										<img id="imgcode67" style="text-align: center;margin: 0 auto;" />
										<input id="text67" type="text" value="输入部件号回车生成条形码"
											style="width:98%; margin-top:5px;border: 0px;outline:none; text-align:left;" />
										<h1 id="result67"
											style="width:100%; margin-top:5px; text-align:left;"></h1>
									</div>
								</div>
							</li>																																			
						</ul>
                    </div>
                </div>
            </div>
        </div>  
    </div>

    <div id="importExcelDlg" class="easyui-dialog" style="width:280px;height:150px;padding:10px 0px" data-options="top:10,closed:true">
		<form action='' enctype="multipart/form-data" method="post" name="fileForm"> 
			<label >
	 			<input type="file" class="file" name="filename" id="fileId" onchange="upload('${pageContext.request.contextPath}/bomTree.htm/barcodeImport')" accept=".xls"/>
			</label> 			
		</form> 
	</div> 
    
    <script type="text/javascript">
    	function download(blobUrl,fileName) {
    	//alert(fileName);
		  	const a = document.createElement('a');
		  	a.style.display = 'none';
		  	a.download = fileName+".pdf";
		  	a.href = blobUrl+"?fileName=" + fileName;
		  	a.click();
		  	document.body.removeChild(a);
		}
    	var downPdf = document.getElementById("renderPdf");
    
    	downPdf.onclick = function() {
    		downpdf();
    	/* html2canvas(document.body, {
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
    	}) */
    	}
    
    	function downpdf(fileName) {
    		html2canvas(document.body, {
    			onrendered : function(canvas) {
    
    				var contentWidth = canvas.width;
    				var contentHeight = canvas.height;
    
    				//一页pdf显示html页面生成的canvas高度;
    				var pageHeight = contentWidth / 555.28 * 841.89;
    				//未生成pdf的html页面高度
    				var leftHeight = contentHeight;
    				//pdf页面偏移
    				var position = 0;
    				//a4纸的尺寸[595.28,841.89]，html页面生成的canvas在pdf中图片的宽高
    				var imgWidth = 555.28;
    				var imgHeight = 555.28 / contentWidth * contentHeight;
    
    				var pageData = canvas.toDataURL('image/jpeg', 1.0);
    
    				var pdf = new jsPDF('', 'pt', 'a4');
    				//有两个高度需要区分，一个是html页面的实际高度，和生成pdf的页面高度(841.89)
    				//当内容未超过pdf一页显示的范围，无需分页
    				if (leftHeight < pageHeight) {
    					pdf.addImage(pageData, 'JPEG', 20, 0, imgWidth, imgHeight);
    				} else {
    					while (leftHeight > 0) {
    						pdf.addImage(pageData, 'JPEG', 20, position, imgWidth, imgHeight)
    						leftHeight -= pageHeight;
    						position -= 841.89;
    						//避免添加空白页
    						if (leftHeight > 0) {
    							pdf.addPage();
    						}
    					}
    				}
    				if(fileName==undefined){
    					fileName = 'content.pdf';
    				}
    				pdf.save(fileName);
    			}
    		})
    	}
    
    	var importExl = document.getElementById("importExl");
    	importExl.onclick = function() {
    		$('#importExcelDlg').dialog('open').dialog('setTitle', '导入表格');
    	}
    
    	function upload(url) {
    		var fileName = $('#fileId').val().split('\\'); //得到文件名数组
    		var fileSize = document.getElementById('fileId').files[0]; //获得文件大小；
    		//var fileName2 = fileName[fileName.length - 1]; // 获得文件名
    		//fileName2 = fileName2.split(".")[0];
    		//fileName2 = fileName2.replace("工单信息", "");
    		//alert(fileName2);
    		filePath = $('#fileId').val().toLowerCase().split(".");
    		fileType = filePath[filePath.length - 1]; //获得文件结尾的类型如 zip rar 这种写法确保是最后的
    		if (!(fileType == "xls")) {
    			alert('文件格式不符合要求！');
    		} else if (fileSize.size > 10485760) {
    			alert('文件超过10485760')
    		} else {
    			//alert('asdfsa');
    			$('#importExcelDlg').dialog('close');
    			var form = $("form[name=fileForm]");
    			var options = {
    				url : url, //'${pageContext.request.contextPath}/numTen.htm/exlImport', //上传文件的路径  
    				type : 'post',
    				dataType:"json", 
    				clearForm : true,
    				success : function(data) {
    					//alert(JSON.stringify(data));
    					disLoad();
    					
    					download('${pageContext.request.contextPath}/bomTree.htm/download', data[0].fileName);
    					 
    					/* for (var i = 0; i < data.length; i++) {
    						for (var key in data[i]) {
    							var sheetrows = data[i][key];
    							//alert(sheetrows.length);
    							var k = 0;
    							for (var j = 0; j < sheetrows.length; j++) {
    								if (k < 68) {
    									$("#imgcode" + k).JsBarcode(sheetrows[j].split("||")[0] + '#' + sheetrows[j].split("||")[1], {
    										textAlign : "left", //设置文本的水平对齐方式
    										fontSize : 10, //设置文本的大小
    										margin : 0, //设置条形码周围的空白边距
    										height : 50
    									});
    									document.getElementById("text" + k).value = '数量:' + sheetrows[j].split("||")[1];
    									document.getElementById("result" + k).innerText = sheetrows[j].split("||")[2];
    									k++;
    									if(j==sheetrows.length-1){
    										for(;68-k>0; k++){  				
		    									//console.log('hello');  
		    									var image = document.getElementById("imgcode" + k);	
		    									image.setAttribute("src",null);								
		    									document.getElementById("text" + k).value = '';
		    									document.getElementById("result" + k).innerText = '';
    										}   										    										 										
    									}
    									if(k==68){
    										downpdf(key +'.pdf');
    										k=0;
    									}
    								}
    							}  							
    						}
    					} */
    					
    				},
    				beforeSubmit : function() {
    					load();
    					//alert('条形码已生成');
    				},
    				complete : function() {
    					//disLoad();
    				}
    			};
    			form.ajaxSubmit(options);
    		//alert('123');
    		}
    	}
    </script>
    <script type="text/javascript">
	
	$("#text0").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text0");
			$("#imgcode0").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});

	$("#text1").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text1");
			$("#imgcode1").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
	$("#text2").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text2");
			$("#imgcode2").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
	$("#text3").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text3");
			$("#imgcode3").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text4").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text4");
			$("#imgcode4").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text5").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text5");
			$("#imgcode5").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text6").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text6");
			$("#imgcode6").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text7").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text7");
			$("#imgcode7").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text8").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text8");
			$("#imgcode8").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text9").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text9");
			$("#imgcode9").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text10").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text10");
			$("#imgcode10").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text11").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text11");
			$("#imgcode11").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text12").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text12");
			$("#imgcode12").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text13").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text13");
			$("#imgcode13").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
			$("#text14").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text14");
			$("#imgcode14").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
	$("#text15").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text15");
			$("#imgcode15").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
	$("#text16").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text16");
			$("#imgcode16").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
	$("#text17").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text17");
			$("#imgcode17").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
	$("#text18").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text18");
			$("#imgcode18").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
	$("#text19").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text19");
			$("#imgcode19").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text20").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text20");
			$("#imgcode20").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text21").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text21");
			$("#imgcode21").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text22").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text22");
			$("#imgcode22").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text23").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text23");
			$("#imgcode23").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
		$("#text24").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text24");
			$("#imgcode24").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
			$("#text25").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text25");
			$("#imgcode25").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
			$("#text26").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text26");
			$("#imgcode26").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
			$("#text27").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text27");
			$("#imgcode27").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
			$("#text28").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text28");
			$("#imgcode28").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
			$("#text29").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text29");
			$("#imgcode29").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
			$("#text30").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text30");
			$("#imgcode30").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
			$("#text31").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text31");
			$("#imgcode31").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
			$("#text32").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text32");
			$("#imgcode32").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
			$("#text33").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text33");
			$("#imgcode33").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
			$("#text34").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text34");
			$("#imgcode34").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});	
				$("#text35").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text35");
			$("#imgcode35").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});
				$("#text36").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text36");
			$("#imgcode36").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});
				$("#text37").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text37");
			$("#imgcode37").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});
				$("#text38").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text38");
			$("#imgcode38").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});
				$("#text39").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text39");
			$("#imgcode39").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});
 				$("#text40").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text40");
			$("#imgcode40").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});
				$("#text41").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text41");
			$("#imgcode41").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});
				$("#text42").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text42");
			$("#imgcode42").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});
				$("#text43").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text43");
			$("#imgcode43").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	});
				$("#text44").on("keydown", function (e) {
		if (e.keyCode == 13) {
			var elText = document.getElementById("text44");
			$("#imgcode44").JsBarcode(elText.value , {
		 		textAlign:"left",//设置文本的水平对齐方式
		 		fontSize:10,//设置文本的大小
		 		margin:5,//设置条形码周围的空白边距
		 		height:50
			}); 
		}
	}); 
</script>
</body> 
</html>
