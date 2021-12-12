function loadslider(slide){
	$.ajax({
  url: "/moviesintheatre",
  method: "get",
  dataType: "json"
}).done(function(data) {
  var ulhtml="";
  $.each( data.results, function( index, movie ) {
	if(movie.poster_path!=null){
		ulhtml=ulhtml+"<li><a href=\"#\"><img height=200 src=\"https://image.tmdb.org/t/p/w200"+movie.poster_path+"\" alt=\"Slide "+(index+1)+"\"></a></li>";
	}
	
	});
	$(slide).html(ulhtml);
	$(".slider").flexslider({
			controlNav: false,
			prevText:'<i class="fa fa-chevron-left"></i>',
			nextText:'<i class="fa fa-chevron-right"></i>',
		});
});
	
	
	
}