jQuery.extend(jQuery.validator.messages, {

     required:'必填',
     rangelength:$.validator.format( "必须在 {0} 到 {1}个字符之间" ),
     equalTo:'两次不一致',
     range:$.validator.format( "必须在 {0}到{1}之间" ),
     email:'请输入正确的邮箱格式',
     maxlength:$.validator.format( "最多{0}个" )
})