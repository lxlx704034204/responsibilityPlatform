(function (window, undefined) {
	/*
	 * detect dependencies.
	 * */
    (function () {
        if ((typeof $) == "undefined") {
            alert("Failed to find the dependency object: jquery.");
        }
    })();
    (function () {
        if ((typeof JSON) == "undefined") {
            alert("Failed to find the dependency object: JSON.");
        }
    })();
    /*
     * construct main object.
     * 
     * */
    var jsless = (function () {
        var jsless = function () { };
        jsless.fn = jsless.prototype = {
            constructor: jsless
        };
        jsless.extend = jsless.fn.extend = function () {
            var length = arguments.length,
                baseObj = this,
                source;
            for (var i = 0; i < length; i++) {
                source = arguments[i];
                for (var memberName in source) {
                    if (baseObj[memberName] == undefined) {
                        baseObj[memberName] = source[memberName];
                    }
                    else {
                        throw new Error("Failed to extend jsless: member[" + 
                        		memberName + "] is already existed.");
                    }
                }
            }
        };
        return jsless;
    })();
    window.jsless = jsless;
})(window);

/*
 * dialog window
 * */
jsless.extend({
	/*
	 * define base alert.
	 * */
	alert: function(msg){
		window.alert(msg);
	}
});

/*
 * debug
 * */
jsless.extend({
	
	getConsole: function(){
		if(jsless.console == undefined || jsless.console == null){
			if(window.console != undefined && window.console != null){
				jsless.console = window.console;
			}else{
				jsless.console = {
					log: function(msg){},
					dir: function(obj){},
					info: function(msg){},
					debug: function(msg){},
					warn: function(msg){},
					error: function(msg){},
					assert: function(boolcondition){}
				};
			}
		}
		return jsless.console;
	},
	showLog: true,
	log: function(msg){
		if(jsless.showLog == true){
			var con = jsless.getConsole();
			if(con.log){
				con.log(msg);
			}
		}
	},
	showDir: true,
	dir: function(obj){
		if(jsless.showDir == true){
			var con = jsless.getConsole();
			if(con.dir){
				con.dir(obj);
			}
		}
	},
	showInfo: true,
	info: function(msg){
		if(jsless.showInfo == true){
			var con = jsless.getConsole();
			if(con.info){
				con.info(msg);
			}
		}
	},
	showDebug: true,
	debug: function(msg){
		if(jsless.showDebug == true){
			var con = jsless.getConsole();
			if(con.debug){
				con.debug(msg);
			}
		}
	},
	showWarn: true,
	warn: function(msg){
		if(jsless.showWarn == true){
			var con = jsless.getConsole();
			if(con.warn){
				con.warn(msg);
			}
		}
	},
	showError: true,
	error: function(msg){
		if(jsless.showError == true){
			var con = jsless.getConsole();
			if(con.error){
				con.error(msg);
			}
		}
	}
});
/*
 * html
 * */
jsless.extend({
	htmlctrls: {
		a: "<a></a>",
		span: "<span></span>",
		li: "<li></li>",
		label: "<label></label>",
		ul: "<ul></ul>",
		ol: "<ol></ol>",
		div: "<div></div>",
		table: "<table></table>",
		tr: "<tr></tr>",
		th: "<th></th>",
		td: "<td></td>",
		select: "<select></select>",
		option: "<option></option>",
		input: "<input />",
		img: "<img />",
		dl: "<dl></dl>",
		dt: "<dt></dt>",
		dd: "<dd></dd>",
		p: "<p></p>"
	},
	getA: function(text,href){
		var a = $(jsless.htmlctrls.a);
		if(text != undefined && text != null){
			a.text(text);
		}
		if(href != undefined && href != null){
			a.attr("href", href);
		}
		return a;
	},
	getSpan: function(text){
		var span = $(jsless.htmlctrls.span);
		if(text != undefined && text != null){
			span.text(text);
		}
		return span;
	},
	getLi: function(){
		return $(jsless.htmlctrls.li);
	},
	getUl: function(){
		return $(jsless.htmlctrls.ul);
	},
	getOl: function(){
		return $(jsless.htmlctrls.ol);
	},
	getDiv: function(){
		return $(jsless.htmlctrls.div);
	},
	getTable: function(){
		return $(jsless.htmlctrls.table);
	},
	getTr: function(){
		return $(jsless.htmlctrls.tr);
	},
	getTh: function(){
		return $(jsless.htmlctrls.th);
	},
	getTd: function(){
		return $(jsless.htmlctrls.td);
	},
	getSelect: function(optsdata){
		var slt = $(jsless.htmlctrls.select);
		if(optsdata != undefined && optsdata != null){
			$.each(optsdata, function(i,v){
				var opt = jsless.getOption(v.text,v.value);
				slt.append(opt);
			});
		}
		return slt;
	},
	getOption: function(text,val){
		var opt = $(jsless.htmlctrls.option);
		if(text != undefined && text != null){
			opt.text(text);
		}
		if(val != undefined && val != null){
			opt.attr("value",val);
		}
		return opt;
	},
	getInput: function(){
		return $(jsless.htmlctrls.input);
	},
	getImg: function(src){
		var img = $(jsless.htmlctrls.img);
		if(src != undefined && src != null){
			img.attr("src", src);
		}
		return img;
	},
	getDl: function(){
		return $(jsless.htmlctrls.dl);
	},
	getDt: function(){
		return $(jsless.htmlctrls.dt);
	},
	getDd: function(){
		return $(jsless.htmlctrls.dd);
	},
	getLabel: function(){
		return $(jsless.htmlctrls.label);
	},
	getP: function(){
		return $(jsless.htmlctrls.p);
	}
});

/*
 * ajax
 * */
jsless.extend({
	ajax: function(params){
		var options = {
			url: null,
			data: null,
			success: null,
			error: null
		};
		$.extend(options, params);
		if(options.url == undefined || options.url == null || options.url == ""){
			jsless.error("the request url of ajax can not be null.");
			return;
		}
		return $.ajax({
			cache: false,
            contentType: "text/html; charset=UTF-8",
            url:options.url,
            data:JSON.stringify(options.data),
            type:"POST",
            dataType:"json",
            error: function (jqXHR, textStatus, errorThrown) {
            	if(options.error != null){
            		options.error(jqXHR,textStatus,errorThrown);
            	}else{
            		jsless.error(textStatus);
            		jsless.error(errorThrown);
            	}
            },
            success:function(response){
            	options.success(response);
            }
		});
	}
});

/**
 * events
 * */
jsless.extend({
	/**
	 * 如果不符合条件ifCondition，就执行otherwiseDo
	 * 
	 * */
	windowClick:function(ifCondition, otherwiseDo){
		$(document).click(function(event) {
            var e = event || window.event;
            var elem = e.srcElement || e.target;
            while (elem) {
                if (ifCondition(elem)) {
                    return;
                }
                elem = elem.parentNode;
            }
            otherwiseDo();
        });
	},
	windowResize: function(func){
		$(window).resize(function(){
        	func();
        });
	},
	windowScroll: function(func){
		$(".site-background").scroll(function(){
			func();
		});
	},
	rightClick: function(targetSelector, func){
		$(document).bind("contextmenu",function(e){
			return false;
	    });
		$(targetSelector).unbind("mousedown").mousedown(function(e){
			if(e.which == 3){
				func(e);
			}
		});
	}
});

/**
 * create menu
 * */
jsless.extend({
	createContextMenu: function(params){
		var options = {
				menuitems:{},
				left:0,
				top:0
		};
		$.extend(options,params);
		
		var menuDivHtml = "<div class='contextmenu'></div>";
		var menuUlHtml = "<ul></ul>";
		var menuitemLiHtml = "<li></li>";
		var menuitemAHtml = "<a href='javascript:void(0);'></a>";
		var menuitemHrHtml = "<hr/>";
		
		var menuDiv = $(menuDivHtml);
		var menuUl = $(menuUlHtml);
		for(var menuitem in options.menuitems){
			var menuitemLi = $(menuitemLiHtml);
			if(menuitem.toLowerCase() == "<hr/>"){
				$(menuitemHrHtml).appendTo(menuitemLi);
			}else{
				var menuitemA = $(menuitemAHtml);
				menuitemA.text(menuitem);
				menuitemA.click(options.menuitems[menuitem]);
				menuitemA.appendTo(menuitemLi);
			}
			menuitemLi.appendTo(menuUl);
		}
		menuDiv.append(menuUl);
		menuDiv.css("left", options.left - 170 + "px");
		menuDiv.css("top", options.top + 10 + "px");
		
		return menuDiv;
	},
	contextMenu: function(params){
		var options = {
				menuitems:{},
				left:0,
				top:0
		};
		$.extend(options,params);
		
		var cm = jsless.createContextMenu(options);
		
	}
});

/**
 * 与string操作相关
 * */
jsless.extend({
	isNullOrEmpty: function(str){
		return str == null || $.trim(str).length == 0;
	}
});

/**
 * 与Knockout相关
 * */
jsless.extend({
	toObservable: function(srcobj){
		var target = {};
		if(ko != undefined && ko != null){
			for(var propname in srcobj){
				var propval = srcobj[propname];
				if(typeof(propval) == "string"){
					target[propname] = ko.observable(propval);
				}else if(typeof(propval) == "number"){
					target[propname] = ko.observable(propval);
				}else if(typeof(propval) == "boolean"){
					target[propname] = ko.observable(propval);
				}else if(typeof(propval) == "undefined"){
					//...
				}else if(typeof(propval) ==  "object"){
					//target[propname] = ko.observable(propval);
					if(propval instanceof Array){
						target[propname] = ko.observableArray(propval);
					}else if(propval instanceof Date){
						target[propname] = ko.observable(propval);
					}else{
						target[propname] = ko.observable(propval);
					}
				}else{
					target[propname] = propval;
				}
			}
		}
		return target;
	},
	toPlainObject: function(srcobj){
		var target = {};
		if(ko != undefined && ko != null){
			for(var propname in srcobj){
				var prop = srcobj[propname];
				if(prop instanceof Function){
					target[propname] = prop();
				} else{
					target[propname] = prop;
				}
			}
		}
		return target;
	}
});

jsless.extend({
	showloading: function(tip){
		var text = tip != undefined ? tip : "加载中...";
		var loadingwrapper = "<div class='loadingwrapper'>"+text+"</div>";
		$(document.body).append(loadingwrapper);
	},
	hideloading: function(){
		$(document.body).find(".loadingwrapper").remove();
	},
	appendLargeLoadingTo: function(target){
		var largeloadingwrapper = $("<div class='largeloadingwrapper'></div>");
		target.append(largeloadingwrapper);
	},
	removeLargeLoadingFrom: function(target){
		target.find(".largeloadingwrapper").remove();
	},
	showProgress: function(){
		var progresswrapper = $("<div class='progresswrapper' style='position:fixed;top:0;left:0;right:0;height:4px;z-index:10000;'><div class='progress' style='margin:0;'><div class='indeterminate'></div></div></div>");
		progresswrapper.attr("id", "_progresswrapper_" + $(".progresswrapper").size());
		$(window.top.document.body).append(progresswrapper);
	},
	hideProgress: function(){
		$(window.top.document.body).find(".progresswrapper").remove();
	}
});

/*
*   与时间类型有关的方法
*/
jsless.extend({
	parseDate: function(datestr){
		return new Date(datestr.replace(/-/g, "/")); 
	},
	parseJsonDate: function(datestr){
		if(datestr == null || datestr == ""){
			return null;
		}
		var sections = datestr.split(" ");
		var dateSection = sections[0];
		var timeSection = sections.length > 1 ? sections[1] : "00:00:00";
		var datePhrases = dateSection.split("-");
		var timePhrases = timeSection.split(":");
		var year = parseInt(datePhrases[0]);
		var month = parseInt(datePhrases[1]);
		var day = parseInt(datePhrases[2]);
		var hour = parseInt(timePhrases[0]);
		var minute = parseInt(timePhrases[1]);
		var second = parseInt(timePhrases[2]);
		var date = new Date();
		date.setFullYear(year);
		date.setMonth(month - 1);
		date.setDate(day);
		date.setHours(hour);
		date.setMinutes(minute);
		date.setSeconds(second);
		date.time = date.getTime();
		return date;
	},
	getShortDateString:function(date){
        if(date == null || date.time == null)
        {
            return "---";
        }
        var realDate = new Date(date.time);
        var month = realDate.getMonth()+1;
        var day = realDate.getDate();
        if(month<10)
        {
            month = "0"+month;
        }
        if(day < 10)
        {
            day = "0"+day;
        }
        return realDate.getFullYear() + "-" + month + "-" + day;
    },
    getDateTime: function (ticks) {
        var regExp = /\/Date\(([0-9]+)(\+0800)?\)\//gi;
        var match = regExp.exec(ticks);
        return new Date(parseInt(match[1]));
    },
    formatDateTime: function (datetime, fmt) {
    	var o = {
	        "M+": datetime.getMonth() + 1, //月份 
	        "d+": datetime.getDate(), //日 
	        "h+": datetime.getHours(), //小时 
	        "m+": datetime.getMinutes(), //分 
	        "s+": datetime.getSeconds(), //秒 
	        "q+": Math.floor((datetime.getMonth() + 3) / 3), //季度 
	        "S": datetime.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) {
	    	fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
	    }
	    for (var k in o){
	    	if (new RegExp("(" + k + ")").test(fmt)) {
	    		fmt = fmt.replace(RegExp.$1, 
	    				(RegExp.$1.length == 1) ? 
	    						(o[k]) : 
	    						(("00" + o[k]).substr(("" + o[k]).length)))
	    	};
	    }
	    
	    return fmt;
    },
    getISODateString: function(date){
    	return jsless.formatDateTime(date,"yyyy-MM-dd");
    },
    getUrlParam : function(name){
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r!=null) return unescape(r[2]); return null;
	},
    getQueryString: function (parameterName) {
        var Request = {
            QueryString: function (paramName) {
                var paramValue = location.search.match(new RegExp("[\?\&]" + paramName + "=([^\&]*)(\&?)", "i"));
                return paramValue ? paramValue[1] : paramValue;
            }
        }
        return Request.QueryString(parameterName);
    },
    getLocalTimezoneTicks: function (dateTime) {
        var ticksTo19700101 = 621355968000000000;
        return dateTime.valueOf() * 10000 + ticksTo19700101;
    },
    getUTCTicks: function (dateTime) {
        var ticksTo19700101 = 621355968000000000;
        return (dateTime.valueOf() - dateTime.getTimezoneOffset() * 60 * 1000) * 10000 + ticksTo19700101;
    }
});

jsless.extend({
	formatCurrency: function(numeric, digitsCount){
		if(numeric == null || numeric == ""){
			return numeric;
		}
		if(digitsCount < 0){
			return numeric;
		}
		var num_f = numeric.toString().replace(/[$¥€,]/g, '')
		var sections = num_f.split('.');
		var pre_sec = sections[0];
		var post_sec = sections.length == 2 ? sections[1] : "";
		
		
		var getPreText = function(str){
			var groupTexts = [];
			var firstGroupLength = str.length % 3;
			var otherGroupsCount = (str.length - firstGroupLength) / 3;
			if(firstGroupLength > 0){
				var firstGroupText = str.substr(0, firstGroupLength);
				groupTexts.push(firstGroupText);
			}
			var otherGroupStartIndex = firstGroupLength;
			for(var i = 0; i < otherGroupsCount; i++){
				var groupText = str.substr(otherGroupStartIndex, 3);
				groupTexts.push(groupText);
				otherGroupStartIndex += 3;
			}
			return groupTexts.join(",");
		};
		
		var getPostText = function(str, digitsCount){
			if(str.length >= digitsCount){
				return str.substr(0, digitsCount);
			} else {
				var zeros = [];
				for(var i = 0; i < (digitsCount - str.length); i++){
					zeros.push('0');
				}
				return str + zeros.join('');
			}
		};
		
		var pre_text = getPreText(pre_sec);

		var text = '';
		if(digitsCount > 0){
			text += pre_text;
			text += '.';
			text += getPostText(post_sec, digitsCount);
		} else {
			text += pre_text;
		}
		return text;
	},
	formatNumber: function(numeric){
		if(numeric == null || numeric == ""){
			return numeric;
		}
//		if(digitsCount < 0){
//			return numeric;
//		}
		var num_f = numeric.toString().replace(/[$¥€,]/g, '')
		var sections = num_f.split('.');
		var pre_sec = sections[0];
		var post_sec = sections.length == 2 ? sections[1] : "";
		
		
		var getPreText = function(str){
			var groupTexts = [];
			var firstGroupLength = str.length % 3;
			var otherGroupsCount = (str.length - firstGroupLength) / 3;
			if(firstGroupLength > 0){
				var firstGroupText = str.substr(0, firstGroupLength);
				groupTexts.push(firstGroupText);
			}
			var otherGroupStartIndex = firstGroupLength;
			for(var i = 0; i < otherGroupsCount; i++){
				var groupText = str.substr(otherGroupStartIndex, 3);
				groupTexts.push(groupText);
				otherGroupStartIndex += 3;
			}
			return groupTexts.join(",");
		};
		
		var getPostText = function(str, digitsCount){
			if(str.length >= digitsCount){
				return str.substr(0, digitsCount);
			} else {
				var zeros = [];
				for(var i = 0; i < (digitsCount - str.length); i++){
					zeros.push('0');
				}
				return str + zeros.join('');
			}
		};
		
		var pre_text = getPreText(pre_sec);

//		var text = '';
//		if(digitsCount > 0){
//			text += pre_text;
//			text += '.';
//			text += getPostText(post_sec, digitsCount);
//		} else {
//			text += pre_text;
//		}
		return pre_text;
	}
});

/*
*   与页面有关的方法
*/
jsless.extend({
    /*
    *   获取框架内部的 document 对象
    */
    getFrameDocument: function (targetWindow, iframeID) {
        var doc = targetWindow.document;
        return doc.getElementById(iframeID).contentDocument || doc.frames[iframeID].document;
    },

    /* 
    *   获取当前窗体页面大小
    */
    getWindowSize: function (targetWindow) {
        var win = targetWindow != undefined ? targetWindow : window;
        // FF,Safari,Opera,Chrome 
        var pageHeight = win.innerHeight, pageWidth = win.innerWidth;
        if (typeof pageWidth != "number") {
            // IE6
            if (win.document.compatMode == "CSS1Compat") {
                pageHeight = win.document.documentElement.clientWidth;
                pageWidth = win.document.documentElement.clientWidth;
            }
            else {
                // IE7+
                pageHeight = win.document.body.clientHeight;
                pageWidth = win.document.body.clientWidth;
            }
        }
        return { height: pageHeight, width: pageWidth };
    },
    requestFullScreen: function(elem){
    	
    	var requestMethod = elem.requestFullScreen || 
    		elem.webkitRequestFullScreen || 
    		elem.mozRequestFullScreen || 
    		elem.msRequestFullScreen;
    	
        if (requestMethod) {      
            requestMethod.call(elem);    
        } else if (typeof window.ActiveXObject !== "undefined") {      
            var wscript = new ActiveXObject("WScript.Shell");    
            if (wscript !== null) {    
                wscript.SendKeys("{F11}");    
            }    
        }    
    }
});

jsless.extend({
	eval: function(scriptstr){
		var jsobj = null;
		try{
			jsobj = (new Function("", "return " + scriptstr))();
		} catch (e){
			throw new Error("jsless.eval error.");
		}
		
		return jsobj;
	}
});

jsless.extend({
	parsePageCatelog: function(contentContainer){
		
		var getParentHeaderName = function(headerName){
			var parentHeaderName = null;
			headerName = headerName.toUpperCase();
			switch(headerName){
			case "H1":
				break;
			case "H2":
				parentHeaderName = "H1";
				break;
			case "H3":
				parentHeaderName = "H2";
				break;
			case "H4":
				parentHeaderName = "H3";
				break;
			case "H5":
				parentHeaderName = "H4";
				break;
			case "H6":
				parentHeaderName = "H5";
				break;
			}
			return parentHeaderName;
		};
		
		var ANCHOR_PRE = "page-catalog-";
		var PAGE_HREF = window.location.href.indexOf("#") >= 0 ? 
				window.location.href.substring(0, window.location.href.indexOf("#")) : window.location.href;
		
		var rootDl = jsless.getDl().addClass("page-catalog-dl table-of-contents");
		var headers = contentContainer.find(":header");

		var lastDt = null;
		$.each(headers, function(i,v){
			var hx = $(this);
			var headerName = hx[0].nodeName.toUpperCase();
			var parentHeaderName = getParentHeaderName(headerName);
			
			var headerText = hx.text();
			var anchorName = ANCHOR_PRE + i;
			hx.attr("id", anchorName).addClass("scrollSpy");
			var href = PAGE_HREF + "#" + anchorName;
			var a_header = jsless.getA(headerText, "javascript:void(0)").attr("href", "#"+anchorName).addClass("page-catalog-header-link");
			var dt_header = jsless.getDt().attr("headername", headerName).addClass("page-catalog-header-dt");
			dt_header.append(a_header);
			
			var anchor = jsless.getA().attr("id", anchorName).addClass("page-catalog-header-anchor");
			hx.prepend(anchor);
			
			var dd_header = jsless.getDd().addClass("page-catalog-header-children");
			
			if(parentHeaderName == null){// current header is 'H1'.
				rootDl.append(dt_header).append(dd_header);
			} else {
				var parent = rootDl.find("dt[headername="+parentHeaderName+"]:last");
				if(parent.size() == 0){ // current header is not in DL.
					rootDl.append(dt_header).append(dd_header);
				} else {
					parent.next().append(dt_header).append(dd_header);
				}
			}
		});
		
		return rootDl;
	}
});