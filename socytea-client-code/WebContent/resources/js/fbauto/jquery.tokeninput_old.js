(function($){
    var DEFAULT_SETTINGS={
        method:"GET",
        contentType:"json",
        queryParam:"q",
        searchDelay:300,
        minChars:1,
        propertyToSearch:"name",
        jsonContainer:null,
        hintText:"",
        noResultsText:"No results",
        searchingText:"Searching...",
        deleteText:"&times;",
        animateDropdown:true,
        tokenLimit:null,
        tokenDelimiter:",",
        preventDuplicates:false,
        tokenValue:"id",
        prePopulate:null,
        processPrePopulate:false,
        idPrefix:"token-input-",
        resultsFormatter:function(item){
            return"<li>"+item[this.propertyToSearch]+"</li>"
            },
        tokenFormatter:function(item){
            return"<li><p>"+item[this.propertyToSearch]+"</p></li>"
            },
        onResult:null,
        onAdd:null,
        onDelete:null,
        onReady:null
    };
    
    var DEFAULT_CLASSES={
        tokenList:"token-input-list",
        token:"token-input-token",
        tokenDelete:"token-input-delete-token",
        selectedToken:"token-input-selected-token",
        highlightedToken:"token-input-highlighted-token",
        dropdown:"token-input-dropdown",
        dropdownItem:"token-input-dropdown-item",
        dropdownItem2:"token-input-dropdown-item2",
        selectedDropdownItem:"token-input-selected-dropdown-item",
        inputToken:"token-input-input-token"
    };
    
    var POSITION={
        BEFORE:0,
        AFTER:1,
        END:2
    };
    
    var KEY={
        BACKSPACE:8,
        TAB:9,
        ENTER:13,
        ESCAPE:27,
        SPACE:32,
        PAGE_UP:33,
        PAGE_DOWN:34,
        END:35,
        HOME:36,
        LEFT:37,
        UP:38,
        RIGHT:39,
        DOWN:40,
        NUMPAD_ENTER:108,
        COMMA:188
    };
    
    var methods={
        init:function(url_or_data_or_function,options){
            var settings=$.extend({},DEFAULT_SETTINGS,options||{});
            return this.each(function(){
                $(this).data("tokenInputObject",new $.TokenList(this,url_or_data_or_function,settings))
                })
            },
        clear:function(){
            this.data("tokenInputObject").clear();
            return this
            },
        add:function(item){
            this.data("tokenInputObject").add(item);
            return this
            },
        remove:function(item){
            this.data("tokenInputObject").remove(item);
            return this
            },
        get:function(){
            return this.data("tokenInputObject").getTokens()
            }
        };
    
$.fn.tokenInput=function(method){
    if(methods[method]){
        return methods[method].apply(this,Array.prototype.slice.call(arguments,1))
        }else{
        return methods.init.apply(this,arguments)
        }
    };

$.TokenList=function(input,url_or_data,settings){
    if($.type(url_or_data)==="string"||$.type(url_or_data)==="function"){
        settings.url=url_or_data;
        var url=computeURL();
        if(settings.crossDomain===undefined){
            if(url.indexOf("://")===-1){
                settings.crossDomain=false
                }else{
                settings.crossDomain=(location.href.split(/\/+/g)[1]!==url.split(/\/+/g)[1])
                }
            }
    }else{
    if(typeof(url_or_data)==="object"){
        settings.local_data=url_or_data
        }
    }
if(settings.classes){
    settings.classes=$.extend({},DEFAULT_CLASSES,settings.classes)
    }else{
    if(settings.theme){
        settings.classes={};
        
        $.each(DEFAULT_CLASSES,function(key,value){
            settings.classes[key]=value+"-"+settings.theme
            })
        }else{
        settings.classes=DEFAULT_CLASSES
        }
    }
var saved_tokens=[];
var token_count=0;
var cache=new $.TokenList.Cache();
var timeout;
var input_val;
var input_box=$('<input type="text"  autocomplete="off">').css({
    outline:"none"
}).attr("id",settings.idPrefix+input.id).focus(function(){
    if(settings.tokenLimit===null||settings.tokenLimit!==token_count){
        show_dropdown_hint()
        }
    }).blur(function(){
    hide_dropdown();
    $(this).val("")
    }).bind("keyup keydown blur update",resize_input).keydown(function(event){
    var previous_token;
    var next_token;
    switch(event.keyCode){
        case KEY.LEFT:case KEY.RIGHT:case KEY.UP:case KEY.DOWN:
            if(!$(this).val()){
            previous_token=input_token.prev();
            next_token=input_token.next();
            if((previous_token.length&&previous_token.get(0)===selected_token)||(next_token.length&&next_token.get(0)===selected_token)){
                if(event.keyCode===KEY.LEFT||event.keyCode===KEY.UP){
                    deselect_token($(selected_token),POSITION.BEFORE)
                    }else{
                    deselect_token($(selected_token),POSITION.AFTER)
                    }
                }else{
            if((event.keyCode===KEY.LEFT||event.keyCode===KEY.UP)&&previous_token.length){
                select_token($(previous_token.get(0)))
                }else{
                if((event.keyCode===KEY.RIGHT||event.keyCode===KEY.DOWN)&&next_token.length){
                    select_token($(next_token.get(0)))
                    }
                }
        }
    }else{
    var dropdown_item=null;
    if(event.keyCode===KEY.DOWN||event.keyCode===KEY.RIGHT){
        dropdown_item=$(selected_dropdown_item).next()
        }else{
        dropdown_item=$(selected_dropdown_item).prev()
        }
        if(dropdown_item.length){
        select_dropdown_item(dropdown_item)
        }
        return false
    }
    break;
case KEY.BACKSPACE:
    previous_token=input_token.prev();
    if(!$(this).val().length){
    if(selected_token){
        delete_token($(selected_token));
        hidden_input.change()
        }else{
        if(previous_token.length){
            select_token($(previous_token.get(0)))
            }
        }
    return false
}else{
    if($(this).val().length===1){
        hide_dropdown()
        }else{
        setTimeout(function(){
            do_search()
            },5)
        }
    }
break;
case KEY.TAB:case KEY.ENTER:case KEY.NUMPAD_ENTER:case KEY.COMMA:
    if(selected_dropdown_item){
    add_token($(selected_dropdown_item).data("tokeninput"));
    hidden_input.change();
    return false
    }else{
    var txt=$("tester").text();
    if(txt!=""&&validateEmail(txt)){
        var manualtoken={
            value:txt,
            label:txt
        };
        
        add_token(manualtoken);
        hidden_input.change();
        return false
        }
    }
break;
case KEY.ESCAPE:
    hide_dropdown();
    return true;
default:
    if(String.fromCharCode(event.which)){
    setTimeout(function(){
        do_search()
        },5)
    }
    break
}
});
var hidden_input=$(input).hide().val("").focus(function(){
    input_box.focus()
    }).blur(function(){
    input_box.blur()
    });
var selected_token=null;
var selected_token_index=0;
var selected_dropdown_item=null;
var token_list=$("<ul />").addClass(settings.classes.tokenList).click(function(event){
    var li=$(event.target).closest("li");
    if(li&&li.get(0)&&$.data(li.get(0),"tokeninput")){
        toggle_select_token(li)
        }else{
        if(selected_token){
            deselect_token($(selected_token),POSITION.END)
            }
            input_box.focus()
        }
    }).mouseover(function(event){
    var li=$(event.target).closest("li");
    if(li&&selected_token!==this){
        li.addClass(settings.classes.highlightedToken)
        }
    }).mouseout(function(event){
    var li=$(event.target).closest("li");
    if(li&&selected_token!==this){
        li.removeClass(settings.classes.highlightedToken)
        }
    }).insertBefore(hidden_input);
var input_token=$("<li />").addClass(settings.classes.inputToken).appendTo(token_list).append(input_box);
var dropdown=$("<div>").addClass(settings.classes.dropdown).appendTo("body").hide();
var input_resizer=$("<tester/>").insertAfter(input_box).css({
    position:"absolute",
    top:-9999,
    left:-9999,
    width:"auto",
    fontSize:input_box.css("fontSize"),
    fontFamily:input_box.css("fontFamily"),
    fontWeight:input_box.css("fontWeight"),
    letterSpacing:input_box.css("letterSpacing"),
    whiteSpace:"nowrap"
});
hidden_input.val("");
var li_data=settings.prePopulate||hidden_input.data("pre");
if(settings.processPrePopulate&&$.isFunction(settings.onResult)){
    li_data=settings.onResult.call(hidden_input,li_data)
    }
    if(li_data&&li_data.length){
    $.each(li_data,function(index,value){
        insert_token(value);
        checkTokenLimit()
        })
    }
    if($.isFunction(settings.onReady)){
    settings.onReady.call()
    }
    this.clear=function(){
    token_list.children("li").each(function(){
        if($(this).children("input").length===0){
            delete_token($(this))
            }
        })
};

this.add=function(item){
    add_token(item)
    };
    
this.remove=function(item){
    token_list.children("li").each(function(){
        if($(this).children("input").length===0){
            var currToken=$(this).data("tokeninput");
            var match=true;
            for(var prop in item){
                if(item[prop]!==currToken[prop]){
                    match=false;
                    break
                }
            }
            if(match){
            delete_token($(this))
            }
        }
    })
};

this.getTokens=function(){
    return saved_tokens
    };
    
function checkTokenLimit(){
    if(settings.tokenLimit!==null&&token_count>=settings.tokenLimit){
        input_box.hide();
        hide_dropdown();
        return
    }
}
function resize_input(){
    if(input_val===(input_val=input_box.val())){
        return
    }
    var escaped=input_val.replace(/&/g,"&amp;").replace(/\s/g," ").replace(/</g,"&lt;").replace(/>/g,"&gt;");
    input_resizer.html(escaped);
    input_box.width(input_resizer.width()+30)
    }
    function is_printable_character(keycode){
    return((keycode>=48&&keycode<=90)||(keycode>=96&&keycode<=111)||(keycode>=186&&keycode<=192)||(keycode>=219&&keycode<=222))
    }
    function insert_token(item){
    var idvalchk=item;
    var this_token=settings.tokenFormatter(item);
    this_token=$(this_token).addClass(settings.classes.token).insertBefore(input_token);
    $("<span>"+settings.deleteText+"</span>").addClass(settings.classes.tokenDelete).appendTo(this_token).click(function(){
        delete_token($(this).parent());
        hidden_input.change();
        return false
        });
    var token_data={
        id:item.id
        };
        
    token_data[settings.propertyToSearch]=item[settings.propertyToSearch];
    $.data(this_token.get(0),"tokeninput",item);
    saved_tokens=saved_tokens.slice(0,selected_token_index).concat([token_data]).concat(saved_tokens.slice(selected_token_index));
    selected_token_index++;
    update_hidden_input(saved_tokens,hidden_input);
    token_count+=1;
    if(settings.tokenLimit!==null&&token_count>=settings.tokenLimit){
        input_box.hide();
        hide_dropdown()
        }
        return this_token
    }
    function add_token(item){
    var callback=settings.onAdd;
    if(token_count>0&&settings.preventDuplicates){
        var found_existing_token=null;
        token_list.children().each(function(){
            var existing_token=$(this);
            var existing_data=$.data(existing_token.get(0),"tokeninput");
            if(existing_data&&existing_data.id===item.id){
                found_existing_token=existing_token;
                return false
                }
            });
    if(found_existing_token){
        select_token(found_existing_token);
        input_token.insertAfter(found_existing_token);
        input_box.focus();
        return
    }
}
if(settings.tokenLimit==null||token_count<settings.tokenLimit){
    var idvalchk=item;
    var select_eid;
    if(forwardflg_fb!=""&&forwardflg_fb=="byname"){
        select_eid=idvalchk.desc
        }else{
        select_eid=idvalchk.value
        }
        var slNwtMbr;
    var sameidchk="no";
    $(".token-input-token-facebook p").each(function(x){
        slNwtMbr=$(this).attr("id");
        if(slNwtMbr!=undefined&&slNwtMbr!=""&&slNwtMbr==$.trim(select_eid)){
            sameidchk="yes";
            return false
            }
        });
if(sameidchk=="no"&&sameidchk!="yes"){
    insert_token(item)
    }
    checkTokenLimit()
}
input_box.val("");
hide_dropdown();
if($.isFunction(callback)){
    callback.call(hidden_input,item)
    }
}
function select_token(token){
    token.addClass(settings.classes.selectedToken);
    selected_token=token.get(0);
    input_box.val("");
    hide_dropdown()
    }
    function deselect_token(token,position){
    token.removeClass(settings.classes.selectedToken);
    selected_token=null;
    if(position===POSITION.BEFORE){
        input_token.insertBefore(token);
        selected_token_index--
    }else{
        if(position===POSITION.AFTER){
            input_token.insertAfter(token);
            selected_token_index++
        }else{
            input_token.appendTo(token_list);
            selected_token_index=token_count
            }
        }
    input_box.focus()
}
function toggle_select_token(token){
    var previous_selected_token=selected_token;
    if(selected_token){
        deselect_token($(selected_token),POSITION.END)
        }
        if(previous_selected_token===token.get(0)){
        deselect_token(token,POSITION.END)
        }else{
        select_token(token)
        }
    }
function delete_token(token){
    var token_data=$.data(token.get(0),"tokeninput");
    var callback=settings.onDelete;
    var index=token.prevAll().length;
    if(index>selected_token_index){
        index--
    }
    token.remove();
    selected_token=null;
    input_box.focus();
    saved_tokens=saved_tokens.slice(0,index).concat(saved_tokens.slice(index+1));
    if(index<selected_token_index){
        selected_token_index--
    }
    update_hidden_input(saved_tokens,hidden_input);
    token_count-=1;
    if(settings.tokenLimit!==null){
        input_box.show().val("").focus()
        }
        if($.isFunction(callback)){
        callback.call(hidden_input,token_data)
        }
    }
function update_hidden_input(saved_tokens,hidden_input){
    var token_values=$.map(saved_tokens,function(el){
        return el[settings.tokenValue]
        });
    hidden_input.val(token_values.join(settings.tokenDelimiter))
    }
    function hide_dropdown(){
    dropdown.hide().empty();
    selected_dropdown_item=null
    }
    function show_dropdown(){
    dropdown.css({
        position:"absolute",
        top:$(token_list).offset().top+$(token_list).outerHeight(),
        left:$(token_list).offset().left,
        zindex:999
    }).show()
    }
    function show_dropdown_searching(){
    if(settings.searchingText){
        dropdown.html("<p>"+settings.searchingText+"</p>");
        show_dropdown()
        }
    }
function show_dropdown_hint(){
    if(settings.hintText){
        dropdown.html("<p>"+settings.hintText+"</p>");
        show_dropdown()
        }
    }
function highlight_term(value,term){
    return value.replace(new RegExp("(?![^&;]+;)(?!<[^<>]*)("+term+")(?![^<>]*>)(?![^&;]+;)","gi"),"<b>$1</b>")
    }
    function find_value_and_highlight_term(template,value,term){
    return template.replace(new RegExp("(?![^&;]+;)(?!<[^<>]*)("+value+")(?![^<>]*>)(?![^&;]+;)","g"),highlight_term(value,term))
    }
    function populate_dropdown(query,results){
    if(results&&results.length){
        dropdown.empty();
        var dropdown_ul=$("<ul>").appendTo(dropdown).mouseover(function(event){
            select_dropdown_item($(event.target).closest("li"))
            }).mousedown(function(event){
            add_token($(event.target).closest("li").data("tokeninput"));
            hidden_input.change();
            return false
            }).hide();
        $.each(results,function(index,value){
            var this_li=settings.resultsFormatter(value);
            this_li=find_value_and_highlight_term(this_li,value[settings.propertyToSearch],query);
            this_li=$(this_li).appendTo(dropdown_ul);
            if(index%2){
                this_li.addClass(settings.classes.dropdownItem)
                }else{
                this_li.addClass(settings.classes.dropdownItem2)
                }
                if(index===0){
                select_dropdown_item(this_li)
                }
                $.data(this_li.get(0),"tokeninput",value)
            });
        show_dropdown();
        if(settings.animateDropdown){
            dropdown_ul.slideDown("fast")
            }else{
            dropdown_ul.show()
            }
        }else{
    if(settings.noResultsText){
        dropdown.html("<p>"+settings.noResultsText+"</p>");
        show_dropdown()
        }
    }
}
function select_dropdown_item(item){
    if(item){
        if(selected_dropdown_item){
            deselect_dropdown_item($(selected_dropdown_item))
            }
            item.addClass(settings.classes.selectedDropdownItem);
        selected_dropdown_item=item.get(0)
        }
    }
function deselect_dropdown_item(item){
    item.removeClass(settings.classes.selectedDropdownItem);
    selected_dropdown_item=null
    }
    function do_search(){
    var query=input_box.val().toLowerCase();
    if(query&&query.length){
        if(selected_token){
            deselect_token($(selected_token),POSITION.AFTER)
            }
            if(query.length>=settings.minChars){
            show_dropdown_searching();
            clearTimeout(timeout);
            timeout=setTimeout(function(){
                run_search(query)
                },settings.searchDelay)
            }else{
            hide_dropdown()
            }
        }
}
function run_search(query){
    var cache_key=query+computeURL();
    var cached_results=cache.get(cache_key);
    if(cached_results){
        populate_dropdown(query,cached_results)
        }else{
        if(settings.url){
            var url=computeURL();
            var ajax_params={};
            
            ajax_params.data={};
            
            if(url.indexOf("?")>-1){
                var parts=url.split("?");
                ajax_params.url=parts[0];
                var param_array=parts[1].split("&");
                $.each(param_array,function(index,value){
                    var kv=value.split("=");
                    ajax_params.data[kv[0]]=kv[1]
                    })
                }else{
                ajax_params.url=url
                }
                ajax_params.data[settings.queryParam]=query;
            ajax_params.type=settings.method;
            ajax_params.dataType=settings.contentType;
            if(settings.crossDomain){
                ajax_params.dataType="jsonp"
                }
                ajax_params.success=function(results){
                if($.isFunction(settings.onResult)){
                    results=settings.onResult.call(hidden_input,results)
                    }
                    cache.add(cache_key,settings.jsonContainer?results[settings.jsonContainer]:results);
                if(input_box.val().toLowerCase()===query){
                    populate_dropdown(query,settings.jsonContainer?results[settings.jsonContainer]:results)
                    }
                };
            
        $.ajax(ajax_params)
        }else{
        if(settings.local_data){
            var results=$.grep(settings.local_data,function(row){
                return row[settings.propertyToSearch].toLowerCase().indexOf(query.toLowerCase())>-1
                });
            if($.isFunction(settings.onResult)){
                results=settings.onResult.call(hidden_input,results)
                }
                cache.add(cache_key,results);
            populate_dropdown(query,results)
            }
        }
}
}
function computeURL(){
    var url=settings.url;
    if(typeof settings.url=="function"){
        url=settings.url.call()
        }
        return url
    }
};

$.TokenList.Cache=function(options){
    var settings=$.extend({
        max_size:500
    },options);
    var data={};
    
    var size=0;
    var flush=function(){
        data={};
        
        size=0
        };
        
    this.add=function(query,results){
        if(size>settings.max_size){
            flush()
            }
            if(!data[query]){
            size+=1
            }
            data[query]=results
        };
        
    this.get=function(query){
        return data[query]
        }
    }
}(jQuery));
function validateEmail($email){
    var emailReg=/^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    if(!emailReg.test($email)){
        return false
        }else{
        return true
        }
    };