/**
 * Created by tgigant on 8/13/15.
 */

jQuery(document).ready(function(){
    var toggleOperation = function(){
        $("li.operation").each(function(){
            if ($(this).height() > 45 ) {
                $(this).addClass("expanded");
            }
        });
        setTimeout(function(){}, 800);
        $("li.operation").each(function(){
            if ($(this).height() < 50 )  {
                $(this).removeClass("expanded");
            }
        });
    };

    $(window).on('click', ".toggleOperation", toggleOperation);
    $(window).on('click', ".collapseResource", toggleOperation);
    $(window).on('click', ".expandResource", toggleOperation);
});
