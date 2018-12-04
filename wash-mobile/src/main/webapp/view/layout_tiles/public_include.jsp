 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
 <%@ include file="/view/layout_tiles/include.inc.jsp" %>

    <script type="text/javascript">
    var baseUrl = "${pageContext.request.contextPath}";
    var wxSdkUrl = '${wxSdkUrl}';
    </script> 
    
 	<!--css -->
	<link rel="stylesheet" type="text/css" href="${mytag:getStaticContext('css/reset.css')}"/>
	<link rel="stylesheet" href="http://cdn.bootcss.com/weui/1.1.1/style/weui.min.css">
	<link rel="stylesheet" href="http://cdn.bootcss.com/jquery-weui/1.0.1/css/jquery-weui.min.css">
	<link rel="stylesheet" type="text/css" href="${mytag:getStaticContext('css/simple-line-icons/simple-line-icons.min.css')}">
	<!--js -->
	<script src="${mytag:getStaticContext('js/jquery-1.11.0.js')}" type="text/javascript"></script>
	<script src="http://cdn.bootcss.com/jquery-weui/1.0.1/js/jquery-weui.min.js"></script>

</script>