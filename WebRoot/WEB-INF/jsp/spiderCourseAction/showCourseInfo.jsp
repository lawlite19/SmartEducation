<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>课程信息</title>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/course.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/h_index.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/master.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/tm.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/comm.css" />

<script language="javascript" src="${pageContext.request.contextPath}/script/jquery-2.0.0.min.js"></script>
<style type="text/css">
			pre {white-space: pre-wrap;       
				/* css-3 */white-space: -moz-pre-wrap;  
				/* Mozilla, since 1999 */white-space: -pre-wrap;      
				/* Opera 4-6 */white-space: -o-pre-wrap;    
				/* Opera 7 */word-wrap: break-word;       
				/* Internet Explorer 5.5+ */
				font-size:14px;
			} 
			
			.zev_content p{word-wrap: break-word;word-break:break-all;}
</style>
<style type="text/css">
	html,body {
		margin: 0px;
		padding: 0px;
		height: 100%;
	}
	
	.ans-book {
		position: relative;
		line-height: 22px;
		clear: both;
	}
	
	.ans-book,.ans-book span {
		display: block;
		text-indent: 0;
	}
	
	.ans-book-cover img {
		width: 68px;
		margin: 0;
	}
	
	.ans-book::foucs {
		background-color: #ccc;
	}
	
	.ans-book .ans-ref-bookname {
		margin-top: 5px;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		display: block;
		width: 44%;
	}
	
	.ans-book .ans-ref-bookname a {
		color: #666666;
		text-decoration: none;
	}
	
	.ans-book .ans-book-info {
		position: absolute;
		top: 0;
		left: 85px;
		width: 100%;
	}
	
	.ans-book .ans-ref-author,.ans-book .ans-ref-publish {
		font-size: 12px;
		color: #999;
	}
  </style>
</head>
<body>
<div class="constr bgwh fix mb46">
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
							<a href="${url}" class="as-ref-link" id="ext-gen1037">
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
