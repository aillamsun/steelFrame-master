<%@tag pageEncoding="UTF-8"%>
<%@attribute name="title" type="java.lang.String" required="false" %>
<%@attribute name="index" type="java.lang.Boolean" required="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<!--[if lt IE 7 ]><html class="ie ie6" lang="en"> <![endif]-->
<!--[if IE 7 ]><html class="ie ie7" lang="en"> <![endif]-->
<!--[if IE 8 ]><html class="ie ie8" lang="en"> <![endif]-->
<!--[if (gte IE 9)|!(IE)]><!-->
<html lang="en" xmlns="http://www.w3.org/1999/html"> <!--<![endif]-->
<head>
    <meta charset="UTF-8">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
    <meta http-equiv="Cache-Control" content="no-store" />
    <meta http-equiv="Pragma" content="no-cache" />
    <meta http-equiv="Expires" content="0" />
    <meta name="description" content="${SYSNAME}"/>
    <title>${SYSNAME}</title>
    <%--<link rel="icon" href="${ctx}/static/images/favicon.ico">
    <link rel="shortcut icon" href="${ctx}/static/images/favicon.ico">--%>

    <%@include file="/WEB-INF/jsp/common/import-common-css.jspf"%>
    <%@include file="/WEB-INF/jsp/common/import-common-js.jspf"%>

    <script type="text/javascript">
        var currentURL = "${requestScope.currentURL}";
    </script>
</head>
