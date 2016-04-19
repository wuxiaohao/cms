<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>${baseInfo.name } -- ${channel.name}</title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/qianduan/css/pic_show.css" />
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/qianduan/js/jquery.SuperSlide.2.1.1.js"></script>
<%@ include file="common.jsp" %> 
<body>
	<jsp:include page="/jsp/template/top.jsp"/>	
	<div class="container">
		<jsp:include page="navs.jsp"/>	
		<h2 class="t_c">${top.title}</h2><hr />
		<section class="post-meta clearfix">
			<span class="author pull-left"> 作者：${top.author}&nbsp;;&nbsp;浏览次数：${top.viewCount}&nbsp;&nbsp;•&nbsp;</span>
			<time class="post-date pull-left" datetime="" title="<fmt:formatDate value="${top.publishDate }" pattern="yyyy-MM-dd HH:mm:ss"/> ">
				<fmt:formatDate value="${top.publishDate }" pattern="yyyy-MM-dd HH:mm:ss"/>
			</time>
			<!-- <span class="pull-right ">分享</span>  -->
		</section>
		<div id="play" class="col-md-12">
			<ul class="img_ul">
				<c:forEach var="img" items="${imgs }">
					<li style="display:block;">
						<a class="img_a"><img src="<%=request.getContextPath() %>/resources/picTopic/${img.picName}" title="${img.picNameOld }"></a>
					</li>
				</c:forEach>
			</ul>
			<a href="javascript:void(0)" class="prev_a change_a" title="上一张"><span></span></a>
			<a href="javascript:void(0)" class="next_a change_a" title="下一张"><span style="display:block;"></span></a>
		</div>
		<div id="news_content">

		</div>
		<div class="img_hd">
			<ul class="clearfix">
				<c:forEach var="img" items="${imgs }">
					<li style="display:block;">
						<a class="img_a"><img onload="imgs_load(this)" src="<%=request.getContextPath() %>/resources/picTopic/${img.picName}" title="${img.picNameOld }"></a>
					</li>
				</c:forEach>
			</ul>
			<a class="bottom_a prev_a" style="opacity:0.7;"></a>
			<a class="bottom_a next_a" style="opacity:0.7;"></a>
		</div>
		<div class="clearfix">
			<!-- 多说评论框 start -->
				<div class="ds-thread" data-thread-key="${vid.id }" data-title="${vid.title }" 
						data-url="<%=request.getContextPath() %>/videoNews/${vid.id}"></div>
			<!-- 多说评论框 end -->
		</div>
	</div>
	<jsp:include page="/jsp/template/bottom.jsp"/>
</body>
<script type="text/javascript">
	var i = 0; //图片标识
	var img_num = $(".img_ul").children("li").length; //图片个数
	$(".img_ul li").hide(); //初始化图片	
	play();
	$(function() {
		$(".img_hd ul").css("width", ($(".img_hd ul li").outerWidth(true)) * img_num); //设置ul的长度
		$(".bottom_a").css("opacity", 0.7); //初始化底部a透明度
		
		if (!window.XMLHttpRequest) { //对ie6设置a的位置
			$(".change_a").css("height", $(".change_a").parent().height());
		}
		$(".change_a").focus(function() {
			this.blur();
		});
		$(".bottom_a").hover(function() { //底部a经过事件
			$(this).css("opacity", 1);
		}, function() {
			$(this).css("opacity", 0.7);
		});
		$(".change_a").hover(function() { //箭头显示事件
			$(this).children("span").show();
		}, function() {
			$(this).children("span").hide();
		});
		$(".img_hd ul li").click(function() {
			i = $(this).index();
			play();
		});
		$(".prev_a").click(function() {
			//i+=img_num;
			i--;
			//i=i%img_num;
			i = (i < 0 ? 0 : i);
			play();
		});
		$(".next_a").click(function() {
			i++;
			//i=i%img_num;
			i = (i > (img_num - 1) ? (img_num - 1) : i);
			play();
		});
	});
	
	function play() { //动画移动	
		var img = new Image(); //图片预加载
		img.onload = function() {
			img_load(img, $(".img_ul").children("li").eq(i).find("img"))
		};
		img.src = $(".img_ul").children("li").eq(i).find("img").attr("src");
		img.title=$(".img_ul").children("li").eq(i).find("img").attr("title");
		$("#news_content").html(img.title);
	
		$(".img_hd ul").children("li").eq(i).addClass("on").siblings().removeClass("on");
		if (img_num > 7) { //大于7个的时候进行移动
			if (i < img_num - 3) { //前3个
				$(".img_hd ul").animate({
					"marginLeft": (-($(".img_hd ul li").outerWidth() + 4) * (i - 3 < 0 ? 0 : (i - 3)))
				});
			} else if (i >= img_num - 3) { //后3个
				$(".img_hd ul").animate({
					"marginLeft": (-($(".img_hd ul li").outerWidth() + 4) * (img_num - 7))
				});
			}
		}
		if (!window.XMLHttpRequest) { //对ie6设置a的位置
			$(".change_a").css("height", $(".change_a").parent().height());
		}
	}
	
	function img_load(img_id, now_imgid) { //大图片加载设置 （img_id 新建的img,now_imgid当前图片）
		if (img_id.width / img_id.height > 1) {
			if (img_id.width >= $("#play").width())
				$(now_imgid).width($("#play").width());
		} else {
			if (img_id.height >= 500) $(now_imgid).height(500);
		}
		$(".img_ul").children("li").eq(i).show().siblings("li").hide(); //大小确定后进行显示
	}
	
	function imgs_load(img_id) { //小图片加载设置
		if (img_id.width >= $(".img_hd ul li").width()) {
			img_id.width = 80
		};
	}
</script>
<!-- JiaThis Button BEGIN -->
<script type="text/javascript" src="http://v3.jiathis.com/code/jiathis_r.js?move=0" charset="utf-8"></script>
<!-- JiaThis Button END -->
<!-- 多说公共JS代码 start (一个网页只需插入一次) -->
<script type="text/javascript">
var duoshuoQuery = {short_name:"testwxh"};
	(function() {
		var ds = document.createElement('script');
		ds.type = 'text/javascript';ds.async = true;
		ds.src = (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//static.duoshuo.com/embed.js';
		ds.charset = 'UTF-8';
		(document.getElementsByTagName('head')[0] 
		 || document.getElementsByTagName('body')[0]).appendChild(ds);
	})();
	</script>
<!-- 多说公共JS代码 end -->
</html>
