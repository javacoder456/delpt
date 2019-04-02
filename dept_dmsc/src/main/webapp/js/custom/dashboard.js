jQuery(document).ready(function(){
	
		jQuery('#overviewselect, input:checkbox').uniform();
		
		///// DATE PICKER /////
		jQuery( "#datepickfrom, #datepickto" ).datepicker();
		
		///// SLIM SCROLL /////
		jQuery('#scroll1').slimscroll({
			color: '#666',
			size: '10px',
			width: 'auto',
			height: '175px'                  
		});
		
		///// ACCORDION /////
		jQuery('#accordion').accordion({autoHeight:  false});
	
		/*需动态生成实际数据集 可使用ajax异步获取数据*/
		var data1 = [[0, 21], [1, 62], [2,33], [3, 81], [4, 55], [5, 133], [6, 185],
		             [7, 55], [8, 43], [9,28], [10, 110], [11, 99], [12, 101], [13, 109],
		             [14, 55], [15, 43], [16,58], [17, 60], [18, 99], [19, 105], [20, 119],
		             [20, 55], [21, 43], [22,48], [23, 110], [24, 99]];
		var data2 = [[0, 55], [1, 43], [2,48], [3, 110], [4, 99], [5, 105], [6, 139],
		             [7, 21], [8, 62], [9,33], [10, 41], [11, 55], [12, 133], [13, 185],
		             [14, 21], [15, 62], [16,33], [17, 55], [18, 55], [19, 83], [20, 125],
		             [21, 121], [22, 142], [23,133], [24, 151]];
			
		function showTooltip(x, y, contents) {
			jQuery('<div id="tooltip" class="tooltipflot">' + contents + '</div>').css( {
				position: 'absolute',
				display: 'none',
				top: y + 5,
				left: x + 5
			}).appendTo("body").fadeIn(200);
		}
	
			
		var plot = jQuery.plot(jQuery("#chartplace"),
			   [ { data: data1, label: "剩余车位(L)", color: "#069"}, { data: data2, label: "停车时间(T)", color: "#FF6600"} ], 
			   
			   {
				   series: {
					   lines: { show: true, fill: true, fillColor: { colors: [ { opacity: 0.05 }, { opacity: 0.10 } ] } },
					   points: { show: true }
				   },
				   legend: { position: 'nw'},
				   grid: { hoverable: true, clickable: true, borderColor: '#ccc', borderWidth: 1, labelMargin: 10 },
				   
				   xaxis: { ticks: [0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24], 
                   }, 
				   yaxis: { min: 0, max: 200 }
				 });
		
		var previousPoint = null;
		jQuery("#chartplace").bind("plothover", function (event, pos, item) {
			jQuery("#x").text(pos.x.toFixed(2));
			jQuery("#y").text(pos.y.toFixed(2));
			
			if(item) {
				if (previousPoint != item.dataIndex) {
					previousPoint = item.dataIndex;
						
					jQuery("#tooltip").remove();
					var x = item.datapoint[0].toFixed(2),
					y = item.datapoint[1].toFixed(2);
						
					showTooltip(item.pageX, item.pageY,
									item.series.label + " of " + x + " = " + y);
				}
			
			} else {
			   jQuery("#tooltip").remove();
			   previousPoint = null;            
			}
		
		});
		
		jQuery("#chartplace").bind("plotclick", function (event, pos, item) {
			if (item) {
				//jQuery("#clickdata").text("You clicked point " + item.dataIndex + " in " + item.series.label + ".");
				plot.highlight(item.series, item.datapoint);
			}
		});
		
		
	///// SWITCHING LIST FROM 3 COLUMNS TO 2 COLUMN LIST /////
	function rearrangeShortcuts() {
		if(jQuery(window).width() < 430) {
			if(jQuery('.shortcuts li.one_half').length == 0) {
				var count = 0;
				jQuery('.shortcuts li').removeAttr('class');
				jQuery('.shortcuts li').each(function(){
					jQuery(this).addClass('one_half');
					if(count%2 != 0) jQuery(this).addClass('last');
					count++;
				});	
			}
		} else {
			if(jQuery('.shortcuts li.one_half').length > 0) {
				jQuery('.shortcuts li').removeAttr('class');
			}
		}
	}
	
	rearrangeShortcuts();
	
	///// ON RESIZE WINDOW /////
	jQuery(window).resize(function(){
		rearrangeShortcuts();
	});


});
