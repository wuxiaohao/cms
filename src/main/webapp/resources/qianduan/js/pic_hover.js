$(function() {
	//图片新闻hover效果
	$("a.thumbnail").each(function() {
		$(this).css('text-decoration', 'none');
		$(this).hover(function() {
			$(this).children("img").animate({
				opacity: 0.8,
			});
			$(this).children("div").css("color", "orangered");
		}, function() {
			$(this).children("img").animate({
				opacity: 1,
			});
			$(this).children("div").css("color", "#000");
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
	//视频播放hover
	$('.videolist li').hover(function() {
		$(this).css('background', '#5E5E5E').children('a').children('p').css('color', '#fbc91b');
	}, function() {
		$(this).css('background', '#000').children('a').children('p').css('color', '#fff');
	});
});