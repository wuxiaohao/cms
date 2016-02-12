$(function() {
	//图片新闻hover效果
	$("a.thumbnail").each(function() {
		$(this).css('text-decoration', 'none');
		$(this).hover(function() {
			$(this).children("img").animate({
				opacity: 0.8,
			});
			$(this).children("p").css("color", "orangered");
		}, function() {
			$(this).children("img").animate({
				opacity: 1,
			});
			$(this).children("p").css("color", "#000");
		});
	});
	//轮播自动播放
	$('#myCarousel').carousel({
		//自动4秒播放
		interval: 4000,
	});
	//返回顶部
	$(window).scroll(function(e) {
		if ($(document).scrollTop() > 403) {
			$("#back-to-top").css({
				"display": "inline"
			});
		} else {
			$("#back-to-top").css({
				"display": "none"
			});
		}
	});
	//导航栏固定
	$(window).on("scroll", function() {
	if ($(window).scrollTop() > 1) {
		$("#top").addClass("topnone")
	} else {
		$("#top").removeClass("topnone")
	}
});
});