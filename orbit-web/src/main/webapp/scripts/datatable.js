/**
 * AUTHOR: LIPEI
 */



(function (window, undefined) {
	/*
	 * detect dependencies.
	 * */
    (function () {
        if ((typeof jQuery) == "undefined") {
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
     * column:{field: fieldname, title: headertitle, formatter:func}
     *
     * */
    var datatable = (function () {
        var datatable = function (args) {
        	var options = {
        		tableSelector: null,
        		columns: [],
        		rows: [],
        		recordCount: 0,
        		pageSize: 10,
        		pageIndex: 0,
                getPagerRows: function(pageIndex, pageSize){}
        	};
        	$.extend(options, args);
        	var columnDefault = {
        		field: null,
        		title: null,
        		headFormatter: function(table){},
        		bodyFormatter: function(row, rowdata){}
        	};

            // var ctrl = {
            //     table: null,
            //     pager: null
            // };

            var currPageIndex = 0;

        	// render thead.
        	var render_thead = function(table, columns){
        		var thead = $("<thead></thead>");
        		if(columns && columns.length > 0){
            		var tr = $("<tr></tr>");
            		for(var colIndex = 0; colIndex < columns.length; colIndex++){
            			var column = columns[colIndex];
            			var th = $("<th></th>");
            			if(column["headFormatter"] != undefined){
            				th.append(column["headFormatter"](table));
            			}
            			if(column['field'] != undefined){
            				th.attr("field", column.field);
            			}
            			if(column['title'] != undefined){
            				var title_span = $("<span></span>").text(column.title);
            				th.append(title_span);
            			}
            			tr.append(th);
            		}
            		thead.append(tr);
            	}
        		table.append(thead);
        	};



        	// render tbody.
        	var render_tbody = function(table, columns, rows){
        		var tbody = $("<tbody></tbody>");
        		if(rows && rows.length > 0){

            		for(var rowIndex = 0; rowIndex < rows.length; rowIndex++){
            			var rowdata = rows[rowIndex];
            			var row = $("<tr></tr>");
            			for(var colIndex = 0; colIndex < columns.length; colIndex++){
            				var column = columns[colIndex];
            				var td = $("<td></td>");
            				if(column["bodyFormatter"] != undefined){
            					td.append(column["bodyFormatter"](row, rowdata));
            				} else {

            					td.attr("field", column.field);
            					td.append(rowdata[column.field]);
            				}
            				row.append(td);

            			}
            			tbody.append(row);
            		}
            	}
        		table.append(tbody);
        	};

        	var render_pager = function(recordCount, pageSize, pageIndex){
        		var group = $("<div class='btn-group pager-buttons' role='group'></div>");
        		var pageCount = Math.ceil(recordCount / pageSize);
                var lastPageIndex = pageCount - 1;
                var startShowIndex = 0 < (pageIndex - 2) ? (pageIndex - 2) : 0;
                var endShowIndex = (startShowIndex + 4) < lastPageIndex ?  (startShowIndex + 4) : lastPageIndex;

        		for(var i = 0; i < pageCount; i++){
                    var page_btn = null;
                    // 总是添加第1页按钮
                    if(i == 0){
                        page_btn = $("<button type='button' class='btn pager-button'></button>").text(i + 1).val(i);
                        group.append(page_btn);
                    }

                    if(i == 1 && startShowIndex > 1){
                        page_btn = $("<button type='button' class='btn pager-ellipsis'></button>").text("...").val(i);
                        group.append(page_btn);
                    }

                    if(i != 0 && i != lastPageIndex && i >= startShowIndex && i <= endShowIndex){
                        page_btn = $("<button type='button' class='btn pager-button'></button>").text(i + 1).val(i);
            			group.append(page_btn);
                    }

                    if(i == (lastPageIndex - 1) && (lastPageIndex - endShowIndex) > 1){
                        page_btn = $("<button type='button' class='btn pager-ellipsis'></button>").text("...").val(i);
                        group.append(page_btn);
                    }

                    // if(endShowIndex < (pageCount - 2) && i == (pageCount -2)){
                    //     page_btn = $("<button type='button' class='btn pager-ellipsis'></button>").text("...").val(i);
                    //     group.append(page_btn);
                    // }

                    // 总是添加最后一页按钮
                    if(i != 0 && i == (pageCount -1)){
                        page_btn = $("<button type='button' class='btn pager-button'></button>").text(i + 1).val(i);
                        group.append(page_btn);
                    }


                    if(page_btn){
                        if(i == pageIndex){
                            page_btn.addClass("btn-primary").attr("disabled", "disabled");
                        } else {
                            page_btn.addClass("btn-default");
                        }
                    }

        		}
                group.delegate(".btn", "click", function(){
                    var targetPageIndex = parseInt($(this).val());
                    options.getPagerRows(targetPageIndex, pageSize);
                });
                currPageIndex = pageIndex;
        		return group;
        	};

        	// render table.
        	this.render = function(){
        		var table = $(options.tableSelector);
                table.empty();
                if(table.next().is('.pager-buttons')){
                    table.next().remove();
                }

        		columns = options.columns;
        		rows = options.rows;
        		render_thead(table, columns);
        		render_tbody(table, columns, rows);
                var pager = render_pager(options.recordCount, options.pageSize, options.pageIndex);
                table.after(pager);
        		return table;
        	};

        	// reload data.
        	this.reload = function(pageIndex, data){
        		var table = $(options.tableSelector);
        		columns = options.columns;
        		if(table.find("tbody").size() > 0){
        			table.find("tbody").empty();
        		}
                if(table.next().is('.pager-buttons')){
                    table.next().remove();
                }
        		render_tbody(table, columns, data);
                var pager = render_pager(options.recordCount, options.pageSize, pageIndex);
                table.after(pager);
        		return table;
        	};

            this.getPageIndex = function(){
                return currPageIndex;
            };

        };

        datatable.fn = datatable.prototype = {
            constructor: datatable
        };

        return datatable;
    })();
    window.datatable = datatable;
})(window);
