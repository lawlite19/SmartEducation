<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>课程信息</title>
<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-2.0.0.min.js"></script>
<!-- layer弹窗插件 -->
<script src="${pageContext.request.contextPath}/layer/layer.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/course.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/h_index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/master.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/tm.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/comm.css" />
<style type="text/css">
			pre {white-space: pre-wrap;       
				/* css-3 */white-space: -moz-pre-wrap;  
				/* Mozilla, since 1999 */white-space: -pre-wrap;      
				/* Opera 4-6 */white-space: -o-pre-wrap;    
				/* Opera 7 */word-wrap: break-word;       
				/* Internet Explorer 5.5+ */
				font-size:14px;
			}
			.span_text{
				font-size:16px;
				color:black;
			} 
			.div_favorite{
				float:right;
				vertical-align: middle
			}
			.zev_content p{word-wrap: break-word;word-break:break-all;}
</style>
<!-- 收藏ajax请求 -->
<script>
		function ajaxFavorite(baseUrl,courseId){
			layer.msg('处理中', {icon: 16});
			$.ajax({ 
   	    		type: "post",
   	    		url: "favorite_add.action", 
   	    		data: {
   	    			"courseId" : courseId
	    			}, 
   	    		dataType : "json",
   	    		async : false,
                success: function(data) { 
                	var str = data.name;
					  if (str=="favorOK") {
						layer.msg('收藏成功', {time: 2000, icon:6});
						var onclickFunction="ajaxCancleFavorite('"+baseUrl+"','"+courseId+"');";
						$("#a_favorite").remove();
						$("#div_favorite").append("<a id='a_cancleFavorite' onclick="+onclickFunction+" href='#'> <div class='div_favorite'> <img id='img_favorite'  alt='已收藏' src="+baseUrl+"/style/images/ic_action_favor_on_pressed.png width='40px' height='40px' style='margin-bottom: -13px'/> <span class='span_text'> 已收藏 </span> </div> </a>");
    				  } else {
    					layer.msg('服务器错误，收藏失败',{time: 2000, icon:5});
    				}
   	    		} 
   	       }); 
		}
</script>
<!-- 取消收藏ajax -->
<script type="text/javascript">
	function ajaxCancleFavorite(baseUrl,courseId){
		layer.msg('处理中', {icon: 16});
		$.ajax({ 
	    		type: "post",
	    		url: "favorite_delete.action", 
	    		data: {
	    			"courseId" : courseId
    			}, 
	    		dataType : "json",
	    		async : false,
            success: function(data) { 
            	 //var json = eval("(" + data + ")");
				 var str = data.name;
					  if (str=="canclefavorOK") {
						layer.msg('取消成功', {time: 2000, icon:6});
						var onclickFunction="ajaxFavorite('"+baseUrl+"','"+courseId+"');";
						$("#a_cancleFavorite").remove();
						$("#div_favorite").append("<a id='a_favorite' onclick="+onclickFunction+" href='#'> <div class='div_favorite'> <img id='img_favorite'  alt='已收藏' src="+baseUrl+"/style/images/ic_action_favor_pressed.png width='40px' height='40px' style='margin-bottom: -13px'/> <span class='span_text'> 收藏 </span> </div> </a>");
      				  } else {
      					layer.msg('服务器错误，取消失败',{time: 2000, icon:5});
      				}
	    		} 
	       }); 
	}
</script>
</head>
<body>
<div class="constr bgwh fix mb46">
<div class="pct66 l pb40" id="div_favorite">
<!-- 课程名称 -->
   <div class="mt10 f23 g5 courseName">
                <span>${courseName}</span>
   </div>
<!-- 收藏按钮 -->

<s:if test="#favorFind!=null">
<a id="a_cancleFavorite" onclick="ajaxCancleFavorite('${pageContext.request.contextPath}','${courseId}');" href="#" >	
	<div class="div_favorite">
			<img id="img_favorite"  alt="已收藏" src="${pageContext.request.contextPath}/style/images/ic_action_favor_on_pressed.png" width="40px" height="40px"
			style="margin-bottom: -13px"
			/>
			<span class="span_text">已收藏</span>
	</div>
</a>
</s:if>
<s:else>
<a id="a_favorite" onclick="ajaxFavorite('${pageContext.request.contextPath}','${courseId}');" href="#" >	
	<div class="div_favorite">
			<img id="img_favorite"  alt="收藏" src="${pageContext.request.contextPath}/style/images/ic_action_favor_pressed.png" width="40px" height="40px"
			style="margin-bottom: -13px"
			/>
			<span class="span_text">收藏</span>
	</div>
</a>
</s:else>
</div>

<!-- 课程介绍 -->
	<div class="pct66 l pb40">
	<div class="ml30 mr30">
	<s:if test="courseDesc!=null">
		<div class="mt30 pt20">
		<div id="courseArticle_96816" class="courseArticle f20 mb10  pb5 bbc ">课程介绍</div>
		<div class="pl20">
           <div class="mt5 ans-cc">
           	${courseDesc}
           </div>
        </div>
		</div>
	</s:if>	
		<!-- 课程参考教材 -->
				<div class="mt30 pt20">
					<div id="courseArticle_97365" class="courseArticle f20 mb10  pb5 bbc ">参考教材</div>
					<div class="mt5 ans-cc">
						<s:iterator value="documentFind">
							<span class="ans-book"><span class="ans-book-cover">
							<a href="${url}"  class="as-ref-link" id="ext-gen1037">
								<img src="${imgUrl}"></a></span><span class="ans-book-info">
								<span class="ans-ref-bookname"><a href="${url}" class="as-ref-link" id="ext-gen1038">
									${name}
								</a>
								</span>
								<span class="ans-ref-author">${author}</span>
								<span class="ans-ref-publish">${publisher}&nbsp;${publishDate}</span>
								</span>
							</span>
							<br/><br/>
						</s:iterator>
					</div>
				</div>
			</div>
	</div>


<!-- 课程章节 -->

	<div class="cell bgblue wh">
		<div id="courseUnit" class="courseArticle mt25 pb20 f24 wh tc">课程章节</div>
		<s:iterator value="chapterList" var="chapter" status="s">
		
		<div class="p20 btdwh fix">
			<div class="wh_cricle_l mr20 l cblue chapter_index">${s.count}</div>
			<div class="cell">
					<div class="f16">${courseName}</div>
				<ul class="mt10">
					
						<li class="mb15 course_section fix">
						<a class="wh" href="${url}" target="_blank">
							<div class="f16 chapter_index l">${chapterNum}</div>
							<div class="f16 pct80 pr10 r">${chapterName}</div>
						</a>
						</li>
					
				</ul>
			</div>
		</div>
		</s:iterator>
	</div>

</div>
</body>
</html>
