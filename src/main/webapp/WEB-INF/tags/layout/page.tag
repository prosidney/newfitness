<%@ tag description="Page layout" %>
 
<%@ attribute name="title"       required="true" description="Page title" %>
<%@ attribute name="user" 		 required="true" description="User logged" %>
<%@ attribute name="keywords"    required="false" description="Page keywords to improve SEO" %>
<%@ attribute name="description" required="false" description="Page description" %>
<%@ attribute name="extraHeader" fragment="true" description="Extra code to put before </head>" %>
<%@ attribute name="extraBottom" fragment="true" description="Extra code to put before </body>" %>
 
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="layout" %>
<%@ taglib prefix="c" 	 uri="http://java.sun.com/jsp/jstl/core"%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        
		<!-- Le styles -->
		<link href="/css/bootstrap.css" rel="stylesheet">
		<style type="text/css">
		  body {
		    padding-top: 60px;
		    padding-bottom: 40px;
		  }
		</style>
		<link href="/css/bootstrap-responsive.css" rel="stylesheet">
		<link href="/css/ui-darkness/jquery-ui-1.10.3.custom.css" rel="stylesheet">
		
		<!-- Le fav and touch icons -->
		<link rel="shortcut icon" href="../assets/ico/favicon.ico">
		<link rel="apple-touch-icon-precomposed" sizes="144x144" href="../ico/apple-touch-icon-144-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="114x114" href="../ico/apple-touch-icon-114-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="72x72" href="../ico/apple-touch-icon-72-precomposed.png">
		<link rel="apple-touch-icon-precomposed" href="../ico/apple-touch-icon-57-precomposed.png">       
 
        <!-- Displaying the attributes using EL -->
        <meta name="keywords"    content="${keywords}" />
        <meta name="description" content="${description}" />
        <title>${title}</title>
 
        <!-- Essential scripts -->
        <script type="text/javascript" src="/js/jquery-1.9.1.js"></script>
        <script type="text/javascript" src="/js/jquery-ui-1.10.3.custom.js"></script>
        
        <script type="text/javascript">
			function enforceNumericValue(obj){  
				 //  check for valid numeric strings	
				 var strValidChars = "0123456789";
				 var strChar;
				 var blnResult = true;
	
				 // if (strString.length == 0) return false;
	
				 //  test strString consists of valid characters listed above
				 for (i = 0; i < obj.value.length && blnResult == true; i++){
				    strChar = obj.value.charAt(i);
				    if (strValidChars.indexOf(strChar) == -1){
						blnResult = false;
					 	obj.value = obj.value.substring(0, obj.value.length-1);
				    }
				 }
		  	}	
		 </script>
 
        <!-- Process the given input fragment -->
        <jsp:invoke fragment="extraHeader"/>
    </head>
    <body>
        <!-- Main -->
        <div id="wrapper">
            <!-- Renders the page header -->
            <layout:header user="${user}"/>
 
            <!-- Renders the tag body inside a DIV -->
            <div id="content">
            	<c:if test="${errorMessage != null}">
        			<span class="error">
		    			<c:out value="${errorMessage}"/>
        			</span>
        		</c:if>
            	<jsp:doBody/>
            </div>
 
            <!-- Renders the page footer -->
            <layout:footer/>
        </div> <!-- Main -->
 
        <!-- Process the given input fragment -->
        <jsp:invoke fragment="extraBottom"/>
        
        <!-- Le javascript
	    ================================================== -->
	    <!-- Placed at the end of the document so the pages load faster -->
	    <script src="/js/bootstrap.js"></script>
	    <script src="/js/jquery-1.9.1.js"></script>
	    <script src="/js/jquery-ui-1.10.3.custom.js"></script>
	    <script src="/js/bootstrap-transition.js"></script>
	    <script src="/js/bootstrap-alert.js"></script>
	    <script src="/js/bootstrap-modal.js"></script>
	    <script src="/js/bootstrap-dropdown.js"></script>
	    <script src="/js/bootstrap-scrollspy.js"></script>
	    <script src="/js/bootstrap-tab.js"></script>
	    <script src="/js/bootstrap-tooltip.js"></script>
	    <script src="/js/bootstrap-popover.js"></script>
	    <script src="/js/bootstrap-button.js"></script>
	    <script src="/js/bootstrap-collapse.js"></script>
	    <script src="/js/bootstrap-carousel.js"></script>
	    <script src="/js/bootstrap-typeahead.js"></script>
    </body>
</html>

