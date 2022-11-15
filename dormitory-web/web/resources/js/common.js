/*
  把表单的值转为json:对于复选框，下拉框等一名多值的情况也适用(通过,隔开)
 * serialize得到：uname=admin&pwd=admi&code=&remember=remember&remember=remember2&a=a1&a=a2
       执行此方法后{uname: "admin", pwd: "admi", code: "", remember: "remember2",a:"a1,a2"}
       当表单内容含有&或者=就出问题了，使用serializeArray解决
 */
function str2Json(formSelector){
  var arr=$(formSelector).serializeArray();//"#pageCfg_form"
  var data={};
  $.each(arr,function(i,obj){//[{name:"a",value:"b"},{name:"f",value:"zuqiu"},{name:f,value:"lanqiu"},{name:"uname",value:"赵道稳"},{name:"record",value:"<a href='adf'..."}]
    if(data[obj["name"]]){//复选框可能提交多个
      data[obj["name"]]=data[obj["name"]]+","+obj["value"];
    }else{
      data[obj["name"]]=obj["value"];
    }
  });
  return data;
}