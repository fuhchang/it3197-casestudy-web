$(function (){
	$(window).on('resize load', function() {
	    $('body').css({"padding-top": $(".navbar").height() + "px"});
	});
	
	$('#tabs a:first').tab('show');
	
	$('#tabs a').click(function (e){
		$(this).tab('show');
	});
});